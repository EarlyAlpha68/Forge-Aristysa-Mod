package net.earlyalpha.aristysa.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCyberwareTierProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerCyberwareTier> PLAYER_CYBERWARE_TIER = CapabilityManager.get(new CapabilityToken<PlayerCyberwareTier>() { });

    private PlayerCyberwareTier tier = null;
    private final LazyOptional<PlayerCyberwareTier> optional = LazyOptional.of(this::createPlayerCyberwareTier);

    private @NotNull PlayerCyberwareTier createPlayerCyberwareTier() {
        if (this.tier == null) {
            this.tier = new PlayerCyberwareTier();
        }
        return this.tier;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction direction) {
        if(cap == PLAYER_CYBERWARE_TIER) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerCyberwareTier().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerCyberwareTier().loadNBTData(nbt);

    }
}
