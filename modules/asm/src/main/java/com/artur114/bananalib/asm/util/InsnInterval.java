package com.artur114.bananalib.asm.util;

import com.artur114.bananalib.asm.internal.InternalASMUtil;
import com.artur114.bananalib.asm.patterns.InsnPattern;
import com.artur114.bananalib.asm.tree.InsnListAdv;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;

import java.util.*;

public class InsnInterval implements Iterable<AbstractInsnNode> {
    private final AbstractInsnNode from;
    private final AbstractInsnNode to;
    private final InsnListAdv parent;
    private final int size;

    public InsnInterval(InsnListAdv parent, AbstractInsnNode from, AbstractInsnNode to) {
        InternalASMUtil.requireNotNull(parent, from, to);
        this.parent = parent;
        this.from = from;
        this.to = to;

        this.validate();
        this.size = this.computeSize();
    }

    public int size() {
        return this.size;
    }

    public InsnListAdv parent() {
        return this.parent;
    }

    public AbstractInsnNode start() {
        return this.from;
    }

    public AbstractInsnNode end() {
        return this.to;
    }

    public Optional<InsnInterval> findPattern(InsnPattern pattern, int patternIndex) {
        List<InsnInterval> ret = InternalASMUtil.findPatternInList(this.parent, this.iterator(), pattern, patternIndex);
        if (!ret.isEmpty()) return Optional.of(ret.get(0));
        return Optional.empty();
    }

    public List<InsnInterval> findPattern(InsnPattern pattern) {
        return InternalASMUtil.findPatternInList(this.parent, this.iterator(), pattern, -1);
    }

    public List<AbstractInsnNode> collectNodes() {
        List<AbstractInsnNode> ret = new ArrayList<>();
        AbstractInsnNode node = this.from;
        while (node != this.to) {
            ret.add(node);
            node = node.getNext();
        }
        ret.add(this.to);
        return ret;
    }

    public InsnListAdv buildNewList() {
        Map<LabelNode, LabelNode> clonedLabels = new HashMap<>();
        AbstractInsnNode next = this.from;
        InsnListAdv ret = new InsnListAdv();
        while (next != null) {
            ret.add(next.clone(clonedLabels));
            if (next == this.to) {
                return ret;
            }
            next = next.getNext();
        }
        throw new IllegalStateException("HOW??????");
    }

    private int computeSize() {
        AbstractInsnNode next = this.from;
        int ret = 0;
        while (next != null) {
            ret++;
            if (next == this.to) return ret;
            next = next.getNext();
        }
        throw new IllegalStateException();
    }

    private void validate() {
        if (!this.parent.contains(this.from) || !this.parent.contains(this.to)) {
            throw new IllegalStateException();
        }
    }

    @Override
    public @NotNull Iterator<AbstractInsnNode> iterator() {
        return new InsnIntervalIterator(this);
    }

    private static final class InsnIntervalIterator implements Iterator<AbstractInsnNode> {
        private final AbstractInsnNode bound;
        private AbstractInsnNode nextInsn;

        private InsnIntervalIterator(InsnInterval parent) {
            this.bound = parent.to.getNext();
            this.nextInsn = parent.from;
        }

        public boolean hasNext() {
            return this.nextInsn != null && this.nextInsn != this.bound;
        }

        public AbstractInsnNode next() {
            if (this.nextInsn == null || this.nextInsn == this.bound) {
                throw new NoSuchElementException();
            }
            AbstractInsnNode result = this.nextInsn;
            this.nextInsn = result.getNext();
            return result;
        }
    }
}
