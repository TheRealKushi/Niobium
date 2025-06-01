package com.kushi.niobium.potion;

import com.kushi.niobium.Niobium;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModPotions {
    private static RegistryKey<Potion> levitationPotionInternal;

    public static final RegistryKey<Potion> LEVITATION_POTION = getOrCreateLevitationPotion();

    private static RegistryKey<Potion> getOrCreateLevitationPotion() {
        if (levitationPotionInternal == null) {
            levitationPotionInternal = registerPotion("levitation_potion",
                    new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 45 * 20, 0)));
        }
        return levitationPotionInternal;
    }

    public static final RegistryKey<Potion> GOLDEN_APPLE_POTION = registerPotion("golden_apple_potion",
            new Potion(
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0),
                    new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1)
            ));

    public static final RegistryKey<Potion> ENCHANTED_GOLDEN_APPLE_POTION = registerPotion("enchanted_golden_apple_potion",
            new Potion(
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3),
                    new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1),
                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 5000, 0),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, 5000, 0)
            ));

    private static RegistryKey<Potion> registerPotion(String name, Potion potion) {
        Identifier id = Identifier.of(Niobium.MOD_ID, name);
        Registry.register(Registries.POTION, id, potion);
        return RegistryKey.of(RegistryKeys.POTION, id);
    }

    public static void registerPotions() {
        Niobium.LOGGER.info("Registering Mod Potions for " + Niobium.MOD_ID);
    }
}
