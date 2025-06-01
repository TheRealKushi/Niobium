package com.kushi.niobium.util;

import com.kushi.niobium.Niobium;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> STONECUTTER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Niobium.MOD_ID, "stonecutter"));
}
