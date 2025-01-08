package com.kushi.niobium;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.component.ModDataComponentTypes;
import com.kushi.niobium.event.WorldCreationHandler;
import com.kushi.niobium.gui.niobium.ModConfig;
import com.kushi.niobium.item.ModItemGroups;
import com.kushi.niobium.item.ModItems;
import com.kushi.niobium.potion.ModPotions;
import com.kushi.niobium.world.gen.ModWorldGeneration;
import com.kushi.niobium.world.tree.ModFoliagePlacerTypes;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Niobium implements ModInitializer {
	public static final String MOD_ID = "niobium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Do not initialize features here based on config
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();

		ModFoliagePlacerTypes.register();

		// Register composting chances
		CompostingChanceRegistry.INSTANCE.add(ModItems.RICE, 0.55f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.RICE_SEEDS, 0.3f);

		// Register Strippable Blocks
		StrippableBlockRegistry.register(ModBlocks.PALM_LOG, ModBlocks.STRIPPED_PALM_LOG);
		StrippableBlockRegistry.register(ModBlocks.PALM_WOOD, ModBlocks.STRIPPED_PALM_WOOD);

		// Register Flammable Blocks
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PALM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PALM_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PALM_PLANKS, 5, 20);

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SHULKER_SHELL, ModPotions.LEVITATION_POTION);
		});

		if (ModConfig.isOptionEnabled("enable_custom_world_gen") &&
				!ModConfig.isFeatureInitialized("custom_world_gen_initialized")) {
			ModWorldGeneration.generateModWorldGen();
			ModConfig.setFeatureInitialized("custom_world_gen_initialized", true);
		}

		if (ModConfig.isOptionEnabled("enable_custom_potions") &&
				!ModConfig.isFeatureInitialized("custom_potions_initialized")) {
			ModPotions.registerPotions();
			ModConfig.setFeatureInitialized("custom_potions_initialized", true);
		}

		if (ModConfig.isOptionEnabled("enable_custom_recipes") &&
				!ModConfig.isFeatureInitialized("custom_recipes_initialized")) {
			// Add custom recipe generation here if needed
			ModConfig.setFeatureInitialized("custom_recipes_initialized", true);
		}
	}
}
