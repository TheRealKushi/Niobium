package com.kushi.niobium.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawn {
    public static void addSpawns() {
        // Only add the spawn if it's not already registered
        try {
            // Register the Illusioner spawn in specific biomes (without conflict)
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_SLOPES),
                    SpawnGroup.MONSTER, EntityType.ILLUSIONER, 100, 1, 1);

            // Register spawn restrictions for the Illusioner
            SpawnRestriction.register(EntityType.ILLUSIONER, SpawnLocationTypes.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, spawnLocation, spawnReason, heightmapType) -> true);
        } catch (IllegalStateException e) {
            // Catch the duplicate registration error and log it, to prevent crash
            System.out.println("Illusioner already registered. Skipping spawn registration.");
        }
    }
}
