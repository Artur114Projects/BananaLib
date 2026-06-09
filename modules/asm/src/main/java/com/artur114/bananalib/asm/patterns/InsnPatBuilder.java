package com.artur114.bananalib.asm.patterns;

import com.artur114.bananalib.asm.tree.insn.IInsnCode;
import com.artur114.bananalib.asm.util.InsnCodes;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Remapper;

import java.util.*;

public class InsnPatBuilder {
    private final ArrayDeque<IInsnCode> buildHeap = new ArrayDeque<>();
    private Remapper mapping = null;

    protected InsnPatBuilder() {}

    public InsnPatBuilder mapping(Remapper remapper) {
        this.mapping = remapper;
        return this;
    }

    public InsnPatBuilder then(int code) {
        this.buildHeap.addLast(InsnCodes.OPCODE.with(code));
        return this;
    }

    public InsnPatBuilder then(IInsnCode code) {
        this.buildHeap.addLast(code);
        return this;
    }

    public InsnPatBuilder then(IInsnCode... codes) {
        this.buildHeap.addAll(Arrays.asList(codes));
        return this;
    }

    public InsnPatBuilder then(Collection<IInsnCode> codes) {
        this.buildHeap.addAll(codes);
        return this;
    }

    public InsnPatBuilder thenInsn(int opcode) {
        return this.then(InsnCodes.INSN.with(opcode));
    }

    public InsnPatBuilder thenAnyInsn() {
        return this.then(InsnCodes.ANY_INSN);
    }

    public InsnPatBuilder thenIntInsn(int opcode, int operand) {
        return this.then(InsnCodes.INT_INSN.with(opcode, operand));
    }

    public InsnPatBuilder thenVarInsn(int opcode, int var) {
        return this.then(InsnCodes.VAR_INSN.with(opcode, var));
    }

    public InsnPatBuilder thenTypeInsn(int opcode, String desc) {
        if (this.mapping == null) {
            return this.then(InsnCodes.TYPE_INSN.with(opcode, desc));
        } else {
            return this.then(InsnCodes.TYPE_INSN.with(opcode, this.mapping.mapType(desc)));
        }
    }

    public InsnPatBuilder thenFieldInsn(int opcode, String owner, String name, String desc) {
        if (this.mapping == null) {
            return this.then(InsnCodes.FIELD_INSN.with(opcode, owner, name, desc));
        } else {
            return this.then(InsnCodes.FIELD_INSN.with(opcode,
                this.mapping.mapType(owner),
                this.mapping.mapFieldName(owner, name, desc),
                this.mapping.mapDesc(desc)
            ));
        }
    }

    public InsnPatBuilder thenMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (this.mapping == null) {
            return this.then(InsnCodes.METHOD_INSN.with(opcode, owner, name, desc, itf));
        } else {
            return this.then(InsnCodes.METHOD_INSN.with(opcode,
                this.mapping.mapType(owner),
                this.mapping.mapMethodName(owner, name, desc),
                this.mapping.mapDesc(desc), itf
            ));
        }
    }

    public InsnPatBuilder thenInvokeDynamicInsn(int opcode, String name, String desc, Handle bsm, Object[] bsmArgs) {
        if (this.mapping == null) {
            return this.then(InsnCodes.INVOKE_DYNAMIC_INSN.with(opcode, name, desc, bsm, bsmArgs));
        } else {
            return this.then(InsnCodes.INVOKE_DYNAMIC_INSN.with(opcode,
                this.mapping.mapInvokeDynamicMethodName(name, desc),
                this.mapping.mapDesc(desc), bsm, bsmArgs
            ));
        }
    }

    public InsnPatBuilder thenJumpInsn(int opcode, Label label) {
        return this.then(InsnCodes.JUMP_INSN.with(opcode, label));
    }

    public InsnPatBuilder thenLdcInsn(Object cst) {
        if (this.mapping != null && cst instanceof Type) {
            return this.then(InsnCodes.LDC_INSN.with(this.mapping.mapValue(cst)));
        } else {
            return this.then(InsnCodes.LDC_INSN.with(cst));
        }
    }

    public InsnPattern build() {
        InsnPattern ret = InsnPattern.pattern(this.buildHeap.toArray(new IInsnCode[0]));
        this.buildHeap.clear();
        this.mapping = null;
        return ret;
    }
}
