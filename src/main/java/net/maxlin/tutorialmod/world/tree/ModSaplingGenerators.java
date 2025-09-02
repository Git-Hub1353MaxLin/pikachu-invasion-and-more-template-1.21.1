package net.maxlin.tutorialmod.world.tree;

import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.maxlin.tutorialmod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator DRIFTWOOD = new SaplingGenerator(PikachuInvasionAndMore.MOD_ID + ":driftwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DRIFTWOOD_KEY), Optional.empty());
}
