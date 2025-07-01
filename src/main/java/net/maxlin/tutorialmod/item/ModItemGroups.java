package net.maxlin.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.maxlin.tutorialmod.block.ModBlocks;
import net.maxlin.tutorialmod.item.custom.ModFoodComponents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PIKACHU_INVASION_AND_MORE = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PikachuInvasionAndMore.MOD_ID, "pikachu_invasion_and_more_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET))
                    .displayName(Text.translatable("itemgroup.pikachu_invasion_and_more.pikachu_invasion_and_more_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);

                        entries.add(ModBlocks.PINK_GARNET_ORE);
                        entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

                        entries.add(ModItems.CHISEL);

                        entries.add(ModBlocks.MAGIC_BLOCK);

                        entries.add(ModItems.CAULIFLOWER);
                        entries.add(ModItems.STARLIGHT_ASHES);

                            }).build());

    public static void registerItemGroups() {
        PikachuInvasionAndMore.LOGGER.info("Registering Item Groups for " + PikachuInvasionAndMore.MOD_ID);
    }
}
