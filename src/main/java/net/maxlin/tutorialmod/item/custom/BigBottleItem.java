package net.maxlin.tutorialmod.item.custom;

import net.maxlin.tutorialmod.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class BigBottleItem extends Item {
    private final boolean filled;

    public BigBottleItem(Settings settings, boolean filled) {
        super(settings);
        this.filled = filled;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        BlockHitResult hitResult = raycast(world, user, filled ? RaycastContext.FluidHandling.NONE : RaycastContext.FluidHandling.SOURCE_ONLY);
        if (hitResult.getType() != BlockHitResult.Type.BLOCK) {
            return TypedActionResult.pass(stack);
        }

        BlockPos pos = hitResult.getBlockPos();

        if (!filled) {
            // 🥤 Try to scoop water
            if (world.getBlockState(pos).getFluidState().isStill()) {
                if (!world.isClient) {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);

                    ItemStack result = new ItemStack(ModItems.BIG_WATER_BOTTLE);
                    stack.decrement(1);
                    if (stack.isEmpty()) {
                        return TypedActionResult.success(result, world.isClient());
                    } else if (!user.getInventory().insertStack(result)) {
                        user.dropItem(result, false);
                    }
                }
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
                return TypedActionResult.success(stack, world.isClient());
            }
        } else {
            // 💧 Place water
            BlockPos placePos = pos.offset(hitResult.getSide());
            if (world.isAir(placePos)) {
                if (!world.isClient) {
                    world.setBlockState(placePos, Blocks.WATER.getDefaultState(), 3);

                    ItemStack result = new ItemStack(ModItems.BIG_GLASS_BOTTLE);
                    stack.decrement(1);
                    if (stack.isEmpty()) {
                        return TypedActionResult.success(result, world.isClient());
                    } else if (!user.getInventory().insertStack(result)) {
                        user.dropItem(result, false);
                    }
                }
                world.playSound(null, placePos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                return TypedActionResult.success(stack, world.isClient());
            }
        }

        return TypedActionResult.pass(stack);
    }

    // ✅ Proper raycast helper
    public static BlockHitResult raycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        double reach = 5.0D;
        var eyePos = player.getCameraPosVec(1.0F);
        var lookVec = player.getRotationVec(1.0F);
        var reachVec = eyePos.add(lookVec.multiply(reach));

        return world.raycast(new RaycastContext(
                eyePos,
                reachVec,
                RaycastContext.ShapeType.OUTLINE,
                fluidHandling,
                player
        ));
    }
}





