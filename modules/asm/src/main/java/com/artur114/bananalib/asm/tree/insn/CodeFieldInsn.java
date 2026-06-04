package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;

import java.util.Arrays;

public class CodeFieldInsn implements IInsnCode {
    private final OpValue<Integer> opcode;
    private final OpValue<String> owner;
    private final OpValue<String> name;
    private final OpValue<String> desc;
    private final OpValue<?>[] vars;

    public CodeFieldInsn() {
        this.opcode = OpValue.empty();
        this.owner = OpValue.empty();
        this.name = OpValue.empty();
        this.desc = OpValue.empty();
        this.vars = new OpValue[4];
        Arrays.fill(this.vars, OpValue.empty());
    }

    private CodeFieldInsn(OpValue<Integer> opcode, OpValue<String> owner, OpValue<String> name, OpValue<String> desc) {
        this.opcode = opcode;
        this.owner = owner;
        this.name = name;
        this.desc = desc;
        this.vars = new OpValue[] {opcode, owner, name, desc};
    }

    public CodeFieldInsn with(int opcode, String owner, String name, String desc) {
        return new CodeFieldInsn(OpValue.of(opcode), OpValue.of(owner), OpValue.of(name), OpValue.of(desc));
    }

    public CodeFieldInsn withOpcode(int opcode) {
        return new CodeFieldInsn(OpValue.of(opcode), this.owner, this.name, this.desc);
    }

    public CodeFieldInsn withOwner(String owner) {
        return new CodeFieldInsn(this.opcode, OpValue.of(owner), this.name, this.desc);
    }

    public CodeFieldInsn withName(String name) {
        return new CodeFieldInsn(this.opcode, this.owner, OpValue.of(name), this.desc);
    }

    public CodeFieldInsn withDesc(String desc) {
        return new CodeFieldInsn(this.opcode, this.owner, this.name, OpValue.of(desc));
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.FIELD_INSN) {
            FieldInsnNode field = (FieldInsnNode) node;
            return OpValue.equals(this.vars, field.getOpcode(), field.owner, field.name, field.desc);
        }
        return false;
    }
}
