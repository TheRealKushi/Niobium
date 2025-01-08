package com.kushi.niobium.block.custom;

import com.kushi.niobium.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class RiceCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 4;
    public static final int SECOND_STAGE_MAX_AGE = 1;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), // Age 0
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), // Age 1
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), // Age 2
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), // Age 3
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), // Age 4
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)  // Age 5
    };

    public static final IntProperty AGE = IntProperty.of("age", 0, 5);

    public RiceCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.RICE_SEEDS;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int currentAge = this.getAge(state);
            if (currentAge < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    if(currentAge == FIRST_STAGE_MAX_AGE) {
                        if(world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
                            world.setBlockState(pos.up(1), this.withAge(currentAge + 1), 2);
                        }
                    } else {
                        world.setBlockState(pos, this.withAge(currentAge + 1), 2);
                    }
                }
            }
        }
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        if(nextAge > maxAge) {
            nextAge = maxAge;
        }

        if (this.getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(1), this.withAge(nextAge), 2);
        } else {
            world.setBlockState(pos, this.withAge(nextAge - 1), 2);
        }
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (this.getAge(state) > FIRST_STAGE_MAX_AGE) {
            // Ensure the block below is either dirt, grass, or another valid crop block at the correct stage
            BlockState belowState = world.getBlockState(pos.down());
            return belowState.isOf(Blocks.DIRT) || belowState.isOf(Blocks.GRASS_BLOCK) || (belowState.isOf(this) && this.getAge(belowState) == FIRST_STAGE_MAX_AGE);
        }
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
