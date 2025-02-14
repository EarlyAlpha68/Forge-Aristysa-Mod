package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.capability.PlayerCyberwareTier;
import net.earlyalpha.aristysa.capability.PlayerCyberwareTierProvider;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

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
        implantName.put("cyberLegTier", "Cyber Leg");
        implantName.put("enderEyeTier", "Ender Eye");
        implantName.put("golemArmTier", "Golem Arm");
        implantName.put("opticalCamoTier", "Optical Camo");
        implantName.put("subdermalArmorTier", "SubDermal Armor");
        implantName.put("wardenHeartTier", "Warden Heart");
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

    public static ItemStack CyberwareItemstack(int implantType, int implantTier) {
        //When call give you the ItemStack of the Implant
        return switch (implantType) {
            case 0 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.CYBERLEG_1.get());
                case 2 -> new ItemStack(ModItems.CYBERLEG_2.get());
                case 3 -> new ItemStack(ModItems.CYBERLEG_3.get());
                default -> ItemStack.EMPTY;
            };
            case 1 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.ENDEREYE_1.get());
                case 2 -> new ItemStack(ModItems.ENDEREYE_2.get());
                case 3 -> new ItemStack(ModItems.ENDEREYE_3.get());
                default -> ItemStack.EMPTY;
            };
            /*
            case 2 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.GOLEMARM_1);
                case 2 -> new ItemStack(ModItems.GOLEMARM_2);
                case 3 -> new ItemStack(ModItems.GOLEMARM_3);
                default -> ItemStack.EMPTY;
            };
            case 3 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.OPTICAL_CAMO_1);
                case 2 -> new ItemStack(ModItems.OPTICAL_CAMO_2);
                case 3 -> new ItemStack(ModItems.OPTICAL_CAMO_3);
                default -> ItemStack.EMPTY;
            };
            case 4 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_1);
                case 2 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_2);
                case 3 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_3);
                default -> ItemStack.EMPTY;
            };
            case 5 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.WARDEN_HEART_1);
                case 2 -> new ItemStack(ModItems.WARDEN_HEART_2);
                case 3 -> new ItemStack(ModItems.WARDEN_HEART_3);
                default -> ItemStack.EMPTY;
            };*/
            default -> ItemStack.EMPTY;
        };



    }
}

