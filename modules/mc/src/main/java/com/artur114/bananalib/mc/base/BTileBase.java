package com.artur114.bananalib.mc.base;


import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;


public abstract class BTileBase extends TileEntity {
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(@NotNull NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readSyncNBT(pkt.getNbtCompound());
    }

    @Override
    public @NotNull NBTTagCompound getUpdateTag() {
        return this.writeSyncNBT(super.getUpdateTag());
    }

    @Override
    public void handleUpdateTag(@NotNull NBTTagCompound tag) {
        this.readSyncNBT(tag);
    }

    public void syncTile(boolean render) {
        IBlockState state = this.world.getBlockState(this.pos);
        this.world.notifyBlockUpdate(this.pos, state, state, 2 + (render ? 4 : 0));
    }

    @SideOnly(Side.CLIENT)
    protected abstract void readSyncNBT(NBTTagCompound nbt);
    protected abstract NBTTagCompound writeSyncNBT(NBTTagCompound nbt);
}
