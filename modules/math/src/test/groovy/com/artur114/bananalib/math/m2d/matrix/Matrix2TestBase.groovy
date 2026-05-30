package com.artur114.bananalib.math.m2d.matrix

import com.artur114.bananalib.math.m2d.vec.IVec2D
import com.artur114.bananalib.math.m2d.vec.Vec2D
import com.artur114.bananalib.math.test.TestBase

abstract class Matrix2TestBase<T> extends TestBase {
    T randomMatrix() {
        def m = identityMatrix()
        if (rand.nextDouble() < 0.5) {
            m.translate(randInt(40), randInt(40))
            m.rotate((float) (this.rand.nextFloat() * 360.0F))
            m.scale(randomPoint(4))
        } else {
            m.rotate((float) (this.rand.nextFloat() * 360.0F))
            m.translate(randInt(40), randInt(40))
            m.scale(randomPoint(4))
        }
        return m
    }

    def withRandMatrix(Closure<Void> cl) {
        IVec2D scale = randomPoint(4)
        IVec2D tr = randomPoint()
        float rot = (float) (this.rand.nextFloat() * 360.0F)
        cl.call(identityMatrix().translate(tr.x(), tr.y()).rotate(rot).scale(scale.x(), scale.y()), tr, rot, scale)
    }

    Vec2D randomPoint() {
        randomPoint(100)
    }

    Vec2D randomPoint(double size) {
        new Vec2D(randDouble(-size, size), randDouble(-size, size))
    }

    abstract T identityMatrix();
}
