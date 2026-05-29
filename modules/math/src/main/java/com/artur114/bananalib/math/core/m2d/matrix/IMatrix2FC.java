package com.artur114.bananalib.math.core.m2d.matrix;

import com.artur114.bananalib.math.core.IEqualsEpsF;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;

import java.nio.FloatBuffer;

public interface IMatrix2FC extends IEqualsEpsF {
    float m00();
    float m01();
    float m02();
    float m10();
    float m11();
    float m12();
    float m20();
    float m21();
    float m22();
    IMatrix2FC invert();
    float determinant();
    boolean isReversible();
    IMatrix2FC mul(IMatrix2FC matrix);
    IMatrix2FC mul(IMatrix2DC matrix);
    IMatrix2FC mulPost(IMatrix2FC matrix);
    IMatrix2FC mulPost(IMatrix2DC matrix);
    IMatrix2FC scale(int x, int y);
    IMatrix2FC scale(float x, float y);
    IMatrix2FC scale(double x, double y);
    IMatrix2FC translate(int x, int y);
    IMatrix2FC translate(float x, float y);
    IMatrix2FC translate(double x, double y);
    IMatrix2FC rotate(float degrees);
    IVec2IC transform(int x, int y);
    IVec2DC transform(float x, float y);
    IVec2DC transform(double x, double y);
    FloatBuffer writeToBuffer(FloatBuffer buf);
    IMatrix2FC toMutable();
    IMatrix2FC toImmutable();
    IMatrix2DC toDouble();
}
