package net.maxlin.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.*;
import net.maxlin.tutorialmod.block.ModBlocks;
import net.maxlin.tutorialmod.block.entity.ModBlockEntities;
import net.maxlin.tutorialmod.component.ModDataComponentTypes;
import net.maxlin.tutorialmod.effect.ModEffects;
import net.maxlin.tutorialmod.enchantment.ModEnchantmentEffects;
import net.maxlin.tutorialmod.enchantment.ModEnchantments;
import net.maxlin.tutorialmod.entity.ModEntities;
import net.maxlin.tutorialmod.entity.custom.MantisEntity;
import net.maxlin.tutorialmod.item.ModItemGroups;
import net.maxlin.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.maxlin.tutorialmod.potion.ModPotions;
import net.maxlin.tutorialmod.screen.ModScreenHandlers;
import net.maxlin.tutorialmod.screen.custom.PedestalScreen;
import net.maxlin.tutorialmod.sound.ModSounds;
import net.maxlin.tutorialmod.util.HammerUsageEvent;
import net.maxlin.tutorialmod.util.ModLootTableModifiers;
import net.maxlin.tutorialmod.util.TickScheduler;
import net.maxlin.tutorialmod.villager.ModVillagers;
import net.maxlin.tutorialmod.world.gen.ModWorldGeneration;
import net.maxlin.tutorialmod.particle.ModParticles;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
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

		ModEnchantmentEffects.registerEnchantmentEffects();

		ModWorldGeneration.generateWorldGen();

		ModEntities.registerModEntities();

		ModVillagers.registerVillagers();

		ModParticles.registerParticles();

		ModLootTableModifiers.modifyLootTables();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES,600);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			TickScheduler.tick();  // Calls your scheduler each server tick
		});


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

		CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER_SEEDS, 0.2f);

		StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_LOG, ModBlocks.STRIPPED_DRIFTWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_WOOD, ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LEAVES, 30, 60);

		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CHAIR, 5, 5);

		//Farmer trades
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
				factories.add((entity, random) -> new TradeOffer(
						new TradedItem(Items.EMERALD, 3),
				new ItemStack(ModItems.CAULIFLOWER, 8), 7, 2, 0.04f));

			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 1),
					new ItemStack(ModItems.HONEY_BERRIES, 2), 13, 2, 0.04f));
	});

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.DIAMOND, 6),
					new ItemStack(ModItems.CAULIFLOWER_SEEDS, 3), 7, 4, 0.04f));


		factories.add((entity, random) -> new TradeOffer(
				new TradedItem(Items.DIAMOND, 6),
				new ItemStack(ModBlocks.DRIFTWOOD_SAPLING, 1), 2, 7, 0.04f));
	});


        //Kaupenger trades
		TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 5),
					new ItemStack(ModItems.PINK_GARNET, 8), 7, 2, 0.04f));

			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItems.BIG_GLASS_BOTTLE, 1), 4, 2, 0.04f));

		});

		TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 2, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(ModItems.PINK_GARNET, 5),
					new ItemStack(ModItems.CHISEL, 8), 7, 4, 0.04f));

			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.NETHERITE_INGOT, 1),
					new ItemStack(ModBlocks.MAGIC_BLOCK, 1), 1, 14, 0.04f));

		});

		TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 3, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(ModItems.PINK_GARNET, 5),
					new ItemStack(ModBlocks.PINK_GARNET_LAMP, 8), 3, 4, 0.04f));

			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.NETHERITE_INGOT, 2),
					new ItemStack(ModItems.SPECTRE_STAFF, 1), 1, 8, 0.04f));

			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.NETHERITE_INGOT, 1),
					new ItemStack(ModBlocks.MAGIC_BLOCK, 1), 1, 14, 0.04f));

		});


	}
}