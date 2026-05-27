package com.artur114.bananalib.math.internal;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ThreadLocalPool<T> {
    private final ThreadLocal<int[]> poolCursor;
    private final Predicate<T> checkReleased;
    private final ThreadLocal<T[]> pool;
    private final Function<T, T> release;
    private final Function<T, T> obtain;
    private final Supplier<T> create;
    private final StackGrowMode mode;

    public ThreadLocalPool(T[] arr, Supplier<T> createObj, Function<T, T> release, Function<T, T> obtain, Predicate<T> checkReleased) {
        this(arr, createObj, release, obtain, checkReleased, StackGrowMode.EXPONENT);
    }

    public ThreadLocalPool(T[] arr, Supplier<T> createObj, Function<T, T> release, Function<T, T> obtain, Predicate<T> checkReleased, StackGrowMode mode) {
        this.checkReleased = checkReleased;
        this.create = createObj;
        this.release = release;
        this.obtain = obtain;
        this.mode = mode;

        this.pool = ThreadLocal.withInitial(() -> Arrays.copyOf(arr, arr.length));
        this.poolCursor = ThreadLocal.withInitial(() -> new int[] {-1});
    }

    public T obtain() {
        T[] pol = this.pool.get();
        int[] polCursorArr = this.poolCursor.get();
        int polCursor = polCursorArr[0];

        if (polCursor < 0) {
            return this.create.get();
        }

        T obj = pol[polCursor];
        pol[polCursor--] = null;
        polCursorArr[0] = polCursor;

        if (obj == null) {
            return this.create.get();
        }

        return this.obtain.apply(obj);
    }

    public void release(T obj) {
        if (obj == null) {
            return;
        }
        if (this.checkReleased.test(obj)) {
            throw new IllegalArgumentException("Double release!");
        }

        T[] pol = this.pool.get();
        int[] polCursorArr = this.poolCursor.get();
        int polCursor = polCursorArr[0];

        if (polCursor + 1 >= pol.length) {
            pol = Arrays.copyOf(pol, this.mode.grow(pol.length));
            this.pool.set(pol);
        }

        pol[++polCursor] = this.release.apply(obj);
        polCursorArr[0] = polCursor;
    }
}
