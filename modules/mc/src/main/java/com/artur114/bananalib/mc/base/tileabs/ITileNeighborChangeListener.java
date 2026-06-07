package com.artur114.bananalib.mc.base.tileabs;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface ITileNeighborChangeListener {
    void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor);
}
