package net.maxlin.tutorialmod.item.custom;

import net.maxlin.tutorialmod.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class MangoJuiceItem extends Item{


    public MangoJuiceItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 32;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if (!world.isClient){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,1500));
            }

            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
                if (stack.isEmpty()) {
                    return new ItemStack(ModItems.BIG_GLASS_BOTTLE);
                } else {
                    player.getInventory().insertStack(new ItemStack(ModItems.BIG_GLASS_BOTTLE));
                }
            }
        }


        return stack;
    }
}

