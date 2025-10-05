package net.maxlin.tutorialmod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record SlimeIncubatorRecipe(Ingredient inputItem, ItemStack output)
        implements Recipe<SlimeIncubatorRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(SlimeIncubatorRecipeInput input, World world) {
        if (world.isClient()) return false;
        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(SlimeIncubatorRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SLIME_INCUBATOR_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.SLIME_INCUBATOR_TYPE;
    }

    public static class Serializer implements RecipeSerializer<SlimeIncubatorRecipe> {
        public static final MapCodec<SlimeIncubatorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(SlimeIncubatorRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(SlimeIncubatorRecipe::output)
        ).apply(inst, SlimeIncubatorRecipe::new));

        public static final PacketCodec<RegistryByteBuf, SlimeIncubatorRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, SlimeIncubatorRecipe::inputItem,
                        ItemStack.PACKET_CODEC, SlimeIncubatorRecipe::output,
                        SlimeIncubatorRecipe::new
                );

        @Override
        public MapCodec<SlimeIncubatorRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, SlimeIncubatorRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}

