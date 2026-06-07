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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BananaRegisterBus implements IRegisterBus {
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
    public void preInit() {
        this.initOther();
    }

    @Override
    public <T extends SoundEvent> T registerSound(T sound) {
        this.soundEvents.add(sound);
        this.register(sound);
        return sound;
    }

    @Override
    public <T extends Block> T registerBlock(T block) {
        this.blocks.add(block);
        this.register(block);
        return block;
    }

    @Override
    public <T extends Item> T registerItem(T item) {
        this.items.add(item);
        this.register(item);
        return item;
    }

    @Override
    public <T> T register(T object) {
        RegistryEntry<?> entry = new RegistryEntry<>(object);
        for (Class<?> clazz : entry.flags) {
            this.interfacesMap.add(clazz, entry);
        }
        if (object instanceof IHasMoreRegisters) {
            ((IHasMoreRegisters) object).registerOther(this);
        }
        return object;
    }

    private void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(this.items.toArray(new Item[0]));
    }

    private void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(this.blocks.toArray(new Block[0]));
    }

    @SideOnly(Side.CLIENT)
    private void registerParticlesTexture(TextureStitchEvent.Pre e) {
        for (RegistryEntry<IHasAtlasSprite> reg : this.interfacesMap.find(IHasAtlasSprite.class)) {
            if (this.checkOptionalRegister(reg, IHasAtlasSprite.class)) {
                continue;
            }
            reg.val().registerAtlasSprite(e.getMap());
        }
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void onModelRegister(ModelRegistryEvent event) {
        for(RegistryEntry<IHasModel> reg : this.interfacesMap.find(IHasModel.class)) {
            if (this.checkOptionalRegister(reg, IHasModel.class)) {
                continue;
            }
            if (reg.has(IHasModelRegister.class)) {
                reg.cast(IHasModelRegister.class).registerModels(reg.val().registerModelsData());
            } else {
                List<ModelRegData> data = reg.val().registerModelsData();

                for (ModelRegData d : data) {
                    ModelLoader.setCustomModelResourceLocation(d.item(), d.meta(), new ModelResourceLocation(Objects.requireNonNull(d.item().getRegistryName()), d.id()));
                }
            }
        }

        for (RegistryEntry<IHasTileSR> reg : this.interfacesMap.find(IHasTileSR.class)) {
            if (this.checkOptionalRegister(reg, IHasTileSR.class)) {
                continue;
            }

            if (reg.has(IHasTileSRRegister.class)) {
                reg.cast(IHasTileSRRegister.class).registerTSR(reg.val().registerTSRData());
            } else {
                TESRRegData data = reg.val().registerTSRData();

                ClientRegistry.bindTileEntitySpecialRenderer((Class) data.tileEntityClass(), data.specialRenderer());
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initOther() {
        for (RegistryEntry<IHasTileEntity> reg : this.interfacesMap.find(IHasTileEntity.class)) {
            if (this.checkOptionalRegister(reg, IHasTileEntity.class)) {
                continue;
            }

            if (reg.has(IHasTileRegister.class)) {
                reg.cast(IHasTileRegister.class).registerTileEntity(reg.val().registerTileData());
            } else {
                TileRegData data = reg.val().registerTileData();
                GameRegistry.registerTileEntity(data.tile(), data.key());
            }
        }

        for (RegistryEntry<IHasCraftRecipe> reg : this.interfacesMap.find(IHasCraftRecipe.class)) {
            if (this.checkOptionalRegister(reg, IHasCraftRecipe.class)) {
                continue;
            }

            if (reg.has(IHasCraftRegister.class)) {
                reg.cast(IHasCraftRegister.class).registerCraftRecipes(reg.val().registerCraftRecipesName());
            } else {
                List<ResourceLocation> data = reg.val().registerCraftRecipesName();

                for (ResourceLocation r : data) {
                    CraftingHelper.register(r, (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
                }
            }
        }

        if (this.net != null) {
            for (RegistryEntry<IHasNetworkPacket> reg : this.interfacesMap.find(IHasNetworkPacket.class)) {
                if (this.checkOptionalRegister(reg, IHasNetworkPacket.class)) {
                    continue;
                }

                if (reg.has(IHasPacketRegister.class)) {
                    reg.cast(IHasPacketRegister.class).registerPackets(reg.val().registerPacketsData());
                } else {
                    List<PacketRegData> data = reg.val().registerPacketsData();

                    for (PacketRegData p : data) {
                        this.net.registerMessage((Class) p.messageHandler(), (Class) p.requestMessageType(), p.discriminator(), p.side());
                    }
                }
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

    private boolean checkOptionalRegister(RegistryEntry<?> obj, Class<?> source) {
        if (obj.has(IOptionalRegister.class)) {
            return !obj.cast(IOptionalRegister.class).shouldRegister(source);
        }
        return false;
    }

    private static final class ClassObjListMap {
        private final Map<Class<?>, List<RegistryEntry<?>>> map = new HashMap<>();

        public void add(Class<?> clazz, RegistryEntry<?> obj) {
            this.map.computeIfAbsent(clazz, (k) -> new ArrayList<>()).add(obj);
        }

        @SuppressWarnings("unchecked")
        public <T> List<RegistryEntry<T>> find(Class<T> clazz) {
            return (List<RegistryEntry<T>>) (List<?>) this.map.computeIfAbsent(clazz, (k) -> new ArrayList<>());
        }
    }

    private static final class RegistryEntry<T> {
        private static final Map<Class<?>, Set<Class<?>>> ignoreCache = new ConcurrentHashMap<>();
        private static final Set<Class<?>> iHasClasses = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(
            IHasAtlasSprite.class, IHasCraftRecipe.class, IHasCraftRegister.class,
            IHasModel.class, IHasModelRegister.class, IHasNetworkPacket.class,
            IHasPacketRegister.class, IHasTileEntity.class, IHasTileRegister.class,
            IHasTileSR.class, IOptionalRegister.class
        )));

        private final Set<Class<?>> flags;
        private final T value;

        private RegistryEntry(T value) {
            Objects.requireNonNull(value);
            this.value = value;
            Set<Class<?>> flags = new HashSet<>();
            Set<Class<?>> ignored = ignored(value.getClass());
            for (Class<?> cl : iHasClasses) {
                if (!ignored.contains(cl)) {
                    if (cl.isInstance(value)) {
                        flags.add(cl);
                    }
                }
            }
            this.flags = Collections.unmodifiableSet(flags);
        }

        private boolean has(Class<?> clazz) {
            return this.flags.contains(clazz);
        }

        private T val() {
            return this.value;
        }

        private <V> V cast(Class<V> clazz) {
            return clazz.cast(this.value);
        }

        private static Set<Class<?>> ignored(Class<?> clazz) {
            return ignoreCache.computeIfAbsent(clazz, (k) -> {
                IgnoreRegisters r = k.getAnnotation(IgnoreRegisters.class);
                return r == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet<>(Arrays.asList(r.value())));
            });
        }
    }

    public static class EventsSource {
        private final BananaRegisterBus buss;

        public EventsSource(BananaRegisterBus out) {
            this.buss = out;
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public void registerParticlesTexture(TextureStitchEvent.Pre e) {
            this.buss.registerParticlesTexture(e);
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public void onModelRegister(ModelRegistryEvent event) {
            this.buss.onModelRegister(event);
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
        public void registerSounds(RegistryEvent.Register<SoundEvent> e) {
            this.buss.registerSounds(e);
        }
    }
}
