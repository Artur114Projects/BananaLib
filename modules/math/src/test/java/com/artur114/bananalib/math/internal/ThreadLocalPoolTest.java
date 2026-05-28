package com.artur114.bananalib.math.internal;

import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m3d.vec.IVec3I;
import com.artur114.bananalib.math.m3d.vec.Vec3I;

class ThreadLocalPoolTest {
    public static void main(String[] args) {
        IVec3I pos = new Vec3I(24, 3, 53);
        IVec2I chunk = pos.divide(16).xz();
        System.out.println(chunk);
        IVec3I block = chunk.scale(16).xzy(24);
        System.out.println(block);
    }
}