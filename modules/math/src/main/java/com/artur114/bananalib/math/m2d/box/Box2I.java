package com.artur114.bananalib.math.m2d.box;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2I;

import java.util.Objects;

public class Box2I implements IBox2I {
    private final int minX, minY, maxX, maxY;

    public Box2I(int minX, int minY, int maxX, int maxY) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
    }

    public Box2I(double minX, double minY, double maxX, double maxY) {
        this(BananaMath.floor(minX), BananaMath.floor(minY), BananaMath.floor(maxX), BananaMath.floor(maxY));
    }

    public Box2I(IVec2DC from, IVec2DC to) {
        this(BananaMath.floor(from.x()), BananaMath.floor(from.y()), BananaMath.floor(to.x()), BananaMath.floor(to.y()));
    }

    public Box2I(IVec2IC from, IVec2IC to) {
        this(from.x(), from.y(), to.x(), to.y());
    }

    public Box2I(IBox2DC box) {
        this(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    public Box2I(IBox2IC box) {
        this(box.minX(), box.minY(), box.maxX(), box.maxY());
    }

    @Override
    public int minX() {
        return this.minX;
    }

    @Override
    public int minY() {
        return this.minY;
    }

    @Override
    public int maxX() {
        return this.maxX;
    }

    @Override
    public int maxY() {
        return this.maxY;
    }

    @Override
    public int size() {
        return (this.maxX - this.minX) * (this.maxY - this.minY);
    }

    @Override
    public IBox2I grow(double amount) {
        return this.grow(amount, amount);
    }

    @Override
    public IBox2I grow(double x, double y) {
        return new Box2I(this.minX - x, this.minY - y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2I grow(int amount) {
        return this.grow(amount, amount);
    }

    @Override
    public IBox2I grow(int x, int y) {
        return new Box2I(this.minX - x, this.minY - y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2I grow(IVec2IC vec2D) {
        return this.grow(vec2D.x(), vec2D.y());
    }

    @Override
    public IBox2I grow(IVec2DC vec2D) {
        return this.grow(vec2D.x(), vec2D.y());
    }

    @Override
    public IBox2I offset(int x, int y) {
        return new Box2I(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2I offset(double x, double y) {
        return new Box2I(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
    }

    @Override
    public IBox2I offset(IVec2DC vec2D) {
        return this.offset(vec2D.x(), vec2D.y());
    }

    @Override
    public IBox2I offset(IVec2IC vec2D) {
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
    public IBox2IM toMutable() {
        return new Box2IM(this);
    }

    @Override
    public IBox2I toImmutable() {
        return this;
    }

    @Override
    public IBox2D toDouble() {
        return new Box2D(this);
    }

    @Override
    public IBox2I copy() {
        return new Box2I(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IBox2I) {
            IBox2I box = (IBox2I) obj;
            return
                    box.minX() == this.minX &&
                    box.minY() == this.minY &&
                    box.maxX() == this.maxX &&
                    box.maxY() == this.maxY;
        } else if (obj instanceof IBox2D) {
            IBox2D box = (IBox2D) obj;
            return
                    box.minX() == this.minX &&
                    box.minY() == this.minY &&
                    box.maxX() == this.maxX &&
                    box.maxY() == this.maxY;
        }
        return false;
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
