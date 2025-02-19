package net.earlyalpha.aristysa.datagen.recipe;


import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FusionCrafterRecipeBuilder implements FinishedRecipe {


    @Override
    public void serializeRecipeData(JsonObject pJson) {

    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return null;
    }

    @Nullable
    @Override
    public JsonObject serializeAdvancement() {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getAdvancementId() {
        return null;
    }
    public static class JsonBuilder implements RecipeBuilder {

        @Override
        public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
            return null;
        }

        @Override
        public RecipeBuilder group(@Nullable String pGroupName) {
            return null;
        }

        @Override
        public Item getResult() {
            return null;
        }

        @Override
        public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {

        }
    }
}
