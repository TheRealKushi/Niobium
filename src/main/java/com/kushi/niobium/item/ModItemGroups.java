package com.kushi.niobium.item;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.potion.ModPotions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
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
                        entries.add(ModItems.ENDRITE_INGOT);
                        entries.add(ModItems.ENDRITE_UPGRADE_SMITHING_TEMPLATE);
                        entries.add(ModItems.CHISEL);
                        entries.add(ModItems.ILLUSIONER_SPAWN_EGG);
                    }).build());

    public static final ItemGroup NIOBIUM_FOODS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_foods"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.COOKED_RICE))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_foods"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_HORSE_MEAT);
                        entries.add(ModItems.COOKED_HORSE_MEAT);
                        entries.add(ModItems.RICE_SEEDS);
                        entries.add(ModItems.RICE);
                        entries.add(ModItems.COOKED_RICE);
                        entries.add(ModItems.BLUEBERRIES);
                    }).build());

    public static final ItemGroup NIOBIUM_POTIONS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_potions"),
            FabricItemGroup.builder().icon(() -> new ItemStack(net.minecraft.item.Items.POTION))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_potions"))
                    .entries((displayContext, entries) -> {
                        entries.add(withPotion(Items.POTION, ModPotions.LEVITATION_POTION));
                        entries.add(withPotion(Items.SPLASH_POTION, ModPotions.LEVITATION_POTION));
                        entries.add(withPotion(Items.LINGERING_POTION, ModPotions.LEVITATION_POTION));
                        entries.add(withPotion(Items.TIPPED_ARROW, ModPotions.LEVITATION_POTION));
                        entries.add(withPotion(Items.POTION, ModPotions.GOLDEN_APPLE_POTION));
                        entries.add(withPotion(Items.SPLASH_POTION, ModPotions.GOLDEN_APPLE_POTION));
                        entries.add(withPotion(Items.LINGERING_POTION, ModPotions.GOLDEN_APPLE_POTION));
                        entries.add(withPotion(Items.TIPPED_ARROW, ModPotions.GOLDEN_APPLE_POTION));
                        entries.add(withPotion(Items.POTION, ModPotions.ENCHANTED_GOLDEN_APPLE_POTION));
                        entries.add(withPotion(Items.SPLASH_POTION, ModPotions.ENCHANTED_GOLDEN_APPLE_POTION));
                        entries.add(withPotion(Items.LINGERING_POTION, ModPotions.ENCHANTED_GOLDEN_APPLE_POTION));
                        entries.add(withPotion(Items.TIPPED_ARROW, ModPotions.ENCHANTED_GOLDEN_APPLE_POTION));
                    }).build());

    public static final ItemGroup NIOBIUM_ARMOR_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_armor"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ENDRITE_HELMET))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_armor"))
                    .entries((displayContext, entries) -> {
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

                        entries.add(ModItems.NETHERITE_HORSE_ARMOR);

                        entries.add(ModItems.WAR_AXE);

                        entries.add(ModItems.EMERALD_HELMET);
                        entries.add(ModItems.EMERALD_CHESTPLATE);
                        entries.add(ModItems.EMERALD_LEGGINGS);
                        entries.add(ModItems.EMERALD_BOOTS);

                        entries.add(ModItems.EMERALD_SWORD);
                        entries.add(ModItems.EMERALD_AXE);
                        entries.add(ModItems.EMERALD_PICKAXE);
                        entries.add(ModItems.EMERALD_SHOVEL);
                        entries.add(ModItems.EMERALD_HOE);
                    }).build());

    public static final ItemGroup NIOBIUM_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Niobium.MOD_ID, "niobium_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.ENDRITE_BLOCK))
                    .displayName(Text.translatable("itemgroup.niobium.niobium_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.EMERALD_PRISM_BLOCK);
                        entries.add(ModBlocks.DIRT_SLAB);

                        // Glass slabs
                        entries.add(ModBlocks.GLASS_SLAB);
                        entries.add(ModBlocks.TINTED_GLASS_SLAB);

                        // Stained-glass slabs
                        entries.add(ModBlocks.BLACK_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.BLUE_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.BROWN_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.CYAN_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.GRAY_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.GREEN_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.LIME_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.MAGENTA_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.ORANGE_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.PINK_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.PURPLE_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.RED_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.WHITE_STAINED_GLASS_SLAB);
                        entries.add(ModBlocks.YELLOW_STAINED_GLASS_SLAB);

                        entries.add(ModBlocks.IRON_TILES_BLOCK);
                        entries.add(ModBlocks.IRON_TILES_STAIRS);
                        entries.add(ModBlocks.IRON_TILES_SLAB);
                        entries.add(ModBlocks.IRON_TILES_WALL);
                        entries.add(ModBlocks.IRON_TILES_DOOR);
                        entries.add(ModBlocks.IRON_TILES_TRAPDOOR);
                        entries.add(ModBlocks.ENDRITE_ORE_BLOCK);
                        entries.add(ModBlocks.ENDRITE_BLOCK);
                        entries.add(ModBlocks.QUARTZ_BRICKS_STAIRS);
                        entries.add(ModBlocks.WOODEN_CHAIR);
                        entries.add(ModBlocks.PALM_LOG);
                        entries.add(ModBlocks.PALM_WOOD);
                        entries.add(ModBlocks.STRIPPED_PALM_LOG);
                        entries.add(ModBlocks.STRIPPED_PALM_WOOD);
                        entries.add(ModBlocks.PALM_PLANKS);
                        entries.add(ModBlocks.PALM_STAIRS);
                        entries.add(ModBlocks.PALM_SLAB);
                        entries.add(ModBlocks.PALM_FENCE);
                        entries.add(ModBlocks.PALM_FENCE_GATE);
                        entries.add(ModItems.PALM_SIGN);
                        entries.add(ModItems.HANGING_PALM_SIGN);
                        entries.add(ModBlocks.PALM_LEAVES);
                        entries.add(ModBlocks.PALM_SAPLING);
                    }).build());

    public static void registerItemGroups() {
        Niobium.LOGGER.info("Registering Item Groups for " + Niobium.MOD_ID);
    }

    private static ItemStack withPotion(Item item, RegistryKey<Potion> potionKey) {
        ItemStack stack = new ItemStack(item);
        RegistryEntry<Potion> potion = Registries.POTION.getEntry(potionKey).orElseThrow();
        stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potion));
        return stack;
    }
}