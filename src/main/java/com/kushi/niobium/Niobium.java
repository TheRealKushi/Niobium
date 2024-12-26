package com.kushi.niobium;

import com.kushi.niobium.blocks.ModBlocks;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Niobium implements ModInitializer {
	public static final String MOD_ID = "niobium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}