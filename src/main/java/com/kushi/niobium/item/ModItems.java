package com.kushi.niobium.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import com.kushi.niobium.Niobium;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item LIGHT_HELMET = registerItem("light_helmet",
            new ArmorItem(ModArmorMaterials.LIGHT_HELMET_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));
    public static final Item IRON_TILE = registerItem("iron_tile", new Item(new Item.Settings()));
    public static final Item ENDRITE_SCRAP = registerItem("endrite_scrap", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Niobium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Niobium.LOGGER.info("Registering Mod Items for " + Niobium.MOD_ID);
    }
}
