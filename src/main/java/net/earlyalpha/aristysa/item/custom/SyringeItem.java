package net.earlyalpha.aristysa.item.custom;

import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SyringeItem extends Item {
    private static final int INJECTION_TIME = 40;
    private static final String INJECTION = "UsageTicks";
    protected String key;

    public SyringeItem(Properties pProperties, String key) {
        super(pProperties);
        this.key = key;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClientSide()) {
            CompoundTag nbt = stack.getOrCreateTag();
            int usageTicks = INJECTION_TIME - remainingUseTicks;
            nbt.putInt(INJECTION, usageTicks);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (!world.isClientSide() && user instanceof Player) {
            Player player = (Player) user;
            CompoundTag nbt = stack.getOrCreateTag();
            int usageTicks = nbt.getInt(INJECTION);

            if (usageTicks >= 30) {
                EarlyUtil.SyringeInject(player,this.key);
                stack.shrink(1);

            }
            nbt.putInt(INJECTION, 0);
        }
        return stack;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt(INJECTION, 0);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return INJECTION_TIME;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        EarlyUtil.SyringeItemToolTip(stack,world,tooltip,context,EarlyUtil.getSyringeType(this.key));
        super.appendHoverText(stack, world, tooltip, context);
    }
}
