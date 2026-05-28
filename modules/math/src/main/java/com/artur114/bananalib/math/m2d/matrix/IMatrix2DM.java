package com.artur114.bananalib.math.m2d.matrix;

import com.artur114.bananalib.math.core.m2d.matrix.IMatrix2DC;
import com.artur114.bananalib.math.core.m2d.matrix.IMatrix2FC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;

public interface IMatrix2DM extends IMatrix2D {
    IMatrix2DM set(double m00, double m01, double m02, double m10, double m11, double m12);
    IMatrix2DM set(IMatrix2FC matrix);
    IMatrix2DM set(IMatrix2DC matrix);
    IMatrix2DM set(double[] ms);
    IMatrix2DM setM00(double m00);
    IMatrix2DM setM01(double m01);
    IMatrix2DM setM02(double m02);
    IMatrix2DM setM10(double m10);
    IMatrix2DM setM11(double m11);
    IMatrix2DM setM12(double m12);
    IMatrix2DM setIdentity();
    IMatrix2DM collapseStack();
    IMatrix2DM resetStack();
    IMatrix2DM pushMatrix();
    IMatrix2DM popMatrix();
    IMatrix2DM invert();
    IMatrix2DM mul(IMatrix2DC matrix);
    IMatrix2DM mul(IMatrix2FC matrix);
    IMatrix2DM mulPost(IMatrix2DC matrix);
    IMatrix2DM mulPost(IMatrix2FC matrix);
    IMatrix2DM scale(int x, int y);
    IMatrix2DM scale(double x, double y);
    IMatrix2DM scale(IVec2DC vec);
    IMatrix2DM scale(IVec2IC vec);
    IMatrix2DM translate(int x, int y);
    IMatrix2DM translate(double x, double y);
    IMatrix2DM translate(IVec2DC vec);
    IMatrix2DM translate(IVec2IC vec);
    IMatrix2DM rotate(double degrees);
    IMatrix2DM rotateAround(int x, int y, double degrees);
    IMatrix2DM rotateAround(double x, double y, double degrees);
    IMatrix2DM rotateAround(IVec2DC point, double degrees);
    IMatrix2DM rotateAround(IVec2IC point, double degrees);
    IMatrix2FM toFloat();
}
