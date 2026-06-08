package com.artur114.bananalib.mc.register.data;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TESRRegData {
    private final TileEntitySpecialRenderer<?> specialRenderer;
    private final Class<?> tileEntityClass;

    public <T extends TileEntity> TESRRegData(Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer) {
        this.specialRenderer = specialRenderer;
        this.tileEntityClass = tileEntityClass;
    }

    public TileEntitySpecialRenderer<?> specialRenderer() {
        return specialRenderer;
    }

    public Class<?> tileEntityClass() {
        return tileEntityClass;
    }

    public static <T extends TileEntity> TESRRegData of(Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer) {
        return new TESRRegData(tileEntityClass, specialRenderer);
    }
}
