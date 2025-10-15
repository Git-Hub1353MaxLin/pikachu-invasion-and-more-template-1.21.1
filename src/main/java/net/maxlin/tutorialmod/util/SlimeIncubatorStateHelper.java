package net.maxlin.tutorialmod.util;

import net.maxlin.tutorialmod.block.custom.SlimeIncubatorBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SlimeIncubatorStateHelper {

    /**
     * Updates the state of a Slime Incubator block.
     *
     * @param world The world containing the block
     * @param pos The block position
     * @param state The state to set (IDLE, ACTIVE, FINISHED)
     */
    public static void setState(World world, BlockPos pos, SlimeIncubatorBlock.IncubatorState state) {
        BlockState blockState = world.getBlockState(pos);

        if (blockState.getBlock() instanceof SlimeIncubatorBlock) {
            world.setBlockState(pos, blockState.with(SlimeIncubatorBlock.INCUBATOR_STATE, state), 3);
        }
    }
}

