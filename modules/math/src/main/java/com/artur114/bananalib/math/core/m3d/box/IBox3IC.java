package com.artur114.bananalib.math.core.m3d.box;

public interface IBox3IC {
    int minX();
    int minY();
    int minZ();
    int maxX();
    int maxY();
    int maxZ();
    int size();
    boolean contains(int x, int y, int z);
    boolean contains(double x, double y, double z);
    boolean intersects(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3IC grow(double amount);
    IBox3IC grow(double x, double y, double z);
    IBox3IC grow(int amount);
    IBox3IC grow(int x, int y, int z);
    IBox3IC offset(int x, int y, int z);
    IBox3IC offset(double x, double y, double z);
    IBox3IC include(int x, int y, int z);
    IBox3IC include(double x, double y, double z);
    IBox3IC union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3IC union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3IC intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3IC intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3IC toMutable();
    IBox3IC toImmutable();
    IBox3DC toDouble();
}
