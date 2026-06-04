package com.artur114.bananalib.math.test

import com.artur114.bananalib.math.BananaMath
import com.artur114.bananalib.math.m2d.matrix.IMatrix2D
import com.artur114.bananalib.math.m2d.matrix.IMatrix2DM
import com.artur114.bananalib.math.m2d.matrix.IMatrix2F
import com.artur114.bananalib.math.m2d.matrix.IMatrix2FM
import com.artur114.bananalib.math.m2d.matrix.Matrix2D
import com.artur114.bananalib.math.m2d.matrix.Matrix2DM
import com.artur114.bananalib.math.m2d.matrix.Matrix2F
import com.artur114.bananalib.math.m2d.matrix.Matrix2FM
import com.artur114.bananalib.math.m2d.vec.IVec2D
import com.artur114.bananalib.math.m2d.vec.IVec2I
import com.artur114.bananalib.math.m2d.vec.Vec2D
import com.artur114.bananalib.math.m2d.vec.Vec2I
import com.artur114.bananalib.math.m3d.matrix.IMatrix3D
import com.artur114.bananalib.math.m3d.matrix.IMatrix3F
import com.artur114.bananalib.math.m3d.matrix.Matrix3D
import com.artur114.bananalib.math.m3d.matrix.Matrix3F
import com.artur114.bananalib.math.m3d.vec.IVec3D
import com.artur114.bananalib.math.m3d.vec.IVec3I
import com.artur114.bananalib.math.m3d.vec.Vec3D
import com.artur114.bananalib.math.m3d.vec.Vec3I

abstract class TestBase {
    public Random rand = new Random(0xBADCEFFE)

    int defMultiCount() {
        return 20
    }

    def multiTest(int count, Closure<Void> closure) {
        for (i in 1..count) {
            closure.call(i)
        }
    }

    def multiTest(Closure<Void> closure) {
        for (i in 1..defMultiCount()) {
            closure.call(i)
        }
    }

    def randInt(int bound) {
        return this.rand.nextInt(bound * 2) - bound
    }

    def randDegrees() {
        return randDouble(-360, 360)
    }

    def randDegreesF() {
        return randFloat(-360, 360)
    }

    def randDouble(double min, double max) {
        return this.rand.nextDouble() * (max - min) + min
    }

    float randFloat(float min, float max) {
        return this.rand.nextFloat() * (max - min) + min
    }

    def randInt(int bound, List exclude) {
        Set checked = new HashSet()
        int ret = randInt(bound)
        while (checked.size() < bound * 2 && exclude.contains(ret)) {
            checked.add(ret = randInt(bound))
        }
        if (checked.size() >= bound * 2) {
            throw new IllegalArgumentException();
        }
        return ret
    }

    def equalsEps(double d0, double d1, double eps) {
        return Math.abs(d0 - d1) <= eps
    }

    def equalsEps(double d0, double d1) {
        return equalsEps(d0, d1, BananaMath.DOUBLE_EQUALS_EPS)
    }

    def equalsEps(float d0, float d1, float eps) {
        return Math.abs(d0 - d1) <= eps
    }

    def equalsEps(float d0, float d1) {
        return equalsEps(d0, d1, BananaMath.FLOAT_EQUALS_EPS)
    }

    IMatrix3D mat3d() {
        return Matrix3D.IDENTITY
    }

    IMatrix3F mat3f() {
        return Matrix3F.IDENTITY
    }

    IMatrix2D mat2d() {
        return Matrix2D.IDENTITY
    }

    IMatrix2F mat2f() {
        return Matrix2F.IDENTITY
    }

    IVec3I vec3i(int x, int y, int z) {
        new Vec3I(x, y, z)
    }

    IVec3D vec3d(double x, double y, double z) {
        new Vec3D(x, y, z)
    }

    IVec2I vec2i(int x, int y) {
        new Vec2I(x, y)
    }

    IVec2D vec2d(double x, double y) {
        new Vec2D(x, y)
    }

    IVec3I getRandVec3i() {
        return randVec3i(100)
    }

    IVec3D getRandVec3d() {
        return randVec3d(100)
    }

    IVec2I getRandVec2i() {
        return randVec2i(100)
    }

    IVec2D getRandVec2d() {
        return randVec2d(100)
    }

    IVec3I randVec3i(int bound) {
        return new Vec3I(randInt(bound), randInt(bound), randInt(bound))
    }

    IVec3D randVec3d(double bound) {
        return new Vec3D(randDouble(-bound, bound), randDouble(-bound, bound), randDouble(-bound, bound))
    }

    IVec2I randVec2i(int bound) {
        return new Vec2I(randInt(bound), randInt(bound))
    }

    IVec2D randVec2d(double bound) {
        return new Vec2D(randDouble(-bound, bound), randDouble(-bound, bound))
    }
}
