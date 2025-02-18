package net.earlyalpha.aristysa.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.networking.ModMessages;
import net.earlyalpha.aristysa.networking.packet.*;
import net.earlyalpha.aristysa.screen.CyberwareGuiMenuType;
import net.earlyalpha.aristysa.screen.CyberwareGuiScreen;
import net.earlyalpha.aristysa.screen.ModMenuTypes;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
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
            if (KeyBinding.OPTICAL_CAMO_USE.consumeClick() && KeyBinding.OPTICAL_CAMO_USE.getKey().getType() != InputConstants.Type.MOUSE) {
                ModMessages.sendToServer(new OpticalCamoC2SPacket());
            }
            if (KeyBinding.ENDER_EYE_USE.consumeClick() && KeyBinding.ENDER_EYE_USE.getKey().getType() != InputConstants.Type.MOUSE) {
                ModMessages.sendToServer(new EnderEyeC2SPacket());
            }
            if (KeyBinding.CYBERLEG_USE.consumeClick() && KeyBinding.CYBERLEG_USE.getKey().getType() != InputConstants.Type.MOUSE) {
                ModMessages.sendToServer(new CyberLegC2SPacket());
            }
            if (KeyBinding.CYBER_IMPLANT_SCREEN_OPEN.consumeClick() && KeyBinding.CYBER_IMPLANT_SCREEN_OPEN.getKey().getType() != InputConstants.Type.MOUSE) {
                ModMessages.sendToServer(new CyberwareScreenC2SPacket());
            }
        }
        @SubscribeEvent
        public static void onMouseInput(InputEvent.MouseButton event) {
            if (KeyBinding.OPTICAL_CAMO_USE.consumeClick() && KeyBinding.OPTICAL_CAMO_USE.getKey().getType() != InputConstants.Type.KEYSYM) {
                ModMessages.sendToServer(new OpticalCamoC2SPacket());
            }
            if (KeyBinding.ENDER_EYE_USE.consumeClick() && KeyBinding.ENDER_EYE_USE.getKey().getType() != InputConstants.Type.KEYSYM) {
                ModMessages.sendToServer(new EnderEyeC2SPacket());
            }
            if (KeyBinding.CYBERLEG_USE.consumeClick() && KeyBinding.CYBERLEG_USE.getKey().getType() != InputConstants.Type.KEYSYM) {
                ModMessages.sendToServer(new CyberLegC2SPacket());
            }
            if (KeyBinding.CYBER_IMPLANT_SCREEN_OPEN.consumeClick() && KeyBinding.CYBER_IMPLANT_SCREEN_OPEN.getKey().getType() != InputConstants.Type.KEYSYM) {
                ModMessages.sendToServer(new CyberwareScreenC2SPacket());
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

