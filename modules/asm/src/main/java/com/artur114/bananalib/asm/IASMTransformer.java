package com.artur114.bananalib.asm;

import com.artur114.bananalib.asm.util.IASMLogger;

import java.io.PrintStream;

public interface IASMTransformer {
    byte[] transform(IASMLogger logger, String className, byte[] bytecode);
    boolean isTarget(String className);
    int priority();
}
