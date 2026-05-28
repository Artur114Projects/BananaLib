package com.artur114.bananalib.math.internal;

import org.jetbrains.annotations.ApiStatus;

/**
 * Internal runtime utility.<br>
 * Not intended for public use.
 */
@ApiStatus.Internal
public enum StackGrowMode {
    EXPONENT, LINEAR_1, LINEAR_2, LINEAR_3, LINEAR_4;

    public int grow(int size) {
        switch (this) {
            case EXPONENT:
                return size * 2;
            case LINEAR_1:
                return size + 1;
            case LINEAR_2:
                return size + 2;
            case LINEAR_3:
                return size + 3;
            case LINEAR_4:
                return size + 4;
            default:
                throw new IllegalStateException();
        }
    }
}
