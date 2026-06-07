package com.artur114.bananalib.mc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Collection;

public interface IWriteToNBT {
    NBTTagCompound writeToNBT(NBTTagCompound nbt);

    static NBTTagList writeToNBTList(Iterable<? extends IWriteToNBT> objects) {
        NBTTagList list = new NBTTagList();

        for (IWriteToNBT obj : objects) {
            list.appendTag(obj.writeToNBT(new NBTTagCompound()));
        }

        return list;
    }
}
