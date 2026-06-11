package com.artur114.bananalib.asm.tree;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.*;

public class MethodNodeAdv extends MethodNode {
    public InsnListAdv instructions;

    public MethodNodeAdv() {
        super.instructions = this.instructions = new InsnListAdv();
    }

    public MethodNodeAdv(int api) {
        super(api);
        super.instructions = this.instructions = new InsnListAdv();
    }

    public MethodNodeAdv(int access, String name, String descriptor, String signature, String[] exceptions) {
        super(Opcodes.ASM5, access, name, descriptor, signature, exceptions);
        super.instructions = this.instructions = new InsnListAdv();
    }

    public MethodNodeAdv(int api, int access, String name, String descriptor, String signature, String[] exceptions) {
        super(api, access, name, descriptor, signature, exceptions);
        super.instructions = this.instructions = new InsnListAdv();
    }

    public void remap(Remapper remapper) {
        for (AbstractInsnNode insn : this.instructions) {
            if (insn instanceof FieldInsnNode) {
                FieldInsnNode f = (FieldInsnNode) insn;
                f.owner = remapper.mapType(f.owner);
                f.name = remapper.mapFieldName(f.owner, f.name, f.desc);
                f.desc = remapper.mapDesc(f.desc);
            } else if (insn instanceof MethodInsnNode) {
                MethodInsnNode m = (MethodInsnNode) insn;
                m.owner = remapper.mapType(m.owner);
                m.name = remapper.mapMethodName(m.owner, m.name, m.desc);
                m.desc = remapper.mapMethodDesc(m.desc);
            } else if (insn instanceof TypeInsnNode) {
                TypeInsnNode t = (TypeInsnNode) insn;
                t.desc = remapper.mapType(t.desc);
            } else if (insn instanceof InvokeDynamicInsnNode) {
                InvokeDynamicInsnNode i = (InvokeDynamicInsnNode) insn;
                i.name = remapper.mapInvokeDynamicMethodName(i.name, i.desc);
                i.desc = remapper.mapDesc(i.desc);
            } else if (insn instanceof LdcInsnNode) {
                LdcInsnNode l = (LdcInsnNode) insn;
                if (l.cst instanceof Type) {
                    l.cst = remapper.mapValue(l.cst);
                }
            } else if (insn instanceof MultiANewArrayInsnNode) {
                MultiANewArrayInsnNode m = (MultiANewArrayInsnNode) insn;
                m.desc = remapper.mapDesc(m.desc);
            }
        }
    }
}
