package com.artur114.bananalib.math

import com.artur114.bananalib.math.m3d.vec.Vec3D

class Test {
    static void main(String[] args) {
        Vec3D vec = new Vec3D(3 ,1 , 2)
        Vec3D v1 = vec * 2
        println v1
        println v1[0]
    }
}
