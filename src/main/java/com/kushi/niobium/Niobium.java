package com.kushi.niobium;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.component.ModDataComponentTypes;
import com.kushi.niobium.item.ModItemGroups;
import com.kushi.niobium.item.ModItems;
import com.kushi.niobium.potion.ModPotions;
import com.kushi.niobium.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Niobium implements ModInitializer {
	public static final String MOD_ID = "niobium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModWorldGeneration.generateModWorldGen();
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModDataComponentTypes.registerDataComponentTypes();

		ModPotions.registerPotions();

		CompostingChanceRegistry.INSTANCE.add(ModItems.RICE, 0.55f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.RICE_SEEDS, 0.3f);

		StrippableBlockRegistry.register(ModBlocks.PALM_LOG, ModBlocks.STRIPPED_PALM_LOG);
		StrippableBlockRegistry.register(ModBlocks.PALM_WOOD, ModBlocks.STRIPPED_PALM_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PALM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PALM_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_PLANKS, 5, 20);

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SHULKER_SHELL, ModPotions.LEVITATION_POTION);
		});
	}
}