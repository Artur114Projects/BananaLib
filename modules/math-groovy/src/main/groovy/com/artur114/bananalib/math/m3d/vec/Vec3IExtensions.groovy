package com.artur114.bananalib.math.m3d.vec

import com.artur114.bananalib.math.core.m3d.vec.IVec3DC
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC
import com.artur114.bananalib.math.m2d.vec.IVec2I

import java.util.function.Function

class Vec3IExtensions {
    static <T extends IVec3IC> T plus(T self, IVec3IC other) {
        return self.add(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T minus(T self, IVec3IC other) {
        return self.subtract(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T plus(T self, IVec3DC other) {
        return self.add(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T minus(T self, IVec3DC other) {
        return self.subtract(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T multiply(T self, Number scalar) {
        return self.scale(scalar.doubleValue()) as T
    }

    static <T extends IVec3IC> T multiply(Number scalar, T self) {
        return self.scale(scalar.doubleValue()) as T
    }

    static <T extends IVec3IC> T multiply(T self, IVec3IC other) {
        return self.scale(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T multiply(T self, IVec3DC other) {
        return self.scale(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T div(T self, Number scalar) {
        return self.divide(scalar.doubleValue()) as T
    }

    static <T extends IVec3IC> T div(T self, IVec3IC other) {
        return self.divide(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T div(T self, IVec3DC other) {
        return self.divide(other.x(), other.y(), other.z()) as T
    }

    static <T extends IVec3IC> T mod(T self, Number val) {
        double d = val.doubleValue()
        return self.add(
                self.x() % d - self.x(),
                self.y() % d - self.y(),
                self.z() % d - self.z()
        ) as T
    }

    static <T extends IVec3IC> T negative(T self) {
        return self.scale(-1) as T
    }

    static int getX(IVec3IC self) {
        return self.x()
    }

    static int getY(IVec3IC self) {
        return self.y()
    }

    static int getZ(IVec3IC self) {
        return self.z()
    }

    static IVec2I getXy(IVec3I self) {
        return self.xy()
    }

    static IVec2I getXz(IVec3I self) {
        return self.xz()
    }

    static IVec2I getYx(IVec3I self) {
        return self.yx()
    }

    static IVec2I getYz(IVec3I self) {
        return self.yz()
    }

    static IVec2I getZx(IVec3I self) {
        return self.zx()
    }

    static IVec2I getZy(IVec3I self) {
        return self.zy()
    }

    static IVec3I getZyx(IVec3I self) {
        return self.zyx()
    }

    static IVec3I getZxy(IVec3I self) {
        return self.zxy()
    }

    static IVec3I getYzx(IVec3I self) {
        return self.yzx()
    }

    static IVec3I getXzy(IVec3I self) {
        return self.xzy()
    }

    static IVec3I getYxz(IVec3I self) {
        return self.yxz()
    }

    static double getAt(IVec3IC self, int index) {
        switch (index) {
            case 0:
                return self.x()
            case 1:
                return self.y()
            case 2:
                return self.z()
            default:
                throw new IndexOutOfBoundsException()
        }
    }

    static <T extends IVec3IC> VecVal<T> getAt(T self, String index) {
        return new VecVal(self, index);
    }

    static class VecVal<T extends IVec3IC> {
        private Function<Double, T> scale
        private Function<Double, T> add
        private int val
        private T orig

        VecVal(T orig, String index) {
            this.orig = orig
            switch (index) {
                case 'x':
                    this.add = {Double it -> this.orig.add(it, 0, 0)}
                    this.scale = {Double it -> this.orig.scale(it, 1, 1)}
                    this.val = orig.x
                    break
                case 'y':
                    this.add = {Double it -> this.orig.add(0, it, 0)}
                    this.scale = {Double it -> this.orig.scale(1, it, 1)}
                    this.val = orig.y
                    break
                case 'z':
                    this.add = {Double it -> this.orig.add(0, 0, it)}
                    this.scale = {Double it -> this.orig.scale(1, 1, it)}
                    this.val = orig.z
                    break
                default:
                    throw new IllegalArgumentException()
            }
        }

        T plus(Number val) {
            return this.add.apply(val.doubleValue())
        }

        T minus(Number val) {
            return this.add.apply(-val.doubleValue())
        }

        T multiply(Number val) {
            return this.scale.apply(val.doubleValue())
        }

        T div(Number val) {
            return this.scale.apply(1.0D / val.doubleValue())
        }

        T mod(Number val) {
            return this.add.apply((this.val % val.doubleValue()) - this.val)
        }

        Object asType(Class<?> type) {
            if (type == double.class || type == Double.class) {
                return this.val
            }
            return this
        }

        @Override
        String toString() {
            return this.val
        }

        @Override
        int hashCode() {
            return Double.hashCode(this.val)
        }

        @Override
        boolean equals(Object obj) {
            if (obj instanceof Number) {
                return this.val == obj.intValue()
            }
            if (obj instanceof VecVal) {
                return this.val == obj.val
            }
            return false
        }
    }
}
