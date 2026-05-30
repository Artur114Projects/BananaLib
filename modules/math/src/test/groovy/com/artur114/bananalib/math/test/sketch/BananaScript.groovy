package com.artur114.bananalib.math.test.sketch

import com.artur114.bananalib.math.m2d.matrix.Matrix2D

abstract class BananaScript extends Script {
    Vec3DG vec(Number x, Number y, Number z) {
        new Vec3DG(x.doubleValue(), y.doubleValue(), z.doubleValue())
    }

    Matrix2D mat() {
        Matrix2D.IDENTITY
    }
}