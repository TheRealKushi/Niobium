package com.kushi.niobium.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import com.kushi.niobium.Niobium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item LIGHT_HELMET = registerItem("light_helmet", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Niobium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Niobium.LOGGER.info("Registering Mod Items for " + Niobium.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(LIGHT_HELMET);
        });
    }
}
