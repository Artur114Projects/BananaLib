package com.artur114.bananalib.bme

import org.gradle.api.Action

final class BananaBench {
    static void run(Action<? extends BenchEngine> action) {
        BenchEngine engine = new BenchEngine();
        action.execute(engine)
        engine.start()
    }

    static void run(@DelegatesTo(value = BenchEngine, strategy = Closure.DELEGATE_FIRST) Closure c) {
        BenchEngine engine = new BenchEngine()

        c.delegate = engine
        c.resolveStrategy = Closure.DELEGATE_FIRST

        c.call()

        engine.start()
    }

    static void runAsync(Action<? extends BenchEngine> action) {
        BenchEngine engine = new BenchEngine();
        action.execute(engine)
        engine.startAsync()
    }

    static void runAsync(@DelegatesTo(value = BenchEngine, strategy = Closure.DELEGATE_FIRST) Closure c) {
        BenchEngine engine = new BenchEngine()

        c.delegate = engine
        c.resolveStrategy = Closure.DELEGATE_FIRST

        c.call()

        engine.startAsync()
    }
}
