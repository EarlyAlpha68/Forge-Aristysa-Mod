package net.earlyalpha.aristysa.util;


import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
    public static void register() {
        ItemProperties.register(ModItems.CRIMSON_LACE.get(), new ResourceLocation("aristysa", "usage_ticks"),
                (stack, world, entity, seed) -> {
                    CompoundTag nbt = stack.getTag();
                    if (nbt != null) {
                        int usageTicks = nbt.getInt("UsageTicks");
                        if (usageTicks >= 30) {
                            return 1.0F;
                        } else if (usageTicks >= 23) {
                            return 0.75F;
                        } else if (usageTicks >= 15) {
                            return 0.5F;
                        } else if (usageTicks >= 5) {
                            return 0.25F;
                        } else {
                            return 0.0F;
                        }
                    }
                    return 0.0F;
                }
        );
        ItemProperties.register(ModItems.PHANTOM_ELIXIR.get(), new ResourceLocation("aristysa", "usage_ticks"),
                (stack, world, entity, seed) -> {
                    CompoundTag nbt = stack.getTag();
                    if (nbt != null) {
                        int usageTicks = nbt.getInt("UsageTicks");
                        if (usageTicks >= 30) {
                            return 1.0F;
                        } else if (usageTicks >= 23) {
                            return 0.75F;
                        } else if (usageTicks >= 15) {
                            return 0.5F;
                        } else if (usageTicks >= 5) {
                            return 0.25F;
                        } else {
                            return 0.0F;
                        }
                    }
                    return 0.0F;
                }
        );
        ItemProperties.register(ModItems.SHADOW_HASTE.get(), new ResourceLocation("aristysa", "usage_ticks"),
                (stack, world, entity, seed) -> {
                    CompoundTag nbt = stack.getTag();
                    if (nbt != null) {
                        int usageTicks = nbt.getInt("UsageTicks");
                        if (usageTicks >= 30) {
                            return 1.0F;
                        } else if (usageTicks >= 23) {
                            return 0.75F;
                        } else if (usageTicks >= 15) {
                            return 0.5F;
                        } else if (usageTicks >= 5) {
                            return 0.25F;
                        } else {
                            return 0.0F;
                        }
                    }
                    return 0.0F;
                }
        );
    }
}
