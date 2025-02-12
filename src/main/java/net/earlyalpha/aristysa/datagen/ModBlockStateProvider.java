package net.earlyalpha.aristysa.datagen;


import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Aristysa.MOD_ID,exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LEAD_BLOCK);
        blockWithItem(ModBlocks.RAW_LEAD_BLOCK);
        blockWithItem(ModBlocks.ALUMINUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALUMINUM_ORE);
        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LEAD_ORE);

    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
