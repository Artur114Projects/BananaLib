package com.artur114.bananalib.mc.registry.interf;

import com.artur114.bananalib.mc.registry.data.TESRRegData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHasTileSRRegister {
    @SideOnly(Side.CLIENT)
    void registerTSR(TESRRegData data);
}
