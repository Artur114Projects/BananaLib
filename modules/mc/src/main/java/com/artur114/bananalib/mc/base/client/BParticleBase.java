package com.artur114.bananalib.mc.base.client;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;

public class BParticleBase extends Particle {
    protected TextureAtlasSprite[] sprite;

    public BParticleBase(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
    }

    public BParticleBase(World worldIn, double xCordIn, double yCordIn, double zCordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCordIn, yCordIn, zCordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }

    protected void setSprite(TextureAtlasSprite... sprite) {
        this.sprite = sprite;
    }

    protected TextureAtlasSprite[] sprite() {
        return this.sprite;
    }

    protected void bindSprite() {
        this.setParticleTexture(this.sprite[0]);
    }

    protected void bindSprite(int index) {
        this.setParticleTexture(this.sprite[index]);
    }

    @Override
    public int getFXLayer() {
        return 1;
    }
}
