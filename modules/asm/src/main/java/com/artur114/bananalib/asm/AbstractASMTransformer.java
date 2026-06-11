package com.artur114.bananalib.asm;

import com.artur114.bananalib.asm.tree.ClassNodeAdv;
import com.artur114.bananalib.asm.util.IASMLogger;
import com.artur114.bananalib.asm.util.InsnCodes;

import java.io.PrintStream;

public abstract class AbstractASMTransformer implements IASMTransformer, InsnCodes {
    protected final String target;

    protected AbstractASMTransformer(String target) {
        this.target = target;
    }

    @Override
    public byte[] transform(IASMLogger logger, String className, byte[] bytecode) {
        ClassNodeAdv clazz = BananaASM.createClassNode(bytecode);
        this.transform(logger, className, clazz);
        return BananaASM.bakeBytecode(clazz);
    }

    protected abstract ClassNodeAdv transform(IASMLogger logger, String className, ClassNodeAdv clazz);

    @Override
    public boolean isTarget(String className) {
        return this.target.equals(className);
    }
}
