package com.artur114.bananalib.math.m3d.vec;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.core.m3d.box.IBox3DC;
import com.artur114.bananalib.math.core.m3d.box.IBox3IC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC;
import com.artur114.bananalib.math.internal.Hasher;
import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.vec.*;
import com.artur114.bananalib.math.m3d.box.IBox3D;
import com.artur114.bananalib.math.m3d.box.IBox3I;

public class Vec3I implements IVec3I {
    public static final Vec3I ZERO = new Vec3I(0, 0, 0);
    private final int x, y, z;

    public Vec3I(double x, double y, double z) {
        this.x = BananaMath.floor(x);
        this.y = BananaMath.floor(y);
        this.z = BananaMath.floor(z);
    }

    public Vec3I(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3I(IVec3DC vec) {
        this(vec.x(), vec.y(), vec.z());
    }

    public Vec3I(IVec3IC vec) {
        this(vec.x(), vec.y(), vec.z());
    }

    public Vec3I(IVec2DC vec, double z) {
        this(vec.x(), vec.y(), z);
    }

    public Vec3I(IVec2IC vec, int z) {
        this(vec.x(), vec.y(), z);
    }

    public Vec3I(IVec2IC vec, double z) {
        this(vec.x(), vec.y(), z);
    }

    public Vec3I(IVec2DC vec, int z) {
        this(vec.x(), vec.y(), z);
    }

    public Vec3I(IVec2DC vec) {
        this(vec.x(), vec.y(), 0);
    }

    public Vec3I(IVec2IC vec) {
        this(vec.x(), vec.y(), 0);
    }

    public Vec3I(double x, IVec2DC vec) {
        this(x, vec.x(), vec.y());
    }

    public Vec3I(int x, IVec2IC vec) {
        this(x, vec.x(), vec.y());
    }

    public Vec3I(double x, IVec2IC vec) {
        this(x, vec.x(), vec.y());
    }

    public Vec3I(int x, IVec2DC vec) {
        this(x, vec.x(), vec.y());
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
    public int z() {
        return this.z;
    }

    @Override
    public IVec2I xy() {
        return new Vec2I(this.x, this.y);
    }

    @Override
    public IVec2I xz() {
        return new Vec2I(this.x, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2I yx() {
        return new Vec2I(this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2I yz() {
        return new Vec2I(this.y, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2I zx() {
        return new Vec2I(this.z, this.x);
    }

    @Override
    public IVec2I zy() {
        return new Vec2I(this.z, this.y);
    }

    @Override
    public IVec3I zyx() {
        return new Vec3I(this.z, this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I zxy() {
        return new Vec3I(this.z, this.x, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I yzx() {
        return new Vec3I(this.y, this.z, this.x);
    }

    @Override
    public IVec3I xzy() {
        return new Vec3I(this.x, this.z, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I yxz() {
        return new Vec3I(this.y, this.x, this.z);
    }

    @Override
    public IVec2IM xy(IVec2IM out) {
        return out.set(this.x, this.y);
    }

    @Override
    public IVec2IM xz(IVec2IM out) {
        return out.set(this.x, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2IM yx(IVec2IM out) {
        return out.set(this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2IM yz(IVec2IM out) {
        return out.set(this.y, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2IM zx(IVec2IM out) {
        return out.set(this.z, this.x);
    }

    @Override
    public IVec2IM zy(IVec2IM out) {
        return out.set(this.z, this.y);
    }

    @Override
    public IVec3IM zyx(IVec3IM out) {
        return out.set(this.z, this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM zxy(IVec3IM out) {
        return out.set(this.z, this.x, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM yzx(IVec3IM out) {
        return out.set(this.y, this.z, this.x);
    }

    @Override
    public IVec3IM xzy(IVec3IM out) {
        return out.set(this.x, this.z, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM yxz(IVec3IM out) {
        return out.set(this.y, this.x, this.z);
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    public float lengthSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Override
    public int dot(int x, int y, int z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public double dot(double x, double y, double z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public IVec3I cross(int x, int y, int z) {
        return new Vec3I(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public IVec3D cross(double x, double y, double z) {
        return new Vec3D(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public int dot(IVec3IC vec) {
        return this.dot(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double dot(IVec3DC vec) {
        return this.dot(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I cross(IVec3IC vec) {
        return this.cross(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3D cross(IVec3DC vec) {
        return this.cross(vec.x(), vec.y(), vec.z());
    }

    @Override
    public float distance(int x, int y, int z) {
        long deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return (float) Math.sqrt(deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
    }

    @Override
    public double distance(double x, double y, double z) {
        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return Math.sqrt(deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
    }

    @Override
    public float distance(IVec3IC vec) {
        return this.distance(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double distance(IVec3DC vec) {
        return this.distance(vec.x(), vec.y(), vec.z());
    }

    @Override
    public float distanceSq(int x, int y, int z) {
        long deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ;
    }

    @Override
    public double distanceSq(double x, double y, double z) {
        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return (deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
    }

    @Override
    public float distanceSq(IVec3IC vec) {
        return this.distanceSq(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double distanceSq(IVec3DC vec) {
        return this.distanceSq(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I add(int x, int y, int z) {
        return new Vec3I(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public IVec3I add(double x, double y, double z) {
        return new Vec3I(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public IVec3I add(IVec3IC vec) {
        return this.add(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I add(IVec3DC vec) {
        return this.add(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I add(IVec2IC vec, int z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I add(IVec2IC vec, double z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I add(IVec2DC vec, int z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I add(IVec2DC vec, double z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I add(IVec2IC vec) {
        return this.add(vec.x(), vec.y(), 0);
    }

    @Override
    public IVec3I add(IVec2DC vec) {
        return this.add(vec.x(), vec.y(), 0);
    }

    @Override
    public IVec3I subtract(int x, int y, int z) {
        return new Vec3I(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public IVec3I subtract(double x, double y, double z) {
        return new Vec3I(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public IVec3I subtract(IVec3IC vec) {
        return this.subtract(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I subtract(IVec3DC vec) {
        return this.subtract(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I subtract(IVec2IC vec, int z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I subtract(IVec2IC vec, double z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I subtract(IVec2DC vec, int z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I subtract(IVec2DC vec, double z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I subtract(IVec2IC vec) {
        return this.subtract(vec.x(), vec.y(), 0);
    }

    @Override
    public IVec3I subtract(IVec2DC vec) {
        return this.subtract(vec.x(), vec.y(), 0);
    }

    @Override
    public IVec3I scale(int val) {
        return new Vec3I(this.x * val, this.y * val, this.z * val);
    }

    @Override
    public IVec3I scale(double val) {
        return new Vec3I(this.x * val, this.y * val, this.z * val);
    }

    @Override
    public IVec3I scale(int x, int y, int z) {
        return new Vec3I(this.x * x, this.y * y, this.z * z);
    }

    @Override
    public IVec3I scale(double x, double y, double z) {
        return new Vec3I(this.x * x, this.y * y, this.z * z);
    }

    @Override
    public IVec3I scale(IVec3IC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I scale(IVec3DC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I scale(IVec2IC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I scale(IVec2IC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I scale(IVec2DC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I scale(IVec2DC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y(), 1);
    }

    @Override
    public IVec3I scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IVec3I divide(int val) {
        return new Vec3I(this.x / val, this.y / val, this.z / val);
    }

    @Override
    public IVec3I divide(double val) {
        return new Vec3I(this.x / val, this.y / val, this.z / val);
    }

    @Override
    public IVec3I divide(int x, int y, int z) {
        return new Vec3I(this.x / x, this.y / y, this.z / z);
    }

    @Override
    public IVec3I divide(double x, double y, double z) {
        return new Vec3I(this.x / x, this.y / y, this.z / z);
    }

    @Override
    public IVec3I divide(IVec3IC vec) {
        return this.divide(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I divide(IVec3DC vec) {
        return this.divide(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3I divide(IVec2IC vec, int z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I divide(IVec2IC vec, double z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I divide(IVec2DC vec, int z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I divide(IVec2DC vec, double z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3I divide(IVec2IC vec) {
        return this.divide(vec.x(), vec.y(), 1);
    }

    @Override
    public IVec3I divide(IVec2DC vec) {
        return this.divide(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IVec3I rotateX(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(this.x, BananaMath.round(this.y * cos + this.z * sin), BananaMath.round(this.z * cos - this.y * sin));
    }

    @Override
    public IVec3I rotateY(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(BananaMath.round(this.x * cos + this.z * sin), this.y, BananaMath.round(this.z * cos - this.x * sin));
    }

    @Override
    public IVec3I rotateZ(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(BananaMath.round(this.x * cos + this.y * sin), BananaMath.round(this.y * cos - this.x * sin), this.z);
    }

    @Override
    public IVec3I rotateXAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(this.x, BananaMath.round(((this.y - y) * cos + (this.z - z) * sin) + y), BananaMath.round(((this.z - z) * cos - (this.y - y) * sin) + z));
    }

    @Override
    public IVec3I rotateXAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(this.x, BananaMath.round(((this.y - y) * cos + (this.z - z) * sin) + y), BananaMath.round(((this.z - z) * cos - (this.y - y) * sin) + z));
    }

    @Override
    public IVec3I rotateXAround(IVec3IC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IVec3I rotateXAround(IVec3DC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IVec3I rotateYAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(((this.x - x) * cos + (this.z - z) * sin) + x, this.y, ((this.z - z) * cos - (this.x - x) * sin) + z);
    }

    @Override
    public IVec3I rotateYAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(((this.x - x) * cos + (this.z - z) * sin) + x, this.y, ((this.z - z) * cos - (this.x - x) * sin) + z);
    }

    @Override
    public IVec3I rotateYAround(IVec3IC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IVec3I rotateYAround(IVec3DC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IVec3I rotateZAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(((this.x - x) * cos + (this.y - y) * sin) + x, ((this.y - y) * cos - (this.x - x) * sin) + y, this.z);
    }

    @Override
    public IVec3I rotateZAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = Math.sin(rad), cos = Math.cos(rad);
        return new Vec3I(((this.x - x) * cos + (this.y - y) * sin) + x, ((this.y - y) * cos - (this.x - x) * sin) + y, this.z);
    }

    @Override
    public IVec3I rotateZAround(IVec3IC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IVec3I rotateZAround(IVec3DC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IVec3I lerp(int x, int y, int z, double delta) {
        return new Vec3I(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.y + (y - this.y) * delta),
            BananaMath.round(this.z + (z - this.z) * delta)
        );
    }

    @Override
    public IVec3I lerp(double x, double y, double z, double delta) {
        return new Vec3I(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.y + (y - this.y) * delta),
            BananaMath.round(this.z + (z - this.z) * delta)
        );
    }

    @Override
    public IVec3I lerp(IVec3IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), vec.z(), delta);
    }

    @Override
    public IVec3I lerp(IVec3DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), vec.z(), delta);
    }

    @Override
    public IVec3I lerp(IVec2IC vec, int z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public IVec3I lerp(IVec2IC vec, double z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public IVec3I lerp(IVec2DC vec, int z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public IVec3I lerp(IVec2DC vec, double z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public IVec3I lerp(IVec2IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), 0, delta);
    }

    @Override
    public IVec3I lerp(IVec2DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), 0.0D, delta);
    }

    @Override
    public IVec3I wrap(IBox3IC box) {
        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IVec3I wrap(IBox3DC box) {
        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IVec3I wrap(IBox2IC box, int minZ, int maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I wrap(IBox2IC box, double minZ, double maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I wrap(IBox2DC box, int minZ, int maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I wrap(IBox2DC box, double minZ, double maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I wrap(IBox2IC box) {
        return this.wrap(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 1);
    }

    @Override
    public IVec3I wrap(IBox2DC box) {
        return this.wrap(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 1);
    }

    @Override
    public IVec3I wrap(int x, int y, int z) {
        return this.wrap(0, 0, 0, x, y, z);
    }

    @Override
    public IVec3I wrap(double x, double y, double z) {
        return this.wrap(0, 0, 0, x, y, z);
    }

    @Override
    public IVec3I wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        int rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
        return new Vec3I(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY, minZ + ((this.z - minZ) % rangeZ + rangeZ) % rangeZ);
    }

    @Override
    public IVec3I wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
        return new Vec3I(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY, minZ + ((this.z - minZ) % rangeZ + rangeZ) % rangeZ);
    }

    @Override
    public IVec3I clamp(IBox3IC box) {
        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IVec3I clamp(IBox3DC box) {
        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IVec3I clamp(IBox2IC box, int minZ, int maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I clamp(IBox2IC box, double minZ, double maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I clamp(IBox2DC box, int minZ, int maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I clamp(IBox2DC box, double minZ, double maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IVec3I clamp(IBox2IC box) {
        return this.clamp(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IVec3I clamp(IBox2DC box) {
        return this.clamp(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IVec3I clamp(int x, int y, int z) {
        return this.clamp(0, 0, 0, x, y, z);
    }

    @Override
    public IVec3I clamp(double x, double y, double z) {
        return this.clamp(0, 0, 0, x, y, z);
    }

    @Override
    public IVec3I clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new Vec3I(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)), Math.max(minZ, Math.min(maxZ, this.z)));
    }

    @Override
    public IVec3I clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new Vec3I(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)), Math.max(minZ, Math.min(maxZ, this.z)));
    }

    @Override
    public IVec3D normalize() {
        double l = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return l < BananaMath.DOUBLE_EPS ? Vec3D.ZERO : new Vec3D(this.x / l, this.y / l, this.z / l);
    }

    @Override
    public IVec3IM toMutable() {
        return new Vec3IM(this);
    }

    @Override
    public IVec3I toImmutable() {
        return this;
    }

    @Override
    public IVec3D toDouble() {
        return new Vec3D(this);
    }

    @Override
    public IVec3I copy() {
        return new Vec3I(this);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override
    public int hashCode() {
        return Hasher.hash(this.x, this.y, this.z);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IVec3I && ((IVec3I) obj).x() == this.x && ((IVec3I) obj).y() == this.y && ((IVec3I) obj).z() == this.z) ||
                (obj instanceof IVec3D && ((IVec3D) obj).x() == this.x && ((IVec3D) obj).y() == this.y && ((IVec3D) obj).z() == this.z);
    }
}
