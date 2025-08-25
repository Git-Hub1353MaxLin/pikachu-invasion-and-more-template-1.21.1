package net.maxlin.tutorialmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import net.maxlin.tutorialmod.util.TickScheduler;

public class StaffOfPikachuXVIIIItem extends Item {

    public StaffOfPikachuXVIIIItem(Settings settings) {
        super(settings.maxDamage(250).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Client: do nothing, just return
        if (world.isClient) return TypedActionResult.success(stack, true);

        if (user.isSneaking()) {
            // SECOND ABILITY: chain explosion wave
            castExplosionWave((ServerWorld) world, user, stack);
            return TypedActionResult.success(stack, false);
        }

        // FIRST ABILITY: lightning at targeted block
        HitResult hit = raycastFromPlayer(world, user, RaycastContext.FluidHandling.NONE);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;
            BlockPos pos = blockHit.getBlockPos().up();

            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.refreshPositionAfterTeleport(
                        pos.getX() + 0.5,
                        pos.getY(),
                        pos.getZ() + 0.5
                );
                world.spawnEntity(lightning);
            }

            applyDurabilityAndCooldown(stack, user, 40);
        }

        return TypedActionResult.success(stack, false);
    }

    private void castExplosionWave(ServerWorld world, PlayerEntity user, ItemStack stack) {
        Vec3d start = user.getCameraPosVec(1.0F);
        Vec3d dir = user.getRotationVec(1.0F).normalize();

        final int maxDistance = 50;
        final int step = 2;                  // spacing between explosions
        final int ticksBetweenSteps = 2;     // much faster delay per explosion step
        final double damageRadius = 2.5;
        final float damageAmount = 14.0f;    // 7 hearts

        int index = 0;
        for (int dist = step; dist <= maxDistance; dist += step) {
            Vec3d point = start.add(dir.multiply(dist));
            BlockPos bpos = new BlockPos(
                    MathHelper.floor(point.x),
                    MathHelper.floor(point.y),
                    MathHelper.floor(point.z)
            );

            int delayTicks = index * ticksBetweenSteps;
            index++;

            TickScheduler.schedule(() -> {
                // Sound with slight random pitch for more dynamic feel
                world.playSound(
                        null,
                        bpos.getX() + 0.5,
                        bpos.getY() + 0.5,
                        bpos.getZ() + 0.5,
                        SoundEvents.ENTITY_GENERIC_EXPLODE,
                        SoundCategory.PLAYERS,
                        1.0f,
                        0.9f + world.random.nextFloat() * 0.2f
                );

                // Particles: a few more for a faster, denser visual
                world.spawnParticles(ParticleTypes.EXPLOSION_EMITTER, point.x, point.y, point.z, 2, 0, 0, 0, 0);
                world.spawnParticles(ParticleTypes.EXPLOSION, point.x, point.y, point.z, 12, 0.5, 0.5, 0.5, 0);

                // Damage entities near this point
                for (Entity e : world.getOtherEntities(user, user.getBoundingBox().expand(maxDistance))) {
                    if (e instanceof LivingEntity living && living != user) {
                        if (living.squaredDistanceTo(point) <= (damageRadius * damageRadius)) {
                            living.damage(world.getDamageSources().magic(), damageAmount);
                        }
                    }
                }
            }, delayTicks);
        }

        // Apply durability + cooldown once on cast
        applyDurabilityAndCooldown(stack, user, 100);
    }


    private void applyDurabilityAndCooldown(ItemStack stack, PlayerEntity user, int cooldownTicks) {
        if (!user.isCreative()) {
            stack.setDamage(stack.getDamage() + 1);
            if (stack.getDamage() >= stack.getMaxDamage()) {
                stack.decrement(1);
                user.getWorld().playSound(
                        null,
                        user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_ITEM_BREAK,
                        SoundCategory.PLAYERS,
                        1f,
                        1f
                );
            }
            user.getItemCooldownManager().set(this, cooldownTicks);
        }
    }

    private HitResult raycastFromPlayer(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d direction = player.getRotationVec(1.0F).multiply(50);
        Vec3d end = start.add(direction);

        return world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, fluidHandling, player));
    }
}


