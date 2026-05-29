package com.artur114.bananalib.math.m2d.vec;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.internal.IntStack;
import com.artur114.bananalib.math.internal.ThreadLocalPool;
import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m3d.vec.*;

public class Vec2IM implements IVec2IM {
    private static final ThreadLocalPool<Vec2IM> pool = new ThreadLocalPool<>(new Vec2IM[4], Vec2IM::new, vec -> {
        vec.resetStack().setZero();
        vec.released = true;
        return vec;
    }, vec -> {
        vec.released = false;
        return vec;
    }, vec -> vec.released);

    public static Vec2IM obtain() {
        return pool.obtain();
    }

    public static void release(Vec2IM vec) {
        pool.release(vec);
    }

    private IntStack stateStack = null;
    private boolean released;
    private int x, y;

    public Vec2IM() {}

    public Vec2IM(double x, double y) {
        this.x = BananaMath.floor(x);
        this.y = BananaMath.floor(y);
    }

    public Vec2IM(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2IM(IVec2DC vec2D) {
        this(vec2D.x(), vec2D.y());
    }

    public Vec2IM(IVec2IC vec2D) {
        this(vec2D.x(), vec2D.y());
    }

    @Override
    public IVec2IM set(int[] pos) {
        if (pos.length < 2) {
            throw new IllegalArgumentException();
        }
        this.x = pos[0];
        this.y = pos[1];
        return this;
    }

    @Override
    public IVec2IM set(double x, double y) {
        this.x = BananaMath.floor(x);
        this.y = BananaMath.floor(y);
        return this;
    }

    @Override
    public IVec2IM set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public IVec2IM set(IVec2DC vec) {
        return this.set(vec.x(), vec.y());
    }

    @Override
    public IVec2IM set(IVec2IC vec) {
        return this.set(vec.x(), vec.y());
    }

    @Override
    public IVec2IM setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public IVec2IM setX(double x) {
        this.x = BananaMath.floor(x);
        return this;
    }

    @Override
    public IVec2IM setY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public IVec2IM setY(double y) {
        this.y = BananaMath.floor(y);
        return this;
    }

    @Override
    public IVec2IM setZero() {
        this.x = 0;
        this.y = 0;
        return this;
    }

    @Override
    public IVec2IM collapseStack() {
        this.stateStack = null;
        return this;
    }

    @Override
    public IVec2IM resetStack() {
        if (this.stateStack != null) {
            this.stateStack.reset();
        }
        return this;
    }

    @Override
    public IVec2IM pushPos() {
        if (this.stateStack == null) {
            this.stateStack = new IntStack(2);
        }
        int[] arr = this.stateStack.newEntry();
        arr[0] = this.x;
        arr[1] = this.y;
        return this;
    }

    @Override
    public IVec2IM popPos() {
        if (this.stateStack == null) {
            throw new IllegalStateException();
        }
        return this.set(this.stateStack.pull());
    }

    @Override
    public int x() {
        return this.x;
    }

    @Override
    public int y() {
        return this.y;
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2I yx() {
        return new Vec2I(this.y, this.x);
    }

    @Override
    public IVec3I xyz(int z) {
        return new Vec3I(this.x, this.y, z);
    }

    @Override
    public IVec3I xzy(int z) {
        return new Vec3I(this.x, z, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I zxy(int z) {
        return new Vec3I(z, this.x, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I yxz(int z) {
        return new Vec3I(this.y, this.x, z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I yzx(int z) {
        return new Vec3I(this.y, z, this.x);
    }

    @Override
    public IVec3I zyx(int z) {
        return new Vec3I(z, this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2IM yx(IVec2IM out) {
        return out.set(this.y, this.x);
    }

    @Override
    public IVec3IM xyz(IVec3IM out, int z) {
        return out.set(this.x, this.y, z);
    }

    @Override
    public IVec3IM xzy(IVec3IM out, int z) {
        return out.set(this.x, z, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM zxy(IVec3IM out, int z) {
        return out.set(z, this.x, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM yxz(IVec3IM out, int z) {
        return out.set(this.y, this.x, z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM yzx(IVec3IM out, int z) {
        return out.set(this.y, z, this.x);
    }

    @Override
    public IVec3IM zyx(IVec3IM out, int z) {
        return out.set(z, this.y, this.x);
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.lengthSq());
    }

    @Override
    public float lengthSq() {
        return this.x * this.x + this.y * this.y;
    }

    @Override
    public int dot(int x, int y) {
        return this.x * x + this.y * y;
    }

    @Override
    public double dot(double x, double y) {
        return this.x * x + this.y * y;
    }

    @Override
    public int cross(int x, int y) {
        return this.x * y - this.y * x;
    }

    @Override
    public double cross(double x, double y) {
        return this.x * y - this.y * x;
    }

    @Override
    public int dot(IVec2IC vec) {
        return this.dot(vec.x(), vec.y());
    }

    @Override
    public double dot(IVec2DC vec) {
        return this.dot(vec.x(), vec.y());
    }

    @Override
    public int cross(IVec2IC vec) {
        return this.cross(vec.x(), vec.y());
    }

    @Override
    public double cross(IVec2DC vec) {
        return this.cross(vec.x(), vec.y());
    }

    @Override
    public float distance(int x, int y) {
        long deltaX = x - this.x;
        long deltaY = y - this.y;
        return (float) Math.sqrt(deltaY * deltaY + deltaX * deltaX);
    }

    @Override
    public double distance(double x, double y) {
        double deltaX = x - this.x;
        double deltaY = y - this.y;
        return Math.sqrt(deltaY * deltaY + deltaX * deltaX);
    }

    @Override
    public float distance(IVec2IC vec) {
        return this.distance(vec.x(), vec.y());
    }

    @Override
    public double distance(IVec2DC vec) {
        return this.distance(vec.x(), vec.y());
    }

    @Override
    public float distanceSq(int x, int y) {
        long deltaX = x - this.x;
        long deltaY = y - this.y;
        return deltaY * deltaY + deltaX * deltaX;
    }

    @Override
    public double distanceSq(double x, double y) {
        double deltaX = x - this.x;
        double deltaY = y - this.y;
        return deltaY * deltaY + deltaX * deltaX;
    }

    @Override
    public float distanceSq(IVec2IC vec) {
        return this.distanceSq(vec.x(), vec.y());
    }

    @Override
    public double distanceSq(IVec2DC vec) {
        return this.distanceSq(vec.x(), vec.y());
    }

    @Override
    public IVec2IM add(int x, int y) {
        return this.set(this.x + x, this.y + y);
    }

    @Override
    public IVec2IM add(double x, double y) {
        return this.set(this.x + x, this.y + y);
    }

    @Override
    public IVec2IM add(IVec2IC vec) {
        return this.add(vec.x(), vec.y());
    }

    @Override
    public IVec2IM add(IVec2DC vec) {
        return this.add(vec.x(), vec.y());
    }

    @Override
    public IVec2IM subtract(int x, int y) {
        return this.set(this.x - x, this.y - y);
    }

    @Override
    public IVec2IM subtract(double x, double y) {
        return this.set(this.x - x, this.y - y);
    }

    @Override
    public IVec2IM subtract(IVec2IC vec) {
        return this.subtract(vec.x(), vec.y());
    }

    @Override
    public IVec2IM subtract(IVec2DC vec) {
        return this.subtract(vec.x(), vec.y());
    }

    @Override
    public IVec2IM scale(int val) {
        return this.set(this.x * val, this.y * val);
    }

    @Override
    public IVec2IM scale(int x, int y) {
        return this.set(this.x * x, this.y * y);
    }

    @Override
    public IVec2IM scale(double x, double y) {
        return this.set(this.x * x, this.y * y);
    }

    @Override
    public IVec2IM scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y());
    }

    @Override
    public IVec2IM scale(double val) {
        return this.set(this.x * val, this.y * val);
    }

    @Override
    public IVec2IM scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y());
    }

    @Override
    public IVec2IM divide(int val) {
        return this.set(this.x / val, this.y / val);
    }

    @Override
    public IVec2IM divide(int x, int y) {
        return this.set(this.x / x, this.y / y);
    }

    @Override
    public IVec2IM divide(double val) {
        return this.set(this.x / val, this.y / val);
    }

    @Override
    public IVec2IM divide(double x, double y) {
        return this.set(this.x / x, this.y / y);
    }

    @Override
    public IVec2IM divide(IVec2IC vec) {
        return this.divide(vec.x(), vec.y());
    }

    @Override
    public IVec2IM divide(IVec2DC vec) {
        return this.divide(vec.x(), vec.y());
    }

    @Override
    public IVec2IM rotate(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return this.set(BananaMath.round(this.x * cos + this.y * sin), BananaMath.round(this.y * cos - this.x * sin));
    }

    @Override
    public IVec2IM rotateAround(int x, int y, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return this.set(BananaMath.round(((this.x - x) * cos + (this.y - y) * sin) + x), BananaMath.round(((this.y - y) * cos - (this.x - x) * sin) + y));
    }

    @Override
    public IVec2IM rotateAround(double x, double y, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return this.set(BananaMath.round(((this.x - x) * cos + (this.y - y) * sin) + x), BananaMath.round(((this.y - y) * cos - (this.x - x) * sin) + y));
    }

    @Override
    public IVec2IM rotateAround(IVec2DC point, double degrees) {
        return this.rotateAround(point.x(), point.y(), degrees);
    }

    @Override
    public IVec2IM rotateAround(IVec2IC point, double degrees) {
        return this.rotateAround(point.x(), point.y(), degrees);
    }

    @Override
    public IVec2IM lerp(int x, int y, double delta) {
        return this.set(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.y + (y - this.y) * delta)
        );
    }

    @Override
    public IVec2IM lerp(double x, double y, double delta) {
        return this.set(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.y + (y - this.y) * delta)
        );
    }

    @Override
    public IVec2IM lerp(IVec2IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), delta);
    }

    @Override
    public IVec2IM lerp(IVec2DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), delta);
    }

    @Override
    public IVec2IM wrap(IBox2I box) {
        return this.wrap(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2IM wrap(IBox2D box) {
        return this.wrap(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2IM wrap(int x, int y) {
        return this.wrap(0, 0, x, y);
    }

    @Override
    public IVec2IM wrap(double x, double y) {
        return this.wrap(0.0D, 0.0D, x, y);
    }

    @Override
    public IVec2IM wrap(int minX, int minY, int maxX, int maxY) {
        int rangeX = (maxX - minX), rangeY = (maxY - minY);
        return this.set(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY);
    }

    @Override
    public IVec2IM wrap(double minX, double minY, double maxX, double maxY) {
        double rangeX = (maxX - minX), rangeY = (maxY - minY);
        return this.set(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY);
    }

    @Override
    public IVec2IM clamp(IBox2I box) {
        return this.clamp(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2IM clamp(IBox2D box) {
        return this.clamp(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2IM clamp(int x, int y) {
        return this.clamp(0, 0, x, y);
    }

    @Override
    public IVec2IM clamp(double x, double y) {
        return this.clamp(0.0D, 0.0D, x, y);
    }

    @Override
    public IVec2IM clamp(int minX, int minY, int maxX, int maxY) {
        return this.set(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)));
    }

    @Override
    public IVec2IM clamp(double minX, double minY, double maxX, double maxY) {
        return this.set(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)));
    }

    @Override
    public IVec2DM normalize() {
        double l = Math.sqrt(this.x * this.x + this.y * this.y);
        return l < BananaMath.DOUBLE_EPS ? new Vec2DM(0, 0) : new Vec2DM(this.x / l, this.y / l);
    }

    @Override
    public IVec2IM toMutable() {
        return this;
    }

    @Override
    public IVec2I toImmutable() {
        return new Vec2I(this);
    }

    @Override
    public IVec2DM toDouble() {
        return new Vec2DM(this);
    }

    @Override
    public IVec2IM copy() {
        Vec2IM vec = new Vec2IM(this);
        if (this.stateStack != null) {
            vec.stateStack = this.stateStack.copy();
        }
        return vec;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IVec2I) {
            IVec2I vec = (IVec2I) obj;
            return vec.x() == this.x && vec.y() == this.y;
        } else if (obj instanceof IVec2D) {
            IVec2D vec = (IVec2D) obj;
            return vec.x() == this.x && vec.y() == this.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(this.x) + Double.hashCode(this.y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
