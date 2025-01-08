package com.kushi.niobium.world.tree.custom;

import com.kushi.niobium.world.tree.ModFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class PalmTreeFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PalmTreeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            fillFoliagePlacerFields(instance)
                    .and(Codec.intRange(0, 10).fieldOf("height").forGetter(placer -> placer.height))
                    .apply(instance, PalmTreeFoliagePlacer::new)
    );

    private final int height;

    public PalmTreeFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerTypes.PALM_TREE_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight,
                            TreeNode treeNode, int foliageHeight, int radius, int offset) {
        // Central point of foliage (top of trunk)
        BlockPos center = treeNode.getCenter();

        // Generate fronds in a radial pattern
        int frondLength = 3; // Adjusted for less dense leaf generation
        int frondDensity = 4; // Adjust for the number of leaves along the frond's direction
        for (int angle = 0; angle < 360; angle += 45) { // 45-degree increments for 8 directions
            double radians = Math.toRadians(angle);

            // Create a single frond by placing leaves along the direction
            for (int step = 0; step <= frondLength; step++) {
                int x = center.getX() + (int) (Math.cos(radians) * step); // x position
                int z = center.getZ() + (int) (Math.sin(radians) * step); // z position
                int y = center.getY() + (step == 0 ? 0 : random.nextInt(2)); // Slight vertical offset for variation
                BlockPos leafPos = new BlockPos(x, y, z);
                placeLeaf(world, placer, random, config, leafPos);
            }
        }
    }

    private void placeLeaf(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos pos) {
        if (world.testBlockState(pos, blockState -> blockState.isAir())) {
            placer.placeBlock(pos, config.foliageProvider.get(random, pos));
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return this.height;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
