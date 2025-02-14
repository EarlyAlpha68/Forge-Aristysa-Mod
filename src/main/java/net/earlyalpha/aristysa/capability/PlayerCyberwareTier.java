package net.earlyalpha.aristysa.capability;


import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.nbt.CompoundTag;

public class PlayerCyberwareTier {
    private int cyberLegTier;
    private int enderEyeTier;
    private int golemArmTier;
    private int opticalCamoTier;
    private int subdermalArmorTier;
    private int wardenHeartTier;


    public int getTier(String key) {
        return switch (EarlyUtil.getImplantType(key)){
            case 0 -> cyberLegTier;
            case 1 -> enderEyeTier;
            case 2 -> golemArmTier;
            case 3 -> opticalCamoTier;
            case 4 -> subdermalArmorTier;
            case 5 -> wardenHeartTier;
            default -> 0;
        };
    }

    public void setTier(int tier, String key) {
      switch (EarlyUtil.getImplantType(key)){
          case 0 :
              this.cyberLegTier = tier;
              break;
          case 1 :
              this.enderEyeTier = tier;
              break;
          case 2 :
              this.golemArmTier = tier;
              break;
          case 3 :
              this.opticalCamoTier = tier;
              break;
          case 4 :
              this.subdermalArmorTier = tier;
              break;
          case 5 :
              this.wardenHeartTier = tier;
              break;
      }
    }



    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("cyberLegTier",cyberLegTier);
        nbt.putInt("enderEyeTier",enderEyeTier);
        nbt.putInt("golemArmTier",golemArmTier);
        nbt.putInt("opticalCamoTier",opticalCamoTier);
        nbt.putInt("subdermalArmorTier",subdermalArmorTier);
        nbt.putInt("wardenHeartTier",wardenHeartTier);
    }
    public void loadNBTData(CompoundTag nbt) {
        cyberLegTier = nbt.getInt("cyberLegTier");
        enderEyeTier = nbt.getInt("enderEyeTier");
        golemArmTier = nbt.getInt("golemArmTier");
        opticalCamoTier = nbt.getInt("opticalCamoTier");
        subdermalArmorTier = nbt.getInt("subdermalArmorTier");
        wardenHeartTier = nbt.getInt("wardenHeartTier");

    }
}
