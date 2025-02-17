package net.earlyalpha.aristysa.screen.slot;

import net.earlyalpha.aristysa.client.ClientCyberwareTierData;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class OnlyReadSlot extends Slot {
    protected Player player;
    protected String key;
    protected int slot;
    public OnlyReadSlot(Container pContainer, int pSlot, int pX, int pY,Player pPlayer,String pKey) {
        super(pContainer, pSlot, pX, pY);
        this.key =pKey;
        this.player = pPlayer;
        this.slot = pSlot;
    }

    @Override
    public boolean mayPickup(Player pPlayer) {
        return false;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return false;
    }

    @Override
    public void set(ItemStack pStack) {


    }

    @Override
    public ItemStack getItem() {
        if (ClientCyberwareTierData.getPlayerCyberware() != null) {
            return EarlyUtil.CyberwareItemstack(EarlyUtil.getImplantType(key), ClientCyberwareTierData.getPlayerCyberware().getInt(key));
        }
        return ItemStack.EMPTY;
    }
}
