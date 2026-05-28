package com.artur114.bananalib.math.m2d.box;

import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;

public interface IBox2I extends IBox2IC {
    boolean contains(IBox2IC box);
    boolean contains(IBox2DC box);
    boolean contains(IVec2IC vec);
    boolean contains(IVec2DC vec);
    boolean intersects(IVec2DC boxFrom, IVec2DC boxTo);
    boolean intersects(IVec2IC boxFrom, IVec2IC boxTo);
    boolean intersects(IBox2DC box);
    boolean intersects(IBox2IC box);
    IBox2I grow(double amount);
    IBox2I grow(double x, double y);
    IBox2I grow(int amount);
    IBox2I grow(int x, int y);
    IBox2I grow(IVec2IC vec);
    IBox2I grow(IVec2DC vec);
    IBox2I offset(int x, int y);
    IBox2I offset(double x, double y);
    IBox2I offset(IVec2DC vec);
    IBox2I offset(IVec2IC vec);
    IBox2IM toMutable();
    IBox2I toImmutable();
    IBox2D toDouble();
    IBox2I copy();
}
