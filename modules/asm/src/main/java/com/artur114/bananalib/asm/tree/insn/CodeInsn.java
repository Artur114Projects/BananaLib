package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;

public class CodeInsn implements IInsnCode {
    private final OpValue<Integer> opcode;

    public CodeInsn() {
        this.opcode = OpValue.empty();
    }

    private CodeInsn(int opcode) {
        this.opcode = OpValue.of(opcode);
    }

    public CodeInsn with(int opcode) {
        return new CodeInsn(opcode);
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.INSN) {
            InsnNode insnNode = (InsnNode) node;
            return this.opcode.ifPEquals(insnNode.getOpcode());
        }
        return false;
    }
}
