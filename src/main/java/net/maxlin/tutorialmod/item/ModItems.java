   package net.maxlin.tutorialmod.item;

   import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
   import net.maxlin.tutorialmod.PikachuInvasionAndMore;
   import net.maxlin.tutorialmod.item.custom.*;
   import net.maxlin.tutorialmod.sound.ModSounds;
   import net.minecraft.client.gui.screen.Screen;
   import net.minecraft.item.*;
   import net.minecraft.item.tooltip.TooltipType;
   import net.minecraft.registry.Registries;
   import net.minecraft.registry.Registry;
   import net.minecraft.resource.featuretoggle.FeatureFlags;
   import net.minecraft.text.Text;
   import net.minecraft.util.Identifier;

   import java.util.List;

   public class ModItems {
       public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()) {
           @Override
           public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
               if (Screen.hasShiftDown()) {
                   tooltip.add(Text.translatable("tooltip.pikachu_invasion_and_more.pink_garnet.shift_down"));
               } else {
                   tooltip.add(Text.translatable("tooltip.pikachu_invasion_and_more.pink_garnet"));
               }

           }
       });
       public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

       public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

       public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)) {
           @Override
           public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
               tooltip.add(Text.translatable("tooltip.pikachu_invasion_and_more.cauliflower.tooltip"));
               super.appendTooltip(stack, context, tooltip, type);
           }
       });

       public static final Item MANGO = registerItem("mango", new Item(new Item.Settings().food(ModFoodComponents.MANGO)));

       public static final Item MANGO_JUICE = registerItem("mango_juice", new MangoJuiceItem(new Item.Settings().maxCount(1)));

       public static final Item BIG_GLASS_BOTTLE = registerItem("big_glass_bottle",
               new BigBottleItem(new Item.Settings().maxCount(16), false));

       public static final Item BIG_WATER_BOTTLE = registerItem("big_water_bottle",
               new BigBottleItem(new Item.Settings().maxCount(1), true));

       public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings()) {
           @Override
           public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
               tooltip.add(Text.translatable("tooltip.pikachu_invasion_and_more.starlight_ashes.tooltip"));
               super.appendTooltip(stack, context, tooltip, type);
           }
       });

       public static final Item PINK_GARNET_SWORD = registerItem("pink_garnet_sword",
               new SwordItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                       .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 4, -2.4f))));

       public static final Item PINK_GARNET_PICKAXE = registerItem("pink_garnet_pickaxe",
                       new PickaxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                               .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 2, -2.8f))));

       public static final Item PINK_GARNET_AXE = registerItem("pink_garnet_axe",
               new AxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                       .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 6, -3.2f))));

       public static final Item PINK_GARNET_SHOVEL = registerItem("pink_garnet_shovel",
                       new ShovelItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                               .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1, -2.4f))));

       public static final Item PINK_GARNET_HOE = registerItem("pink_garnet_hoe",
                       new HoeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                               .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 0, -3f))));

       public static final Item PINK_GARNET_HAMMER = registerItem("pink_garnet_hammer",
               new HammerItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                       .attributeModifiers(HammerItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 7, -2.6f))));

       public static final Item PINK_GARNET_HELMET = registerItem("pink_garnet_helmet",
               new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                       .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));

       public static final Item PINK_GARNET_CHESTPLATE = registerItem("pink_garnet_chestplate",
               new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                       .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));

       public static final Item PINK_GARNET_LEGGINGS = registerItem("pink_garnet_leggings",
               new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                       .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));

       public static final Item PINK_GARNET_BOOTS = registerItem("pink_garnet_boots",
               new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                       .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

       public static final Item PINK_GARNET_HORSE_ARMOR = registerItem("pink_garnet_horse_armor",
              new AnimalArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1)));

       public static final Item KAUPEN_SMITHING_TEMPLATE = registerItem("kaupen_armor_trim_smithing_template",
                SmithingTemplateItem.of(Identifier.of(PikachuInvasionAndMore.MOD_ID, "kaupen"), FeatureFlags.VANILLA));

       public static final Item KAUPEN_BOW = registerItem("kaupen_bow",
               new BowItem(new Item.Settings().maxDamage(500)));

       public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
               new Item(new Item.Settings().jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).maxCount(1)));


       public static final Item STAFF_OF_PIKACHU_XVIII = registerItem("staff_of_pikachu_xviii",
               new StaffOfPikachuXVIIIItem( new Item.Settings().maxDamage(250).maxCount(1)));







       private static Item registerItem(String name, Item item) {
           return Registry.register(Registries.ITEM, Identifier.of(PikachuInvasionAndMore.MOD_ID, name), item);
       }

       //commit

       public static void registerModItems() {
           PikachuInvasionAndMore.LOGGER.info("Registering Mod Items for " + PikachuInvasionAndMore.MOD_ID);

           ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
               entries.add(PINK_GARNET);
               entries.add(RAW_PINK_GARNET);
           });
      }
  }

