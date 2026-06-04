package com.artur114.bananalib.asm.tree;

import com.artur114.bananalib.asm.BananaASM;
import com.artur114.bananalib.asm.patterns.InsnPattern;
import com.artur114.bananalib.asm.tree.insn.IInsnCode;
import com.artur114.bananalib.asm.util.InsnInterval;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;

import java.util.*;

public class InsnListAdv extends InsnList {
    public void replaceAll(InsnPattern pattern, InsnList replacement) {
        this.findPattern(pattern).forEach((area) -> this.replace(area, replacement));
    }

    public void replaceFirst(InsnPattern pattern, InsnList replacement) {
        this.findPattern(pattern, 0).ifPresent((area) -> this.replace(area, replacement));
    }

    public void replace(InsnInterval area, InsnList replacement) {
        if (area.parent() != this) {
            return;
        }

        List<AbstractInsnNode> remove = area.collectNodes();
        AbstractInsnNode insertNode = area.start().getPrevious();
        remove.forEach(this::remove);

        if (insertNode == null) {
            this.insert(replacement);
        } else {
            this.insert(insertNode, replacement);
        }
    }

    public Optional<InsnInterval> findPattern(InsnPattern pattern, int patternIndex) {
        Iterator<AbstractInsnNode> iterator = this.iterator();
        int currentId = 0;

        if (pattern.isEmpty()) {
            return Optional.empty();
        }

        while (iterator.hasNext()) {
            AbstractInsnNode node = this.skipDummyNodes(iterator);
            if (node == null) break;
            boolean flag = true;
            AbstractInsnNode next = node;
            for (IInsnCode code : pattern) {
                if (next != null) {
                    next = this.skipDummyNodes(next);
                }
                if (next == null || !code.matches(next)) {
                    flag = false;
                    break;
                }
                next = next.getNext();
            }

            if (flag) {
                if (patternIndex == currentId) {
                    return Optional.of(new InsnInterval(this, node, next));
                }
                currentId++;
            }
        }

        return Optional.empty();
    }

    public List<InsnInterval> findPattern(InsnPattern pattern) {
        Iterator<AbstractInsnNode> iterator = this.iterator();
        List<InsnInterval> ret = new ArrayList<>();

        if (pattern.isEmpty()) {
            return ret;
        }

        while (iterator.hasNext()) {
            AbstractInsnNode node = this.skipDummyNodes(iterator);
            if (node == null) break;
            boolean flag = true;
            AbstractInsnNode next = node;
            for (IInsnCode code : pattern) {
                if (next != null) {
                    next = this.skipDummyNodes(next);
                }
                if (next == null || !code.matches(next)) {
                    flag = false;
                    break;
                }
                next = next.getNext();
            }

            if (flag) {
                ret.add(new InsnInterval(this, node, next));
            }
        }

        return ret;
    }

    public InsnListAdv copy() {
        return BananaASM.copyList(this);
    }

    private AbstractInsnNode skipDummyNodes(Iterator<AbstractInsnNode> iterator) {
        AbstractInsnNode ret = iterator.next();
        while (iterator.hasNext() && ret.getOpcode() == -1) {
            ret = iterator.next();
        }
        if (!iterator.hasNext() && ret.getOpcode() == -1) {
            return null;
        }
        return ret;
    }

    private AbstractInsnNode skipDummyNodes(AbstractInsnNode node) {
        AbstractInsnNode ret = node;
        while (ret != null && ret.getOpcode() == -1) {
            ret = ret.getNext();
        }
        return ret;
    }
}
