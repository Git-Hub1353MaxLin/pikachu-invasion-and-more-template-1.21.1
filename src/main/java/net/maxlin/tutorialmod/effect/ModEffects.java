package net.maxlin.tutorialmod.effect;

import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SLIMEY = registerStatusEffect("slimey",
            new SlimeyEffect(StatusEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(PikachuInvasionAndMore.MOD_ID, "slimey"), -0.25f,
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));



    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(PikachuInvasionAndMore.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        PikachuInvasionAndMore.LOGGER.info("Registering Mod Effects for " + PikachuInvasionAndMore.MOD_ID);
    }
}
