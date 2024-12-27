package com.kushi.niobium.item;

import com.kushi.niobium.item.custom.ChiselItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import com.kushi.niobium.Niobium;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item IRON_TILE = registerItem("iron_tile", new Item(new Item.Settings()));
    public static final Item ENDRITE_SCRAP = registerItem("endrite_scrap", new Item(new Item.Settings()));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

    public static final Item RICE = registerItem("rice", new Item(new Item.Settings().food(ModFoodComponents.RICE)));
    public static final Item COOKED_RICE = registerItem("cooked_rice", new Item(new Item.Settings().food(ModFoodComponents.COOKED_RICE)));

    public static final Item LIGHT_HELMET = registerItem("light_helmet",
            new ArmorItem(ModArmorMaterials.LIGHT_HELMET_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));

    public static final Item ENDRITE_HELMET = registerItem("endrite_helmet",
            new ArmorItem(ModArmorMaterials.LIGHT_HELMET_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));

    public static final Item ENDRITE_CHESTPLATE = registerItem("endrite_chestplate",
            new ArmorItem(ModArmorMaterials.LIGHT_HELMET_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));

    public static final Item ENDRITE_LEGGINGS = registerItem("endrite_leggings",
            new ArmorItem(ModArmorMaterials.LIGHT_HELMET_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));

    public static final Item ENDRITE_BOOTS = registerItem("endrite_boots",
            new ArmorItem(ModArmorMaterials.LIGHT_HELMET_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Niobium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Niobium.LOGGER.info("Registering Mod Items for " + Niobium.MOD_ID);
    }
}
