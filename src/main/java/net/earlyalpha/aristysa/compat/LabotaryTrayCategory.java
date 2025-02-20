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
import net.earlyalpha.aristysa.recipe.LabotaryTrayRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class LabotaryTrayCategory implements IRecipeCategory<LabotaryTrayRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Aristysa.MOD_ID,"labotary_tray_craft");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Aristysa.MOD_ID,
            "textures/gui/labotary_tray_gui_recipe_showcase.png");
    public static final RecipeType<LabotaryTrayRecipe> LABOTARY_TRAY_TYPE =
            new RecipeType<>(UID, LabotaryTrayRecipe.class);

    private final IDrawable bG;
    private final IDrawable icon;

    public LabotaryTrayCategory(IGuiHelper guiHelper) {
        this.bG = guiHelper.createDrawable(TEXTURE,0,0,176,80);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.LABOTARY_TRAY.get()));
    }


    @Override
    public RecipeType<LabotaryTrayRecipe> getRecipeType() {
        return LABOTARY_TRAY_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.aristysa.labotary_tray");
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
    public void setRecipe(IRecipeLayoutBuilder builder, LabotaryTrayRecipe recipe, IFocusGroup focuse) {
        builder.addSlot(RecipeIngredientRole.INPUT,42,35).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT,64,35).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT,118,35).addItemStack(recipe.getResultItem(null));
    }
}
