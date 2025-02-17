package net.earlyalpha.aristysa.effect;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS , Aristysa.MOD_ID);

    public static final RegistryObject<MobEffect> CYBERLEG_COOLDOWN = MOB_EFFECT.register("cyberleg_cooldown",
            () -> new CyberwareCooldown(MobEffectCategory.NEUTRAL,200200200));
    public static final RegistryObject<MobEffect> ENDER_EYE_COOLDOWN = MOB_EFFECT.register("ender_eye_cooldown",
            () -> new CyberwareCooldown(MobEffectCategory.NEUTRAL,200200200));
    public static final RegistryObject<MobEffect> WARDEN_HEART_COOLDOWN = MOB_EFFECT.register("warden_heart_cooldown",
            () -> new CyberwareCooldown(MobEffectCategory.NEUTRAL,200200200));
    public static final RegistryObject<MobEffect> OPTICAL_CAMO_COOLDOWN = MOB_EFFECT.register("optical_camo_cooldown",
            () -> new CyberwareCooldown(MobEffectCategory.NEUTRAL,200200200));
    public static final RegistryObject<MobEffect> CRIMSON_WOUND = MOB_EFFECT.register("crimson_wound",
            () -> new CrimsonWound(MobEffectCategory.HARMFUL,0x8B0000));



    public static void register(IEventBus eventBus) {
        MOB_EFFECT.register(eventBus);
    }
}
