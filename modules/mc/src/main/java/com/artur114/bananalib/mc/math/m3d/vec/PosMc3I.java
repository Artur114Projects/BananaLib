package com.artur114.bananalib.mc.math.m3d.vec;

import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m2d.vec.IVec2IM;
import com.artur114.bananalib.math.m3d.box.IBox3D;
import com.artur114.bananalib.math.m3d.box.IBox3I;
import com.artur114.bananalib.math.m3d.vec.IVec3D;
import com.artur114.bananalib.math.m3d.vec.IVec3I;
import com.artur114.bananalib.math.m3d.vec.IVec3IM;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

public class PosMc3I extends BlockPos implements IVec3I {
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
        return null;
    }

    @Override
    public IVec2I xz() {
        return null;
    }

    @Override
    public IVec2I yx() {
        return null;
    }

    @Override
    public IVec2I yz() {
        return null;
    }

    @Override
    public IVec2I zx() {
        return null;
    }

    @Override
    public IVec2I zy() {
        return null;
    }

    @Override
    public IVec3I zyx() {
        return null;
    }

    @Override
    public IVec3I zxy() {
        return null;
    }

    @Override
    public IVec3I yzx() {
        return null;
    }

    @Override
    public IVec3I xzy() {
        return null;
    }

    @Override
    public IVec3I yxz() {
        return null;
    }

    @Override
    public IVec2IM xy(IVec2IM out) {
        return null;
    }

    @Override
    public IVec2IM xz(IVec2IM out) {
        return null;
    }

    @Override
    public IVec2IM yx(IVec2IM out) {
        return null;
    }

    @Override
    public IVec2IM yz(IVec2IM out) {
        return null;
    }

    @Override
    public IVec2IM zx(IVec2IM out) {
        return null;
    }

    @Override
    public IVec2IM zy(IVec2IM out) {
        return null;
    }

    @Override
    public IVec3IM zyx(IVec3IM out) {
        return null;
    }

    @Override
    public IVec3IM zxy(IVec3IM out) {
        return null;
    }

    @Override
    public IVec3IM yzx(IVec3IM out) {
        return null;
    }

    @Override
    public IVec3IM xzy(IVec3IM out) {
        return null;
    }

    @Override
    public IVec3IM yxz(IVec3IM out) {
        return null;
    }


    @Override
    public float length() {
        return 0;
    }

    @Override
    public float lengthSq() {
        return 0;
    }

    @Override
    public int dot(IVec3I vec) {
        return 0;
    }

    @Override
    public double dot(IVec3D vec) {
        return 0;
    }

    @Override
    public IVec3I cross(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3D cross(IVec3D vec) {
        return null;
    }

    @Override
    public float distance(int x, int y, int z) {
        return 0;
    }

    @Override
    public float distance(double x, double y, double z) {
        return 0;
    }

    @Override
    public float distance(IVec3I vec) {
        return 0;
    }

    @Override
    public float distance(IVec3D vec) {
        return 0;
    }

    @Override
    public double distanceSq(double toX, double toY, double toZ) {
        return super.distanceSq(toX, toY, toZ);
    }

    @Override
    public float distanceSq(int x, int y, int z) {
        return 0;
    }

    @Override
    public float distanceSq(IVec3I vec) {
        return 0;
    }

    @Override
    public double distanceSq(IVec3D vec) {
        return 0;
    }

    @Override
    public @NotNull PosMc3I add(int x, int y, int z) {
        return null;
    }

    @Override
    public PosMc3I add(double x, double y, double z) {
        return null;
    }

    @Override
    public PosMc3I add(IVec3I vec) {
        return null;
    }

    @Override
    public PosMc3I add(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3I add(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3I add(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3I add(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3I add(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3I add(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3I add(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3I subtract(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3I subtract(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3I subtract(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3I scale(int val) {
        return null;
    }

    @Override
    public IVec3I scale(double val) {
        return null;
    }

    @Override
    public IVec3I scale(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3I scale(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3I scale(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3I scale(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3I scale(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3I scale(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3I scale(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3I scale(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3I scale(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3I scale(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3I divide(int val) {
        return null;
    }

    @Override
    public IVec3I divide(double val) {
        return null;
    }

    @Override
    public IVec3I divide(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3I divide(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3I divide(IVec3I vec) {
        return null;
    }

    @Override
    public IVec3I divide(IVec3D vec) {
        return null;
    }

    @Override
    public IVec3I divide(IVec2I vec, int z) {
        return null;
    }

    @Override
    public IVec3I divide(IVec2I vec, double z) {
        return null;
    }

    @Override
    public IVec3I divide(IVec2D vec, int z) {
        return null;
    }

    @Override
    public IVec3I divide(IVec2D vec, double z) {
        return null;
    }

    @Override
    public IVec3I divide(IVec2I vec) {
        return null;
    }

    @Override
    public IVec3I divide(IVec2D vec) {
        return null;
    }

    @Override
    public IVec3I rotateX(double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateY(double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateZ(double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateXAround(int x, int y, int z, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateXAround(double x, double y, double z, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateXAround(IVec3I point, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateXAround(IVec3D point, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateYAround(int x, int y, int z, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateYAround(double x, double y, double z, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateYAround(IVec3I point, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateYAround(IVec3D point, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateZAround(int x, int y, int z, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateZAround(double x, double y, double z, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateZAround(IVec3I point, double degrees) {
        return null;
    }

    @Override
    public IVec3I rotateZAround(IVec3D point, double degrees) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox3I box) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox3D box) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox2I box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox2I box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox2D box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox2D box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox2I box) {
        return null;
    }

    @Override
    public IVec3I wrap(IBox2D box) {
        return null;
    }

    @Override
    public IVec3I wrap(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3I wrap(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3I wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return null;
    }

    @Override
    public IVec3I wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox3I box) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox3D box) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox2I box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox2I box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox2D box, int minZ, int maxZ) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox2D box, double minZ, double maxZ) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox2I box) {
        return null;
    }

    @Override
    public IVec3I clamp(IBox2D box) {
        return null;
    }

    @Override
    public IVec3I clamp(int x, int y, int z) {
        return null;
    }

    @Override
    public IVec3I clamp(double x, double y, double z) {
        return null;
    }

    @Override
    public IVec3I clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return null;
    }

    @Override
    public IVec3I clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return null;
    }

    @Override
    public IVec3D normalize() {
        return null;
    }

    @Override
    public PosMc3I toImmutable() {
        return null;
    }

    @Override
    public IVec3IM toMutable() {
        return null;
    }

    @Override
    public IVec3D toDouble() {
        return null;
    }

    @Override
    public IVec3I copy() {
        return null;
    }
}
