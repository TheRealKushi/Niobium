package com.kushi.niobium.world.tree;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.mixin.FoliagePlacerTypeInvoker;
import com.kushi.niobium.world.tree.custom.PalmTreeFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<?> PALM_TREE_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("palm_tree_foliage_placer", PalmTreeFoliagePlacer.CODEC);

    public static void register() {
        Niobium.LOGGER.info("Registering Foliage Placer for " + Niobium.MOD_ID);
    }
}
