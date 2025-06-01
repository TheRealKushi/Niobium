package com.kushi.niobium.block;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.block.custom.BlueBerryBushBlock;
import com.kushi.niobium.block.custom.ChairBlock;
import com.kushi.niobium.block.custom.ModSaplingBlock;
import com.kushi.niobium.block.custom.RiceCropBlock;
import com.kushi.niobium.world.tree.ModSaplingGenerator;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block EMERALD_PRISM_BLOCK = registerBlock("emerald_prism_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.EMERALD_GREEN)
                    .instrument(NoteBlockInstrument.BIT)
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .luminance(state -> 15)
                    .sounds(BlockSoundGroup.METAL)));

    // First declare and register IRON_TILES_BLOCK
    public static final Block IRON_TILES_BLOCK = registerBlock("iron_tiles_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)));

    public static final Block QUARTZ_BRICKS_STAIRS = registerBlock("quartz_bricks_stairs",
            new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create()
                            .strength(0.8F)
                            .requiresTool()));

    public static final Block IRON_TILES_SLAB = registerBlock("iron_tiles_slab",
            new SlabBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)));

    public static final Block IRON_TILES_STAIRS = registerBlock("iron_tiles_stairs",
            new StairsBlock(IRON_TILES_BLOCK.getDefaultState(), // Delay evaluation
                    AbstractBlock.Settings.create()
                            .strength(5.0F, 6.0F)
                            .requiresTool()
                            .sounds(BlockSoundGroup.METAL)));

    public static final Block IRON_TILES_TRAPDOOR = registerBlock("iron_tiles_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON ,AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()));

    public static final Block IRON_TILES_WALL = registerBlock("iron_tiles_wall",
            new WallBlock(AbstractBlock.Settings.create()
                    .strength(5.0F, 6.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)));

    public static final Block IRON_TILES_DOOR = registerBlock("iron_tiles_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .strength(5.0F, 6.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()));

    public static final Block RICE_CROP = registerBlockWithoutBlockItem("rice_crop",
            new RiceCropBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .mapColor(MapColor.DARK_GREEN)));

    public static final Block BLUEBERRY_BUSH = registerBlockWithoutBlockItem("blueberry_bush",
            new BlueBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final Block DIRT_SLAB = registerBlock("dirt_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

    public static final Block GLASS_SLAB = registerBlock("glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block BLACK_STAINED_GLASS_SLAB = registerBlock("black_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS)));
    public static final Block BLUE_STAINED_GLASS_SLAB = registerBlock("blue_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS)));
    public static final Block BROWN_STAINED_GLASS_SLAB = registerBlock("brown_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS)));
    public static final Block CYAN_STAINED_GLASS_SLAB = registerBlock("cyan_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS)));
    public static final Block GRAY_STAINED_GLASS_SLAB = registerBlock("gray_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS)));
    public static final Block GREEN_STAINED_GLASS_SLAB = registerBlock("green_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS)));
    public static final Block LIGHT_BLUE_STAINED_GLASS_SLAB = registerBlock("light_blue_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS)));
    public static final Block LIGHT_GRAY_STAINED_GLASS_SLAB = registerBlock("light_gray_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS)));
    public static final Block LIME_STAINED_GLASS_SLAB = registerBlock("lime_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS)));
    public static final Block MAGENTA_STAINED_GLASS_SLAB = registerBlock("magenta_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS)));
    public static final Block ORANGE_STAINED_GLASS_SLAB = registerBlock("orange_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS)));
    public static final Block PINK_STAINED_GLASS_SLAB = registerBlock("pink_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS)));
    public static final Block PURPLE_STAINED_GLASS_SLAB = registerBlock("purple_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS)));
    public static final Block RED_STAINED_GLASS_SLAB = registerBlock("red_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS)));
    public static final Block WHITE_STAINED_GLASS_SLAB = registerBlock("white_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS)));
    public static final Block YELLOW_STAINED_GLASS_SLAB = registerBlock("yellow_stained_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS)));
    public static final Block TINTED_GLASS_SLAB = registerBlock("tinted_glass_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.TINTED_GLASS)));

    public static final Block PALM_LOG = registerBlock("palm_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_LOG)));
    public static final Block PALM_WOOD = registerBlock("palm_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_WOOD)));
    public static final Block STRIPPED_PALM_LOG = registerBlock("stripped_palm_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_JUNGLE_LOG)));
    public static final Block STRIPPED_PALM_WOOD = registerBlock("stripped_palm_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_JUNGLE_WOOD)));
    public static final Block PALM_PLANKS = registerBlock("palm_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final Block PALM_STAIRS = registerBlock("palm_stairs",
            new StairsBlock(ModBlocks.PALM_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.JUNGLE_STAIRS)));
    public static final Block PALM_SLAB = registerBlock("palm_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_SLAB)));
    public static final Block PALM_FENCE = registerBlock("palm_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_FENCE)));
    public static final Block PALM_FENCE_GATE = registerBlock("palm_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.JUNGLE_FENCE_GATE)));

    public static final Identifier PALM_SIGN_TEXTURE = Identifier.of(Niobium.MOD_ID, "entity/signs/palm");
    public static final Identifier PALM_HANGING_SIGN_TEXTURE = Identifier.of(Niobium.MOD_ID, "entity/signs/hanging/palm");
    public static final Identifier PALM_HANGING_GUI_SIGN_TEXTURE = Identifier.of(Niobium.MOD_ID, "textures/gui/hanging_signs/palm");

    public static final Block STANDING_PALM_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, "palm_standing_sign"),
            new TerraformSignBlock(PALM_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    public static final Block WALL_PALM_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, "palm_wall_sign"),
            new TerraformWallSignBlock(PALM_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)));
    public static final Block HANGING_PALM_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, "palm_hanging_sign"),
            new TerraformHangingSignBlock(PALM_HANGING_SIGN_TEXTURE, PALM_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    public static final Block WALL_HANGING_PALM_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, "palm_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(PALM_HANGING_SIGN_TEXTURE, PALM_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final Block PALM_LEAVES = registerBlock("palm_leaves",
            new Block(AbstractBlock.Settings.copy(Blocks.JUNGLE_LEAVES)));
    public static final Block PALM_SAPLING = registerBlock("palm_sapling",
            new ModSaplingBlock(ModSaplingGenerator.PALMTREE, AbstractBlock.Settings.copy(Blocks.JUNGLE_SAPLING), Blocks.SAND));

    public static final Block ENDRITE_BLOCK = register3ArgBlock("endrite_block",
            AbstractBlock.Settings.create()
                    .strength(3.0F, 3.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .instrument(NoteBlockInstrument.BASEDRUM), // Block settings
            new Item.Settings()
                    .fireproof()
                    .rarity(Rarity.RARE)// Item settings
    );

    public static final Block ENDRITE_ORE_BLOCK = registerBlock("endrite_ore_block",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 7),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .sounds(BlockSoundGroup.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(3.0F, 3.0F)));

    public static final Block WOODEN_CHAIR = registerBlock("wooden_chair",
            new ChairBlock(AbstractBlock.Settings.create().nonOpaque().burnable()));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, name), block);
    }

    private static Block register3ArgBlock(String name, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        Block block = new Block(blockSettings); // Create the block with the given settings
        register3ArgBlockItem(name, block, itemSettings); // Register the item variant with item settings
        return Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, name), block);
    }

    // Make sure you have this method to register block items
    private static void register3ArgBlockItem(String name, Block block, Item.Settings itemSettings) {
        Registry.register(Registries.ITEM, Identifier.of(Niobium.MOD_ID, name), new BlockItem(block, itemSettings));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Niobium.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    //Test

    public static void registerModBlocks() {
        Niobium.LOGGER.info("Registering Mod Blocks for " + Niobium.MOD_ID);
    }
}