package net.maxlin.tutorialmod.block.entity;

import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.maxlin.tutorialmod.block.ModBlocks;
import net.maxlin.tutorialmod.block.entity.custom.GrowthChamberBlockEntity;
import net.maxlin.tutorialmod.block.entity.custom.PedestalBlockEntity;
import net.maxlin.tutorialmod.block.entity.custom.SlimeIncubatorBlockEntity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PikachuInvasionAndMore.MOD_ID, "pedestal_be"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static final BlockEntityType<GrowthChamberBlockEntity> GROWTH_CHAMBER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PikachuInvasionAndMore.MOD_ID, "growth_chamber_be"),
                    BlockEntityType.Builder.create(GrowthChamberBlockEntity::new, ModBlocks.GROWTH_CHAMBER).build(null));

    public static final BlockEntityType<SlimeIncubatorBlockEntity> SLIME_INCUBATOR_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PikachuInvasionAndMore.MOD_ID, "slime_incubator_be"),
                    BlockEntityType.Builder.create(SlimeIncubatorBlockEntity::new, ModBlocks.SLIME_INCUBATOR).build(null));

    public static void registerBlockEntities() {
        PikachuInvasionAndMore.LOGGER.info("Registering Block Entities for " + PikachuInvasionAndMore.MOD_ID);
    }
}
