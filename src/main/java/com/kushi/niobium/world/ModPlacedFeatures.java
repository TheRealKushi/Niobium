package com.kushi.niobium.world;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> ENDRITE_ORE_PLACED_KEY_LARGE = registerKey("endrite_ore_place_key_large");
    public static final RegistryKey<PlacedFeature> ENDRITE_ORE_PLACED_KEY_SMALL = registerKey("endrite_ore_place_key_small");

    public static final RegistryKey<PlacedFeature> PALMTREE_PLACED_KEY = registerKey("palmtree_placed");
    public static final RegistryKey<PlacedFeature> BLUEBERRY_BUSH_PLACED_KEY = registerKey("blueberry_bush_placed");
    public static final RegistryKey<PlacedFeature> RICE_CROP_PLACED_KEY = registerKey("rice_crop_key");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, ENDRITE_ORE_PLACED_KEY_LARGE, configuredFeatures.getOrThrow(ModConfiguredFeatures.ENDRITE_ORE_KEY_LARGE),
                ModOrePlacement.modifiersWithCount(1,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(16), YOffset.fixed(32))
                ));
        register(context, ENDRITE_ORE_PLACED_KEY_SMALL, configuredFeatures.getOrThrow(ModConfiguredFeatures.ENDRITE_ORE_KEY_SMALL),
                ModOrePlacement.modifiersWithCount(3,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(33), YOffset.fixed(54))
                ));

        register(context, PALMTREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PALM_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.1f, 1), ModBlocks.PALM_SAPLING));

        register(context, BLUEBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUEBERRY_BUSH_KEY),
                RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, RICE_CROP_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RICE_CROP_KEY),
                RarityFilterPlacementModifier.of(52), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Niobium.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
