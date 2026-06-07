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
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m2d.vec.IVec2IM;
import com.artur114.bananalib.math.m2d.vec.Vec2I;
import com.artur114.bananalib.math.m3d.vec.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

public class PosMc3I extends BlockPos implements IPosMc3I {
    public PosMc3I(int x, int y, int z) {
        super(x, y, z);
    }

    public PosMc3I(double x, double y, double z) {
        super(x, y, z);
    }

    public PosMc3I(Entity source) {
        super(source);
    }

    public PosMc3I(Vec3d vec) {
        super(vec);
    }

    public PosMc3I(Vec3i source) {
        super(source);
    }

    public PosMc3I(IVec3DC vec) {
        this(vec.x(), vec.y(), vec.z());
    }

    public PosMc3I(IVec3IC vec) {
        this(vec.x(), vec.y(), vec.z());
    }

    public PosMc3I(IVec2DC vec, double z) {
        this(vec.x(), vec.y(), z);
    }

    public PosMc3I(IVec2IC vec, int z) {
        this(vec.x(), vec.y(), z);
    }

    public PosMc3I(IVec2IC vec, double z) {
        this(vec.x(), vec.y(), z);
    }

    public PosMc3I(IVec2DC vec, int z) {
        this(vec.x(), vec.y(), z);
    }

    public PosMc3I(IVec2DC vec) {
        this(vec.x(), vec.y(), 0);
    }

    public PosMc3I(IVec2IC vec) {
        this(vec.x(), vec.y(), 0);
    }

    public PosMc3I(double x, IVec2DC vec) {
        this(x, vec.x(), vec.y());
    }

    public PosMc3I(int x, IVec2IC vec) {
        this(x, vec.x(), vec.y());
    }

    public PosMc3I(double x, IVec2IC vec) {
        this(x, vec.x(), vec.y());
    }

    public PosMc3I(int x, IVec2DC vec) {
        this(x, vec.x(), vec.y());
    }
    
    @Override
    public int chunkX() {
        return this.getX() >> 4;
    }

    @Override
    public int chunkZ() {
        return this.getZ() >> 4;
    }

    @Override
    public int x() {
        return this.getX();
    }

    @Override
    public int y() {
        return this.getY();
    }

    @Override
    public int z() {
        return this.getZ();
    }

    @Override
    public IVec2I xy() {
        return new Vec2I(this.getX(), this.getY());
    }

    @Override
    public IVec2I xz() {
        return new Vec2I(this.getX(), this.getZ());
    }

    @Override
    public IVec2I yx() {
        return new Vec2I(this.getY(), this.getX());
    }

    @Override
    public IVec2I yz() {
        return new Vec2I(this.getY(), this.getZ());
    }

    @Override
    public IVec2I zx() {
        return new Vec2I(this.getZ(), this.getX());
    }

    @Override
    public IVec2I zy() {
        return new Vec2I(this.getZ(), this.getY());
    }

    @Override
    public IVec3I zyx() {
        return new PosMc3I(this.getZ(), this.getY(), this.getX());
    }

    @Override
    public IVec3I zxy() {
        return new PosMc3I(this.getZ(), this.getX(), this.getY());
    }

    @Override
    public IVec3I yzx() {
        return new PosMc3I(this.getY(), this.getZ(), this.getX());
    }

    @Override
    public IVec3I xzy() {
        return new PosMc3I(this.getX(), this.getZ(), this.getY());
    }

    @Override
    public IVec3I yxz() {
        return new PosMc3I(this.getY(), this.getX(), this.getZ());
    }

    @Override
    public IVec2IM xy(IVec2IM out) {
        return out.set(this.getX(), this.getY());
    }

    @Override
    public IVec2IM xz(IVec2IM out) {
        return out.set(this.getX(), this.getZ());
    }

    @Override
    public IVec2IM yx(IVec2IM out) {
        return out.set(this.getY(), this.getX());
    }

    @Override
    public IVec2IM yz(IVec2IM out) {
        return out.set(this.getY(), this.getZ());
    }

    @Override
    public IVec2IM zx(IVec2IM out) {
        return out.set(this.getZ(), this.getX());
    }

    @Override
    public IVec2IM zy(IVec2IM out) {
        return out.set(this.getZ(), this.getY());
    }

    @Override
    public IVec3IM zyx(IVec3IM out) {
        return out.set(this.getZ(), this.getY(), this.getX());
    }

    @Override
    public IVec3IM zxy(IVec3IM out) {
        return out.set(this.getZ(), this.getX(), this.getY());
    }

    @Override
    public IVec3IM yzx(IVec3IM out) {
        return out.set(this.getY(), this.getZ(), this.getX());
    }

    @Override
    public IVec3IM xzy(IVec3IM out) {
        return out.set(this.getX(), this.getZ(), this.getY());
    }

    @Override
    public IVec3IM yxz(IVec3IM out) {
        return out.set(this.getY(), this.getX(), this.getZ());
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ());
    }

    @Override
    public float lengthSq() {
        return this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ();
    }

    @Override
    public int dot(int x, int y, int z) {
        return this.getX() * x + this.getY() * y + this.getZ() * z;
    }

    @Override
    public double dot(double x, double y, double z) {
        return this.getX() * x + this.getY() * y + this.getZ() * z;
    }

    @Override
    public IVec3I cross(int x, int y, int z) {
        return new PosMc3I(this.getY() * z - this.getZ() * y, this.getZ() * x - this.getX() * z, this.getX() * y - this.getY() * x);
    }

    @Override
    public IVec3D cross(double x, double y, double z) {
        return new Vec3D(this.getY() * z - this.getZ() * y, this.getZ() * x - this.getX() * z, this.getX() * y - this.getY() * x);
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
        long deltaX = x - this.getX(), deltaY = y - this.getY(), deltaZ = z - this.getZ();
        return (float) Math.sqrt(deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
    }

    @Override
    public double distance(double x, double y, double z) {
        double deltaX = x - this.getX(), deltaY = y - this.getY(), deltaZ = z - this.getZ();
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
        long deltaX = x - this.getX(), deltaY = y - this.getY(), deltaZ = z - this.getZ();
        return deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ;
    }

    @Override
    public double distanceSq(double x, double y, double z) {
        double deltaX = x - this.getX(), deltaY = y - this.getY(), deltaZ = z - this.getZ();
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
    public @NotNull PosMc3I up(int n) {
        return new PosMc3I(this.getX(), this.getY() + n, this.getZ());
    }

    @Override
    public @NotNull PosMc3I up() {
        return this.up(1);
    }

    @Override
    public @NotNull PosMc3I offset(@NotNull EnumFacing facing, int n) {
        return n == 0 ? this : new PosMc3I(this.getX() + facing.getFrontOffsetX() * n, this.getY() + facing.getFrontOffsetY() * n, this.getZ() + facing.getFrontOffsetZ() * n);
    }

    @Override
    public @NotNull PosMc3I offset(@NotNull EnumFacing facing) {
        return this.offset(facing, 1);
    }

    @Override
    public @NotNull PosMc3I add(int x, int y, int z) {
        return new PosMc3I(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    @Override
    public @NotNull PosMc3I add(double x, double y, double z) {
        return new PosMc3I(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    @Override
    public PosMc3I add(IVec3IC vec) {
        return this.add(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I add(IVec3DC vec) {
        return this.add(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I add(Vec3d vec) {
        return this.add(vec.x, vec.y, vec.z);
    }

    @Override
    public @NotNull PosMc3I add(Vec3i vec) {
        return this.add(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public PosMc3I add(IVec2IC vec, int z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I add(IVec2IC vec, double z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I add(IVec2DC vec, int z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I add(IVec2DC vec, double z) {
        return this.add(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I add(IVec2IC vec) {
        return this.add(vec.x(), vec.y(), 0);
    }

    @Override
    public PosMc3I add(IVec2DC vec) {
        return this.add(vec.x(), vec.y(), 0);
    }

    @Override
    public PosMc3I subtract(int x, int y, int z) {
        return new PosMc3I(this.getX() - x, this.getY() - y, this.getZ() - z);
    }

    @Override
    public PosMc3I subtract(double x, double y, double z) {
        return new PosMc3I(this.getX() - x, this.getY() - y, this.getZ() - z);
    }

    @Override
    public PosMc3I subtract(Vec3d vec) {
        return this.subtract(vec.x, vec.y, vec.z);
    }

    @Override
    public @NotNull PosMc3I subtract(Vec3i vec) {
        return this.subtract(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public PosMc3I subtract(IVec3IC vec) {
        return this.subtract(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I subtract(IVec3DC vec) {
        return this.subtract(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I subtract(IVec2IC vec, int z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I subtract(IVec2IC vec, double z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I subtract(IVec2DC vec, int z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I subtract(IVec2DC vec, double z) {
        return this.subtract(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I subtract(IVec2IC vec) {
        return this.subtract(vec.x(), vec.y(), 0);
    }

    @Override
    public PosMc3I subtract(IVec2DC vec) {
        return this.subtract(vec.x(), vec.y(), 0);
    }

    @Override
    public PosMc3I scale(int val) {
        return new PosMc3I(this.getX() * val, this.getY() * val, this.getZ() * val);
    }

    @Override
    public PosMc3I scale(double val) {
        return new PosMc3I(this.getX() * val, this.getY() * val, this.getZ() * val);
    }

    @Override
    public PosMc3I scale(int x, int y, int z) {
        return new PosMc3I(this.getX() * x, this.getY() * y, this.getZ() * z);
    }

    @Override
    public PosMc3I scale(double x, double y, double z) {
        return new PosMc3I(this.getX() * x, this.getY() * y, this.getZ() * z);
    }

    @Override
    public PosMc3I scale(Vec3i vec) {
        return this.scale(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public PosMc3I scale(Vec3d vec) {
        return this.scale(vec.x, vec.y, vec.z);
    }

    @Override
    public PosMc3I scale(IVec3IC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I scale(IVec3DC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I scale(IVec2IC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I scale(IVec2IC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I scale(IVec2DC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I scale(IVec2DC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y(), 1);
    }

    @Override
    public PosMc3I scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public PosMc3I divide(int val) {
        return new PosMc3I(this.getX() / val, this.getY() / val, this.getZ() / val);
    }

    @Override
    public PosMc3I divide(double val) {
        return new PosMc3I(this.getX() / val, this.getY() / val, this.getZ() / val);
    }

    @Override
    public PosMc3I divide(int x, int y, int z) {
        return new PosMc3I(this.getX() / x, this.getY() / y, this.getZ() / z);
    }

    @Override
    public PosMc3I divide(double x, double y, double z) {
        return new PosMc3I(this.getX() / x, this.getY() / y, this.getZ() / z);
    }

    @Override
    public PosMc3I divide(Vec3i vec) {
        return this.divide(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public PosMc3I divide(Vec3d vec) {
        return this.divide(vec.x, vec.y, vec.z);
    }

    @Override
    public PosMc3I divide(IVec3IC vec) {
        return this.divide(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I divide(IVec3DC vec) {
        return this.divide(vec.x(), vec.y(), vec.z());
    }

    @Override
    public PosMc3I divide(IVec2IC vec, int z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I divide(IVec2IC vec, double z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I divide(IVec2DC vec, int z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I divide(IVec2DC vec, double z) {
        return this.divide(vec.x(), vec.y(), z);
    }

    @Override
    public PosMc3I divide(IVec2IC vec) {
        return this.divide(vec.x(), vec.y(), 1);
    }

    @Override
    public PosMc3I divide(IVec2DC vec) {
        return this.divide(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public PosMc3I rotateX(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(this.getX(), BananaMath.round(this.getY() * cos - this.getZ() * sin), BananaMath.round(this.getZ() * cos + this.getY() * sin));
    }

    @Override
    public PosMc3I rotateY(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(BananaMath.round(this.getX() * cos + this.getZ() * sin), this.getY(), BananaMath.round(this.getZ() * cos - this.getX() * sin));
    }

    @Override
    public PosMc3I rotateZ(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(BananaMath.round(this.getX() * cos - this.getY() * sin), BananaMath.round(this.getY() * cos + this.getX() * sin), this.getZ());
    }

    @Override
    public PosMc3I rotateXAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(this.getX(), BananaMath.round(((this.getY() - y) * cos - (this.getZ() - z) * sin) + y), BananaMath.round(((this.getZ() - z) * cos + (this.getY() - y) * sin) + z));
    }

    @Override
    public PosMc3I rotateXAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(this.getX(), BananaMath.round(((this.getY() - y) * cos - (this.getZ() - z) * sin) + y), BananaMath.round(((this.getZ() - z) * cos + (this.getY() - y) * sin) + z));
    }

    @Override
    public PosMc3I rotateXAround(IVec3IC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public PosMc3I rotateXAround(IVec3DC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public PosMc3I rotateYAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(((this.getX() - x) * cos + (this.getZ() - z) * sin) + x, this.getY(), ((this.getZ() - z) * cos - (this.getX() - x) * sin) + z);
    }

    @Override
    public PosMc3I rotateYAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(((this.getX() - x) * cos + (this.getZ() - z) * sin) + x, this.getY(), ((this.getZ() - z) * cos - (this.getX() - x) * sin) + z);
    }

    @Override
    public PosMc3I rotateYAround(IVec3IC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public PosMc3I rotateYAround(IVec3DC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public PosMc3I rotateZAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(((this.getX() - x) * cos - (this.getY() - y) * sin) + x, ((this.getY() - y) * cos + (this.getX() - x) * sin) + y, this.getZ());
    }

    @Override
    public PosMc3I rotateZAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc3I(((this.getX() - x) * cos - (this.getY() - y) * sin) + x, ((this.getY() - y) * cos + (this.getX() - x) * sin) + y, this.getZ());
    }

    @Override
    public PosMc3I rotateZAround(IVec3IC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public PosMc3I rotateZAround(IVec3DC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public PosMc3I lerp(int x, int y, int z, double delta) {
        return new PosMc3I(
                BananaMath.round(this.getX() + (x - this.getX()) * delta),
                BananaMath.round(this.getY() + (y - this.getY()) * delta),
                BananaMath.round(this.getZ() + (z - this.getZ()) * delta)
        );
    }

    @Override
    public PosMc3I lerp(double x, double y, double z, double delta) {
        return new PosMc3I(
                BananaMath.round(this.getX() + (x - this.getX()) * delta),
                BananaMath.round(this.getY() + (y - this.getY()) * delta),
                BananaMath.round(this.getZ() + (z - this.getZ()) * delta)
        );
    }

    @Override
    public PosMc3I lerp(IVec3IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), vec.z(), delta);
    }

    @Override
    public PosMc3I lerp(IVec3DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), vec.z(), delta);
    }

    @Override
    public PosMc3I lerp(IVec2IC vec, int z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public PosMc3I lerp(IVec2IC vec, double z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public PosMc3I lerp(IVec2DC vec, int z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public PosMc3I lerp(IVec2DC vec, double z, double delta) {
        return this.lerp(vec.x(), vec.y(), z, delta);
    }

    @Override
    public PosMc3I lerp(IVec2IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), 0, delta);
    }

    @Override
    public PosMc3I lerp(IVec2DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), 0.0D, delta);
    }

    @Override
    public PosMc3I wrap(IBox3IC box) {
        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public PosMc3I wrap(IBox3DC box) {
        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public PosMc3I wrap(IBox2IC box, int minZ, int maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I wrap(IBox2IC box, double minZ, double maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I wrap(IBox2DC box, int minZ, int maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I wrap(IBox2DC box, double minZ, double maxZ) {
        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I wrap(IBox2IC box) {
        return this.wrap(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public PosMc3I wrap(IBox2DC box) {
        return this.wrap(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public PosMc3I wrap(int x, int y, int z) {
        return this.wrap(0, 0, 0, x, y, z);
    }

    @Override
    public PosMc3I wrap(double x, double y, double z) {
        return this.wrap(0, 0, 0, x, y, z);
    }

    @Override
    public PosMc3I wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        int rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
        return new PosMc3I(minX + ((this.getX() - minX) % rangeX + rangeX) % rangeX, minY + ((this.getY() - minY) % rangeY + rangeY) % rangeY, minZ + ((this.getZ() - minZ) % rangeZ + rangeZ) % rangeZ);
    }

    @Override
    public PosMc3I wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
        return new PosMc3I(minX + ((this.getX() - minX) % rangeX + rangeX) % rangeX, minY + ((this.getY() - minY) % rangeY + rangeY) % rangeY, minZ + ((this.getZ() - minZ) % rangeZ + rangeZ) % rangeZ);
    }

    @Override
    public PosMc3I clamp(IBox3IC box) {
        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public PosMc3I clamp(IBox3DC box) {
        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public PosMc3I clamp(IBox2IC box, int minZ, int maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I clamp(IBox2IC box, double minZ, double maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I clamp(IBox2DC box, int minZ, int maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I clamp(IBox2DC box, double minZ, double maxZ) {
        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public PosMc3I clamp(IBox2IC box) {
        return this.clamp(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public PosMc3I clamp(IBox2DC box) {
        return this.clamp(box.minX(), box.minY(), Integer.MIN_VALUE, box.maxX(), box.maxY(), Integer.MAX_VALUE);
    }

    @Override
    public PosMc3I clamp(int x, int y, int z) {
        return this.clamp(0, 0, 0, x, y, z);
    }

    @Override
    public PosMc3I clamp(double x, double y, double z) {
        return this.clamp(0, 0, 0, x, y, z);
    }

    @Override
    public PosMc3I clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new PosMc3I(Math.max(minX, Math.min(maxX, this.getX())), Math.max(minY, Math.min(maxY, this.getY())), Math.max(minZ, Math.min(maxZ, this.getZ())));
    }

    @Override
    public PosMc3I clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new PosMc3I(Math.max(minX, Math.min(maxX, this.getX())), Math.max(minY, Math.min(maxY, this.getY())), Math.max(minZ, Math.min(maxZ, this.getZ())));
    }

    @Override
    public IVecMc3D normalize() {
        double l = Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ());
        return l < BananaMath.DOUBLE_EPS ? VecMc3D.ZERO : new VecMc3D(this.getX() / l, this.getY() / l, this.getZ() / l);
    }

    @Override
    public @NotNull PosMc3I toImmutable() {
        return this;
    }

    @Override
    public PosMc3IM toMutable() {
        return new PosMc3IM((Vec3i) this);
    }

    @Override
    public IVecMc3D toDouble() {
        return new VecMc3D((Vec3i) this);
    }

    @Override
    public PosMc3I copy() {
        return new PosMc3I((Vec3i) this);
    }

    @Override
    public @NotNull String toString() {
        return "(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IVec3I) {
            IVec3I vec = (IVec3I) obj;
            return vec.x() == this.getX() && vec.y() == this.getY() && vec.z() == this.getZ();
        } else if (obj instanceof IVec3D) {
            IVec3D vec = (IVec3D) obj;
            return vec.x() == this.getX() && vec.y() == this.getY() && vec.z() == this.getZ();
        }
        return super.equals(obj);
    }
}
