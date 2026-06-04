package com.artur114.bananalib.asm.tree;

import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

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
        super(access, name, descriptor, signature, exceptions);
        super.instructions = this.instructions = new InsnListAdv();
    }

    public MethodNodeAdv(int api, int access, String name, String descriptor, String signature, String[] exceptions) {
        super(api, access, name, descriptor, signature, exceptions);
        super.instructions = this.instructions = new InsnListAdv();
    }
}
