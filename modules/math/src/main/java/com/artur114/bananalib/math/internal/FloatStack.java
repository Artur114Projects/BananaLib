package com.artur114.bananalib.math.internal;

import java.util.Arrays;

/**
 * Internal runtime utility.<br>
 * Not intended for public use.
 */
public class FloatStack {
    private final StackGrowMode mode;
    private final int dataSize;
    private float[][] stack;
    private int cursor = 0;

    public FloatStack(int dataSize) {
        this(dataSize, StackGrowMode.LINEAR_1);
    }

    public FloatStack(int dataSize, StackGrowMode growMode) {
        if (dataSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.stack = new float[1][];
        this.mode = growMode;
        this.dataSize = dataSize;
    }

    private FloatStack(FloatStack parent) {
        this.stack = Arrays.copyOf(parent.stack, parent.stack.length);
        this.dataSize = parent.dataSize;
        this.cursor = parent.cursor;
        this.mode = parent.mode;

        for (int i = 0; i != this.stack.length; i++) {
            float[] a = this.stack[i];
            if (a != null) {
                this.stack[i] = Arrays.copyOf(a, a.length);
            }
        }
    }

    public void reset() {
        this.cursor = 0;
    }

    public void collapse() {
        this.stack = new float[1][];
        this.cursor = 0;
    }

    public float[] newEntry() {
        if (this.cursor >= this.stack.length) {
            this.stack = Arrays.copyOf(this.stack, this.mode.grow(this.stack.length));
        }

        float[] arr = this.stack[this.cursor++];

        if (arr == null) {
            arr = this.stack[this.cursor - 1] = new float[this.dataSize];
        }

        return arr;
    }

    public float[] pull() {
        if (this.cursor - 1 < 0) {
            throw new IllegalStateException();
        }
        return this.stack[--this.cursor];
    }

    public FloatStack copy() {
        return new FloatStack(this);
    }
}
