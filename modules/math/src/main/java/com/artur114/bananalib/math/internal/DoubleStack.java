package com.artur114.bananalib.math.internal;

import java.util.Arrays;

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
}
