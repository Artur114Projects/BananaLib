package com.artur114.bananalib.mc.nbt.auto.meta;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ClassNBTMeta {
    private final List<IFieldNBTMeta> fieldsRec;
    private final List<IFieldNBTMeta> fields;
    private final ClassNBTMeta parent;
    private final Set<String> keys;

    public ClassNBTMeta(List<IFieldNBTMeta> fields, Set<String> keys, @Nullable ClassNBTMeta parent) {
        this.keys = Collections.unmodifiableSet(new HashSet<>(keys));
        this.fields = Collections.unmodifiableList(fields);
        this.parent = parent;

        List<IFieldNBTMeta> req = new ArrayList<>();
        if (parent != null) req.addAll(parent.fieldsRec());
        req.addAll(fields);
        this.fieldsRec = Collections.unmodifiableList(req);
    }

    public Set<String> keys() {
        return this.keys;
    }

    public List<IFieldNBTMeta> fieldsRec() {
        return this.fieldsRec;
    }

    public List<IFieldNBTMeta> fields() {
        return this.fields;
    }

    public ClassNBTMeta parent() {
        return this.parent;
    }
}
