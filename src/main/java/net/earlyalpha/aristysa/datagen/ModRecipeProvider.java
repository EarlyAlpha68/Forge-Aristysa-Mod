package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.datagen.recipe.FusionCrafterRecipeBuilder;
import net.earlyalpha.aristysa.datagen.recipe.LabotaryTrayRecipeBuilder;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
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


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LABOTARY_TRAY.get())
                .pattern("SWG")
                .pattern("OWA")
                .pattern("III")
                .define('S', ModItems.EMPTY_SYRINGE.get())
                .define('A', ModItems.ALUMINUM_PLATE.get())
                .define('O', Items.GOLD_INGOT)
                .define('W', ModItems.WIRE.get())
                .define('G', Items.GLASS_BOTTLE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.EMPTY_SYRINGE.get()), has(ModItems.EMPTY_SYRINGE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPTY_SYRINGE.get())
                .pattern(" GI")
                .pattern("G G")
                .pattern("IG ")
                .define('G', Blocks.GLASS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Blocks.GLASS), has(Blocks.GLASS))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WARDEN_HEART_1.get())
                .pattern("YIY")
                .pattern("LSL")
                .pattern("HCH")
                .define('S', Items.NETHER_STAR)
                .define('C', ModItems.MICRO_CHIP.get())
                .define('H', Items.ECHO_SHARD)
                .define('L', ModTags.Items.LEAD_PLATES)
                .define('Y', ModItems.SYNTHETIC_MUSCLE.get())
                .define('I', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WARDEN_HEART_2.get())
                .pattern("SPS")
                .pattern("PCP")
                .pattern("SMS")
                .define('C', ModItems.WARDEN_HEART_1.get())
                .define('S', Items.NETHER_STAR)
                .define('P', ModItems.CONDUCTIVE_PASTE.get())
                .define('M', ModItems.MICRO_CHIP.get())
                .unlockedBy(getHasName(ModItems.WARDEN_HEART_1.get()), has(ModItems.WARDEN_HEART_1.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WARDEN_HEART_3.get())
                .pattern("NSN")
                .pattern("SCS")
                .pattern("NSN")
                .define('C', ModItems.WARDEN_HEART_2.get())
                .define('S', Items.NETHER_STAR)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItems.WARDEN_HEART_2.get()), has(ModItems.WARDEN_HEART_2.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OPTICAL_CAMO_1.get())
                .pattern("GOG")
                .pattern("OCO")
                .pattern("GOG")
                .define('C', ModItems.SUBDERMAL_ARMOR_1.get())
                .define('O', ModItems.OPTICAL_FIBER.get())
                .define('G', Blocks.GLASS)
                .unlockedBy(getHasName(ModItems.SUBDERMAL_ARMOR_1.get()), has(ModItems.SUBDERMAL_ARMOR_1.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OPTICAL_CAMO_2.get())
                .pattern("APA")
                .pattern("MCP")
                .pattern("AMA")
                .define('C', ModItems.OPTICAL_CAMO_1.get())
                .define('A', ModTags.Items.ALUMINUM_PLATES)
                .define('P', ModItems.CONDUCTIVE_PASTE.get())
                .define('M', ModItems.MICRO_CHIP.get())
                .unlockedBy(getHasName(ModItems.OPTICAL_CAMO_1.get()), has(ModItems.OPTICAL_CAMO_1.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OPTICAL_CAMO_3)
                .pattern("DND")
                .pattern("OCN")
                .pattern("OOD")
                .define('C', ModItems.OPTICAL_CAMO_2)
                .define('O',ModItems.OPTICAL_FIBER)
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItems.OPTICAL_CAMO_2), has(ModItems.OPTICAL_CAMO_2))
                .save(pWriter,new Identifier(getRecipeName(ModItems.OPTICAL_CAMO_3)));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUBDERMAL_ARMOR_1)
                .pattern("PAP")
                .pattern("ALA")
                .pattern("PAP")
                .define('L',ModBlocks.LEAD_BLOCK)
                .define('A', ModTags.Items.ALUMINUM_PLATES)
                .define('P',ModTags.Items.LEAD_PLATES)
                .unlockedBy(getHasName(ModBlocks.LEAD_BLOCK), has(ModBlocks.LEAD_BLOCK))
                .save(pWriter,new Identifier(getRecipeName(ModItems.SUBDERMAL_ARMOR_1)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUBDERMAL_ARMOR_2)
                .pattern("ADA")
                .pattern("DCD")
                .pattern("ADA")
                .define('C',ModItems.SUBDERMAL_ARMOR_1)
                .define('A', ModTags.Items.ALUMINUM_PLATES)
                .define('D',Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.SUBDERMAL_ARMOR_1), has(ModItems.SUBDERMAL_ARMOR_1))
                .save(pWriter,new Identifier(getRecipeName(ModItems.SUBDERMAL_ARMOR_2)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUBDERMAL_ARMOR_3)
                .pattern("DND")
                .pattern("ACN")
                .pattern("AAD")
                .define('C', ModItems.SUBDERMAL_ARMOR_2)
                .define('A',ModTags.Items.ALUMINUM_PLATES)
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItems.SUBDERMAL_ARMOR_2), has(ModItems.SUBDERMAL_ARMOR_2))
                .save(pWriter,new Identifier(getRecipeName(ModItems.SUBDERMAL_ARMOR_3)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLEMARM_1)
                .pattern("SII")
                .pattern("WMI")
                .pattern("SWS")
                .define('I',Blocks.IRON_BLOCK)
                .define('W', ModItems.WIRE)
                .define('M',ModItems.MICRO_CHIP)
                .define('S',ModItems.SYNTHETIC_MUSCLE)
                .unlockedBy(getHasName(Blocks.IRON_BLOCK), has(Blocks.IRON_BLOCK))
                .save(pWriter,new Identifier(getRecipeName(ModItems.GOLEMARM_1)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLEMARM_2)
                .pattern("WSI")
                .pattern("SCS")
                .pattern("SSW")
                .define('C',ModItems.GOLEMARM_1)
                .define('I',Blocks.IRON_BLOCK)
                .define('W', ModItems.WIRE)
                .define('S',ModItems.SYNTHETIC_MUSCLE)
                .unlockedBy(getHasName(ModItems.GOLEMARM_1), has(ModItems.GOLEMARM_1))
                .save(pWriter,new Identifier(getRecipeName(ModItems.GOLEMARM_2)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLEMARM_3)
                .pattern("NDD")
                .pattern("ICD")
                .pattern("WIN")
                .define('C', ModItems.GOLEMARM_2)
                .define('I',Blocks.IRON_BLOCK)
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_INGOT)
                .define('W',ModItems.WIRE)
                .unlockedBy(getHasName(ModItems.GOLEMARM_2), has(ModItems.GOLEMARM_2))
                .save(pWriter,new Identifier(getRecipeName(ModItems.GOLEMARM_3)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENDEREYE_1)
                .pattern("WGG")
                .pattern("OMG")
                .pattern("EOW")
                .define('G',Blocks.GLASS)
                .define('W', ModItems.WIRE)
                .define('M',ModItems.MICRO_CHIP)
                .define('O',ModItems.OPTICAL_FIBER)
                .define('E',Items.ENDER_EYE)
                .unlockedBy(getHasName(ModItems.OPTICAL_FIBER), has(ModItems.OPTICAL_FIBER))
                .save(pWriter,new Identifier(getRecipeName(ModItems.ENDEREYE_1)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENDEREYE_2)
                .pattern("RRR")
                .pattern("OCR")
                .pattern("WOR")
                .define('C',ModItems.ENDEREYE_1)
                .define('O',ModItems.OPTICAL_FIBER)
                .define('R',Items.REDSTONE)
                .define('W',ModItems.WIRE)
                .unlockedBy(getHasName(ModItems.ENDEREYE_1), has(ModItems.ENDEREYE_1))
                .save(pWriter,new Identifier(getRecipeName(ModItems.ENDEREYE_2)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENDEREYE_3)
                .pattern("NDD")
                .pattern("OCD")
                .pattern("WON")
                .define('C', ModItems.ENDEREYE_2)
                .define('O', ModItems.OPTICAL_FIBER)
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_INGOT)
                .define('W',ModItems.WIRE)
                .unlockedBy(getHasName(ModItems.ENDEREYE_2), has(ModItems.ENDEREYE_2))
                .save(pWriter,new Identifier(getRecipeName(ModItems.ENDEREYE_3)));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CYBERLEG_1)
                .pattern("AWS")
                .pattern("WSW")
                .pattern("SWA")
                .define('A',ModTags.Items.ALUMINUM_PLATES)
                .define('W', ModItems.WIRE)
                .define('S',ModItems.SYNTHETIC_MUSCLE)
                .unlockedBy(getHasName(ModItems.SYNTHETIC_MUSCLE), has(ModItems.SYNTHETIC_MUSCLE))
                .save(pWriter,new Identifier(getRecipeName(ModItems.CYBERLEG_1)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CYBERLEG_2)
                .pattern("DSS")
                .pattern("WCS")
                .pattern("DWD")
                .define('C',ModItems.CYBERLEG_1)
                .define('S', ModItems.SYNTHETIC_MUSCLE)
                .define('D',Items.DIAMOND)
                .define('W',ModItems.WIRE)
                .unlockedBy(getHasName(ModItems.CYBERLEG_1), has(ModItems.CYBERLEG_1))
                .save(pWriter,new Identifier(getRecipeName(ModItems.CYBERLEG_2)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CYBERLEG_3)
                .pattern("NON")
                .pattern("OCO")
                .pattern("NON")
                .define('C', ModItems.CYBERLEG_2)
                .define('O', ModItems.OPTICAL_FIBER)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItems.CYBERLEG_2), has(ModItems.CYBERLEG_2))
                .save(pWriter,new Identifier(getRecipeName(ModItems.CYBERLEG_3)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FUSION_CRAFTER)
                .pattern("SQS")
                .pattern("QDQ")
                .pattern("SQS")
                .define('S',ModTags.Items.LEAD_INGOTS)
                .define('Q', Items.IRON_INGOT)
                .define('D',ModBlocks.LEAD_BLOCK)
                .unlockedBy(getHasName(ModItems.LEAD_INGOT), has(ModItems.LEAD_INGOT))
                .save(pWriter,new Identifier(getRecipeName(ModBlocks.FUSION_CRAFTER)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CONDUCTIVE_PASTE)
                .pattern("SQS")
                .pattern("QDQ")
                .pattern("SQS")
                .define('S', Items.COAL)
                .define('Q', Items.COPPER_INGOT)
                .define('D', Items.REDSTONE)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(pWriter,new Identifier(getRecipeName(ModItems.CONDUCTIVE_PASTE)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OPTICAL_FIBER)
                .pattern("DSG")
                .pattern("SGS")
                .pattern("GSD")
                .define('S', ModItems.SHEATHE)
                .define('G', Blocks.GLASS)
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.SHEATHE), has(ModItems.SHEATHE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIRE)
                .pattern(" SG")
                .pattern("SGS")
                .pattern("GS ")
                .define('S', ModItems.SHEATHE)
                .define('G', Items.COPPER_INGOT)
                .unlockedBy(getHasName(ModItems.SHEATHE), has(ModItems.SHEATHE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SYNTHETIC_MUSCLE.get())
                .pattern("QED")
                .pattern("ESE")
                .pattern("DEQ")
                .define('S',Items.BLAZE_ROD)
                .define('Q', Items.ROTTEN_FLESH)
                .define('D',Items.LEATHER)
                .define('E', ModItems.ALUMINUM_INGOT)
                .unlockedBy(getHasName(ModItems.ALUMINUM_INGOT.get()), has(ModItems.ALUMINUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MICRO_CHIP.get())
                .pattern("SHS")
                .pattern("DQD")
                .pattern("EFE")
                .define('S', Items.COPPER_INGOT)
                .define('Q', ModItems.CONDUCTIVE_PASTE.get())
                .define('D',Items.SCUTE)
                .define('E',Items.GOLD_INGOT)
                .define('F', Blocks.LIGHTNING_ROD)
                .define('H',Items.REPEATER)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
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
