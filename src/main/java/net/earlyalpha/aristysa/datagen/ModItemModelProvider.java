package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,ExistingFileHelper existingFileHelper) {
        super(output, Aristysa.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.LEAD_INGOT);
        simpleItem(ModItems.RAW_LEAD);
        simpleItem(ModItems.ALUMINUM_INGOT);
        simpleItem(ModItems.RAW_ALUMINUM);

    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Aristysa.MOD_ID,"item/" + item.getId().getPath()));
    }
}
