package com.kushi.niobium.gui.niobium;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModCheckboxWidget extends ClickableWidget {
	private static final Identifier SELECTED_HIGHLIGHTED_TEXTURE = Identifier.ofVanilla("textures/gui/widget/checkbox_selected_highlighted.png");
	private static final Identifier SELECTED_TEXTURE = Identifier.ofVanilla("textures/gui/widget/checkbox_selected.png");
	private static final Identifier HIGHLIGHTED_TEXTURE = Identifier.ofVanilla("textures/gui/widget/checkbox_highlighted.png");
	private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/widget/checkbox.png");
	private static final int TEXT_COLOR = 0xE0E0E0;

	private boolean checked;
	private final Callback callback;
	private final TextRenderer textRenderer;
	private final Text message;

	public ModCheckboxWidget(int x, int y, int width, Text message, TextRenderer textRenderer, boolean checked, Callback callback) {
		super(x, y, width, 20, message);
		this.message = message;
		this.textRenderer = textRenderer;
		this.checked = checked;
		this.callback = callback;
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		this.checked = !this.checked;
		this.callback.onValueChange(this, this.checked);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
		builder.put(NarrationPart.TITLE, this.getMessage());
		builder.put(NarrationPart.USAGE, Text.translatable("narration.checkbox.usage"));
	}

	public boolean isChecked() {
		return this.checked;
	}

	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		Identifier texture = this.checked
				? (this.isHovered() ? SELECTED_HIGHLIGHTED_TEXTURE : SELECTED_TEXTURE)
				: (this.isHovered() ? HIGHLIGHTED_TEXTURE : TEXTURE);
		context.drawTexture(texture, this.getX(), this.getY(), 0, 0, 20, 20, 20, 20);

		int textX = this.getX() + 24;
		int textY = this.getY() + (this.height - 8) / 2;
		context.drawText(this.textRenderer, this.message, textX, textY, TEXT_COLOR, false);
	}

	@FunctionalInterface
	public interface Callback {
		void onValueChange(ModCheckboxWidget widget, boolean checked);
	}
}