package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,@Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Aristysa.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LEAD_BLOCK.get(),
                        ModBlocks.LEAD_ORE.get(),
                        ModBlocks.ALUMINUM_ORE.get(),
                        ModBlocks.DEEPSLATE_LEAD_ORE.get(),
                        ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(),
                        ModBlocks.RAW_LEAD_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.LEAD_BLOCK.get(),
                        ModBlocks.LEAD_ORE.get(),
                        ModBlocks.ALUMINUM_ORE.get(),
                        ModBlocks.DEEPSLATE_LEAD_ORE.get(),
                        ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(),
                        ModBlocks.RAW_LEAD_BLOCK.get());


        tag(ModTags.Blocks.ALUMINUM_ORE)
                .add(ModBlocks.ALUMINUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get());
        tag(ModTags.Blocks.LEAD_ORE)
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get());
        tag(ModTags.Blocks.LEAD_BLOCKS)
                .add(ModBlocks.LEAD_BLOCK.get());
    }
}
