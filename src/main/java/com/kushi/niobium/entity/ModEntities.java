package com.kushi.niobium.entity;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.entity.custom.ChairEntity;
import com.kushi.niobium.entity.custom.WarAxeProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<WarAxeProjectileEntity> WAR_AXE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Niobium.MOD_ID, "war_axe"),
            EntityType.Builder.<WarAxeProjectileEntity>create(WarAxeProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    public static final EntityType<ChairEntity> WOODEN_CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Niobium.MOD_ID, "chair_entity"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build());



    public static void registerModEntities() {
        Niobium.LOGGER.info("Registering Mod Entities for " + Niobium.MOD_ID);
    }
}