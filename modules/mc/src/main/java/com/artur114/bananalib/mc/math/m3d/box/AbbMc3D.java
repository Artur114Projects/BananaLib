package com.artur114.bananalib.mc.math.m3d.box;

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
import com.artur114.bananalib.math.m3d.box.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

public class AbbMc3D extends AxisAlignedBB implements IAbbMc3D {
    public static final AbbMc3D EMPTY = new AbbMc3D(0, 0, 0, 0, 0, 0);
    
    public AbbMc3D(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        super(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public AbbMc3D(BlockPos pos) {
        super(pos);
    }

    public AbbMc3D(BlockPos pos1, BlockPos pos2) {
        super(pos1, pos2);
    }

    public AbbMc3D(Vec3d min, Vec3d max) {
        super(min, max);
    }

    public AbbMc3D(IBox3IC box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public AbbMc3D(IBox3DC box) {
        this(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    public AbbMc3D(IBox2IC box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public AbbMc3D(IBox2IC box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public AbbMc3D(IBox2DC box, int minZ, int maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public AbbMc3D(IBox2DC box, double minZ, double maxZ) {
        this(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    public AbbMc3D(IBox2IC box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    public AbbMc3D(IBox2DC box) {
        this(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0.0D);
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
    public boolean contains(AxisAlignedBB box) {
        return this.contains(box.minX, box.minY, box.minZ) && this.contains(box.maxX, box.maxY, box.maxZ);
    }

    @Override
    public boolean contains(Vec3i vec) {
        return this.contains(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public boolean contains(Vec3d vec) {
        return this.contains(vec.x, vec.y, vec.z);
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
    public @NotNull AbbMc3D grow(double amount) {
        return new AbbMc3D(
            this.minX - amount, this.minY - amount, this.minZ - amount,
            this.maxX + amount, this.maxY + amount, this.maxZ + amount
        );
    }

    @Override
    public @NotNull AbbMc3D grow(double x, double y, double z) {
        return new AbbMc3D(
            this.minX - x, this.minY - y, this.minZ - z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public AbbMc3D grow(int amount) {
        return new AbbMc3D(
            this.minX - amount, this.minY - amount, this.minZ - amount,
            this.maxX + amount, this.maxY + amount, this.maxZ + amount
        );
    }

    @Override
    public AbbMc3D grow(int x, int y, int z) {
        return new AbbMc3D(
            this.minX - x, this.minY - y, this.minZ - z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public AbbMc3D grow(Vec3i vec) {
        return this.grow(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public AbbMc3D grow(Vec3d vec) {
        return this.grow(vec.x, vec.y, vec.z);
    }

    @Override
    public AbbMc3D grow(IVec3IC vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public AbbMc3D grow(IVec3DC vec) {
        return this.grow(vec.x(), vec.y(), vec.z());
    }

    @Override
    public AbbMc3D grow(IVec2IC vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D grow(IVec2IC vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D grow(IVec2DC vec, int z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D grow(IVec2DC vec, double z) {
        return this.grow(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D grow(IVec2IC vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public AbbMc3D grow(IVec2DC vec) {
        return this.grow(vec.x(), vec.y(), 0);
    }

    @Override
    public AbbMc3D offset(int x, int y, int z) {
        return new AbbMc3D(
            this.minX + x, this.minY + y, this.minZ + z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public @NotNull AbbMc3D offset(double x, double y, double z) {
        return new AbbMc3D(
            this.minX + x, this.minY + y, this.minZ + z,
            this.maxX + x, this.maxY + y, this.maxZ + z
        );
    }

    @Override
    public AbbMc3D offset(Vec3i vec) {
        return this.offset(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public @NotNull AbbMc3D offset(Vec3d vec) {
        return this.offset(vec.x, vec.y, vec.z);
    }

    @Override
    public AbbMc3D offset(IVec3IC vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public AbbMc3D offset(IVec3DC vec) {
        return this.offset(vec.x(), vec.y(), vec.z());
    }

    @Override
    public AbbMc3D offset(IVec2IC vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D offset(IVec2IC vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D offset(IVec2DC vec, int z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D offset(IVec2DC vec, double z) {
        return this.offset(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D offset(IVec2IC vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public AbbMc3D offset(IVec2DC vec) {
        return this.offset(vec.x(), vec.y(), 0);
    }

    @Override
    public AbbMc3D include(int x, int y, int z) {
        if (this.contains(x, y, z)) return this;
        return new AbbMc3D(
            Math.min(this.minX, x), Math.min(this.minY, y), Math.min(this.minZ, z),
            Math.max(this.maxX, x), Math.max(this.maxY, y), Math.max(this.maxZ, z)
        );
    }

    @Override
    public AbbMc3D include(double x, double y, double z) {
        if (this.contains(x, y, z)) return this;
        return new AbbMc3D(
            Math.min(this.minX, x), Math.min(this.minY, y), Math.min(this.minZ, z),
            Math.max(this.maxX, x), Math.max(this.maxY, y), Math.max(this.maxZ, z)
        );
    }

    @Override
    public AbbMc3D include(Vec3i vec) {
        return this.include(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public AbbMc3D include(Vec3d vec) {
        return this.include(vec.x, vec.y, vec.z);
    }

    @Override
    public AbbMc3D include(IVec3IC vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public AbbMc3D include(IVec3DC vec) {
        return this.include(vec.x(), vec.y(), vec.z());
    }

    @Override
    public AbbMc3D include(IVec2IC vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D include(IVec2IC vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D include(IVec2DC vec, int z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D include(IVec2DC vec, double z) {
        return this.include(vec.x(), vec.y(), z);
    }

    @Override
    public AbbMc3D include(IVec2IC vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public AbbMc3D include(IVec2DC vec) {
        return this.include(vec.x(), vec.y(), 0);
    }

    @Override
    public AbbMc3D union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new AbbMc3D(
            Math.min(this.minX, minX), Math.min(this.minY, minY), Math.min(this.minZ, minZ),
            Math.max(this.maxX, maxX), Math.max(this.maxY, maxY), Math.max(this.maxZ, maxZ)
        );
    }

    @Override
    public AbbMc3D union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new AbbMc3D(
            Math.min(this.minX, minX), Math.min(this.minY, minY), Math.min(this.minZ, minZ),
            Math.max(this.maxX, maxX), Math.max(this.maxY, maxY), Math.max(this.maxZ, maxZ)
        );
    }

    @Override
    public AbbMc3D union(Vec3i boxFrom, Vec3i boxTo) {
        return this.union(boxFrom.getX(), boxFrom.getY(), boxFrom.getZ(), boxTo.getX(), boxTo.getY(), boxTo.getZ());
    }

    @Override
    public AbbMc3D union(Vec3d boxFrom, Vec3d boxTo) {
        return this.union(boxFrom.x, boxFrom.y, boxFrom.z, boxTo.x, boxTo.y, boxTo.z);
    }

    @Override
    public @NotNull AbbMc3D union(AxisAlignedBB box) {
        return this.union(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    @Override
    public AbbMc3D union(IVec3DC boxFrom, IVec3DC boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public AbbMc3D union(IVec3IC boxFrom, IVec3IC boxTo) {
        return this.union(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public AbbMc3D union(IBox3DC box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public AbbMc3D union(IBox3IC box) {
        return this.union(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public AbbMc3D union(IBox2IC box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D union(IBox2IC box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D union(IBox2DC box, int minZ, int maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D union(IBox2DC box, double minZ, double maxZ) {
        return this.union(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D union(IBox2IC box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public AbbMc3D union(IBox2DC box) {
        return this.union(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public AbbMc3D intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return EMPTY;
        return new AbbMc3D(
            Math.max(this.minX, minX), Math.max(this.minY, minY), Math.max(this.minZ, minZ),
            Math.min(this.maxX, maxX), Math.min(this.maxY, maxY), Math.min(this.maxZ, maxZ)
        );
    }

    @Override
    public AbbMc3D intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (!this.intersects(minX, minY, minZ, maxX, maxY, maxZ)) return EMPTY;
        return new AbbMc3D(
            Math.max(this.minX, minX), Math.max(this.minY, minY), Math.max(this.minZ, minZ),
            Math.min(this.maxX, maxX), Math.min(this.maxY, maxY), Math.min(this.maxZ, maxZ)
        );
    }

    @Override
    public AbbMc3D intersection(Vec3i boxFrom, Vec3i boxTo) {
        return this.intersection(boxFrom.getX(), boxFrom.getY(), boxFrom.getZ(), boxTo.getX(), boxTo.getY(), boxTo.getZ());
    }

    @Override
    public AbbMc3D intersection(Vec3d boxFrom, Vec3d boxTo) {
        return this.intersection(boxFrom.x, boxFrom.y, boxFrom.z, boxTo.x, boxTo.y, boxTo.z);
    }

    @Override
    public AbbMc3D intersection(AxisAlignedBB box) {
        return this.intersection(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    @Override
    public AbbMc3D intersection(IVec3DC boxFrom, IVec3DC boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public AbbMc3D intersection(IVec3IC boxFrom, IVec3IC boxTo) {
        return this.intersection(boxFrom.x(), boxFrom.y(), boxFrom.z(), boxTo.x(), boxTo.y(), boxTo.z());
    }

    @Override
    public AbbMc3D intersection(IBox3DC box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public AbbMc3D intersection(IBox3IC box) {
        return this.intersection(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
    }

    @Override
    public AbbMc3D intersection(IBox2IC box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D intersection(IBox2IC box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D intersection(IBox2DC box, int minZ, int maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D intersection(IBox2DC box, double minZ, double maxZ) {
        return this.intersection(box.minX(), box.minY(), minZ, box.maxX(), box.maxY(), maxZ);
    }

    @Override
    public AbbMc3D intersection(IBox2IC box) {
        return this.intersection(box.minX(), box.minY(), 0, box.maxX(), box.maxY(), 0);
    }

    @Override
    public AbbMc3D intersection(IBox2DC box) {
        return this.intersection(box.minX(), box.minY(), 0.0D, box.maxX(), box.maxY(), 0.0D);
    }

    @Override
    public IBox3DM toMutable() {
        return new Box3DM(this);
    }

    @Override
    public AbbMc3D toImmutable() {
        return this;
    }

    @Override
    public IBox3I floor() {
        return new Box3I(
            BananaMath.ceil(this.minX), BananaMath.ceil(this.minY), BananaMath.ceil(this.minZ),
            BananaMath.floor(this.maxX), BananaMath.floor(this.maxY), BananaMath.floor(this.maxZ)
        );
    }

    @Override
    public IBox3I round() {
        return new Box3I(
            BananaMath.round(this.minX), BananaMath.round(this.minY), BananaMath.round(this.minZ),
            BananaMath.round(this.maxX), BananaMath.round(this.maxY), BananaMath.round(this.maxZ)
        );
    }

    @Override
    public IBox3I ceil() {
        return new Box3I(
            BananaMath.floor(this.minX), BananaMath.floor(this.minY), BananaMath.floor(this.minZ),
            BananaMath.ceil(this.maxX), BananaMath.ceil(this.maxY), BananaMath.ceil(this.maxZ)
        );
    }

    @Override
    public AbbMc3D copy() {
        return new AbbMc3D(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IBox3D) {
            IBox3D box = (IBox3D) obj;
            return
                    box.minX() == this.minX &&
                    box.minY() == this.minY &&
                    box.minZ() == this.minZ &&
                    box.maxX() == this.maxX &&
                    box.maxY() == this.maxY &&
                    box.maxZ() == this.maxZ;
        } else if (obj instanceof IBox3I) {
            IBox3I box = (IBox3I) obj;
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
    public boolean equalsEps(Object obj, double eps) {
        if (obj instanceof IBox3D) {
            IBox3D box = (IBox3D) obj;
            return
                    Math.abs(box.minX() - this.minX) <= eps &&
                    Math.abs(box.minY() - this.minY) <= eps &&
                    Math.abs(box.minZ() - this.minZ) <= eps &&
                    Math.abs(box.maxX() - this.maxX) <= eps &&
                    Math.abs(box.maxY() - this.maxY) <= eps &&
                    Math.abs(box.maxZ() - this.maxZ) <= eps;
        } else if (obj instanceof IBox3I) {
            IBox3I box = (IBox3I) obj;
            return
                    Math.abs(box.minX() - this.minX) <= eps &&
                    Math.abs(box.minY() - this.minY) <= eps &&
                    Math.abs(box.minZ() - this.minZ) <= eps &&
                    Math.abs(box.maxX() - this.maxX) <= eps &&
                    Math.abs(box.maxY() - this.maxY) <= eps &&
                    Math.abs(box.maxZ() - this.maxZ) <= eps;
        }
        return super.equals(obj);
    }

    @Override
    public @NotNull String toString() {
        return "[" + this.minX + ", " + this.minY + ", " + this.minZ + "] -> ["  + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
    }
}
