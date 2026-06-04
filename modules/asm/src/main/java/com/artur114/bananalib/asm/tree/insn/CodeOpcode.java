package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;

public class CodeOpcode implements IInsnCode {
    private final OpValue<Integer> opcode;

    public CodeOpcode() {
        this.opcode = OpValue.empty();
    }

    private CodeOpcode(int opcode) {
        this.opcode = OpValue.of(opcode);
    }

    public CodeOpcode with(int opcode) {
        return new CodeOpcode(opcode);
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        return this.opcode.ifPEquals(node.getOpcode(), false);
    }
}
