package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.capability.PlayerCyberwareTierProvider;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.List;
import java.util.HashMap;
import java.util.UUID;
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
    public static void SyringeInject(LivingEntity player, String key) {
        //Utility for the effect of the custom SyringeItem class
        switch (getSyringeType(key)) {
            case 0 :
                player.addEffect(new MobEffectInstance(ModEffects.CRIMSON_WOUND.get(), 12000, 0, true, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 3, true, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 2, true, true, true));
                break;
            case 1 :
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 6, true, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 6000, 4, true, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 6000, 4, true, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 6000, 2, true, true, true));
                break;
            case 2 :
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 6000, 3, true, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 6000, 1, true, true, true));
            default:
                break;
        }

    }
    public static CompoundTag playerNbtSyncProvider(ServerPlayer serverPlayer) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("cyberLegTier", EarlyUtil.getImplantTier(serverPlayer,"cyberLegTier"));
        nbt.putInt("enderEyeTier",EarlyUtil.getImplantTier(serverPlayer,"enderEyeTier"));
        nbt.putInt("golemArmTier",EarlyUtil.getImplantTier(serverPlayer,"golemArmTier"));
        nbt.putInt("opticalCamoTier",EarlyUtil.getImplantTier(serverPlayer,"opticalCamoTier"));
        nbt.putInt("subdermalArmorTier",EarlyUtil.getImplantTier(serverPlayer,"subdermalArmorTier"));
        nbt.putInt("wardenHeartTier",EarlyUtil.getImplantTier(serverPlayer,"wardenHeartTier"));
        return nbt;
    }
    public static void wardenHeartEffect(ServerPlayer player , int tier, LivingDeathEvent event) {
        int duration = getWardenHeartDuration(tier);
        player.setHealth(1.0F);
        event.setCanceled(true);
        player.removeAllEffects();
        player.addEffect(new MobEffectInstance(ModEffects.WARDEN_HEART_COOLDOWN.get(),duration,0,false,false,true));
        player.setHealth(player.getMaxHealth());
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.NEUTRAL, 1.0f, 1.0f);
        applyEffectToNearbyEntities(player,tier*3);
    }

    public static int getWardenHeartDuration(int tier) {
        return switch(tier){
            case 1 ->18000;
            case 2 ->12000;
            case 3 ->6000;
            default -> 0;
        };
    }
    public static void applyEffectToNearbyEntities(Player player,int radius) {
        List<Entity> nearbyEntities = player.level().getEntities(player, player.getBoundingBox().inflate(radius));

        for (Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.DARKNESS, 600, 1));
            }
        }
    }
    public static void applyModifier(ServerPlayer player, Attribute attribute, UUID modifierId, double value) {
        AttributeInstance attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null) {
            AttributeModifier existingModifier = attributeInstance.getModifier(modifierId);
            if (existingModifier != null) {
                attributeInstance.removeModifier(modifierId);
            }
            attributeInstance.addPermanentModifier(new AttributeModifier(modifierId, "Attribute Modifier", value, AttributeModifier.Operation.ADDITION));
        }
    }
    public static double getGolemArmModifierValue(int tier, String key) {
        return switch (key) {
            case "knockbach" -> switch (tier) {
                case 1 -> 3.0;
                case 2 -> 4.0;
                case 3 -> 5.0;
                default -> 0.0;
            };
            case "attack" -> switch (tier) {
                case 1 -> 3.0;
                case 2 -> 5.0;
                case 3 -> 7.0;
                default -> 2.0;
            };
            case "speed" -> switch (tier) {
                case 1, 2, 3 -> 2.0;
                default -> 4.0;
            };
            default -> 0;
        };
    }
    public static double getArmorValueModifier(int tier) {
        return switch (tier) {
            case 1 -> 5.0;
            case 2 -> 7.0;
            case 3 -> 10.0;
            default -> 0.0;
        };
    }
    public static void Dash (ServerPlayer player){
        Vec3 lookVec = player.getLookAngle();
        Vec3 dashVec = lookVec.scale(1.15F);
        player.setDeltaMovement(dashVec.x, dashVec.y, dashVec.z);
        player.setSprinting(true);
        player.connection.send(new ClientboundSetEntityMotionPacket(player));

    }

    public static BlockHitResult getHitBlock(Player player, int currentTeleportDistance ) {
        //Utility for the enderEye(calculation of the block to teleport to)
        Level world = player.level();
        Vec3 startVec = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 endVec = startVec.add(lookVec.x * currentTeleportDistance, lookVec.y * currentTeleportDistance, lookVec.z * currentTeleportDistance);

        ClipContext hitResult =new ClipContext(
                startVec,
                endVec,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
        );
        return world.clip(hitResult);
    }
    public static int getCurrentTeleportDistance(Player player) {
        return switch (getImplantTier(player, "enderEyeTier")) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            default -> 0;
        };
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
    public static void SyringeItemToolTip(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag context, int type) {
        //Utility for the tooltip of the custom SyringeItem class
        switch (type) {
            case 0:
                if (Screen.hasShiftDown()) {
                    tooltip.add(Component.translatable("tooltip.aristysa.crimsonlace_shift0"));
                    tooltip.add(Component.translatable("tooltip.aristysa.crimsonlace_shift1"));
                    tooltip.add(Component.translatable("tooltip.aristysa.crimsonlace_shift2"));
                } else {
                    tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            case 1:
                if (Screen.hasShiftDown()) {
                    tooltip.add(Component.translatable("tooltip.aristysa.phantom_elixir_shift0"));
                    tooltip.add(Component.translatable("tooltip.aristysa.phantom_elixir_shift1"));
                } else {
                    tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            case 2:
                if (Screen.hasShiftDown()) {
                    tooltip.add(Component.translatable("tooltip.aristysa.shadow_haste_shift0"));
                    tooltip.add(Component.translatable("tooltip.aristysa.shadow_haste_shift1"));
                } else {
                    tooltip.add(Component.translatable("tooltip.aristysa.hold_shift"));
                }
            default:
                break;
        }
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


