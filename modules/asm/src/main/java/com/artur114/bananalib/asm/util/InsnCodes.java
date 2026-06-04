package com.artur114.bananalib.asm.util;

import com.artur114.bananalib.asm.tree.insn.*;
import org.objectweb.asm.Opcodes;

public interface InsnCodes extends Opcodes {
    CodeInsn INSN = new CodeInsn();
    CodeIntInsn INT_INSN = new CodeIntInsn();
    CodeVarInsn VAR_INSN = new CodeVarInsn();
    CodeTypeInsn TYPE_INSN = new CodeTypeInsn();
    CodeFieldInsn FIELD_INSN = new CodeFieldInsn();
    CodeMethodInsn METHOD_INSN = new CodeMethodInsn();
    CodeInvokeDynamicInsn INVOKE_DYNAMIC_INSN = new CodeInvokeDynamicInsn();
    CodeJumpInsn JUMP_INSN = new CodeJumpInsn();
    CodeLdcInsn LDC_INSN = new CodeLdcInsn();
    CodeOpcode OPCODE = new CodeOpcode();
    IInsnCode ANY_INSN = (v) -> true;
}
