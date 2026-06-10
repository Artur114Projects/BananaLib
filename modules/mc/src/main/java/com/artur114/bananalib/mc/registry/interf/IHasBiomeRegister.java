package com.artur114.bananalib.mc.registry.interf;

import com.artur114.bananalib.mc.registry.data.BiomeRegData;

import java.util.List;

public interface IHasBiomeRegister {
    void registerBiomes(List<BiomeRegData> data);
}