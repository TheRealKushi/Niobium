package com.kushi.niobium.item;

import com.kushi.niobium.Niobium;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> LIGHT_HELMET_MATERIAL = registerArmorMaterial("light_helmet",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 4);

            }), 10, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, () -> Ingredient.ofItems(ModItems.LIGHT_HELMET),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Niobium.MOD_ID, "light_helmet"))), 0,0));

    public static final RegistryEntry<ArmorMaterial> ENDRITE_ARMOR_MATERIAL = registerArmorMaterial("endrite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.BODY, 15);

            }), 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItems.ENDRITE_SCRAP),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Niobium.MOD_ID, "endrite"))), 0,0));

    public static final RegistryEntry<ArmorMaterial> EMERALD_ARMOR_MATERIAL = registerArmorMaterial("emerald",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 7);

            }), 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(Items.EMERALD),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Niobium.MOD_ID, "emerald"))), 0,0));


    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(Niobium.MOD_ID, name), material.get());
    }
}
