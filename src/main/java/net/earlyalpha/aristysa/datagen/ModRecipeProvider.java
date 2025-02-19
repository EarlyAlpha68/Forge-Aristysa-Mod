package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.datagen.recipe.FusionCrafterRecipeBuilder;
import net.earlyalpha.aristysa.datagen.recipe.LabotaryTrayRecipeBuilder;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder  {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }
    private static final List<ItemLike> LEAD_SMELTABLES = List.of(ModItems.RAW_LEAD.get(),
            ModBlocks.DEEPSLATE_LEAD_ORE.get(), ModBlocks.LEAD_ORE.get());
    private static final List<ItemLike> ALUMINUM_SMELTABLES = List.of(ModItems.RAW_ALUMINUM.get(),
            ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), ModBlocks.ALUMINUM_ORE.get());

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, LEAD_SMELTABLES,RecipeCategory.MISC,ModItems.LEAD_INGOT.get(),0.25f,100,"lead_ingot");
        oreBlasting(pWriter, LEAD_SMELTABLES,RecipeCategory.MISC,ModItems.LEAD_INGOT.get(),0.25f,100,"lead_ingot");
        oreSmelting(pWriter, ALUMINUM_SMELTABLES,RecipeCategory.MISC,ModItems.ALUMINUM_INGOT.get(),0.25f,100,"lead_ingot");
        oreBlasting(pWriter, ALUMINUM_SMELTABLES,RecipeCategory.MISC,ModItems.ALUMINUM_INGOT.get(),0.25f,100,"lead_ingot");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_LEAD_BLOCK.get())
                .pattern("LLL")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', ModItems.RAW_LEAD.get())
                .unlockedBy(getHasName(ModItems.RAW_LEAD.get()),has(ModItems.RAW_LEAD.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEAD_BLOCK.get())
                .pattern("LLL")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', ModItems.LEAD_INGOT.get())
                .unlockedBy(getHasName(ModItems.LEAD_INGOT.get()),has(ModItems.LEAD_INGOT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 9)
                .requires(ModBlocks.LEAD_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LEAD_BLOCK.get()),has(ModBlocks.LEAD_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_LEAD.get(), 9)
                .requires(ModBlocks.RAW_LEAD_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_LEAD_BLOCK.get()),has(ModBlocks.RAW_LEAD_BLOCK.get()))
                .save(pWriter);
        new FusionCrafterRecipeBuilder(new ItemLike[]{ModItems.ALUMINUM_INGOT.get(), ModItems.ALUMINUM_INGOT.get()},
                ModItems.ALUMINUM_PLATE.get(), 1).save(pWriter, "aluminum_plate");
        new LabotaryTrayRecipeBuilder(new ItemLike[]{ModItems.ALUMINUM_INGOT.get(), ModItems.ALUMINUM_INGOT.get()},
                ModItems.ALUMINUM_PLATE.get(), 1).save(pWriter, "aluminum_plate");
    }


    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Aristysa.MOD_ID + ":"+ getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
