package com.artur114.bananalib.mc.base;

import com.artur114.bananalib.mc.registry.data.ModelRegData;
import com.artur114.bananalib.mc.registry.interf.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class BItemBlockBase extends ItemBlock implements IHasModel {
    protected IRarity rarity = EnumRarity.COMMON;

    public BItemBlockBase(Block block) {
        super(block);
    }

    public BItemBlockBase setRarity(IRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
        return this.rarity;
    }

    @Override
    public List<ModelRegData> registerModelsData() {
        return Collections.singletonList(ModelRegData.inventory(this, 0));
    }
}
