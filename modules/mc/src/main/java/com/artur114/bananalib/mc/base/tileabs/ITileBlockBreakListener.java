package com.artur114.bananalib.mc.base.tileabs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ITileBlockBreakListener {
    void breakBlock(World worldIn, BlockPos pos, IBlockState state);
}
