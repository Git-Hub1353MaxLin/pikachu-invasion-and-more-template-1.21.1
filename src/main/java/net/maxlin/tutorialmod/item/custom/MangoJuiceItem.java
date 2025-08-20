package net.maxlin.tutorialmod.item.custom;

import net.maxlin.tutorialmod.item.ModItems;
import net.maxlin.tutorialmod.util.ModTags;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class MangoJuiceItem extends Item{
    public static final FoodComponent MANGO_JUICE = new FoodComponent.Builder().nutrition(0).saturationModifier(0f)
        .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1500), 1f).alwaysEdible().build();

    public MangoJuiceItem(Settings settings) {
        super(settings.food(MANGO_JUICE).maxCount(1));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            return new ItemStack(ModItems.BIG_GLASS_BOTTLE);
        }

        return result;
    }
}

