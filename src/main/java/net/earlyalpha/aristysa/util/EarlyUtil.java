package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.capability.PlayerCyberwareTier;
import net.earlyalpha.aristysa.capability.PlayerCyberwareTierProvider;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
    public static int getImplantTier(Player player,String key) {
        // Get the implant tier from the player capability
        AtomicInteger tierHolder = new AtomicInteger();
        player.getCapability(PlayerCyberwareTierProvider.PLAYER_CYBERWARE_TIER).ifPresent(playerCyberwareTier -> {
            tierHolder.set(playerCyberwareTier.getTier(key));
        });
        return tierHolder.get();
    }
    public static void setImplantTier(Player player,String key,int tier) {
        player.getCapability(PlayerCyberwareTierProvider.PLAYER_CYBERWARE_TIER).ifPresent(playerCyberwareTier -> {
            playerCyberwareTier.setTier(tier,key);
        });
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

            case 2 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.GOLEMARM_1.get());
                case 2 -> new ItemStack(ModItems.GOLEMARM_2.get());
                case 3 -> new ItemStack(ModItems.GOLEMARM_3.get());
                default -> ItemStack.EMPTY;
            };
            case 3 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.OPTICAL_CAMO_1.get());
                case 2 -> new ItemStack(ModItems.OPTICAL_CAMO_2.get());
                case 3 -> new ItemStack(ModItems.OPTICAL_CAMO_3.get());
                default -> ItemStack.EMPTY;
            };
            case 4 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_1.get());
                case 2 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_2.get());
                case 3 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_3.get());
                default -> ItemStack.EMPTY;
            };
            case 5 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.WARDEN_HEART_1.get());
                case 2 -> new ItemStack(ModItems.WARDEN_HEART_2.get());
                case 3 -> new ItemStack(ModItems.WARDEN_HEART_3.get());
                default -> ItemStack.EMPTY;
            };
            default -> ItemStack.EMPTY;
        };
    }
        public static void cyberwareItemToolTip(List<Component> tooltip, int tier, int type) {
            //Utility for the tooltip of the custom CyberwareItem class
            switch (type) {
                case 0 :
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()) {
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.cyberleg_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.cyberleg1_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()) {
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.cyberleg_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.cyberleg2_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()) {
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Component.translatable("tooltip.aristysa.cyberleg_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.cyberleg3_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 1 :
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.endereye1_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.endereye1_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.endereye2_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.endereye2_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Component.translatable("tooltip.aristysa.endereye3_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.endereye3_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.golemarm_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.golemarm1_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.golemarm_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.golemarm2_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Component.translatable("tooltip.aristysa.golemarm_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.golemarm3_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.optical_camo1_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.optical_camo1_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.optical_camo2_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.optical_camo2_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Component.translatable("tooltip.aristysa.optical_camo3_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.optical_camo3_shift2"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.subdermalarmor1_shift1"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.subdermalarmor2_shift1"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Component.translatable("tooltip.aristysa.subdermalarmor3_shift1"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart1_shift2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart1_shift3"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart2_shift2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart2_shift3"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Component.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart_shift1"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart3_shift2"));
                                tooltip.add(Component.translatable("tooltip.aristysa.wardenheart3_shift3"));

                            } else {
                                tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
            }
        }


    }


