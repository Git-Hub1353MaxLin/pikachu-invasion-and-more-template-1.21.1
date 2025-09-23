package net.maxlin.tutorialmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f).build();

        public static final FoodComponent KOHLRABI = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
                .statusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200), 0.15f).build();

    public static final FoodComponent MANGO = new FoodComponent.Builder().nutrition(5).saturationModifier(0.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 250), 0.25f).build();

    public static final FoodComponent HONEY_BERRY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.15f)
            .snack().build();

}
