package com.artur114.bananalib.mc.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public interface IRegisterBus {
    IRegisterBus putNetWrapper(SimpleNetworkWrapper wrapper);
    void subscribe();
    void preInit();
    void init();
    void postInit();

    void registerSound(SoundEvent... sound);
    void scanAndRegister(Class<?>... clazz);
    void registerBlock(Block... block);
    void registerBiome(Biome... biome);
    void registerItem(Item... item);
    void registerAuto(Object... object);
    void register(Object... object);
    <T extends SoundEvent> T registerSound(T sound);
    <T extends Class<?>> T scanAndRegister(T clazz);
    <T extends Biome> T registerBiome(T biome);
    <T extends Block> T registerBlock(T block);
    <T extends Item> T registerItem(T item);
    <T> T registerAuto(T object);
    <T> T register(T object);
}
