package com.artur114.bananalib.mc.math.m3d.box;

import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m3d.box.IBox3D;
import com.artur114.bananalib.math.m3d.box.IBox3DM;
import com.artur114.bananalib.math.m3d.box.IBox3I;
import com.artur114.bananalib.math.m3d.vec.IVec3D;
import com.artur114.bananalib.math.m3d.vec.IVec3I;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AbbMc3D extends AxisAlignedBB implements IBox3D {
    public AbbMc3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(x1, y1, z1, x2, y2, z2);
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

    @Override
    public double minX() {
        return 0;
    }

    @Override
    public double minY() {
        return 0;
    }

    @Override
    public double minZ() {
        return 0;
    }

    @Override
    public double maxX() {
        return 0;
    }

    @Override
    public double maxY() {
        return 0;
    }

    @Override
    public double maxZ() {
        return 0;
    }

    @Override
    public double size() {
        return 0;
    }

    @Override
    public boolean contains(int x, int y, int z) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double z) {
        return false;
    }

    @Override
    public boolean contains(IBox3I box) {
        return false;
    }

    @Override
    public boolean contains(IBox3D box) {
        return false;
    }

    @Override
    public boolean contains(IVec3I vec) {
        return false;
    }

    @Override
    public boolean contains(IVec3D vec) {
        return false;
    }

    @Override
    public boolean contains(IVec2I vec, int z) {
        return false;
    }

    @Override
    public boolean contains(IVec2I vec, double z) {
        return false;
    }

    @Override
    public boolean contains(IVec2D vec, int z) {
        return false;
    }

    @Override
    public boolean contains(IVec2D vec, double z) {
        return false;
    }

    @Override
    public boolean contains(IVec2I vec) {
        return false;
    }

    @Override
    public boolean contains(IVec2D vec) {
        return false;
    }

    @Override
    public boolean contains(IBox2I box, int minZ, int maxZ) {
        return false;
    }

    @Override
    public boolean contains(IBox2I box, double minZ, double maxZ) {
        return false;
    }

    @Override
    public boolean contains(IBox2D box, int minZ, int maxZ) {
        return false;
    }

    @Override
    public boolean contains(IBox2D box, double minZ, double maxZ) {
        return false;
    }

    @Override
    public boolean contains(IBox2I box) {
        return false;
    }

    @Override
    public boolean contains(IBox2D box) {
        return false;
    }

    @Override
    public boolean intersects(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return false;
    }

    @Override
    public boolean intersects(IVec3D boxFrom, IVec3D boxTo) {
        return false;
    }

    @Override
    public boolean intersects(IVec3I boxFrom, IVec3I boxTo) {
        return false;
    }

    @Override
    public boolean intersects(IBox3D box) {
        return false;
    }

    @Override
    public boolean intersects(IBox3I box) {
        return false;
    }

    @Override
    public boolean intersects(IBox2I box, int minZ, int maxZ) {
        return false;
    }

    @Override
    public boolean intersects(IBox2I box, double minZ, double maxZ) {
        return false;
    }

    @Override
    public boolean intersects(IBox2D box, int minZ, int maxZ) {
        return false;
    }

    @Override
    public boolean intersects(IBox2D box, double minZ, double maxZ) {
        return false;
    }

    @Override
    public boolean intersects(IBox2I box) {
        return false;
    }

    @Override
    public boolean intersects(IBox2D box) {
        return false;
    }

    @Override
    public IBox3D grow(int amount) {
        return null;
    }

    @Override
    public IBox3D grow(int x, int y, int z) {
        return null;
    }

    @Override
    public AbbMc3D grow(double value) {
        return null;
    }

    @Override
    public AbbMc3D grow(double x, double y, double z) {
        return null;
    }

    @Override
    public IBox3D grow(IVec3I vec) {
        return null;
    }

    @Override
    public IBox3D grow(IVec3D vec) {
        return null;
    }

    @Override
    public IBox3D grow(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IBox3D grow(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IBox3D grow(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IBox3D grow(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IBox3D grow(IVec2I vec) {
        return null;
    }

    @Override
    public IBox3D grow(IVec2D vec) {
        return null;
    }

    @Override
    public IBox3D offset(int x, int y, int z) {
        return null;
    }

    @Override
    public AbbMc3D offset(double x, double y, double z) {
        return null;
    }

    @Override
    public IBox3D offset(IVec3I vec) {
        return null;
    }

    @Override
    public IBox3D offset(IVec3D vec) {
        return null;
    }

    @Override
    public IBox3D offset(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IBox3D offset(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IBox3D offset(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IBox3D offset(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IBox3D offset(IVec2I vec) {
        return null;
    }

    @Override
    public IBox3D offset(IVec2D vec) {
        return null;
    }

    @Override
    public IBox3D include(int x, int y, int z) {
        return null;
    }

    @Override
    public IBox3D include(double x, double y, double z) {
        return null;
    }

    @Override
    public IBox3D include(IVec3I vec) {
        return null;
    }

    @Override
    public IBox3D include(IVec3D vec) {
        return null;
    }

    @Override
    public IBox3D include(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IBox3D include(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IBox3D include(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IBox3D include(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IBox3D include(IVec2I vec) {
        return null;
    }

    @Override
    public IBox3D include(IVec2D vec) {
        return null;
    }

    @Override
    public IBox3D union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return null;
    }

    @Override
    public IBox3D union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return null;
    }

    @Override
    public IBox3D union(IVec3D boxFrom, IVec3D boxTo) {
        return null;
    }

    @Override
    public IBox3D union(IVec3I boxFrom, IVec3I boxTo) {
        return null;
    }

    @Override
    public IBox3D union(IBox3D box) {
        return null;
    }

    @Override
    public IBox3D union(IBox3I box) {
        return null;
    }

    @Override
    public IBox3D union(IBox2I box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IBox3D union(IBox2I box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IBox3D union(IBox2D box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IBox3D union(IBox2D box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IBox3D union(IBox2I box) {
        return null;
    }

    @Override
    public IBox3D union(IBox2D box) {
        return null;
    }

    @Override
    public IBox3D intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return null;
    }

    @Override
    public IBox3D intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return null;
    }

    @Override
    public IBox3D intersection(IVec3D boxFrom, IVec3D boxTo) {
        return null;
    }

    @Override
    public IBox3D intersection(IVec3I boxFrom, IVec3I boxTo) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox3D box) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox3I box) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox2I box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox2I box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox2D box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox2D box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox2I box) {
        return null;
    }

    @Override
    public IBox3D intersection(IBox2D box) {
        return null;
    }

    @Override
    public IBox3DM toMutable() {
        return null;
    }

    @Override
    public IBox3D toImmutable() {
        return null;
    }

    @Override
    public IBox3I floor() {
        return null;
    }

    @Override
    public IBox3I round() {
        return null;
    }

    @Override
    public IBox3I ceil() {
        return null;
    }

    @Override
    public IBox3D copy() {
        return null;
    }
}
