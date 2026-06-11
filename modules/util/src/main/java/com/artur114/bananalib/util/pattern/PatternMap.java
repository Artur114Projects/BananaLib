package com.artur114.bananalib.util.pattern;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PatternMap<V> {
    private final Map<NamespacePath.Part, V> shapelessDomains = new HashMap<>();
    private final Map<NamespacePath.Part, V> shapelessPaths = new HashMap<>();
    private final Map<NamespacePath, V> map = new HashMap<>();
    private V absoluteValue = null;

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean matchesKey(Object key) {
        if (!(key instanceof NamespacePath)) {
            return false;
        }

        NamespacePath space = (NamespacePath) key;
        NamespacePath.Part path = space.pathLocation();
        NamespacePath.Part domain = space.domainLocation();

        boolean ret = this.map.containsKey(space);

        ret |= this.shapelessDomains.containsKey(path);
        ret |= this.shapelessPaths.containsKey(domain);

        return ret;
    }

    public boolean containsValue(V value) {
        return this.map.containsValue(value) ||
                this.shapelessDomains.containsValue(value) ||
                this.shapelessPaths.containsValue(value) ||
                Objects.equals(this.absoluteValue, value);
    }

    public V get(NamespacePath space) {
        V value = this.map.get(space);
        if (value != null) {
            return value;
        }

        value = this.shapelessPaths.get(space.domainLocation());
        if (value != null) {
            return value;
        }

        value = this.shapelessDomains.get(space.pathLocation());
        if (value != null) {
            return value;
        }

        return this.absoluteValue;
    }

    public List<V> getAll(NamespacePath space) {
        List<V> list = new ArrayList<>();

        this.map.forEach((k, v) -> {
            if (space.matches(k)) {
                list.add(v);
            }
        });

        return list;
    }

    public V put(NamespacePath space, V value) {
        if (space.isAbsoluteShapeless()) {
            this.absoluteValue = value;
        }

        V obj = this.map.put(space, value);

        if (space.isAbsoluteShapeless()) {
            return obj;
        }

        NamespacePath.Part path = space.pathLocation();
        NamespacePath.Part domain = space.domainLocation();

        if (domain.isShapeless()) {
            this.shapelessDomains.put(path, value);
        }

        if (path.isShapeless()) {
            this.shapelessPaths.put(domain, value);
        }

        return obj;
    }

    public V remove(NamespacePath space) {
        V obj = this.map.remove(space);

        if (obj != null) {
            if (space.domainLocation().isShapeless()) {
                this.shapelessDomains.remove(space.pathLocation());
            }
            if (space.pathLocation().isShapeless()) {
                this.shapelessPaths.remove(space.domainLocation());
            }
        }

        if (space.isAbsoluteShapeless()) {
            this.absoluteValue = null;
        }

        return obj;
    }

    public void removeObject(V obj) {
        this.map.values().removeIf(v -> v == obj);
        this.shapelessDomains.values().removeIf(v -> v == obj);
        this.shapelessPaths.values().removeIf(v -> v == obj);
    }

    public void putAll(Map<? extends NamespacePath, ? extends V> m) {
        m.forEach(this::put);
    }

    public void clear() {
        this.shapelessDomains.clear();
        this.shapelessPaths.clear();
        this.map.clear();
    }

    public @NotNull Set<NamespacePath> keySet() {
        return this.map.keySet();
    }

    public @NotNull Collection<V> values() {
        return this.map.values();
    }

    public @NotNull Set<Map.Entry<NamespacePath, V>> entrySet() {
        return this.map.entrySet();
    }
}
