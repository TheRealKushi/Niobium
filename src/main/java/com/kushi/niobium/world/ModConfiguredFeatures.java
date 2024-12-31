package com.kushi.niobium.world;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.block.custom.BlueBerryBushBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.BendingTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDRITE_ORE_KEY_LARGE = registerKey("endrite_ore_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDRITE_ORE_KEY_SMALL = registerKey("endrite_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALM_TREE_KEY = registerKey("palm_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH_KEY = registerKey("blueberry_bush_key");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> endEndriteOres =
                List.of(OreFeatureConfig.createTarget(endReplaceables, ModBlocks.ENDRITE_ORE_BLOCK.getDefaultState()));

        register(context, ENDRITE_ORE_KEY_LARGE, Feature.SCATTERED_ORE, new OreFeatureConfig(endEndriteOres, 3, 1.0F));
        register(context, ENDRITE_ORE_KEY_SMALL, Feature.SCATTERED_ORE, new OreFeatureConfig(endEndriteOres, 2, 1.0F));

        // Palm Tree Configuration
        register(context, PALM_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.PALM_LOG),
                new BendingTrunkPlacer(5, 4, 1, 5, UniformIntProvider.create(1, 1)),
                BlockStateProvider.of(ModBlocks.PALM_LEAVES),
                new RandomSpreadFoliagePlacer(UniformIntProvider.create(3, 4), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 8),
                new TwoLayersFeatureSize(1, 0, 2)).dirtProvider(BlockStateProvider.of(Blocks.SAND)).build());

        register(context, BLUEBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of((BlockState)ModBlocks.BLUEBERRY_BUSH.getDefaultState().with(SweetBerryBushBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK)));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Niobium.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
