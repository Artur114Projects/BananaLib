package com.artur114.bananalib.mc.registry;

import com.artur114.bananalib.mc.registry.ann.AutoInstantiate;
import com.artur114.bananalib.mc.registry.ann.IgnoreRegisters;
import com.artur114.bananalib.mc.registry.ann.RegistryContainer;
import com.artur114.bananalib.mc.registry.ann.RegistryEntry;
import com.artur114.bananalib.mc.registry.data.*;
import com.artur114.bananalib.mc.registry.interf.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BananaRegisterBus implements IRegisterBus {
    private static final Logger log = LogManager.getLogger("B-REGISTRY-BUS");
    private final Class2RegListMap interfacesMap = new Class2RegListMap();
    private final Set<Object> registered = new HashSet<>();
    private final List<SoundEvent> soundEvents = new ArrayList<>();
    private final List<Block> blocks = new ArrayList<>();
    private final List<Biome> biomes = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private boolean subscribed = false;
    private SimpleNetworkWrapper net;

    @Override
    public IRegisterBus putNetWrapper(SimpleNetworkWrapper wrapper) {
        this.net = wrapper; return this;
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
        for (RegEntry<ILoadStagePre> listen : this.interfacesMap.find(ILoadStagePre.class)) {
            if (this.checkOptionalRegister(listen, ILoadStagePre.class)) {
                continue;
            }
            listen.val().onPreInit();
        }
    }

    @Override
    public void init() {
        for (RegEntry<ILoadStageInit> listen : this.interfacesMap.find(ILoadStageInit.class)) {
            if (this.checkOptionalRegister(listen, ILoadStageInit.class)) {
                continue;
            }
            listen.val().onInit();
        }
    }

    @Override
    public void postInit() {
        for (RegEntry<ILoadStagePost> listen : this.interfacesMap.find(ILoadStagePost.class)) {
            if (this.checkOptionalRegister(listen, ILoadStagePost.class)) {
                continue;
            }
            listen.val().onPostInit();
        }
    }

    @Override
    public void registerSound(SoundEvent... sound) {
        for (SoundEvent obj : sound) {
            this.registerSound(obj);
        }
    }

    @Override
    public void scanAndRegister(Class<?>... clazz) {
        for (Class<?> obj : clazz) {
            this.scanAndRegister(obj);
        }
    }

    @Override
    public void registerBlock(Block... block) {
        for (Block obj : block) {
            this.registerBlock(obj);
        }
    }

    @Override
    public void registerBiome(Biome... biome) {
        for (Biome obj : biome) {
            this.registerBiome(obj);
        }
    }

    @Override
    public void registerItem(Item... item) {
        for (Item obj : item) {
            this.registerItem(obj);
        }
    }

    @Override
    public void registerAuto(Object... object) {
        for (Object obj : object) {
            this.registerAuto(obj);
        }
    }

    @Override
    public void register(Object... object) {
        for (Object obj : object) {
            this.register(obj);
        }
    }

    @Override
    public <T extends SoundEvent> T registerSound(T sound) {
        if (sound == null) return null;
        if (this.registered.contains(sound)) {
            this.logDoubleReg(sound);
            return sound;
        }
        this.soundEvents.add(sound);
        this.register(sound);
        return sound;
    }

    @Override
    public <T extends Class<?>> T scanAndRegister(T clazz) {
        if (clazz == null) return null;
        this.scanAndRegisterInternal(clazz);
        return clazz;
    }

    @Override
    public <T extends Biome> T registerBiome(T biome) {
        if (biome == null) return null;
        if (this.registered.contains(biome)) {
            this.logDoubleReg(biome);
            return biome;
        }
        this.biomes.add(biome);
        this.register(biome);
        return biome;
    }

    @Override
    public <T extends Block> T registerBlock(T block) {
        if (block == null) return null;
        if (this.registered.contains(block)) {
            this.logDoubleReg(block);
            return block;
        }
        this.blocks.add(block);
        this.register(block);
        return block;
    }

    @Override
    public <T extends Item> T registerItem(T item) {
        if (item == null) return null;
        if (this.registered.contains(item)) {
            this.logDoubleReg(item);
            return item;
        }
        this.items.add(item);
        this.register(item);
        return item;
    }

    @Override
    public <T> T registerAuto(T object) {
        if (object == null) return null;
        if (object instanceof Item) {
            this.registerItem((Item) object);
        } else if (object instanceof Block) {
            this.registerBlock((Block) object);
        } else if (object instanceof Biome) {
            this.registerBiome((Biome) object);
        } else if (object instanceof SoundEvent) {
            this.registerSound((SoundEvent) object);
        } else if (object instanceof Class<?>) {
            this.scanAndRegister((Class<?>) object);
        } else if (object.getClass().isArray()) {
            this.registerAuto((Object[]) object);
        } else {
            this.register(object);
        }
        return object;
    }

    @Override
    public <T> T register(T object) {
        if (object == null) return null;
        if (this.registered.contains(object)) {
            this.logDoubleReg(object);
            return object;
        }
        RegEntry<?> entry = new RegEntry<>(object);
        for (Class<?> clazz : entry.flags) {
            this.interfacesMap.add(clazz, entry);
        }
        if (object instanceof IHasMoreRegisters) {
            ((IHasMoreRegisters) object).registerOther(this);
        }
        this.registered.add(object);
        return object;
    }

    @Override
    public <T> List<T> entries(Class<T> clazz) {
        List<RegEntry<T>> entries = this.interfacesMap.findIfHas(clazz);

        if (entries.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<>(entries.size());
        for (RegEntry<T> entry : entries) {
            list.add(entry.value);
        }

        return list;
    }

    @Override
    public List<SoundEvent> sounds() {
        return Collections.unmodifiableList(this.soundEvents);
    }

    @Override
    public List<Biome> biomes() {
        return Collections.unmodifiableList(this.biomes);
    }

    @Override
    public List<Block> blocks() {
        return Collections.unmodifiableList(this.blocks);
    }

    @Override
    public List<Item> items() {
        return Collections.unmodifiableList(this.items);
    }

    private void logDoubleReg(Object o) {
        log.warn("Attempt to double register an object {}", o);
    }

    private void scanAndRegisterInternal(Class<?> clazz) {
        if (clazz.isAnnotationPresent(AutoInstantiate.class)) {
            try {
                Object obj = clazz.getDeclaredConstructor().newInstance();
                this.registerAuto(obj);
            } catch (Exception e) {
                log.warn("Failed to create instance of class: {}", clazz);
            }
        } else {
            this.scanAndRegisterFields(clazz, !clazz.isAnnotationPresent(RegistryContainer.class));
        }
    }

    private void scanAndRegisterFields(Class<?> clazz, boolean checkAnnotation) {
        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (!checkAnnotation || field.isAnnotationPresent(RegistryEntry.class)) {
                boolean as = field.isAccessible();
                field.setAccessible(true);
                try {
                    Object val = field.get(null);
                    this.registerAuto(val);
                } catch (IllegalAccessException ignored) {}
                field.setAccessible(as);
            }
        }
    }

    private void onBiomeRegister(RegistryEvent.Register<Biome> event) {
        event.getRegistry().registerAll(this.biomes.toArray(new Biome[0]));

        for(RegEntry<IHasBiome> reg : this.interfacesMap.find(IHasBiome.class)) {
            if (this.checkOptionalRegister(reg, IHasBiome.class)) {
                continue;
            }
            if (reg.has(IHasBiomeRegister.class)) {
                reg.cast(IHasBiomeRegister.class).registerBiomes(reg.val().registerBiomesData());
            } else {
                List<BiomeRegData> data = reg.val().registerBiomesData();

                for (BiomeRegData biome : data) {
                    BiomeDictionary.addTypes(biome.biome(), biome.types());

                    if (biome.biomeEntry() != null) {
                        BiomeManager.addBiome(biome.biomeType(), biome.biomeEntry());
                        BiomeManager.addSpawnBiome(biome.biome());
                    }
                }
            }
        }
    }

    private void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(this.items.toArray(new Item[0]));
    }

    private void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(this.blocks.toArray(new Block[0]));
    }

    @SideOnly(Side.CLIENT)
    private void registerTextures(TextureStitchEvent.Pre e) {
        for (RegEntry<IHasAtlasSprite> reg : this.interfacesMap.find(IHasAtlasSprite.class)) {
            if (this.checkOptionalRegister(reg, IHasAtlasSprite.class)) {
                continue;
            }
            reg.val().registerAtlasSprite(e.getMap());
        }
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void onModelRegister(ModelRegistryEvent event) {
        for(RegEntry<IHasModel> reg : this.interfacesMap.find(IHasModel.class)) {
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

        for (RegEntry<IHasTileSR> reg : this.interfacesMap.find(IHasTileSR.class)) {
            if (this.checkOptionalRegister(reg, IHasTileSR.class)) {
                continue;
            }

            if (reg.has(IHasTileSRRegister.class)) {
                reg.cast(IHasTileSRRegister.class).registerTSR(reg.val().registerTSRData());
            } else {
                TESRRegData data = reg.val().registerTSRData();

                ClientRegistry.bindTileEntitySpecialRenderer((Class) data.tileEntityClass(), (TileEntitySpecialRenderer) data.specialRenderer());
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initOther() {
        for (RegEntry<IHasTileEntity> reg : this.interfacesMap.find(IHasTileEntity.class)) {
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

        for (RegEntry<IHasCraftRecipe> reg : this.interfacesMap.find(IHasCraftRecipe.class)) {
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
            for (RegEntry<IHasNetworkPacket> reg : this.interfacesMap.find(IHasNetworkPacket.class)) {
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

    private boolean checkOptionalRegister(RegEntry<?> obj, Class<?> source) {
        if (obj.has(IOptionalRegister.class)) {
            return !obj.cast(IOptionalRegister.class).shouldRegister(source);
        }
        return false;
    }

    private static final class Class2RegListMap {
        private final Map<Class<?>, List<RegEntry<?>>> map = new HashMap<>();

        public void add(Class<?> clazz, RegEntry<?> obj) {
            this.map.computeIfAbsent(clazz, (k) -> new ArrayList<>()).add(obj);
        }

        @SuppressWarnings("unchecked")
        public <T> List<RegEntry<T>> find(Class<T> clazz) {
            return (List<RegEntry<T>>) (List<?>) this.map.computeIfAbsent(clazz, (k) -> new ArrayList<>());
        }

        @SuppressWarnings("unchecked")
        public <T> List<RegEntry<T>> findIfHas(Class<T> clazz) {
            return (List<RegEntry<T>>) (List<?>) this.map.getOrDefault(clazz, Collections.emptyList());
        }
    }

    private static final class RegEntry<T> {
        private static final Map<Class<?>, Set<Class<?>>> ignoreCache = new ConcurrentHashMap<>();
        private static final Set<Class<?>> iHasClasses = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(
            IHasAtlasSprite.class, IHasCraftRecipe.class, IHasCraftRegister.class,
            IHasModel.class, IHasModelRegister.class, IHasNetworkPacket.class,
            IHasPacketRegister.class, IHasTileEntity.class, IHasTileRegister.class,
            IHasTileSR.class, IHasTileSRRegister.class, IOptionalRegister.class,
            IHasBiome.class, IHasBiomeRegister.class, ILoadStageInit.class,
            ILoadStagePre.class, ILoadStagePost.class
        )));

        private final Set<Class<?>> flags;
        private final T value;

        private RegEntry(T value) {
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
        private final BananaRegisterBus bus;

        public EventsSource(BananaRegisterBus out) {
            this.bus = out;
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public void registerTextures(TextureStitchEvent.Pre e) {
            this.bus.registerTextures(e);
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public void onModelRegister(ModelRegistryEvent event) {
            this.bus.onModelRegister(event);
        }

        @SubscribeEvent
        public void onItemRegister(RegistryEvent.Register<Item> event) {
            this.bus.onItemRegister(event);
        }

        @SubscribeEvent
        public void onBlockRegister(RegistryEvent.Register<Block> event) {
            this.bus.onBlockRegister(event);
        }

        @SubscribeEvent
        public void registerSounds(RegistryEvent.Register<SoundEvent> e) {
            this.bus.registerSounds(e);
        }

        @SubscribeEvent
        public void onBiomeRegister(RegistryEvent.Register<Biome> e) {
            this.bus.onBiomeRegister(e);
        }
    }
}
