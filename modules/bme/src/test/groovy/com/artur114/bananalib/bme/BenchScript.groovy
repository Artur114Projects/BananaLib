package com.artur114.bananalib.bme

import com.artur114.bananalib.math.m2d.matrix.Matrix2FM
import com.artur114.bananalib.math.m2d.vec.IVec2DM
import com.artur114.bananalib.math.m2d.vec.Vec2DM
import org.joml.Matrix3f
import org.joml.Vector3f

BananaBench.run {
    iterations 16000000
    warmup 6000000
    repeats 6
    runs 3

    bench("Matrix2FM") {
        setup {
            def matrixOrig = new Matrix2FM().translate(10, 20).rotate(45).scale(2, 3)
            def matrix0 = new Matrix2FM().translate(10, 20).rotate(45).scale(2, 3)
            def matrix1 = new Matrix2FM().translate(140, 9).rotate(85).scale(1, 3)
            IVec2DM vec = new Vec2DM()

            [matrixOrig, matrix0, matrix1, vec]
        }

        beforeEach {
            it[1].set(it[0])
        }

        test("transform") {
            it.consume it.data[1].transform(it.data[3])
        }

        test("mul") {
            it.consume it.data[1].mul(it.data[2])
        }
    }

    bench("Matrix3f") {
        setup {
            def out = new Matrix3f()
            def matrixOrig = new Matrix3f().rotateZ(Math.toRadians(45) as float).scale(2, 3, 1)
            def matrix0 = new Matrix3f().rotateZ(Math.toRadians(45) as float).scale(2, 3, 1)
            def matrix1 = new Matrix3f().rotateZ(Math.toRadians(76) as float).scale(4, 1, 1)
            Vector3f vector3f = new Vector3f()

            [matrixOrig, matrix0, matrix1, out, vector3f]
        }

        beforeEach {
            it[1].set(it[0])
        }

        test("transform") {
            it.consume it.data[1].transform(it.data[4])
        }

        test("mul") {
            it.consume it.data[1].mul(it.data[2], it.data[3])
        }
    }
}
