package com.artur114.bananalib.mc.register;

import com.artur114.bananalib.mc.register.interf.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class BananaRegisterBuss implements IRegisterBuss {
    private final ClassObjListMap interfacesMap = new ClassObjListMap();
    private final List<SoundEvent> soundEvents = new ArrayList<>();
    private final List<Block> blocks = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private boolean subscribed = false;
    private SimpleNetworkWrapper net;

    @Override
    public void putNetWrapper(SimpleNetworkWrapper wrapper) {
        this.net = wrapper;
    }

    @Override
    public void subscribe() {
        if (this.subscribed) throw new IllegalStateException("Double subscribe!");
        MinecraftForge.EVENT_BUS.register(new EventsSource(this));
        this.subscribed = true;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void preInit() {
        for (IHasTileEntity reg : this.interfacesMap.find(IHasTileEntity.class)) {
            if (this.checkOptionalRegister(reg, IHasTileEntity.class)) {
                continue;
            }

            if (reg instanceof IHasTileRegister) {
                ((IHasTileRegister) reg).registerTileEntity(reg.registerTileData());
            } else {
                TileRegData data = reg.registerTileData();
                GameRegistry.registerTileEntity(data.tile(), data.key());
            }
        }

        for (IHasCraftRecipe reg : this.interfacesMap.find(IHasCraftRecipe.class)) {
            if (this.checkOptionalRegister(reg, IHasCraftRecipe.class)) {
                continue;
            }

            if (reg instanceof IHasCraftRegister) {
                ((IHasCraftRegister) reg).registerCraftRecipes(reg.registerCraftRecipesName());
            } else {
                ResourceLocation[] data = reg.registerCraftRecipesName();

                for (ResourceLocation r : data) {
                    CraftingHelper.register(r, (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
                }
            }
        }

        if (this.net != null) {
            for (IHasNetworkPacket reg : this.interfacesMap.find(IHasNetworkPacket.class)) {
                if (this.checkOptionalRegister(reg, IHasNetworkPacket.class)) {
                    continue;
                }

                if (reg instanceof IHasPacketRegister) {
                    ((IHasPacketRegister) reg).registerPackets(reg.registerPacketsData());
                } else {
                    PacketRegData[] data = reg.registerPacketsData();

                    for (PacketRegData p : data) {
                        this.net.registerMessage((Class) p.messageHandler(), (Class) p.requestMessageType(), p.discriminator(), p.side());
                    }
                }
            }
        }
    }

    @Override
    public void registerSound(SoundEvent sound) {
        this.soundEvents.add(sound);
        this.register(sound);
    }

    @Override
    public void registerBlock(Block block) {
        this.blocks.add(block);
        this.register(block);
    }

    @Override
    public void registerItem(Item item) {
        this.items.add(item);
        this.register(item);
    }

    @Override
    public void register(Object object) {
        if (object instanceof IHasAtlasSprite) {
            this.interfacesMap.add(IHasAtlasSprite.class, object);
        }
        if (object instanceof IHasCraftRecipe) {
            this.interfacesMap.add(IHasCraftRecipe.class, object);
        }
        if (object instanceof IHasModel) {
            this.interfacesMap.add(IHasModel.class, object);
        }
        if (object instanceof IHasNetworkPacket) {
            this.interfacesMap.add(IHasNetworkPacket.class, object);
        }
        if (object instanceof IHasTileEntity) {
            this.interfacesMap.add(IHasTileEntity.class, object);
        }
        if (object instanceof IHasTileSR) {
            this.interfacesMap.add(IHasTileSR.class, object);
        }
    }

    private void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(this.items.toArray(new Item[0]));
    }

    private void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(this.blocks.toArray(new Block[0]));
    }

    @SideOnly(Side.CLIENT)
    private void registerParticlesTexture(TextureStitchEvent.Pre e) {
        for (IHasAtlasSprite reg : this.interfacesMap.find(IHasAtlasSprite.class)) {
            if (this.checkOptionalRegister(reg, IHasAtlasSprite.class)) {
                continue;
            }
            reg.registerAtlasSprite(e.getMap());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void onModelRegister(ModelRegistryEvent event) {
        for(IHasModel reg : this.interfacesMap.find(IHasModel.class)) {
            if (this.checkOptionalRegister(reg, IHasModel.class)) {
                continue;
            }
            if (reg instanceof IHasModelRegister) {
                ((IHasModelRegister) reg).registerModels(reg.registerModelsData());
            } else {
                ModelRegData[] data = reg.registerModelsData();

                for (ModelRegData d : data) {
                    ModelLoader.setCustomModelResourceLocation(d.item(), d.meta(), new ModelResourceLocation(Objects.requireNonNull(d.item().getRegistryName()), d.id()));
                }
            }
        }

        for (IHasTileSR reg : this.interfacesMap.find(IHasTileSR.class)) {
            if (this.checkOptionalRegister(reg, IHasTileSR.class)) {
                continue;
            }

            if (reg instanceof IHasTileSRRegister) {
                ((IHasTileSRRegister) reg).registerTSR(reg.registerTSRData());
            } else {
                TESRRegData data = reg.registerTSRData();

                ClientRegistry.bindTileEntitySpecialRenderer((Class) data.tileEntityClass(), data.specialRenderer());
            }
        }
    }

    public void registerSounds(RegistryEvent.Register<SoundEvent> e) {
        for (SoundEvent soundEvent : this.soundEvents) {
            if (soundEvent != null) {
                e.getRegistry().register(soundEvent);
            }
        }
    }

    private boolean checkOptionalRegister(Object obj, Class<?> source) {
        if (obj instanceof IOptionalRegister) {
            return !((IOptionalRegister) obj).doRegister(source);
        }
        return false;
    }

    private static final class ClassObjListMap {
        private final Map<Class<?>, List<Object>> map = new HashMap<>();

        public void add(Class<?> clazz, Object obj) {
            this.map.computeIfAbsent(clazz, (k) -> new ArrayList<>()).add(obj);
        }

        @SuppressWarnings("unchecked")
        public <T> List<T> find(Class<T> clazz) {
            return (List<T>) this.map.computeIfAbsent(clazz, (k) -> new ArrayList<>());
        }
    }

    public static class EventsSource {
        private final BananaRegisterBuss buss;

        public EventsSource(BananaRegisterBuss out) {
            this.buss = out;
        }

        @SubscribeEvent
        public void onItemRegister(RegistryEvent.Register<Item> event) {
            this.buss.onItemRegister(event);
        }

        @SubscribeEvent
        public void onBlockRegister(RegistryEvent.Register<Block> event) {
            this.buss.onBlockRegister(event);
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public void registerParticlesTexture(TextureStitchEvent.Pre e) {
            this.buss.registerParticlesTexture(e);
        }

        @SubscribeEvent
        public void onModelRegister(ModelRegistryEvent event) {
            this.buss.onModelRegister(event);
        }

        @SubscribeEvent
        public void registerSounds(RegistryEvent.Register<SoundEvent> e) {
            this.buss.registerSounds(e);
        }
    }
}
