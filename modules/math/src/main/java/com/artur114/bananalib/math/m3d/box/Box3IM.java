package com.artur114.bananalib.math.m3d.box;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.internal.IntStack;
import com.artur114.bananalib.math.internal.ThreadLocalPool;
import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m3d.vec.IVec3D;
import com.artur114.bananalib.math.m3d.vec.IVec3I;

public class Box3IM implements IBox3IM {
    private static final ThreadLocalPool<Box3IM> pool = new ThreadLocalPool<>(new Box3IM[4], Box3IM::new, box -> {
        box.resetStack().setZero();
        box.released = true;
        return box;
    }, box -> {
        box.released = false;
        return box;
    }, box -> box.released);

    public static Box3IM obtain() {
        return pool.obtain();
    }

    public static void release(Box3IM box) {
        pool.release(box);
    }

    private IntStack stateStack = null;
    private int minX, minY, minZ;
    private int maxX, maxY, maxZ;
    private boolean released;

    public Box3IM() {}

    public Box3IM(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = BananaMath.floor(Math.min(minX, maxX));
        this.minY = BananaMath.floor(Math.min(minY, maxY));
        this.minZ = BananaMath.floor(Math.min(minZ, maxZ));

        this.maxX = BananaMath.floor(Math.max(minX, maxX));
        this.maxY = BananaMath.floor(Math.max(minY, maxY));
        this.maxZ = BananaMath.floor(Math.max(minZ, maxZ));
    }

    public Box3IM(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);

        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
    }

    public Box3IM(IBox3I box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public Box3IM(IBox3D box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public Box3IM(IBox2I box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3IM(IBox2I box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3IM(IBox2D box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3IM(IBox2D box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3IM(IBox2I box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    public Box3IM(IBox2D box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3IM set(int[] box) {
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
    public IBox3IM set(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
        return this;
    }

    @Override
    public IBox3IM set(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = BananaMath.floor(Math.min(minX, maxX));
        this.minY = BananaMath.floor(Math.min(minY, maxY));
        this.minZ = BananaMath.floor(Math.min(minZ, maxZ));
        this.maxX = BananaMath.floor(Math.max(minX, maxX));
        this.maxY = BananaMath.floor(Math.max(minY, maxY));
        this.maxZ = BananaMath.floor(Math.max(minZ, maxZ));
        return this;
    }

    @Override
    public IBox3IM set(IVec3D boxFrom, IVec3D boxTo) {
        return this.set(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3IM set(IVec3I boxFrom, IVec3I boxTo) {
        return this.set(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3IM set(IBox3D box) {
        return this.set(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3IM set(IBox3I box) {
        return this.set(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3IM set(IBox2I box, int minZ, int maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM set(IBox2I box, double minZ, double maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM set(IBox2D box, int minZ, int maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM set(IBox2D box, double minZ, double maxZ) {
        return this.set(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM set(IBox2I box) {
        return this.set(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3IM set(IBox2D box) {
        return this.set(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3IM setZero() {
        this.minX = 0;
        this.minY = 0;
        this.minZ = 0;
        this.maxX = 0;
        this.maxY = 0;
        this.maxZ = 0;
        return this;
    }

    @Override
    public IBox3IM collapseStack() {
        this.stateStack = null;
        return this;
    }

    @Override
    public IBox3IM resetStack() {
        if (this.stateStack != null) {
            this.stateStack.reset();
        }
        return this;
    }

    @Override
    public IBox3IM pushBox() {
        if (this.stateStack == null) {
            this.stateStack = new IntStack(6);
        }
        int[] arr = this.stateStack.newEntry();
        arr[0] = this.minX;
        arr[1] = this.minY;
        arr[2] = this.minZ;
        arr[3] = this.maxX;
        arr[4] = this.maxY;
        arr[5] = this.maxZ;
        return this;
    }

    @Override
    public IBox3IM popBox() {
        if (this.stateStack == null) {
            throw new IllegalStateException();
        }
        return this.set(this.stateStack.pull());
    }

    @Override
    public int minX() {
        return this.minX;
    }

    @Override
    public int minY() {
        return this.minY;
    }

    @Override
    public int minZ() {
        return this.minZ;
    }

    @Override
    public int maxX() {
        return this.maxX;
    }

    @Override
    public int maxY() {
        return this.maxY;
    }

    @Override
    public int maxZ() {
        return this.maxZ;
    }

    @Override
    public int size() {
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
    public IBox3IM grow(double amount) {
        return this.set(
            BananaMath.floor(this.minX - amount), BananaMath.floor(this.minY - amount), BananaMath.floor(this.minZ - amount),
            BananaMath.ceil(this.maxX + amount), BananaMath.ceil(this.maxY + amount), BananaMath.ceil(this.maxZ + amount)
        );
    }

    @Override
    public IBox3IM grow(double x, double y, double z) {
        return this.set(
            BananaMath.floor(this.minX - x), BananaMath.floor(this.minY - y), BananaMath.floor(this.minZ - z),
            BananaMath.ceil(this.maxX + x), BananaMath.ceil(this.maxY + y), BananaMath.ceil(this.maxZ + z)
        );
    }

    @Override
    public IBox3IM grow(int amount) {
        return this.set(
            this.minX - amount, this.minY - amount, this.minZ - amount,
            this.maxX + amount, this.maxY + amount, this.maxZ + amount
        );
    }

    @Override
    public IBox3IM grow(int x, int y, int z) {
        return this.set(
            this.minX - x, this.minY - y, this.minZ - z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3IM grow(IVec3I vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3IM grow(IVec3D vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3IM grow(IVec2I vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM grow(IVec2I vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM grow(IVec2D vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM grow(IVec2D vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM grow(IVec2I vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3IM grow(IVec2D vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3IM offset(int x, int y, int z) {
        return this.set(
            this.minX + x, this.minY + y, this.minZ + z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3IM offset(double x, double y, double z) {
        return this.set(
            BananaMath.floor(this.minX + x), BananaMath.floor(this.minY + y), BananaMath.floor(this.minZ + z),
            BananaMath.ceil(this.maxX + x), BananaMath.ceil(this.maxY + y), BananaMath.ceil(this.maxZ + z)
        );
    }

    @Override
    public IBox3IM offset(IVec3I vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3IM offset(IVec3D vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3IM offset(IVec2I vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM offset(IVec2I vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM offset(IVec2D vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM offset(IVec2D vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM offset(IVec2I vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3IM offset(IVec2D vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3IM include(int x, int y, int z) {
        if (this.contains(x, y, z)) return this;
        return this.set(
            Math.min(this.minX, x), Math.min(this.minY, y), Math.min(this.minZ, z),
            Math.max(this.maxX, x), Math.max(this.maxY, y), Math.max(this.maxZ, z)
        );
    }

    @Override
    public IBox3IM include(double x, double y, double z) {
        if (this.contains(x, y, z)) return this;
        return this.set(
            BananaMath.floor(Math.min(this.minX, x)), BananaMath.floor(Math.min(this.minY, y)), BananaMath.floor(Math.min(this.minZ, z)),
            BananaMath.ceil(Math.max(this.maxX, x)), BananaMath.ceil(Math.max(this.maxY, y)), BananaMath.ceil(Math.max(this.maxZ, z))
        );
    }

    @Override
    public IBox3IM include(IVec3I vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3IM include(IVec3D vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3IM include(IVec2I vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM include(IVec2I vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM include(IVec2D vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM include(IVec2D vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3IM include(IVec2I vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3IM include(IVec2D vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3IM union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return this.set(
            Math.min(this.minX, minX), Math.min(this.minY, minY), Math.min(this.minZ, minZ),
            Math.max(this.maxX, maxX), Math.max(this.maxY, maxY), Math.max(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3IM union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.set(
            BananaMath.floor(Math.min(this.minX, minX)), BananaMath.floor(Math.min(this.minY, minY)), BananaMath.floor(Math.min(this.minZ, minZ)),
            BananaMath.ceil(Math.max(this.maxX, maxX)), BananaMath.ceil(Math.max(this.maxY, maxY)), BananaMath.ceil(Math.max(this.maxZ, maxZ))
        );
    }

    @Override
    public IBox3IM union(IVec3D boxFrom, IVec3D boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3IM union(IVec3I boxFrom, IVec3I boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3IM union(IBox3D box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3IM union(IBox3I box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3IM union(IBox2I box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM union(IBox2I box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM union(IBox2D box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM union(IBox2D box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM union(IBox2I box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3IM union(IBox2D box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3IM intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return this.setZero();
        return this.set(
            Math.max(this.minX, minX), Math.max(this.minY, minY), Math.max(this.minZ, minZ),
            Math.min(this.maxX, maxX), Math.min(this.maxY, maxY), Math.min(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3IM intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return this.setZero();
        return this.set(
            BananaMath.floor(Math.max(this.minX, minX)), BananaMath.floor(Math.max(this.minY, minY)), BananaMath.floor(Math.max(this.minZ, minZ)),
            BananaMath.ceil(Math.min(this.maxX, maxX)), BananaMath.ceil(Math.min(this.maxY, maxY)), BananaMath.ceil(Math.min(this.maxZ, maxZ))
        );
    }

    @Override
    public IBox3IM intersection(IVec3D boxFrom, IVec3D boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3IM intersection(IVec3I boxFrom, IVec3I boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3IM intersection(IBox3D box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3IM intersection(IBox3I box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3IM intersection(IBox2I box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM intersection(IBox2I box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM intersection(IBox2D box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM intersection(IBox2D box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3IM intersection(IBox2I box) {
        return this.intersection(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3IM intersection(IBox2D box) {
        return this.intersection(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3IM toMutable() {
        return this;
    }

    @Override
    public IBox3I toImmutable() {
        return new Box3I(this);
    }

    @Override
    public IBox3DM toDouble() {
        return new Box3DM(this);
    }

    @Override
    public IBox3IM copy() {
        return new Box3IM(this);
    }
}
