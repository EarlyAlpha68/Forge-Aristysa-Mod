package net.earlyalpha.aristysa.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FusionCrafterRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> recipeItem;
    private final ItemStack output;
    private final ResourceLocation id;

    public FusionCrafterRecipe(NonNullList<Ingredient> recipeItem, ItemStack output, ResourceLocation id) {
        this.recipeItem = recipeItem;
        this.output = output;
        this.id = id;
    }


    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()){
            return false;
        }
        return recipeItem.get(0).test(pContainer.getItem(0)) && recipeItem.get(1).test(pContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
    public static class Type implements RecipeType<FusionCrafterRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "fusion_crafter";
    }

    public static class Serializer implements RecipeSerializer<FusionCrafterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Aristysa.MOD_ID,"fusion_crafting");
        @Override
        public FusionCrafterRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new FusionCrafterRecipe(inputs, output, id);
        }

        @Override
        public @Nullable FusionCrafterRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new FusionCrafterRecipe(inputs, output, id);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FusionCrafterRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(null), false);

        }
    }
}
