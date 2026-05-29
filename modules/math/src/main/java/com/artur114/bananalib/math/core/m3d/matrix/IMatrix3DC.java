package com.artur114.bananalib.math.core.m3d.matrix;

import com.artur114.bananalib.math.core.IEqualsEpsD;
import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC;
import com.artur114.bananalib.math.m3d.vec.IVec3DM;
import com.artur114.bananalib.math.m3d.vec.IVec3IM;
import org.jetbrains.annotations.Contract;

import java.nio.DoubleBuffer;

public interface IMatrix3DC extends IEqualsEpsD {
    double m00();
    double m01();
    double m02();
    double m03();
    double m10();
    double m11();
    double m12();
    double m13();
    double m20();
    double m21();
    double m22();
    double m23();
    double m30();
    double m31();
    double m32();
    double m33();
    @Contract("-> new")
    IMatrix3DC invert();
    @Contract("-> new")
    IMatrix3DC transpose();
    double determinant();
    boolean isReversible();
    IMatrix3DC mul(IMatrix3DC matrix);
    IMatrix3DC mul(IMatrix3FC matrix);
    IMatrix3DC mulPost(IMatrix3DC matrix);
    IMatrix3DC mulPost(IMatrix3FC matrix);
    IMatrix3DC div(int val);
    IMatrix3DC div(double val);
    IMatrix3DC scale(int val);
    IMatrix3DC scale(double val);
    IMatrix3DC scale(int x, int y, int z);
    IMatrix3DC scale(double x, double y, double z);
    IMatrix3DC translate(int x, int y, int z);
    IMatrix3DC translate(double x, double y, double z);
    IMatrix3DC rotateX(double degrees);
    IMatrix3DC rotateY(double degrees);
    IMatrix3DC rotateZ(double degrees);
    IMatrix3DC rotate(double degrees, int x, int y, int z);
    IMatrix3DC rotate(double degrees, double x, double y, double z);
    IMatrix3DC localScale(int val);
    IMatrix3DC localScale(double val);
    IMatrix3DC localScale(int x, int y, int z);
    IMatrix3DC localScale(double x, double y, double z);
    IMatrix3DC localTranslate(int x, int y, int z);
    IMatrix3DC localTranslate(double x, double y, double z);
    IMatrix3DC localRotateX(double degrees);
    IMatrix3DC localRotateY(double degrees);
    IMatrix3DC localRotateZ(double degrees);
    IMatrix3DC localRotate(double degrees, int x, int y, int z);
    IMatrix3DC localRotate(double degrees, double x, double y, double z);
    @Contract("_,_,_ -> new") IVec3IC transform(int x, int y, int z);
    @Contract("_,_,_ -> new") IVec3DC transform(double x, double y, double z);
    @Contract("_ -> new") IVec3IC transform(IVec3IC vec);
    @Contract("_ -> _") IVec3IM transform(IVec3IM vec);
    @Contract("_ -> new") IVec3DC transform(IVec3DC vec);
    @Contract("_ -> _") IVec3DM transform(IVec3DM vec);
    @Contract("_ -> _") DoubleBuffer writeToBuffer(DoubleBuffer buf);
    @Contract("-> new") IMatrix3DC toMutable();
    @Contract("-> this") IMatrix3DC toImmutable();
    @Contract("-> new") IMatrix3FC toFloat();
}
