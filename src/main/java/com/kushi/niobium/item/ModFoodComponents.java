package com.kushi.niobium.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent RICE = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3F)
            .build();
    public static final FoodComponent COOKED_RICE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F)
            .build();
}
