package com.artur114.bananalib.mc.base;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public interface IBiomeData {
    BiomeManager.BiomeType biomeType();
    BiomeDictionary.Type[] types();
    int weight();
}
