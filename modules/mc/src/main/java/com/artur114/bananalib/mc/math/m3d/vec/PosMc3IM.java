//package com.artur114.bananalib.mc.math.m3d.vec;
//
//import com.artur114.bananalib.math.BananaMath;
//import com.artur114.bananalib.math.internal.IntStack;
//import com.artur114.bananalib.math.internal.ThreadLocalPool;
//import com.artur114.bananalib.math.m2d.box.IBox2D;
//import com.artur114.bananalib.math.m2d.box.IBox2I;
//import com.artur114.bananalib.math.m2d.vec.*;
//import com.artur114.bananalib.math.m3d.box.IBox3D;
//import com.artur114.bananalib.math.m3d.box.IBox3I;
//import com.artur114.bananalib.math.m3d.vec.*;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Vec3d;
//import net.minecraft.util.math.Vec3i;
//import org.jetbrains.annotations.NotNull;
//
//public class PosMc3IM extends BlockPos implements IVec3IM {
//    private static final ThreadLocalPool<PosMc3IM> pool = new ThreadLocalPool<>(new PosMc3IM[4], PosMc3IM::new, vec -> {
//        vec.resetStack().setZero();
//        vec.released = true;
//        return vec;
//    }, vec -> {
//        vec.released = false;
//        return vec;
//    }, vec -> vec.released);
//
//    public static PosMc3IM obtain() {
//        return pool.obtain();
//    }
//
//    public static void release(PosMc3IM vec) {
//        pool.release(vec);
//    }
//
//    private IntStack stateStack = null;
//    private boolean released;
//    private int x, y, z;
//
//    public PosMc3IM() {this(0, 0, 0);}
//
//    public PosMc3IM(double x, double y, double z) {
//        super(0, 0, 0);
//        this.x = BananaMath.floor(x);
//        this.y = BananaMath.floor(y);
//        this.z = BananaMath.floor(z);
//    }
//
//    public PosMc3IM(int x, int y, int z) {
//        super(0, 0, 0);
//        this.x = x;
//        this.y = y;
//        this.z = z;
//    }
//
//    public PosMc3IM(IVec3D vec) {
//        this(vec.x(), vec.y(), vec.z());
//    }
//
//    public PosMc3IM(IVec3I vec) {
//        this(vec.x(), vec.y(), vec.z());
//    }
//
//    public PosMc3IM(IVec2D vec2D, double z) {
//        this(vec2D.x(), vec2D.y(), z);
//    }
//
//    public PosMc3IM(IVec2I vec2D, int z) {
//        this(vec2D.x(), vec2D.y(), z);
//    }
//
//    public PosMc3IM(IVec2I vec2D, double z) {
//        this(vec2D.x(), vec2D.y(), z);
//    }
//
//    public PosMc3IM(IVec2D vec2D, int z) {
//        this(vec2D.x(), vec2D.y(), z);
//    }
//
//    public PosMc3IM(IVec2D vec2D) {
//        this(vec2D.x(), vec2D.y(), 0);
//    }
//
//    public PosMc3IM(IVec2I vec2D) {
//        this(vec2D.x(), vec2D.y(), 0);
//    }
//
//    public PosMc3IM(double x, IVec2D vec2D) {
//        this(x, vec2D.x(), vec2D.y());
//    }
//
//    public PosMc3IM(int x, IVec2I vec2D) {
//        this(x, vec2D.x(), vec2D.y());
//    }
//
//    public PosMc3IM(double x, IVec2I vec2D) {
//        this(x, vec2D.x(), vec2D.y());
//    }
//
//    public PosMc3IM(int x, IVec2D vec2D) {
//        this(x, vec2D.x(), vec2D.y());
//    }
//
//    public PosMc3IM(Vec3d vec) {
//        this(vec.x, vec.y, vec.z);
//    }
//
//    public PosMc3IM(Vec3i vec) {
//        this(vec.getX(), vec.getY(), vec.getZ());
//    }
//
//    public PosMc3IM(Entity source) {
//        this(source.posX, source.posY, source.posZ);
//    }
//
//    public PosMc3IM(PosMc3IM pos) {
//        this(pos.x(), pos.y(), pos.z());
//    }
//
//    @Override
//    public PosMc3IM set(int[] pos) {
//        if (pos.length < 3) {
//            throw new IllegalArgumentException();
//        }
//        this.x = pos[0];
//        this.y = pos[1];
//        this.z = pos[2];
//        return this;
//    }
//
//    @Override
//    public PosMc3IM set(double x, double y, double z) {
//        this.x = BananaMath.floor(x);
//        this.y = BananaMath.floor(y);
//        this.z = BananaMath.floor(z);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM set(int x, int y, int z) {
//        this.x = x;
//        this.y = y;
//        this.z = z;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM set(IVec3I vec) {
//        return this.set(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM set(IVec3D vec) {
//        return this.set(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM set(IVec2D vec, double z) {
//        return this.set(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM set(IVec2D vec, int z) {
//        return this.set(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM set(IVec2I vec, double z) {
//        return this.set(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM set(IVec2I vec, int z) {
//        return this.set(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM set(IVec2D vec) {
//        return this.set(vec.x(), vec.y(), 0);
//    }
//
//    @Override
//    public PosMc3IM set(IVec2I vec) {
//        return this.set(vec.x(), vec.y(), 0);
//    }
//
//    @Override
//    public PosMc3IM setZero() {
//        this.x = 0;
//        this.y = 0;
//        this.z = 0;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM collapseStack() {
//        this.stateStack = null;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM resetStack() {
//        if (this.stateStack != null) {
//            this.stateStack.reset();
//        }
//        return this;
//    }
//
//    @Override
//    public PosMc3IM pushPos() {
//        if (this.stateStack == null) {
//            this.stateStack = new IntStack(3);
//        }
//        int[] arr = this.stateStack.newEntry();
//        arr[0] = this.x;
//        arr[1] = this.y;
//        arr[2] = this.z;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM popPos() {
//        if (this.stateStack == null) {
//            throw new IllegalStateException();
//        }
//        return this.set(this.stateStack.pull());
//    }
//
//    @Override
//    public PosMc3IM setX(int x) {
//        this.x = x;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM setX(double x) {
//        this.x = BananaMath.floor(x);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM setY(int y) {
//        this.y = y;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM setY(double y) {
//        this.y = BananaMath.floor(y);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM setZ(int z) {
//        this.z = z;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM setZ(double z) {
//        this.z = BananaMath.floor(z);
//        return this;
//    }
//
//    @Override
//    public int x() {
//        return this.x;
//    }
//
//    @Override
//    public int y() {
//        return this.y;
//    }
//
//    @Override
//    public int z() {
//        return this.z;
//    }
//
//    @Override
//    public int getX() {
//        return this.x;
//    }
//
//    @Override
//    public int getY() {
//        return this.y;
//    }
//
//    @Override
//    public int getZ() {
//        return this.z;
//    }
//
//    @Override
//    public IVec2I xy() {
//        return new Vec2I(this.x, this.y);
//    }
//
//    @Override
//    public IVec2I xz() {
//        return new Vec2I(this.x, this.z);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec2I yx() {
//        return new Vec2I(this.y, this.x);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec2I yz() {
//        return new Vec2I(this.y, this.z);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec2I zx() {
//        return new Vec2I(this.z, this.x);
//    }
//
//    @Override
//    public IVec2I zy() {
//        return new Vec2I(this.z, this.y);
//    }
//
//    @Override
//    public IVec3I zyx() {
//        return new Vec3I(this.z, this.y, this.x);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec3I zxy() {
//        return new Vec3I(this.z, this.x, this.y);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec3I yzx() {
//        return new Vec3I(this.y, this.z, this.x);
//    }
//
//    @Override
//    public IVec3I xzy() {
//        return new Vec3I(this.x, this.z, this.y);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec3I yxz() {
//        return new Vec3I(this.y, this.x, this.z);
//    }
//
//    @Override
//    public IVec2IM xy(IVec2IM out) {
//        return out.set(this.x, this.y);
//    }
//
//    @Override
//    public IVec2IM xz(IVec2IM out) {
//        return out.set(this.x, this.z);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec2IM yx(IVec2IM out) {
//        return out.set(this.y, this.x);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec2IM yz(IVec2IM out) {
//        return out.set(this.y, this.z);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec2IM zx(IVec2IM out) {
//        return out.set(this.z, this.x);
//    }
//
//    @Override
//    public IVec2IM zy(IVec2IM out) {
//        return out.set(this.z, this.y);
//    }
//
//    @Override
//    public IVec3IM zyx(IVec3IM out) {
//        return out.set(this.z, this.y, this.x);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec3IM zxy(IVec3IM out) {
//        return out.set(this.z, this.x, this.y);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec3IM yzx(IVec3IM out) {
//        return out.set(this.y, this.z, this.x);
//    }
//
//    @Override
//    public IVec3IM xzy(IVec3IM out) {
//        return out.set(this.x, this.z, this.y);
//    }
//
//    @Override
//    @SuppressWarnings("SuspiciousNameCombination")
//    public IVec3IM yxz(IVec3IM out) {
//        return out.set(this.y, this.x, this.z);
//    }
//
//    @Override
//    public float length() {
//        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
//    }
//
//    @Override
//    public float lengthSq() {
//        return this.x * this.x + this.y * this.y + this.z * this.z;
//    }
//
//    @Override
//    public int dot(IVec3I vec) {
//        return this.x * vec.x() + this.y * vec.y() + this.z * vec.z();
//    }
//
//    @Override
//    public double dot(IVec3D vec) {
//        return this.x * vec.x() + this.y * vec.y() + this.z * vec.z();
//    }
//
//    @Override
//    public IVec3I cross(IVec3I vec) {
//        return new Vec3I(BananaMath.round((long) this.y * vec.z() - (long) this.z * vec.y()), BananaMath.round((long) this.z * vec.x() - (long) this.x * vec.z()), BananaMath.round((long) this.x * vec.y() - (long) this.y * vec.x()));
//    }
//
//    @Override
//    public IVec3D cross(IVec3D vec) {
//        return new Vec3D((long) this.y * vec.z() - (long) this.z * vec.y(), (long) this.z * vec.x() - (long) this.x * vec.z(), (long) this.x * vec.y() - (long) this.y * vec.x());
//    }
//
//    @Override
//    public float distance(int x, int y, int z) {
//        long deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
//        return (float) Math.sqrt(deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
//    }
//
//    @Override
//    public double distance(double x, double y, double z) {
//        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
//        return Math.sqrt(deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
//    }
//
//    @Override
//    public float distance(IVec3I vec) {
//        return this.distance(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public double distance(IVec3D vec) {
//        return this.distance(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public float distanceSq(int x, int y, int z) {
//        long deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
//        return deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ;
//    }
//
//    @Override
//    public double distanceSq(double x, double y, double z) {
//        double deltaX = x - this.x, deltaY = y - this.y, deltaZ = z - this.z;
//        return (deltaY * deltaY + deltaX * deltaX + deltaZ * deltaZ);
//    }
//
//    @Override
//    public float distanceSq(IVec3I vec) {
//        return this.distanceSq(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public double distanceSq(IVec3D vec) {
//        return this.distanceSq(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public @NotNull PosMc3IM add(int x, int y, int z) {
//        this.x += x;
//        this.y += y;
//        this.z += z;
//        return this;
//    }
//
//    @Override
//    public @NotNull PosMc3IM add(double x, double y, double z) {
//        this.x += BananaMath.floor(x);
//        this.y += BananaMath.floor(y);
//        this.z += BananaMath.floor(z);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM add(IVec3I vec) {
//        return this.add(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM add(IVec3D vec) {
//        return this.add(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM add(IVec2I vec, int z) {
//        return this.add(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM add(IVec2I vec, double z) {
//        return this.add(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM add(IVec2D vec, int z) {
//        return this.add(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM add(IVec2D vec, double z) {
//        return this.add(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM add(IVec2I vec) {
//        return this.add(vec.x(), vec.y(), 0);
//    }
//
//    @Override
//    public PosMc3IM add(IVec2D vec) {
//        return this.add(vec.x(), vec.y(), 0);
//    }
//
//    @Override
//    public PosMc3IM subtract(int x, int y, int z) {
//        this.x -= x;
//        this.y -= y;
//        this.z -= z;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM subtract(double x, double y, double z) {
//        this.x -= BananaMath.floor(x);
//        this.y -= BananaMath.floor(y);
//        this.z -= BananaMath.floor(z);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec3I vec) {
//        return this.subtract(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec3D vec) {
//        return this.subtract(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec2I vec, int z) {
//        return this.subtract(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec2I vec, double z) {
//        return this.subtract(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec2D vec, int z) {
//        return this.subtract(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec2D vec, double z) {
//        return this.subtract(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec2I vec) {
//        return this.subtract(vec.x(), vec.y(), 0);
//    }
//
//    @Override
//    public PosMc3IM subtract(IVec2D vec) {
//        return this.subtract(vec.x(), vec.y(), 0);
//    }
//
//    @Override
//    public PosMc3IM scale(int val) {
//        this.x *= val;
//        this.y *= val;
//        this.z *= val;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM scale(double val) {
//        this.set(this.x * val, this.y * val, this.z * val);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM scale(int x, int y, int z) {
//        this.x *= x;
//        this.y *= y;
//        this.z *= z;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM scale(double x, double y, double z) {
//        this.set(this.x * x, this.y * y, this.z * z);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM scale(IVec3I vec) {
//        return this.scale(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM scale(IVec3D vec) {
//        return this.scale(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM scale(IVec2I vec, int z) {
//        return this.scale(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM scale(IVec2I vec, double z) {
//        return this.scale(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM scale(IVec2D vec, int z) {
//        return this.scale(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM scale(IVec2D vec, double z) {
//        return this.scale(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM scale(IVec2I vec) {
//        return this.scale(vec.x(), vec.y(), 1);
//    }
//
//    @Override
//    public PosMc3IM scale(IVec2D vec) {
//        return this.scale(vec.x(), vec.y(), 1.0D);
//    }
//
//    @Override
//    public PosMc3IM divide(int val) {
//        this.x /= val;
//        this.y /= val;
//        this.z /= val;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM divide(double val) {
//        this.set(this.x / val, this.y / val, this.z / val);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM divide(int x, int y, int z) {
//        this.x /= x;
//        this.y /= y;
//        this.z /= z;
//        return this;
//    }
//
//    @Override
//    public PosMc3IM divide(double x, double y, double z) {
//        this.set(this.x / x, this.y / y, this.z / z);
//        return this;
//    }
//
//    @Override
//    public PosMc3IM divide(IVec3I vec) {
//        return this.divide(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM divide(IVec3D vec) {
//        return this.divide(vec.x(), vec.y(), vec.z());
//    }
//
//    @Override
//    public PosMc3IM divide(IVec2I vec, int z) {
//        return this.divide(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM divide(IVec2I vec, double z) {
//        return this.divide(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM divide(IVec2D vec, int z) {
//        return this.divide(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM divide(IVec2D vec, double z) {
//        return this.divide(vec.x(), vec.y(), z);
//    }
//
//    @Override
//    public PosMc3IM divide(IVec2I vec) {
//        return this.divide(vec.x(), vec.y(), 1);
//    }
//
//    @Override
//    public PosMc3IM divide(IVec2D vec) {
//        return this.divide(vec.x(), vec.y(), 1.0D);
//    }
//
//    @Override
//    public PosMc3IM rotateX(double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(this.x, this.y * cos + this.z * sin, this.z * cos - this.y * sin);
//    }
//
//    @Override
//    public PosMc3IM rotateY(double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(this.x * cos + this.z * sin, this.y, this.z * cos - this.x * sin);
//    }
//
//    @Override
//    public PosMc3IM rotateZ(double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(this.x * cos + this.y * sin, this.y * cos - this.x * sin, this.z);
//    }
//
//    @Override
//    public PosMc3IM rotateXAround(int x, int y, int z, double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(this.x, ((this.y - y) * cos + (this.z - z) * sin) + y, ((this.z - z) * cos - (this.y - y) * sin) + z);
//    }
//
//    @Override
//    public PosMc3IM rotateXAround(double x, double y, double z, double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(this.x, ((this.y - y) * cos + (this.z - z) * sin) + y, ((this.z - z) * cos - (this.y - y) * sin) + z);
//    }
//
//    @Override
//    public PosMc3IM rotateXAround(IVec3I point, double degrees) {
//        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
//    }
//
//    @Override
//    public PosMc3IM rotateXAround(IVec3D point, double degrees) {
//        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
//    }
//
//    @Override
//    public PosMc3IM rotateYAround(int x, int y, int z, double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(((this.x - x) * cos + (this.z - z) * sin) + x, this.y, ((this.z - z) * cos - (this.x - x) * sin) + z);
//    }
//
//    @Override
//    public PosMc3IM rotateYAround(double x, double y, double z, double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(((this.x - x) * cos + (this.z - z) * sin) + x, this.y, ((this.z - z) * cos - (this.x - x) * sin) + z);
//    }
//
//    @Override
//    public PosMc3IM rotateYAround(IVec3I point, double degrees) {
//        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
//    }
//
//    @Override
//    public PosMc3IM rotateYAround(IVec3D point, double degrees) {
//        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
//    }
//
//    @Override
//    public PosMc3IM rotateZAround(int x, int y, int z, double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(((this.x - x) * cos + (this.y - y) * sin) + x, ((this.y - y) * cos - (this.x - x) * sin) + y, this.z);
//    }
//
//    @Override
//    public PosMc3IM rotateZAround(double x, double y, double z, double degrees) {
//        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
//            return this;
//        }
//
//        double rad = Math.toRadians(degrees);
//        double sin = Math.sin(rad), cos = Math.cos(rad);
//        return this.set(((this.x - x) * cos + (this.y - y) * sin) + x, ((this.y - y) * cos - (this.x - x) * sin) + y, this.z);
//    }
//
//    @Override
//    public PosMc3IM rotateZAround(IVec3I point, double degrees) {
//        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
//    }
//
//    @Override
//    public PosMc3IM rotateZAround(IVec3D point, double degrees) {
//        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox3I box) {
//        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox3D box) {
//        return this.wrap(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox2I box, int minZ, int maxZ) {
//        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox2I box, double minZ, double maxZ) {
//        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox2D box, int minZ, int maxZ) {
//        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox2D box, double minZ, double maxZ) {
//        return this.wrap(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox2I box) {
//        return this.wrap(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 1);
//    }
//
//    @Override
//    public PosMc3IM wrap(IBox2D box) {
//        return this.wrap(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 1);
//    }
//
//    @Override
//    public PosMc3IM wrap(int x, int y, int z) {
//        return this.wrap(0, 0, 0, x, y, z);
//    }
//
//    @Override
//    public PosMc3IM wrap(double x, double y, double z) {
//        return this.wrap(0, 0, 0, x, y, z);
//    }
//
//    @Override
//    public PosMc3IM wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
//        double rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
//        return this.set(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY, minZ + ((this.z - minZ) % rangeZ + rangeZ) % rangeZ);
//    }
//
//    @Override
//    public PosMc3IM wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
//        double rangeX = (maxX - minX), rangeY = (maxY - minY), rangeZ = (maxZ - minZ);
//        return this.set(minX + ((this.x - minX) % rangeX + rangeX) % rangeX, minY + ((this.y - minY) % rangeY + rangeY) % rangeY, minZ + ((this.z - minZ) % rangeZ + rangeZ) % rangeZ);
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox3I box) {
//        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox3D box) {
//        return this.clamp(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox2I box, int minZ, int maxZ) {
//        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox2I box, double minZ, double maxZ) {
//        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox2D box, int minZ, int maxZ) {
//        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox2D box, double minZ, double maxZ) {
//        return this.clamp(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox2I box) {
//        return this.clamp(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
//    }
//
//    @Override
//    public PosMc3IM clamp(IBox2D box) {
//        return this.clamp(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
//    }
//
//    @Override
//    public PosMc3IM clamp(int x, int y, int z) {
//        return this.clamp(0, 0, 0, x, y, z);
//    }
//
//    @Override
//    public PosMc3IM clamp(double x, double y, double z) {
//        return this.clamp(0, 0, 0, x, y, z);
//    }
//
//    @Override
//    public PosMc3IM clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
//        return this.set(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)), Math.max(minZ, Math.min(maxZ, this.z)));
//    }
//
//    @Override
//    public PosMc3IM clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
//        return this.set(Math.max(minX, Math.min(maxX, this.x)), Math.max(minY, Math.min(maxY, this.y)), Math.max(minZ, Math.min(maxZ, this.z)));
//    }
//
//    @Override
//    public IVec3DM normalize() {
//        double l = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
//        return l < 1.0E-4D ? new Vec3DM() : new Vec3DM(this.x / l, this.y / l, this.z / l);
//    }
//
//    @Override
//    public PosMc3IM toMutable() {
//        return this;
//    }
//
//    @Override
//    public @NotNull PosMc3I toImmutable() {
//        return new PosMc3I(this);
//    }
//
//    @Override
//    public IVec3DM toDouble() {
//        return new Vec3DM(this);
//    }
//
//    @Override
//    public PosMc3IM copy() {
//        return new PosMc3IM(this);
//    }
//
//    @Override
//    public @NotNull String toString() {
//        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
//    }
//
//    @Override
//    public int hashCode() {
//        return 31 * (31 * Double.hashCode(this.x) + Double.hashCode(this.y)) + Double.hashCode(this.z);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return (obj instanceof IVec3I && ((IVec3I) obj).x() == this.x && ((IVec3I) obj).y() == this.y && ((IVec3I) obj).z() == this.z) ||
//                (obj instanceof IVec3D && ((IVec3D) obj).x() == this.x && ((IVec3D) obj).y() == this.y && ((IVec3D) obj).z() == this.z);
//    }
//}
