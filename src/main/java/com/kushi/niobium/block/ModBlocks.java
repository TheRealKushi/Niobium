package com.kushi.niobium.block;

import com.kushi.niobium.Niobium;
import com.kushi.niobium.block.custom.BlueBerryBushBlock;
import com.kushi.niobium.block.custom.RiceCropBlock;
import com.kushi.niobium.world.tree.ModSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
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

    public static final Block IRON_TILES_BLOCK = registerBlock("iron_tiles_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)));

    public static final Block IRON_TILES_STAIRS = registerBlock("iron_tiles_stairs",
            new StairsBlock(ModBlocks.IRON_TILES_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create()
                            .strength(5.0F, 6.0F)
                            .requiresTool()
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
    public static final Block PALM_LEAVES = registerBlock("palm_leaves",
            new Block(AbstractBlock.Settings.copy(Blocks.JUNGLE_LEAVES)));
    public static final Block PALM_SAPLING = registerBlock("palm_sapling",
            new SaplingBlock(ModSaplingGenerator.PALMTREE, AbstractBlock.Settings.copy(Blocks.JUNGLE_SAPLING)));

    public static final Block ENDRITE_BLOCK = registerBlock("endrite_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0F, 3.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block ENDRITE_ORE_BLOCK = registerBlock("endrite_ore_block",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 7),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .sounds(BlockSoundGroup.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(3.0F, 3.0F)
            ));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, name), block);
    }

        private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Niobium.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Niobium.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Niobium.LOGGER.info("Registering Mod Blocks for " + Niobium.MOD_ID);
    }
}
