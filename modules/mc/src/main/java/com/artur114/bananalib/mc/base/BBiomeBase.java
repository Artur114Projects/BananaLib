package com.artur114.bananalib.mc.base;

import com.artur114.bananalib.mc.registry.data.BiomeRegData;
import com.artur114.bananalib.mc.registry.interf.IHasBiome;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

import java.util.Collections;
import java.util.List;

public class BBiomeBase extends Biome implements IHasBiome {
    protected final BiomeManager.BiomeType biomeType;
    protected final BiomeDictionary.Type[] types;
    protected final int weight;

    public BBiomeBase(ResourceLocation name, BiomeProperties properties, IBiomeData data) {
        super(properties);
        this.setRegistryName(name);
        this.biomeType = data.biomeType();
        this.weight = data.weight();
        this.types = data.types();
    }

    @Override
    public List<BiomeRegData> registerBiomesData() {
        if (this.weight > 0) {
            return Collections.singletonList(BiomeRegData.of(new BiomeManager.BiomeEntry(this, this.weight), this, this.biomeType, this.types));
        }
        return Collections.singletonList(BiomeRegData.noGenerable(this.biomeType, this, this.types));
    }
}
