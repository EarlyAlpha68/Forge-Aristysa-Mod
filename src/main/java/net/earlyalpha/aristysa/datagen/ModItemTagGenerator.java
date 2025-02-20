package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Aristysa.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(ModTags.Items.ALUMINUM_INGOTS)
                .add(ModItems.ALUMINUM_INGOT.get());
        tag(ModTags.Items.LEAD_INGOTS)
                .add(ModItems.LEAD_INGOT.get());
        tag(ModTags.Items.ALUMINUM_PLATES)
                .add(ModItems.ALUMINUM_PLATE.get());
        tag(ModTags.Items.LEAD_PLATES)
                .add(ModItems.LEAD_PLATE.get());


    }
}
