package net.maxlin.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock; // ✅ import vanilla CropBlock
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class CauliflowerCropBlock extends CropBlock {

    public static final IntProperty AGE = IntProperty.of("age", 0, 6);

    public CauliflowerCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return super.getSeedsItem();
    }

    @Override
    protected IntProperty getAgeProperty() {
        return super.getAgeProperty();
    }

    @Override
    public int getMaxAge() {
        return super.getMaxAge();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}

