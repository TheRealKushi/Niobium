package com.kushi.niobium;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.component.ModDataComponentTypes;
import com.kushi.niobium.entity.ModEntities;
import com.kushi.niobium.item.ModItemGroups;
import com.kushi.niobium.item.ModItems;
import com.kushi.niobium.potion.ModPotions;
import com.kushi.niobium.util.ModLootTableModifiers;
import com.kushi.niobium.world.gen.ModWorldGeneration;
import com.kushi.niobium.world.tree.ModFoliagePlacerTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		ModEntities.registerModEntities();
		ModLootTableModifiers.modifyLootTables();

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

		ModPotions.registerPotions();

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(
					Potions.AWKWARD,
					Items.SHULKER_SHELL,
					Registries.POTION.getEntry(ModPotions.LEVITATION_POTION).orElseThrow()
			);
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(
					Potions.AWKWARD,
					Items.GOLDEN_APPLE,
					Registries.POTION.getEntry(ModPotions.GOLDEN_APPLE_POTION).orElseThrow()
			);
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(
					Potions.AWKWARD,
					Items.ENCHANTED_GOLDEN_APPLE,
					Registries.POTION.getEntry(ModPotions.ENCHANTED_GOLDEN_APPLE_POTION).orElseThrow()
			);
		});

		ModWorldGeneration.generateModWorldGen();
	}
}