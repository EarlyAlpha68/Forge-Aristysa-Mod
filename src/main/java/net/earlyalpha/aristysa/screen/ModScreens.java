package net.earlyalpha.aristysa.screen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ModScreens {
    public static void register() {
        MenuScreens.register(ModMenuTypes.CYBERWARE_MENU.get(), new MenuScreens.ScreenConstructor<CyberwareGuiMenuType, CyberwareGuiScreen>() {
            @Override
            public CyberwareGuiScreen create(CyberwareGuiMenuType pMenu, Inventory pInventory, Component pTitle) {
                return new CyberwareGuiScreen(pMenu,pInventory,pTitle);
            }
        });
    }
}
