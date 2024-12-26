package com.kushi.niobium.item;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup NIOBIUM_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.LIGHT_HELMET))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.LIGHT_HELMET);
                        entries.add(ModItems.IRON_TILE);
                        entries.add(ModItems.ENDRITE_SCRAP);
                    }).build());

    public static final ItemGroup NIOBIUM_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.EMERALD_PRISM_BLOCK))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.EMERALD_PRISM_BLOCK);
                        entries.add(ModBlocks.IRON_TILES_BLOCK);
                        entries.add(ModBlocks.IRON_TILES_SLAB);
                        entries.add(ModBlocks.ENDRITE_ORE_BLOCK);
                    }).build());


    public static void registerItemGroups() {
        Niobium.LOGGER.info("Registering Item Groups for " + Niobium.MOD_ID);
    }
}
