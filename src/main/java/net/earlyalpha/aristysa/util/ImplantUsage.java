package net.earlyalpha.aristysa.util;


import net.earlyalpha.aristysa.effect.ModEffects;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;



public class ImplantUsage {
    public static void OpticalCamo(ServerPlayer player) {
        if (!player.hasEffect(ModEffects.OPTICAL_CAMO_COOLDOWN.get())) {
            int i = EarlyUtil.getImplantTier(player, "opticalCamoTier");
            switch (i) {
                case 1:
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 400, 0, false, false, true));
                    player.addEffect(new MobEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN.get(), 2200, 0, false, false, true));
                    break;
                case 2:
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 800, 0, false, false, true));
                    player.addEffect(new MobEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN.get(), 2000, 0, false, false, true));
                    break;
                case 3:
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1200, 0, false, false, true));
                    player.addEffect(new MobEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN.get(), 1800, 0, false, false, true));
                    break;
                default:
                    break;
            }
        }
    }

    public static void teleportEnderEyePlayer(Player player) {
        if (EarlyUtil.getImplantTier(player,"enderEyeTier") > 0) {
            int currentTeleportDistance = EarlyUtil.getCurrentTeleportDistance(player);
            BlockHitResult hitResult = EarlyUtil.getHitBlock(player, currentTeleportDistance);
            if (hitResult.getType() == HitResult.Type.BLOCK && (!player.hasEffect(ModEffects.ENDER_EYE_COOLDOWN.get()))) {
                Vec3 hitPos = hitResult.getLocation();
                player.teleportTo(hitPos.get(Direction.Axis.X), hitPos.get(Direction.Axis.Y) + 1, hitPos.get(Direction.Axis.Z));
                switch (EarlyUtil.getImplantTier(player, "enderEyeTier")) {
                    case 1:
                        player.addEffect(new MobEffectInstance(ModEffects.ENDER_EYE_COOLDOWN.get(), 1800, 0, false, false, true));
                        break;
                    case 2:
                        player.addEffect(new MobEffectInstance(ModEffects.ENDER_EYE_COOLDOWN.get(), 1200, 0, false, false, true));
                        break;
                    case 3:
                        player.addEffect(new MobEffectInstance(ModEffects.ENDER_EYE_COOLDOWN.get(), 600, 0, false, false, true));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void CyberLeg(ServerPlayer player) {
        int i = EarlyUtil.getImplantTier(player, "cyberLegTier");
        if (!player.hasEffect(ModEffects.CYBERLEG_COOLDOWN.get())) {
            switch (i) {
                case 1:
                    EarlyUtil.Dash(player);
                    player.addEffect(new MobEffectInstance(ModEffects.CYBERLEG_COOLDOWN.get(), 300, 0, false, false, true));
                    break;
                case 2:
                    EarlyUtil.Dash(player);
                    player.addEffect(new MobEffectInstance(ModEffects.CYBERLEG_COOLDOWN.get(), 200, 0, false, false, true));
                    break;
                case 3:
                    EarlyUtil.Dash(player);
                    player.addEffect(new MobEffectInstance(ModEffects.CYBERLEG_COOLDOWN.get(), 100, 0, false, false, true));
                    break;
                default:
                    break;
            }
        }
    }



}
