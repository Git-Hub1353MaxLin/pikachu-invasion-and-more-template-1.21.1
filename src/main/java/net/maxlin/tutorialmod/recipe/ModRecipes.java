package net.maxlin.tutorialmod.recipe;


import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<GrowthChamberRecipe> GROWTH_CHAMBER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(PikachuInvasionAndMore.MOD_ID, "growth_chamber"),
            new GrowthChamberRecipe.Serializer());
    public static final RecipeType<GrowthChamberRecipe> GROWTH_CHAMBER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(PikachuInvasionAndMore.MOD_ID, "growth_chamber"), new RecipeType<GrowthChamberRecipe>() {
                @Override
                public String toString() {
                    return "growth_chamber";
                }
            });

    public static final RecipeSerializer<SlimeIncubatorRecipe> SLIME_INCUBATOR_SERIALIZER= Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(PikachuInvasionAndMore.MOD_ID, "slime_incubator"),
            new SlimeIncubatorRecipe.Serializer());
    public static final RecipeType<SlimeIncubatorRecipe> SLIME_INCUBATOR_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(PikachuInvasionAndMore.MOD_ID, "slime_incubator"), new RecipeType<SlimeIncubatorRecipe>() {
                @Override
                public String toString() {
                    return "slime_incubator";
                }
            });

    public static void registerRecipes() {
        PikachuInvasionAndMore.LOGGER.info("Registering Custom Recipes for " + PikachuInvasionAndMore.MOD_ID);
    }
}
