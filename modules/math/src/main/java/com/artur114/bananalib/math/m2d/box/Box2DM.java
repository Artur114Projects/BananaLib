package com.artur114.bananalib.math.m2d.box;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.internal.DoubleStack;
import com.artur114.bananalib.math.internal.ThreadLocalPool;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;

import java.util.Objects;

public class Box2DM implements IBox2DM {
    private static final ThreadLocalPool<Box2DM> pool = new ThreadLocalPool<>(new Box2DM[4], Box2DM::new, box -> {
        box.resetStack().setZero();
        box.released = true;
        return box;
    }, box -> {
        box.released = false;
        return box;
    }, box -> box.released);

    public static Box2DM obtain() {
        return pool.obtain();
    }

    public static void release(Box2DM box) {
        pool.release(box);
    }

    private double minX, minY, maxX, maxY;
    private DoubleStack stateStack = null;
    private boolean released;

    public Box2DM() {}

    public Box2DM(double minX, double minY, double maxX, double maxY) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
    }

    public Box2DM(int minX, int minY, int maxX, int maxY) {
        this(minX, minY, maxX, (double) maxY);
    }

    public Box2DM(IVec2DC from, IVec2DC to) {
        this(from.x(), from.y(), to.x(), to.y());
    }

    public Box2DM(IVec2IC from, IVec2IC to) {
        this(from.x(), from.y(), to.x(), to.y());
    }

    public Box2DM(IBox2DC box) {
        this(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    public Box2DM(IBox2IC box) {
        this(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public IBox2DM set(double[] box) {
        if (box.length < 4) {
            throw new IllegalArgumentException();
        }
        this.minX = Math.min(box[0], box[2]);
        this.minY = Math.min(box[1], box[3]);
        this.maxX = Math.max(box[0], box[2]);
        this.maxY = Math.max(box[1], box[3]);
        return this;
    }

    @Override
    public IBox2DM set(int minX, int minY, int maxX, int maxY) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        return this;
    }

    @Override
    public IBox2DM set(double minX, double minY, double maxX, double maxY) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        return this;
    }

    @Override
    public IBox2DM set(IVec2DC boxFrom, IVec2DC boxTo) {
        return this.set(boxFrom.x(), boxFrom.y(), boxTo.x(), boxTo.y());
    }

    @Override
    public IBox2DM set(IVec2IC boxFrom, IVec2IC boxTo) {
        return this.set(boxFrom.x(), boxFrom.y(), boxTo.x(), boxTo.y());
    }

    @Override
    public IBox2DM set(IBox2DC area2D) {
        return this.set(area2D.minX(), area2D.minY(), area2D.maxX(), area2D.maxY());
    }

    @Override
    public IBox2DM set(IBox2IC area2D) {
        return this.set(area2D.minX(), area2D.minY(), area2D.maxX(), area2D.maxY());
    }

    @Override
    public IBox2DM setZero() {
        this.minX = 0.0D;
        this.minY = 0.0D;
        this.maxX = 0.0D;
        this.maxY = 0.0D;
        return this;
    }

    @Override
    public IBox2DM collapseStack() {
        this.stateStack = null;
        return this;
    }

    @Override
    public IBox2DM resetStack() {
        if (this.stateStack != null) {
            this.stateStack.reset();
        }
        return this;
    }

    @Override
    public IBox2DM pushBox() {
        if (this.stateStack == null) {
            this.stateStack = new DoubleStack(4);
        }
        double[] arr = this.stateStack.newEntry();
        arr[0] = this.minX;
        arr[1] = this.minY;
        arr[2] = this.maxX;
        arr[3] = this.maxY;
        return this;
    }

    @Override
    public IBox2DM popBox() {
        if (this.stateStack == null) {
            throw new IllegalStateException();
        }
        return this.set(this.stateStack.pull());
    }

    @Override
    public double minX() {
        return this.minX;
    }

    @Override
    public double minY() {
        return this.minY;
    }

    @Override
    public double maxX() {
        return this.maxX;
    }

    @Override
    public double maxY() {
        return this.maxY;
    }

    @Override
    public double size() {
        return (this.maxX - this.minX) * (this.maxY - this.minY);
    }

    @Override
    public IBox2DM grow(double amount) {
        return this.grow(amount, amount);
    }

    @Override
    public IBox2DM grow(double x, double y) {
        return this.set(this.minX - x, this.minY - y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2DM grow(int amount) {
        return this.grow(amount, amount);
    }

    @Override
    public IBox2DM grow(int x, int y) {
        return this.set(this.minX - x, this.minY - y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2DM grow(IVec2IC vec2D) {
        return this.grow(vec2D.x(), vec2D.y());
    }

    @Override
    public IBox2DM grow(IVec2DC vec2D) {
        return this.grow(vec2D.x(), vec2D.y());
    }

    @Override
    public IBox2DM offset(int x, int y) {
        return this.set(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2DM offset(double x, double y) {
        return this.set(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2DM offset(IVec2DC vec2D) {
        return this.offset(vec2D.x(), vec2D.y());
    }

    @Override
    public IBox2DM offset(IVec2IC vec2D) {
        return this.offset(vec2D.x(), vec2D.y());
    }

    @Override
    public boolean intersects(int minX, int minY, int maxX, int maxY) {
        return this.minX <= maxX && this.maxX >= minX && this.minY <= maxY && this.maxY >= minY;
    }

    @Override
    public boolean intersects(double minX, double minY, double maxX, double maxY) {
        return this.minX <= maxX && this.maxX >= minX && this.minY <= maxY && this.maxY >= minY;
    }

    @Override
    public boolean intersects(IVec2DC boxFrom, IVec2DC boxTo) {
        return this.intersects(boxFrom.x(), boxFrom.y(), boxTo.x(), boxTo.y());
    }

    @Override
    public boolean intersects(IVec2IC boxFrom, IVec2IC boxTo) {
        return this.intersects(boxFrom.x(), boxFrom.y(), boxTo.x(), boxTo.y());
    }

    @Override
    public boolean intersects(IBox2DC area2D) {
        return this.intersects(area2D.minX(), area2D.minY(), area2D.maxX(), area2D.maxY());
    }

    @Override
    public boolean intersects(IBox2IC area2D) {
        return this.intersects(area2D.minX(), area2D.minY(), area2D.maxX(), area2D.maxY());
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= this.minX && y >= this.minY && x <= this.maxX && y <= this.maxY;
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= this.minX && y >= this.minY && x <= this.maxX && y <= this.maxY;
    }

    @Override
    public boolean contains(IBox2IC area2D) {
        return this.contains(area2D.minX(), area2D.minY()) && this.contains(area2D.maxX(), area2D.maxY());
    }

    @Override
    public boolean contains(IBox2DC area2D) {
        return this.contains(area2D.minX(), area2D.minY()) && this.contains(area2D.maxX(), area2D.maxY());
    }

    @Override
    public boolean contains(IVec2IC vec2D) {
        return this.contains(vec2D.x(), vec2D.y());
    }

    @Override
    public boolean contains(IVec2DC vec2D) {
        return this.contains(vec2D.x(), vec2D.y());
    }

    @Override
    public IBox2DM toMutable() {
        return this;
    }

    @Override
    public IBox2D toImmutable() {
        return new Box2D(this);
    }

    @Override
    public IBox2IM floor() {
        return new Box2IM(BananaMath.ceil(this.minX), BananaMath.ceil(this.minY), BananaMath.floor(this.maxX), BananaMath.floor(this.maxY));
    }

    @Override
    public IBox2IM round() {
        return new Box2IM(BananaMath.round(this.minX), BananaMath.round(this.minY), BananaMath.round(this.maxX), BananaMath.round(this.maxY));
    }

    @Override
    public IBox2IM ceil() {
        return new Box2IM(BananaMath.floor(this.minX), BananaMath.floor(this.minY), BananaMath.ceil(this.maxX), BananaMath.ceil(this.maxY));
    }

    @Override
    public IBox2DM copy() {
        Box2DM box = new Box2DM(this);
        if (this.stateStack != null) {
            box.stateStack = this.stateStack.copy();
        }
        return box;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IBox2D && ((IBox2D) obj).minX() == this.minX && ((IBox2D) obj).minY() == this.minY && ((IBox2D) obj).maxX() == this.maxX && ((IBox2D) obj).maxY() == this.maxY) ||
                (obj instanceof IBox2I && ((IBox2I) obj).minX() == this.minX && ((IBox2I) obj).minY() == this.minY && ((IBox2I) obj).maxX() == this.maxX && ((IBox2I) obj).maxY() == this.maxY);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * (31 * Double.hashCode(this.minX) + Double.hashCode(this.minY)) + Double.hashCode(this.maxX)) + Double.hashCode(this.maxY);
    }

    @Override
    public String toString() {
        return "[" + this.minX + ", " + this.minY + "] -> ["  + this.maxX + ", " + this.maxY + "]";
    }
}
