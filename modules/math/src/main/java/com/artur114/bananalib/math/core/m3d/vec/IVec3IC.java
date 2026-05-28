package com.artur114.bananalib.math.core.m3d.vec;


public interface IVec3IC {
    int x();
    int y();
    int z();
    float length();
    float lengthSq();
    int dot(int x, int y, int z);
    double dot(double x, double y, double z);
    IVec3IC cross(int x, int y, int z);
    IVec3DC cross(double x, double y, double z);
    float distance(int x, int y, int z);
    double distance(double x, double y, double z);
    float distanceSq(int x, int y, int z);
    double distanceSq(double x, double y, double z);
    IVec3IC add(int x, int y, int z);
    IVec3IC add(double x, double y, double z);
    IVec3IC subtract(int x, int y, int z);
    IVec3IC subtract(double x, double y, double z);
    IVec3IC scale(int val);
    IVec3IC scale(double val);
    IVec3IC scale(int x, int y, int z);
    IVec3IC scale(double x, double y, double z);
    IVec3IC divide(int val);
    IVec3IC divide(double val);
    IVec3IC divide(int x, int y, int z);
    IVec3IC divide(double x, double y, double z);
    IVec3IC rotateX(double degrees);
    IVec3IC rotateY(double degrees);
    IVec3IC rotateZ(double degrees);
    IVec3IC lerp(int x, int y, int z, double delta);
    IVec3IC lerp(double x, double y, double z, double delta);
    IVec3DC normalize();
    IVec3IC toMutable();
    IVec3IC toImmutable();
    IVec3DC toDouble();
}
