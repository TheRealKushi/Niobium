package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.ENDRITE_SWORD)
                .add(ModItems.EMERALD_SWORD);

        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .add(ModItems.ENDRITE_SWORD)
                .add(ModItems.EMERALD_SWORD);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.ENDRITE_AXE)
                .add(ModItems.EMERALD_AXE);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.ENDRITE_PICKAXE)
                .add(ModItems.EMERALD_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.ENDRITE_SHOVEL)
                .add(ModItems.EMERALD_SHOVEL);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.ENDRITE_HOE)
                .add(ModItems.EMERALD_HOE);

        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .addTag(ItemTags.AXES)
                .addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.HOES);

        getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(ItemTags.AXES)
                .addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.HOES);

        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.ENDRITE_BOW);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.ENDRITE_HELMET)
                .add(ModItems.EMERALD_HELMET);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.ENDRITE_HELMET)
                .add(ModItems.EMERALD_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.ENDRITE_CHESTPLATE)
                .add(ModItems.EMERALD_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.ENDRITE_CHESTPLATE)
                .add(ModItems.EMERALD_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.ENDRITE_LEGGINGS)
                .add(ModItems.EMERALD_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.ENDRITE_LEGGINGS)
                .add(ModItems.EMERALD_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.ENDRITE_BOOTS)
                .add(ModItems.EMERALD_BOOTS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.ENDRITE_BOOTS)
                .add(ModItems.EMERALD_BOOTS);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR)
                .addTag(ItemTags.LEG_ARMOR)
                .addTag(ItemTags.CHEST_ARMOR)
                .addTag(ItemTags.HEAD_ARMOR);

        getOrCreateTagBuilder(ItemTags.ARMOR_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR)
                .addTag(ItemTags.LEG_ARMOR)
                .addTag(ItemTags.CHEST_ARMOR)
                .addTag(ItemTags.HEAD_ARMOR);

        getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR)
                .addTag(ItemTags.LEG_ARMOR)
                .addTag(ItemTags.CHEST_ARMOR)
                .addTag(ItemTags.HEAD_ARMOR)
                .addTag(ItemTags.SWORDS)
                .addTag(ItemTags.AXES)
                .addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.HOES)
                .add(ModItems.ENDRITE_BOW);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .addTag(ItemTags.FOOT_ARMOR)
                .addTag(ItemTags.LEG_ARMOR)
                .addTag(ItemTags.CHEST_ARMOR)
                .addTag(ItemTags.HEAD_ARMOR);

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ENDRITE_UPGRADE_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ItemTags.FOX_FOOD)
                .add(ModItems.BLUEBERRIES);

        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(ModBlocks.PALM_LOG.asItem())
                .add(ModBlocks.PALM_WOOD.asItem())
                .add(ModBlocks.STRIPPED_PALM_LOG.asItem())
                .add(ModBlocks.STRIPPED_PALM_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG.asItem())
                .add(ModBlocks.PALM_WOOD.asItem())
                .add(ModBlocks.STRIPPED_PALM_LOG.asItem())
                .add(ModBlocks.STRIPPED_PALM_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.PALM_PLANKS.asItem());
    }
}