package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Aristysa.MOD_ID);

    public static final RegistryObject<MenuType<CyberwareGuiMenuType>> CYBERWARE_MENU =
            registerMenuType("cyberware_menu", CyberwareGuiMenuType::new);
    public static final RegistryObject<MenuType<FusionCrafterMenu>> FUSION_CRAFTER_MENU =
            registerMenuType("fusion_crafter_menu",FusionCrafterMenu::new);


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
