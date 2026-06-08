package com.artur114.bananalib.mc.base.client;

import com.artur114.bananalib.mc.register.interf.IHasAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class RegAtlasSprite implements IHasAtlasSprite {
    private TextureAtlasSprite atlasSprite = null;
    protected final ResourceLocation sprite;

    public RegAtlasSprite(ResourceLocation sprite) {
        this.sprite = sprite;
    }

    @Override
    public void registerAtlasSprite(TextureMap map) {
        this.atlasSprite = map.registerSprite(this.sprite);
    }

    public String spriteName() {
        return this.sprite.toString();
    }

    public ResourceLocation rl() {
        return this.sprite;
    }

    public TextureAtlasSprite atlasSprite() {
        return this.atlasSprite;
    }

    @Override
    public String toString() {
        return this.spriteName();
    }

    public static RegAtlasSprite of(ResourceLocation sprite) {
        return new RegAtlasSprite(sprite);
    }

    public static RegAtlasSprite[] of(ResourceLocation... sprites) {
        RegAtlasSprite[] ret = new RegAtlasSprite[sprites.length];
        for (int i = 0; i != sprites.length; i++) {
            ret[i] = new RegAtlasSprite(sprites[i]);
        }
        return ret;
    }

    public static RegAtlasSprite[] of(String modid, String... names) {
        RegAtlasSprite[] ret = new RegAtlasSprite[names.length];
        for (int i = 0; i != names.length; i++) {
            ret[i] = new RegAtlasSprite(new ResourceLocation(modid, names[i]));
        }
        return ret;
    }

    public static RegAtlasSprite[] of(String modid, String name, int startIndex, int namesCount) {
        RegAtlasSprite[] ret = new RegAtlasSprite[namesCount];
        for (int i = 0; i != namesCount; i++) {
            ret[i] = new RegAtlasSprite(new ResourceLocation(modid, name + (startIndex + i)));
        }
        return ret;
    }

    public static TextureAtlasSprite[] toTextures(RegAtlasSprite... sprites) {
        TextureAtlasSprite[] ret = new TextureAtlasSprite[sprites.length];
        for (int i = 0; i != sprites.length; i++) {
            ret[i] = sprites[i].atlasSprite;
        }
        return ret;
    }
}
