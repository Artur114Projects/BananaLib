package com.artur114.bananalib.asm;

import com.artur114.bananalib.asm.tree.ClassNodeAdv;

import java.io.PrintStream;

public abstract class AbstractASMTransformer implements IASMTransformer {
    protected final String target;

    protected AbstractASMTransformer(String target) {
        this.target = target;
    }

    @Override
    public byte[] transform(PrintStream logOut, String className, byte[] bytecode) {
        ClassNodeAdv clazz = BananaASM.createClassNode(bytecode);
        this.transform(logOut, className, clazz);
        return BananaASM.bakeBytecode(clazz);
    }

    protected abstract ClassNodeAdv transform(PrintStream logOut, String className, ClassNodeAdv clazz);

    @Override
    public boolean isTarget(String className) {
        return this.target.equals(className);
    }
}
