package com.artur114.bananalib.mc.registry.interf;

import com.artur114.bananalib.mc.registry.IRegisterBus;

public interface IHasMoreRegisters {
    void registerOther(IRegisterBus bus);
}
