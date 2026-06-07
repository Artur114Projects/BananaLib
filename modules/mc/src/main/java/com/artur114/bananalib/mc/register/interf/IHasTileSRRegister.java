package com.artur114.bananalib.mc.register.interf;

import com.artur114.bananalib.mc.register.TESRRegData;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public interface IHasTileSRRegister {
    void registerTSR(TESRRegData data);
}
