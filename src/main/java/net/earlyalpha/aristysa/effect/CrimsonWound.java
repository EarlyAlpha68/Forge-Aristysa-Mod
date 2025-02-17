package net.earlyalpha.aristysa.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimsonWound extends MobEffect {
    protected CrimsonWound(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    public static final UUID HALF_MAX_HEALTH_UUID = UUID.fromString("cc9880e9-7b53-4e45-8c2e-7d292f717c30");

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity entity, int pAmplifier, double pHealth) {
        if (entity.getHealth() > entity.getMaxHealth() / 2) {
            entity.setHealth(entity.getMaxHealth() / 2);
        } else {
            entity.setHealth(entity.getHealth());
        }
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        {
            AttributeInstance attributeInstance = entity.getAttribute(Attributes.MAX_HEALTH);
            if (attributeInstance != null) {
                AttributeModifier halfMaxHealthModifier = new AttributeModifier(HALF_MAX_HEALTH_UUID, "Half max health", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL);
                if (!attributeInstance.hasModifier(halfMaxHealthModifier)) {
                    attributeInstance.addTransientModifier(halfMaxHealthModifier);
                }
            }
            super.applyEffectTick(entity,pAmplifier);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap pAttributeMap, int pAmplifier) {
        AttributeInstance attributeInstance = entity.getAttribute(Attributes.MAX_HEALTH);
        if (attributeInstance != null) {
            attributeInstance.removeModifier(HALF_MAX_HEALTH_UUID);
        }
        super.removeAttributeModifiers(entity, pAttributeMap, pAmplifier);
    }
    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        return ret;
    }
}
