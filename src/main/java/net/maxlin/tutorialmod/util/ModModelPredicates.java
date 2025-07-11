package net.maxlin.tutorialmod.util;

import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.maxlin.tutorialmod.component.ModDataComponentTypes;
import net.maxlin.tutorialmod.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.CHISEL, Identifier.of(PikachuInvasionAndMore.MOD_ID, "used"),
        (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.COORDINATES) !=null ? 1f : 0f);
    }
}
