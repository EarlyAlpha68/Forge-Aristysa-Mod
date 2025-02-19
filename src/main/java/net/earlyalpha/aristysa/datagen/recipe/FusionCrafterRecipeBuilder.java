package net.earlyalpha.aristysa.datagen.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.recipe.FusionCrafterRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.registries.RegistriesDatapackGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FusionCrafterRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient[] ingredients;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public FusionCrafterRecipeBuilder(ItemLike[] ingredients, ItemLike result, int count) {
        this.ingredients = new Ingredient[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            this.ingredients[i] = Ingredient.of(ingredients[i]);
        }
        this.result = result.asItem();
        this.count = count;
    }


    @Override
    public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance conditions) {
        this.advancement.addCriterion(name, conditions);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> exporter, ResourceLocation recipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId));

        exporter.accept(new JsonBuilder(recipeId, this.result, this.ingredients, this.count,
                        this.advancement, new ResourceLocation(recipeId.getNamespace(), "recipes/"
                        + recipeId.getPath())));
    }

    public static class JsonBuilder implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Ingredient[] ingredients;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public JsonBuilder(ResourceLocation id, Item result, Ingredient[] ingredients,
                           int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.result = result;
            this.ingredients = ingredients;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray jsonarray = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                jsonarray.add(ingredient.toJson());
            }

            json.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            json.add("output", jsonobject);

        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(Aristysa.MOD_ID,
                    ForgeRegistries.ITEMS.getKey(this.result).getPath() + "_from_fusion_crafter");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return FusionCrafterRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
