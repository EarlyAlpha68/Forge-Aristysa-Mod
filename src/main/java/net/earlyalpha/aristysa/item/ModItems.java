package net.earlyalpha.aristysa.item;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.item.custom.CyberwareItem;
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
    public static final RegistryObject<Item> ALUMINUM_PLATE = ITEMS.register("aluminum_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CYBERLEG_1 = ITEMS.register("cyberleg_1",
            () -> new CyberwareItem(new Item.Properties(),1,"cyberLegTier"));
    public static final RegistryObject<Item> CYBERLEG_2 = ITEMS.register("cyberleg_2",
            () -> new CyberwareItem(new Item.Properties(),2,"cyberLegTier"));
    public static final RegistryObject<Item> CYBERLEG_3 = ITEMS.register("cyberleg_3",
            () -> new CyberwareItem(new Item.Properties(),3,"cyberLegTier"));
    public static final RegistryObject<Item> ENDEREYE_1 = ITEMS.register("endereye_1",
            () -> new CyberwareItem(new Item.Properties(),1,"enderEyeTier"));
    public static final RegistryObject<Item> ENDEREYE_2 = ITEMS.register("endereye_2",
            () -> new CyberwareItem(new Item.Properties(),2,"enderEyeTier"));
    public static final RegistryObject<Item> ENDEREYE_3 = ITEMS.register("endereye_3",
            () -> new CyberwareItem(new Item.Properties(),3,"enderEyeTier"));
    public static final RegistryObject<Item> GOLEMARM_1 = ITEMS.register("golemarm_1",
            () -> new CyberwareItem(new Item.Properties(),1,"golemArmTier"));
    public static final RegistryObject<Item> GOLEMARM_2 = ITEMS.register("golemarm_2",
            () -> new CyberwareItem(new Item.Properties(),2,"golemArmTier"));
    public static final RegistryObject<Item> GOLEMARM_3 = ITEMS.register("golemarm_3",
            () -> new CyberwareItem(new Item.Properties(),3,"golemArmTier"));
    public static final RegistryObject<Item> OPTICAL_CAMO_1 = ITEMS.register("optical_camo_1",
            () -> new CyberwareItem(new Item.Properties(),1,"opticalCamoTier"));
    public static final RegistryObject<Item> OPTICAL_CAMO_2 = ITEMS.register("optical_camo_2",
            () -> new CyberwareItem(new Item.Properties(),2,"opticalCamoTier"));
    public static final RegistryObject<Item> OPTICAL_CAMO_3 = ITEMS.register("optical_camo_3",
            () -> new CyberwareItem(new Item.Properties(),3,"opticalCamoTier"));
    public static final RegistryObject<Item> SUBDERMAL_ARMOR_1 = ITEMS.register("subdermal_armor_1",
            () -> new CyberwareItem(new Item.Properties(),1,"subdermalArmorTier"));
    public static final RegistryObject<Item> SUBDERMAL_ARMOR_2 = ITEMS.register("subdermal_armor_2",
            () -> new CyberwareItem(new Item.Properties(),2,"subdermalArmorTier"));
    public static final RegistryObject<Item> SUBDERMAL_ARMOR_3 = ITEMS.register("subdermal_armor_3",
            () -> new CyberwareItem(new Item.Properties(),3,"subdermalArmorTier"));
    public static final RegistryObject<Item> WARDEN_HEART_1 = ITEMS.register("wardenheart_1",
            () -> new CyberwareItem(new Item.Properties(),1,"wardenHeartTier"));
    public static final RegistryObject<Item> WARDEN_HEART_2 = ITEMS.register("wardenheart_2",
            () -> new CyberwareItem(new Item.Properties(),2,"wardenHeartTier"));
    public static final RegistryObject<Item> WARDEN_HEART_3 = ITEMS.register("wardenheart_3",
            () -> new CyberwareItem(new Item.Properties(),3,"wardenHeartTier"));






    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
