package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.client.ClientCyberwareTierData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class Cyberware_SyncS2CPacket {
    private final CompoundTag cyberware;

    public Cyberware_SyncS2CPacket(CompoundTag cyberware) {
        this.cyberware = cyberware;
    }
    public Cyberware_SyncS2CPacket(FriendlyByteBuf buf) {
        this.cyberware = buf.readNbt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(cyberware);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ClientCyberwareTierData.set(cyberware);
        });
        return true;
    }
}

