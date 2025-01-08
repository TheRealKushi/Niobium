package com.kushi.niobium.gui.niobium;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ModCheckboxWidget extends PressableWidget {
	private static final Identifier SELECTED_HIGHLIGHTED_TEXTURE = Identifier.of("minecraft", "textures/gui/widget/checkbox_selected_highlighted.png");
	private static final Identifier SELECTED_TEXTURE = Identifier.of("minecraft", "textures/gui/widget/checkbox_selected.png");
	private static final Identifier HIGHLIGHTED_TEXTURE = Identifier.of("minecraft", "textures/gui/widget/checkbox_highlighted.png");
	private static final Identifier TEXTURE = Identifier.of("minecraft", "textures/gui/widget/checkbox.png");
	private static final int TEXT_COLOR = 14737632;
	private boolean checked;
	private final Callback callback;
	private final Text message;
	private final TextRenderer textRenderer;

	public ModCheckboxWidget(int x, int y, int width, Text message, TextRenderer textRenderer, boolean checked, Callback callback) {
		super(x, y, width, 20, message);
		this.message = message;
		this.textRenderer = textRenderer;
		this.checked = checked;
		this.callback = callback;
	}

	@Override
	public void onPress() {
		this.checked = !this.checked;
		this.callback.onValueChange(this, this.checked);
	}

	public boolean isChecked() {
		return this.checked;
	}

	@Override
	public void appendClickableNarrations(NarrationMessageBuilder builder) {
		builder.put(NarrationPart.TITLE, this.getNarrationMessage());
		builder.put(NarrationPart.USAGE, Text.translatable("narration.checkbox.usage"));
	}

	@Override
	public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		MinecraftClient client = MinecraftClient.getInstance();
		RenderSystem.enableDepthTest();
		RenderSystem.enableBlend();

		Identifier texture = this.checked ? (this.isHovered() ? SELECTED_HIGHLIGHTED_TEXTURE : SELECTED_TEXTURE) : (this.isHovered() ? HIGHLIGHTED_TEXTURE : TEXTURE);
		context.drawGuiTexture(texture, this.getX(), this.getY(), 0, 0, 20, 20, 20, 20);

		int textX = this.getX() + 24;
		int textY = this.getY() + (this.height - 8) / 2;
		context.drawText(this.textRenderer, this.message, textX, textY, TEXT_COLOR, false);
	}

	@Environment(EnvType.CLIENT)
	public interface Callback {
		void onValueChange(ModCheckboxWidget widget, boolean checked);
	}

	public static class Builder {
		private final Text message;
		private final TextRenderer textRenderer;
		private int x;
		private int y;
		private int width = 150;
		private boolean checked;
		private Callback callback = (widget, state) -> {};
		@Nullable
		private Tooltip tooltip;

		public Builder(Text message, TextRenderer textRenderer) {
			this.message = message;
			this.textRenderer = textRenderer;
		}

		public Builder pos(int x, int y) {
			this.x = x;
			this.y = y;
			return this;
		}

		public Builder width(int width) {
			this.width = width;
			return this;
		}

		public Builder checked(boolean checked) {
			this.checked = checked;
			return this;
		}

		public Builder callback(Callback callback) {
			this.callback = callback;
			return this;
		}

		public Builder tooltip(@Nullable Tooltip tooltip) {
			this.tooltip = tooltip;
			return this;
		}

		public ModCheckboxWidget build() {
			ModCheckboxWidget widget = new ModCheckboxWidget(this.x, this.y, this.width, this.message, this.textRenderer, this.checked, this.callback);
			if (this.tooltip != null) {
				widget.setTooltip(this.tooltip);
			}
			return widget;
		}
	}
}