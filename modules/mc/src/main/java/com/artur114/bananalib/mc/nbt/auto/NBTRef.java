package com.artur114.bananalib.mc.nbt.auto;

import com.artur114.bananalib.mc.nbt.IReadFromNBT;
import com.artur114.bananalib.mc.nbt.IWriteToNBT;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

public class NBTRef<T extends IReadFromNBT & IWriteToNBT> {
    private final Supplier<T> factory;
    private T value = null;

    public NBTRef(Supplier<T> factory) {
        this.factory = Objects.requireNonNull(factory, "factory is null");
    }

    public @NotNull T val() {
        if (this.value == null) {
            this.value = Objects.requireNonNull(this.factory.get(), "factory return null!");
        }

        return this.value;
    }

    public boolean isInitialised() {
        return this.value != null;
    }

    public NBTTagCompound ser() {
        return this.val().writeToNBT(new NBTTagCompound());
    }

    public void des(NBTTagCompound data) {
        this.val().readFromNBT(data);
    }
}
