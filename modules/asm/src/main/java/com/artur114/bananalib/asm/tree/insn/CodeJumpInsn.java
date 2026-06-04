package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;

public class CodeJumpInsn implements IInsnCode {
    private final OpValue<Integer> opcode;
    private final OpValue<Label> label;

    public CodeJumpInsn() {
        this.opcode = OpValue.empty();
        this.label = OpValue.empty();
    }

    private CodeJumpInsn(OpValue<Integer> opcode, OpValue<Label> label) {
        this.opcode = opcode;
        this.label = label;
    }

    public CodeJumpInsn with(int opcode, Label label) {
        return new CodeJumpInsn(OpValue.of(opcode), OpValue.of(label));
    }

    public CodeJumpInsn withOpcode(int opcode) {
        return new CodeJumpInsn(OpValue.of(opcode), this.label);
    }

    public CodeJumpInsn withLabel(Label label) {
        return new CodeJumpInsn(this.opcode, OpValue.of(label));
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.JUMP_INSN) {
            JumpInsnNode jump = (JumpInsnNode) node;
            return this.opcode.ifPEquals(jump.getOpcode()) && this.label.ifPEquals(jump.label);
        }
        return false;
    }
}
