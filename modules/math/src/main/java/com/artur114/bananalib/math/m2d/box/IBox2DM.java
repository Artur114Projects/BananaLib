package com.artur114.bananalib.math.m2d.box;

import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;

public interface IBox2DM extends IBox2D {
    IBox2DM set(double[] box);
    IBox2DM set(int minX, int minY, int maxX, int maxY);
    IBox2DM set(double minX, double minY, double maxX, double maxY);
    IBox2DM set(IVec2DC boxFrom, IVec2DC boxTo);
    IBox2DM set(IVec2IC boxFrom, IVec2IC boxTo);
    IBox2DM set(IBox2DC area2D);
    IBox2DM set(IBox2IC area2D);
    IBox2DM setZero();
    IBox2DM collapseStack();
    IBox2DM resetStack();
    IBox2DM pushBox();
    IBox2DM popBox();
    IBox2DM grow(double amount);
    IBox2DM grow(double x, double y);
    IBox2DM grow(int amount);
    IBox2DM grow(int x, int y);
    IBox2DM grow(IVec2IC vec);
    IBox2DM grow(IVec2DC vec);
    IBox2DM offset(int x, int y);
    IBox2DM offset(double x, double y);
    IBox2DM offset(IVec2DC vec);
    IBox2DM offset(IVec2IC vec);
    IBox2IM floor();
    IBox2IM round();
    IBox2IM ceil();
    IBox2DM copy();
}
