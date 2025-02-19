package net.earlyalpha.aristysa;

import com.mojang.logging.LogUtils;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.block.entity.ModBlockEntities;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.item.ModCreativeTabs;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.networking.ModMessages;
import net.earlyalpha.aristysa.screen.CyberwareGuiScreen;
import net.earlyalpha.aristysa.screen.FusionCrafterScreen;
import net.earlyalpha.aristysa.screen.ModMenuTypes;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Aristysa.MOD_ID)
public class Aristysa {
    public static final String MOD_ID = "aristysa";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Aristysa() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModCreativeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
        });

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.FUSION_CRAFTER_MENU.get(), FusionCrafterScreen::new);
            MenuScreens.register(ModMenuTypes.CYBERWARE_MENU.get(), CyberwareGuiScreen::new);
        }
    }
}
