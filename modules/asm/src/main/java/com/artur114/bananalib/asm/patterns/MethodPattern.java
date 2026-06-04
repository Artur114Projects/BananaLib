package com.artur114.bananalib.asm.patterns;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.tree.MethodNode;

public class MethodPattern {
    public static final MethodPattern INSTANCE = new MethodPattern();
    private final OpValue<String> name;
    private final OpValue<String> desc;

    public MethodPattern() {
        this.name = OpValue.empty();
        this.desc = OpValue.empty();
    }

    public MethodPattern(OpValue<String> name, OpValue<String> desc) {
        this.name = name;
        this.desc = desc;
    }

    public MethodPattern with(String name, String desc) {
        return new MethodPattern(OpValue.of(name), OpValue.of(desc));
    }

    public MethodPattern withName(String name) {
        return new MethodPattern(OpValue.of(name), this.desc);
    }

    public MethodPattern withDesc(String desc) {
        return new MethodPattern(this.name, OpValue.of(desc));
    }

    public boolean matches(MethodNode node) {
        return this.name.ifPEquals(node.name) && this.desc.ifPEquals(node.desc);
    }

    public static MethodPattern from(String name, String desc) {
        return INSTANCE.with(name, desc);
    }

    public static MethodPattern fromName(String name) {
        return INSTANCE.withName(name);
    }

    public static MethodPattern fromDesc(String desc) {
        return INSTANCE.withDesc(desc);
    }
}
