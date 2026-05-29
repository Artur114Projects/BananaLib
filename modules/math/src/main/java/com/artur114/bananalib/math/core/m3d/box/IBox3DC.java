package com.artur114.bananalib.math.core.m3d.box;

import com.artur114.bananalib.math.core.IEqualsEpsD;

public interface IBox3DC extends IEqualsEpsD {
    double minX();
    double minY();
    double minZ();
    double maxX();
    double maxY();
    double maxZ();
    double size();
    boolean contains(int x, int y, int z);
    boolean contains(double x, double y, double z);
    boolean intersects(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3DC grow(double amount);
    IBox3DC grow(double x, double y, double z);
    IBox3DC grow(int amount);
    IBox3DC grow(int x, int y, int z);
    IBox3DC offset(int x, int y, int z);
    IBox3DC offset(double x, double y, double z);
    IBox3DC include(int x, int y, int z);
    IBox3DC include(double x, double y, double z);
    IBox3DC union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3DC union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3DC intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3DC intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3DC toMutable();
    IBox3DC toImmutable();
    IBox3IC floor();
    IBox3IC round();
    IBox3IC ceil();
}
