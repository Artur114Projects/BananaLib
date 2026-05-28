package com.artur114.bananalib.math.core.m2d.vec;

import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;

public interface IVec2IC {
    int x();
    int y();
    float length();
    float lengthSq();
    int dot(int x, int y);
    double dot(double x, double y);
    int cross(int x, int y);
    double cross(double x, double y);
    float distance(int x, int y);
    double distance(double x, double y);
    float distanceSq(int x, int y);
    double distanceSq(double x, double y);
    IVec2IC add(int x, int y);
    IVec2IC add(double x, double y);
    IVec2IC subtract(int x, int y);
    IVec2IC subtract(double x, double y);
    IVec2IC scale(int val);
    IVec2IC scale(int x, int y);
    IVec2IC scale(double val);
    IVec2IC scale(double x, double y);
    IVec2IC divide(int val);
    IVec2IC divide(int x, int y);
    IVec2IC divide(double val);
    IVec2IC divide(double x, double y);
    IVec2IC rotate(double degrees);
    IVec2IC lerp(int x, int y, double delta);
    IVec2IC lerp(double x, double y, double delta);
    IVec2DC normalize();
    IVec2IC toMutable();
    IVec2IC toImmutable();
    IVec2DC toDouble();
}
