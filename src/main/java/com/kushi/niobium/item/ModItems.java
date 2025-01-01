package com.kushi.niobium.item;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.item.custom.ChiselItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import com.kushi.niobium.Niobium;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    public static final Item IRON_TILE = registerItem("iron_tile", new Item(new Item.Settings()) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if(Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("tooltip.niobium.iron_tile.shift_down"));
            } else {
                tooltip.add(Text.translatable("tooltip.niobium.iron_tile"));
                super.appendTooltip(stack, context, tooltip, type);
            }
        }
    });

    public static final Item EMERALD_SWORD = registerItem("emerald_sword", new SwordItem(ModToolMaterials.EMERALD, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.EMERALD, 3, -2.4f)).maxCount(1)));
    public static final Item EMERALD_PICKAXE = registerItem("emerald_pickaxe", new PickaxeItem(ModToolMaterials.EMERALD, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.EMERALD, 1, -2.8f)).maxCount(1)));
    public static final Item EMERALD_SHOVEL = registerItem("emerald_shovel", new ShovelItem(ModToolMaterials.EMERALD, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.EMERALD, 1.5f, -3.0f)).maxCount(1)));
    public static final Item EMERALD_AXE = registerItem("emerald_axe", new AxeItem(ModToolMaterials.EMERALD, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.EMERALD, 6, -3.2f)).maxCount(1)));
    public static final Item EMERALD_HOE = registerItem("emerald_hoe", new HoeItem(ModToolMaterials.EMERALD, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.EMERALD, 0, -3f)).maxCount(1)));

    public static final Item ENDRITE_SCRAP = registerItem("endrite_scrap", new Item(new Item.Settings().fireproof()));

    public static final Item ENDRITE_SWORD = registerItem("endrite_sword", new SwordItem(ModToolMaterials.ENDRITE, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ENDRITE, 3, -2.4f)).fireproof().maxCount(1)));
    public static final Item ENDRITE_PICKAXE = registerItem("endrite_pickaxe", new PickaxeItem(ModToolMaterials.ENDRITE, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ENDRITE, 1, -2.8f)).fireproof().maxCount(1)));
    public static final Item ENDRITE_SHOVEL = registerItem("endrite_shovel", new ShovelItem(ModToolMaterials.ENDRITE, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.ENDRITE, 1.5f, -3.0f)).fireproof().maxCount(1)));
    public static final Item ENDRITE_AXE = registerItem("endrite_axe", new AxeItem(ModToolMaterials.ENDRITE, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ENDRITE, 6, -3.2f)).fireproof().maxCount(1)));
    public static final Item ENDRITE_HOE = registerItem("endrite_hoe", new HoeItem(ModToolMaterials.ENDRITE, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.ENDRITE, 0, -3f)).fireproof().maxCount(1)));

    public static final Item ENDRITE_BOW = registerItem("endrite_bow",
            new BowItem(new Item.Settings().maxDamage(500).maxCount(1).fireproof()));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32).maxCount(1)));

    public static final Item RICE = registerItem("rice", new Item(new Item.Settings().food(ModFoodComponents.RICE)));
    public static final Item COOKED_RICE = registerItem("cooked_rice", new Item(new Item.Settings().food(ModFoodComponents.COOKED_RICE)));

    public static final Item RICE_SEEDS = registerItem("rice_seeds",
            new AliasedBlockItem(ModBlocks.RICE_CROP, new Item.Settings()));

    public static final Item BLUEBERRIES = registerItem("blueberries",
            new AliasedBlockItem(ModBlocks.BLUEBERRY_BUSH, new Item.Settings().food(ModFoodComponents.BLUEBERRIES)));

    public static final Item LIGHT_HELMET = registerItem("light_helmet",
            new ArmorItem(ModArmorMaterials.LIGHT_HELMET_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15)).maxCount(1)));

    public static final Item ENDRITE_UPGRADE_SMITHING_TEMPLATE = registerItem("endrite_upgrade_template",
            SmithingTemplateItem.createNetheriteUpgrade());

    public static final Item ENDRITE_HELMET = registerItem("endrite_helmet",
            new ArmorItem(ModArmorMaterials.ENDRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(42)).maxCount(1).fireproof()));

    public static final Item ENDRITE_CHESTPLATE = registerItem("endrite_chestplate",
            new ArmorItem(ModArmorMaterials.ENDRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(42)).maxCount(1).fireproof()));

    public static final Item ENDRITE_LEGGINGS = registerItem("endrite_leggings",
            new ArmorItem(ModArmorMaterials.ENDRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(42)).maxCount(1).fireproof()));

    public static final Item ENDRITE_BOOTS = registerItem("endrite_boots",
            new ArmorItem(ModArmorMaterials.ENDRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(42)).maxCount(1).fireproof()));

    public static final Item EMERALD_HELMET = registerItem("emerald_helmet",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(22)).maxCount(1)));

    public static final Item EMERALD_CHESTPLATE = registerItem("emerald_chestplate",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(22)).maxCount(1)));

    public static final Item EMERALD_LEGGINGS = registerItem("emerald_leggings",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(22)).maxCount(1)));

    public static final Item EMERALD_BOOTS = registerItem("emerald_boots",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(22)).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Niobium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Niobium.LOGGER.info("Registering Mod Items for " + Niobium.MOD_ID);
    }
}
