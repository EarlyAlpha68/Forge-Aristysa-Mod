package net.earlyalpha.aristysa.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.recipe.FusionCrafterRecipe;
import net.earlyalpha.aristysa.recipe.LabotaryTrayRecipe;
import net.earlyalpha.aristysa.screen.FusionCrafterScreen;
import net.earlyalpha.aristysa.screen.LabotaryTrayScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIAristysaModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Aristysa.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FusionCraftCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new LabotaryTrayCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FusionCrafterScreen.class,60,30,20,30,
                FusionCraftCategory.FUSION_CRAFT_TYPE);
        registration.addRecipeClickArea(LabotaryTrayScreen.class,60,30,20,30,
                LabotaryTrayCategory.LABOTARY_TRAY_TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<FusionCrafterRecipe> fusionCrafterRecipes = recipeManager.getAllRecipesFor(FusionCrafterRecipe.Type.INSTANCE);
        registration.addRecipes(FusionCraftCategory.FUSION_CRAFT_TYPE,fusionCrafterRecipes);

        List<LabotaryTrayRecipe> labotaryTrayRecipes = recipeManager.getAllRecipesFor(LabotaryTrayRecipe.Type.INSTANCE);
        registration.addRecipes(LabotaryTrayCategory.LABOTARY_TRAY_TYPE,labotaryTrayRecipes);
    }
}
