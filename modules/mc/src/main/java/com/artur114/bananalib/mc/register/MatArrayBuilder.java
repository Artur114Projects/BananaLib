package com.artur114.bananalib.mc.register;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MatArrayBuilder {
    private SoundType soundType = SoundType.STONE;
    private Material material = Material.ROCK;
    private float resistance = 10.0F;
    private float hardness = 1.5F;

    public MatArrayBuilder setHardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public MatArrayBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public MatArrayBuilder setResistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    public MatArrayBuilder setSoundType(SoundType soundType) {
        this.soundType = soundType;
        return this;
    }

    public MaterialArray build() {
        return new MaterialArray(this.soundType, this.material, this.resistance, this.hardness);
    }
}
