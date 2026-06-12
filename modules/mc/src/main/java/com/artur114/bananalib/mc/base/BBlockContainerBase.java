package com.artur114.bananalib.mc.base;

import com.artur114.bananalib.mc.base.tileabs.*;
import com.artur114.bananalib.mc.registry.IRegisterBus;
import com.artur114.bananalib.mc.registry.data.ModelRegData;
import com.artur114.bananalib.mc.registry.data.TESRRegData;
import com.artur114.bananalib.mc.registry.data.TileRegData;
import com.artur114.bananalib.mc.registry.interf.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BBlockContainerBase<T extends TileEntity> extends BlockContainer implements IHasTileEntity, IHasTileSR, IHasModel, IHasMoreRegisters, IOptionalRegister {
    private TileEntitySpecialRenderer<T> tileRender = null;
    private final boolean isRedstoneConnListener;
    private final boolean isNeighborListener;
    private final boolean isMultiBBProvider;
    private final boolean isBreakListener;
    private final boolean isPlaceListener;
    private final boolean isUseListener;
    protected boolean isForCreative = false;
    protected boolean isOpaqueCube = true;
    protected boolean isFullCube = true;
    public final @Nullable Item item;

    public BBlockContainerBase(String name, Material material, MapColor mapColor, float hardness, float resistance, SoundType soundType) {
        super(material, mapColor);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);

        this.item = createItemBlock();
        this.isUseListener = ITileBlockUseListener.class.isAssignableFrom(this.tileClass());
        this.isPlaceListener = ITileBlockPlaceListener.class.isAssignableFrom(this.tileClass());
        this.isBreakListener = ITileBlockBreakListener.class.isAssignableFrom(this.tileClass());
        this.isMultiBBProvider = ITileMultiBBProvider.class.isAssignableFrom(this.tileClass());
        this.isNeighborListener = ITileNeighborChangeListener.class.isAssignableFrom(this.tileClass());
        this.isRedstoneConnListener = ITileCanRedstoneListener.class.isAssignableFrom(this.tileClass());
    }

    public BBlockContainerBase(String name, Material material, float hardness, float resistance, SoundType soundType) {
        this(name, material, material.getMaterialMapColor(), hardness, resistance, soundType);
    }

    public BBlockContainerBase(String name, MaterialArray mat) {
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
    public List<ModelRegData> registerModelsData() {
        if (this.item == null) return Collections.emptyList();
        return Collections.singletonList(ModelRegData.inventory(this.item, 0));
    }

    @Override
    public void registerOther(IRegisterBus bus) {
        if (this.item == null) return;
        bus.registerItem(this.item);
    }

    @Override
    public boolean shouldRegister(Class<?> registerSource) {
        if (registerSource == IHasTileSR.class) {
            return this.tileRender != null;
        }
        return true;
    }

    protected @Nullable Item createItemBlock() {
        return new BItemBlockBase(this).setRegistryName(Objects.requireNonNull(this.getRegistryName()));
    }

    @NotNull
    public abstract Class<T> tileClass();
    @Nullable
    public abstract T createTileEntity(@NotNull World world, @NotNull IBlockState blockState);

    @Override
    public boolean hasTileEntity(@NotNull IBlockState blockState) {
        return true;
    }

    public BBlockContainerBase<T> setNotFillAndOpaqueCube() {
        this.isOpaqueCube = false;
        this.isFullCube = false;
        return this;
    }

    public BBlockContainerBase<T> setNotFullCube() {
        this.isFullCube = false;
        return this;
    }

    public BBlockContainerBase<T> setNotOpaqueCube() {
        this.isOpaqueCube = false;
        return this;
    }

    public BBlockContainerBase<T> setForCreative() {
        this.isForCreative = true;
        return this;
    }

    @Override
    public @NotNull BBlockContainerBase<T> setCreativeTab(@NotNull CreativeTabs tab) {
        super.setCreativeTab(tab); return this;
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