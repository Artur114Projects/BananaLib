package com.artur114.bananalib.asm.internal;

import com.artur114.bananalib.asm.patterns.InsnPattern;
import com.artur114.bananalib.asm.tree.InsnListAdv;
import com.artur114.bananalib.asm.tree.insn.IInsnCode;
import com.artur114.bananalib.asm.util.InsnInterval;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LabelNode;

import java.util.*;

public class InternalASMUtil {
    public static InsnListAdv copyList(InsnListAdv list) {
        Map<LabelNode, LabelNode> clonedLabels = new HashMap<>();
        Iterator<AbstractInsnNode> iterator = list.iterator();
        InsnListAdv ret = new InsnListAdv();
        while (iterator.hasNext()) {
            ret.add(iterator.next().clone(clonedLabels));
        }
        return ret;
    }

    public static List<InsnInterval> findPatternInList(InsnListAdv parentList, Iterator<AbstractInsnNode> source, InsnPattern pattern, int patternIndex) {
        List<InsnInterval> ret = new ArrayList<>();
        int currentId = 0;

        if (pattern.isEmpty()) {
            return ret;
        }

        while (source.hasNext()) {
            AbstractInsnNode node = skipDummyNodes(source);
            if (node == null) break;
            boolean flag = true;
            AbstractInsnNode next = node;
            for (IInsnCode code : pattern) {
                if (next != null) {
                    next = skipDummyNodes(next);
                }
                if (next == null || !code.matches(next)) {
                    flag = false;
                    break;
                }
                next = next.getNext();
            }

            if (flag) {
                if (patternIndex != -1) {
                    if (patternIndex == currentId) {
                        ret.add(new InsnInterval(parentList, node, next)); return ret;
                    }
                    currentId++;
                } else {
                    ret.add(new InsnInterval(parentList, node, next));
                }

            }
        }

        return ret;
    }

    public static void requireNotNull(String message, Object... objects) {
        for (Object obj : objects) {
            if (obj == null) throw new NullPointerException(message);
        }
    }

    public static void requireNotNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) throw new NullPointerException();
        }
    }

    private static AbstractInsnNode skipDummyNodes(Iterator<AbstractInsnNode> iterator) {
        AbstractInsnNode ret = iterator.next();
        while (iterator.hasNext() && ret.getOpcode() == -1) {
            ret = iterator.next();
        }
        if (!iterator.hasNext() && ret.getOpcode() == -1) {
            return null;
        }
        return ret;
    }

    private static AbstractInsnNode skipDummyNodes(AbstractInsnNode node) {
        AbstractInsnNode ret = node;
        while (ret != null && ret.getOpcode() == -1) {
            ret = ret.getNext();
        }
        return ret;
    }
}
