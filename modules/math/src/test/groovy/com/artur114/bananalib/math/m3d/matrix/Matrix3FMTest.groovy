package com.artur114.bananalib.math.m3d.matrix

import com.artur114.bananalib.math.BananaMath
import com.artur114.bananalib.math.m2d.matrix.Matrix2F
import com.artur114.bananalib.math.m2d.vec.IVec2D
import com.artur114.bananalib.math.m3d.vec.IVec3D
import com.artur114.bananalib.math.test.TestBase
import org.junit.jupiter.api.Test

class Matrix3FMTest  extends TestBase {
    @Override
    int defMultiCount() {
        return 1000
    }

    @Test
    void "inverse transform test"() {
        multiTest {
            def m = randomMatrix
            def v = randVec3d
            assert m.copy().invert().transform(m.copy().transform(v)).equalsEps(v, BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "identity matrix mul test"() {
        def identity = identityMatrix
        def m = randomMatrix
        assert m.copy().mul(identity).equalsEps(m)
        assert identity.copy().mul(m).equalsEps(m)
    }

    @Test
    void "rotated vector length test"() {
        multiTest {
            float rot = randDegreesF()
            def p = randVec3d
            assert equalsEps(p.length(), identityMatrix.rotateX(rot).transform(p).length(), BananaMath.FLOAT_EQUALS_EPS)
            assert equalsEps(p.length(), identityMatrix.rotateY(rot).transform(p).length(), BananaMath.FLOAT_EQUALS_EPS)
            assert equalsEps(p.length(), identityMatrix.rotateZ(rot).transform(p).length(), BananaMath.FLOAT_EQUALS_EPS)

            assert equalsEps(p.length(), identityMatrix.rotate(rot, 1, 0, 0).transform(p).length(), BananaMath.FLOAT_EQUALS_EPS)
            assert equalsEps(p.length(), identityMatrix.rotate(rot, 0, 1, 0).transform(p).length(), BananaMath.FLOAT_EQUALS_EPS)
            assert equalsEps(p.length(), identityMatrix.rotate(rot, 0, 0, 1).transform(p).length(), BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "identity transform test"() {
        multiTest {
            def point = randVec3d
            assert identityMatrix.transform(point).equalsEps(point, BananaMath.FLOAT_EQUALS_EPS)
            def mat = randomMatrix
            assert mat.copy().mul(identityMatrix).equalsEps(mat, BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "determinant mul test"() {
        multiTest {
            def a = randomMatrix
            def b = randomMatrix

            def ab = a.copy().mul(b)

            assert equalsEps(ab.determinant(), a.determinant() * b.determinant(), 1.0E-3F)
        }
    }

    @Test
    void "inverse determinant test"() {
        multiTest {
            def m = randomMatrix
            assert equalsEps(m.copy().invert().determinant(), 1.0F / m.determinant(), BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "mul order test"() {
        multiTest {
            def tr = this.randVec3d
            def v = vec3d(1, 0, 0)

            assert !identityMatrix.translate(tr).rotateZ(90).transform(v).equalsEps(identityMatrix.rotateZ(90).translate(tr).transform(v), BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "transform work test"() {
        multiTest {
            def vec2 = this.randVec3d(20)
            def vec = this.randVec3d(20)
            float rot = randDegreesF()

            assert identityMatrix.translate(vec2).transform(vec).equalsEps(vec.add(vec2), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.translate(vec2.scale(-1)).transform(vec).equalsEps(vec.add(vec2.scale(-1)), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.scale(vec2).transform(vec).equalsEps(vec.scale(vec2), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.scale(vec2.scale(-1)).transform(vec).equalsEps(vec.scale(vec2.scale(-1)), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.rotateX(rot).transform(vec).equalsEps(vec.rotateX(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateX(-rot).transform(vec).equalsEps(vec.rotateX(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateY(rot).transform(vec).equalsEps(vec.rotateY(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateY(-rot).transform(vec).equalsEps(vec.rotateY(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateZ(rot).transform(vec).equalsEps(vec.rotateZ(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateZ(-rot).transform(vec).equalsEps(vec.rotateZ(-rot), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.rotate(rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotate(-rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotate(rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotate(-rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotate(rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotate(-rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(-rot), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.rotateXAround(vec2, rot).transform(vec).equalsEps(vec.rotateXAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateXAround(vec2, -rot).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateYAround(vec2, rot).transform(vec).equalsEps(vec.rotateYAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateYAround(vec2, -rot).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateZAround(vec2, rot).transform(vec).equalsEps(vec.rotateZAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateZAround(vec2, -rot).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.rotateAround(vec2, rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateAround(vec2, -rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateAround(vec2, rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateAround(vec2, -rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateAround(vec2, rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.rotateAround(vec2, -rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)


            assert identityMatrix.localTranslate(vec2).transform(vec).equalsEps(vec.add(vec2), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localTranslate(vec2.scale(-1)).transform(vec).equalsEps(vec.add(vec2.scale(-1)), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.localScale(vec2).transform(vec).equalsEps(vec.scale(vec2), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localScale(vec2.scale(-1)).transform(vec).equalsEps(vec.scale(vec2.scale(-1)), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.localRotateX(rot).transform(vec).equalsEps(vec.rotateX(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateX(-rot).transform(vec).equalsEps(vec.rotateX(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateY(rot).transform(vec).equalsEps(vec.rotateY(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateY(-rot).transform(vec).equalsEps(vec.rotateY(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateZ(rot).transform(vec).equalsEps(vec.rotateZ(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateZ(-rot).transform(vec).equalsEps(vec.rotateZ(-rot), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.localRotate(rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotate(-rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotate(rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotate(-rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(-rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotate(rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotate(-rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(-rot), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.localRotateXAround(vec2, rot).transform(vec).equalsEps(vec.rotateXAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateXAround(vec2, -rot).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateYAround(vec2, rot).transform(vec).equalsEps(vec.rotateYAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateYAround(vec2, -rot).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateZAround(vec2, rot).transform(vec).equalsEps(vec.rotateZAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateZAround(vec2, -rot).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)

            assert identityMatrix.localRotateAround(vec2, rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateAround(vec2, -rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateAround(vec2, rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateAround(vec2, -rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateAround(vec2, rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, rot), BananaMath.FLOAT_EQUALS_EPS)
            assert identityMatrix.localRotateAround(vec2, -rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot), BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "local methods test"() {
        multiTest {
            def point = this.randVec3d.round()
            def axis = this.randVec3d
            float rot = randDegreesF()

            assert identityMatrix.translate(point).rotate(rot, axis).equalsEps(identityMatrix.localRotate(rot, axis).localTranslate(point))
            assert identityMatrix.translate(point).scale(axis.normalize()).equalsEps(identityMatrix.localScale(axis.normalize()).localTranslate(point))
        }
    }

    @Test
    void "int and double overloads test"() {
        multiTest {
            def point = this.randVec3d.round()
            def axis = this.randVec3d
            float rot = randDegreesF()

            assert identityMatrix.translate(point).equalsEps(identityMatrix.translate(point.toDouble()))
            assert identityMatrix.scale(point).equalsEps(identityMatrix.scale(point.toDouble()))
            assert identityMatrix.rotateAround(point, rot, axis).equalsEps(identityMatrix.rotateAround(point.toDouble(), rot, axis))
        }
    }

    @Test
    void "matrix2 rotate test"() {
        multiTest(80) {
            def m3 = this.identityMatrix
            def m2 = Matrix2F.IDENTITY
            IVec2D vec = randVec2d
            float rot = randDegreesF()

            assert m2.rotate(rot).transform(vec).equalsEps(m3.rotateZ(rot).transform(vec), BananaMath.FLOAT_EQUALS_EPS)
        }
    }


    @Test
    void "vec and matrix rotate test"() {
        multiTest {
            def m = this.identityMatrix
            IVec3D vec = randVec3d
            float rot = randDegreesF()

            assert m.copy().rotateX(rot).transform(vec).equalsEps(vec.rotateX(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert m.copy().rotateY(rot).transform(vec).equalsEps(vec.rotateY(rot), BananaMath.FLOAT_EQUALS_EPS)
            assert m.copy().rotateZ(rot).transform(vec).equalsEps(vec.rotateZ(rot), BananaMath.FLOAT_EQUALS_EPS)
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

            assert m.copy().rotateX(0).equalsEps(m)
            assert m.copy().rotateY(0).equalsEps(m)
            assert m.copy().rotateZ(0).equalsEps(m)

            assert m.copy().rotate(0, randVec3d).equalsEps(m)
        }
    }

    @Test
    void "rotate test"() {
        multiTest {
            def m = this.identityMatrix
            float rot = randDegreesF()

            assert m.copy().rotate(rot, 1, 0, 0).equalsEps(m.rotateX(rot))
            assert m.copy().rotate(rot, 0, 1, 0).equalsEps(m.rotateY(rot))
            assert m.copy().rotate(rot, 0, 0, 1).equalsEps(m.rotateZ(rot))
        }
    }

    @Test
    void "invert matrix test"() {
        multiTest {
            def m = this.randomMatrix
            assert m.mul(m.copy().invert()).equalsEps(identityMatrix)
        }
    }

    @Test
    void "invert invert matrix test"() {
        multiTest {
            def m = this.randomMatrix
            assert m.equalsEps(m.copy().invert().invert())
        }
    }

    @Test
    void "mul associativity test"() {
        multiTest {
            def a = this.randomMatrix
            def b = this.randomMatrix
            assert a.copy().mul(b).equalsEps(b.copy().mulPost(a))
        }
    }

    def getRandomMatrix() {
        def m = this.identityMatrix
        if (this.rand.nextDouble() < 0.5) {
            m = m.translate(this.randVec3d(40))
            m = m.rotate(randDouble(-360, 360) as float, this.randVec3d)
            int d = this.rand.nextBoolean() ? -1 : 1
            m = m.scale(this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d)
        } else {
            m = m.rotate(randDouble(-360, 360) as float, this.randVec3d)
            m = m.translate(this.randVec3d(40))
            int d = this.rand.nextBoolean() ? -1 : 1
            m = m.scale(this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d, this.randDouble(0.2, 4) * d)
        }
        return m
    }

    def getIdentityMatrix() {
        return new Matrix3FM()
    }
}
