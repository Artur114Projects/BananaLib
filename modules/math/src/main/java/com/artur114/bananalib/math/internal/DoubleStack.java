package com.artur114.bananalib.math.internal;

import java.util.Arrays;

/**
 * Internal runtime utility.<br>
 * Not intended for public use.
 */
public class DoubleStack {
    private final StackGrowMode mode;
    private final int dataSize;
    private double[][] stack;
    private int cursor = 0;

    public DoubleStack(int dataSize) {
        this(dataSize, StackGrowMode.LINEAR_1);
    }

    public DoubleStack(int dataSize, StackGrowMode growMode) {
        if (dataSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.stack = new double[1][];
        this.mode = growMode;
        this.dataSize = dataSize;
    }

    private DoubleStack(DoubleStack parent) {
        this.stack = Arrays.copyOf(parent.stack, parent.stack.length);
        this.dataSize = parent.dataSize;
        this.cursor = parent.cursor;
        this.mode = parent.mode;

        for (int i = 0; i != this.stack.length; i++) {
            double[] a = this.stack[i];
            if (a != null) {
                this.stack[i] = Arrays.copyOf(a, a.length);
            }
        }
    }

    public void reset() {
        this.cursor = 0;
    }

    public void collapse() {
        this.stack = new double[1][];
        this.cursor = 0;
    }

    public double[] newEntry() {
        if (this.cursor >= this.stack.length) {
            this.stack = Arrays.copyOf(this.stack, this.mode.grow(this.stack.length));
        }

        double[] arr = this.stack[this.cursor++];

        if (arr == null) {
            arr = this.stack[this.cursor - 1] = new double[this.dataSize];
        }

        return arr;
    }

    public double[] pull() {
        if (this.cursor - 1 < 0) {
            throw new IllegalStateException();
        }
        return this.stack[--this.cursor];
    }

    public DoubleStack copy() {
        return new DoubleStack(this);
    }
}
