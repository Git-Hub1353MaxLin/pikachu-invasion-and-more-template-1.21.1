package net.maxlin.tutorialmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.maxlin.tutorialmod.block.entity.ModBlockEntities;
import net.maxlin.tutorialmod.block.entity.custom.SlimeIncubatorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SlimeIncubatorBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<SlimeIncubatorBlock> CODEC = SlimeIncubatorBlock.createCodec((java.util.function.Function<Settings, SlimeIncubatorBlock>) SlimeIncubatorBlock::new);

    // Enum property for block state
    public static final EnumProperty<IncubatorState> INCUBATOR_STATE = EnumProperty.of("state", IncubatorState.class);

    public SlimeIncubatorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(INCUBATOR_STATE, IncubatorState.IDLE));
    }

    public enum IncubatorState implements StringIdentifiable {
        IDLE, ACTIVE, FINISHED;

        @Override
        public String asString() {
            return this.name().toLowerCase();
        }
    }


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(INCUBATOR_STATE);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SlimeIncubatorBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SlimeIncubatorBlockEntity) {
                ItemScatterer.spawn(world, pos, ((SlimeIncubatorBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((SlimeIncubatorBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ItemActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient) return null;

        return validateTicker(type, ModBlockEntities.SLIME_INCUBATOR_BE,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    // Utility method to update the incubator state
    public static void setIncubatorState(World world, BlockPos pos, IncubatorState state) {
        BlockState blockState = world.getBlockState(pos);
        world.setBlockState(pos, blockState.with(INCUBATOR_STATE, state), 3);
    }

    // Enum for idle / active / finished
}
