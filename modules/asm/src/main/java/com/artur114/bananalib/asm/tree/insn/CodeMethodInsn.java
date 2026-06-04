package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.util.Arrays;

public class CodeMethodInsn implements IInsnCode {
    private final OpValue<Integer> opcode;
    private final OpValue<String> owner;
    private final OpValue<String> name;
    private final OpValue<String> desc;
    private final OpValue<Boolean> itf;
    private final OpValue<?>[] vars;

    public CodeMethodInsn() {
        this.opcode = OpValue.empty();
        this.owner = OpValue.empty();
        this.name = OpValue.empty();
        this.desc = OpValue.empty();
        this.itf = OpValue.empty();
        this.vars = new OpValue[5];
        Arrays.fill(this.vars, OpValue.empty());
    }

    private CodeMethodInsn(OpValue<Integer> opcode, OpValue<String> owner, OpValue<String> name, OpValue<String> desc, OpValue<Boolean> itf) {
        this.opcode = opcode;
        this.owner = owner;
        this.name = name;
        this.desc = desc;
        this.itf = itf;
        this.vars = new OpValue[] {opcode, owner, name, desc, itf};
    }

    public CodeMethodInsn with(int opcode, String owner, String name, String desc, boolean itf) {
        return new CodeMethodInsn(OpValue.of(opcode), OpValue.of(owner), OpValue.of(name), OpValue.of(desc), OpValue.of(itf));
    }

    public CodeMethodInsn withOpcode(int opcode) {
        return new CodeMethodInsn(OpValue.of(opcode), this.owner, this.name, this.desc, this.itf);
    }

    public CodeMethodInsn withOwner(String owner) {
        return new CodeMethodInsn(this.opcode, OpValue.of(owner), this.name, this.desc, this.itf);
    }

    public CodeMethodInsn withName(String name) {
        return new CodeMethodInsn(this.opcode, this.owner, OpValue.of(name), this.desc, this.itf);
    }

    public CodeMethodInsn withDesc(String desc) {
        return new CodeMethodInsn(this.opcode, this.owner, this.name, OpValue.of(desc), this.itf);
    }

    public CodeMethodInsn withItf(boolean itf) {
        return new CodeMethodInsn(this.opcode, this.owner, this.name, this.desc, OpValue.of(itf));
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.METHOD_INSN) {
            MethodInsnNode method = (MethodInsnNode) node;
            return OpValue.equals(this.vars, method.getOpcode(), method.owner, method.name, method.desc, method.itf);
        }
        return false;
    }
}
