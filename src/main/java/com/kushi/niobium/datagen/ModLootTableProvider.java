package com.kushi.niobium.datagen;

import com.kushi.niobium.block.ModBlocks;
import com.kushi.niobium.block.custom.BlueBerryBushBlock;
import com.kushi.niobium.block.custom.RiceCropBlock;
import com.kushi.niobium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(ModBlocks.EMERALD_PRISM_BLOCK);
        addDrop(ModBlocks.IRON_TILES_BLOCK);
        addDrop(ModBlocks.IRON_TILES_STAIRS);
        addDrop(ModBlocks.QUARTZ_BRICKS_STAIRS);
        addDrop(ModBlocks.IRON_TILES_SLAB, slabDrops(ModBlocks.IRON_TILES_SLAB));
        addDrop(ModBlocks.IRON_TILES_DOOR, doorDrops(ModBlocks.IRON_TILES_DOOR));
        addDrop(ModBlocks.IRON_TILES_WALL);
        addDrop(ModBlocks.IRON_TILES_TRAPDOOR);
        addDrop(ModBlocks.ENDRITE_BLOCK);

        addDrop(ModBlocks.PALM_LOG);
        addDrop(ModBlocks.PALM_WOOD);
        addDrop(ModBlocks.STRIPPED_PALM_LOG);
        addDrop(ModBlocks.STRIPPED_PALM_WOOD);
        addDrop(ModBlocks.PALM_PLANKS);
        addDrop(ModBlocks.PALM_SAPLING);

        addDrop(ModBlocks.PALM_LEAVES, leavesDrops(ModBlocks.PALM_LEAVES, ModBlocks.PALM_SAPLING, 0.0625f));

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.RICE_CROP)
                        .properties(StatePredicate.Builder.create().exactMatch(RiceCropBlock.AGE, 5));
        this.addDrop(ModBlocks.RICE_CROP, this.cropDrops(ModBlocks.RICE_CROP, ModItems.RICE, ModItems.RICE_SEEDS, builder2));

        this.addDrop(
                ModBlocks.BLUEBERRY_BUSH, (
                        block) -> (LootTable.Builder)this.applyExplosionDecay(
                                block,
                            LootTable.builder()
                                    .pool(
                                            LootPool.builder()
                                                    .conditionally(
                                                            BlockStatePropertyLootCondition.builder(ModBlocks.BLUEBERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueBerryBushBlock.AGE, 3))
                                                    )
                                                    .with(ItemEntry.builder(ModItems.BLUEBERRIES))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                    ).pool(
                                            LootPool.builder()
                                                    .conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.BLUEBERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueBerryBushBlock.AGE, 2))
                                                    )
                                                    .with(ItemEntry.builder(ModItems.BLUEBERRIES))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                    )));

        addDrop(ModBlocks.ENDRITE_ORE_BLOCK, oreDrops(ModBlocks.ENDRITE_ORE_BLOCK, ModItems.ENDRITE_SCRAP));
    }
}
