package com.artur114.bananalib.math.core;

import com.artur114.bananalib.math.BananaMath;

public interface IEqualsEpsD {
    default boolean equalsEps(Object obj) {return this.equalsEps(obj, BananaMath.DOUBLE_EQUALS_EPS);}
    boolean equalsEps(Object obj, double eps);
}
