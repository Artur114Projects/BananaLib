package com.artur114.bananalib.math

import com.artur114.bananalib.math.m3d.vec.Vec3D

class Test {
    static void main(String[] args) {
        for (i in 0..40) {
            println ""
            float v = (float) ((new Random().nextFloat() * 2) - 1)

            println "v ${v}"
            println "my ${BananaMath.snap(v ,0.5)}"
            println "no my ${BananaMath.snap1(v ,0.5)}"
        }
    }
}
