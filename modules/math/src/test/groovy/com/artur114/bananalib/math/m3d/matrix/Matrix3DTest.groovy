package com.artur114.bananalib.math.m3d.matrix

import com.artur114.bananalib.math.m2d.matrix.IMatrix2D
import com.artur114.bananalib.math.m2d.matrix.Matrix2D
import com.artur114.bananalib.math.m2d.vec.IVec2D
import com.artur114.bananalib.math.m3d.vec.IVec3D
import com.artur114.bananalib.math.test.TestBase
import org.junit.jupiter.api.Test

class Matrix3DTest extends TestBase {
    @Override
    int defMultiCount() {
        return 1000
    }

    @Test
    void "inverse transform test"() {
        multiTest {
            def m = randomMatrix
            def v = randVec3d
            assert m.invert().transform(m.transform(v)).equalsEps(v)
        }
    }

    @Test
    void "identity matrix mul test"() {
        def identity = identityMatrix
        def m = randomMatrix
        assert m.mul(identity).equalsEps(m)
        assert identity.mul(m).equalsEps(m)
    }

    @Test
    void "rotated vector length test"() {
        multiTest {
            double rot = randDouble(-360, 360)
            def p = randVec3d
            assert equalsEps(p.length(), identityMatrix.rotateX(rot).transform(p).length())
            assert equalsEps(p.length(), identityMatrix.rotateY(rot).transform(p).length())
            assert equalsEps(p.length(), identityMatrix.rotateZ(rot).transform(p).length())

            assert equalsEps(p.length(), identityMatrix.rotate(rot, 1, 0, 0).transform(p).length())
            assert equalsEps(p.length(), identityMatrix.rotate(rot, 0, 1, 0).transform(p).length())
            assert equalsEps(p.length(), identityMatrix.rotate(rot, 0, 0, 1).transform(p).length())
        }
    }

    @Test
    void "identity transform test"() {
        multiTest {
            def point = randVec3d
            assert identityMatrix.transform(point).equalsEps(point)
        }
    }

    @Test
    void "determinant mul test"() {
        multiTest {
            def a = randomMatrix
            def b = randomMatrix

            def ab = a.mul(b)

            assert equalsEps(ab.determinant(), a.determinant() * b.determinant())
        }
    }

    @Test
    void "inverse determinant test"() {
        multiTest {
            def m = randomMatrix
            assert equalsEps(m.invert().determinant(), 1.0D / m.determinant())
        }
    }

    @Test
    void "mul order test"() {
        multiTest {
            def tr = this.randVec3d
            def v = vec3d(1, 0, 0)

            assert !identityMatrix.translate(tr).rotateZ(90).transform(v).equalsEps(identityMatrix.rotateZ(90).translate(tr).transform(v))
        }
    }

    @Test
    void "transform work test"() {
        multiTest {
            def vec2 = this.randVec3d
            def vec = this.randVec3d
            double rot = randDegrees()

            assert identityMatrix.translate(vec2).transform(vec).equalsEps(vec.add(vec2))
            assert identityMatrix.translate(vec2.scale(-1)).transform(vec).equalsEps(vec.add(vec2.scale(-1)))

            assert identityMatrix.scale(vec2).transform(vec).equalsEps(vec.scale(vec2))
            assert identityMatrix.scale(vec2.scale(-1)).transform(vec).equalsEps(vec.scale(vec2.scale(-1)))

            assert identityMatrix.rotateX(rot).transform(vec).equalsEps(vec.rotateX(rot))
            assert identityMatrix.rotateX(-rot).transform(vec).equalsEps(vec.rotateX(-rot))
            assert identityMatrix.rotateY(rot).transform(vec).equalsEps(vec.rotateY(rot))
            assert identityMatrix.rotateY(-rot).transform(vec).equalsEps(vec.rotateY(-rot))
            assert identityMatrix.rotateZ(rot).transform(vec).equalsEps(vec.rotateZ(rot))
            assert identityMatrix.rotateZ(-rot).transform(vec).equalsEps(vec.rotateZ(-rot))

            assert identityMatrix.rotate(rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(rot))
            assert identityMatrix.rotate(-rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(-rot))
            assert identityMatrix.rotate(rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(rot))
            assert identityMatrix.rotate(-rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(-rot))
            assert identityMatrix.rotate(rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(rot))
            assert identityMatrix.rotate(-rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(-rot))

            assert identityMatrix.rotateXAround(vec2, rot).transform(vec).equalsEps(vec.rotateXAround(vec2, rot))
            assert identityMatrix.rotateXAround(vec2, -rot).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot))
            assert identityMatrix.rotateYAround(vec2, rot).transform(vec).equalsEps(vec.rotateYAround(vec2, rot))
            assert identityMatrix.rotateYAround(vec2, -rot).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot))
            assert identityMatrix.rotateZAround(vec2, rot).transform(vec).equalsEps(vec.rotateZAround(vec2, rot))
            assert identityMatrix.rotateZAround(vec2, -rot).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot))

            assert identityMatrix.rotateAround(vec2, rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, rot))
            assert identityMatrix.rotateAround(vec2, -rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot))
            assert identityMatrix.rotateAround(vec2, rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, rot))
            assert identityMatrix.rotateAround(vec2, -rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot))
            assert identityMatrix.rotateAround(vec2, rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, rot))
            assert identityMatrix.rotateAround(vec2, -rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot))


            assert identityMatrix.localTranslate(vec2).transform(vec).equalsEps(vec.add(vec2))
            assert identityMatrix.localTranslate(vec2.scale(-1)).transform(vec).equalsEps(vec.add(vec2.scale(-1)))

            assert identityMatrix.localScale(vec2).transform(vec).equalsEps(vec.scale(vec2))
            assert identityMatrix.localScale(vec2.scale(-1)).transform(vec).equalsEps(vec.scale(vec2.scale(-1)))

            assert identityMatrix.localRotateX(rot).transform(vec).equalsEps(vec.rotateX(rot))
            assert identityMatrix.localRotateX(-rot).transform(vec).equalsEps(vec.rotateX(-rot))
            assert identityMatrix.localRotateY(rot).transform(vec).equalsEps(vec.rotateY(rot))
            assert identityMatrix.localRotateY(-rot).transform(vec).equalsEps(vec.rotateY(-rot))
            assert identityMatrix.localRotateZ(rot).transform(vec).equalsEps(vec.rotateZ(rot))
            assert identityMatrix.localRotateZ(-rot).transform(vec).equalsEps(vec.rotateZ(-rot))

            assert identityMatrix.localRotate(rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(rot))
            assert identityMatrix.localRotate(-rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateX(-rot))
            assert identityMatrix.localRotate(rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(rot))
            assert identityMatrix.localRotate(-rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateY(-rot))
            assert identityMatrix.localRotate(rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(rot))
            assert identityMatrix.localRotate(-rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZ(-rot))

            assert identityMatrix.localRotateXAround(vec2, rot).transform(vec).equalsEps(vec.rotateXAround(vec2, rot))
            assert identityMatrix.localRotateXAround(vec2, -rot).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot))
            assert identityMatrix.localRotateYAround(vec2, rot).transform(vec).equalsEps(vec.rotateYAround(vec2, rot))
            assert identityMatrix.localRotateYAround(vec2, -rot).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot))
            assert identityMatrix.localRotateZAround(vec2, rot).transform(vec).equalsEps(vec.rotateZAround(vec2, rot))
            assert identityMatrix.localRotateZAround(vec2, -rot).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot))

            assert identityMatrix.localRotateAround(vec2, rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, rot))
            assert identityMatrix.localRotateAround(vec2, -rot, 1, 0, 0).transform(vec).equalsEps(vec.rotateXAround(vec2, -rot))
            assert identityMatrix.localRotateAround(vec2, rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, rot))
            assert identityMatrix.localRotateAround(vec2, -rot, 0, 1, 0).transform(vec).equalsEps(vec.rotateYAround(vec2, -rot))
            assert identityMatrix.localRotateAround(vec2, rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, rot))
            assert identityMatrix.localRotateAround(vec2, -rot, 0, 0, 1).transform(vec).equalsEps(vec.rotateZAround(vec2, -rot))
        }
    }

    @Test
    void "local methods test"() {
        multiTest {
            def point = this.randVec3d.round()
            def axis = this.randVec3d
            double rot = randDegrees()

            assert identityMatrix.translate(point).rotate(rot, axis).equalsEps(identityMatrix.localRotate(rot, axis).localTranslate(point))
            assert identityMatrix.translate(point).scale(axis.normalize()).equalsEps(identityMatrix.localScale(axis.normalize()).localTranslate(point))
        }
    }

    @Test
    void "int and double overloads test"() {
        multiTest {
            def point = this.randVec3d.round()
            def axis = this.randVec3d
            double rot = randDouble(0, 360)

            assert identityMatrix.translate(point).equalsEps(identityMatrix.translate(point.toDouble()))
            assert identityMatrix.scale(point).equalsEps(identityMatrix.scale(point.toDouble()))
            assert identityMatrix.rotateAround(point, rot, axis).equalsEps(identityMatrix.rotateAround(point.toDouble(), rot, axis))
        }
    }

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
