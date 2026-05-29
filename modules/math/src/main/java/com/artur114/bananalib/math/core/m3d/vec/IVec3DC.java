package com.artur114.bananalib.math.core.m3d.vec;

import com.artur114.bananalib.math.core.IEqualsEpsD;

public interface IVec3DC extends IEqualsEpsD {
    double x();
    double y();
    double z();
    double length();
    double lengthSq();
    double dot(int x, int y, int z);
    double dot(double x, double y, double z);
    IVec3DC cross(int x, int y, int z);
    IVec3DC cross(double x, double y, double z);
    double distance(int x, int y, int z);
    double distance(double x, double y, double z);
    double distanceSq(int x, int y, int z);
    double distanceSq(double x, double y, double z);
    IVec3DC add(int x, int y, int z);
    IVec3DC add(double x, double y, double z);
    IVec3DC subtract(int x, int y, int z);
    IVec3DC subtract(double x, double y, double z);
    IVec3DC scale(int val);
    IVec3DC scale(double val);
    IVec3DC scale(int x, int y, int z);
    IVec3DC scale(double x, double y, double z);
    IVec3DC divide(int val);
    IVec3DC divide(double val);
    IVec3DC divide(int x, int y, int z);
    IVec3DC divide(double x, double y, double z);
    IVec3DC rotateX(double degrees);
    IVec3DC rotateY(double degrees);
    IVec3DC rotateZ(double degrees);
    IVec3DC lerp(int x, int y, int z, double delta);
    IVec3DC lerp(double x, double y, double z, double delta);
    IVec3DC normalize();
    IVec3DC toMutable();
    IVec3DC toImmutable();
    IVec3IC floor();
    IVec3IC round();
    IVec3IC ceil();
}
