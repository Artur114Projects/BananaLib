package com.artur114.bananalib.mc.base;

import com.artur114.bananalib.mc.registry.data.ModelRegData;
import com.artur114.bananalib.mc.registry.interf.IHasModel;
import net.minecraft.item.Item;

import java.util.Collections;
import java.util.List;

public abstract class BItemBase extends Item implements IHasModel {
    public BItemBase(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public List<ModelRegData> registerModelsData() {
        return Collections.singletonList(ModelRegData.inventory(this, 0));
    }
}
