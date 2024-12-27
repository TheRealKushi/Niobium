package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMERALD_PRISM_BLOCK);
        BlockStateModelGenerator.BlockTexturePool ironTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.IRON_TILES_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDRITE_ORE_BLOCK);

        ironTilesPool.stairs(ModBlocks.IRON_TILES_STAIRS);
        ironTilesPool.slab(ModBlocks.IRON_TILES_SLAB);
        ironTilesPool.wall(ModBlocks.IRON_TILES_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.IRON_TILES_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.IRON_TILES_TRAPDOOR);
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
