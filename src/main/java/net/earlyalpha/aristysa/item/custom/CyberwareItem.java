package net.earlyalpha.aristysa.item.custom;


import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;


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
            if (!(((IEntityDataSaver) player).getPersistentData().getInt(this.key) == this.tier)) {
                alreadyHasIt(player,this.tier,this.key);
                EarlyUtil.addTier(((IEntityDataSaver) player), this.tier,key );
                player.sendSystemMessage(Component.literal("You successfully implant yourself an " + EarlyUtil.getImplantName(this.key)+" tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
                ItemStack itemStack = player.getItemInHand(usedHand);
                itemStack.setCount(itemStack.getCount()-1);
                player.setItemInHand(usedHand, itemStack);
            } else {
                player.sendMessage(Text.literal("You already have an " +EarlyUtil.getImplantName(this.key)+ " tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }
    private static void alreadyHasIt(Player player, int tiers, String key) {
        int implantType = EarlyUtil.getImplantType(key);
        int implantTier = EarlyUtil.getImplantTier(player,key);
        if ((implantTier == 1 && (!(tiers == 1)))) {
            if (!player.getInventory().add(EarlyUtil.CyberwareItemstack(implantType,1))) {
                player.drop(EarlyUtil.CyberwareItemstack(implantType,1), false);
            }
        }
        if ((implantTier == 2 && (!(tiers == 2)))) {
            if (!player.getInventory().add(EarlyUtil.CyberwareItemstack(implantType,2))) {
                player.drop(EarlyUtil.CyberwareItemstack(implantType,2), false);
            }
        }
        if ((implantTier == 3 && (!(tiers == 3)))) {
            if (!player.getInventory().add(EarlyUtil.CyberwareItemstack(implantType,3))) {
                player.drop(EarlyUtil.CyberwareItemstack(implantType,3), false);
            }
        }
    }
}
