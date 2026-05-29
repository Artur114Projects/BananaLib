package com.artur114.bananalib.math.core.m2d.box;

import com.artur114.bananalib.math.core.IEqualsEpsD;

public interface IBox2DC extends IEqualsEpsD {
    double minX();
    double minY();
    double maxX();
    double maxY();
    double size();
    boolean contains(int x, int y);
    boolean contains(double x, double y);
    boolean intersects(int minX, int minY, int maxX, int maxY);
    boolean intersects(double minX, double minY, double maxX, double maxY);
    IBox2DC grow(double amount);
    IBox2DC grow(double x, double y);
    IBox2DC grow(int amount);
    IBox2DC grow(int x, int y);
    IBox2DC offset(int x, int y);
    IBox2DC offset(double x, double y);
    IBox2DC toMutable();
    IBox2DC toImmutable();
    IBox2IC floor();
    IBox2IC round();
    IBox2IC ceil();
}
