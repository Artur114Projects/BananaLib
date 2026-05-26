package com.artur114.bananalib.math.m3d.box;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.internal.DoubleStack;
import com.artur114.bananalib.math.internal.ThreadLocalPool;
import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m3d.vec.IVec3D;
import com.artur114.bananalib.math.m3d.vec.IVec3I;

public class Box3DM implements IBox3DM {
    private static final ThreadLocalPool<Box3DM> pool = new ThreadLocalPool<>(new Box3DM[4], Box3DM::new, box -> {
        box.resetStack().setZero();
        box.released = true;
        return box;
    }, box -> {
        box.released = false;
        return box;
    }, box -> box.released);

    public static Box3DM obtain() {
        return pool.obtain();
    }

    public static void release(Box3DM box) {
        pool.release(box);
    }

    private DoubleStack stateStack = null;
    private double minX, minY, minZ;
    private double maxX, maxY, maxZ;
    private boolean released;

    public Box3DM() {}

    public Box3DM(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);

        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
    }

    public Box3DM(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);

        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
    }

    public Box3DM(IBox3I box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public Box3DM(IBox3D box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public Box3DM(IBox2I box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3DM(IBox2I box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3DM(IBox2D box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3DM(IBox2D box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3DM(IBox2I box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    public Box3DM(IBox2D box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3DM set(double[] box) {
        if (box.length < 6) {
            throw new IllegalArgumentException();
        }
        this.minX = Math.min(box[0], box[3]);
        this.minY = Math.min(box[1], box[4]);
        this.minZ = Math.min(box[2], box[5]);
        this.maxX = Math.max(box[0], box[3]);
        this.maxY = Math.max(box[1], box[4]);
        this.maxZ = Math.max(box[2], box[5]);
        return this;
    }

    @Override
    public IBox3DM set(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
        return this;
    }

    @Override
    public IBox3DM set(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
        return this;
    }

    @Override
    public IBox3DM set(IVec3D boxFrom, IVec3D boxTo) {
        return this.set(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3DM set(IVec3I boxFrom, IVec3I boxTo) {
        return this.set(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3DM set(IBox3D box) {
        return this.set(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3DM set(IBox3I box) {
        return this.set(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3DM set(IBox2I box, int minZ, int maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM set(IBox2I box, double minZ, double maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM set(IBox2D box, int minZ, int maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM set(IBox2D box, double minZ, double maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM set(IBox2I box) {
        return this.set(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3DM set(IBox2D box) {
        return this.set(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3DM setZero() {
        this.minX = 0.0D;
        this.minY = 0.0D;
        this.minZ = 0.0D;
        this.maxX = 0.0D;
        this.maxY = 0.0D;
        this.maxZ = 0.0D;
        return this;
    }

    @Override
    public IBox3DM collapseStack() {
        this.stateStack = null;
        return this;
    }

    @Override
    public IBox3DM resetStack() {
        if (this.stateStack != null) {
            this.stateStack.reset();
        }
        return this;
    }

    @Override
    public IBox3DM pushBox() {
        if (this.stateStack == null) {
            this.stateStack = new DoubleStack(6);
        }
        double[] arr = this.stateStack.newEntry();
        arr[0] = this.minX;
        arr[1] = this.minY;
        arr[2] = this.minZ;
        arr[3] = this.maxX;
        arr[4] = this.maxY;
        arr[5] = this.maxZ;
        return this;
    }

    @Override
    public IBox3DM popBox() {
        if (this.stateStack == null) {
            throw new IllegalStateException();
        }
        return this.set(this.stateStack.pull());
    }

    @Override
    public double minX() {
        return this.minX;
    }

    @Override
    public double minY() {
        return this.minY;
    }

    @Override
    public double minZ() {
        return this.minZ;
    }

    @Override
    public double maxX() {
        return this.maxX;
    }

    @Override
    public double maxY() {
        return this.maxY;
    }

    @Override
    public double maxZ() {
        return this.maxZ;
    }

    @Override
    public double size() {
        return (this.maxX - this.minX) * (this.maxY - this.minY) * (this.maxZ - this.minZ);
    }

    @Override
    public boolean contains(int x, int y, int z) {
        return x >= this.minX && y >= this.minY && z >= this.minZ && x <= this.maxX && y <= this.maxY && z <= this.maxZ;
    }

    @Override
    public boolean contains(double x, double y, double z) {
        return x >= this.minX && y >= this.minY && z >= this.minZ && x <= this.maxX && y <= this.maxY && z <= this.maxZ;
    }

    @Override
    public boolean contains(IBox3I box) {
        return this.contains(box.minX(), box.minY(), box.minZ()) && this.contains(box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean contains(IBox3D box) {
        return this.contains(box.minX(), box.minY(), box.minZ()) && this.contains(box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean contains(IVec3I vec) {
        return this.contains(vec.x(), vec.y(), vec.z());
    }

    @Override
    public boolean contains(IVec3D vec) {
        return this.contains(vec.x(), vec.y(), vec.z());
    }

    @Override
    public boolean contains(IVec2I vec, int z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2I vec, double z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2D vec, int z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2D vec, double z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2I vec) {
        return this.contains(vec.x(), vec.y(), 0);
    }

    @Override
    public boolean contains(IVec2D vec) {
        return this.contains(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public boolean contains(IBox2I box, int minZ, int maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2I box, double minZ, double maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2D box, int minZ, int maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2D box, double minZ, double maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2I box) {
        return this.contains(box.minX(), box.minY(), 0) && this.contains(box.maxX(), box.maxY(), 0);
    }

    @Override
    public boolean contains(IBox2D box) {
        return this.contains(box.minX(), box.minY(), 0.0D) && this.contains(box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public boolean intersects(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return this.minX() <= maxX && this.maxX() >= minX && this.minY() <= maxY && this.maxY() >= minY && this.minZ() <= maxZ && this.maxZ() >= minZ;
    }

    @Override
    public boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.minX() <= maxX && this.maxX() >= minX && this.minY() <= maxY && this.maxY() >= minY && this.minZ() <= maxZ && this.maxZ() >= minZ;
    }

    @Override
    public boolean intersects(IVec3D boxFrom, IVec3D boxTo) {
        return this.intersects(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public boolean intersects(IVec3I boxFrom, IVec3I boxTo) {
        return this.intersects(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public boolean intersects(IBox3D box) {
        return this.intersects(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean intersects(IBox3I box) {
        return this.intersects(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean intersects(IBox2I box, int minZ, int maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2I box, double minZ, double maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2D box, int minZ, int maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2D box, double minZ, double maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2I box) {
        return this.intersects(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public boolean intersects(IBox2D box) {
        return this.intersects(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3DM grow(double amount) {
        return this.set(
            this.minX - amount, this.minY - amount, this.minZ - amount,
            this.maxX + amount, this.maxY + amount, this.maxZ + amount
        );
    }

    @Override
    public IBox3DM grow(double x, double y, double z) {
        return this.set(
            this.minX - x, this.minY - y, this.minZ - z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3DM grow(int amount) {
        return this.set(
            this.minX - amount, this.minY - amount, this.minZ - amount,
            this.maxX + amount, this.maxY + amount, this.maxZ + amount
        );
    }

    @Override
    public IBox3DM grow(int x, int y, int z) {
        return this.set(
            this.minX - x, this.minY - y, this.minZ - z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3DM grow(IVec3I vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3DM grow(IVec3D vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3DM grow(IVec2I vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM grow(IVec2I vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM grow(IVec2D vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM grow(IVec2D vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM grow(IVec2I vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3DM grow(IVec2D vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3DM offset(int x, int y, int z) {
        return this.set(
            this.minX + x, this.minY + y, this.minZ + z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3DM offset(double x, double y, double z) {
        return this.set(
            this.minX + x, this.minY + y, this.minZ + z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3DM offset(IVec3I vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3DM offset(IVec3D vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3DM offset(IVec2I vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM offset(IVec2I vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM offset(IVec2D vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM offset(IVec2D vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM offset(IVec2I vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3DM offset(IVec2D vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3DM include(int x, int y, int z) {
        if (this.contains(x, y, z)) return this;
        return this.set(
            Math.min(this.minX, x), Math.min(this.minY, y), Math.min(this.minZ, z),
            Math.max(this.maxX, x), Math.max(this.maxY, y), Math.max(this.maxZ, z)
        );
    }

    @Override
    public IBox3DM include(double x, double y, double z) {
        if (this.contains(x, y, z)) return this;
        return this.set(
            Math.min(this.minX, x), Math.min(this.minY, y), Math.min(this.minZ, z),
            Math.max(this.maxX, x), Math.max(this.maxY, y), Math.max(this.maxZ, z)
        );
    }

    @Override
    public IBox3DM include(IVec3I vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3DM include(IVec3D vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3DM include(IVec2I vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM include(IVec2I vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM include(IVec2D vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM include(IVec2D vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3DM include(IVec2I vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3DM include(IVec2D vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3DM union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return this.set(
            Math.min(this.minX, minX), Math.min(this.minY, minY), Math.min(this.minZ, minZ),
            Math.max(this.maxX, maxX), Math.max(this.maxY, maxY), Math.max(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3DM union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.set(
            Math.min(this.minX, minX), Math.min(this.minY, minY), Math.min(this.minZ, minZ),
            Math.max(this.maxX, maxX), Math.max(this.maxY, maxY), Math.max(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3DM union(IVec3D boxFrom, IVec3D boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3DM union(IVec3I boxFrom, IVec3I boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3DM union(IBox3D box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3DM union(IBox3I box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3DM union(IBox2I box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM union(IBox2I box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM union(IBox2D box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM union(IBox2D box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM union(IBox2I box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3DM union(IBox2D box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3DM intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return this.setZero();
        return this.set(
            Math.max(this.minX, minX), Math.max(this.minY, minY), Math.max(this.minZ, minZ),
            Math.min(this.maxX, maxX), Math.min(this.maxY, maxY), Math.min(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3DM intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return this.setZero();
        return this.set(
            Math.max(this.minX, minX), Math.max(this.minY, minY), Math.max(this.minZ, minZ),
            Math.min(this.maxX, maxX), Math.min(this.maxY, maxY), Math.min(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3DM intersection(IVec3D boxFrom, IVec3D boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3DM intersection(IVec3I boxFrom, IVec3I boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3DM intersection(IBox3D box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3DM intersection(IBox3I box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3DM intersection(IBox2I box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM intersection(IBox2I box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM intersection(IBox2D box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM intersection(IBox2D box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3DM intersection(IBox2I box) {
        return this.intersection(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3DM intersection(IBox2D box) {
        return this.intersection(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3DM toMutable() {
        return this;
    }

    @Override
    public IBox3D toImmutable() {
        return new Box3D(this);
    }

    @Override
    public IBox3IM floor() {
        return new Box3IM(this);
    }

    @Override
    public IBox3IM round() {
        return new Box3IM(
            BananaMath.round(this.minX), BananaMath.round(this.minY), BananaMath.round(this.minZ),
            BananaMath.round(this.maxX), BananaMath.round(this.maxY), BananaMath.round(this.maxZ)
        );
    }

    @Override
    public IBox3IM ceil() {
        return new Box3IM(
            BananaMath.ceil(this.minX), BananaMath.ceil(this.minY), BananaMath.ceil(this.minZ),
            BananaMath.ceil(this.maxX), BananaMath.ceil(this.maxY), BananaMath.ceil(this.maxZ)
        );
    }

    @Override
    public IBox3DM copy() {
        return new Box3DM(this);
    }
}
