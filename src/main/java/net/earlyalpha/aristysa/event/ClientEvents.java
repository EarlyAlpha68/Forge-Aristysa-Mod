package net.earlyalpha.aristysa.event;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.networking.ModMessages;
import net.earlyalpha.aristysa.networking.packet.CyberLegC2SPacket;
import net.earlyalpha.aristysa.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Aristysa.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.OPTICAL_CAMO_USE.consumeClick()) {

            }
            if (KeyBinding.ENDER_EYE_USE.consumeClick()) {

            }
            if (KeyBinding.CYBERLEG_USE.consumeClick()) {
                ModMessages.sendToServer(new CyberLegC2SPacket());
            }
            if (KeyBinding.CYBER_IMPLANT_SCREEN_OPEN.consumeClick()) {

            }
        }

    }
    @Mod.EventBusSubscriber(modid = Aristysa.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.OPTICAL_CAMO_USE);
            event.register(KeyBinding.ENDER_EYE_USE);
            event.register(KeyBinding.CYBERLEG_USE);
            event.register(KeyBinding.CYBER_IMPLANT_SCREEN_OPEN);
        }
    }
}

