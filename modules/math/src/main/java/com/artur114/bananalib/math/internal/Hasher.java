package com.artur114.bananalib.math.internal;

public class Hasher {
    public static int hash(double v0) {
        return Double.hashCode(v0);
    }

    public static int hash(double v0, double v1) {
        return 31 * Double.hashCode(v0) + Double.hashCode(v1);
    }

    public static int hash(double v0, double v1, double v2) {
        return 31 * (31 * Double.hashCode(v0) + Double.hashCode(v1)) + Double.hashCode(v2);
    }

    public static int hash(double v0, double v1, double v2, double v3) {
        return 31 * (31 * (31 * Double.hashCode(v0) + Double.hashCode(v1)) + Double.hashCode(v2)) + Double.hashCode(v3);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4) {
        return 31 * (31 * (31 * (31 * Double.hashCode(v0) + Double.hashCode(v1)) + Double.hashCode(v2)) + Double.hashCode(v3)) + Double.hashCode(v4);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5) {
        return 31 * (31 * (31 * (31 * (31 * Double.hashCode(v0) + Double.hashCode(v1)) + Double.hashCode(v2)) + Double.hashCode(v3)) + Double.hashCode(v4)) + Double.hashCode(v5);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6) {
        return 31 * (31 * (31 * (31 * (31 * (31 * Double.hashCode(v0) + Double.hashCode(v1)) + Double.hashCode(v2)) + Double.hashCode(v3)) + Double.hashCode(v4)) + Double.hashCode(v5)) + Double.hashCode(v6);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {
        return 31 * (31 * (31 * (31 * (31 * (31 * (31 * Double.hashCode(v0) + Double.hashCode(v1)) + Double.hashCode(v2)) + Double.hashCode(v3)) + Double.hashCode(v4)) + Double.hashCode(v5)) + Double.hashCode(v6)) + Double.hashCode(v7);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8) {
        return 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * Double.hashCode(v0) + Double.hashCode(v1)) + Double.hashCode(v2)) + Double.hashCode(v3)) + Double.hashCode(v4)) + Double.hashCode(v5)) + Double.hashCode(v6)) + Double.hashCode(v7)) + Double.hashCode(v8);
    }

    public static int hash(double... vs) {
        int ret = 1;

        for (double v : vs) {
            ret = 31 * ret + Double.hashCode(v);
        }

        return ret;
    }

    public static int hashIEEE754(double v0) {
        return Double.hashCode(v0 == 0.0D ? 0.0D : v0);
    }

    public static int hashIEEE754(double v0, double v1) {
        return 31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1);
    }

    public static int hashIEEE754(double v0, double v1, double v2) {
        return 31 * (31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1)) + Double.hashCode(v2 == 0.0D ? 0.0D : v2);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3) {
        return 31 * (31 * (31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1)) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)) + Double.hashCode(v3 == 0.0D ? 0.0D : v3);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4) {
        return 31 * (31 * (31 * (31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1)) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)) + Double.hashCode(v4 == 0.0D ? 0.0D : v4);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5) {
        return 31 * (31 * (31 * (31 * (31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1)) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)) + Double.hashCode(v5 == 0.0D ? 0.0D : v5);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6) {
        return 31 * (31 * (31 * (31 * (31 * (31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1)) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)) + Double.hashCode(v6 == 0.0D ? 0.0D : v6);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {
        return 31 * (31 * (31 * (31 * (31 * (31 * (31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1)) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)) + Double.hashCode(v7 == 0.0D ? 0.0D : v7);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8) {
        return 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0) + Double.hashCode(v1 == 0.0D ? 0.0D : v1)) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)) + Double.hashCode(v8 == 0.0D ? 0.0D : v8);
    }

    public static int hashIEEE754(double... vs) {
        int ret = 1;

        for (double v : vs) {
            ret = 31 * ret + Double.hashCode(v == 0.0D ? 0.0D : v);
        }

        return ret;
    }
}
