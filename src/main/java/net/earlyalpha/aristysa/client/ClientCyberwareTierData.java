package net.earlyalpha.aristysa.client;

import net.minecraft.nbt.CompoundTag;

public class ClientCyberwareTierData {
    private static CompoundTag playerCyberware;

    public static void set(CompoundTag cyberware) {
        ClientCyberwareTierData.playerCyberware = cyberware;
    }

    public static CompoundTag getPlayerCyberware() {
        return playerCyberware;
    }
}
