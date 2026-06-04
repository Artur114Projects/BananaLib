package com.artur114.bananalib.asm.internal;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;

public class OpValue<T> {
    private static final OpValue<?> empty = new OpValue<>(null);
    private final T value;

    private OpValue(T value) {
        this.value = value;
    }

    public T get() {
        if (this.value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (this.value != null) {
            consumer.accept(this.value);
        }
    }

    public <R> R ifPFun(Function<? super T, R> func, R def) {
        if (this.value != null) {
            return func.apply(this.value);
        }
        return def;
    }

    public boolean ifPEquals(Object obj, boolean def) {
        if (this.value != null) {
            return this.value.equals(obj);
        }
        return def;
    }

    public boolean ifPEquals(Object obj) {
        return this.ifPEquals(obj, true);
    }

    public static <V> OpValue<V> of(V v) {
        return v == null ? empty() : new OpValue<>(v);
    }

    @SuppressWarnings("unchecked")
    public static <V> OpValue<V> empty() {
        return (OpValue<V>) empty;
    }

    public static boolean equals(OpValue<?>[] opValues, Object... values) {
        if (opValues.length != values.length) return false;
        for (int i = 0; i != opValues.length; i++) {
            if (!opValues[i].ifPEquals(values[i])) {
                return false;
            }
        }
        return true;
    }
}
