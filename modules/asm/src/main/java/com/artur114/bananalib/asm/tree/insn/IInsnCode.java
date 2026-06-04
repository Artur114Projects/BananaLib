package com.artur114.bananalib.asm.tree.insn;

import org.objectweb.asm.tree.AbstractInsnNode;

public interface IInsnCode {
    boolean matches(AbstractInsnNode node);
}
