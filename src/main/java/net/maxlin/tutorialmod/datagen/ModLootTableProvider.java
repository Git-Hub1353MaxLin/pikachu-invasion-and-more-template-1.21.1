package net.maxlin.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.maxlin.tutorialmod.block.ModBlocks;
import net.maxlin.tutorialmod.block.custom.CauliflowerCropBlock;
import net.maxlin.tutorialmod.block.custom.HoneyBerryBushBlock;
import net.maxlin.tutorialmod.block.custom.KohlrabiCropBlock;
import net.maxlin.tutorialmod.block.custom.RadishCropBlock;
import net.maxlin.tutorialmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
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

        addDrop(ModBlocks.PINK_GARNET_BLOCK);
        addDrop(ModBlocks.RAW_PINK_GARNET_BLOCK);
        addDrop(ModBlocks.MAGIC_BLOCK);

        addDrop(ModBlocks.PINK_GARNET_ORE, oreDrops(ModBlocks.PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
        addDrop(ModBlocks.PINK_GARNET_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.PINK_GARNET_DEEPSLATE_ORE, ModItems.RAW_PINK_GARNET, 3, 7 ));

        addDrop(ModBlocks.PINK_GARNET_STAIRS);
        addDrop(ModBlocks.PINK_GARNET_SLAB, slabDrops(ModBlocks.PINK_GARNET_SLAB));
        addDrop(ModBlocks.PINK_GARNET_BUTTON);
        addDrop(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
        addDrop(ModBlocks.PINK_GARNET_FENCE);
        addDrop(ModBlocks.PINK_GARNET_FENCE_GATE);
        addDrop(ModBlocks.PINK_GARNET_WALL);
        addDrop(ModBlocks.PINK_GARNET_DOOR, doorDrops(ModBlocks.PINK_GARNET_DOOR));
        addDrop(ModBlocks.PINK_GARNET_TRAPDOOR);
        addDrop(ModBlocks.PINK_GARNET_LAMP);
        addDrop(ModBlocks.CHAIR);




        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ModBlocks.CAULIFLOWER_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(CauliflowerCropBlock.AGE, CauliflowerCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.CAULIFLOWER_CROP, this.cropDrops(ModBlocks.CAULIFLOWER_CROP, ModItems.CAULIFLOWER, ModItems.CAULIFLOWER_SEEDS, builder));

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.KOHLRABI_CROP)
                        .properties(StatePredicate.Builder.create().exactMatch(KohlrabiCropBlock.AGE, KohlrabiCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.KOHLRABI_CROP, this.cropDrops(ModBlocks.KOHLRABI_CROP, ModItems.KOHLRABI, ModItems.KOHLRABI_SEEDS, builder2));

        BlockStatePropertyLootCondition.Builder builder3 = BlockStatePropertyLootCondition.builder(ModBlocks.RADISH_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(RadishCropBlock.AGE, RadishCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.RADISH_CROP, this.cropDrops(ModBlocks.RADISH_CROP, ModItems.RADISH, ModItems.RADISH_SEEDS, builder3));



      this.addDrop(ModBlocks.HONEY_BERRY_BUSH,
                   block -> this.applyExplosionDecay(
            block,LootTable.builder().pool(LootPool.builder().conditionally(
            BlockStatePropertyLootCondition.builder(ModBlocks.HONEY_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(HoneyBerryBushBlock.AGE, 3))
            )
            .with(ItemEntry.builder(ModItems.HONEY_BERRIES))
            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
            .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
            ).pool(LootPool.builder().conditionally(
            BlockStatePropertyLootCondition.builder(ModBlocks.HONEY_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(HoneyBerryBushBlock.AGE, 2))
            ).with(ItemEntry.builder(ModItems.HONEY_BERRIES))
            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
            .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));

        addDrop(ModBlocks.PINK_GARNET_END_ORE, multipleOreDrops(ModBlocks.PINK_GARNET_END_ORE, ModItems.RAW_PINK_GARNET, 4, 9));
        addDrop(ModBlocks.PINK_GARNET_NETHER_ORE, multipleOreDrops(ModBlocks.PINK_GARNET_NETHER_ORE, ModItems.RAW_PINK_GARNET, 3, 8));

        addDrop(ModBlocks.DRIFTWOOD_LOG);
        addDrop(ModBlocks.DRIFTWOOD_WOOD);
        addDrop(ModBlocks.STRIPPED_DRIFTWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_DRIFTWOOD_WOOD);
        addDrop(ModBlocks.DRIFTWOOD_PLANKS);
        addDrop(ModBlocks.DRIFTWOOD_SAPLING);

        addDrop(ModBlocks.DRIFTWOOD_LEAVES, leavesDrops(ModBlocks.DRIFTWOOD_LEAVES, ModBlocks.DRIFTWOOD_SAPLING, 0.0625f));

        addDrop(ModBlocks.WALNUT_LOG);
        addDrop(ModBlocks.WALNUT_WOOD);
        addDrop(ModBlocks.STRIPPED_WALNUT_LOG);
        addDrop(ModBlocks.STRIPPED_WALNUT_WOOD);
        addDrop(ModBlocks.WALNUT_PLANKS);
        addDrop(ModBlocks.WALNUT_SAPLING);

        addDrop(ModBlocks.WALNUT_LEAVES, leavesDrops(ModBlocks.WALNUT_LEAVES, ModBlocks.WALNUT_SAPLING, 0.0625f));

}

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
