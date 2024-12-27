package com.kushi.niobium.util;

import com.kushi.niobium.Niobium;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ENDRITE_TOOL = createTag("needs_endrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_ENDRITE_TOOL = createTag("incorrect_for_endrite_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Niobium.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Niobium.MOD_ID, name));
        }
    }
}
