package net.earlyalpha.aristysa.datagen.loot;

import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }



    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.LEAD_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_LEAD_BLOCK.get());

        this.add(ModBlocks.ALUMINUM_ORE.get(),
                block -> createOreDrop(ModBlocks.ALUMINUM_ORE.get(), ModItems.RAW_ALUMINUM.get()));
        this.add(ModBlocks.LEAD_ORE.get(),
                block -> createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get()));
        this.add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(),
                block -> createOreDrop(ModBlocks.ALUMINUM_ORE.get(), ModItems.RAW_ALUMINUM.get()));
        this.add(ModBlocks.DEEPSLATE_LEAD_ORE.get(),
                block -> createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
