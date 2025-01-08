package com.kushi.niobium.world.gen;

import com.kushi.niobium.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModCropGeneration {
    public static void generateCrops() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.RIVER, BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.RICE_CROP_PLACED_KEY);
    }
}
