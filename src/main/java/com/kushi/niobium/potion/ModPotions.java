package com.kushi.niobium.potion;

import com.kushi.niobium.Niobium;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {
    private static RegistryEntry<Potion> levitationPotionInternal;  // Internal variable

    public static final RegistryEntry<Potion> LEVITATION_POTION = getOrCreateLevitationPotion();

    public static void registerPotions() {
        Niobium.LOGGER.info("Registering Mod Potions for " + Niobium.MOD_ID);
    }

    private static RegistryEntry<Potion> getOrCreateLevitationPotion() {
        if (levitationPotionInternal == null) {
            levitationPotionInternal = registerPotion("levitation_potion",
                    new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 45 * 20, 0)));
        }
        return levitationPotionInternal;
    }

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(Niobium.MOD_ID, name), potion);
    }
}
