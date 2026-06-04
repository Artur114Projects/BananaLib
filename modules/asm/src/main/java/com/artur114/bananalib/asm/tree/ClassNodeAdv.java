package com.artur114.bananalib.asm.tree;

import com.artur114.bananalib.asm.patterns.MethodPattern;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassNodeAdv extends ClassNode {
    public List<MethodNodeAdv> methods = new ArrayList<>();

    public ClassNodeAdv() {
        this(Opcodes.ASM6);
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
            if (pattern.matches(node)) {
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
            if (pattern.matches(node)) {
                return Optional.of(node);
            }
        }
        return Optional.empty();
    }

    public Optional<MethodNodeAdv> findMethod(String name) {
        return this.findMethod(MethodPattern.fromName(name));
    }
}
