package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.screen.CyberwareGuiMenuType;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.ImplantUsage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnderEyeC2SPacket {
    public EnderEyeC2SPacket() {

    }
    public EnderEyeC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ServerPlayer serverPlayer = context.getSender();
            ImplantUsage.teleportEnderEyePlayer(serverPlayer);
        });
        return true;
    }
}
