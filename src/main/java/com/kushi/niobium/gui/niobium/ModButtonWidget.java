package com.kushi.niobium.gui.niobium;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class ModButtonWidget extends ButtonWidget {
	public static final int DEFAULT_WIDTH_SMALL = 120;
	public static final int DEFAULT_WIDTH = 150;
	public static final int DEFAULT_HEIGHT = 20;
	protected static final NarrationSupplier DEFAULT_NARRATION_SUPPLIER = Supplier::get;

	private final NarrationSupplier narrationSupplier;

	public ModButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
		super(x, y, width, height, message, btn -> onPress.onPress((ModButtonWidget) btn), DEFAULT_NARRATION_SUPPLIER::createNarrationMessage);
		this.narrationSupplier = narrationSupplier;
	}

	@Override
	public void appendClickableNarrations(NarrationMessageBuilder builder) {
		this.appendDefaultNarrations(builder);
	}

	@Override
	public MutableText getNarrationMessage() {
		return this.narrationSupplier.createNarrationMessage(super::getNarrationMessage);
	}

	@Environment(EnvType.CLIENT)
	public static class Builder {
		private final Text message;
		private final PressAction onPress;
		@Nullable
		private Tooltip tooltip;
		private int x;
		private int y;
		private int width = DEFAULT_WIDTH;
		private int height = DEFAULT_HEIGHT;
		private NarrationSupplier narrationSupplier = DEFAULT_NARRATION_SUPPLIER;

		public Builder(Text message, PressAction onPress) {
			this.message = message;
			this.onPress = onPress;
		}

		public Builder position(int x, int y) {
			this.x = x;
			this.y = y;
			return this;
		}

		public Builder width(int width) {
			this.width = width;
			return this;
		}

		public Builder size(int width, int height) {
			this.width = width;
			this.height = height;
			return this;
		}

		public Builder dimensions(int x, int y, int width, int height) {
			return this.position(x, y).size(width, height);
		}

		public Builder tooltip(@Nullable Tooltip tooltip) {
			this.tooltip = tooltip;
			return this;
		}

		public Builder narrationSupplier(NarrationSupplier narrationSupplier) {
			this.narrationSupplier = narrationSupplier;
			return this;
		}

		public ModButtonWidget build() {
			ModButtonWidget widget = new ModButtonWidget(x, y, width, height, message, onPress, narrationSupplier);
			widget.setTooltip(tooltip);
			return widget;
		}
	}

	@Environment(EnvType.CLIENT)
	public interface NarrationSupplier {
		MutableText createNarrationMessage(Supplier<MutableText> supplier);
	}

	@Environment(EnvType.CLIENT)
	public interface PressAction {
		void onPress(ModButtonWidget buttonWidget);
	}
}
