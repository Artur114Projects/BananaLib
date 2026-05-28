package com.artur114.bananalib.math.m2d.box;


import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;

public interface IBox2IM extends IBox2I {
    IBox2IM set(int[] box);
    IBox2IM set(int minX, int minY, int maxX, int maxY);
    IBox2IM set(double minX, double minY, double maxX, double maxY);
    IBox2IM set(IVec2DC boxFrom, IVec2DC boxTo);
    IBox2IM set(IVec2IC boxFrom, IVec2IC boxTo);
    IBox2IM set(IBox2DC box);
    IBox2IM set(IBox2IC box);
    IBox2IM setZero();
    IBox2IM collapseStack();
    IBox2IM resetStack();
    IBox2IM pushBox();
    IBox2IM popBox();
    IBox2IM grow(double amount);
    IBox2IM grow(double x, double y);
    IBox2IM grow(int amount);
    IBox2IM grow(int x, int y);
    IBox2IM grow(IVec2IC vec);
    IBox2IM grow(IVec2DC vec);
    IBox2IM offset(int x, int y);
    IBox2IM offset(double x, double y);
    IBox2IM offset(IVec2DC vec);
    IBox2IM offset(IVec2IC vec);
    IBox2DM toDouble();
    IBox2IM copy();
}
