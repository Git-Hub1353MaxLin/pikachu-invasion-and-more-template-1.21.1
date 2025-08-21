package net.maxlin.tutorialmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class StaffOfPikachuXVIIIItem extends Item {

    public StaffOfPikachuXVIIIItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(250));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Raycast where the player is looking
            HitResult hit = raycastFromPlayer(world, user, RaycastContext.FluidHandling.NONE);

            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) hit;
                BlockPos pos = blockHit.getBlockPos().up();

                // Spawn lightning
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                if (lightning != null) {
                    lightning.refreshPositionAfterTeleport(
                            pos.getX() + 0.5,
                            pos.getY(),
                            pos.getZ() + 0.5
                    );
                    world.spawnEntity(lightning);
                }

                // Increase durability
                stack.setDamage(stack.getDamage() + 1);

                // Add cooldown (40 ticks = 2 seconds)
                user.getItemCooldownManager().set(this, 40);

                if (!user.getAbilities().creativeMode) {
                    stack.setDamage(stack.getDamage() + 1);

                    if (stack.getDamage() >= stack.getMaxDamage()) {
                        world.playSound(null, user.getBlockPos(),
                                SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS,
                                1.0f, 1.0f);
                        stack.decrement(1); // remove when broken
                    }
                }



            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    // Raycast for aiming the staff
    private HitResult raycastFromPlayer(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d direction = player.getRotationVec(1.0F).multiply(50); // 50 block range
        Vec3d end = start.add(direction);

        return world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, fluidHandling, player));
    }
}

