package net.earlyalpha.aristysa.item;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Aristysa.MOD_ID);

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
