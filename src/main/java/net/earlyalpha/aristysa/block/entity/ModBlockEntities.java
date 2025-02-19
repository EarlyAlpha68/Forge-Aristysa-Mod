package net.earlyalpha.aristysa.block.entity;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.block.custom.LabotaryTrayBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES.getRegistryName(), Aristysa.MOD_ID);

    public static final RegistryObject<BlockEntityType<FusionCrafterBlockEntity>> FUSION_CRAFTER_BE =
            BLOCK_ENTITIES.register("fusion_crafter_be", () ->
                    BlockEntityType.Builder.of(FusionCrafterBlockEntity::new,
                            ModBlocks.FUSION_CRAFTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<LabotaryTrayBlockEntity>> LABOTARY_TRAY_BE =
            BLOCK_ENTITIES.register("labotary_tray_be", () ->
                    BlockEntityType.Builder.of(LabotaryTrayBlockEntity::new,
                            ModBlocks.LABOTARY_TRAY.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
