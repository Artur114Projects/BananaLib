package com.artur114.bananalib.mc.registry.interf;

import com.artur114.bananalib.mc.registry.data.TESRRegData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHasTileSR extends IHasTileEntity {
    @SideOnly(Side.CLIENT)
    TESRRegData registerTSRData();
}
