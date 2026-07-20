package com.artur114.bananalib.mc.nbt.auto;

import com.artur114.bananalib.mc.nbt.auto.meta.ClassNBTMeta;
import com.artur114.bananalib.mc.nbt.auto.meta.FieldNBTMeta;
import com.artur114.bananalib.mc.nbt.auto.meta.IFieldNBTMeta;
import com.artur114.bananalib.mc.nbt.auto.meta.NBTEntryType;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AutoNBTMetaBuilder {
    private static final ConcurrentHashMap<Class<?>, ClassNBTMeta> cache = new ConcurrentHashMap<>();

    public static ClassNBTMeta metaFor(Class<?> clazz) {
        ClassNBTMeta meta = cache.get(clazz);
        if (meta == null) {
            meta = computeMeta(clazz);
            cache.put(clazz, meta);
        }
        return meta;
    }

    private static ClassNBTMeta computeMeta(Class<?> clazz) {
        boolean isContainer = clazz.isAnnotationPresent(AutoNBTContainer.class);
        List<IFieldNBTMeta> fields = new ArrayList<>();
        Set<String> names = new HashSet<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (!checkMods(field)) {
                continue;
            }
            if (field.isAnnotationPresent(AutoNBTIgnore.class)) {
                continue;
            }
            if (!isContainer && !field.isAnnotationPresent(AutoNBTEntry.class)) {
                continue;
            }
            if (!isTypeSupported(field.getType())) {
                if (!isContainer) {
                    throw new IllegalStateException("Unsupported field type: " + field.getType());
                }
                continue;
            }

            String name = nameOf(field);

            if (names.contains(name)) {
                throw new IllegalStateException("Duplicate NBT key '" + name + "' in " + clazz.getName());
            } else {
                names.add(name);
            }

            fields.add(createMeta(name, field));
        }

        Class<?> sup = clazz.getSuperclass();
        ClassNBTMeta parent = null;

        if (sup != null && sup != Object.class) {
            parent = metaFor(sup);
        }

        if (parent != null) {
            if (parent.keys().stream().anyMatch(names::contains)) {
                throw new IllegalStateException("Duplicate NBT key in " + clazz.getName());
            } else {
                names.addAll(parent.keys());
            }
        }

        return new ClassNBTMeta(fields, names, parent);
    }

    private static IFieldNBTMeta createMeta(String name, Field field) {
        field.setAccessible(true);
        return new FieldNBTMeta(NBTEntryType.of(field.getType()), field, name);
    }

    private static boolean checkMods(Field field) {
        return !Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers());
    }

    private static String nameOf(Field field) {
        AutoNBTEntry ann = field.getAnnotation(AutoNBTEntry.class);

        if (ann != null && !ann.value().isEmpty()) {
            return ann.value();
        } else {
            return field.getName();
        }
    }

    private static boolean isTypeSupported(Class<?> clazz) {
        return NBTEntryType.of(clazz) != null;
    }
}
