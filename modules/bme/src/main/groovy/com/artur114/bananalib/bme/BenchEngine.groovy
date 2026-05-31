package com.artur114.bananalib.bme

import com.artur114.bananalib.bme.conf.BenchGroup
import groovy.transform.CompileStatic

@CompileStatic
class BenchEngine implements Runnable {
    private Map<String, List<BenchEntry>> tests = new HashMap<>()
    private int iterations = 1000000
    private int repeats = 4
    private int warmup = 1000000
    private int runs = 2

    void runs(int count) {
        this.runs = count
    }

    void warmup(int count) {
        this.warmup = count
    }

    void iterations(int count) {
        this.iterations = count
    }

    void repeats(int count) {
        this.repeats = count
    }

    void bench(String name, @DelegatesTo(value = BenchGroup, strategy = Closure.DELEGATE_FIRST)  Closure c) {
        BenchGroup group = new BenchGroup(name)
        c.delegate = group
        c.resolveStrategy = Closure.DELEGATE_FIRST
        c.call(group)

        List<BenchEntry> l = this.tests.computeIfAbsent(group.name, {new ArrayList<>()})
        l.addAll(group.tests)
    }

    @Override
    void run() {
        Map<BenchEntry, Double> resultsMin = new HashMap<>()
        Map<BenchEntry, Double> resultsMax = new HashMap<>()

        Map<BenchEntry, Double> resultsAv = new HashMap<>()
        for (f in 1..this.runs) {
            println "------------------------------RUN(${f})------------------------------"
            for (String group : this.tests.keySet()) {
                List<BenchEntry> t = this.tests.get(group)
                println "# Benchmarking group ${group}"
                println()
                for (BenchEntry entry : t) {
                    println "# Benchmarking ${group}:${entry.name}"

                    for (i in 1..this.repeats) {
                        println "# Warmup iteration ${i}"

                        entry.run(this.warmup)
                    }
                    println()


                    for (i in 1..this.repeats) {
                        printf "Iteration ${i}"
                        long time = entry.run(this.iterations)
                        println(": ${time / this.iterations}ns/op")
                        resultsAv.put(entry, resultsAv.getOrDefault(entry, 0.0D) + time)

                        double max = resultsMax.getOrDefault(entry, 0.0D)
                        resultsMax.put(entry, max > time ? max : time)

                        double min = resultsMin.getOrDefault(entry, Double.MAX_VALUE)
                        resultsMin.put(entry, min < time ? min : time)
                    }

                    println()
                }

                println()
            }
        }

        println "------------------------------RESULTS------------------------------"

        for (String group : this.tests.keySet()) {
            List<BenchEntry> t = this.tests.get(group)
            println group

            for (BenchEntry entry : t) {
                println "    .${entry.name} avg: ${(double) (resultsAv.get(entry) / (double) (this.iterations * this.runs * this.repeats))}ns/op"
                println "    .${entry.name} min: ${(double) (resultsMin.get(entry) / this.iterations)}ns/op"
                println "    .${entry.name} max: ${(double) (resultsMax.get(entry) / this.iterations)}ns/op"
                println "    .${entry.name} stable: ${(double) (resultsMin.get(entry) / resultsMax.get(entry)) * 100.0D}%"
            }

            println()
        }
    }

    void start() {
        this.run()
    }

    void startAsync() {
        new Thread(this).start()
    }
}
