package com.artur114.bananalib.mc.nbt.auto.meta;

import net.minecraft.nbt.NBTTagCompound;

public interface IFieldNBTMeta {
    void write(Object owner, NBTTagCompound data);
    void read(Object owner, NBTTagCompound data);
}
