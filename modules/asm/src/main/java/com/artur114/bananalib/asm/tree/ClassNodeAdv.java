package com.artur114.bananalib.asm.tree;

import com.artur114.bananalib.asm.patterns.MethodPattern;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassNodeAdv extends ClassNode {
    public List<MethodNodeAdv> methods = new ArrayList<>();

    public ClassNodeAdv() {
        this(Opcodes.ASM5);
    }

    @SuppressWarnings("unchecked")
    public ClassNodeAdv(int api) {
        super(api);
        super.methods = (List<MethodNode>) ((List<?>) this.methods);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodNodeAdv method = new MethodNodeAdv(access, name, descriptor, signature, exceptions);
        this.methods.add(method);
        return method;
    }

    public List<MethodNodeAdv> findMethods(MethodPattern pattern) {
        List<MethodNodeAdv> ret = new ArrayList<>();
        for (MethodNodeAdv node : this.methods) {
            if (pattern.matches(this, node)) {
                ret.add(node);
            }
        }
        return ret;
    }

    public List<MethodNodeAdv> findMethods(String name) {
        return this.findMethods(MethodPattern.fromName(name));
    }

    public Optional<MethodNodeAdv> findMethod(MethodPattern pattern) {
        for (MethodNodeAdv node : this.methods) {
            if (pattern.matches(this, node)) {
                return Optional.of(node);
            }
        }
        return Optional.empty();
    }

    public Optional<MethodNodeAdv> findMethod(String name) {
        return this.findMethod(MethodPattern.fromName(name));
    }

    public void remap(Remapper remapper) {
        this.name = remapper.mapType(this.name);
        for (MethodNodeAdv method : this.methods) {
            method.name = remapper.mapMethodName(this.name, method.name, method.desc);
            method.desc = remapper.mapDesc(method.desc);
        }
        for (FieldNode field : this.fields) {
            field.name = remapper.mapFieldName(this.name, field.name, field.desc);
            field.desc = remapper.mapDesc(field.desc);
        }
    }
}
