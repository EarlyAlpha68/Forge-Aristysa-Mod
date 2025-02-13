package net.earlyalpha.aristysa.item;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Aristysa.MOD_ID);


    public static final RegistryObject<CreativeModeTab> ARISTYSA_TAB = CREATIVE_MODE_TAB.register("aristysa_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.LEAD_INGOT.get()))
                    .title(Component.translatable("creativetab.aristysa_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LEAD_INGOT.get());
                        output.accept(ModItems.RAW_LEAD.get());
                        output.accept(ModBlocks.LEAD_BLOCK.get());
                        output.accept(ModBlocks.RAW_LEAD_BLOCK.get());
                        output.accept(ModBlocks.LEAD_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_LEAD_ORE.get());
                        output.accept(ModItems.ALUMINUM_INGOT.get());
                        output.accept(ModItems.RAW_ALUMINUM.get());
                        output.accept(ModBlocks.ALUMINUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
