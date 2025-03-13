package net.earlyalpha.aristysa.event;

import net.earlyalpha.aristysa.Aristysa;

import net.earlyalpha.aristysa.capability.PlayerCyberwareTier;
import net.earlyalpha.aristysa.capability.PlayerCyberwareTierProvider;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Aristysa.MOD_ID)
public class ModEvents {
    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("55fc0c0b-12d6-4a0f-8b8c-4a3cddae36c5");
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("fa233e1c-4180-4865-b01b-bc45191b7e5d");
    private static final UUID ATTACK_KNOCKBACK_MODIFIER_ID = UUID.fromString("5f5d9b3f-83ed-4d5a-9f79-0cc4727b95ff");

    @SubscribeEvent
    public static void onMobEffectRemove(MobEffectEvent.Remove event) {
        if (!(event.getEntity().level().isClientSide())) {
            if (event.getEntity() instanceof ServerPlayer) {
                if (shouldNotClear(event.getEffect())) {
                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean shouldNotClear(MobEffect effect) {
        return effect == ModEffects.WARDEN_HEART_COOLDOWN.get();
    }


    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerCyberwareTierProvider.PLAYER_CYBERWARE_TIER).isPresent()) {
                event.addCapability(new ResourceLocation(Aristysa.MOD_ID, "properties"), new PlayerCyberwareTierProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerCyberwareTier.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            ServerPlayer player = (ServerPlayer) event.player;
            int subdermalArmorTier = EarlyUtil.getImplantTier(player,"subdermalArmorTier");
            int golemArmTier = EarlyUtil.getImplantTier(player,"golemArmTier");
            if (subdermalArmorTier > 0) {
                double aV = EarlyUtil.getArmorValueModifier(subdermalArmorTier);
                AttributeInstance armorAttribute = player.getAttribute(Attributes.ARMOR);
                if (armorAttribute != null) {
                    armorAttribute.setBaseValue(aV);
                }
            }
            if (golemArmTier > 0) {
                double KnockbackValue = EarlyUtil.getGolemArmModifierValue(golemArmTier,"knockback");
                double AttackValue = EarlyUtil.getGolemArmModifierValue(golemArmTier,"attack");
                double SpeedValue = EarlyUtil.getGolemArmModifierValue(golemArmTier,"speed");
                EarlyUtil.applyModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER_ID, AttackValue);
                EarlyUtil.applyModifier(player, Attributes.ATTACK_KNOCKBACK, ATTACK_KNOCKBACK_MODIFIER_ID, KnockbackValue);
                EarlyUtil.applyModifier(player, Attributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER_ID, SpeedValue);
            }

        }
    }
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            int tier = EarlyUtil.getImplantTier(player,"wardenHeartTier");

            if (tier > 0  && !player.hasEffect(ModEffects.WARDEN_HEART_COOLDOWN.get())) {
                event.setCanceled(true);
                EarlyUtil.wardenHeartEffect(player,tier,event);
            }
        }
    }

    @SubscribeEvent
        public static void onEntityDeadBody(LivingDropsEvent event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            switch (EarlyUtil.getImplantTier(player,"opticalCamoTier")) {
                case 1:
                    player.drop(ModItems.OPTICAL_CAMO_1.get().getDefaultInstance(),false);
                    break;
                case 2:
                    player.drop(ModItems.OPTICAL_CAMO_2.get().getDefaultInstance(),false);
                    break;
                case 3:
                    player.drop(ModItems.OPTICAL_CAMO_3.get().getDefaultInstance(),false);
                    break;
                default:
                    break;
            }
            switch (EarlyUtil.getImplantTier(player,"golemArmTier")) {
                case 1:
                    player.drop(ModItems.GOLEMARM_1.get().getDefaultInstance(),false);
                    break;
                case 2:
                    player.drop(ModItems.GOLEMARM_2.get().getDefaultInstance(),false);
                    break;
                case 3:
                    player.drop(ModItems.GOLEMARM_3.get().getDefaultInstance(),false);
                    break;
                default:
                    break;
            }
            switch (EarlyUtil.getImplantTier(player,"enderEyeTier")) {
                case 1:
                    player.drop(ModItems.ENDEREYE_1.get().getDefaultInstance(),false);
                    break;
                case 2:
                    player.drop(ModItems.ENDEREYE_2.get().getDefaultInstance(),false);
                    break;
                case 3:
                    player.drop(ModItems.ENDEREYE_3.get().getDefaultInstance(),false);
                    break;
                default:
                    break;
            }
            switch (EarlyUtil.getImplantTier(player,"subdermalArmorTier")) {
                case 1:
                    player.drop(ModItems.SUBDERMAL_ARMOR_1.get().getDefaultInstance(),false);
                    break;
                case 2:
                    player.drop(ModItems.SUBDERMAL_ARMOR_2.get().getDefaultInstance(),false);
                    break;
                case 3:
                    player.drop(ModItems.SUBDERMAL_ARMOR_3.get().getDefaultInstance(),false);
                    break;
                default:
                    break;
            }
            switch (EarlyUtil.getImplantTier(player,"wardenHeartTier")) {
                case 1:
                    player.drop(ModItems.WARDEN_HEART_1.get().getDefaultInstance(),false);
                    break;
                case 2:
                    player.drop(ModItems.WARDEN_HEART_2.get().getDefaultInstance(),false);
                    break;
                case 3:
                    player.drop(ModItems.WARDEN_HEART_3.get().getDefaultInstance(),false);
                    break;
                default:
                    break;
            }
            switch (EarlyUtil.getImplantTier(player,"cyberLegTier")) {
                case 1:
                    player.drop(ModItems.CYBERLEG_1.get().getDefaultInstance(),false);
                    break;
                case 2:
                    player.drop(ModItems.CYBERLEG_2.get().getDefaultInstance(),false);
                    break;
                case 3:
                    player.drop(ModItems.CYBERLEG_3.get().getDefaultInstance(),false);
                    break;
                default:
                    break;
            }
        }
    }



}
