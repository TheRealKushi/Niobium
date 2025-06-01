package com.kushi.niobium.gui.niobium;

import com.kushi.niobium.gui.browsepage.BrowsepageScreen;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.slf4j.Logger;

@Environment(EnvType.CLIENT)
public class NiobiumOptionsScreen extends Screen {

    private static final Logger LOGGER = LogUtils.getLogger();
    protected final Screen parent;

    public NiobiumOptionsScreen(Screen parent) {
        super(Text.translatable("niobiumOptions.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        // "Enable Custom World Gen" button
        int buttonWidth = 200;
        int buttonHeight = 20;
        int centerX = this.width / 2 - buttonWidth / 2;

        this.addDrawableChild(
                CyclingButtonWidget.builder(value -> Text.translatable(value.toString()))
                        .values("Enabled", "Disabled")
                        .values(ModConfig.isOptionEnabled("enable_custom_world_gen") ? "Enabled" : "Disabled")
                        .build(centerX, this.height / 6, buttonWidth, buttonHeight,
                                Text.translatable("niobiumOptions.enable_custom_world_gen"),
                                (button, value) -> {
                                    boolean enabled = value.equals("Enabled");
                                    ModConfig.setOption("enable_custom_world_gen", enabled);
                                })
        );

        this.addDrawableChild(
                CyclingButtonWidget.builder(value -> Text.translatable(value.toString()))
                        .values("Enabled", "Disabled")
                        .values(ModConfig.isOptionEnabled("enable_custom_potions") ? "Enabled" : "Disabled")
                        .build(centerX, this.height / 6 + buttonHeight + 10, buttonWidth, buttonHeight,
                                Text.translatable("niobiumOptions.enable_custom_potions"),
                                (button, value) -> {
                                    boolean enabled = value.equals("Enabled");
                                    ModConfig.setOption("enable_custom_potions", enabled);
                                })
        );

        // Mod Addons button position adjusted dynamically
        int modAddonsButtonY = this.height / 6 + buttonHeight + 40; // Positioned below the other buttons
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("modGui.addons"), button -> {
                    assert this.client != null;
                    this.client.setScreen(new BrowsepageScreen(this));
                })
                .dimensions(centerX, modAddonsButtonY, buttonWidth, buttonHeight)
                .build());

        // Back button positioned at the bottom of the screen
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.back"), button -> {
                    assert this.client != null;
                    this.client.setScreen(this.parent);
                })
                .dimensions(centerX, this.height - 38, buttonWidth, buttonHeight)
                .build());
    }

    @Override
    public void close() {
        assert this.client != null;
        this.client.setScreen(this.parent);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        super.render(drawContext, mouseX, mouseY, delta);
        drawContext.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 8, 0xFFFFFF);
    }
}