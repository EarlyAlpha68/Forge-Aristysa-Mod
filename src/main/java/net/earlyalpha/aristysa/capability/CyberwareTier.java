package net.earlyalpha.aristysa.capability;


import net.minecraft.nbt.CompoundTag;

public class CyberwareTier {
    private int tier;
    private String key;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("test",tier);
    }
    public void loadNBTData(CompoundTag nbt) {
        tier = nbt.getInt("test");
    }
}
