package net.earlyalpha.aristysa.util;

import java.util.HashMap;

public class EarlyUtil {
    public static HashMap<String, Integer> implantType = new HashMap<>();
    public static HashMap<String, String> implantName = new HashMap<>();
    public static HashMap<String, Integer> syringeType = new HashMap<>();
    static {
        //Init the key to sort the custom item
        syringeType.put("crimsonLace", 0);
        syringeType.put("phantomElixir", 1);
        syringeType.put("shadowHaste", 2);
        //
        implantType.put("cyberLegTier", 0);
        implantType.put("enderEyeTier", 1);
        implantType.put("golemArmTier", 2);
        implantType.put("opticalCamoTier", 3);
        implantType.put("subdermalArmorTier", 4);
        implantType.put("wardenHeartTier", 5);
        //Init the implants name in their specific order
        implantName.put("cyberLegTier","Cyber Leg");
        implantName.put("enderEyeTier","Ender Eye");
        implantName.put("golemArmTier","Golem Arm");
        implantName.put("opticalCamoTier","Optical Camo");
        implantName.put("subdermalArmorTier","SubDermal Armor");
        implantName.put("wardenHeartTier","Warden Heart");
    }
    public static int getImplantType(String key) {
        return implantType.getOrDefault(key, -1);
        //give the attached number of order of a specific implant
    }

    public static String getImplantName(String key) {
        return implantName.getOrDefault(key, "");
        //give the attached number of order of a specific implant
    }
    public static int getSyringeType(String key) {
        return syringeType.getOrDefault(key, -1);
        //give the attached number of order of a specific implant
    }
}
