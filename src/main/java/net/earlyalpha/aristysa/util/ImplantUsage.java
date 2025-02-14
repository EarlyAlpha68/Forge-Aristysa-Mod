package net.earlyalpha.aristysa.util;


import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;


public class ImplantUsage {
    public static void OpticalCamo(ServerPlayer player) {
      /*  if (!player.hasStatusEffect(ModEffects.OPTICAL_CAMO_COOLDOWN)) {
            int i = EarlyUtil.getImplantTier(player, "opticalCamoTier");
            switch (i) {
                case 1:
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 400, 0, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN, 2200, 0, false, false, true));
                    break;
                case 2:
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 800, 0, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN, 2000, 0, false, false, true));
                    break;
                case 3:
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 1200, 0, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN, 1800, 0, false, false, true));
                    break;
                default:
                    break;
            }
        }*/
    }

    public static void EnderEye(ServerPlayer player) {
        //if ((!player.hasStatusEffect(ModEffects.ENDER_EYE_COOLDOWN)) && ((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier") > 0) {
          //  if (player instanceof EnderEyeTp teleportation) {
          //      teleportation.triggerTeleport();
          //  }
       // }
    }

    public static void CyberLeg(ServerPlayer player) {
        int i = EarlyUtil.getImplantTier(player, "cyberLegTier");
        if (/*!player.hasStatusEffect(ModEffects.CYBERLEG_COOLDOWN)*/true) {
            switch (i) {
                case 1:
                    Dash(player);
                   // player.addStatusEffect(new StatusEffectInstance(ModEffects.CYBERLEG_COOLDOWN, 300, 0, false, false, true));
                    break;
                case 2:
                    Dash(player);
                   // player.addStatusEffect(new StatusEffectInstance(ModEffects.CYBERLEG_COOLDOWN, 200, 0, false, false, true));
                    break;
                case 3:
                    Dash(player);
                  //  player.addStatusEffect(new StatusEffectInstance(ModEffects.CYBERLEG_COOLDOWN, 100, 0, false, false, true));
                    break;
                default:
                    break;
            }
        }
    }

    private static void Dash (ServerPlayer player){
        Vec3 lookVec = player.getLookAngle();
        Vec3 dashVec = lookVec.scale(2);
        player.setDeltaMovement(dashVec.x, dashVec.y, dashVec.z);
        player.hurtMarked = true;
        player.connection.send(new ClientboundSetEntityMotionPacket(player));

    }

}
