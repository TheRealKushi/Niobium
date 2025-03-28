package com.kushi.niobium.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FoliagePlacerType.class)
public interface FoliagePlacerTypeInvoker {
	@Invoker("register")
	static <P extends FoliagePlacer> FoliagePlacerType<P> callRegister(String id, MapCodec<P> codec) {
		throw new IllegalStateException("This mixin should be replaced at runtime.");
	}
}
