package net.earlyalpha.aristysa.item.custom;


import net.earlyalpha.aristysa.capability.PlayerCyberwareTierProvider;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public class CyberwareItem extends Item {
    protected String key;
    protected int tier;

    public CyberwareItem(Properties pProperties,int tier,String key) {
        super(pProperties);
        this.key = key;
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand usedHand) {
            if (!world.isClientSide()) {
                if (!(EarlyUtil.getImplantTier(player,this.key) == this.tier)) {
                    alreadyHasIt(player,this.tier, EarlyUtil.getImplantTier(player,this.key), this.key);
                    EarlyUtil.setImplantTier(player,this.key,this.tier);
                    player.sendSystemMessage(Component.literal("You successfully implant yourself an " + EarlyUtil.getImplantName(this.key)+" tier " + EarlyUtil.getImplantTier(player,key)));
                    ItemStack itemStack = player.getItemInHand(usedHand);
                    itemStack.shrink(1);
                    player.setItemInHand(usedHand,itemStack );
                } else {
                    player.sendSystemMessage(Component.literal("You already have an " + EarlyUtil.getImplantName(this.key)+ " tier " + EarlyUtil.getImplantTier(player,key)));
                }
            }

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }

    private static void alreadyHasIt(Player player, int tiers,int implantTier, String key) {

        int implantType = EarlyUtil.getImplantType(key);
        if ((implantTier == 1 && (!(tiers == 1)))) {
            player.getInventory().add(EarlyUtil.CyberwareItemstack(implantType, 1));
        } else if ((implantTier == 2 && (!(tiers == 2)))) {
            player.getInventory().add(EarlyUtil.CyberwareItemstack(implantType, 2));
        } else if ((implantTier == 3 && (!(tiers == 3)))) {
            player.getInventory().add(EarlyUtil.CyberwareItemstack(implantType, 3));
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        EarlyUtil.cyberwareItemToolTip(pTooltipComponents,this.tier,EarlyUtil.getImplantType(this.key));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}


