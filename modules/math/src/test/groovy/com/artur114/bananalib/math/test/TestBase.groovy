package com.artur114.bananalib.math.test

import com.artur114.bananalib.math.BananaMath

class TestBase {
    public Random rand = new Random(0xBADCEFFE)

    def multiTest(int count, Closure<Void> closure) {
        for (i in 1..count) {
            closure.call(i)
        }
    }

    def multiTest(Closure<Void> closure) {
        for (i in 1..20) {
            closure.call(i)
        }
    }

    def randInt(int bound) {
        return this.rand.nextInt(bound * 2) - bound
    }

    def randDouble(double min, double max) {
        return this.rand.nextDouble() * (max - min) + min
    }

    float randFloat(float min, float max) {
        return this.rand.nextFloat() * (max - min) + min
    }

    def randInt(int bound, List exclude) {
        Set checked = new HashSet()
        int ret = randInt(bound)
        while (checked.size() < bound * 2 && exclude.contains(ret)) {
            checked.add(ret = randInt(bound))
        }
        if (checked.size() >= bound * 2) {
            throw new IllegalArgumentException();
        }
        return ret
    }

    def equalsEps(double d0, double d1, double eps) {
        return Math.abs(d0 - d1) <= eps
    }

    def equalsEps(double d0, double d1) {
        return equalsEps(d0, d1, BananaMath.DOUBLE_EPS)
    }

    def equalsEps(float d0, float d1, float eps) {
        return Math.abs(d0 - d1) <= eps
    }

    def equalsEps(float d0, float d1) {
        return equalsEps(d0, d1, BananaMath.FLOAT_EPS)
    }
}
