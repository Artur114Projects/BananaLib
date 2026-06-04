package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class CodeVarInsn implements IInsnCode {
    private final OpValue<Integer> opcode;
    private final OpValue<Integer> var;

    public CodeVarInsn() {
        this.opcode = OpValue.empty();
        this.var = OpValue.empty();
    }

    private CodeVarInsn(OpValue<Integer> opcode, OpValue<Integer> var) {
        this.opcode = opcode;
        this.var = var;
    }

    public CodeVarInsn with(int opcode, int var) {
        return new CodeVarInsn(OpValue.of(opcode), OpValue.of(var));
    }

    public CodeVarInsn withOpcode(int opcode) {
        return new CodeVarInsn(OpValue.of(opcode), this.var);
    }

    public CodeVarInsn withVar(int var) {
        return new CodeVarInsn(this.opcode, OpValue.of(var));
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.VAR_INSN) {
            VarInsnNode var = (VarInsnNode) node;
            return this.opcode.ifPEquals(var.getOpcode()) && this.var.ifPEquals(var.var);
        }
        return false;
    }
}
