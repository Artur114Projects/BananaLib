package com.artur114.bananalib.mc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Interface representing the ability to load from nbt
 *
 * @author Artur114
 * @since 1.0
 * @see IWriteToNBT
 */
public interface IReadFromNBT {
    /**
     * Reads the state of an object from the specified {@link NBTTagCompound}.
     *
     * @param nbt data source
      * @see IWriteToNBT#writeToNBT(NBTTagCompound)
     */
    void readFromNBT(@NotNull NBTTagCompound nbt);

    /**
     * An overload of {@link #instantiateNBTList(NBTTagList, Supplier, Supplier)} returning {@code ArrayList} as a collection
     * @param list instance of {@link NBTTagList} from which the data will be read
     * @param creator Factory providing implementations of {@link IReadFromNBT} of type {@code <T>}
     * @return A new {@link ArrayList} instance containing deserialized objects
     * @param <T> type of implementation {@link IReadFromNBT} created by {@code creator}
     * @see #instantiateNBTList(NBTTagList, Supplier, Supplier)
     */
    static <T extends IReadFromNBT> List<T> instantiateNBTList(@NotNull NBTTagList list, @NotNull Supplier<T> creator) {
        return instantiateNBTList(list, creator, ArrayList::new);
    }

    /**
     * Receives instances through {@code creator} and loads objects implementing {@link IReadFromNBT} from the passed {@link NBTTagList} into the collection created by {@code listCreator}.
     * {@code list.getTagType()} should return {@code 10} ({@link NBTTagCompound}).
     * {@code creator} should not return {@code null}.
     * {@code listCreator} should not return {@code null}.
     * @param list instance of {@link NBTTagList} from which the data will be read
     * @param creator Factory providing implementations of {@link IReadFromNBT} of type {@code <T>}
     * @param listCreator Factory providing the returned collection
     * @return A new collection created by {@code listCreator} containing the deserialized objects. The order of the elements is determined by the implementation of the collection created by {@code listCreator}.
     * @param <T> type of implementation {@link IReadFromNBT} created by {@code creator}
     * @param <L> type of return collection
     * @throws NullPointerException if {@code list}, {@code creator}, {@code listCreator} are null, or {@code creator}, {@code listCreator} returned null
     * @throws IllegalArgumentException if type of {@code list} is not {@code 10} ({@link NBTTagCompound})
     * @see IWriteToNBT#writeToNBTList(Iterable)
     */
    static <T extends IReadFromNBT, L extends Collection<T>> L instantiateNBTList(@NotNull NBTTagList list, @NotNull Supplier<T> creator, @NotNull Supplier<L> listCreator) {
        Objects.requireNonNull(listCreator, "listCreator is null");
        Objects.requireNonNull(creator, "creator is null");
        Objects.requireNonNull(list, "list is null");
        if (list.getTagType() != 10) {
            throw new IllegalArgumentException("Invalid NBTTagList type, expect 10, got " + list.getTagType());
        }
        L ret = Objects.requireNonNull(listCreator.get(), "listCreator returned null");
        for (int i = 0; i != list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            T read = Objects.requireNonNull(creator.get(), "creator returned null");
            read.readFromNBT(tag);
            ret.add(read);
        }
        return ret;
    }
}
