package com.artur114.bananalib.math.m2d.matrix

import com.artur114.bananalib.math.m2d.vec.IVec2D
import com.artur114.bananalib.math.m2d.vec.IVec2I
import com.artur114.bananalib.math.m2d.vec.Vec2D
import com.artur114.bananalib.math.test.TestBase
import org.junit.jupiter.api.Test

class Matrix2DMTest extends TestBase {
    @Test
    void "rotated vector length test"() {
        multiTest {
            double rot = randDouble(-360, 360)
            IVec2D p = randomPoint()
            assert equalsEps(p.length(), identityMatrix().rotate(rot).transform(p).length())
        }
    }

    @Test
    void "identity transform test"() {
        multiTest {
            IVec2D point = randomPoint()
            assert identityMatrix().transform(point).equalsEps(point)
        }
    }

    @Test
    void "mul associativity test"() {
        multiTest {
            def a = randomMatrix()
            def b = randomMatrix()
            def c = randomMatrix()

            assert a.copy().mul(b).mul(c).equalsEps(a.copy().mul(b.copy().mul(c)))
        }
    }

    @Test
    void "determinant mul test"() {
        multiTest {
            def a = randomMatrix()
            def b = randomMatrix()

            def ab = a.copy().mul(b)

            assert equalsEps(ab.determinant(), a.determinant() * b.determinant())
        }
    }

    @Test
    void "inverse determinant test"() {
        multiTest {
            Matrix2DM m = randomMatrix()
            assert equalsEps(m.copy().invert().determinant(), 1.0D / m.determinant())
        }
    }

    @Test
    void "int and double overloads test"() {
        multiTest {
            IVec2I point = randomPoint().round()
            double rot = randDouble(0, 360)

            assert identityMatrix().translate(point).equalsEps(identityMatrix().translate(point.toDouble()))
            assert identityMatrix().scale(point).equalsEps(identityMatrix().scale(point.toDouble()))
            assert identityMatrix().rotateAround(point, rot).equalsEps(identityMatrix().rotateAround(point.toDouble(), rot))
        }
    }

    @Test
    void "mul and mulPos test"() {
        multiTest {
            randomPoint().with {
                double rot = randDouble(0, 360)
                IVec2D tr = randomPoint()

                def mul = identityMatrix().mul(identityMatrix().translate(tr.x(), tr.y())).mul(identityMatrix().rotate(rot))
                def mulPost = identityMatrix().mulPost(identityMatrix().translate(tr.x(), tr.y())).mulPost(identityMatrix().rotate(rot))

                assert mul.transform(it).equalsEps(it.add(tr.x(), tr.y()).rotate(rot))
                assert mulPost.transform(it).equalsEps(it.rotate(rot).add(tr.x(), tr.y()))
            }
            randomPoint().round().with {
                double rot = randDouble(0, 360)
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
            assert m.equalsEps(m.copy().invert().invert())
        }
    }

    @Test
    void "invert matrix test"() {
        def identity = identityMatrix()
        multiTest {
            def m = randomMatrix()
            assert m.mul(m.copy().invert()).equalsEps(identity)
        }
    }

    @Test
    void "invert matrix restore point test"() {
        multiTest {
            withRandMatrix { Matrix2DM m, tr, rot, scale ->
                IVec2D point = randomPoint()
                IVec2D pointTr = m.transform(point)
                assert point.equalsEps(m.invert().transform(pointTr))
            }
        }
    }

    @Test
    void "determinant test"() {
        assert equalsEps(identityMatrix().determinant(), 1.0D)
        multiTest {
            withRandMatrix {Matrix2DM m, tr, rot, IVec2D scale ->
                assert equalsEps(m.determinant(), scale.x() * scale.y())
            }
        }
    }

    @Test
    void "rotate around point test"() {
        multiTest {
            identityMatrix().with {
                IVec2I around = randomPoint(16).round()
                IVec2I point = randomPoint(16).round()
                double rot = randDouble(-360, 360)
                assert it.rotateAround(around, rot).transform(point) == point.rotateAround(around, rot)
            }

            identityMatrix().with {
                double rot = randDouble(-360, 360)
                IVec2D around = randomPoint()
                IVec2D point = randomPoint()
                assert it.rotateAround(around, rot).transform(point).equalsEps(point.rotateAround(around, rot))
            }

            IVec2D tr = randomPoint()
            def m = identityMatrix().rotateAround(tr.x(), tr.y(), 90)
            IVec2D p = new Vec2D(tr)
            IVec2D rotated = m.transform(p)
            assert p.equalsEps(rotated, 1e-12)
        }
    }

    def randomMatrix() {
        Matrix2DM m = new Matrix2DM()
        if (rand.nextDouble() < 0.5) {
            m.translate(randInt(40), randInt(40))
            m.rotate(this.rand.nextDouble() * 360)
            m.scale(randomPoint(4))
        } else {
            m.rotate(this.rand.nextDouble() * 360)
            m.translate(randInt(40), randInt(40))
            m.scale(randomPoint(4))
        }
        return m
    }

    def withRandMatrix(Closure<Void> cl) {
        IVec2D scale = randomPoint(4)
        IVec2D tr = randomPoint()
        double rot = this.rand.nextDouble() * 360
        cl.call(new Matrix2DM().translate(tr.x(), tr.y()).rotate(rot).scale(scale.x(), scale.y()), tr, rot, scale)
    }

    Vec2D randomPoint() {
        randomPoint(100)
    }

    Vec2D randomPoint(double size) {
        new Vec2D(randDouble(-size, size), randDouble(-size, size))
    }

    def identityMatrix() {
        new Matrix2DM().setIdentity()
    }
}
