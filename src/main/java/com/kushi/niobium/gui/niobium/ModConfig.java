package com.kushi.niobium.gui.niobium;

import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    private static final Map<String, Boolean> OPTIONS = new HashMap<>();
    private static final Map<String, Boolean> INITIALIZED_FEATURES = new HashMap<>();

    static {
        // Default option values
        OPTIONS.put("enable_custom_world_gen", true);
        OPTIONS.put("enable_custom_potions", true);

        // Default initialization flags
        INITIALIZED_FEATURES.put("custom_world_gen_initialized", false);
        INITIALIZED_FEATURES.put("custom_potions_initialized", false);
    }

    public static boolean isOptionEnabled(String option) {
        return OPTIONS.getOrDefault(option, false);
    }

    public static void setOption(String option, boolean value) {
        OPTIONS.put(option, value);
    }

    public static boolean isFeatureInitialized(String feature) {
        return INITIALIZED_FEATURES.getOrDefault(feature, false);
    }

    public static void setFeatureInitialized(String feature, boolean initialized) {
        INITIALIZED_FEATURES.put(feature, initialized);
    }
}
