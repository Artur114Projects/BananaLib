package com.artur114.bananalib.mc.services.client;

import com.artur114.bananalib.math.m3d.box.Box3DM;
import com.artur114.bananalib.math.m3d.box.IBox3DM;
import com.artur114.bananalib.mc.base.tileabs.ITileMultiBBProvider;
import com.artur114.bananalib.mc.services.IService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockHighlightService implements IService {
    @SubscribeEvent
    public void drawBlockHighlightEvent(DrawBlockHighlightEvent e) {
        if (e.getTarget().typeOfHit != RayTraceResult.Type.BLOCK) {
            return;
        }

        BlockPos pos = e.getTarget().getBlockPos();
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        World world = mc.world;

        TileEntity tileRaw = mc.world.getTileEntity(pos);

        if (tileRaw instanceof ITileMultiBBProvider) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.glLineWidth(2.0F);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);

            if (world.getWorldBorder().contains(e.getTarget().getBlockPos())) {
                double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) e.getPartialTicks();
                double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) e.getPartialTicks();
                double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) e.getPartialTicks();
                for (AxisAlignedBB bb : ((ITileMultiBBProvider) tileRaw).boundingBoxes()) {
                    IBox3DM box = Box3DM.obtain().set(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ);
                    box.grow(0.002D).offset(pos.getX(), pos.getY(), pos.getZ()).offset(-x, -y, -z);
                    RenderGlobal.drawBoundingBox(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ(), 0.0F, 0.0F, 0.0F, 0.4F);
                    Box3DM.release((Box3DM) box);
                }
            }

            GlStateManager.depthMask(true);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();

            e.setCanceled(true);
        }
    }

    @Override
    public void unsubscribe() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @Override
    public void subscribe() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
