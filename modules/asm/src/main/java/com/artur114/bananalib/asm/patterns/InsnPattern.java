package com.artur114.bananalib.asm.patterns;

import com.artur114.bananalib.asm.tree.insn.IInsnCode;
import com.artur114.bananalib.asm.util.InsnCodes;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AbstractInsnNode;

import java.util.Arrays;
import java.util.Iterator;

public class InsnPattern implements Iterable<IInsnCode> {
    public static final InsnPattern EMPTY = new InsnPattern(new IInsnCode[0]);
    private final IInsnCode[] pattern;

    private InsnPattern(IInsnCode[] pattern) {
        this.pattern = pattern;
    }

    public int size() {
        return this.pattern.length;
    }

    public boolean isEmpty() {
        return this.pattern.length == 0;
    }

    @Override
    public @NotNull Iterator<IInsnCode> iterator() {
        return Arrays.asList(this.pattern).iterator();
    }

    public static InsnPatBuilder builder() {
        return new InsnPatBuilder();
    }

    public static InsnPattern pattern(int... opcodes) {
        if (opcodes.length == 0) {
            return EMPTY;
        }
        IInsnCode[] pattern = new IInsnCode[opcodes.length];
        for (int i = 0; i != opcodes.length; i++) {
            pattern[i] = InsnCodes.OPCODE.with(opcodes[i]);
        }
        return new InsnPattern(pattern);
    }

    public static InsnPattern pattern(IInsnCode... pattern) {
        if (pattern.length == 0) {
            return EMPTY;
        }

        return new InsnPattern(pattern);
    }

    public static InsnPattern merge(InsnPattern pattern, InsnPattern after) {
        IInsnCode[] p = new IInsnCode[pattern.size() + after.size()];
        System.arraycopy(pattern.pattern, 0, p, 0, pattern.size());
        System.arraycopy(after.pattern, 0, p, pattern.size(), after.size());
        return new InsnPattern(p);
    }
}
