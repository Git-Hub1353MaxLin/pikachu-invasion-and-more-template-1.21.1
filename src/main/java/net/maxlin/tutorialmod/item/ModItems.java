   package net.maxlin.tutorialmod.item;

   import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
   import net.maxlin.tutorialmod.PikachuInvasionAndMore;
   import net.minecraft.item.Item;
   import net.minecraft.item.ItemGroups;
   import net.minecraft.registry.Registries;
   import net.minecraft.registry.Registry;
   import net.minecraft.util.Identifier;

   public class ModItems {
       public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
       public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

       //name: "thunder_ore", new Item(new Item.Settings())
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