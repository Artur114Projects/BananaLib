package com.artur114.bananalib.bme;

public class BlackHole {
    public static volatile Object sink;
    public final Object[] data;

    public BlackHole(Object[] data) {
        this.data = data;
    }

    public void consume(Object obj) {
        sink = obj;
    }
}
