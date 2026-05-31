package com.artur114.bananalib.math

import com.artur114.bananalib.math.m2d.matrix.Matrix2D
import com.artur114.bananalib.math.m2d.vec.Vec2D
import com.artur114.bananalib.math.m3d.matrix.Matrix3D


def v = new Vec2D(1, 0)

println(Matrix2D.IDENTITY.rotate(90).transform(v))
println(Matrix3D.IDENTITY.rotateZ(90).transform(v))