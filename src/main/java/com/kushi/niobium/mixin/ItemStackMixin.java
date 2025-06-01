package com.kushi.niobium.mixin;

import com.kushi.niobium.potion.ModPotions;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
    private void modifyPotionRarity(CallbackInfoReturnable<Rarity> cir) {
        ItemStack stack = (ItemStack) (Object) this;

        // Check if the item is a potion
        if (stack.getItem() instanceof PotionItem) {
            // Get potion data using DataComponentTypes.POTION_CONTENTS
            PotionContentsComponent potionComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionComponent != null) {
                // Check if the potion contains the specific effects of the Golden Apple Potion
                boolean hasGoldenAppleEffects = false;

                // Check for Absorption effect
                for (StatusEffectInstance effectInstance : potionComponent.getEffects()) {
                    if (effectInstance.getEffectType() == StatusEffects.ABSORPTION) {
                        hasGoldenAppleEffects = true;
                    }
                    // Check for Regeneration effect (Level 1)
                    if (effectInstance.getEffectType() == StatusEffects.REGENERATION && effectInstance.getAmplifier() == 1) {
                        hasGoldenAppleEffects = true;
                    }
                }

                // If it has the Golden Apple Potion effects, set the rarity to UNCOMMON
                if (hasGoldenAppleEffects) {
                    cir.setReturnValue(Rarity.UNCOMMON);
                }
            }
        }
    }
}
