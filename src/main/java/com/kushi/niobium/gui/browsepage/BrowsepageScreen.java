package com.kushi.niobium.gui.browsepage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kushi.niobium.Niobium;
import com.kushi.niobium.gui.niobium.ModButtonWidget;
import com.kushi.niobium.modrinth.ModrinthAPI;
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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
        addSelectableChild(searchField);

        addNavigationButtons();
        populateMods("");
    }

    private void addNavigationButtons() {
// Adjust button positioning dynamically
        int buttonY = this.height - 60; // Position buttons near the bottom of the screen

// Previous button
        addDrawableChild(new ModButtonWidget(
                this.width - 110,
                buttonY,  // Dynamically calculated y-coordinate for Previous button
                100,
                20,
                Text.of("Previous"),
                button -> changePage(-1),
                Supplier::get
        ));

// Next button
        addDrawableChild(new ModButtonWidget(
                this.width - 110,
                buttonY + 30,  // Position the next button 30px below
                100,
                20,
                Text.of("Next"),
                button -> changePage(1),
                Supplier::get
        ));
    }

    private void changePage(int delta) {
        int maxPage = (modList.size() - 1) / itemsPerPage;
        currentPage = Math.max(0, Math.min(maxPage, currentPage + delta));
    }

    private void populateMods(String query) {
        if (query == null || query.trim().isEmpty()) {
            query = ""; // Ensures empty query triggers the default behavior for loading mods
        }

        try {
            // Call to Modrinth API to fetch mods based on the query
            String jsonResponse = ModrinthAPI.fetchMods(query);

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonArray hits = jsonObject.getAsJsonArray("hits"); // Parse as JsonArray

            modList.clear(); // Clear current list before populating with fresh data

            // Iterate through each mod and add to the modList
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
            ImageReader reader = ImageIO.getImageReadersByMIMEType("image/webp").next();
            ImageInputStream input = ImageIO.createImageInputStream(webpInputStream);
            reader.setInput(input, true);
            BufferedImage webpImage = reader.read(0);  // Read WebP image

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
            assert client != null;
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
                        Files.write(iconPath, imageData);  // Save to cache
                    } else {
                        return Identifier.of("assets/niobium/default_icon.png");
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
            return Identifier.of("assets/niobium/default_icon.png");
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
                    context.drawTexture(iconId, 10, y + 20, 32, 32, 0.0F, 0.0F, 16, 16, 16, 16);
                }

                // Create a download button only for the current mod
                ButtonWidget downloadButton = ButtonWidget.builder(Text.of("Download"), button -> {
                    // Change text if it's not "Downloaded"
                    if (!button.getMessage().getString().equals("Downloaded")) {
                        Niobium.LOGGER.info("Downloading mod: {}", modInfo.getDisplayText());
                        downloadMod(modInfo);  // Start the download
                        button.setMessage(Text.of("Downloaded"));  // Update button text
                    }
                }).dimensions(this.width - 110, y, 100, 20).build();
                addDrawableChild(downloadButton);
            }
            y += 60;  // Increase the y-coordinate for the next item
        }
    }

    private void downloadMod(ModInfo modInfo) {
        new Thread(() -> {
            try {
                // Fetch the mod details
                String modId = modInfo.getId();
                String versionUrl = "https://api.modrinth.com/v2/project/" + modId + "/version";

                // Fetch the mod versions
                Request versionRequest = new Request.Builder().url(versionUrl).build();
                Response versionResponse = ModrinthAPI.CLIENT.newCall(versionRequest).execute();

                assert client != null;
                if (versionResponse.isSuccessful()) {
                    assert versionResponse.body() != null;
                    String versionJson = versionResponse.body().string();

                    // Attempt to parse the JSON and check if it is valid
                    try {
                        JsonObject versionData = JsonParser.parseString(versionJson).getAsJsonObject();
                        JsonArray versions = versionData.getAsJsonArray("versions");

                        // Check if "versions" is not null or empty
                        if (versions != null && !versions.isEmpty()) {
                            JsonObject latestVersion = versions.get(0).getAsJsonObject();

                            // Validate that the necessary fields exist
                            if (latestVersion.has("id") && latestVersion.has("version_number")) {
                                String versionId = latestVersion.get("id").getAsString();
                                String versionName = latestVersion.get("version_number").getAsString(); // Adjust if the key is different

                                // Log or display the latest version name
                                System.out.println("Latest Version Name: " + versionName);

                                // Construct the URL to download the mod file using the project and version ID
                                String downloadUrl = "https://cdn.modrinth.com/data/" + modId + "/versions/" + versionId + "/files/" + versionName;
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

    // Helper method to download the mod file
    private void downloadModFile(ModInfo modInfo, String downloadUrl) {
        new Thread(() -> {
            try {
                Request request = new Request.Builder().url(downloadUrl).build();
                Response response = ModrinthAPI.CLIENT.newCall(request).execute();

                assert client != null;
                if (response.isSuccessful()) {
                    // Proceed with downloading

                    Path modsFolder = Paths.get(client.runDirectory.getPath(), "mods");
                    Files.createDirectories(modsFolder);

                    Path modFile = modsFolder.resolve(modInfo.getId() + ".jar");
                    assert response.body() != null;
                    Files.write(modFile, response.body().bytes());

                    // Send success message
                    client.execute(() -> {
                        if (client.player != null) {
                            client.player.sendMessage(Text.of("Downloaded " + modInfo.getDisplayText() + " successfully!"));
                        }
                    });
                } else {
                    // Handle failure with error details
                    client.execute(() -> {
                        if (client.player != null) {
                            client.player.sendMessage(Text.of("Failed to download: " + response.code() + " " + response.message()));
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
        List<String> lines = breakTextIntoLines(text, maxWidth);
        for (int i = 0; i < lines.size(); i++) {
            context.drawTextWithShadow(this.textRenderer, lines.get(i), x, y + i * textRenderer.fontHeight, 0xFFFFFF);
        }
    }

    private List<String> breakTextIntoLines(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine.isEmpty() ? word : currentLine + " " + word;
            if (textRenderer.getWidth(testLine) <= maxWidth) {
                currentLine.append(" ").append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }

        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        // Adjust scroll offset based on the scroll direction
        if (amount > 0) {
            scrollOffset = Math.max(scrollOffset - 20, 0);  // Prevent scrolling above the top
        } else {
            scrollOffset = Math.min(scrollOffset + 20, (modList.size() - 1) / itemsPerPage * 60);  // Prevent scrolling below the bottom
        }
        return true;  // Return true to indicate that the scroll event has been handled
    }

    @Override
    public void close() {
        assert client != null;
        client.setScreen(this.parent);
    }
}
