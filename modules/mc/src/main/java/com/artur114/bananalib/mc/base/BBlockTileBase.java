package com.artur114.bananalib.mc.base;

import com.artur114.bananalib.mc.base.tileabs.*;
import com.artur114.bananalib.mc.registry.data.TESRRegData;
import com.artur114.bananalib.mc.registry.data.TileRegData;
import com.artur114.bananalib.mc.registry.interf.IHasTileEntity;
import com.artur114.bananalib.mc.registry.interf.IHasTileSR;
import com.artur114.bananalib.mc.registry.interf.IOptionalRegister;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BBlockTileBase<T extends TileEntity> extends BBlockBase implements IHasTileEntity, IHasTileSR, IOptionalRegister {
    protected TileEntitySpecialRenderer<T> tileRender = null;
    protected final boolean isRedstoneConnListener;
    protected final boolean isNeighborListener;
    protected final boolean isMultiBBProvider;
    protected final boolean isBreakListener;
    protected final boolean isPlaceListener;
    protected final boolean isUseListener;

    public BBlockTileBase(String name, Material material, MapColor mapColor, float hardness, float resistance, SoundType soundType) {
        super(name, material, mapColor, hardness, resistance, soundType);

        this.isUseListener = ITileBlockUseListener.class.isAssignableFrom(this.tileClass());
        this.isPlaceListener = ITileBlockPlaceListener.class.isAssignableFrom(this.tileClass());
        this.isBreakListener = ITileBlockBreakListener.class.isAssignableFrom(this.tileClass());
        this.isMultiBBProvider = ITileMultiBBProvider.class.isAssignableFrom(this.tileClass());
        this.isNeighborListener = ITileNeighborChangeListener.class.isAssignableFrom(this.tileClass());
        this.isRedstoneConnListener = ITileCanRedstoneListener.class.isAssignableFrom(this.tileClass());
    }

    public BBlockTileBase(String name, Material material, float hardness, float resistance, SoundType soundType) {
        this(name, material, material.getMaterialMapColor(), hardness, resistance, soundType);
    }

    public BBlockTileBase(String name, MaterialArray mat) {
        this(name, mat.material(), mat.hardness(), mat.resistance(), mat.soundType());
    }

    public void setTileRender(TileEntitySpecialRenderer<T> render) {
        this.tileRender = render;
    }

    @Override
    public TileRegData registerTileData() {
        return TileRegData.of(this.tileClass(), this.getRegistryName());
    }

    @Override
    public TESRRegData registerTSRData() {
        return TESRRegData.of(this.tileClass(), this.tileRender);
    }

    @Override
    public boolean shouldRegister(Class<?> registerSource) {
        if (registerSource == IHasTileSR.class) {
            return this.tileRender != null;
        }
        return true;
    }

    @NotNull
    public abstract Class<T> tileClass();
    @Nullable
    public abstract T createTileEntity(@NotNull World world, @NotNull IBlockState blockState);

    @Override
    public boolean hasTileEntity(@NotNull IBlockState blockState) {
        return true;
    }

    @Override
    public void onNeighborChange(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull BlockPos neighbor) {
        if (this.isNeighborListener) {
            TileEntity tile = world.getTileEntity(pos);
            if (this.tileClass().isInstance(tile)) {
                ((ITileNeighborChangeListener) tile).onNeighborChange(world, pos, neighbor);
            }
        }
    }

    @Nullable
    @Override
    protected RayTraceResult rayTrace(@NotNull BlockPos pos, @NotNull Vec3d start, @NotNull Vec3d end, @NotNull AxisAlignedBB boundingBox) {
        if (this.isMultiBBProvider) {
            TileEntity tile = Minecraft.getMinecraft().world.getTileEntity(pos);
            if (this.tileClass().isInstance(tile)) {
                Vec3d vec3d = start.subtract(pos.getX(), pos.getY(), pos.getZ());
                Vec3d vec3d1 = end.subtract(pos.getX(), pos.getY(), pos.getZ());
                RayTraceResult raytraceresult = null;
                for (AxisAlignedBB bb : ((ITileMultiBBProvider) tile).boundingBoxes()) {
                    raytraceresult = bb.calculateIntercept(vec3d, vec3d1);

                    if (raytraceresult != null) {
                        break;
                    }
                }
                return raytraceresult == null ? null : new RayTraceResult(raytraceresult.hitVec.addVector(pos.getX(), pos.getY(), pos.getZ()), raytraceresult.sideHit, pos);
            }
        }

        return super.rayTrace(pos, start, end, boundingBox);

    }

    @Override
    public void addCollisionBoxToList(@NotNull IBlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull AxisAlignedBB entityBox, @NotNull List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        if (this.isMultiBBProvider) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (this.tileClass().isInstance(tile)) {
                for (AxisAlignedBB bb : ((ITileMultiBBProvider) tile).boundingBoxes()) {
                    Block.addCollisionBoxToList(pos, entityBox, collidingBoxes, bb);
                }
                return;
            }
        }

        super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, isActualState);
    }

    @Override
    public boolean onBlockActivated(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityPlayer playerIn, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this.isUseListener) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (this.tileClass().isInstance(tile)) {
                return ((ITileBlockUseListener) tile).onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
            }
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityLivingBase placer, @NotNull ItemStack stack) {
        if (this.isPlaceListener) {
            TileEntity tile = this.createTileEntity(worldIn, state);
            if (tile != null) {
                worldIn.setTileEntity(pos, tile);
                ((ITileBlockPlaceListener) tile).onBlockPlacedBy(worldIn, pos, state, placer, stack);
            }
        }

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void breakBlock(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
        if (this.isBreakListener) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (this.tileClass().isInstance(tile)) {
                ((ITileBlockBreakListener) tile).breakBlock(worldIn, pos, state);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean canConnectRedstone(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos, @Nullable EnumFacing side) {
        if (this.isRedstoneConnListener) {
            TileEntity tile = world.getTileEntity(pos);
            if (this.tileClass().isInstance(tile)) {
                return ((ITileCanRedstoneListener) tile).canConnectRedstone(state, world, pos, side);
            }
        }

        return super.canConnectRedstone(state, world, pos, side);
    }
}