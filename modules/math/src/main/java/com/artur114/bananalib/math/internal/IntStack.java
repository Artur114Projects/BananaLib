package com.artur114.bananalib.math.internal;

import java.util.Arrays;

public class IntStack {
    private final StackGrowMode mode;
    private final int dataSize;
    private int[][] stack;
    private int cursor = 0;

    public IntStack(int dataSize) {
        this(dataSize, StackGrowMode.LINEAR_1);
    }

    public IntStack(int dataSize, StackGrowMode growMode) {
        if (dataSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.stack = new int[1][];
        this.mode = growMode;
        this.dataSize = dataSize;
    }

    public void reset() {
        this.cursor = 0;
    }

    public void collapse() {
        this.stack = new int[1][];
        this.cursor = 0;
    }

    public int[] newEntry() {
        if (this.cursor >= this.stack.length) {
            this.stack = Arrays.copyOf(this.stack, this.mode.grow(this.stack.length));
        }

        int[] arr = this.stack[this.cursor++];

        if (arr == null) {
            arr = this.stack[this.cursor - 1] = new int[this.dataSize];
        }

        return arr;
    }

    public int[] pull() {
        if (this.cursor - 1 < 0) {
            throw new IllegalStateException();
        }
        return this.stack[--this.cursor];
    }
}
