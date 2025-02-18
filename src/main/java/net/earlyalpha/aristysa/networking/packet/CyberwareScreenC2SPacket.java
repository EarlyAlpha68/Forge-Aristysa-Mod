package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.networking.ModMessages;
import net.earlyalpha.aristysa.screen.CyberwareGuiMenuType;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CyberwareScreenC2SPacket {
    public CyberwareScreenC2SPacket() {

    }
    public CyberwareScreenC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ServerPlayer serverPlayer = context.getSender();
            CompoundTag nbt = EarlyUtil.playerNbtSyncProvider(serverPlayer);
            ModMessages.sendToPlayer(new Cyberware_SyncS2CPacket(nbt),serverPlayer);
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, player) -> new CyberwareGuiMenuType(containerId, playerInventory),
                    Component.nullToEmpty("")));
        });
        return true;
    }


}
