package net.earlyalpha.aristysa.event;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.capability.PlayerCyberwareTier;
import net.earlyalpha.aristysa.capability.PlayerCyberwareTierProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aristysa.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerCyberwareTierProvider.PLAYER_CYBERWARE_TIER).isPresent()) {
                event.addCapability(new ResourceLocation(Aristysa.MOD_ID, "properties"), new PlayerCyberwareTierProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerCyberwareTier.class);
    }

}
