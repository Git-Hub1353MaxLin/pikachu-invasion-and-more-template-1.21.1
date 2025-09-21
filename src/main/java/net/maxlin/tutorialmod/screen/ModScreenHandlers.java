package net.maxlin.tutorialmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.maxlin.tutorialmod.PikachuInvasionAndMore;
import net.maxlin.tutorialmod.screen.custom.GrowthChamberScreenHandler;
import net.maxlin.tutorialmod.screen.custom.PedestalScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<PedestalScreenHandler> PEDESTAL_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(PikachuInvasionAndMore.MOD_ID, "pedestal_screen_handler"),
                    new ExtendedScreenHandlerType<>(PedestalScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<GrowthChamberScreenHandler> GROWTH_CHAMBER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(PikachuInvasionAndMore.MOD_ID, "growth_chamber_screen_handler"),
                    new ExtendedScreenHandlerType<>(GrowthChamberScreenHandler::new, BlockPos.PACKET_CODEC));



    public static void registerScreenHandlers() {
        PikachuInvasionAndMore.LOGGER.info("Registering Screen Handlers for " + PikachuInvasionAndMore.MOD_ID);
    }
}