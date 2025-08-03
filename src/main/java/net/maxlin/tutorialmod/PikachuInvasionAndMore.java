package net.maxlin.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
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
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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

		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if(entity instanceof SheepEntity sheepEntity && !world.isClient()) {
				if (player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("The Player just hit a sheep with an End Rod! You sick frick!"));
					player.getMainHandStack().decrement(1);
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1500, 8));

				}
				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if(entity instanceof CowEntity cowEntity && !world.isClient()) {
				if (player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("The Player just hit a cow with an End Rod! You sick frick!"));
					player.getMainHandStack().decrement(1);
					cowEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1500, 8));

				}
				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if(entity instanceof PigEntity pigEntity && !world.isClient()) {
				if (player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("The Player just hit a pig with an End Rod! You sick frick!"));
					player.getMainHandStack().decrement(1);
					pigEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1500, 8));

				}
				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if(entity instanceof ChickenEntity chickenEntity && !world.isClient()) {
				if (player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("The Player just hit a chicken with an End Rod! You sick frick!"));
					player.getMainHandStack().decrement(1);
					chickenEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1500, 8));

				}
				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		});
		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);

		});

	}
}