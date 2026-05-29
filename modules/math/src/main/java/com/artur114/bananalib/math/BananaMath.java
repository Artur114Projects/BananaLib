package com.artur114.bananalib.math;

public class BananaMath {
    public static final double DOUBLE_EQUALS_EPS = 1.0E-12D;
    public static final float FLOAT_EQUALS_EPS = 1E-4F;
    public static final double DOUBLE_EPS = 1.0E-12D;
    public static final float FLOAT_EPS = 1.0E-6F;

    public static float lerp(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

    public static double lerp(double start, double end, float pct) {
        return start + (end - start) * pct;
    }

    public static float lerp(float start, float end, double pct) {
        return (float) (start + (end - start) * pct);
    }

    public static double lerp(double start, double end, double pct) {
        return start + (end - start) * pct;
    }

    public static int mod(double v) {
        return v < 0 ? -1 : 0;
    }

    public static int mod(float v) {
        return v < 0 ? -1 : 0;
    }

    public static int mod(int v) {
        return v < 0 ? -1 : 0;
    }

    public static double sin(double rad) {
        double sin = Math.sin(rad);
        if (Math.abs(sin) < DOUBLE_EPS) sin = 0.0;
        if (Math.abs(sin - 1.0) < DOUBLE_EPS) sin = 1.0;
        if (Math.abs(sin + 1.0) < DOUBLE_EPS) sin = -1.0;
        return sin;
    }

    public static double cos(double rad) {
        double cos = Math.cos(rad);
        if (Math.abs(cos) < DOUBLE_EPS) cos = 0.0;
        if (Math.abs(cos - 1.0) < DOUBLE_EPS) cos = 1.0;
        if (Math.abs(cos + 1.0) < DOUBLE_EPS) cos = -1.0;
        return cos;
    }

    public static int round(double d) {
        d = d + 0.5 + DOUBLE_EPS;
        int i = (int) d;
        return d < (double) i ? i - 1 : i;
    }

    public static int round(float f) {
        f = f + 0.5F + FLOAT_EPS;
        int i = (int) f;
        return f < (float) i ? i - 1 : i;
    }

    public static int floor(double d) {
        int i = (int) d;
        return d < (double) i ? i - 1 : i;
    }

    public static int floor(float f) {
        int i = (int) f;
        return f < (float) i ? i - 1 : i;
    }

    public static int ceil(float f) {
        int i = (int) f;
        return f > (float) i ? i + 1 : i;
    }

    public static int ceil(double d) {
        int i = (int) d;
        return d > (double) i ? i + 1 : i;
    }
}
