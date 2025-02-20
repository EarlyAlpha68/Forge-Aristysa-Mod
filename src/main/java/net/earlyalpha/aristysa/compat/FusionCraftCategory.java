package net.earlyalpha.aristysa.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.recipe.FusionCrafterRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class FusionCraftCategory implements IRecipeCategory<FusionCrafterRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Aristysa.MOD_ID,"fusion_crafting");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Aristysa.MOD_ID,
            "textures/gui/fusion_crafter_gui_recipe_showcase.png");
    public static final RecipeType<FusionCrafterRecipe> FUSION_CRAFT_TYPE =
            new RecipeType<>(UID, FusionCrafterRecipe.class);

    private final IDrawable bG;
    private final IDrawable icon;

    public FusionCraftCategory(IGuiHelper guiHelper) {
        this.bG = guiHelper.createDrawable(TEXTURE,0,0,176,80);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FUSION_CRAFTER.get()));
    }


    @Override
    public RecipeType<FusionCrafterRecipe> getRecipeType() {
        return FUSION_CRAFT_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.aristysa.fusion_crafter");
    }


    @Override
    public @Nullable IDrawable getBackground() {
        return this.bG;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FusionCrafterRecipe recipe, IFocusGroup focuse) {
        builder.addSlot(RecipeIngredientRole.INPUT,26,34).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT,134,34).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT,80,34).addItemStack(recipe.getResultItem(null));
    }
}
