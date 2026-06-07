package com.artur114.bananalib.asm.util;

import org.objectweb.asm.commons.Remapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BananaRemapper extends Remapper {
    private final Map<String, String> mapHistory;
    private final Map<String, String> mapping;
    public boolean doIgnoreOwner = false;
    public boolean doIgnoreDesc = false;

    public BananaRemapper(Map<String, String> mapping) {
        this.mapping = new HashMap<>(mapping);
        this.mapHistory = new HashMap<>();
    }

    public BananaRemapper(String oldName, String newName) {
        this.mapping = Collections.singletonMap(oldName, newName);
        this.mapHistory = new HashMap<>();
    }

    public BananaRemapper unoRemapper() {
        Map<String, String> ret = new HashMap<>();
        this.mapHistory.forEach((k, v) -> ret.put(v, k));
        return new BananaRemapper(ret);
    }

    public BananaRemapper reverse() {
        Map<String, String> ret = new HashMap<>();
        this.mapping.forEach((k, v) -> ret.put(v, k));
        return new BananaRemapper(ret);
    }

    public Map<String, String> mapHistory() {
        return Collections.unmodifiableMap(mapHistory);
    }

    public void clearHistory() {
        this.mapHistory.clear();
    }

    @Override
    public String mapMethodName(String owner, String name, String desc) {
        String s = map(this.wrapOwner(owner) + name + this.wrapDesc(desc));
        if (s != null) this.mapHistory.put(owner + "." + name + desc, s);
        return s == null ? name : s;
    }

    @Override
    public String mapInvokeDynamicMethodName(String name, String desc) {
        String s = map('.' + name + this.wrapDesc(desc));
        if (s != null) this.mapHistory.put("." + name + desc, s);
        return s == null ? name : s;
    }

    @Override
    public String mapFieldName(String owner, String name, String desc) {
        String s = map(this.wrapOwner(owner) + name);
        if (s != null) this.mapHistory.put(owner + "." + name, s);
        return s == null ? name : s;
    }

    @Override
    public String map(String key) {
        return mapping.get(key);
    }

    private String wrapOwner(String owner) {
        return this.doIgnoreOwner ? "" : owner + '.';
    }

    private String wrapDesc(String desc) {
        return this.doIgnoreDesc ? "" : desc;
    }
}
