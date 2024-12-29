package com.kushi.niobium.world.tree;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerator {
    public static final SaplingGenerator PALMTREE = new SaplingGenerator(Niobium.MOD_ID + ":palmtree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.PALM_TREE_KEY), Optional.empty());
}
