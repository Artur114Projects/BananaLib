package com.artur114.bananalib.bme

import org.gradle.api.Action

class BenchEntry {
    private static volatile Object sink

    private final Action<Object[]> beforeEach
    private final Action<Object[]> bench
    private final Closure<List> setup
    final String group
    final String name

    BenchEntry(Closure<List> setup, Action<Object[]> beforeEach, Action<Object[]> bench, String group, String name) {
        this.beforeEach = beforeEach
        this.setup = setup
        this.bench = bench
        this.group = group
        this.name = name
    }

    long run(int count) {
        List data = this.setup.call()
        Object[] d = data as Object[]
        long start = System.nanoTime()
        for (int i = 0; i < count; i++) {
            beforeEach.execute(d)
            sink = bench.execute(d)
        }
        return System.nanoTime() - start
    }
}
