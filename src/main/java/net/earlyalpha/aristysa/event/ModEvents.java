package net.earlyalpha.aristysa.event;

import com.google.common.eventbus.DeadEvent;
import net.earlyalpha.aristysa.Aristysa;

import net.earlyalpha.aristysa.capability.PlayerCyberwareTier;
import net.earlyalpha.aristysa.capability.PlayerCyberwareTierProvider;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.EntityTickList;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
                EarlyUtil.wardenHeartEffect(player,tier,event);
            }
        }

    }
}
