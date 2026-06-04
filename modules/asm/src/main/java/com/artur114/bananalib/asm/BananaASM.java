package com.artur114.bananalib.asm;

import com.artur114.bananalib.asm.tree.ClassNodeAdv;
import com.artur114.bananalib.asm.tree.InsnListAdv;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class BananaASM {
    public static ClassNodeAdv createClassNode(byte[] basicClass) {
        return createClassNode(basicClass, ClassReader.SKIP_FRAMES);
    }

    public static ClassNodeAdv createClassNode(byte[] basicClass, int flags) {
        ClassReader classReader = new ClassReader(basicClass);
        ClassNodeAdv clazz = new ClassNodeAdv();
        classReader.accept(clazz, flags);
        return clazz;
    }

    public static byte[] bakeBytecode(ClassNode cn) {
        return bakeBytecode(cn, ClassWriter.COMPUTE_FRAMES);
    }

    public static byte[] bakeBytecode(ClassNode cn, int flags) {
        ClassWriter writer = new ClassWriter(flags);
        cn.accept(writer);
        return writer.toByteArray();
    }

    public static InsnListAdv copyList(InsnListAdv list) {
        Map<LabelNode, LabelNode> clonedLabels = new HashMap<>();
        Iterator<AbstractInsnNode> iterator = list.iterator();
        InsnListAdv ret = new InsnListAdv();
        while (iterator.hasNext()) {
            ret.add(iterator.next().clone(clonedLabels));
        }
        return ret;
    }

    public static InsnList copyList(InsnList list) {
        Map<LabelNode, LabelNode> clonedLabels = new HashMap<>();
        Iterator<AbstractInsnNode> iterator = list.iterator();
        InsnList ret = new InsnList();
        while (iterator.hasNext()) {
            ret.add(iterator.next().clone(clonedLabels));
        }
        return ret;
    }
}
