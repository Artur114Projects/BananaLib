package com.artur114.bananalib.math.m2d.matrix;

import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.matrix.IMatrix2DC;
import com.artur114.bananalib.math.core.m2d.matrix.IMatrix2FC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.m2d.box.IBox2D;
import com.artur114.bananalib.math.m2d.box.IBox2DM;
import com.artur114.bananalib.math.m2d.box.IBox2I;
import com.artur114.bananalib.math.m2d.box.IBox2IM;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2DM;
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m2d.vec.IVec2IM;

import java.nio.DoubleBuffer;


public interface IMatrix2D extends IMatrix2DC {
    IMatrix2D invert();
    IMatrix2D mul(IMatrix2DC matrix);
    IMatrix2D mul(IMatrix2FC matrix);
    IMatrix2D mulPost(IMatrix2DC matrix);
    IMatrix2D mulPost(IMatrix2FC matrix);
    IMatrix2D scale(int x, int y);
    IMatrix2D scale(double x, double y);
    IMatrix2D scale(IVec2DC vec);
    IMatrix2D scale(IVec2IC vec);
    IMatrix2D translate(int x, int y);
    IMatrix2D translate(double x, double y);
    IMatrix2D translate(IVec2DC vec);
    IMatrix2D translate(IVec2IC vec);
    IMatrix2D rotate(double degrees);
    IMatrix2D rotateAround(int x, int y, double degrees);
    IMatrix2D rotateAround(double x, double y, double degrees);
    IMatrix2D rotateAround(IVec2DC point, double degrees);
    IMatrix2D rotateAround(IVec2IC point, double degrees);
    IVec2I transform(int x, int y);
    IVec2D transform(double x, double y);
    IVec2I transform(IVec2IC vec);
    IVec2IM transform(IVec2IM vec);
    IVec2D transform(IVec2DC vec);
    IVec2DM transform(IVec2DM vec);
    IVec2I[] transform(IVec2IC... vec);
    IVec2IM[] transform(IVec2IM... vec);
    IVec2D[] transform(IVec2DC... vec);
    IVec2DM[] transform(IVec2DM... vec);
    IBox2I transform(IBox2IC box);
    IBox2IM transform(IBox2IM box);
    IBox2D transform(IBox2DC box);
    IBox2DM transform(IBox2DM box);
    IBox2I[] transform(IBox2IC... box);
    IBox2IM[] transform(IBox2IM... box);
    IBox2D[] transform(IBox2DC... box);
    IBox2DM[] transform(IBox2DM... box);
    DoubleBuffer writeToBuffer(DoubleBuffer buf);
    IMatrix2DM toMutable();
    IMatrix2D toImmutable();
    IMatrix2F toFloat();
    IMatrix2D copy();
}
