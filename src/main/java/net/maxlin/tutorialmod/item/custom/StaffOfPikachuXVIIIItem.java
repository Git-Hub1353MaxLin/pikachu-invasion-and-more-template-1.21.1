package net.maxlin.tutorialmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
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
import net.minecraft.server.world.ServerWorld;
import net.maxlin.tutorialmod.util.TickScheduler;

public class StaffOfPikachuXVIIIItem extends SwordItem {

    public StaffOfPikachuXVIIIItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    // LEFT-CLICK on mob
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!world.isClient) {
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
                world.spawnEntity(lightning);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    // RIGHT-CLICK on block
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            if (user.isSneaking()) {
                // Sneak + right-click -> explosion wave
                castExplosionWave((ServerWorld) world, user, stack);
                return TypedActionResult.success(stack, false);
            }

            // Lightning at targeted block
            HitResult hit = raycastFromPlayer(world, user, RaycastContext.FluidHandling.NONE);
            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) hit;
                BlockPos pos = blockHit.getBlockPos().up();

                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                if (lightning != null) {
                    lightning.refreshPositionAfterTeleport(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                    world.spawnEntity(lightning);
                }

                if (!user.isCreative()) {
                    stack.setDamage(stack.getDamage() + 1);
                    if (stack.getDamage() >= stack.getMaxDamage()) stack.decrement(1);
                    user.getItemCooldownManager().set(this, 40); // 2 sec cooldown
                }
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    private void castExplosionWave(ServerWorld world, PlayerEntity user, ItemStack stack) {
        Vec3d start = user.getCameraPosVec(1.0F);
        Vec3d dir = user.getRotationVec(1.0F).normalize();

        final int maxDistance = 50;
        final int step = 3;
        final int ticksBetweenSteps = 2;
        final double damageRadius = 2.5;
        final float damageAmount = 14.0f;

        int index = 0;
        for (int dist = step; dist <= maxDistance; dist += step) {
            Vec3d point = start.add(dir.multiply(dist));
            BlockPos bpos = new BlockPos((int) Math.floor(point.x), (int) Math.floor(point.y), (int) Math.floor(point.z));
            int delayTicks = index * ticksBetweenSteps;
            index++;

            TickScheduler.schedule(() -> {
                world.playSound(null, bpos.getX() + 0.5, bpos.getY() + 0.5, bpos.getZ() + 0.5,
                        SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0f, 1.0f);

                world.spawnParticles(ParticleTypes.EXPLOSION_EMITTER, point.x, point.y, point.z, 1, 0, 0, 0, 0);
                world.spawnParticles(ParticleTypes.EXPLOSION, point.x, point.y, point.z, 8, 0.5, 0.5, 0.5, 0);

                for (Entity e : world.getOtherEntities(user, user.getBoundingBox().expand(maxDistance))) {
                    if (e instanceof LivingEntity living && living != user) {
                        if (living.squaredDistanceTo(point) <= (damageRadius * damageRadius)) {
                            living.damage(world.getDamageSources().magic(), damageAmount);
                        }
                    }
                }
            }, delayTicks);
        }

        if (!user.isCreative()) {
            stack.setDamage(stack.getDamage() + 1);
            if (stack.getDamage() >= stack.getMaxDamage()) stack.decrement(1);
            user.getItemCooldownManager().set(this, 100);
        }
    }

    private HitResult raycastFromPlayer(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d direction = player.getRotationVec(1.0F).multiply(50);
        Vec3d end = start.add(direction);

        return world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, fluidHandling, player));
    }
}



