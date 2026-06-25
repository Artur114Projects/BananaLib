package com.artur114.bananalib.mc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Interface representing the ability to write to NBT
 *
 * @author Artur114
 * @since 1.0
 * @see IReadFromNBT
 */
public interface IWriteToNBT {

    /**
     * Writes the state of an object to the specified {@link NBTTagCompound} and returns it.
     *
     * @param nbt data output
     * @return {@code nbt} with the recorded state of the object, cannot be null!
     * @see IReadFromNBT#readFromNBT(NBTTagCompound)
     */
    @NotNull
    @Contract("_ -> _")
    NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt);

    /**
     * Write the state of objects to {@link NBTTagList} received from {@code objects}.
     * The order of tags in {@link NBTTagList} will be the same as the iterator provides them.
     * @param objects {@link Iterable} providing objects implementing {@link IWriteToNBT}
     * @return a new {@link NBTTagList} containing the serialized state of all objects from {@code objects}
     * @throws NullPointerException if {@code objects} is null or if {@link #writeToNBT(NBTTagCompound)} returned null
     * @see IReadFromNBT#instantiateNBTList(NBTTagList, Supplier, Supplier)
     */
    static NBTTagList writeToNBTList(@NotNull Iterable<? extends IWriteToNBT> objects) {
        Objects.requireNonNull(objects, "objects is null");
        NBTTagList list = new NBTTagList();

        for (IWriteToNBT obj : objects) {
            list.appendTag(Objects.requireNonNull(obj.writeToNBT(new NBTTagCompound()), "instance of IWriteToNBT returned null"));
        }

        return list;
    }
}
