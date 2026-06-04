package com.artur114.bananalib.asm.tree.insn;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class CodeTypeInsn implements IInsnCode {
    private final OpValue<Integer> opcode;
    private final OpValue<String> desc;

    public CodeTypeInsn() {
        this.opcode = OpValue.empty();
        this.desc = OpValue.empty();
    }

    private CodeTypeInsn(OpValue<Integer> opcode, OpValue<String> desc) {
        this.opcode = opcode;
        this.desc = desc;
    }

    public CodeTypeInsn with(int opcode, String desc) {
        return new CodeTypeInsn(OpValue.of(opcode), OpValue.of(desc));
    }

    public CodeTypeInsn withOpcode(int opcode) {
        return new CodeTypeInsn(OpValue.of(opcode), this.desc);
    }

    public CodeTypeInsn withDesc(String desc) {
        return new CodeTypeInsn(this.opcode, OpValue.of(desc));
    }

    @Override
    public boolean matches(AbstractInsnNode node) {
        if (node.getType() == AbstractInsnNode.TYPE_INSN) {
            TypeInsnNode type = (TypeInsnNode) node;
            return this.opcode.ifPEquals(type.getOpcode()) && this.desc.ifPEquals(type.desc);
        }
        return false;
    }
}
