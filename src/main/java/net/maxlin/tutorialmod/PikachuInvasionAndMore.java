package net.maxlin.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.maxlin.tutorialmod.block.ModBlocks;
import net.maxlin.tutorialmod.component.ModDataComponentTypes;
import net.maxlin.tutorialmod.effect.ModEffects;
import net.maxlin.tutorialmod.item.ModItemGroups;
import net.maxlin.tutorialmod.item.ModItems;

import net.maxlin.tutorialmod.potion.ModPotions;
import net.maxlin.tutorialmod.sound.ModSounds;
import net.maxlin.tutorialmod.util.HammerUsageEvent;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
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

		ModDataComponentTypes.registerDataComponentTypes();

		ModSounds.registerSounds();

		ModEffects.registerEffects();

		ModPotions.registerPotions();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES,3000);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
		});
	}
}