package net.maxlin.tutorialmod.entity;

import net.maxlin.tutorialmod.entity.custom.MantisEntity;
import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MantisEntity> MANTIS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(PikachuInvasionAndMore.MOD_ID, "mantis"),
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2.5f).build());


    public static void registerModEntities() {
        PikachuInvasionAndMore.LOGGER.info("Registering Mod Entities for " + PikachuInvasionAndMore.MOD_ID);
    }
}