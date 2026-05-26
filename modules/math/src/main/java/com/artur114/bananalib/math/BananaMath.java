package com.artur114.bananalib.math;

public class BananaMath {
    public static final double DOUBLE_EPS = 1.0E-12D;
    public static final float FLOAT_EPS = 1.0E-6F;

    public static float interpolate(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

    public static double interpolate(double start, double end, float pct) {
        return start + (end - start) * pct;
    }

    public static float interpolate(float start, float end, double pct) {
        return (float) (start + (end - start) * pct);
    }

    public static double interpolate(double start, double end, double pct) {
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

    public static int round(double d) {
        int i = (int) d;
        return d - i > 0.5 ? i + 1 : i;
    }

    public static int round(float d) {
        int i = (int) d;
        return d - i > 0.5F ? i + 1 : i;
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
