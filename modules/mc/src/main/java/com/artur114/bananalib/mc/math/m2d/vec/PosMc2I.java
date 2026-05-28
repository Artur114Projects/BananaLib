//package com.artur114.bananalib.mc.math.m2d.vec;
//
//import com.artur114.bananalib.math.m2d.box.IBox2D;
//import com.artur114.bananalib.math.m2d.box.IBox2I;
//import com.artur114.bananalib.math.m2d.vec.IVec2D;
//import com.artur114.bananalib.math.m2d.vec.IVec2I;
//import com.artur114.bananalib.math.m2d.vec.IVec2IM;
//import com.artur114.bananalib.math.m3d.vec.IVec3D;
//import com.artur114.bananalib.math.m3d.vec.IVec3I;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.ChunkPos;
//
//public class PosMc2I extends ChunkPos implements IVec2I {
//    public PosMc2I(int x, int z) {
//        super(x, z);
//    }
//
//    public PosMc2I(BlockPos pos) {
//        super(pos);
//    }
//
//    @Override
//    public int x() {
//        return 0;
//    }
//
////    @Override
//    public int y() {
//        return 0;
//    }
//
//    @Override
//    public IVec2I yx() {
//        return null;
//    }
//
//    @Override
//    public IVec3I xyz(int z) {
//        return null;
//    }
//
//    @Override
//    public IVec3I xzy(int z) {
//        return null;
//    }
//
//    @Override
//    public IVec3I zxy(int z) {
//        return null;
//    }
//
//    @Override
//    public IVec2D yxD() {
//        return null;
//    }
//
//    @Override
//    public IVec3D xyzD(double z) {
//        return null;
//    }
//
//    @Override
//    public IVec3D xzyD(double z) {
//        return null;
//    }
//
//    @Override
//    public IVec3D zxyD(double z) {
//        return null;
//    }
//
//    @Override
//    public float length() {
//        return 0;
//    }
//
//    @Override
//    public float lengthSq() {
//        return 0;
//    }
//
//    @Override
//    public int dot(IVec2I vec) {
//        return 0;
//    }
//
//    @Override
//    public double dot(IVec2D vec) {
//        return 0;
//    }
//
//    @Override
//    public int cross(IVec2I vec) {
//        return 0;
//    }
//
//    @Override
//    public double cross(IVec2D vec) {
//        return 0;
//    }
//
//    @Override
//    public float distance(int x, int y) {
//        return 0;
//    }
//
//    @Override
//    public float distance(double x, double y) {
//        return 0;
//    }
//
//    @Override
//    public float distance(IVec2I vec) {
//        return 0;
//    }
//
//    @Override
//    public float distance(IVec2D vec) {
//        return 0;
//    }
//
//    @Override
//    public float distanceSq(int x, int y) {
//        return 0;
//    }
//
//    @Override
//    public float distanceSq(double x, double y) {
//        return 0;
//    }
//
//    @Override
//    public float distanceSq(IVec2I vec) {
//        return 0;
//    }
//
//    @Override
//    public float distanceSq(IVec2D vec) {
//        return 0;
//    }
//
//    @Override
//    public IVec2I add(int x, int y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I add(double x, double y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I add(IVec2I vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I add(IVec2D vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I subtract(int x, int y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I subtract(double x, double y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I subtract(IVec2I vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I subtract(IVec2D vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I scale(int val) {
//        return null;
//    }
//
//    @Override
//    public IVec2I scale(int x, int y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I scale(double val) {
//        return null;
//    }
//
//    @Override
//    public IVec2I scale(double x, double y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I scale(IVec2I vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I scale(IVec2D vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I divide(int val) {
//        return null;
//    }
//
//    @Override
//    public IVec2I divide(int x, int y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I divide(double val) {
//        return null;
//    }
//
//    @Override
//    public IVec2I divide(double x, double y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I divide(IVec2I vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I divide(IVec2D vec) {
//        return null;
//    }
//
//    @Override
//    public IVec2I rotate(double degrees) {
//        return null;
//    }
//
//    @Override
//    public IVec2I rotateAround(int x, int y, double degrees) {
//        return null;
//    }
//
//    @Override
//    public IVec2I rotateAround(double x, double y, double degrees) {
//        return null;
//    }
//
//    @Override
//    public IVec2I rotateAround(IVec2D point, double degrees) {
//        return null;
//    }
//
//    @Override
//    public IVec2I rotateAround(IVec2I point, double degrees) {
//        return null;
//    }
//
//    @Override
//    public IVec2I wrap(IBox2I box) {
//        return null;
//    }
//
//    @Override
//    public IVec2I wrap(IBox2D box) {
//        return null;
//    }
//
//    @Override
//    public IVec2I wrap(int x, int y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I wrap(double x, double y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I wrap(int minX, int minY, int maxX, int maxY) {
//        return null;
//    }
//
//    @Override
//    public IVec2I wrap(double minX, double minY, double maxX, double maxY) {
//        return null;
//    }
//
//    @Override
//    public IVec2I clamp(IBox2I box) {
//        return null;
//    }
//
//    @Override
//    public IVec2I clamp(IBox2D box) {
//        return null;
//    }
//
//    @Override
//    public IVec2I clamp(int x, int y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I clamp(double x, double y) {
//        return null;
//    }
//
//    @Override
//    public IVec2I clamp(int minX, int minY, int maxX, int maxY) {
//        return null;
//    }
//
//    @Override
//    public IVec2I clamp(double minX, double minY, double maxX, double maxY) {
//        return null;
//    }
//
//    @Override
//    public IVec2D normalize() {
//        return null;
//    }
//
//    @Override
//    public IVec2IM toMutable() {
//        return null;
//    }
//
//    @Override
//    public IVec2I toImmutable() {
//        return null;
//    }
//
//    @Override
//    public IVec2D toDouble() {
//        return null;
//    }
//
//    @Override
//    public IVec2I copy() {
//        return null;
//    }
//}
