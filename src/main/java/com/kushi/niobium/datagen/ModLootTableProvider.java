package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.EMERALD_PRISM_BLOCK);
        addDrop(ModBlocks.IRON_TILES_BLOCK);
        addDrop(ModBlocks.IRON_TILES_STAIRS);
        addDrop(ModBlocks.IRON_TILES_SLAB, slabDrops(ModBlocks.IRON_TILES_SLAB));
        addDrop(ModBlocks.IRON_TILES_DOOR, doorDrops(ModBlocks.IRON_TILES_DOOR));
        addDrop(ModBlocks.IRON_TILES_WALL);
        addDrop(ModBlocks.IRON_TILES_TRAPDOOR);

        addDrop(ModBlocks.ENDRITE_ORE_BLOCK, oreDrops(ModBlocks.ENDRITE_ORE_BLOCK, ModItems.ENDRITE_SCRAP));
    }
}
