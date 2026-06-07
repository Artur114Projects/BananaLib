package com.artur114.bananalib.mc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public interface IReadFromNBT {
    void readFromNBT(NBTTagCompound nbt);

    static <T extends IReadFromNBT> List<T> instantiateNBTList(NBTTagList list, Supplier<T> creator) {
        Objects.requireNonNull(creator);
        Objects.requireNonNull(list);
        List<T> ret = new ArrayList<>(list.tagCount());
        for (int i = 0; i != list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            T read = creator.get();
            read.readFromNBT(tag);
            ret.add(read);
        }
        return ret;
    }
}
