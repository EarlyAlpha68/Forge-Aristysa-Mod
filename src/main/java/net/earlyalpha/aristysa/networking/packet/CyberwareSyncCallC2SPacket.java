package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.networking.ModMessages;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.ImplantUsage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CyberwareSyncCallC2SPacket {
    public CyberwareSyncCallC2SPacket() {

    }
    public CyberwareSyncCallC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ServerPlayer player = context.getSender();
            CompoundTag nbt = new CompoundTag();
            nbt.putInt("cyberLegTier", EarlyUtil.getImplantTier(player,"cyberLegTier"));
            nbt.putInt("enderEyeTier",EarlyUtil.getImplantTier(player,"enderEyeTier"));
            nbt.putInt("golemArmTier",EarlyUtil.getImplantTier(player,"golemArmTier"));
            nbt.putInt("opticalCamoTier",EarlyUtil.getImplantTier(player,"opticalCamoTier"));
            nbt.putInt("subdermalArmorTier",EarlyUtil.getImplantTier(player,"subdermalArmorTier"));
            nbt.putInt("wardenHeartTier",EarlyUtil.getImplantTier(player,"wardenHeartTier"));
            ModMessages.sendToPlayer(new Cyberware_SyncS2CPacket(nbt),player);
        });
        return true;
    }
}
