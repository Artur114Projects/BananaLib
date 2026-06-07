package com.artur114.bananalib.mc.register.interf;

import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IHasCraftRegister {
    void registerCraftRecipes(List<ResourceLocation> names);
}
