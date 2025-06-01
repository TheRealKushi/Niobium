package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.block.custom.BlueBerryBushBlock;
import com.kushi.niobium.block.custom.RiceCropBlock;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMERALD_PRISM_BLOCK);
        BlockStateModelGenerator.BlockTexturePool ironTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.IRON_TILES_BLOCK);
        BlockStateModelGenerator.BlockTexturePool quartzTilesPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.QUARTZ_BRICKS);
        quartzTilesPool.stairs(ModBlocks.QUARTZ_BRICKS_STAIRS);

        // For Dirt Slab
        BlockStateModelGenerator.BlockTexturePool dirtPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DIRT);
        dirtPool.slab(ModBlocks.DIRT_SLAB); // Register dirt slab with the dirt texture

// For Glass Slab
        BlockStateModelGenerator.BlockTexturePool glassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GLASS);
        glassPool.slab(ModBlocks.GLASS_SLAB);

// For Tinted Glass Slab
        BlockStateModelGenerator.BlockTexturePool tintedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.TINTED_GLASS);
        tintedGlassPool.slab(ModBlocks.TINTED_GLASS_SLAB);

// For Stained Glass Slabs
        BlockStateModelGenerator.BlockTexturePool blackStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BLACK_STAINED_GLASS);
        blackStainedGlassPool.slab(ModBlocks.BLACK_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool blueStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BLUE_STAINED_GLASS);
        blueStainedGlassPool.slab(ModBlocks.BLUE_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool brownStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BROWN_STAINED_GLASS);
        brownStainedGlassPool.slab(ModBlocks.BROWN_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool cyanStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CYAN_STAINED_GLASS);
        cyanStainedGlassPool.slab(ModBlocks.CYAN_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool grayStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GRAY_STAINED_GLASS);
        grayStainedGlassPool.slab(ModBlocks.GRAY_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool greenStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GREEN_STAINED_GLASS);
        greenStainedGlassPool.slab(ModBlocks.GREEN_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool lightBlueStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIGHT_BLUE_STAINED_GLASS);
        lightBlueStainedGlassPool.slab(ModBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool lightGrayStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIGHT_GRAY_STAINED_GLASS);
        lightGrayStainedGlassPool.slab(ModBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool limeStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIME_STAINED_GLASS);
        limeStainedGlassPool.slab(ModBlocks.LIME_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool magentaStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.MAGENTA_STAINED_GLASS);
        magentaStainedGlassPool.slab(ModBlocks.MAGENTA_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool orangeStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.ORANGE_STAINED_GLASS);
        orangeStainedGlassPool.slab(ModBlocks.ORANGE_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool pinkStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PINK_STAINED_GLASS);
        pinkStainedGlassPool.slab(ModBlocks.PINK_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool purpleStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PURPLE_STAINED_GLASS);
        purpleStainedGlassPool.slab(ModBlocks.PURPLE_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool redStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_STAINED_GLASS);
        redStainedGlassPool.slab(ModBlocks.RED_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool whiteStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.WHITE_STAINED_GLASS);
        whiteStainedGlassPool.slab(ModBlocks.WHITE_STAINED_GLASS_SLAB);

        BlockStateModelGenerator.BlockTexturePool yellowStainedGlassPool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.YELLOW_STAINED_GLASS);
        yellowStainedGlassPool.slab(ModBlocks.YELLOW_STAINED_GLASS_SLAB);

        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_PALM_LOG, ModBlocks.HANGING_PALM_SIGN, ModBlocks.WALL_HANGING_PALM_SIGN);

        var palmFamily = new BlockFamily.Builder(ModBlocks.PALM_PLANKS)
                .fence(ModBlocks.PALM_FENCE)
                .fenceGate(ModBlocks.PALM_FENCE_GATE)
                .sign(ModBlocks.STANDING_PALM_SIGN, ModBlocks.WALL_PALM_SIGN)
                .slab(ModBlocks.PALM_SLAB)
                .stairs(ModBlocks.PALM_STAIRS)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();

        blockStateModelGenerator.registerCubeAllModelTexturePool(palmFamily.getBaseBlock())
                .family(palmFamily);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDRITE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDRITE_BLOCK);

        ironTilesPool.stairs(ModBlocks.IRON_TILES_STAIRS);
        ironTilesPool.slab(ModBlocks.IRON_TILES_SLAB);
        ironTilesPool.wall(ModBlocks.IRON_TILES_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.IRON_TILES_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.IRON_TILES_TRAPDOOR);

        blockStateModelGenerator.registerLog(ModBlocks.PALM_LOG).log(ModBlocks.PALM_LOG).wood(ModBlocks.PALM_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PALM_LOG).log(ModBlocks.STRIPPED_PALM_LOG).wood(ModBlocks.STRIPPED_PALM_WOOD);

        blockStateModelGenerator.registerSingleton(ModBlocks.PALM_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.PALM_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerCrop(ModBlocks.RICE_CROP, RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.BLUEBERRY_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED,
                BlueBerryBushBlock.AGE, 0, 1, 2, 3);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WOODEN_CHAIR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COOKED_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RICE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_HORSE_MEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_HORSE_MEAT, Models.GENERATED);

        itemModelGenerator.register(ModItems.IRON_TILE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDRITE_SCRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDRITE_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.ENDRITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.EMERALD_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EMERALD_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EMERALD_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EMERALD_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EMERALD_BOOTS));

        itemModelGenerator.register(ModItems.NETHERITE_HORSE_ARMOR, Models.GENERATED);

        itemModelGenerator.register(ModItems.ENDRITE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModBlocks.PALM_SAPLING.asItem(), Models.GENERATED);

        itemModelGenerator.register(ModItems.ILLUSIONER_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}