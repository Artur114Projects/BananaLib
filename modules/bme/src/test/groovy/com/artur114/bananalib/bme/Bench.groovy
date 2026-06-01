package com.artur114.bananalib.bme

import com.artur114.bananalib.math.m3d.matrix.IMatrix3F
import com.artur114.bananalib.math.m3d.matrix.Matrix3F
import com.artur114.bananalib.math.m3d.matrix.Matrix3FM
import groovy.transform.CompileStatic
import org.joml.Matrix4f


@CompileStatic
class Bench {
    static void main(String[] args) {
        BananaBench.run {
            iterations 8000000
            warmup 6000000
            repeats 6
            runs 3

            bench("Matrix3FM") {
                setup {
                    def m0Orig = new Matrix3F().rotate(234, 1, 0 ,0).translate(1, 4, 7).scale(1, 2, 1)
                    def m0 = new Matrix3FM().rotate(234, 1, 0 ,0).translate(1, 4, 7).scale(1, 2, 1)
                    def m1 = new Matrix3FM().rotate(134, 1, 2 ,0).translate(2, 8, 7).scale(2, 3, 1)

                    [m0Orig, m0, m1]
                }

                beforeEach {
                    ((Matrix3FM) it[1]).set((IMatrix3F) it[0])
                }

                test("invert") {
                    it.consume(((Matrix3FM) it.data[1]).invert())
                }

                test("mul") {
                    it.consume(((Matrix3FM) it.data[1]).mul((Matrix3FM) it.data[2]))
                }

                test("localTranslate") {
                    it.consume(((Matrix3FM) it.data[1]).localTranslate(25, -82, 34))
                }

                test("compositeTransform") {
                    it.consume(((Matrix3FM) it.data[1]).localTranslate(25, -82, 34).localScale(3).localRotateX(334))
                }
            }

            bench("Matrix4f") {
                setup {
                    def m0Orig = new Matrix4f().rotate(234, 1, 0 ,0).translate(1, 4, 7).scale(1, 2, 1)
                    def m0 = new Matrix4f().rotate(234, 1, 0 ,0).translate(1, 4, 7).scale(1, 2, 1)
                    def m1 = new Matrix4f().rotate(134, 1, 2 ,0).translate(2, 8, 7).scale(2, 3, 1)
                    def out = new Matrix4f()

                    [m0Orig, m0, m1, out]
                }

                beforeEach {
                    ((Matrix4f) it[1]).set(((Matrix4f) it[0]))
                }

                test("invert") {
                    it.consume(((Matrix4f) it.data[1]).invert(((Matrix4f) it.data[3])))
                }

                test("mul") {
                    it.consume((((Matrix4f) it.data[1]).mul((Matrix4f) it.data[2], (Matrix4f) it.data[3])))
                }

                test("translate") {
                    it.consume(((Matrix4f) it.data[1]).translate(25, -82, 34))
                }

                test("compositeTransform") {
                    it.consume(((Matrix4f) it.data[1]).translate(25, -82, 34).scale(3, 3, 3).rotateX(334))
                }
            }
        }
    }
}
