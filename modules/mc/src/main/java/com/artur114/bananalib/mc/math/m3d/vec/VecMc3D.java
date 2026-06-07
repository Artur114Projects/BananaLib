package com.artur114.bananalib.mc.math.m3d.vec;

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
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2DM;
import com.artur114.bananalib.math.m2d.vec.Vec2D;
import com.artur114.bananalib.math.m3d.vec.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

public class VecMc3D extends Vec3d implements IVecMc3D {
    public static final VecMc3D ZERO = new VecMc3D(0, 0, 0);

    public VecMc3D(double xIn, double yIn, double zIn) {
        super(xIn, yIn, zIn);
    }

    public VecMc3D(Vec3i vector) {
        super(vector);
    }

    public VecMc3D(Entity source) {
        this(source.posX, source.posY, source.posZ);
    }

    public VecMc3D(Vec3d vec) {
        this(vec.x, vec.y, vec.z);
    }

    public VecMc3D(IVec3D vec) {
        this(vec.x(), vec.y(), vec.z());
    }

    public VecMc3D(IVec3I vec) {
        this(vec.x(), vec.y(), vec.z());
    }

    public VecMc3D(IVec2DC vec, double z) {
        this(vec.x(), vec.y(), z);
    }

    public VecMc3D(IVec2IC vec, int z) {
        this(vec.x(), vec.y(), z);
    }

    public VecMc3D(IVec2IC vec, double z) {
        this(vec.x(), vec.y(), z);
    }

    public VecMc3D(IVec2DC vec, int z) {
        this(vec.x(), vec.y(), z);
    }

    public VecMc3D(IVec2DC vec) {
        this(vec.x(), vec.y(), 0);
    }

    public VecMc3D(IVec2IC vec) {
        this(vec.x(), vec.y(), 0);
    }

    public VecMc3D(double x, IVec2DC vec) {
        this(x, vec.x(), vec.y());
    }

    public VecMc3D(int x, IVec2IC vec) {
        this(x, vec.x(), vec.y());
    }

    public VecMc3D(double x, IVec2IC vec) {
        this(x, vec.x(), vec.y());
    }

    public VecMc3D(int x, IVec2DC vec) {
        this(x, vec.x(), vec.y());
    }


    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public IVec2D xy() {
        return new Vec2D(this.x, this.y);
    }

    @Override
    public IVec2D xz() {
        return new Vec2D(this.x, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2D yx() {
        return new Vec2D(this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2D yz() {
        return new Vec2D(this.y, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2D zx() {
        return new Vec2D(this.z, this.x);
    }

    @Override
    public IVec2D zy() {
        return new Vec2D(this.z, this.y);
    }

    @Override
    public VecMc3D zyx() {
        return new VecMc3D(this.z, this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public VecMc3D zxy() {
        return new VecMc3D(this.z, this.x, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public VecMc3D yzx() {
        return new VecMc3D(this.y, this.z, this.x);
    }

    @Override
    public VecMc3D xzy() {
        return new VecMc3D(this.x, this.z, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public VecMc3D yxz() {
        return new VecMc3D(this.y, this.x, this.z);
    }

    @Override
    public IVec2DM xy(IVec2DM out) {
        return out.set(this.x, this.y);
    }

    @Override
    public IVec2DM xz(IVec2DM out) {
        return out.set(this.x, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2DM yx(IVec2DM out) {
        return out.set(this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2DM yz(IVec2DM out) {
        return out.set(this.y, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2DM zx(IVec2DM out) {
        return out.set(this.z, this.x);
    }

    @Override
    public IVec2DM zy(IVec2DM out) {
        return out.set(this.z, this.y);
    }

    @Override
    public IVec3DM zyx(IVec3DM out) {
        return out.set(this.z, this.y, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3DM zxy(IVec3DM out) {
        return out.set(this.z, this.x, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3DM yzx(IVec3DM out) {
        return out.set(this.y, this.z, this.x);
    }

    @Override
    public IVec3DM xzy(IVec3DM out) {
        return out.set(this.x, this.z, this.y);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3DM yxz(IVec3DM out) {
        return out.set(this.y, this.x, this.z);
    }

    @Override
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    public double lengthSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Override
    public double dot(int x, int y, int z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public double dot(double x, double y, double z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public VecMc3D cross(int x, int y, int z) {
        return new VecMc3D(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public VecMc3D cross(double x, double y, double z) {
        return new VecMc3D(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public double dot(IVec3IC vec) {
        return this.dot(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double dot(IVec3DC vec) {
        return this.dot(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D cross(IVec3IC vec) {
        return this.cross(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D cross(IVec3DC vec) {
        return this.cross(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double distance(int x, int y, int z) {
        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return Math.sqrt(deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
    }

    @Override
    public double distance(double x, double y, double z) {
        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return Math.sqrt(deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
    }

    @Override
    public double distance(IVec3IC vec) {
        return this.distance(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double distance(IVec3DC vec) {
        return this.distance(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double distanceSq(int x, int y, int z) {
        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ;
    }

    @Override
    public double distanceSq(double x, double y, double z) {
        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
        return deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ;
    }

    @Override
    public double distanceSq(IVec3IC vec) {
        return this.distanceSq(vec.x(), vec.y(), vec.z());
    }

    @Override
    public double distanceSq(IVec3DC vec) {
        return this.distanceSq(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D add(int x, int y, int z) {
        return new VecMc3D(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public VecMc3D add(double x, double y, double z) {
        return new VecMc3D(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public VecMc3D add(Vec3i vec) {
        return this.add(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public @NotNull VecMc3D add(Vec3d vec) {
        return this.add(vec.x, vec.y, vec.z);
    }

    @Override
    public VecMc3D add(IVec3IC vec) {
        return this.add(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D add(IVec3DC vec) {
        return this.add(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D add(IVec2IC vec, int z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D add(IVec2IC vec, double z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D add(IVec2DC vec, int z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D add(IVec2DC vec, double z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D add(IVec2IC vec) {
        return this.add(vec.x(), vec.y(), 0);
    }

    @Override
    public VecMc3D add(IVec2DC vec) {
        return this.add(vec.x(), vec.y(), 0);
    }

    @Override
    public VecMc3D subtract(int x, int y, int z) {
        return new VecMc3D(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public @NotNull VecMc3D subtract(double x, double y, double z) {
        return new VecMc3D(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public VecMc3D subtract(Vec3i vec) {
        return this.subtract(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public @NotNull VecMc3D subtract(Vec3d vec) {
        return this.subtract(vec.x, vec.y, vec.z);
    }

    @Override
    public VecMc3D subtract(IVec3IC vec) {
        return this.subtract(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D subtract(IVec3DC vec) {
        return this.subtract(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D subtract(IVec2IC vec, int z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D subtract(IVec2IC vec, double z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D subtract(IVec2DC vec, int z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D subtract(IVec2DC vec, double z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D subtract(IVec2IC vec) {
        return this.subtract(vec.x(), vec.y(), 0);
    }

    @Override
    public VecMc3D subtract(IVec2DC vec) {
        return this.subtract(vec.x(), vec.y(), 0);
    }

    @Override
    public VecMc3D scale(int val) {
        return new VecMc3D(this.x * val, this.y * val, this.z * val);
    }

    @Override
    public @NotNull VecMc3D scale(double val) {
        return new VecMc3D(this.x * val, this.y * val, this.z * val);
    }

    @Override
    public VecMc3D scale(int x, int y, int z) {
        return new VecMc3D(this.x * x, this.y * y, this.z * z);
    }

    @Override
    public VecMc3D scale(double x, double y, double z) {
        return new VecMc3D(this.x * x, this.y * y, this.z * z);
    }

    @Override
    public VecMc3D scale(Vec3i vec) {
        return this.scale(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public VecMc3D scale(Vec3d vec) {
        return this.scale(vec.x, vec.y, vec.z);
    }

    @Override
    public VecMc3D scale(IVec3IC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D scale(IVec3DC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D scale(IVec2IC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D scale(IVec2IC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D scale(IVec2DC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D scale(IVec2DC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y(), 1);
    }

    @Override
    public VecMc3D scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public VecMc3D divide(int val) {
        return new VecMc3D(this.x / val, this.y / val, this.z / val);
    }

    @Override
    public VecMc3D divide(double val) {
        return new VecMc3D(this.x / val, this.y / val, this.z / val);
    }

    @Override
    public VecMc3D divide(int x, int y, int z) {
        return new VecMc3D(this.x / x, this.y / y, this.z / z);
    }

    @Override
    public VecMc3D divide(double x, double y, double z) {
        return new VecMc3D(this.x / x, this.y / y, this.z / z);
    }

    @Override
    public VecMc3D divide(Vec3i vec) {
        return this.divide(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public VecMc3D divide(Vec3d vec) {
        return this.divide(vec.x, vec.y, vec.z);
    }

    @Override
    public VecMc3D divide(IVec3IC vec) {
        return this.divide(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D divide(IVec3DC vec) {
        return this.divide(vec.x(), vec.y(), vec.z());
    }

    @Override
    public VecMc3D divide(IVec2IC vec, int z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D divide(IVec2IC vec, double z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D divide(IVec2DC vec, int z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D divide(IVec2DC vec, double z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public VecMc3D divide(IVec2IC vec) {
        return this.divide(vec.x(), vec.y(), 1);
    }

    @Override
    public VecMc3D divide(IVec2DC vec) {
        return this.divide(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public VecMc3D rotateX(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(this.x, this.y * cos - this.z * sin, this.y * sin + this.z * cos);
    }

    @Override
    public VecMc3D rotateY(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(this.x * cos + this.z * sin, this.y, this.z * cos - this.x * sin);
    }

    @Override
    public VecMc3D rotateZ(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(this.x * cos - this.y * sin, this.y * cos + this.x * sin, this.z);
    }

    @Override
    public VecMc3D rotateXAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(this.x, ((this.y - y) * cos - (this.z - z) * sin) + y, ((this.z - z) * cos + (this.y - y) * sin) + z);
    }

    @Override
    public VecMc3D rotateXAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(this.x, ((this.y - y) * cos - (this.z - z) * sin) + y, ((this.z - z) * cos + (this.y - y) * sin) + z);
    }

    @Override
    public VecMc3D rotateXAround(IVec3IC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public VecMc3D rotateXAround(IVec3DC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public VecMc3D rotateYAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(((this.x - x) * cos + (this.z - z) * sin) + x, this.y, ((this.z - z) * cos - (this.x - x) * sin) + z);
    }

    @Override
    public VecMc3D rotateYAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(((this.x - x) * cos + (this.z - z) * sin) + x, this.y, ((this.z - z) * cos - (this.x - x) * sin) + z);
    }

    @Override
    public VecMc3D rotateYAround(IVec3IC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public VecMc3D rotateYAround(IVec3DC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public VecMc3D rotateZAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(((this.x - x) * cos - (this.y - y) * sin) + x, ((this.y - y) * cos + (this.x - x) * sin) + y, this.z);
    }

    @Override
    public VecMc3D rotateZAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new VecMc3D(((this.x - x) * cos - (this.y - y) * sin) + x, ((this.y - y) * cos + (this.x - x) * sin) + y, this.z);
    }

    @Override
    public VecMc3D rotateZAround(IVec3IC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public VecMc3D rotateZAround(IVec3DC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public VecMc3D lerp(int x, int y, int z, double delta) {
        return new VecMc3D(
            this.x + (x - this.x) * delta,
            this.y + (y - this.y) * delta,
            this.z + (z - this.z) * delta
        );
    }

    @Override
    public VecMc3D lerp(double x, double y, double z, double delta) {
        return new VecMc3D(
            this.x + (x - this.x) * delta,
            this.y + (y - this.y) * delta,
            this.z + (z - this.z) * delta
        );
    }

    @Override
    public VecMc3D lerp(IVec3IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), vec.z(), delta);
    }

    @Override
    public VecMc3D lerp(IVec3DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), vec.z(), delta);
    }

    @Override
    public VecMc3D lerp(IVec2IC vec, int z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public VecMc3D lerp(IVec2IC vec, double z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public VecMc3D lerp(IVec2DC vec, int z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public VecMc3D lerp(IVec2DC vec, double z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public VecMc3D lerp(IVec2IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), 0, delta);
    }

    @Override
    public VecMc3D lerp(IVec2DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), 0.0D, delta);
    }

    @Override
    public VecMc3D wrap(IBox3IC box) {
        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public VecMc3D wrap(IBox3DC box) {
        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public VecMc3D wrap(IBox2IC box, int minZ, int maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D wrap(IBox2IC box, double minZ, double maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D wrap(IBox2DC box, int minZ, int maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D wrap(IBox2DC box, double minZ, double maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D wrap(IBox2IC box) {
        return this.wrap(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public VecMc3D wrap(IBox2DC box) {
        return this.wrap(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public VecMc3D wrap(int x, int y, int z) {
        return this.wrap(0, 0, 0, x, y, z);
    }

    @Override
    public VecMc3D wrap(double x, double y, double z) {
        return this.wrap(0, 0, 0, x, y, z);
    }

    @Override
    public VecMc3D wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        double rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
        return new VecMc3D(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY, minZ + ((this.z - minZ) % rangeZ + rangeZ) % rangeZ);
    }

    @Override
    public VecMc3D wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
        return new VecMc3D(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY, minZ + ((this.z - minZ) % rangeZ + rangeZ) % rangeZ);
    }

    @Override
    public VecMc3D clamp(IBox3IC box) {
        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public VecMc3D clamp(IBox3DC box) {
        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public VecMc3D clamp(IBox2IC box, int minZ, int maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D clamp(IBox2IC box, double minZ, double maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D clamp(IBox2DC box, int minZ, int maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D clamp(IBox2DC box, double minZ, double maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public VecMc3D clamp(IBox2IC box) {
        return this.clamp(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public VecMc3D clamp(IBox2DC box) {
        return this.clamp(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public VecMc3D clamp(int x, int y, int z) {
        return this.clamp(0, 0, 0, x, y, z);
    }

    @Override
    public VecMc3D clamp(double x, double y, double z) {
        return this.clamp(0, 0, 0, x, y, z);
    }

    @Override
    public VecMc3D clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new VecMc3D(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)), Math.max(minZ, Math.min(maxZ, this.z)));
    }

    @Override
    public VecMc3D clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new VecMc3D(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)), Math.max(minZ, Math.min(maxZ, this.z)));
    }

    @Override
    public @NotNull VecMc3D normalize() {
        double l = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return l < BananaMath.DOUBLE_EPS ? ZERO : new VecMc3D(this.x / l, this.y / l, this.z / l);
    }

    @Override
    public IVec3DM toMutable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public VecMc3D toImmutable() {
        return this;
    }

    @Override
    public IPosMc3I floor() {
        return new PosMc3I(BananaMath.floor(this.x), BananaMath.floor(this.y), BananaMath.floor(this.z));
    }

    @Override
    public IPosMc3I round() {
        return new PosMc3I(BananaMath.round(this.x), BananaMath.round(this.y), BananaMath.round(this.z));
    }

    @Override
    public IPosMc3I ceil() {
        return new PosMc3I(BananaMath.ceil(this.x), BananaMath.ceil(this.y), BananaMath.ceil(this.z));
    }

    @Override
    public VecMc3D copy() {
        return new VecMc3D((Vec3d) this);
    }

    @Override
    public @NotNull String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IVec3D) {
            IVec3D vec = (IVec3D) obj;
            return vec.x() == this.x && vec.y() == this.y && vec.z() == this.z;
        } else if (obj instanceof IVec3I) {
            IVec3I vec = (IVec3I) obj;
            return vec.x() == this.x && vec.y() == this.y && vec.z() == this.z;
        }
        return false;
    }

    @Override
    public boolean equalsEps(Object obj, double eps) {
        if (obj instanceof IVec3D) {
            IVec3D vec = (IVec3D) obj;
            return Math.abs(vec.x() - this.x) <= eps && Math.abs(vec.y() - this.y) <= eps && Math.abs(vec.z() - this.z) <= eps;
        } else if (obj instanceof IVec3I) {
            IVec3I vec = (IVec3I) obj;
            return Math.abs(vec.x() - this.x) <= eps && Math.abs(vec.y() - this.y) <= eps && Math.abs(vec.z() - this.z) <= eps;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Hasher.hash(this.x, this.y, this.z);
    }
}
