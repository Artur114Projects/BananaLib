package com.artur114.bananalib.asm.tree;

import com.artur114.bananalib.asm.BananaASM;
import com.artur114.bananalib.asm.internal.InternalASMUtil;
import com.artur114.bananalib.asm.patterns.InsnPattern;
import com.artur114.bananalib.asm.util.InsnInterval;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;

import java.util.*;

public class InsnListAdv extends InsnList implements Iterable<AbstractInsnNode> {
    public void replaceAll(InsnPattern pattern, InsnList replacement) {
        this.findPattern(pattern).forEach((area) -> this.replace(area, BananaASM.copyList(replacement)));
    }

    public void replaceFirst(InsnPattern pattern, InsnList replacement) {
        this.findPattern(pattern, 0).ifPresent((area) -> this.replace(area, BananaASM.copyList(replacement)));
    }

    public void replace(int patternIndex, InsnPattern pattern, InsnList replacement) {
        this.findPattern(pattern, patternIndex).ifPresent((area) -> this.replace(area, BananaASM.copyList(replacement)));
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
        List<InsnInterval> ret = InternalASMUtil.findPatternInList(this, this.iterator(), pattern, patternIndex);
        if (!ret.isEmpty()) return Optional.of(ret.get(0));
        return Optional.empty();
    }

    public List<InsnInterval> findPattern(InsnPattern pattern) {
        return InternalASMUtil.findPatternInList(this, this.iterator(), pattern, -1);
    }

    public InsnListAdv copy() {
        return InternalASMUtil.copyList(this);
    }

    @Override
    public @NotNull ListIterator<AbstractInsnNode> iterator() {
        return super.iterator();
    }
}
