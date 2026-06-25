package com.artur114.bananalib.mc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

/**
 * Interface representing the ability to automatically read from NBT
 *
 * @author Artur114
 * @since 1.1
 * @see IAutoWriteToNBT
 */
public interface IAutoReadFromNBT extends IReadFromNBT {
    /**
     * The default method that delegates to {@link BananaAutoNBT#readFromNBT(Object, NBTTagCompound)}
     * @param nbt data source
     * @see BananaAutoNBT#readFromNBT(Object, NBTTagCompound)
     */
    @Override
    default void readFromNBT(@NotNull NBTTagCompound nbt) {
        BananaAutoNBT.readFromNBT(this, nbt);
    }
}
