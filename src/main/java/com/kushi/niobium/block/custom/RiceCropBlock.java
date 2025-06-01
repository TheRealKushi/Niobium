package com.kushi.niobium.block.custom;

import com.kushi.niobium.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class RiceCropBlock extends CropBlock implements Waterloggable {

    public static final int FIRST_STAGE_MAX_AGE = 4;
    public static final int SECOND_STAGE_MAX_AGE = 1;

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final IntProperty AGE = IntProperty.of("age", 0, 5);

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),  // Age 0
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),  // Age 1
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),  // Age 2
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), // Age 3
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), // Age 4
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)  // Age 5
    };

    public RiceCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(WATERLOGGED, true)
                .with(AGE, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(AGE)];
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.RICE_SEEDS;
    }

    // ✅ Keeps first-stage rice **waterlogged** properly
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    // ✅ Ensures first-stage rice remains **waterlogged** when placed
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean isWaterlogged = ctx.getWorld().getBlockState(ctx.getBlockPos()).getFluidState().isOf(Fluids.WATER);
        return this.getDefaultState().with(WATERLOGGED, isWaterlogged);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState stateBelow = world.getBlockState(pos.down());
        BlockState stateAbove = world.getBlockState(pos.up());

        boolean isInWater = world.getFluidState(pos).getFluid() == Fluids.WATER;
        boolean isBelowValid = stateBelow.isOf(Blocks.WATER) || stateBelow.isOf(Blocks.DIRT) || (stateBelow.isOf(this) && stateBelow.get(AGE) == FIRST_STAGE_MAX_AGE);
        boolean isAboveValid = stateAbove.isOf(Blocks.AIR);

        if (state.get(AGE) == 0) {
            return isInWater && isBelowValid && isAboveValid;
        } else {
            return isBelowValid;
        }
    }

    // ✅ Growth logic - second stage **leaves the water**
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int currentAge = this.getAge(state);
        if (currentAge >= getMaxAge()) return; // Prevents overgrowing or bonemeal use on max age

        int nextAge = currentAge + 1; // Always increase by 1
        if (nextAge > getMaxAge()) nextAge = getMaxAge();

        if (currentAge == FIRST_STAGE_MAX_AGE) {
            BlockPos abovePos = pos.up();
            if (world.getBlockState(abovePos).isOf(Blocks.WATER)) {
                world.setBlockState(abovePos, Blocks.AIR.getDefaultState(), 2);
            }
            world.setBlockState(abovePos, this.withAge(nextAge).with(WATERLOGGED, false), 2);
        } else {
            world.setBlockState(pos, this.withAge(nextAge).with(WATERLOGGED, currentAge < FIRST_STAGE_MAX_AGE), 2);
        }
    }

    // ✅ Prevents breaking when **growing next to solid blocks**
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (this.getAge(state) == FIRST_STAGE_MAX_AGE) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = pos.offset(direction);
                BlockState neighborState = world.getBlockState(neighborPos);

                if (!neighborState.isAir() && neighborState.isSolidBlock(world, neighborPos)) {
                    return; // Don't break if there's a solid block nearby
                }
            }
        }
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    // ✅ **Fix: Prevent bonemeal on fully grown bottom crop**
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        int age = this.getAge(state);
        if (age == getMaxAge()) {
            BlockState above = world.getBlockState(pos.up());
            return above.isOf(this) && this.getAge(above) < getMaxAge();
        }
        return age < getMaxAge();
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
        builder.add(AGE, WATERLOGGED);
    }
}
