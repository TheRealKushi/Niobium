package com.kushi.niobium.world.gen;

import com.kushi.niobium.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BEACH, BiomeKeys.DESERT),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PALMTREE_PLACED_KEY);
    }
}
