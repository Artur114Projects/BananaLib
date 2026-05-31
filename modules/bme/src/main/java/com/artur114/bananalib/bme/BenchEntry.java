package com.artur114.bananalib.bme;

import groovy.lang.Closure;
import org.gradle.api.Action;

import java.util.List;

public class BenchEntry {
    private final Action<Object[]> beforeEach;
    private final Action<BlackHole> bench;
    private final Closure<List<?>> setup;
    public final String group;
    public final String name;

    public BenchEntry(Closure<List<?>> setup, Action<Object[]> beforeEach, Action<BlackHole> bench, String group, String name) {
        this.beforeEach = beforeEach;
        this.setup = setup;
        this.bench = bench;
        this.group = group;
        this.name = name;
    }

    public long run(int count) {
        Object[] data = this.setup.call().toArray(new Object[0]);
        BlackHole blackHole = new BlackHole(data);
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            this.beforeEach.execute(data);
            this.bench.execute(blackHole);
        }
        return System.nanoTime() - start;
    }
}
