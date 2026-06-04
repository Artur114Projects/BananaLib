package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;

import java.util.Arrays;

public class CodeInvokeDynamicInsn implements IInsnCode {
    private final OpValue<Integer> opcode;
    private final OpValue<String> name;
    private final OpValue<String> desc;
    private final OpValue<Handle> bsm;
    private final OpValue<Object[]> bsmArgs;
    private final OpValue<?>[] vars;

    public CodeInvokeDynamicInsn() {
        this.opcode = OpValue.empty();
        this.name = OpValue.empty();
        this.desc = OpValue.empty();
        this.bsm = OpValue.empty();
        this.bsmArgs = OpValue.empty();
        this.vars = new OpValue[4];
        Arrays.fill(this.vars, OpValue.empty());
    }

    private CodeInvokeDynamicInsn(OpValue<Integer> opcode, OpValue<String> name, OpValue<String> desc, OpValue<Handle> bsm, OpValue<Object[]> bsmArgs) {
        this.opcode = opcode;
        this.name = name;
        this.desc = desc;
        this.bsm = bsm;
        this.bsmArgs = bsmArgs;
        this.vars = new OpValue[] {opcode, name, desc, bsm};
    }

    public CodeInvokeDynamicInsn with(int opcode, String name, String desc, Handle bsm, Object[] bsmArgs) {
        return new CodeInvokeDynamicInsn(OpValue.of(opcode), OpValue.of(name), OpValue.of(desc), OpValue.of(bsm), OpValue.of(bsmArgs));
    }

    public CodeInvokeDynamicInsn withOpcode(int opcode) {
        return new CodeInvokeDynamicInsn(OpValue.of(opcode), this.name, this.desc, this.bsm, this.bsmArgs);
    }

    public CodeInvokeDynamicInsn withName(String name) {
        return new CodeInvokeDynamicInsn(this.opcode, OpValue.of(name), this.desc, this.bsm, this.bsmArgs);
    }

    public CodeInvokeDynamicInsn withDesc(String desc) {
        return new CodeInvokeDynamicInsn(this.opcode, this.name, OpValue.of(desc), this.bsm, this.bsmArgs);
    }

    public CodeInvokeDynamicInsn withBsm(Handle bsm) {
        return new CodeInvokeDynamicInsn(this.opcode, this.name, this.desc, OpValue.of(bsm), this.bsmArgs);
    }

    public CodeInvokeDynamicInsn withBsmArgs(Object[] bsmArgs) {
        return new CodeInvokeDynamicInsn(this.opcode, this.name, this.desc, this.bsm, OpValue.of(bsmArgs));
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.INVOKE_DYNAMIC_INSN) {
            InvokeDynamicInsnNode method = (InvokeDynamicInsnNode) node;
            return OpValue.equals(this.vars, method.getOpcode(), method.name, method.desc, method.bsm) && this.bsmArgs.ifPFun(arg -> Arrays.equals(arg, method.bsmArgs), true);
        }
        return false;
    }
}
