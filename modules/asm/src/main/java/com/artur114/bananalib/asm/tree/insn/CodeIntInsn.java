package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.IntInsnNode;

public class CodeIntInsn implements IInsnCode {
    private final OpValue<Integer> operand;
    private final OpValue<Integer> opcode;

    public CodeIntInsn() {
        this.operand = OpValue.empty();
        this.opcode = OpValue.empty();
    }

    private CodeIntInsn(OpValue<Integer> opcode, OpValue<Integer> operand) {
        this.operand = operand;
        this.opcode = opcode;
    }

    public CodeIntInsn with(int opcode, int operand) {
        return new CodeIntInsn(OpValue.of(opcode), OpValue.of(operand));
    }

    public CodeIntInsn withOpcode(int opcode) {
        return new CodeIntInsn(OpValue.of(opcode), this.operand);
    }

    public CodeIntInsn withOperand(int operand) {
        return new CodeIntInsn(this.opcode, OpValue.of(operand));
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.INT_INSN) {
            IntInsnNode var = (IntInsnNode) node;
            return this.opcode.ifPEquals(var.getOpcode()) && this.operand.ifPEquals(var.operand);
        }
        return false;
    }
}
