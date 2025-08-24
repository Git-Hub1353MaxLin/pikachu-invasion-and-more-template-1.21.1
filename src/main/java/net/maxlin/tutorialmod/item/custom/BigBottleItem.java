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

        if (!filled) {
            // ✅ Scoop water
            BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
            if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
                BlockPos pos = hitResult.getBlockPos();
                if (world.getBlockState(pos).getBlock() == Blocks.WATER) {
                    if (!world.isClient) {
                        // Remove the water block like a real bucket
                        world.removeBlock(pos, false);

                        stack.decrement(1);
                        ItemStack result = new ItemStack(ModItems.BIG_WATER_BOTTLE);
                        if (stack.isEmpty()) {
                            return TypedActionResult.success(result);
                        } else if (!user.getInventory().insertStack(result)) {
                            user.dropItem(result, false);
                        }
                    }
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    return TypedActionResult.success(stack);
                }
            }
        } else {
            // ✅ Empty water
            BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
            if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
                BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
                if (world.getBlockState(pos).isAir()) {
                    if (!world.isClient) {
                        world.setBlockState(pos, Blocks.WATER.getDefaultState());

                        stack.decrement(1);
                        ItemStack result = new ItemStack(ModItems.BIG_GLASS_BOTTLE);
                        if (stack.isEmpty()) {
                            return TypedActionResult.success(result);
                        } else if (!user.getInventory().insertStack(result)) {
                            user.dropItem(result, false);
                        }
                    }
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    return TypedActionResult.success(stack);
                }
            }
        }

        return TypedActionResult.pass(stack);
    }

    // Raycast helper
    public static BlockHitResult raycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        return (BlockHitResult) player.raycast(5.0D, 0.0F, false); // Adjust reach if needed
    }
}



