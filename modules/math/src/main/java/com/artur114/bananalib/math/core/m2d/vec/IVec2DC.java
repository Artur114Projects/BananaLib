package com.artur114.bananalib.math.core.m2d.vec;

public interface IVec2DC {
    double x();
    double y();
    double length();
    double lengthSq();
    double dot(int x, int y);
    double dot(double x, double y);
    double cross(int x, int y);
    double cross(double x, double y);
    double distance(int x, int y);
    double distance(double x, double y);
    double distanceSq(int x, int y);
    double distanceSq(double x, double y);
    IVec2DC add(int x, int y);
    IVec2DC add(double x, double y);
    IVec2DC subtract(int x, int y);
    IVec2DC subtract(double x, double y);
    IVec2DC scale(int val);
    IVec2DC scale(int x, int y);
    IVec2DC scale(double val);
    IVec2DC scale(double x, double y);
    IVec2DC divide(int val);
    IVec2DC divide(int x, int y);
    IVec2DC divide(double val);
    IVec2DC divide(double x, double y);
    IVec2DC rotate(double degrees);
    IVec2DC lerp(int x, int y, double delta);
    IVec2DC lerp(double x, double y, double delta);
    IVec2DC normalize();
    IVec2DC toMutable();
    IVec2DC toImmutable();
    IVec2IC floor();
    IVec2IC round();
    IVec2IC ceil();
}
