package com.artur114.bananalib.mc.math.m3d.vec;

import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2DM;
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m3d.box.IBox3D;
import com.artur114.bananalib.math.m3d.box.IBox3I;
import com.artur114.bananalib.math.m3d.vec.IVec3D;
import com.artur114.bananalib.math.m3d.vec.IVec3DM;
import com.artur114.bananalib.math.m3d.vec.IVec3I;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class VecMc3D extends Vec3d implements IVec3D {
    public VecMc3D(double xIn, double yIn, double zIn) {
        super(xIn, yIn, zIn);
    }

    public VecMc3D(Vec3i vector) {
        super(vector);
    }

    @Override
    public double x() {
        return 0;
    }

    @Override
    public double y() {
        return 0;
    }

    @Override
    public double z() {
        return 0;
    }

    @Override
    public IVec2D xy() {
        return null;
    }

    @Override
    public IVec2D xz() {
        return null;
    }

    @Override
    public IVec2D yx() {
        return null;
    }

    @Override
    public IVec2D yz() {
        return null;
    }

    @Override
    public IVec2D zx() {
        return null;
    }

    @Override
    public IVec2D zy() {
        return null;
    }

    @Override
    public IVec3D zyx() {
        return null;
    }

    @Override
    public IVec3D zxy() {
        return null;
    }

    @Override
    public IVec3D yzx() {
        return null;
    }

    @Override
    public IVec3D xzy() {
        return null;
    }

    @Override
    public IVec3D yxz() {
        return null;
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
        return 0;
    }

    @Override
    public double lengthSq() {
        return 0;
    }

    @Override
    public double dot(IVec3I vec) {
        return 0;
    }

    @Override
    public double dot(IVec3D vec) {
        return 0;
    }

    @Override
    public IVec3D cross(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3D cross(IVec3D vec) {
        return null;
    }

    @Override
    public double distance(int x, int y, int z) {
        return 0;
    }

    @Override
    public double distance(double x, double y, double z) {
        return 0;
    }

    @Override
    public double distance(IVec3I vec) {
        return 0;
    }

    @Override
    public double distance(IVec3D vec) {
        return 0;
    }

    @Override
    public double distanceSq(int x, int y, int z) {
        return 0;
    }

    @Override
    public double distanceSq(double x, double y, double z) {
        return 0;
    }

    @Override
    public double distanceSq(IVec3I vec) {
        return 0;
    }

    @Override
    public double distanceSq(IVec3D vec) {
        return 0;
    }

    @Override
    public IVec3D add(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3D add(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3D add(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3D add(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3D add(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3D add(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3D add(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3D add(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3D add(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3D add(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3D subtract(int x, int y, int z) {
        return null;
    }

    @Override
    public VecMc3D subtract(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3D subtract(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3D scale(int val) {
        return null;
    }

    @Override
    public IVec3D scale(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3D scale(double x, double y, double z) {
        return null;
    }

    @Override
    public VecMc3D scale(double factor) {
        return null;
    }

    @Override
    public IVec3D scale(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3D scale(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3D scale(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3D scale(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3D scale(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3D scale(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3D scale(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3D scale(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3D divide(int val) {
        return null;
    }

    @Override
    public IVec3D divide(double val) {
        return null;
    }

    @Override
    public IVec3D divide(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3D divide(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3D divide(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3D divide(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3D divide(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3D divide(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3D divide(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3D divide(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3D divide(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3D divide(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3D rotateX(double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateY(double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateZ(double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateXAround(int x, int y, int z, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateXAround(double x, double y, double z, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateXAround(IVec3I point, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateXAround(IVec3D point, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateYAround(int x, int y, int z, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateYAround(double x, double y, double z, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateYAround(IVec3I point, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateYAround(IVec3D point, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateZAround(int x, int y, int z, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateZAround(double x, double y, double z, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateZAround(IVec3I point, double degrees) {
        return null;
    }

    @Override
    public IVec3D rotateZAround(IVec3D point, double degrees) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox3I box) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox3D box) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox2I box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox2I box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox2D box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox2D box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox2I box) {
        return null;
    }

    @Override
    public IVec3D wrap(IBox2D box) {
        return null;
    }

    @Override
    public IVec3D wrap(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3D wrap(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3D wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return null;
    }

    @Override
    public IVec3D wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox3I box) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox3D box) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox2I box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox2I box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox2D box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox2D box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox2I box) {
        return null;
    }

    @Override
    public IVec3D clamp(IBox2D box) {
        return null;
    }

    @Override
    public IVec3D clamp(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3D clamp(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3D clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return null;
    }

    @Override
    public IVec3D clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return null;
    }

    @Override
    public VecMc3D normalize() {
        return null;
    }

    @Override
    public IVec3DM toMutable() {
        return null;
    }

    @Override
    public IVec3D toImmutable() {
        return null;
    }

    @Override
    public IVec3I floor() {
        return null;
    }

    @Override
    public IVec3I round() {
        return null;
    }

    @Override
    public IVec3I ceil() {
        return null;
    }

    @Override
    public IVec3D copy() {
        return null;
    }
}
