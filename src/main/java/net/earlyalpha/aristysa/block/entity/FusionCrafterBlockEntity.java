package net.earlyalpha.aristysa.block.entity;

import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.screen.FusionCrafterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;



public class FusionCrafterBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3);

    private static final int SLOT_1 = 0;
    private static final int SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    public FusionCrafterBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FUSION_CRAFTER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> FusionCrafterBlockEntity.this.progress;
                    case 1 -> FusionCrafterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int v) {
                switch (i) {
                    case 0: FusionCrafterBlockEntity.this.progress = v;
                    case 1: FusionCrafterBlockEntity.this.maxProgress = v;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i< itemStackHandler.getSlots(); i++) {
            inv.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("Fusion Crafter");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new FusionCrafterMenu(i,inventory,this,this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory",itemStackHandler.serializeNBT());
        nbt.putInt("fusion_crafter.progress",progress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("fusion_crafter.progress");
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
        if(canInsertIntoOutputSlot() && hasRecipe()) {
            increaseCraftingProgress();
            setChanged(world,pos,state);
            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }
    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        this.itemStackHandler.setStackInSlot(SLOT_1,ItemStack.EMPTY);
        this.itemStackHandler.setStackInSlot(SLOT_2,ItemStack.EMPTY);
        this.itemStackHandler.setStackInSlot(OUTPUT_SLOT,new ItemStack(ModItems.ALUMINUM_PLATE.get(),1));

    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
         boolean hasCraftingItem = this.itemStackHandler.getStackInSlot(SLOT_1).getItem() == ModItems.ALUMINUM_INGOT.get() && this.itemStackHandler.getStackInSlot(SLOT_2).getItem() == ModItems.ALUMINUM_INGOT.get();
         ItemStack result = new ItemStack(ModItems.ALUMINUM_PLATE.get());
         return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) &&canInsertItemIntoOutputSlot(result.getItem().getDefaultInstance());

    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() >= this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count;
    }


    private boolean canInsertIntoOutputSlot() {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }


    
}
