package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> COOKED_RICE_SMELTABLES = List.of(ModItems.RICE);

        offerSmelting(recipeExporter, COOKED_RICE_SMELTABLES, RecipeCategory.FOOD, ModItems.COOKED_RICE, 0.25f, 200, "cooked_rice");

        offerCampfireCooking(recipeExporter, COOKED_RICE_SMELTABLES, RecipeCategory.FOOD, ModItems.COOKED_RICE, 0.25f, 600, "cooked_rice");
        offerSmoking(recipeExporter, COOKED_RICE_SMELTABLES, RecipeCategory.FOOD, ModItems.COOKED_RICE, 0.25f, 100, "cooked_rice");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IRON_TILE)
                .pattern("###")
                .input('#', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.IRON_TILES_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.IRON_TILE)
                .criterion(hasItem(ModItems.IRON_TILE), conditionsFromItem(ModItems.IRON_TILE))
                .offerTo(recipeExporter);

        offerStonecuttingRecipe(recipeExporter, ModBlocks.IRON_TILES_BLOCK, ModBlocks.IRON_TILES_SLAB, 2, "iron_tiles_slab_stonecutting");
        offerStonecuttingRecipe(recipeExporter, ModBlocks.IRON_TILES_BLOCK, ModBlocks.IRON_TILES_WALL, 1, "iron_tiles_wall_stonecutting");
        offerStonecuttingRecipe(recipeExporter, ModBlocks.IRON_TILES_BLOCK, ModBlocks.IRON_TILES_STAIRS, 1, "iron_tiles_stairs_stonecutting");

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_SWORD,         // Base (Netherite Sword)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_SWORD,       // Result (Endrite Sword)
                "endrite_sword"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_PICKAXE,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_PICKAXE,     // Result (Endrite Pickaxe)
                "endrite_pickaxe"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_AXE,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_AXE,     // Result (Endrite Pickaxe)
                "endrite_axe"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_SHOVEL,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_SHOVEL,     // Result (Endrite Pickaxe)
                "endrite_shovel"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_HOE,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_HOE,     // Result (Endrite Pickaxe)
                "endrite_hoe"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_HELMET,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_HELMET,     // Result (Endrite Pickaxe)
                "endrite_helmet"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_CHESTPLATE,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_CHESTPLATE,     // Result (Endrite Pickaxe)
                "endrite_chestplate"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_LEGGINGS,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_LEGGINGS,     // Result (Endrite Pickaxe)
                "endrite_leggings"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.NETHERITE_BOOTS,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_BOOTS,     // Result (Endrite Pickaxe)
                "endrite_boots"
        );

        offerSmithingRecipe(
                recipeExporter,
                Items.BOW,      // Base (Netherite Pickaxe)
                ModItems.ENDRITE_SCRAP,       // Addition (Endrite Scrap)
                ModItems.ENDRITE_BOW,     // Result (Endrite Pickaxe)
                "endrite_bow"
        );

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.EMERALD_PRISM_BLOCK)
                .pattern("P#P")
                .pattern("#G#")
                .pattern("P#P")
                .input('#', Items.EMERALD)
                .input('G', Items.GLOWSTONE_DUST)
                .input('P', Items.PRISMARINE_SHARD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMERALD_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMERALD_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMERALD_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMERALD_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(recipeExporter);

        // Add a shapeless recipe to convert Nether Wart Block into 9 Nether Wart
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NETHER_WART, 9)
                .input(Blocks.NETHER_WART_BLOCK)
                .criterion(hasItem(Blocks.NETHER_WART_BLOCK), conditionsFromItem(Blocks.NETHER_WART_BLOCK))
                .offerTo(recipeExporter, "nether_wart_from_nether_wart_block");


        createDoorRecipe(recipeExporter, ModBlocks.IRON_TILES_DOOR, ModItems.IRON_TILE);

        offerWallRecipe(recipeExporter, RecipeCategory.MISC, ModBlocks.IRON_TILES_WALL, ModItems.IRON_TILE);

        createSlabRecipe(recipeExporter, ModBlocks.IRON_TILES_SLAB, ModItems.IRON_TILE);

        createTrapdoorRecipe(recipeExporter, ModBlocks.IRON_TILES_TRAPDOOR, ModItems.IRON_TILE);

        createStairsRecipe(recipeExporter, ModBlocks.IRON_TILES_STAIRS, ModItems.IRON_TILE, "iron_tiles_stairs_shaped");
        createStairsRecipe(recipeExporter, ModBlocks.QUARTZ_BRICKS_STAIRS, Blocks.QUARTZ_BRICKS, "quartz_bricks_stairs_shaped");
    }

    private void offerSmithingRecipe(
            RecipeExporter recipeExporter,
            ItemConvertible base,
            ItemConvertible addition,
            Item result, // Change to Item
            String recipeId
    ) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ModItems.ENDRITE_UPGRADE_SMITHING_TEMPLATE), // Template (e.g., Netherite Upgrade)
                        Ingredient.ofItems(base),                                     // Base item
                        Ingredient.ofItems(addition),                                 // Addition material
                        RecipeCategory.MISC,                                         // Recipe category
                        result                                                        // Result item
                )
                .criterion(FabricRecipeProvider.hasItem(base), FabricRecipeProvider.conditionsFromItem(base))
                .criterion(FabricRecipeProvider.hasItem(addition), FabricRecipeProvider.conditionsFromItem(addition))
                .offerTo(recipeExporter, "smithing/" + recipeId);
    }

    private void offerCampfireCooking(RecipeExporter recipeExporter, List<ItemConvertible> inputs, RecipeCategory recipeCategory, Item output, float experience, int cookingTime, String name) {
        for (ItemConvertible input : inputs) {
            CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(input), recipeCategory, output, experience, cookingTime)
                    .criterion(hasItem(input), conditionsFromItem(input))
                    .offerTo(recipeExporter, name + "_from_campfire_");
        }
    }

    private void offerSmoking(RecipeExporter recipeExporter, List<ItemConvertible> inputs, RecipeCategory recipeCategory, Item output, float experience, int cookingTime, String name) {
        for (ItemConvertible input : inputs) {
            CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), recipeCategory, output, experience, cookingTime)
                    .criterion(hasItem(input), conditionsFromItem(input))
                    .offerTo(recipeExporter, name + "_from_smoking_");
        }
    }

    private void createDoorRecipe(RecipeExporter recipeExporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(recipeExporter);
    }

    private void createSlabRecipe(RecipeExporter recipeExporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("###")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(recipeExporter);
    }

    private void createStairsRecipe(RecipeExporter recipeExporter, ItemConvertible stairs, ItemConvertible material, String recipeId) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, stairs)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', material)
                .criterion(FabricRecipeProvider.hasItem(material), FabricRecipeProvider.conditionsFromItem(material))
                .offerTo(recipeExporter, recipeId);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, stairs)
                .pattern("  #")
                .pattern(" ##")
                .pattern("###")
                .input('#', material)
                .criterion(FabricRecipeProvider.hasItem(material), FabricRecipeProvider.conditionsFromItem(material))
                .offerTo(recipeExporter, recipeId + "_flipped");
    }


    private void createTrapdoorRecipe(RecipeExporter recipeExporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("###")
                .pattern("###")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(recipeExporter);
    }

    private void offerStonecuttingRecipe(RecipeExporter recipeExporter, ItemConvertible input, ItemConvertible output, int count, String recipeId) {
        StonecuttingRecipeJsonBuilder.createStonecutting(
                        Ingredient.ofItems(input),                 // Convert input to Ingredient
                        RecipeCategory.BUILDING_BLOCKS,            // Choose appropriate RecipeCategory
                        output,                                    // Output item
                        count                                      // Item count
                )
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(recipeExporter, recipeId);
    }
}
