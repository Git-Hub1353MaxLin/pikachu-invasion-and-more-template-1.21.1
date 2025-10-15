package net.maxlin.tutorialmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.BreezeWindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public class Kiai extends Item {
    public Kiai(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Spawn the stationary Breeze Wind Charge
            BreezeWindChargeEntity charge = new BreezeWindChargeEntity(EntityType.BREEZE_WIND_CHARGE, world);

            // 🔹 Position it a bit in front of the player so it doesn’t spawn inside them
            double distance = 1.5;
            double x = user.getX() - Math.sin(Math.toRadians(user.getYaw())) * distance;
            double y = user.getEyeY();
            double z = user.getZ() + Math.cos(Math.toRadians(user.getYaw())) * distance;
            charge.setPosition(x, y, z);

            // Optional: align it with player’s facing
            charge.setYaw(user.getYaw());
            charge.setPitch(user.getPitch());

            world.spawnEntity(charge);
        }


        return TypedActionResult.success(stack, world.isClient());
    }
}

