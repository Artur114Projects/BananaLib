package com.artur114.bananalib.mc.register.data;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BiomeRegData {
    private final @Nullable BiomeManager.BiomeEntry biomeEntry;
    private final BiomeManager.BiomeType biomeType;
    private final BiomeDictionary.Type[] types;
    private final Biome biome;

    public BiomeRegData(@Nullable BiomeManager.BiomeEntry biomeEntry, @NotNull Biome biome, @NotNull BiomeManager.BiomeType biomeType, @NotNull BiomeDictionary.Type[] types) {
        this.biomeEntry = biomeEntry;
        this.biomeType = biomeType;
        this.types = types;
        this.biome = biome;
    }

    public @Nullable BiomeManager.BiomeEntry biomeEntry() {
        return this.biomeEntry;
    }

    public BiomeManager.BiomeType biomeType() {
        return this.biomeType;
    }

    public BiomeDictionary.Type[] types() {
        return this.types;
    }

    public Biome biome() {
        return this.biome;
    }

    public static BiomeRegData of(@Nullable BiomeManager.BiomeEntry biomeEntry, @NotNull Biome biome, @NotNull BiomeManager.BiomeType biomeType, @NotNull BiomeDictionary.Type[] types) {
        return new BiomeRegData(biomeEntry, biome, biomeType, types);
    }

    public static BiomeRegData noGenerable(@NotNull BiomeManager.BiomeType biomeType, @NotNull Biome biome, @NotNull BiomeDictionary.Type[] types) {
        return new BiomeRegData(null, biome, biomeType, types);
    }
}
