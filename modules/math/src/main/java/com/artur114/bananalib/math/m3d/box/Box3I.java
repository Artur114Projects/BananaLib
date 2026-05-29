package com.artur114.bananalib.math.m3d.box;

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

public class Box3I implements IBox3I {
    public static final Box3I EMPTY = new Box3I(0, 0, 0, 0, 0, 0);
    private final int minX, minY, minZ;
    private final int maxX, maxY, maxZ;

    public Box3I(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = BananaMath.floor(Math.min(minX, maxX));
        this.minY = BananaMath.floor(Math.min(minY, maxY));
        this.minZ = BananaMath.floor(Math.min(minZ, maxZ));

        this.maxX = BananaMath.floor(Math.max(minX, maxX));
        this.maxY = BananaMath.floor(Math.max(minY, maxY));
        this.maxZ = BananaMath.floor(Math.max(minZ, maxZ));
    }

    public Box3I(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);

        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
    }

    public Box3I(IBox3IC box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public Box3I(IBox3DC box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public Box3I(IBox2IC box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3I(IBox2IC box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3I(IBox2DC box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3I(IBox2DC box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public Box3I(IBox2IC box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    public Box3I(IBox2DC box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0.0D);
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
    public boolean contains(IBox3IC box) {
        return this.contains(box.minX(), box.minY(), box.minZ()) && this.contains(box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean contains(IBox3DC box) {
        return this.contains(box.minX(), box.minY(), box.minZ()) && this.contains(box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean contains(IVec3IC vec) {
        return this.contains(vec.x(), vec.y(), vec.z());
    }

    @Override
    public boolean contains(IVec3DC vec) {
        return this.contains(vec.x(), vec.y(), vec.z());
    }

    @Override
    public boolean contains(IVec2IC vec, int z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2IC vec, double z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2DC vec, int z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2DC vec, double z) {
        return this.contains(vec.x(), vec.y(), z);
    }

    @Override
    public boolean contains(IVec2IC vec) {
        return this.contains(vec.x(), vec.y(), 0);
    }

    @Override
    public boolean contains(IVec2DC vec) {
        return this.contains(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public boolean contains(IBox2IC box, int minZ, int maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2IC box, double minZ, double maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2DC box, int minZ, int maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2DC box, double minZ, double maxZ) {
        return this.contains(box.minX(), box.minY(), minZ) && this.contains(box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean contains(IBox2IC box) {
        return this.contains(box.minX(), box.minY(), 0) && this.contains(box.maxX(), box.maxY(), 0);
    }

    @Override
    public boolean contains(IBox2DC box) {
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
    public boolean intersects(IVec3DC boxFrom, IVec3DC boxTo) {
        return this.intersects(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public boolean intersects(IVec3IC boxFrom, IVec3IC boxTo) {
        return this.intersects(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public boolean intersects(IBox3DC box) {
        return this.intersects(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean intersects(IBox3IC box) {
        return this.intersects(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public boolean intersects(IBox2IC box, int minZ, int maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2IC box, double minZ, double maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2DC box, int minZ, int maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2DC box, double minZ, double maxZ) {
        return this.intersects(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public boolean intersects(IBox2IC box) {
        return this.intersects(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public boolean intersects(IBox2DC box) {
        return this.intersects(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3I grow(double amount) {
        return new Box3I(
            BananaMath.floor(this.minX - amount), BananaMath.floor(this.minY - amount), BananaMath.floor(this.minZ - amount),
            BananaMath.ceil(this.maxX + amount), BananaMath.ceil(this.maxY + amount), BananaMath.ceil(this.maxZ + amount)
        );
    }

    @Override
    public IBox3I grow(double x, double y, double z) {
        return new Box3I(
            BananaMath.floor(this.minX - x), BananaMath.floor(this.minY - y), BananaMath.floor(this.minZ - z),
            BananaMath.ceil(this.maxX + x), BananaMath.ceil(this.maxY + y), BananaMath.ceil(this.maxZ + z)
        );
    }

    @Override
    public IBox3I grow(int amount) {
        return new Box3I(
            this.minX - amount, this.minY - amount, this.minZ - amount,
            this.maxX + amount, this.maxY + amount, this.maxZ + amount
        );
    }

    @Override
    public IBox3I grow(int x, int y, int z) {
        return new Box3I(
            this.minX - x, this.minY - y, this.minZ - z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3I grow(IVec3IC vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3I grow(IVec3DC vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3I grow(IVec2IC vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I grow(IVec2IC vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I grow(IVec2DC vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I grow(IVec2DC vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I grow(IVec2IC vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3I grow(IVec2DC vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3I offset(int x, int y, int z) {
        return new Box3I(
            this.minX + x, this.minY + y, this.minZ + z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public IBox3I offset(double x, double y, double z) {
        return new Box3I(
                BananaMath.floor(this.minX + x), BananaMath.floor(this.minY + y), BananaMath.floor(this.minZ + z),
                BananaMath.ceil(this.maxX + x), BananaMath.ceil(this.maxY + y), BananaMath.ceil(this.maxZ + z)
        );
    }

    @Override
    public IBox3I offset(IVec3IC vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3I offset(IVec3DC vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3I offset(IVec2IC vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I offset(IVec2IC vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I offset(IVec2DC vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I offset(IVec2DC vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I offset(IVec2IC vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3I offset(IVec2DC vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3I include(int x, int y, int z) {
        if (this.contains(x, y, z)) return this;
        return new Box3I(
            Math.min(this.minX, x), Math.min(this.minY, y), Math.min(this.minZ, z),
            Math.max(this.maxX, x), Math.max(this.maxY, y), Math.max(this.maxZ, z)
        );
    }

    @Override
    public IBox3I include(double x, double y, double z) {
        if (this.contains(x, y, z)) return this;
        return new Box3I(
            BananaMath.floor(Math.min(this.minX, x)), BananaMath.floor(Math.min(this.minY, y)), BananaMath.floor(Math.min(this.minZ, z)),
            BananaMath.ceil(Math.max(this.maxX, x)), BananaMath.ceil(Math.max(this.maxY, y)), BananaMath.ceil(Math.max(this.maxZ, z))
        );
    }

    @Override
    public IBox3I include(IVec3IC vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3I include(IVec3DC vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IBox3I include(IVec2IC vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I include(IVec2IC vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I include(IVec2DC vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I include(IVec2DC vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public IBox3I include(IVec2IC vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3I include(IVec2DC vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public IBox3I union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new Box3I(
            Math.min(this.minX, minX), Math.min(this.minY, minY), Math.min(this.minZ, minZ),
            Math.max(this.maxX, maxX), Math.max(this.maxY, maxY), Math.max(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3I union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new Box3I(
            BananaMath.floor(Math.min(this.minX, minX)), BananaMath.floor(Math.min(this.minY, minY)), BananaMath.floor(Math.min(this.minZ, minZ)),
            BananaMath.ceil(Math.max(this.maxX, maxX)), BananaMath.ceil(Math.max(this.maxY, maxY)), BananaMath.ceil(Math.max(this.maxZ, maxZ))
        );
    }

    @Override
    public IBox3I union(IVec3DC boxFrom, IVec3DC boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3I union(IVec3IC boxFrom, IVec3IC boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3I union(IBox3DC box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3I union(IBox3IC box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3I union(IBox2IC box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I union(IBox2IC box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I union(IBox2DC box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I union(IBox2DC box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I union(IBox2IC box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3I union(IBox2DC box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3I intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return EMPTY;
        return new Box3I(
            Math.max(this.minX, minX), Math.max(this.minY, minY), Math.max(this.minZ, minZ),
            Math.min(this.maxX, maxX), Math.min(this.maxY, maxY), Math.min(this.maxZ, maxZ)
        );
    }

    @Override
    public IBox3I intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return EMPTY;
        return new Box3I(
            BananaMath.floor(Math.max(this.minX, minX)), BananaMath.floor(Math.max(this.minY, minY)), BananaMath.floor(Math.max(this.minZ, minZ)),
            BananaMath.ceil(Math.min(this.maxX, maxX)), BananaMath.ceil(Math.min(this.maxY, maxY)), BananaMath.ceil(Math.min(this.maxZ, maxZ))
        );
    }

    @Override
    public IBox3I intersection(IVec3DC boxFrom, IVec3DC boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3I intersection(IVec3IC boxFrom, IVec3IC boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public IBox3I intersection(IBox3DC box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3I intersection(IBox3IC box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public IBox3I intersection(IBox2IC box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I intersection(IBox2IC box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I intersection(IBox2DC box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I intersection(IBox2DC box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public IBox3I intersection(IBox2IC box) {
        return this.intersection(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public IBox3I intersection(IBox2DC box) {
        return this.intersection(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3IM toMutable() {
        return new Box3IM(this);
    }

    @Override
    public IBox3I toImmutable() {
        return this;
    }

    @Override
    public IBox3D toDouble() {
        return new Box3D(this);
    }

    @Override
    public IBox3I copy() {
        return new Box3I(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IBox3I) {
            IBox3I box = (IBox3I) obj;
            return
                    box.minX() == this.minX &&
                    box.minY() == this.minY &&
                    box.minZ() == this.minZ &&
                    box.maxX() == this.maxX &&
                    box.maxY() == this.maxY &&
                    box.maxZ() == this.maxZ;
        } else if (obj instanceof IBox3D) {
            IBox3D box = (IBox3D) obj;
            return
                    box.minX() == this.minX &&
                    box.minY() == this.minY &&
                    box.minZ() == this.minZ &&
                    box.maxX() == this.maxX &&
                    box.maxY() == this.maxY &&
                    box.maxZ() == this.maxZ;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Hasher.hash(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }

    @Override
    public String toString() {
        return "[" + this.minX + ", " + this.minY + ", " + this.minZ + "] -> ["  + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
    }
}
