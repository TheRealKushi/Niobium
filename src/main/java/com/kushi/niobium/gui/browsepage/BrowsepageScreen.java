package com.kushi.niobium.gui.browsepage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kushi.niobium.Niobium;
import com.kushi.niobium.gui.niobium.ModButtonWidget;
import com.kushi.niobium.modrinth.ModrinthAPI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import okhttp3.Request;
import okhttp3.Response;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BrowsepageScreen extends Screen {

    private TextFieldWidget searchField;
    private final int itemsPerPage = 10;
    private int currentPage = 0;
    private int scrollOffset = 0;
    protected final Screen parent;
    private final List<ModInfo> modList = new ArrayList<>();

    public BrowsepageScreen(Screen screen) {
        super(Text.translatable("modAddons.title"));
        this.parent = screen;
    }

    public static class ModInfo {
        private final String id;
        private final String name;
        private final String description;
        private final String iconUrl;

        public ModInfo(String id, String name, String description, String iconUrl) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.iconUrl = iconUrl;
        }

        public String getId() {
            return id;
        }

        public String getDisplayText() {
            return name + " - " + description;
        }

        public String getIconUrl() {
            return iconUrl;
        }
    }

    @Override
    protected void init() {
        super.init();
        searchField = new TextFieldWidget(textRenderer, this.width / 2 - 100, 20, 200, 20, Text.translatable("modAddons.search"));
        addDrawableChild(searchField);

        addNavigationButtons();
        populateMods("");
    }

    private void addNavigationButtons() {
        int buttonY = this.height - 60;

        addDrawableChild(
                new ModButtonWidget.Builder(Text.literal("Previous"), btn -> {
                    changePage(-1); // Go to previous page
                })
                        .dimensions(this.width - 110, buttonY, 100, 20)
                        .build()
        );

        addDrawableChild(
                new ModButtonWidget.Builder(Text.literal("Next"), btn -> {
                    changePage(1); // Go to next page
                })
                        .dimensions(this.width - 110, buttonY + 30, 100, 20)
                        .build()
        );
    }

    private void refreshModButtons() {
        // Remove old buttons
        this.children().removeIf(widget -> widget instanceof ButtonWidget && !Objects.equals(((ButtonWidget) widget).getMessage().getString(), "Previous") && !Objects.equals(((ButtonWidget) widget).getMessage().getString(), "Next"));

        // Add new buttons for current page
        int startIndex = currentPage * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, modList.size());
        int y = 50 - scrollOffset;

        for (int i = startIndex; i < endIndex; i++) {
            ModInfo modInfo = modList.get(i);

            ButtonWidget downloadButton = ButtonWidget.builder(Text.literal("Download"), button -> {
                if (!button.getMessage().getString().equals("Downloaded")) {
                    Niobium.LOGGER.info("Downloading mod: {}", modInfo.getDisplayText());
                    downloadMod(modInfo);
                    button.setMessage(Text.literal("Downloaded"));
                }
            }).dimensions(this.width - 110, y, 100, 20).build();

            addDrawableChild(downloadButton);
            y += 60;
        }
    }

    private void changePage(int delta) {
        int maxPage = (modList.size() - 1) / itemsPerPage;
        currentPage = Math.max(0, Math.min(maxPage, currentPage + delta));
        refreshModButtons();
    }

    private void populateMods(String query) {
        if (query == null || query.trim().isEmpty()) {
            query = "";
        }

        try {
            String jsonResponse = ModrinthAPI.fetchMods(query);

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonArray hits = jsonObject.getAsJsonArray("hits");

            modList.clear();

            for (int i = 0; i < hits.size(); i++) {
                JsonObject mod = hits.get(i).getAsJsonObject();
                String modId = mod.get("project_id").getAsString();
                String modName = mod.get("title").getAsString();
                String modDescription = mod.get("description").getAsString();
                String iconUrl = mod.has("icon_url") ? mod.get("icon_url").getAsString() : "";
                modList.add(new ModInfo(modId, modName, modDescription, iconUrl));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] convertWebpToPng(byte[] webpData) throws IOException {
        try (ByteArrayInputStream webpInputStream = new ByteArrayInputStream(webpData)) {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByMIMEType("image/webp");
            if (!readers.hasNext()) {
                throw new IOException("No WebP reader available");
            }
            ImageReader reader = readers.next();
            ImageInputStream input = ImageIO.createImageInputStream(webpInputStream);
            reader.setInput(input, true);
            BufferedImage webpImage = reader.read(0);

            if (webpImage == null) {
                throw new IOException("Failed to read WebP image. Image data might be corrupt or unsupported.");
            }

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            ImageIO.write(webpImage, "png", pngOutputStream);
            return pngOutputStream.toByteArray();
        } catch (Exception e) {
            throw new IOException("Error converting WebP to PNG", e);
        }
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (searchField.charTyped(chr, modifiers)) {
            populateMods(searchField.getText());
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    private final Map<String, Identifier> iconCache = new HashMap<>();

    private boolean isValidPng(byte[] imageData) {
        return imageData.length >= 8 &&
                imageData[0] == (byte) 0x89 &&
                imageData[1] == 0x50 &&
                imageData[2] == 0x4E &&
                imageData[3] == 0x47 &&
                imageData[4] == 0x0D &&
                imageData[5] == 0x0A &&
                imageData[6] == 0x1A &&
                imageData[7] == 0x0A;
    }

    private Identifier getOrDownloadIcon(String url) {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            Path cacheDir = Paths.get(client.runDirectory.getPath(), "modrinth_cache");
            Files.createDirectories(cacheDir);

            String fileName = Integer.toHexString(url.hashCode()) + ".png";
            Path iconPath = cacheDir.resolve(fileName);

            if (iconCache.containsKey(fileName)) {
                return iconCache.get(fileName);
            }

            if (!Files.exists(iconPath)) {
                Request request = new Request.Builder().url(url).build();
                try (Response response = ModrinthAPI.CLIENT.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body() != null) {
                        byte[] imageData = response.body().bytes();
                        if (url.endsWith(".webp")) {
                            imageData = convertWebpToPng(imageData);
                        }
                        Files.write(iconPath, imageData);
                    } else {
                        return Identifier.of("niobium", "default_icon.png");
                    }
                }
            }

            byte[] imageData = Files.readAllBytes(iconPath);
            if (!isValidPng(imageData)) {
                throw new IOException("Invalid PNG file format: " + iconPath);
            }

            try (FileInputStream inputStream = new FileInputStream(iconPath.toFile())) {
                NativeImage nativeImage = NativeImage.read(inputStream);
                NativeImageBackedTexture texture = new NativeImageBackedTexture(nativeImage);
                Identifier identifier = Identifier.of("modrinth_cache", fileName);
                client.getTextureManager().registerTexture(identifier, texture);
                iconCache.put(fileName, identifier);
                return identifier;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Identifier.of("niobium", "default_icon.png");
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        searchField.render(context, mouseX, mouseY, delta);

        int startIndex = currentPage * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, modList.size());
        int y = 50 - scrollOffset;

        for (int i = startIndex; i < endIndex; i++) {
            ModInfo modInfo = modList.get(i);
            if (y >= 50 && y <= this.height - 50) {
                renderWrappedText(context, modInfo.getDisplayText(), 10, y, this.width - 140);

                if (!modInfo.getIconUrl().isEmpty()) {
                    Identifier iconId = getOrDownloadIcon(modInfo.getIconUrl());
                    context.drawTexture(iconId, 10, y + 20, 0.0F, 0.0F, 32, 32, 16, 16);
                }
            }
            y += 60;
        }
    }

    private void downloadMod(ModInfo modInfo) {
        new Thread(() -> {
            try {
                String modId = modInfo.getId();
                String versionUrl = "https://api.modrinth.com/v2/project/" + modId + "/version";

                Request versionRequest = new Request.Builder().url(versionUrl).build();
                Response versionResponse = ModrinthAPI.CLIENT.newCall(versionRequest).execute();

                assert this.client != null;
                if (versionResponse.isSuccessful()) {
                    assert versionResponse.body() != null;
                    String versionJson = versionResponse.body().string();

                    try {
                        JsonObject versionData = JsonParser.parseString(versionJson).getAsJsonObject();
                        JsonArray versions = versionData.getAsJsonArray("versions");

                        if (versions != null && !versions.isEmpty()) {
                            JsonObject latestVersion = versions.get(0).getAsJsonObject();

                            if (latestVersion.has("id") && latestVersion.has("version_number")) {
                                String versionName = latestVersion.get("version_number").getAsString(); // Adjust if the key is different
                                System.out.println("Latest Version Name: " + versionName);

                                String downloadUrl = latestVersion.get("url").getAsString();

                                downloadModFile(modInfo, downloadUrl);
                            } else {
                                System.err.println("Required fields are missing in the response.");
                            }
                        } else {
                            System.err.println("No versions found in the response.");
                        }
                    } catch (Exception e) {
                        System.err.println("Failed to parse the version JSON response: " + e.getMessage());
                    }
                } else {
                    System.err.println("Failed to fetch mod version information: " + versionResponse.code());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void downloadModFile(ModInfo modInfo, String downloadUrl) {
        new Thread(() -> {
            try {
                Request request = new Request.Builder().url(downloadUrl).build();
                Response response = ModrinthAPI.CLIENT.newCall(request).execute();

                assert this.client != null;
                if (response.isSuccessful()) {

                    Path modsFolder = Paths.get(this.client.runDirectory.getPath(), "mods");
                    Files.createDirectories(modsFolder);

                    Path modFile = modsFolder.resolve(modInfo.getId() + ".jar");
                    assert response.body() != null;
                    Files.write(modFile, response.body().bytes());

                    this.client.execute(() -> {
                        if (this.client.inGameHud != null) {
                            this.client.inGameHud.getChatHud().addMessage(Text.literal("Downloaded " + modInfo.getDisplayText() + " successfully!"));
                        }
                    });
                } else {
                    this.client.execute(() -> {
                        if (this.client.inGameHud != null) {
                            this.client.inGameHud.getChatHud().addMessage(Text.literal("Failed to download: " + response.code() + " " + response.message()));
                        }
                    });
                    System.err.println("Failed to download mod: " + response.code() + " " + response.message());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void renderWrappedText(DrawContext context, String text, int x, int y, int maxWidth) {
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
        List<String> lines = wrapText(renderer, text, maxWidth);
        int lineHeight = 10;
        for (String line : lines) {
            context.drawText(renderer, line, x, y, 0xFFFFFF, false);
            y += lineHeight;
        }
    }

    private List<String> wrapText(TextRenderer renderer, String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        for (String word : text.split(" ")) {
            String lineWithWord = currentLine + (currentLine.length() == 0 ? "" : " ") + word;
            if (renderer.getWidth(lineWithWord) > maxWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            } else {
                if (currentLine.length() > 0) currentLine.append(" ");
                currentLine.append(word);
            }
        }
        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString());
        }
        return lines;
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        scrollOffset -= amount * 10;
        scrollOffset = Math.max(0, scrollOffset);
        return true;
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(parent);
    }
}