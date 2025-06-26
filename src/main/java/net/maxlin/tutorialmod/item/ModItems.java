   package net.maxlin.tutorialmod.item;

   import net.maxlin.tutorialmod.PikachuInvasionAndMore;
   import net.minecraft.item.Item;
   import net.minecraft.registry.Registries;
   import net.minecraft.registry.Registry;
   import net.minecraft.util.Identifier;

   public class ModItems {


       private static Item registerItem(String name, Item item) {
           return Registry.register(Registries.ITEM, Identifier.of(PikachuInvasionAndMore.MOD_ID ))
       }
       //commit





   {
}