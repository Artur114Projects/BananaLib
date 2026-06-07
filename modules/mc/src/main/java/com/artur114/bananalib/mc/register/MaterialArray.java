package com.artur114.bananalib.mc.register;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MaterialArray {
    private final SoundType soundType;
    private final Material material;
    private final float resistance;
    private final float hardness;

    public MaterialArray(SoundType soundType, Material material, float resistance, float hardness) {
        this.soundType = soundType;
        this.material = material;
        this.resistance = resistance;
        this.hardness = hardness;
    }

    public SoundType soundType() {
        return this.soundType;
    }

    public Material material() {
        return this.material;
    }

    public float resistance() {
        return this.resistance;
    }

    public float hardness() {
        return this.hardness;
    }

    public static MatArrayBuilder builder() {
        return new MatArrayBuilder();
    }

    public static MaterialArray from(SoundType soundType, Material material, float resistance, float hardness) {
        return new MaterialArray(soundType, material, resistance, hardness);
    }
}
