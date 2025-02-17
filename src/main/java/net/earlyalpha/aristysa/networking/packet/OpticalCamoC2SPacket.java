package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.ImplantUsage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpticalCamoC2SPacket {
    public OpticalCamoC2SPacket() {

    }
    public OpticalCamoC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ServerPlayer player = context.getSender();
            ImplantUsage.OpticalCamo(player);
        });
        return true;
    }
}
