package net.earlyalpha.aristysa.capability;


import net.minecraft.nbt.CompoundTag;

public class PlayerCyberwareTier {
    private int tier;
    private int tier2;
    private String key;

    public int getTier() {
        return tier;
    }

    public int getTier2() {
        return tier2;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
    public void setTier2(int tier) {
        this.tier2 = tier;
    }



    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("test",tier);
        nbt.putInt("test2",tier2);
    }
    public void loadNBTData(CompoundTag nbt) {
        tier = nbt.getInt("test");
        tier2 = nbt.getInt("test2");
    }
}
