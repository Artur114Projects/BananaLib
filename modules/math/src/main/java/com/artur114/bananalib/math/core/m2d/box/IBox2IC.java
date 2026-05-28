package com.artur114.bananalib.math.core.m2d.box;

public interface IBox2IC {
    int minX();
    int minY();
    int maxX();
    int maxY();
    int size();
    boolean contains(int x, int y);
    boolean contains(double x, double y);
    boolean intersects(int minX, int minY, int maxX, int maxY);
    boolean intersects(double minX, double minY, double maxX, double maxY);
    IBox2IC grow(double amount);
    IBox2IC grow(double x, double y);
    IBox2IC grow(int amount);
    IBox2IC grow(int x, int y);
    IBox2IC offset(int x, int y);
    IBox2IC offset(double x, double y);
    IBox2IC toMutable();
    IBox2IC toImmutable();
    IBox2DC toDouble();
}
