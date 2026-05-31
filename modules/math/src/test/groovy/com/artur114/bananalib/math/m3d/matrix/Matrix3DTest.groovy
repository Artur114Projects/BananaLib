package com.artur114.bananalib.math.m3d.matrix

import com.artur114.bananalib.math.m2d.matrix.Matrix2D
import com.artur114.bananalib.math.m2d.vec.IVec2D
import com.artur114.bananalib.math.m3d.vec.IVec3D
import com.artur114.bananalib.math.test.TestBase
import org.junit.jupiter.api.Test

class Matrix3DTest extends TestBase {

    @Test
    void "matrix2 rotate test"() {
        multiTest(80) {
            def m3 = this.identityMatrix
            def m2 = Matrix2D.IDENTITY
            IVec2D vec = randVec2d
            double rot = randDegrees()

            assert m2.rotate(rot).transform(vec).equalsEps(m3.rotateZ(rot).transform(vec))
        }
    }


    @Test
    void "vec and matrix rotate test"() {
        multiTest {
            def m = this.identityMatrix
            IVec3D vec = randVec3d
            double rot = randDegrees()

            assert m.rotateX(rot).transform(vec).equalsEps(vec.rotateX(rot))
            assert m.rotateY(rot).transform(vec).equalsEps(vec.rotateY(rot))
            assert m.rotateZ(rot).transform(vec).equalsEps(vec.rotateZ(rot))
        }
    }

    @Test
    void "full rotate test"() {
        multiTest {
            def axis = randVec3d
            assert identityMatrix.rotate(360, axis).equalsEps(identityMatrix)
        }
    }

    @Test
    void "zero rotate test"() {
        multiTest {
            def m = randomMatrix

            assert m.rotateX(0).equalsEps(m)
            assert m.rotateY(0).equalsEps(m)
            assert m.rotateZ(0).equalsEps(m)

            assert m.rotate(0, randVec3d).equalsEps(m)
        }
    }

    @Test
    void "rotate test"() {
        multiTest {
            def m = this.identityMatrix
            double rot = randDegrees()

            assert m.rotate(rot, 1, 0, 0).equalsEps(m.rotateX(rot))
            assert m.rotate(rot, 0, 1, 0).equalsEps(m.rotateY(rot))
            assert m.rotate(rot, 0, 0, 1).equalsEps(m.rotateZ(rot))
        }
    }

    @Test
    void "invert matrix test"() {
        multiTest {
            def m = this.randomMatrix
            assert m.mul(m.invert()).equalsEps(identityMatrix)
        }
    }

    @Test
    void "invert invert matrix test"() {
        multiTest {
            def m = this.randomMatrix
            assert m.equalsEps(m.invert().invert())
        }
    }

    @Test
    void "mul associativity test"() {
        multiTest {
            def a = this.randomMatrix
            def b = this.randomMatrix
            assert a.mul(b).equalsEps(b.mulPost(a))
        }
    }

    def getRandomMatrix() {
        def m = this.identityMatrix
        if (this.rand.nextDouble() < 0.5) {
            m = m.translate(this.randVec3d)
            m = m.rotate(randDouble(-360, 360), this.randVec3d)
            int d = this.rand.nextBoolean() ? -1 : 1
            m = m.scale(this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d)
        } else {
            m = m.rotate(randDouble(-360, 360), this.randVec3d)
            m = m.translate(this.randVec3d)
            int d = this.rand.nextBoolean() ? -1 : 1
            m = m.scale(this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d)
        }
        return m
    }

    def getIdentityMatrix() {
        return Matrix3D.IDENTITY
    }
}
