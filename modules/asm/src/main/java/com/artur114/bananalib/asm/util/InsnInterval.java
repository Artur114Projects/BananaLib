package com.artur114.bananalib.asm.util;

import com.artur114.bananalib.asm.tree.InsnListAdv;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsnInterval {
    private final AbstractInsnNode from;
    private final AbstractInsnNode to;
    private final InsnList parent;

    public InsnInterval(InsnList parent, AbstractInsnNode from, AbstractInsnNode to) {
        this.parent = parent;
        this.from = from;
        this.to = to;
    }

    public InsnList parent() {
        return this.parent;
    }

    public AbstractInsnNode start() {
        return this.from;
    }

    public AbstractInsnNode end() {
        return this.to;
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
        AbstractInsnNode next = from;
        InsnListAdv ret = new InsnListAdv();
        while (next != null) {
            ret.add(next.clone(clonedLabels));
            if (next == to) {
                return ret;
            }
            next = next.getNext();
        }
        return null;
    }
}
