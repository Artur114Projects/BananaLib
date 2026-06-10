package com.artur114.bananalib.mc.base;

import com.artur114.bananalib.mc.registry.IRegisterBus;
import com.artur114.bananalib.mc.registry.data.ModelRegData;
import com.artur114.bananalib.mc.registry.interf.IHasModel;
import com.artur114.bananalib.mc.registry.interf.IHasMoreRegisters;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BBlockBase extends Block implements IHasModel, IHasMoreRegisters {
    protected boolean isForCreative = false;
    protected boolean isOpaqueCube = true;
    protected boolean isFullCube = true;
    public final @Nullable Item item;

    public BBlockBase(String name, Material material, MapColor mapColor, float hardness, float resistance, SoundType soundType) {
        super(material, mapColor);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);

        this.item = this.createItemBlock();
    }

    public BBlockBase(String name, Material material, float hardness, float resistance, SoundType soundType) {
        this(name, material, material.getMaterialMapColor(), hardness, resistance, soundType);
    }

    public BBlockBase(String name, MaterialArray mat) {
        this(name, mat.material(), mat.hardness(), mat.resistance(), mat.soundType());
    }

    protected @Nullable Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName()));
    }

    public BBlockBase setNotFillAndOpaqueCube() {
        this.isOpaqueCube = false;
        this.isFullCube = false;
        return this;
    }

    public BBlockBase setNotFullCube() {
        this.isFullCube = false;
        return this;
    }

    public BBlockBase setNotOpaqueCube() {
        this.isOpaqueCube = false;
        return this;
    }

    public BBlockBase setForCreative() {
        this.isForCreative = true;
        return this;
    }

    @Override
    public @NotNull BBlockBase setCreativeTab(@NotNull CreativeTabs tab) {
        return (BBlockBase) super.setCreativeTab(tab);
    }

    @Override
    public boolean isFullCube(@NotNull IBlockState state) {
        return this.isFullCube;
    }

    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {
        return this.isOpaqueCube;
    }

    @Override
    public List<ModelRegData> registerModelsData() {
        if (this.item == null) return Collections.emptyList();
        return Collections.singletonList(ModelRegData.inventory(this.item, 0));
    }

    @Override
    public void registerOther(IRegisterBus bus) {
        if (this.item == null) return;
        bus.registerItem(this.item);
    }
}
