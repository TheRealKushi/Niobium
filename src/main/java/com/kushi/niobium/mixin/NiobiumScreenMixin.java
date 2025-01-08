package com.kushi.niobium.mixin;

import com.kushi.niobium.gui.niobium.NiobiumOptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public abstract class NiobiumScreenMixin extends Screen {

	protected NiobiumScreenMixin(Text text) {
		super(text);
	}

	@Inject(at = @At("RETURN"), method = "init")
	private void addCustomButton(CallbackInfo ci) {
		// Positioning the custom Niobium button dynamically
		int buttonWidth = 210;
		int buttonHeight = 20;
		int centerX = this.width / 2 - buttonWidth / 2;

		// Adding the custom Niobium options button
		this.addDrawableChild(
				ButtonWidget.builder(Text.translatable("menu.niobium"), buttonWidget -> {
							// Open NiobiumOptionsScreen with CreateWorldScreen as the parent
                            assert this.client != null;
                            this.client.setScreen(new NiobiumOptionsScreen(this));
						})
						.dimensions(centerX, this.height / 2 + 40, buttonWidth, buttonHeight) // Dynamic position
						.build()
		);
	}
}
