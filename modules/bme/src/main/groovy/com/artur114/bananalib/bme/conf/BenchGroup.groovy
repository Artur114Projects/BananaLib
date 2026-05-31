package com.artur114.bananalib.bme.conf

import com.artur114.bananalib.bme.BenchEngine
import com.artur114.bananalib.bme.BenchEntry
import com.artur114.bananalib.bme.BlackHole
import org.gradle.api.Action

class BenchGroup {
    private List<BenchEntry> tests = new ArrayList<>()
    private Action<Object[]> beforeEach = null
    private Closure<List> setup = null
    final String name

    BenchGroup(String name) {
        this.name = name
    }

    void setup(Closure<List> c) {
        this.setup = c
    }

    void beforeEach(Action<Object[]> c) {
        this.beforeEach = c
    }

    void test(String name, Action<BlackHole> c) {
        Action<Object[]> beforeEach = this.beforeEach == null ? {null} : this.beforeEach
        Closure<List> setup = this.setup == null ? {[]} : this.setup
        this.tests.add(new BenchEntry(setup, beforeEach, c, this.name, name))
    }

    List<BenchEntry> getTests() {
        return this.tests
    }
}
