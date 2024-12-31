package com.kushi.niobium.item;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.potion.ModPotions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.decoration.painting.PaintingVariants;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup NIOBIUM_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ENDRITE_SCRAP))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.IRON_TILE);
                        entries.add(ModItems.ENDRITE_SCRAP);
                        entries.add(ModItems.ENDRITE_UPGRADE_SMITHING_TEMPLATE);
                        entries.add(ModItems.CHISEL);
                    }).build());

    public static final ItemGroup NIOBIUM_FOODS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_foods"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.COOKED_RICE))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_foods"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.RICE_SEEDS);
                        entries.add(ModItems.RICE);
                        entries.add(ModItems.COOKED_RICE);
                        entries.add(ModItems.BLUEBERRIES);
                    }).build());

    public static final ItemGroup NIOBIUM_ARMOR_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_armor"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.LIGHT_HELMET))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_armor"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.LIGHT_HELMET);

                        entries.add(ModItems.ENDRITE_HELMET);
                        entries.add(ModItems.ENDRITE_CHESTPLATE);
                        entries.add(ModItems.ENDRITE_LEGGINGS);
                        entries.add(ModItems.ENDRITE_BOOTS);

                        entries.add(ModItems.ENDRITE_SWORD);
                        entries.add(ModItems.ENDRITE_AXE);
                        entries.add(ModItems.ENDRITE_PICKAXE);
                        entries.add(ModItems.ENDRITE_SHOVEL);
                        entries.add(ModItems.ENDRITE_HOE);
                        entries.add(ModItems.ENDRITE_BOW);

                        entries.add(ModItems.EMERALD_HELMET);
                        entries.add(ModItems.EMERALD_CHESTPLATE);
                        entries.add(ModItems.EMERALD_LEGGINGS);
                        entries.add(ModItems.EMERALD_BOOTS);
                    }).build());

    public static final ItemGroup NIOBIUM_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.EMERALD_PRISM_BLOCK))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.EMERALD_PRISM_BLOCK);
                        entries.add(ModBlocks.IRON_TILES_BLOCK);
                        entries.add(ModBlocks.IRON_TILES_STAIRS);
                        entries.add(ModBlocks.IRON_TILES_SLAB);
                        entries.add(ModBlocks.IRON_TILES_WALL);
                        entries.add(ModBlocks.IRON_TILES_DOOR);
                        entries.add(ModBlocks.IRON_TILES_TRAPDOOR);
                        entries.add(ModBlocks.ENDRITE_ORE_BLOCK);
                        entries.add(ModBlocks.ENDRITE_BLOCK);
                        entries.add(ModBlocks.QUARTZ_BRICKS_STAIRS);
                        entries.add(ModBlocks.PALM_LOG);
                        entries.add(ModBlocks.PALM_WOOD);
                        entries.add(ModBlocks.STRIPPED_PALM_LOG);
                        entries.add(ModBlocks.STRIPPED_PALM_WOOD);
                        entries.add(ModBlocks.PALM_PLANKS);
                        entries.add(ModBlocks.PALM_LEAVES);
                        entries.add(ModBlocks.PALM_SAPLING);
                    }).build());




    public static void registerItemGroups() {
        Niobium.LOGGER.info("Registering Item Groups for " + Niobium.MOD_ID);
    }
}
