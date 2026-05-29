package com.artur114.bananalib.math.core.m2d.matrix;

import com.artur114.bananalib.math.core.IEqualsEpsD;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;

import java.nio.DoubleBuffer;

public interface IMatrix2DC extends IEqualsEpsD {
    double m00();
    double m01();
    double m02();
    double m10();
    double m11();
    double m12();
    double m20();
    double m21();
    double m22();
    IMatrix2DC invert();
    double determinant();
    boolean isReversible();
    IMatrix2DC mul(IMatrix2DC matrix);
    IMatrix2DC mul(IMatrix2FC matrix);
    IMatrix2DC mulPost(IMatrix2DC matrix);
    IMatrix2DC mulPost(IMatrix2FC matrix);
    IMatrix2DC scale(int x, int y);
    IMatrix2DC scale(double x, double y);
    IMatrix2DC translate(int x, int y);
    IMatrix2DC translate(double x, double y);
    IMatrix2DC rotate(double degrees);
    IVec2IC transform(int x, int y);
    IVec2DC transform(double x, double y);
    DoubleBuffer writeToBuffer(DoubleBuffer buf);
    IMatrix2DC toMutable();
    IMatrix2DC toImmutable();
    IMatrix2FC toFloat();
}
