package net.maxlin.tutorialmod.block.entity;

import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.maxlin.tutorialmod.block.ModBlocks;
import net.maxlin.tutorialmod.block.entity.PedestalBlockEntity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PikachuInvasionAndMore.MOD_ID, "pedestal_be"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static void registerBlockEntities() {
        PikachuInvasionAndMore.LOGGER.info("Registering Block Entities for " + PikachuInvasionAndMore.MOD_ID);
    }
}
