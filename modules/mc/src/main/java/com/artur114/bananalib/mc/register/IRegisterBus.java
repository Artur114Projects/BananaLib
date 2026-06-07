package com.artur114.bananalib.mc.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public interface IRegisterBus {
    void putNetWrapper(SimpleNetworkWrapper wrapper);
    void subscribe();
    void preInit();

    <T extends SoundEvent> T registerSound(T sound);
    <T extends Block> T registerBlock(T block);
    <T extends Item> T registerItem(T item);
    <T> T register(T object);
}
