package com.artur114.bananalib.mc.registry.interf;

import com.artur114.bananalib.mc.registry.data.ModelRegData;

import java.util.List;

public interface IHasModelRegister {
    void registerModels(List<ModelRegData> models);
}
