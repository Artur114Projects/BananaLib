package com.artur114.bananalib.mc.base.tileabs;

import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public interface ITileMultiBBProvider {
    List<AxisAlignedBB> boundingBoxes();
}
