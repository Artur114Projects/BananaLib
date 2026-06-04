package com.artur114.bananalib.asm.util;

import com.artur114.bananalib.asm.tree.InsnListAdv;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class InsnBuilder implements InsnCodes {
    private InsnListAdv buildHeap = new InsnListAdv();

    public InsnBuilder then(AbstractInsnNode node) {
        this.buildHeap.add(node);
        return this;
    }

    public InsnBuilder then(AbstractInsnNode... nodes) {
        for (AbstractInsnNode node : nodes) this.buildHeap.add(node);
        return this;
    }

    public InsnBuilder then(Collection<AbstractInsnNode> nodes) {
        for (AbstractInsnNode node : nodes) this.buildHeap.add(node);
        return this;
    }

    public InsnBuilder then(InsnList list) {
        this.buildHeap.add(list);
        return this;
    }

    public InsnBuilder insn(int opcode) {
        return this.then(new InsnNode(opcode));
    }

    public InsnBuilder intInsn(int opcode, int operand) {
        return this.then(new IntInsnNode(opcode, operand));
    }

    public InsnBuilder varInsn(int opcode, int var) {
        return this.then(new VarInsnNode(opcode, var));
    }

    public InsnBuilder typeInsn(int opcode, String desc) {
        return this.then(new TypeInsnNode(opcode, desc));
    }

    public InsnBuilder fieldInsn(int opcode, String owner, String name, String desc) {
        return this.then(new FieldInsnNode(opcode, owner, name, desc));
    }

    public InsnBuilder methodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        return this.then(new MethodInsnNode(opcode, owner, name, desc, itf));
    }

    public InsnBuilder invokeDynamicInsn(String name, String desc, Handle bsm, Object[] bsmArgs) {
        return this.then(new InvokeDynamicInsnNode(name, desc, bsm, bsmArgs));
    }

    public InsnBuilder jumpInsn(int opcode, LabelNode label) {
        return this.then(new JumpInsnNode(opcode, label));
    }

    public InsnBuilder ldcInsn(Object cst) {
        return this.then(new LdcInsnNode(cst));
    }

    public InsnBuilder invokeStatic(String owner, String name, String desc) {
        return this.then(new MethodInsnNode(INVOKESTATIC, owner, name, desc, false));
    }

    public InsnBuilder invokeVirtual(String owner, String name, String desc, boolean itf) {
        return this.then(new MethodInsnNode(INVOKEVIRTUAL, owner, name, desc, itf));
    }

    public InsnBuilder invokeSpecial(String owner, String name, String desc) {
        return this.then(new MethodInsnNode(INVOKESPECIAL, owner, name, desc, false));
    }

    public InsnBuilder ifFalseReturn(int returnOpcode) {
        LabelNode node = new LabelNode();
        this.then(new JumpInsnNode(IFNE, node));
        this.insn(returnOpcode);
        this.then(node);
        return this;
    }

    public InsnBuilder ifTrueReturn(int returnOpcode) {
        LabelNode node = new LabelNode();
        this.then(new JumpInsnNode(IFEQ, node));
        this.insn(returnOpcode);
        this.then(node);
        return this;
    }

    public InsnBuilder loadVars(String... vars) {
        for (String v : vars) {
            String[] vs = v.split("\\|");
            if (vs.length != 2 || vs[0].length() != 1 || !this.canFormat(vs[1])) {
                throw new IllegalArgumentException("Invalid var string! string:" + v);
            }
            this.then(this.loadVar(vs[0].charAt(0), Integer.parseInt(vs[1])));
        }
        return this;
    }

    public InsnListAdv build() {
        InsnListAdv ret = this.buildHeap;
        this.buildHeap = new InsnListAdv();
        return ret;
    }

    private VarInsnNode loadVar(char id, int index) {
        switch (id) {
            case 'I':
                return new VarInsnNode(ILOAD, index);
            case 'L':
                return new VarInsnNode(LLOAD, index);
            case 'F':
                return new VarInsnNode(FLOAD, index);
            case 'D':
                return new VarInsnNode(DLOAD, index);
            case 'A':
                return new VarInsnNode(ALOAD, index);
            default:
                throw new IllegalArgumentException("Invalid id! id:" + id);
        }
    }

    private boolean canFormat(String string) {
        try {
            Integer.parseInt(string);return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
