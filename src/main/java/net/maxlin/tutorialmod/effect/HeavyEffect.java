package net.maxlin.tutorialmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class HeavyEffect extends StatusEffect {

    private static final UUID DAMAGE_BOOST_UUID = UUID.fromString("f4de126d-6666-4a11-bbce-0517fa45e770");
    private static final UUID SLOW_MOVEMENT_UUID = UUID.fromString("a5531e5a-2a4e-4cf9-a2d9-bf2d2c934a31");

    public HeavyEffect(StatusEffectCategory category, int color) {
        super(category, color);

        // +30% Attack Damage
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                Identifier.of(DAMAGE_BOOST_UUID.toString()),
                0.3,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        // -30% Movement Speed
        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                Identifier.of(SLOW_MOVEMENT_UUID.toString()),
                -0.3,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                Identifier.of(DAMAGE_BOOST_UUID.toString()),
                1.2,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }


    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Reduce jump strength by lowering upward velocity after jumping
        if (entity.getVelocity().y > 0) {
            entity.setVelocity(entity.getVelocity().x, entity.getVelocity().y * 0.6, entity.getVelocity().z);
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // applyUpdateEffect runs every tick
    }
}

