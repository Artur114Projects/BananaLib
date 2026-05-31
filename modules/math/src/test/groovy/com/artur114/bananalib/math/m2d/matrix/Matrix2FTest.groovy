package com.artur114.bananalib.math.m2d.matrix

import com.artur114.bananalib.math.BananaMath
import com.artur114.bananalib.math.m2d.vec.IVec2D
import com.artur114.bananalib.math.m2d.vec.IVec2I
import com.artur114.bananalib.math.m2d.vec.Vec2D
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class Matrix2FTest extends Matrix2TestBase<IMatrix2F> {
    @Test
    void "identity matrix mul test"() {
        IMatrix2F identity = identityMatrix()
        IMatrix2F m = randomMatrix()
        assert m.mul(identity).equalsEps(m)
        assert identity.mul(m).equalsEps(m)
    }

    @Test
    void "non reversible matrix inv test"() {
        assertThrows(ArithmeticException.class, { identityMatrix().scale(0, 1).invert() })
    }

    @Test
    void "rotated vector length test"() {
        multiTest {
            float rot = randFloat(-360, 360)
            IVec2D p = randomPoint()
            assert equalsEps(p.length(), identityMatrix().rotate(rot).transform(p).length(), BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "identity transform test"() {
        multiTest {
            IVec2D point = randomPoint()
            assert identityMatrix().transform(point).equalsEps(point, BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "mul associativity test"() {
        multiTest {
            def a = randomMatrix()
            def b = randomMatrix()
            def c = randomMatrix()

            assert a.mul(b).mul(c).equalsEps(a.mul(b.mul(c)), 1.0E-3F)
        }
    }

    @Test
    void "determinant mul test"() {
        multiTest {
            def a = randomMatrix()
            def b = randomMatrix()

            def ab = a.mul(b)

            assert equalsEps(ab.determinant(), a.determinant() * b.determinant(), BananaMath.FLOAT_EQUALS_EPS)
        }
    }

    @Test
    void "inverse determinant test"() {
        multiTest {
            def m = randomMatrix()
            assert equalsEps(m.invert().determinant(), (float) (1.0F / m.determinant()))
        }
    }

    @Test
    void "int and double overloads test"() {
        multiTest {
            IVec2I point = randomPoint().round()
            float rot = randFloat(0, 360)

            assert identityMatrix().translate(point).equalsEps(identityMatrix().translate(point.toDouble()))
            assert identityMatrix().scale(point).equalsEps(identityMatrix().scale(point.toDouble()))
            assert identityMatrix().rotateAround(point, rot).equalsEps(identityMatrix().rotateAround(point.toDouble(), rot))
        }
    }

    @Test
    void "mul and mulPos test"() {
        multiTest {
            randomPoint().with {
                float rot = randFloat(0, 360)
                IVec2D tr = randomPoint()

                def mul = identityMatrix().mul(identityMatrix().translate(tr.x(), tr.y())).mul(identityMatrix().rotate(rot))
                def mulPost = identityMatrix().mulPost(identityMatrix().translate(tr.x(), tr.y())).mulPost(identityMatrix().rotate(rot))

                assert mul.transform(it).equalsEps(it.add(tr.x(), tr.y()).rotate(rot), BananaMath.FLOAT_EQUALS_EPS)
                assert mulPost.transform(it).equalsEps(it.rotate(rot).add(tr.x(), tr.y()), BananaMath.FLOAT_EQUALS_EPS)
            }
            randomPoint().round().with {
                float rot = randFloat(0, 360)
                IVec2I tr = randomPoint().round()

                def mul = identityMatrix().mul(identityMatrix().translate(tr.x(), tr.y())).mul(identityMatrix().rotate(rot))
                def mulPost = identityMatrix().mulPost(identityMatrix().translate(tr.x(), tr.y())).mulPost(identityMatrix().rotate(rot))

                assert mul.transform(it) == (it.add(tr.x(), tr.y()).rotate(rot))
                assert mulPost.transform(it) == (it.rotate(rot).add(tr.x(), tr.y()))
            }
            return null
        }
    }

    @Test
    void "invert invert matrix test"() {
        multiTest {
            def m = randomMatrix()
            assert m.equalsEps(m.invert().invert())
        }
    }

    @Test
    void "invert matrix test"() {
        def identity = identityMatrix()
        multiTest {
            def m = randomMatrix()
            assert m.mul(m.invert()).equalsEps(identity)
        }
    }

    @Test
    void "invert matrix restore point test"() {
        multiTest {
            withRandMatrix { IMatrix2F m, tr, rot, scale ->
                IVec2D point = randomPoint()
                IVec2D pointTr = m.transform(point)
                assert point.equalsEps(m.invert().transform(pointTr), BananaMath.FLOAT_EQUALS_EPS)
            }
        }
    }

    @Test
    void "determinant test"() {
        assert equalsEps(identityMatrix().determinant(), 1.0D)
        multiTest {
            withRandMatrix {IMatrix2F m, tr, rot, IVec2D scale ->
                assert equalsEps(m.determinant(), (float) (scale.x() * scale.y()))
            }
        }
    }

    @Test
    void "rotate around point test"() {
        multiTest {
            identityMatrix().with {
                IVec2I around = randomPoint(16).round()
                IVec2I point = randomPoint(16).round()
                float rot = randFloat(-360, 360)
                assert it.rotateAround(around, rot).transform(point) == point.rotateAround(around, rot)
            }

            identityMatrix().with {
                float rot = randFloat(-360, 360)
                IVec2D around = randomPoint()
                IVec2D point = randomPoint()
                assert it.rotateAround(around, rot).transform(point).equalsEps(point.rotateAround(around, rot), BananaMath.FLOAT_EQUALS_EPS)
            }

            IVec2D tr = randomPoint()
            def m = identityMatrix().rotateAround(tr.x(), tr.y(), 90)
            IVec2D p = new Vec2D(tr)
            IVec2D rotated = m.transform(p)
            assert p.equalsEps(rotated, BananaMath.FLOAT_EQUALS_EPS)
        }
    }
    
    @Override
    IMatrix2F identityMatrix() {
        return Matrix2F.IDENTITY
    }
}
