package com.artur114.bananalib.mc.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public interface IRegisterBuss {
    void putNetWrapper(SimpleNetworkWrapper wrapper);
    void subscribe();
    void preInit();

    void registerSound(SoundEvent sound);
    void registerBlock(Block block);
    void registerItem(Item item);
    void register(Object object);
}
