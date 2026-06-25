package com.artur114.bananalib.mc.nbt.auto.meta;

import com.artur114.bananalib.util.func.TriConsumer;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public enum NBTEntryType {
    TAG_COMPOUND(NBTTagCompound.class, NBTTagCompound::setTag, NBTTagCompound::getCompoundTag),
    BYTE_ARR(byte[].class, NBTTagCompound::setByteArray, NBTTagCompound::getByteArray),
    INT_ARR(int[].class, NBTTagCompound::setIntArray, NBTTagCompound::getIntArray),
    BOOLEAN(boolean.class, NBTTagCompound::setBoolean, NBTTagCompound::getBoolean),
    DOUBLE(double.class, NBTTagCompound::setDouble, NBTTagCompound::getDouble),
    STRING(String.class, NBTTagCompound::setString, NBTTagCompound::getString),
    UUID(UUID.class, NBTTagCompound::setUniqueId, NBTTagCompound::getUniqueId),
    SHORT(short.class, NBTTagCompound::setShort, NBTTagCompound::getShort),
    INT(int.class, NBTTagCompound::setInteger, NBTTagCompound::getInteger),
    FLOAT(float.class, NBTTagCompound::setFloat, NBTTagCompound::getFloat),
    BYTE(byte.class, NBTTagCompound::setByte, NBTTagCompound::getByte),
    LONG(long.class, NBTTagCompound::setLong, NBTTagCompound::getLong);

    private final TriConsumer<NBTTagCompound, String, Object> writer;
    private final BiFunction<NBTTagCompound, String, Object> reader;
    private final Predicate<Class<?>> isSuitable;

    @SuppressWarnings("unchecked")
    <T> NBTEntryType(final Class<T> type, TriConsumer<NBTTagCompound, String, T> writer, BiFunction<NBTTagCompound, String, T> reader) {
        this((c) -> c == type, (TriConsumer<NBTTagCompound, String, Object>) writer, (BiFunction<NBTTagCompound, String, Object>) reader);
    }

    NBTEntryType(Predicate<Class<?>> isSuitable, TriConsumer<NBTTagCompound, String, Object> writer, BiFunction<NBTTagCompound, String, Object> reader) {
        this.isSuitable = isSuitable;
        this.reader = reader;
        this.writer = writer;
    }

    public void write(NBTTagCompound nbt, String name, Object data) {
        if (data == null) return;
        this.writer.accept(nbt, name, data);
    }

    public Object read(NBTTagCompound nbt, String name) {
        return this.reader.apply(nbt, name);
    }

    private static final Map<Class<?>, NBTEntryType> cache = new ConcurrentHashMap<>();

    public static @Nullable NBTEntryType of(Class<?> clazz) throws IllegalStateException {
        return cache.computeIfAbsent(clazz, NBTEntryType::findType);
    }

    private static @Nullable NBTEntryType findType(Class<?> clazz) throws IllegalStateException {
        NBTEntryType ret = null;

        for (NBTEntryType type : values()) {
            if (type.isSuitable.test(clazz)) {
                if (ret != null) {
                    throw new IllegalStateException("The class is suitable for multiple NBTEntryTypes! type1: " + ret + " type2:" + type + " clazz:" + clazz);
                } else {
                    ret = type;
                }
            }
        }

        return ret;
    }
}
