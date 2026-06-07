package com.artur114.bananalib.asm.patterns;

import com.artur114.bananalib.asm.internal.OpValue;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodPattern {
    public static final MethodPattern INSTANCE = new MethodPattern();
    private final OpValue<Remapper> remapper;
    private final OpValue<String> name;
    private final OpValue<String> desc;

    public MethodPattern() {
        this.remapper = OpValue.empty();
        this.name = OpValue.empty();
        this.desc = OpValue.empty();
    }

    private MethodPattern(OpValue<Remapper> remapper, OpValue<String> name, OpValue<String> desc) {
        this.remapper = remapper;
        this.name = name;
        this.desc = desc;
    }

    public MethodPattern with(String name, String desc) {
        return new MethodPattern(this.remapper, OpValue.of(name), OpValue.of(desc));
    }

    public MethodPattern withRemapper(Remapper remapper) {
        return new MethodPattern(OpValue.of(remapper), this.name, this.desc);
    }

    public MethodPattern withName(String name) {
        return new MethodPattern(this.remapper, OpValue.of(name), this.desc);
    }

    public MethodPattern withDesc(String desc) {
        return new MethodPattern(this.remapper, this.name, OpValue.of(desc));
    }

    public boolean matches(ClassNode clazz, MethodNode method) {
        if (this.remapper.isPresent()) {
            Remapper remapper = this.remapper.get();
            return this.name.ifPEquals(remapper.mapMethodName(clazz.name, method.name, method.desc)) && this.desc.ifPEquals(remapper.mapDesc(method.desc));
        } else {
            return this.name.ifPEquals(method.name) && this.desc.ifPEquals(method.desc);
        }
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
