package com.artur114.bananalib.mc.register;

import net.minecraft.item.Item;

public class ModelRegData {
    private final Item item;
    private final int meta;
    private final String id;

    public ModelRegData(Item item, int meta, String id) {
        this.item = item;
        this.meta = meta;
        this.id = id;
    }

    public Item item() {
        return item;
    }

    public int meta() {
        return meta;
    }

    public String id() {
        return id;
    }

    public static ModelRegData of(Item item, int meta, String id) {
        return new ModelRegData(item, meta, id);
    }
}
