package com.kushi.niobium.world.gen;

import com.kushi.niobium.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.END_HIGHLANDS, BiomeKeys.END_MIDLANDS), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.ENDRITE_ORE_PLACED_KEY_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.END_HIGHLANDS, BiomeKeys.END_MIDLANDS), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.ENDRITE_ORE_PLACED_KEY_SMALL);
    }
}
