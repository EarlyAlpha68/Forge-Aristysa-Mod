package net.earlyalpha.aristysa.screen;

import com.mojang.blaze3d.platform.ScreenManager;
import net.earlyalpha.aristysa.screen.slot.OnlyReadSlot;
import net.minecraft.network.FriendlyByteBuf;

import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CyberwareGuiMenuType extends AbstractContainerMenu {


    private final Container container;
    public CyberwareGuiMenuType(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv);
    }

    public CyberwareGuiMenuType(int pContainerId, Inventory inv) {
        super(ModMenuTypes.CYBERWARE_MENU.get(), pContainerId);
        this.container = new SimpleContainer(8);
            checkContainerSize(inv, 8);
        addSlot(new OnlyReadSlot(container,0,19,137,inv.player,"cyberLegTier"));
        addSlot(new OnlyReadSlot(container,1,19,17,inv.player,"enderEyeTier"));
        addSlot(new OnlyReadSlot(container,2,19,57,inv.player,"golemArmTier"));
        addSlot(new OnlyReadSlot(container,3,141,57,inv.player,"opticalCamoTier"));
        addSlot(new OnlyReadSlot(container,4,141,97,inv.player,"subdermalArmorTier"));
        addSlot(new OnlyReadSlot(container,5,19,97,inv.player,"wardenHeartTier"));


    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;




    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }



}