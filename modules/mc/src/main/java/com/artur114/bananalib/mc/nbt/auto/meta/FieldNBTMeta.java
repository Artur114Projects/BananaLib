package com.artur114.bananalib.mc.nbt.auto.meta;

import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class FieldNBTMeta implements IFieldNBTMeta {
    private final NBTEntryType type;
    private final Field field;
    private final String key;

    public FieldNBTMeta(NBTEntryType type, Field field, String key) {
        this.field = field;
        this.type = type;
        this.key = key;
    }

    @Override
    public void write(Object owner, NBTTagCompound data) {
        try {
            this.type.write(data, this.key, this.field.get(owner));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void read(Object owner, NBTTagCompound data) {
        try {
            this.field.set(owner, this.type.read(data, this.key));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
