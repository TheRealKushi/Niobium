package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.block.custom.BlueBerryBushBlock;
import com.kushi.niobium.block.custom.RiceCropBlock;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.item.ArmorItem;

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



        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDRITE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDRITE_BLOCK);

        ironTilesPool.stairs(ModBlocks.IRON_TILES_STAIRS);
        ironTilesPool.slab(ModBlocks.IRON_TILES_SLAB);
        ironTilesPool.wall(ModBlocks.IRON_TILES_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.IRON_TILES_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.IRON_TILES_TRAPDOOR);

        blockStateModelGenerator.registerLog(ModBlocks.PALM_LOG).log(ModBlocks.PALM_LOG).wood(ModBlocks.PALM_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PALM_LOG).log(ModBlocks.STRIPPED_PALM_LOG).wood(ModBlocks.STRIPPED_PALM_WOOD);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PALM_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.PALM_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.PALM_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerCrop(ModBlocks.RICE_CROP, RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.BLUEBERRY_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED,
                BlueBerryBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LIGHT_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RICE, Models.GENERATED);

        itemModelGenerator.register(ModItems.IRON_TILE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDRITE_SCRAP, Models.GENERATED);

        itemModelGenerator.register(ModItems.ENDRITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDRITE_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDRITE_BOOTS));

        itemModelGenerator.register(ModItems.ENDRITE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
    }
}
