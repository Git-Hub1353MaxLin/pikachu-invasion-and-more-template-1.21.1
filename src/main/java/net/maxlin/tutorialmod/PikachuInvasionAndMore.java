package net.maxlin.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.maxlin.tutorialmod.block.ModBlocks;
import net.maxlin.tutorialmod.item.ModItemGroups;
import net.maxlin.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PikachuInvasionAndMore implements ModInitializer {
	public static final String MOD_ID = "pikachu_invasion_and_more";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        //Third commit.
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES,3000);
	}
}