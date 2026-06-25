package com.artur114.bananalib.mc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 * Interface representing the ability to automatically write to NBT
 *
 * @author Artur114
 * @since 1.1
 * @see IAutoReadFromNBT
 */
public interface IAutoWriteToNBT extends IWriteToNBT {
    /**
     * The default method that delegates to {@link BananaAutoNBT#writeToNBT(Object, NBTTagCompound)}
     * @param nbt data source
     * @see BananaAutoNBT#writeToNBT(Object, NBTTagCompound)
     */
    @Override
    default @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        return BananaAutoNBT.writeToNBT(this, nbt);
    }
}
