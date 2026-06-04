package com.artur114.bananalib.asm;

import java.io.PrintStream;

public interface IASMTransformer {
    byte[] transform(PrintStream logOut, String className, byte[] bytecode);
    boolean isTarget(String className);
    int priority();
}
