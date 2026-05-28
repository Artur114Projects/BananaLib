package com.artur114.bananalib.math.m2d.vec;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m3d.vec.*;

public class Vec2I implements IVec2I {
    private final int x, y;

    public Vec2I(double x, double y) {
        this.x = BananaMath.floor(x);
        this.y = BananaMath.floor(y);
    }

    public Vec2I(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2I(IVec2DC vec2D) {
        this(vec2D.x(), vec2D.y());
    }

    public Vec2I(IVec2IC vec2D) {
        this(vec2D.x(), vec2D.y());
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
    public IVec2I add(int x, int y) {
        return new Vec2I(this.x + x, this.y + y);
    }

    @Override
    public IVec2I add(double x, double y) {
        return new Vec2I(this.x + x, this.y + y);
    }

    @Override
    public IVec2I add(IVec2IC vec) {
        return this.add(vec.x(), vec.y());
    }

    @Override
    public IVec2I add(IVec2DC vec) {
        return this.add(vec.x(), vec.y());
    }

    @Override
    public IVec2I subtract(int x, int y) {
        return new Vec2I(this.x - x, this.y - y);
    }

    @Override
    public IVec2I subtract(double x, double y) {
        return new Vec2I(this.x - x, this.y - y);
    }

    @Override
    public IVec2I subtract(IVec2IC vec) {
        return this.subtract(vec.x(), vec.y());
    }

    @Override
    public IVec2I subtract(IVec2DC vec) {
        return this.subtract(vec.x(), vec.y());
    }

    @Override
    public IVec2I scale(int x, int y)  {
        return new Vec2I(this.x * x, this.y * y);
    }

    @Override
    public IVec2I scale(double val) {
        return new Vec2I(this.x * val, this.y * val);
    }

    @Override
    public IVec2I scale(double x, double y) {
        return new Vec2I(this.x * x, this.y * y);
    }

    @Override
    public IVec2I scale(int val) {
        return new Vec2I(this.x * val, this.y * val);
    }

    @Override
    public IVec2I scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y());
    }

    @Override
    public IVec2I scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y());
    }

    @Override
    public IVec2I divide(int val) {
        return new Vec2I(this.x / val, this.y / val);
    }

    @Override
    public IVec2I divide(int x, int y) {
        return new Vec2I(this.x / x, this.y / y);
    }

    @Override
    public IVec2I divide(double val) {
        return new Vec2I(this.x / val, this.y / val);
    }

    @Override
    public IVec2I divide(double x, double y) {
        return new Vec2I(this.x / x, this.y / y);
    }

    @Override
    public IVec2I divide(IVec2IC vec) {
        return this.divide(vec.x(), vec.y());
    }

    @Override
    public IVec2I divide(IVec2DC vec) {
        return this.divide(vec.x(), vec.y());
    }

    @Override
    public IVec2I rotate(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec2I(BananaMath.round(this.x * cos + this.y * sin), BananaMath.round(this.y * cos - this.x * sin));
    }

    @Override
    public IVec2I rotateAround(int x, int y, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec2I(BananaMath.round(((this.x - x) * cos + ((this.y - y) * sin)) + x), BananaMath.round(((this.y - y) * cos - (this.x - x) * sin) + y));
    }

    @Override
    public IVec2I rotateAround(double x, double y, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec2I(BananaMath.round(((this.x - x) * cos + ((this.y - y) * sin)) + x), BananaMath.round(((this.y - y) * cos - (this.x - x) * sin) + y));
    }

    @Override
    public IVec2I rotateAround(IVec2DC point, double degrees) {
        return this.rotateAround(point.x(), point.y(), degrees);
    }

    @Override
    public IVec2I rotateAround(IVec2IC point, double degrees) {
        return this.rotateAround(point.x(), point.y(), degrees);
    }

    @Override
    public IVec2I lerp(int x, int y, double delta) {
        return new Vec2I(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.y + (y - this.y) * delta)
        );
    }

    @Override
    public IVec2I lerp(double x, double y, double delta) {
        return new Vec2I(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.y + (y - this.y) * delta)
        );
    }

    @Override
    public IVec2I lerp(IVec2IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), delta);
    }

    @Override
    public IVec2I lerp(IVec2DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), delta);
    }

    @Override
    public IVec2I wrap(IBox2I box) {
        return this.wrap(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2I wrap(IBox2D box) {
        return this.wrap(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2I wrap(int x, int y) {
        return this.wrap(0, 0, x, y);
    }

    @Override
    public IVec2I wrap(double x, double y) {
        return this.wrap(0.0D, 0.0D, x, y);
    }

    @Override
    public IVec2I wrap(int minX, int minY, int maxX, int maxY) {
        int rangeX = (maxX - minX), rangeY = (maxY - minY);
        return new Vec2I(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY);
    }

    @Override
    public IVec2I wrap(double minX, double minY, double maxX, double maxY) {
        double rangeX = (maxX - minX), rangeY = (maxY - minY);
        return new Vec2I(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY);
    }

    @Override
    public IVec2I clamp(IBox2I box) {
        return this.clamp(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2I clamp(IBox2D box) {
        return this.clamp(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IVec2I clamp(int x, int y) {
        return this.clamp(0, 0, x, y);
    }

    @Override
    public IVec2I clamp(double x, double y) {
        return this.clamp(0.0D, 0.0D, x, y);
    }

    @Override
    public IVec2I clamp(int minX, int minY, int maxX, int maxY) {
        return new Vec2I(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)));
    }

    @Override
    public IVec2I clamp(double minX, double minY, double maxX, double maxY) {
        return new Vec2I(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)));
    }

    @Override
    public IVec2D normalize() {
        double l = Math.sqrt(this.x * this.x + this.y * this.y);
        return l < BananaMath.DOUBLE_EPS ? Vec2D.ZERO : new Vec2D(this.x / l, this.y / l);
    }

    @Override
    public IVec2IM toMutable() {
        return new Vec2IM(this);
    }

    @Override
    public IVec2I toImmutable() {
        return this;
    }

    @Override
    public IVec2D toDouble() {
        return new Vec2D(this);
    }

    @Override
    public IVec2I copy() {
        return new Vec2I(this);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IVec2I && ((IVec2I) obj).x() == this.x && ((IVec2I) obj).y() == this.y) ||
                (obj instanceof IVec2D && ((IVec2D) obj).x() == this.x && ((IVec2D) obj).y() == this.y);
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
