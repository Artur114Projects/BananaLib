package com.artur114.bananalib.mc.register.data;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileRegData {
    private final Class<? extends TileEntity> tile;
    private final ResourceLocation key;

    public TileRegData(Class<? extends TileEntity> tile, ResourceLocation key) {
        this.tile = tile;
        this.key = key;
    }

    public Class<? extends TileEntity> tile() {
        return tile;
    }

    public ResourceLocation key() {
        return key;
    }

    public static TileRegData of(Class<? extends TileEntity> tile, ResourceLocation key) {
        return new TileRegData(tile, key);
    }
}
