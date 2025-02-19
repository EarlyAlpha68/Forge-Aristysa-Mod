package net.earlyalpha.aristysa.recipe;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Aristysa.MOD_ID);

    public static final RegistryObject<RecipeSerializer<FusionCrafterRecipe>> FUSION_CRAFTER =
            SERIALIZERS.register("fusion_crafting", () -> FusionCrafterRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
