   package net.maxlin.tutorialmod.item;

   import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
   import net.maxlin.tutorialmod.PikachuInvasionAndMore;
   import net.maxlin.tutorialmod.item.custom.ChiselItem;
   import net.maxlin.tutorialmod.item.custom.ModFoodComponents;
   import net.minecraft.item.*;
   import net.minecraft.item.tooltip.TooltipType;
   import net.minecraft.registry.Registries;
   import net.minecraft.registry.Registry;
   import net.minecraft.text.Text;
   import net.minecraft.util.Identifier;

   import java.util.List;

   public class ModItems {
       public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()) {
           @Override
           public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
               tooltip.add(Text.translatable("tooltip.pikachu_invasion_and_more.pink_garnet.tooltip"));
               super.appendTooltip(stack, context, tooltip, type);
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

