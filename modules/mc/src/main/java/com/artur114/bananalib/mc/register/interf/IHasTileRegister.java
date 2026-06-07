package com.artur114.bananalib.mc.register.interf;

import com.artur114.bananalib.mc.register.TileRegData;
import net.minecraft.tileentity.TileEntity;

public interface IHasTileRegister {
    void registerTileEntity(TileRegData data);
}
