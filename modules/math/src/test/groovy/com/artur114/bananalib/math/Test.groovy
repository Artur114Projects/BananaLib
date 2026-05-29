package com.artur114.bananalib.math

import com.artur114.bananalib.math.m2d.matrix.Matrix2DM
import com.artur114.bananalib.math.m2d.matrix.Matrix2FM
import com.artur114.bananalib.math.m2d.vec.Vec2D
import com.artur114.bananalib.math.test.TestBase

class Test extends TestBase {
    static void main(String[] args) {
        new Test().start()

        println("test start")
        long time = System.nanoTime()
        println new Test().start()
        println "mul count: ${512_000_000 * 2}"
        println "is took ${(System.nanoTime() - time) / 1_000_000}ms"
    }

    def start() {
        def matrix = identityMatrix()
        def m = randomMatrix()
        def mInv = matrix.copy().mul(m).invert()

        multiTest(512_000_000, ({
            matrix.mul(m)
            matrix.mul(mInv)
        } as Closure<Void>))

        return matrix
    }

    def randomMatrix() {
        def m = identityMatrix()
        if (rand.nextDouble() < 0.5) {
            m.translate(randInt(20) + 30, randInt(20) + 30)
            m.rotate(this.rand.nextFloat() * 300.0F + 60 as float)
            m.scale(randomPoint(4))
        } else {
            m.rotate(this.rand.nextFloat() * 300.0F + 60 as float)
            m.translate(randInt(20) + 30, randInt(20) + 30)
            m.scale(randomPoint(4))
        }
        return m
    }

    Vec2D randomPoint() {
        randomPoint(100)
    }

    Vec2D randomPoint(double size) {
        if (rand.nextFloat() < 0.5) {
            return new Vec2D(randDouble(1, size), randDouble(1, size))
        } else {
            return new Vec2D(randDouble(-size, -1), randDouble(-size, -1))
        }
    }

    def identityMatrix() {
        new Matrix2FM().setIdentity()
    }
}
