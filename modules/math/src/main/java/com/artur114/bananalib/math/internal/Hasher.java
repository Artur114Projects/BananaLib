package com.artur114.bananalib.math.internal;

import org.jetbrains.annotations.ApiStatus;

/**
 * Internal runtime utility.<br>
 * Not intended for public use.<br>
 * Yeah, it's cursed!
 */
@ApiStatus.Internal
public class Hasher {
    public static int hash(double v0) {
        return Double.hashCode(v0);
    }

    public static int hash(double v0, double v1) {
        return 31 * Double.hashCode(v0) + Double.hashCode(v1);
    }

    public static int hash(double v0, double v1, double v2) {
        return 31 * (
                31 * Double.hashCode(v0) + Double.hashCode(v1)
        ) + Double.hashCode(v2);
    }

    public static int hash(double v0, double v1, double v2, double v3) {
        return 31 * (
                31 * (
                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                ) + Double.hashCode(v2)
        ) + Double.hashCode(v3);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4) {
        return 31 * (
                31 * (
                        31 * (
                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                        ) + Double.hashCode(v2)
                ) + Double.hashCode(v3)
        ) + Double.hashCode(v4);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                ) + Double.hashCode(v2)
                        ) + Double.hashCode(v3)
                ) + Double.hashCode(v4)
        ) + Double.hashCode(v5);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                        ) + Double.hashCode(v2)
                                ) + Double.hashCode(v3)
                        ) + Double.hashCode(v4)
                ) + Double.hashCode(v5)
        ) + Double.hashCode(v6);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                ) + Double.hashCode(v2)
                                        ) + Double.hashCode(v3)
                                ) + Double.hashCode(v4)
                        ) + Double.hashCode(v5)
                ) + Double.hashCode(v6)
        ) + Double.hashCode(v7);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                        ) + Double.hashCode(v2)
                                                ) + Double.hashCode(v3)
                                        ) + Double.hashCode(v4)
                                ) + Double.hashCode(v5)
                        ) + Double.hashCode(v6)
                ) + Double.hashCode(v7)
        ) + Double.hashCode(v8);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                ) + Double.hashCode(v2)
                                                        ) + Double.hashCode(v3)
                                                ) + Double.hashCode(v4)
                                        ) + Double.hashCode(v5)
                                ) + Double.hashCode(v6)
                        ) + Double.hashCode(v7)
                ) + Double.hashCode(v8)
        ) + Double.hashCode(v9);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                        ) + Double.hashCode(v2)
                                                                ) + Double.hashCode(v3)
                                                        ) + Double.hashCode(v4)
                                                ) + Double.hashCode(v5)
                                        ) + Double.hashCode(v6)
                                ) + Double.hashCode(v7)
                        ) + Double.hashCode(v8)
                ) + Double.hashCode(v9)
        ) + Double.hashCode(v10);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                ) + Double.hashCode(v2)
                                                                        ) + Double.hashCode(v3)
                                                                ) + Double.hashCode(v4)
                                                        ) + Double.hashCode(v5)
                                                ) + Double.hashCode(v6)
                                        ) + Double.hashCode(v7)
                                ) + Double.hashCode(v8)
                        ) + Double.hashCode(v9)
                ) + Double.hashCode(v10)
        ) + Double.hashCode(v11);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                        ) + Double.hashCode(v2)
                                                                                ) + Double.hashCode(v3)
                                                                        ) + Double.hashCode(v4)
                                                                ) + Double.hashCode(v5)
                                                        ) + Double.hashCode(v6)
                                                ) + Double.hashCode(v7)
                                        ) + Double.hashCode(v8)
                                ) + Double.hashCode(v9)
                        ) + Double.hashCode(v10)
                ) + Double.hashCode(v11)
        ) + Double.hashCode(v12);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                ) + Double.hashCode(v2)
                                                                                        ) + Double.hashCode(v3)
                                                                                ) + Double.hashCode(v4)
                                                                        ) + Double.hashCode(v5)
                                                                ) + Double.hashCode(v6)
                                                        ) + Double.hashCode(v7)
                                                ) + Double.hashCode(v8)
                                        ) + Double.hashCode(v9)
                                ) + Double.hashCode(v10)
                        ) + Double.hashCode(v11)
                ) + Double.hashCode(v12)
        ) + Double.hashCode(v13);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                        ) + Double.hashCode(v2)
                                                                                                ) + Double.hashCode(v3)
                                                                                        ) + Double.hashCode(v4)
                                                                                ) + Double.hashCode(v5)
                                                                        ) + Double.hashCode(v6)
                                                                ) + Double.hashCode(v7)
                                                        ) + Double.hashCode(v8)
                                                ) + Double.hashCode(v9)
                                        ) + Double.hashCode(v10)
                                ) + Double.hashCode(v11)
                        ) + Double.hashCode(v12)
                ) + Double.hashCode(v13)
        ) + Double.hashCode(v14);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                ) + Double.hashCode(v2)
                                                                                                        ) + Double.hashCode(v3)
                                                                                                ) + Double.hashCode(v4)
                                                                                        ) + Double.hashCode(v5)
                                                                                ) + Double.hashCode(v6)
                                                                        ) + Double.hashCode(v7)
                                                                ) + Double.hashCode(v8)
                                                        ) + Double.hashCode(v9)
                                                ) + Double.hashCode(v10)
                                        ) + Double.hashCode(v11)
                                ) + Double.hashCode(v12)
                        ) + Double.hashCode(v13)
                ) + Double.hashCode(v14)
        ) + Double.hashCode(v15);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                ) + Double.hashCode(v3)
                                                                                                        ) + Double.hashCode(v4)
                                                                                                ) + Double.hashCode(v5)
                                                                                        ) + Double.hashCode(v6)
                                                                                ) + Double.hashCode(v7)
                                                                        ) + Double.hashCode(v8)
                                                                ) + Double.hashCode(v9)
                                                        ) + Double.hashCode(v10)
                                                ) + Double.hashCode(v11)
                                        ) + Double.hashCode(v12)
                                ) + Double.hashCode(v13)
                        ) + Double.hashCode(v14)
                ) + Double.hashCode(v15)
        ) + Double.hashCode(v16);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                ) + Double.hashCode(v4)
                                                                                                        ) + Double.hashCode(v5)
                                                                                                ) + Double.hashCode(v6)
                                                                                        ) + Double.hashCode(v7)
                                                                                ) + Double.hashCode(v8)
                                                                        ) + Double.hashCode(v9)
                                                                ) + Double.hashCode(v10)
                                                        ) + Double.hashCode(v11)
                                                ) + Double.hashCode(v12)
                                        ) + Double.hashCode(v13)
                                ) + Double.hashCode(v14)
                        ) + Double.hashCode(v15)
                ) + Double.hashCode(v16)
        ) + Double.hashCode(v17);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                                ) + Double.hashCode(v3)
                                                                                                                        ) + Double.hashCode(v4)
                                                                                                                ) + Double.hashCode(v5)
                                                                                                        ) + Double.hashCode(v6)
                                                                                                ) + Double.hashCode(v7)
                                                                                        ) + Double.hashCode(v8)
                                                                                ) + Double.hashCode(v9)
                                                                        ) + Double.hashCode(v10)
                                                                ) + Double.hashCode(v11)
                                                        ) + Double.hashCode(v12)
                                                ) + Double.hashCode(v13)
                                        ) + Double.hashCode(v14)
                                ) + Double.hashCode(v15)
                        ) + Double.hashCode(v16)
                ) + Double.hashCode(v17)
        ) + Double.hashCode(v18);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                                ) + Double.hashCode(v4)
                                                                                                                        ) + Double.hashCode(v5)
                                                                                                                ) + Double.hashCode(v6)
                                                                                                        ) + Double.hashCode(v7)
                                                                                                ) + Double.hashCode(v8)
                                                                                        ) + Double.hashCode(v9)
                                                                                ) + Double.hashCode(v10)
                                                                        ) + Double.hashCode(v11)
                                                                ) + Double.hashCode(v12)
                                                        ) + Double.hashCode(v13)
                                                ) + Double.hashCode(v14)
                                        ) + Double.hashCode(v15)
                                ) + Double.hashCode(v16)
                        ) + Double.hashCode(v17)
                ) + Double.hashCode(v18)
        ) + Double.hashCode(v19);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                                                ) + Double.hashCode(v3)
                                                                                                                                        ) + Double.hashCode(v4)
                                                                                                                                ) + Double.hashCode(v5)
                                                                                                                        ) + Double.hashCode(v6)
                                                                                                                ) + Double.hashCode(v7)
                                                                                                        ) + Double.hashCode(v8)
                                                                                                ) + Double.hashCode(v9)
                                                                                        ) + Double.hashCode(v10)
                                                                                ) + Double.hashCode(v11)
                                                                        ) + Double.hashCode(v12)
                                                                ) + Double.hashCode(v13)
                                                        ) + Double.hashCode(v14)
                                                ) + Double.hashCode(v15)
                                        ) + Double.hashCode(v16)
                                ) + Double.hashCode(v17)
                        ) + Double.hashCode(v18)
                ) + Double.hashCode(v19)
        ) + Double.hashCode(v20);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                                                ) + Double.hashCode(v4)
                                                                                                                                        ) + Double.hashCode(v5)
                                                                                                                                ) + Double.hashCode(v6)
                                                                                                                        ) + Double.hashCode(v7)
                                                                                                                ) + Double.hashCode(v8)
                                                                                                        ) + Double.hashCode(v9)
                                                                                                ) + Double.hashCode(v10)
                                                                                        ) + Double.hashCode(v11)
                                                                                ) + Double.hashCode(v12)
                                                                        ) + Double.hashCode(v13)
                                                                ) + Double.hashCode(v14)
                                                        ) + Double.hashCode(v15)
                                                ) + Double.hashCode(v16)
                                        ) + Double.hashCode(v17)
                                ) + Double.hashCode(v18)
                        ) + Double.hashCode(v19)
                ) + Double.hashCode(v20)
        ) + Double.hashCode(v21);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                                                                ) + Double.hashCode(v3)
                                                                                                                                                        ) + Double.hashCode(v4)
                                                                                                                                                ) + Double.hashCode(v5)
                                                                                                                                        ) + Double.hashCode(v6)
                                                                                                                                ) + Double.hashCode(v7)
                                                                                                                        ) + Double.hashCode(v8)
                                                                                                                ) + Double.hashCode(v9)
                                                                                                        ) + Double.hashCode(v10)
                                                                                                ) + Double.hashCode(v11)
                                                                                        ) + Double.hashCode(v12)
                                                                                ) + Double.hashCode(v13)
                                                                        ) + Double.hashCode(v14)
                                                                ) + Double.hashCode(v15)
                                                        ) + Double.hashCode(v16)
                                                ) + Double.hashCode(v17)
                                        ) + Double.hashCode(v18)
                                ) + Double.hashCode(v19)
                        ) + Double.hashCode(v20)
                ) + Double.hashCode(v21)
        ) + Double.hashCode(v22);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                                                                ) + Double.hashCode(v4)
                                                                                                                                                        ) + Double.hashCode(v5)
                                                                                                                                                ) + Double.hashCode(v6)
                                                                                                                                        ) + Double.hashCode(v7)
                                                                                                                                ) + Double.hashCode(v8)
                                                                                                                        ) + Double.hashCode(v9)
                                                                                                                ) + Double.hashCode(v10)
                                                                                                        ) + Double.hashCode(v11)
                                                                                                ) + Double.hashCode(v12)
                                                                                        ) + Double.hashCode(v13)
                                                                                ) + Double.hashCode(v14)
                                                                        ) + Double.hashCode(v15)
                                                                ) + Double.hashCode(v16)
                                                        ) + Double.hashCode(v17)
                                                ) + Double.hashCode(v18)
                                        ) + Double.hashCode(v19)
                                ) + Double.hashCode(v20)
                        ) + Double.hashCode(v21)
                ) + Double.hashCode(v22)
        ) + Double.hashCode(v23);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                                                                                ) + Double.hashCode(v3)
                                                                                                                                                                        ) + Double.hashCode(v4)
                                                                                                                                                                ) + Double.hashCode(v5)
                                                                                                                                                        ) + Double.hashCode(v6)
                                                                                                                                                ) + Double.hashCode(v7)
                                                                                                                                        ) + Double.hashCode(v8)
                                                                                                                                ) + Double.hashCode(v9)
                                                                                                                        ) + Double.hashCode(v10)
                                                                                                                ) + Double.hashCode(v11)
                                                                                                        ) + Double.hashCode(v12)
                                                                                                ) + Double.hashCode(v13)
                                                                                        ) + Double.hashCode(v14)
                                                                                ) + Double.hashCode(v15)
                                                                        ) + Double.hashCode(v16)
                                                                ) + Double.hashCode(v17)
                                                        ) + Double.hashCode(v18)
                                                ) + Double.hashCode(v19)
                                        ) + Double.hashCode(v20)
                                ) + Double.hashCode(v21)
                        ) + Double.hashCode(v22)
                ) + Double.hashCode(v23)
        ) + Double.hashCode(v24);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                                                                                ) + Double.hashCode(v4)
                                                                                                                                                                        ) + Double.hashCode(v5)
                                                                                                                                                                ) + Double.hashCode(v6)
                                                                                                                                                        ) + Double.hashCode(v7)
                                                                                                                                                ) + Double.hashCode(v8)
                                                                                                                                        ) + Double.hashCode(v9)
                                                                                                                                ) + Double.hashCode(v10)
                                                                                                                        ) + Double.hashCode(v11)
                                                                                                                ) + Double.hashCode(v12)
                                                                                                        ) + Double.hashCode(v13)
                                                                                                ) + Double.hashCode(v14)
                                                                                        ) + Double.hashCode(v15)
                                                                                ) + Double.hashCode(v16)
                                                                        ) + Double.hashCode(v17)
                                                                ) + Double.hashCode(v18)
                                                        ) + Double.hashCode(v19)
                                                ) + Double.hashCode(v20)
                                        ) + Double.hashCode(v21)
                                ) + Double.hashCode(v22)
                        ) + Double.hashCode(v23)
                ) + Double.hashCode(v24)
        ) + Double.hashCode(v25);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                                                                                                ) + Double.hashCode(v3)
                                                                                                                                                                                        ) + Double.hashCode(v4)
                                                                                                                                                                                ) + Double.hashCode(v5)
                                                                                                                                                                        ) + Double.hashCode(v6)
                                                                                                                                                                ) + Double.hashCode(v7)
                                                                                                                                                        ) + Double.hashCode(v8)
                                                                                                                                                ) + Double.hashCode(v9)
                                                                                                                                        ) + Double.hashCode(v10)
                                                                                                                                ) + Double.hashCode(v11)
                                                                                                                        ) + Double.hashCode(v12)
                                                                                                                ) + Double.hashCode(v13)
                                                                                                        ) + Double.hashCode(v14)
                                                                                                ) + Double.hashCode(v15)
                                                                                        ) + Double.hashCode(v16)
                                                                                ) + Double.hashCode(v17)
                                                                        ) + Double.hashCode(v18)
                                                                ) + Double.hashCode(v19)
                                                        ) + Double.hashCode(v20)
                                                ) + Double.hashCode(v21)
                                        ) + Double.hashCode(v22)
                                ) + Double.hashCode(v23)
                        ) + Double.hashCode(v24)
                ) + Double.hashCode(v25)
        ) + Double.hashCode(v26);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                                                                                                ) + Double.hashCode(v4)
                                                                                                                                                                                        ) + Double.hashCode(v5)
                                                                                                                                                                                ) + Double.hashCode(v6)
                                                                                                                                                                        ) + Double.hashCode(v7)
                                                                                                                                                                ) + Double.hashCode(v8)
                                                                                                                                                        ) + Double.hashCode(v9)
                                                                                                                                                ) + Double.hashCode(v10)
                                                                                                                                        ) + Double.hashCode(v11)
                                                                                                                                ) + Double.hashCode(v12)
                                                                                                                        ) + Double.hashCode(v13)
                                                                                                                ) + Double.hashCode(v14)
                                                                                                        ) + Double.hashCode(v15)
                                                                                                ) + Double.hashCode(v16)
                                                                                        ) + Double.hashCode(v17)
                                                                                ) + Double.hashCode(v18)
                                                                        ) + Double.hashCode(v19)
                                                                ) + Double.hashCode(v20)
                                                        ) + Double.hashCode(v21)
                                                ) + Double.hashCode(v22)
                                        ) + Double.hashCode(v23)
                                ) + Double.hashCode(v24)
                        ) + Double.hashCode(v25)
                ) + Double.hashCode(v26)
        ) + Double.hashCode(v27);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                                                                                                                ) + Double.hashCode(v3)
                                                                                                                                                                                                        ) + Double.hashCode(v4)
                                                                                                                                                                                                ) + Double.hashCode(v5)
                                                                                                                                                                                        ) + Double.hashCode(v6)
                                                                                                                                                                                ) + Double.hashCode(v7)
                                                                                                                                                                        ) + Double.hashCode(v8)
                                                                                                                                                                ) + Double.hashCode(v9)
                                                                                                                                                        ) + Double.hashCode(v10)
                                                                                                                                                ) + Double.hashCode(v11)
                                                                                                                                        ) + Double.hashCode(v12)
                                                                                                                                ) + Double.hashCode(v13)
                                                                                                                        ) + Double.hashCode(v14)
                                                                                                                ) + Double.hashCode(v15)
                                                                                                        ) + Double.hashCode(v16)
                                                                                                ) + Double.hashCode(v17)
                                                                                        ) + Double.hashCode(v18)
                                                                                ) + Double.hashCode(v19)
                                                                        ) + Double.hashCode(v20)
                                                                ) + Double.hashCode(v21)
                                                        ) + Double.hashCode(v22)
                                                ) + Double.hashCode(v23)
                                        ) + Double.hashCode(v24)
                                ) + Double.hashCode(v25)
                        ) + Double.hashCode(v26)
                ) + Double.hashCode(v27)
        ) + Double.hashCode(v28);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28, double v29) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                                                                                                                ) + Double.hashCode(v4)
                                                                                                                                                                                                        ) + Double.hashCode(v5)
                                                                                                                                                                                                ) + Double.hashCode(v6)
                                                                                                                                                                                        ) + Double.hashCode(v7)
                                                                                                                                                                                ) + Double.hashCode(v8)
                                                                                                                                                                        ) + Double.hashCode(v9)
                                                                                                                                                                ) + Double.hashCode(v10)
                                                                                                                                                        ) + Double.hashCode(v11)
                                                                                                                                                ) + Double.hashCode(v12)
                                                                                                                                        ) + Double.hashCode(v13)
                                                                                                                                ) + Double.hashCode(v14)
                                                                                                                        ) + Double.hashCode(v15)
                                                                                                                ) + Double.hashCode(v16)
                                                                                                        ) + Double.hashCode(v17)
                                                                                                ) + Double.hashCode(v18)
                                                                                        ) + Double.hashCode(v19)
                                                                                ) + Double.hashCode(v20)
                                                                        ) + Double.hashCode(v21)
                                                                ) + Double.hashCode(v22)
                                                        ) + Double.hashCode(v23)
                                                ) + Double.hashCode(v24)
                                        ) + Double.hashCode(v25)
                                ) + Double.hashCode(v26)
                        ) + Double.hashCode(v27)
                ) + Double.hashCode(v28)
        ) + Double.hashCode(v29);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28, double v29, double v30) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                                31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                                                                        ) + Double.hashCode(v2)
                                                                                                                                                                                                                                ) + Double.hashCode(v3)
                                                                                                                                                                                                                        ) + Double.hashCode(v4)
                                                                                                                                                                                                                ) + Double.hashCode(v5)
                                                                                                                                                                                                        ) + Double.hashCode(v6)
                                                                                                                                                                                                ) + Double.hashCode(v7)
                                                                                                                                                                                        ) + Double.hashCode(v8)
                                                                                                                                                                                ) + Double.hashCode(v9)
                                                                                                                                                                        ) + Double.hashCode(v10)
                                                                                                                                                                ) + Double.hashCode(v11)
                                                                                                                                                        ) + Double.hashCode(v12)
                                                                                                                                                ) + Double.hashCode(v13)
                                                                                                                                        ) + Double.hashCode(v14)
                                                                                                                                ) + Double.hashCode(v15)
                                                                                                                        ) + Double.hashCode(v16)
                                                                                                                ) + Double.hashCode(v17)
                                                                                                        ) + Double.hashCode(v18)
                                                                                                ) + Double.hashCode(v19)
                                                                                        ) + Double.hashCode(v20)
                                                                                ) + Double.hashCode(v21)
                                                                        ) + Double.hashCode(v22)
                                                                ) + Double.hashCode(v23)
                                                        ) + Double.hashCode(v24)
                                                ) + Double.hashCode(v25)
                                        ) + Double.hashCode(v26)
                                ) + Double.hashCode(v27)
                        ) + Double.hashCode(v28)
                ) + Double.hashCode(v29)
        ) + Double.hashCode(v30);
    }

    public static int hash(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28, double v29, double v30, double v31) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                                        31 * Double.hashCode(v0) + Double.hashCode(v1)
                                                                                                                                                                                                                                                ) + Double.hashCode(v2)
                                                                                                                                                                                                                                        ) + Double.hashCode(v3)
                                                                                                                                                                                                                                ) + Double.hashCode(v4)
                                                                                                                                                                                                                        ) + Double.hashCode(v5)
                                                                                                                                                                                                                ) + Double.hashCode(v6)
                                                                                                                                                                                                        ) + Double.hashCode(v7)
                                                                                                                                                                                                ) + Double.hashCode(v8)
                                                                                                                                                                                        ) + Double.hashCode(v9)
                                                                                                                                                                                ) + Double.hashCode(v10)
                                                                                                                                                                        ) + Double.hashCode(v11)
                                                                                                                                                                ) + Double.hashCode(v12)
                                                                                                                                                        ) + Double.hashCode(v13)
                                                                                                                                                ) + Double.hashCode(v14)
                                                                                                                                        ) + Double.hashCode(v15)
                                                                                                                                ) + Double.hashCode(v16)
                                                                                                                        ) + Double.hashCode(v17)
                                                                                                                ) + Double.hashCode(v18)
                                                                                                        ) + Double.hashCode(v19)
                                                                                                ) + Double.hashCode(v20)
                                                                                        ) + Double.hashCode(v21)
                                                                                ) + Double.hashCode(v22)
                                                                        ) + Double.hashCode(v23)
                                                                ) + Double.hashCode(v24)
                                                        ) + Double.hashCode(v25)
                                                ) + Double.hashCode(v26)
                                        ) + Double.hashCode(v27)
                                ) + Double.hashCode(v28)
                        ) + Double.hashCode(v29)
                ) + Double.hashCode(v30)
        ) + Double.hashCode(v31);
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
        return 31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                + Double.hashCode(v1 == 0.0D ? 0.0D : v1);
    }

    public static int hashIEEE754(double v0, double v1, double v2) {
        return 31 * (
                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3) {
        return 31 * (
                31 * (
                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4) {
        return 31 * (
                31 * (
                        31 * (
                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
        ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
        ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                        ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
        ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                        ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
        ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                        ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                        ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
        ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                        ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                        ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
        ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                        ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                        ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                        ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
        ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                                ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                        ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                                ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                        ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                                ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                        ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
                ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24)
        ) + Double.hashCode(v25 == 0.0D ? 0.0D : v25);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                                        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                                ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                                        ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                                ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                                        ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                                ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                                        ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                                ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
                        ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24)
                ) + Double.hashCode(v25 == 0.0D ? 0.0D : v25)
        ) + Double.hashCode(v26 == 0.0D ? 0.0D : v26);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                                                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                                        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                                                ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                                        ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                                                ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                                        ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                                                ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                                        ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
                                ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24)
                        ) + Double.hashCode(v25 == 0.0D ? 0.0D : v25)
                ) + Double.hashCode(v26 == 0.0D ? 0.0D : v26)
        ) + Double.hashCode(v27 == 0.0D ? 0.0D : v27);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                                                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                                                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                                                        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                                                ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                                                        ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                                                ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                                                        ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                                                ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                                                        ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                                                ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
                                        ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24)
                                ) + Double.hashCode(v25 == 0.0D ? 0.0D : v25)
                        ) + Double.hashCode(v26 == 0.0D ? 0.0D : v26)
                ) + Double.hashCode(v27 == 0.0D ? 0.0D : v27)
        ) + Double.hashCode(v28 == 0.0D ? 0.0D : v28);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28, double v29) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                                                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                                                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                                                                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                                                        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                                                                ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                                                        ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                                                                ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                                                        ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                                                                ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                                                        ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
                                                ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24)
                                        ) + Double.hashCode(v25 == 0.0D ? 0.0D : v25)
                                ) + Double.hashCode(v26 == 0.0D ? 0.0D : v26)
                        ) + Double.hashCode(v27 == 0.0D ? 0.0D : v27)
                ) + Double.hashCode(v28 == 0.0D ? 0.0D : v28)
        ) + Double.hashCode(v29 == 0.0D ? 0.0D : v29);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28, double v29, double v30) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                                31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                                                                        + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                                                                        ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                                                                ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                                                                        ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                                                                ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                                                                        ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                                                                ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                                                                        ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                                                                ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                                                                        ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                                                                ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                                                                        ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                                                                ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                                                                        ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                                                                ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                                                                        ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                                                                ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                                                                        ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                                                                ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                                                                        ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                                                                ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                                                                        ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                                                                ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
                                                        ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24)
                                                ) + Double.hashCode(v25 == 0.0D ? 0.0D : v25)
                                        ) + Double.hashCode(v26 == 0.0D ? 0.0D : v26)
                                ) + Double.hashCode(v27 == 0.0D ? 0.0D : v27)
                        ) + Double.hashCode(v28 == 0.0D ? 0.0D : v28)
                ) + Double.hashCode(v29 == 0.0D ? 0.0D : v29)
        ) + Double.hashCode(v30 == 0.0D ? 0.0D : v30);
    }

    public static int hashIEEE754(double v0, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12, double v13, double v14, double v15, double v16, double v17, double v18, double v19, double v20, double v21, double v22, double v23, double v24, double v25, double v26, double v27, double v28, double v29, double v30, double v31) {
        return 31 * (
                31 * (
                        31 * (
                                31 * (
                                        31 * (
                                                31 * (
                                                        31 * (
                                                                31 * (
                                                                        31 * (
                                                                                31 * (
                                                                                        31 * (
                                                                                                31 * (
                                                                                                        31 * (
                                                                                                                31 * (
                                                                                                                        31 * (
                                                                                                                                31 * (
                                                                                                                                        31 * (
                                                                                                                                                31 * (
                                                                                                                                                        31 * (
                                                                                                                                                                31 * (
                                                                                                                                                                        31 * (
                                                                                                                                                                                31 * (
                                                                                                                                                                                        31 * (
                                                                                                                                                                                                31 * (
                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                        31 * (
                                                                                                                                                                                                                                                31 * (
                                                                                                                                                                                                                                                        31 * Double.hashCode(v0 == 0.0D ? 0.0D : v0)
                                                                                                                                                                                                                                                                + Double.hashCode(v1 == 0.0D ? 0.0D : v1)
                                                                                                                                                                                                                                                ) + Double.hashCode(v2 == 0.0D ? 0.0D : v2)
                                                                                                                                                                                                                                        ) + Double.hashCode(v3 == 0.0D ? 0.0D : v3)
                                                                                                                                                                                                                                ) + Double.hashCode(v4 == 0.0D ? 0.0D : v4)
                                                                                                                                                                                                                        ) + Double.hashCode(v5 == 0.0D ? 0.0D : v5)
                                                                                                                                                                                                                ) + Double.hashCode(v6 == 0.0D ? 0.0D : v6)
                                                                                                                                                                                                        ) + Double.hashCode(v7 == 0.0D ? 0.0D : v7)
                                                                                                                                                                                                ) + Double.hashCode(v8 == 0.0D ? 0.0D : v8)
                                                                                                                                                                                        ) + Double.hashCode(v9 == 0.0D ? 0.0D : v9)
                                                                                                                                                                                ) + Double.hashCode(v10 == 0.0D ? 0.0D : v10)
                                                                                                                                                                        ) + Double.hashCode(v11 == 0.0D ? 0.0D : v11)
                                                                                                                                                                ) + Double.hashCode(v12 == 0.0D ? 0.0D : v12)
                                                                                                                                                        ) + Double.hashCode(v13 == 0.0D ? 0.0D : v13)
                                                                                                                                                ) + Double.hashCode(v14 == 0.0D ? 0.0D : v14)
                                                                                                                                        ) + Double.hashCode(v15 == 0.0D ? 0.0D : v15)
                                                                                                                                ) + Double.hashCode(v16 == 0.0D ? 0.0D : v16)
                                                                                                                        ) + Double.hashCode(v17 == 0.0D ? 0.0D : v17)
                                                                                                                ) + Double.hashCode(v18 == 0.0D ? 0.0D : v18)
                                                                                                        ) + Double.hashCode(v19 == 0.0D ? 0.0D : v19)
                                                                                                ) + Double.hashCode(v20 == 0.0D ? 0.0D : v20)
                                                                                        ) + Double.hashCode(v21 == 0.0D ? 0.0D : v21)
                                                                                ) + Double.hashCode(v22 == 0.0D ? 0.0D : v22)
                                                                        ) + Double.hashCode(v23 == 0.0D ? 0.0D : v23)
                                                                ) + Double.hashCode(v24 == 0.0D ? 0.0D : v24)
                                                        ) + Double.hashCode(v25 == 0.0D ? 0.0D : v25)
                                                ) + Double.hashCode(v26 == 0.0D ? 0.0D : v26)
                                        ) + Double.hashCode(v27 == 0.0D ? 0.0D : v27)
                                ) + Double.hashCode(v28 == 0.0D ? 0.0D : v28)
                        ) + Double.hashCode(v29 == 0.0D ? 0.0D : v29)
                ) + Double.hashCode(v30 == 0.0D ? 0.0D : v30)
        ) + Double.hashCode(v31 == 0.0D ? 0.0D : v31);
    }

    public static int hashIEEE754(double... vs) {
        int ret = 1;

        for (double v : vs) {
            ret = 31 * ret + Double.hashCode(v == 0.0D ? 0.0D : v);
        }

        return ret;
    }
}