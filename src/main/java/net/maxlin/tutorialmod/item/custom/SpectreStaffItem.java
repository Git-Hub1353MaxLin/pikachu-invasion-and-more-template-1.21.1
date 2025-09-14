package net.maxlin.tutorialmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import net.maxlin.tutorialmod.util.TickScheduler;

public class SpectreStaffItem extends Item {

    public SpectreStaffItem(Settings settings) {
        super(settings.maxDamage(128).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            castSonicBoom(world, user, stack);
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    private void castSonicBoom(World world, PlayerEntity user, ItemStack stack) {
        if (!(world instanceof ServerWorld serverWorld)) return;

        Vec3d start = user.getCameraPosVec(1.0F);
        Vec3d dir = user.getRotationVec(1.0F).normalize();

        int maxDistance = 30;       // how far the beam travels
        double stepSize = 1.0;      // spacing between each beam step
        double ticksBetweenSteps = 1.5;  // delay between each step
        double damageRadius = 2.0;  // thickness of the beam
        float damageAmount = 10.0f; // hearts of damage

        int index = 0;
        for (double d = 1; d <= maxDistance; d += stepSize) {
            Vec3d point = start.add(dir.multiply(d));
            int delay = (int) (index * ticksBetweenSteps);

            final int currentIndex = index;
            TickScheduler.schedule(() -> {
                // Sonic boom particles
                serverWorld.spawnParticles(
                        ParticleTypes.SONIC_BOOM,
                        point.x, point.y, point.z,
                        1, 0, 0, 0, 0
                );

                // Damage entities along the beam
                for (Entity e : serverWorld.getOtherEntities(user, user.getBoundingBox().expand(maxDistance))) {
                    if (e instanceof LivingEntity living && living != user) {
                        if (living.squaredDistanceTo(point) <= damageRadius * damageRadius) {
                            living.damage(serverWorld.getDamageSources().sonicBoom(user), damageAmount);
                        }
                    }
                }

                // Play sound every few steps
                if (currentIndex % 5 == 0) {
                    serverWorld.playSound(
                            null,
                            point.x, point.y, point.z,
                            SoundEvents.ENTITY_WARDEN_SONIC_BOOM,
                            SoundCategory.PLAYERS,
                            2.0f,
                            1.0f
                    );
                }
            }, delay);

            index++;
        }

        // Cooldown & durability
        if (!user.isCreative()) {
            damageStaff(stack, world, user);
        }
        user.getItemCooldownManager().set(this, 100);
    }

    private void damageStaff(ItemStack stack, World world, PlayerEntity user) {
        stack.setDamage(stack.getDamage() + 1);
        if (stack.getDamage() >= stack.getMaxDamage()) {
            stack.decrement(1);
            world.playSound(
                    null,
                    user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_ITEM_BREAK,
                    SoundCategory.PLAYERS,
                    1f,
                    1f
            );
        }
    }
}
