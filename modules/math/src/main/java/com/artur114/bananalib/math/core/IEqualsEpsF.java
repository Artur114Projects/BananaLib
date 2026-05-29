package com.artur114.bananalib.math.core;

import com.artur114.bananalib.math.BananaMath;

public interface IEqualsEpsF {
    default boolean equalsEps(Object obj) {return this.equalsEps(obj, BananaMath.FLOAT_EQUALS_EPS);}
    boolean equalsEps(Object obj, float eps);
}
