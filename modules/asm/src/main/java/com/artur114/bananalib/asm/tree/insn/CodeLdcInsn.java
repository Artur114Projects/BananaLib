package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;

public class CodeLdcInsn implements IInsnCode {
    private final OpValue<Object> cst;

    public CodeLdcInsn() {
        this.cst = OpValue.empty();
    }

    private CodeLdcInsn(Object cst) {
        this.cst = OpValue.of(cst);
    }

    public CodeLdcInsn with(Object cst) {
        return new CodeLdcInsn(cst);
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.LDC_INSN) {
            LdcInsnNode ldc = (LdcInsnNode) node;
            return this.cst.ifPEquals(ldc.cst);
        }
        return false;
    }
}
