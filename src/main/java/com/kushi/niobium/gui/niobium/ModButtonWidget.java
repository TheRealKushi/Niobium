package com.kushi.niobium.gui.niobium;

import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ModButtonWidget extends ClickableWidget {
	public static final int DEFAULT_WIDTH_SMALL = 120;
	public static final int DEFAULT_WIDTH = 150;
	public static final int DEFAULT_HEIGHT = 20;
	protected static final NarrationSupplier DEFAULT_NARRATION_SUPPLIER = supplier -> supplier.get();

	private final PressAction onPress;
	private final NarrationSupplier narrationSupplier;

	public ModButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
		super(x, y, width, height, message);
		this.onPress = onPress;
		this.narrationSupplier = narrationSupplier;
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		this.onPress.onPress(this);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder narrationMessageBuilder) {
		this.appendDefaultNarrations(narrationMessageBuilder);
	}

	@Override
	protected MutableText getNarrationMessage() {
		return this.narrationSupplier.createNarrationMessage(() -> super.getNarrationMessage());
	}

	@Override
	protected void renderWidget(DrawContext drawContext, int i, int j, float f) {

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
		private ModButtonWidget.NarrationSupplier narrationSupplier = ModButtonWidget.DEFAULT_NARRATION_SUPPLIER;

		public Builder(Text message, PressAction onPress) {
			this.message = message;
			this.onPress = onPress;
		}

		public ModButtonWidget.Builder position(int i, int j) {
			this.x = i;
			this.y = j;
			return this;
		}

		public ModButtonWidget.Builder width(int i) {
			this.width = i;
			return this;
		}

		public ModButtonWidget.Builder size(int i, int j) {
			this.width = i;
			this.height = j;
			return this;
		}

		public ModButtonWidget.Builder dimensions(int i, int j, int k, int l) {
			return this.position(i, j).size(k, l);
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
