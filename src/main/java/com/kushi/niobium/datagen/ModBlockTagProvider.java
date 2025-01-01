package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.IRON_TILES_BLOCK)
                .add(ModBlocks.IRON_TILES_SLAB)
                .add(ModBlocks.EMERALD_PRISM_BLOCK)
                .add(ModBlocks.ENDRITE_ORE_BLOCK)
                .add(ModBlocks.ENDRITE_BLOCK)
                .add(ModBlocks.IRON_TILES_STAIRS)
                .add(ModBlocks.IRON_TILES_TRAPDOOR)
                .add(ModBlocks.IRON_TILES_DOOR)
                .add(ModBlocks.IRON_TILES_WALL);

        // You can keep these other tags if needed for blocks requiring different tools
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ENDRITE_ORE_BLOCK)
                .add(ModBlocks.ENDRITE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.EMERALD_PRISM_BLOCK);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_EMERALD_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.IRON_TILES_BLOCK)
                .add(ModBlocks.IRON_TILES_SLAB)
                .add(ModBlocks.IRON_TILES_STAIRS)
                .add(ModBlocks.IRON_TILES_TRAPDOOR)
                .add(ModBlocks.IRON_TILES_DOOR)
                .add(ModBlocks.IRON_TILES_WALL);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.IRON_TILES_WALL);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG)
                .add(ModBlocks.PALM_WOOD)
                .add(ModBlocks.STRIPPED_PALM_LOG)
                .add(ModBlocks.STRIPPED_PALM_WOOD);
    }
}
