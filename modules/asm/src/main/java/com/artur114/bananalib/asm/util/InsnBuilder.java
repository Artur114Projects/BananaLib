package com.artur114.bananalib.asm.util;

import com.artur114.bananalib.asm.patterns.InsnPatBuilder;
import com.artur114.bananalib.asm.tree.InsnListAdv;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.*;

import java.util.Collection;
import java.util.function.Consumer;

public class InsnBuilder implements InsnCodes {
    private InsnListAdv buildHeap = new InsnListAdv();
    private Remapper mapping = null;

    public InsnBuilder mapping(Remapper remapper) {
        this.mapping = remapper;
        return this;
    }

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
        if (this.mapping == null) {
            return this.then(new TypeInsnNode(opcode, desc));
        } else {
            return this.then(new TypeInsnNode(opcode, this.mapping.mapType(desc)));
        }
    }

    public InsnBuilder fieldInsn(int opcode, String owner, String name, String desc) {
        if (this.mapping == null) {
            return this.then(new FieldInsnNode(opcode, owner, name, desc));
        } else {
            return this.then(new FieldInsnNode(opcode,
                this.mapping.mapType(owner),
                this.mapping.mapFieldName(owner, name, desc),
                this.mapping.mapDesc(desc)
            ));
        }
    }

    public InsnBuilder methodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (this.mapping == null) {
            return this.then(new MethodInsnNode(opcode, owner, name, desc, itf));
        } else {
            return this.then(new MethodInsnNode(opcode,
                this.mapping.mapType(owner),
                this.mapping.mapMethodName(owner, name, desc),
                this.mapping.mapDesc(desc), itf
            ));
        }
    }

    public InsnBuilder invokeDynamicInsn(String name, String desc, Handle bsm, Object[] bsmArgs) {
        if (this.mapping == null) {
            return this.then(new InvokeDynamicInsnNode(name, desc, bsm, bsmArgs));
        } else {
            return this.then(new InvokeDynamicInsnNode(
                this.mapping.mapInvokeDynamicMethodName(name, desc),
                this.mapping.mapDesc(desc), bsm, bsmArgs
            ));
        }
    }

    public InsnBuilder jumpInsn(int opcode, LabelNode label) {
        return this.then(new JumpInsnNode(opcode, label));
    }

    public InsnBuilder ldcInsn(Object cst) {
        if (this.mapping != null && cst instanceof Type) {
            return this.then(new LdcInsnNode(this.mapping.mapValue(cst)));
        } else {
            return this.then(new LdcInsnNode(cst));
        }
    }

    public InsnBuilder invokeStatic(String owner, String name, String desc) {
        return this.methodInsn(INVOKESTATIC, owner, name, desc, false);
    }

    public InsnBuilder invokeVirtual(String owner, String name, String desc, boolean itf) {
        return this.methodInsn(INVOKEVIRTUAL, owner, name, desc, itf);
    }

    public InsnBuilder invokeSpecial(String owner, String name, String desc) {
        return this.methodInsn(INVOKESPECIAL, owner, name, desc, false);
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

    public InsnBuilder thenIf(Consumer<InsnBuilder> body) {
        LabelNode node = new LabelNode();
        this.then(new JumpInsnNode(IFEQ, node));
        body.accept(this);
        this.then(node);
        return this;
    }

    public InsnBuilder thenIfNo(Consumer<InsnBuilder> body) {
        LabelNode node = new LabelNode();
        this.then(new JumpInsnNode(IFNE, node));
        body.accept(this);
        this.then(node);
        return this;
    }

    public InsnBuilder loadVars(String... vars) {
        for (String v : vars) {
            String[] vs = v.split(":");
            if (vs.length != 2 || vs[0].length() != 1) {
                throw new IllegalArgumentException("Invalid var string! string:" + v);
            } this.then(this.loadVar(vs[0].charAt(0), Integer.parseInt(vs[1])));
        }
        return this;
    }

    public InsnListAdv build() {
        InsnListAdv ret = this.buildHeap;
        this.buildHeap = new InsnListAdv();
        this.mapping = null;
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
}
