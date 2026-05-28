package com.artur114.bananalib.math.m2d.box;

import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;

public interface IBox2D extends IBox2DC {
    boolean contains(IBox2IC box);
    boolean contains(IBox2DC box);
    boolean contains(IVec2IC vec);
    boolean contains(IVec2DC vec);
    boolean intersects(IVec2DC boxFrom, IVec2DC boxTo);
    boolean intersects(IVec2IC boxFrom, IVec2IC boxTo);
    boolean intersects(IBox2DC box);
    boolean intersects(IBox2IC box);
    IBox2D grow(double amount);
    IBox2D grow(double x, double y);
    IBox2D grow(int amount);
    IBox2D grow(int x, int y);
    IBox2D grow(IVec2IC vec);
    IBox2D grow(IVec2DC vec);
    IBox2D offset(int x, int y);
    IBox2D offset(double x, double y);
    IBox2D offset(IVec2DC vec);
    IBox2D offset(IVec2IC vec);
    IBox2DM toMutable();
    IBox2D toImmutable();
    IBox2I floor();
    IBox2I round();
    IBox2I ceil();
    IBox2D copy();
}
