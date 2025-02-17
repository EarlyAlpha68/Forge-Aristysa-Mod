package net.earlyalpha.aristysa.networking;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Aristysa.MOD_ID,"messages"))
                .networkProtocolVersion(()-> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(CyberLegC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CyberLegC2SPacket::new)
                .encoder(CyberLegC2SPacket::toBytes)
                .consumerMainThread(CyberLegC2SPacket::handle)
                .add();

        net.messageBuilder(OpticalCamoC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpticalCamoC2SPacket::new)
                .encoder(OpticalCamoC2SPacket::toBytes)
                .consumerMainThread(OpticalCamoC2SPacket::handle)
                .add();

        net.messageBuilder(EnderEyeC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(EnderEyeC2SPacket::new)
                .encoder(EnderEyeC2SPacket::toBytes)
                .consumerMainThread(EnderEyeC2SPacket::handle)
                .add();

        net.messageBuilder(CyberwareSyncCallC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CyberwareSyncCallC2SPacket::new)
                .encoder(CyberwareSyncCallC2SPacket::toBytes)
                .consumerMainThread(CyberwareSyncCallC2SPacket::handle)
                .add();


        net.messageBuilder(Cyberware_SyncS2CPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(Cyberware_SyncS2CPacket::new)
                .encoder(Cyberware_SyncS2CPacket::toBytes)
                .consumerMainThread(Cyberware_SyncS2CPacket::handle)
                .add();
    }


    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()-> player), message);
    }


}
