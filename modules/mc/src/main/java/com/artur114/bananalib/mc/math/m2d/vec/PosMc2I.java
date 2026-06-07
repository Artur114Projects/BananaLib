package com.artur114.bananalib.mc.math.m2d.vec;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.vec.*;
import com.artur114.bananalib.math.m3d.vec.IVec3I;
import com.artur114.bananalib.math.m3d.vec.IVec3IM;
import com.artur114.bananalib.math.m3d.vec.Vec3I;
import com.artur114.bananalib.mc.math.m3d.vec.IPosMc3I;
import com.artur114.bananalib.mc.math.m3d.vec.PosMc3I;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.jetbrains.annotations.NotNull;

public class PosMc2I extends ChunkPos implements IPosMc2I {
    public PosMc2I(int x, int z) {
        super(x, z);
    }

    public PosMc2I(double x, double z) {
        super(BananaMath.floor(x), BananaMath.floor(z));
    }

    public PosMc2I(BlockPos pos) {
        super(pos);
    }

    public PosMc2I(IVec2DC vec2D) {
        this(vec2D.x(), vec2D.y());
    }

    public PosMc2I(IVec2IC vec2D) {
        this(vec2D.x(), vec2D.y());
    }

    @Override
    public int x() {
        return this.x;
    }

    @Override
    public int y() {
        return this.z;
    }

    @Override
    public PosMc2I yx() {
        return new PosMc2I(this.z, this.x);
    }

    @Override
    public IVec3I xyz(int z) {
        return new Vec3I(this.x, this.z, z);
    }

    @Override
    public IVec3I xzy(int z) {
        return new Vec3I(this.x, z, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I zxy(int z) {
        return new Vec3I(z, this.x, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3I yxz(int z) {
        return new Vec3I(this.z, this.x, z);
    }

    @Override
    public IVec3I yzx(int z) {
        return new Vec3I(this.z, z, this.x);
    }

    @Override
    public IVec3I zyx(int z) {
        return new Vec3I(z, this.z, this.x);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2IM yx(IVec2IM out) {
        return out.set(this.z, this.x);
    }

    @Override
    public IVec3IM xyz(IVec3IM out, int z) {
        return out.set(this.x, this.z, z);
    }

    @Override
    public IVec3IM xzy(IVec3IM out, int z) {
        return out.set(this.x, z, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM zxy(IVec3IM out, int z) {
        return out.set(z, this.x, this.z);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public IVec3IM yxz(IVec3IM out, int z) {
        return out.set(this.z, this.x, z);
    }

    @Override
    public IVec3IM yzx(IVec3IM out, int z) {
        return out.set(this.z, z, this.x);
    }

    @Override
    public IVec3IM zyx(IVec3IM out, int z) {
        return out.set(z, this.z, this.x);
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.lengthSq());
    }

    @Override
    public float lengthSq() {
        return this.x * this.x + this.z * this.z;
    }

    @Override
    public int dot(int x, int y) {
        return this.x * x + this.z * y;
    }

    @Override
    public double dot(double x, double y) {
        return this.x * x + this.z * y;
    }

    @Override
    public int cross(int x, int y) {
        return this.x * y - this.z * x;
    }

    @Override
    public double cross(double x, double y) {
        return this.x * y - this.z * x;
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
        long deltaY = y - this.z;
        return (float) Math.sqrt(deltaY * deltaY + deltaX * deltaX);
    }

    @Override
    public double distance(double x, double y) {
        double deltaX = x - this.x;
        double deltaY = y - this.z;
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
        long deltaY = y - this.z;
        return deltaY * deltaY + deltaX * deltaX;
    }

    @Override
    public double distanceSq(double x, double y) {
        double deltaX = x - this.x;
        double deltaY = y - this.z;
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
    public IPosMc3I block(int x, int y, int z) {
        return new PosMc3I((this.x << 4) + x, y, (this.z << 4) + z);
    }

    @Override
    public PosMc2I add(int x, int y) {
        return new PosMc2I(this.x + x, this.z + y);
    }

    @Override
    public PosMc2I add(double x, double y) {
        return new PosMc2I(this.x + x, this.z + y);
    }

    @Override
    public IPosMc2I add(ChunkPos pos) {
        return this.add(pos.x, pos.z);
    }

    @Override
    public PosMc2I add(IVec2IC vec) {
        return this.add(vec.x(), vec.y());
    }

    @Override
    public PosMc2I add(IVec2DC vec) {
        return this.add(vec.x(), vec.y());
    }

    @Override
    public PosMc2I subtract(int x, int y) {
        return new PosMc2I(this.x - x, this.z - y);
    }

    @Override
    public PosMc2I subtract(double x, double y) {
        return new PosMc2I(this.x - x, this.z - y);
    }

    @Override
    public IPosMc2I subtract(ChunkPos pos) {
        return this.subtract(pos.x, pos.z);
    }

    @Override
    public PosMc2I subtract(IVec2IC vec) {
        return this.subtract(vec.x(), vec.y());
    }

    @Override
    public PosMc2I subtract(IVec2DC vec) {
        return this.subtract(vec.x(), vec.y());
    }

    @Override
    public PosMc2I scale(int x, int y)  {
        return new PosMc2I(this.x * x, this.z * y);
    }

    @Override
    public PosMc2I scale(double val) {
        return new PosMc2I(this.x * val, this.z * val);
    }

    @Override
    public PosMc2I scale(double x, double y) {
        return new PosMc2I(this.x * x, this.z * y);
    }

    @Override
    public IPosMc2I scale(ChunkPos pos) {
        return this.scale(pos.x, pos.z);
    }

    @Override
    public PosMc2I scale(int val) {
        return new PosMc2I(this.x * val, this.z * val);
    }

    @Override
    public PosMc2I scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y());
    }

    @Override
    public PosMc2I scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y());
    }

    @Override
    public PosMc2I divide(int val) {
        return new PosMc2I(this.x / val, this.z / val);
    }

    @Override
    public PosMc2I divide(int x, int y) {
        return new PosMc2I(this.x / x, this.z / y);
    }

    @Override
    public PosMc2I divide(double val) {
        return new PosMc2I(this.x / val, this.z / val);
    }

    @Override
    public PosMc2I divide(double x, double y) {
        return new PosMc2I(this.x / x, this.z / y);
    }

    @Override
    public IPosMc2I divide(ChunkPos pos) {
        return this.divide(pos.x, pos.z);
    }

    @Override
    public PosMc2I divide(IVec2IC vec) {
        return this.divide(vec.x(), vec.y());
    }

    @Override
    public PosMc2I divide(IVec2DC vec) {
        return this.divide(vec.x(), vec.y());
    }

    @Override
    public PosMc2I rotate(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc2I(
            BananaMath.round(this.x * cos - this.z * sin),
            BananaMath.round(this.z * cos + this.x * sin)
        );
    }

    @Override
    public PosMc2I rotateAround(int x, int y, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc2I(
            BananaMath.round(((this.x - x) * cos - ((this.z - y) * sin)) + x),
            BananaMath.round(((this.z - y) * cos + (this.x - x) * sin) + y)
        );
    }

    @Override
    public PosMc2I rotateAround(double x, double y, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rad = Math.toRadians(degrees);
        double sin = BananaMath.sin(rad), cos = BananaMath.cos(rad);
        return new PosMc2I(
            BananaMath.round(((this.x - x) * cos - ((this.z - y) * sin)) + x),
            BananaMath.round(((this.z - y) * cos + (this.x - x) * sin) + y)
        );
    }

    @Override
    public PosMc2I rotateAround(IVec2DC point, double degrees) {
        return this.rotateAround(point.x(), point.y(), degrees);
    }

    @Override
    public PosMc2I rotateAround(IVec2IC point, double degrees) {
        return this.rotateAround(point.x(), point.y(), degrees);
    }

    @Override
    public PosMc2I lerp(int x, int y, double delta) {
        return new PosMc2I(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.z + (y - this.z) * delta)
        );
    }

    @Override
    public PosMc2I lerp(double x, double y, double delta) {
        return new PosMc2I(
            BananaMath.round(this.x + (x - this.x) * delta),
            BananaMath.round(this.z + (y - this.z) * delta)
        );
    }

    @Override
    public PosMc2I lerp(IVec2IC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), delta);
    }

    @Override
    public PosMc2I lerp(IVec2DC vec, double delta) {
        return this.lerp(vec.x(), vec.y(), delta);
    }

    @Override
    public PosMc2I wrap(IBox2I box) {
        return this.wrap(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public PosMc2I wrap(IBox2D box) {
        return this.wrap(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public PosMc2I wrap(int x, int y) {
        return this.wrap(0, 0, x, y);
    }

    @Override
    public PosMc2I wrap(double x, double y) {
        return this.wrap(0.0D, 0.0D, x, y);
    }

    @Override
    public PosMc2I wrap(int minX, int minY, int maxX, int maxY) {
        int rangeX = (maxX - minX), rangeY = (maxY - minY);
        return new PosMc2I(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.z - minY) % rangeY + rangeY) % rangeY);
    }

    @Override
    public PosMc2I wrap(double minX, double minY, double maxX, double maxY) {
        double rangeX = (maxX - minX), rangeY = (maxY - minY);
        return new PosMc2I(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.z - minY) % rangeY + rangeY) % rangeY);
    }

    @Override
    public PosMc2I clamp(IBox2I box) {
        return this.clamp(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public PosMc2I clamp(IBox2D box) {
        return this.clamp(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public PosMc2I clamp(int x, int y) {
        return this.clamp(0, 0, x, y);
    }

    @Override
    public PosMc2I clamp(double x, double y) {
        return this.clamp(0.0D, 0.0D, x, y);
    }

    @Override
    public PosMc2I clamp(int minX, int minY, int maxX, int maxY) {
        return new PosMc2I(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.z)));
    }

    @Override
    public PosMc2I clamp(double minX, double minY, double maxX, double maxY) {
        return new PosMc2I(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.z)));
    }

    @Override
    public IVec2D normalize() {
        double l = Math.sqrt(this.x * this.x + this.z * this.z);
        return l < BananaMath.DOUBLE_EPS ? Vec2D.ZERO : new Vec2D(this.x / l, this.z / l);
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
        return new PosMc2I(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IVec2I) {
            IVec2I vec = (IVec2I) obj;
            return vec.x() == this.x && vec.y() == this.z;
        } else if (obj instanceof IVec2D) {
            IVec2D vec = (IVec2D) obj;
            return vec.x() == this.x && vec.y() == this.z;
        }
        return super.equals(obj);
    }

    @Override
    public @NotNull String toString() {
        return "(" + this.x + ", " + this.z + ")";
    }
}
