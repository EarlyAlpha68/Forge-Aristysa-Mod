package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.networking.ModMessages;
import net.earlyalpha.aristysa.screen.CyberwareGuiMenuType;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.ImplantUsage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
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
            CompoundTag nbt = new CompoundTag();
            nbt.putInt("cyberLegTier", EarlyUtil.getImplantTier(serverPlayer,"cyberLegTier"));
            nbt.putInt("enderEyeTier",EarlyUtil.getImplantTier(serverPlayer,"enderEyeTier"));
            nbt.putInt("golemArmTier",EarlyUtil.getImplantTier(serverPlayer,"golemArmTier"));
            nbt.putInt("opticalCamoTier",EarlyUtil.getImplantTier(serverPlayer,"opticalCamoTier"));
            nbt.putInt("subdermalArmorTier",EarlyUtil.getImplantTier(serverPlayer,"subdermalArmorTier"));
            nbt.putInt("wardenHeartTier",EarlyUtil.getImplantTier(serverPlayer,"wardenHeartTier"));
            ModMessages.sendToPlayer(new Cyberware_SyncS2CPacket(nbt),serverPlayer);
           // ImplantUsage.teleportEnderEyePlayer(serverPlayer);
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, player) -> new CyberwareGuiMenuType(containerId, playerInventory),
                    Component.translatable("menu.title.aristysa.title")));
        });
        return true;
    }
}
