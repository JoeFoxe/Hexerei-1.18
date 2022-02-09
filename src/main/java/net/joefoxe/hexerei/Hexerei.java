package net.joefoxe.hexerei;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.serialization.Codec;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.client.renderer.entity.ModEntityTypes;
import net.joefoxe.hexerei.config.HexConfig;
import net.joefoxe.hexerei.config.ModKeyBindings;
import net.joefoxe.hexerei.data.recipes.HexereiDataGenerator;
import net.joefoxe.hexerei.data.recipes.HexereiRecipeProvider;
import net.joefoxe.hexerei.events.SageBurningPlateEvent;
import net.joefoxe.hexerei.screen.BroomScreen;
import net.joefoxe.hexerei.world.gen.*;
import net.joefoxe.hexerei.world.structure.structures.WitchHutStructure;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.joefoxe.hexerei.container.ModContainers;
import net.joefoxe.hexerei.data.recipes.ModRecipeTypes;
import net.joefoxe.hexerei.fluid.ModFluids;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.particle.ModParticleTypes;
import net.joefoxe.hexerei.screen.CofferScreen;
import net.joefoxe.hexerei.screen.HerbJarScreen;
import net.joefoxe.hexerei.screen.MixingCauldronScreen;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.joefoxe.hexerei.util.*;
import net.joefoxe.hexerei.world.biome.ModBiomes;
import net.joefoxe.hexerei.world.processor.DarkCovenLegProcessor;
import net.joefoxe.hexerei.world.processor.MangroveTreeLegProcessor;
import net.joefoxe.hexerei.world.processor.WitchHutLegProcessor;
import net.joefoxe.hexerei.world.structure.ModStructures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Hexerei.MOD_ID)
public class Hexerei
{
    public static SidedProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static final String MOD_ID = "hexerei";

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(HexereiConstants.CHANNEL_NAME)
            .clientAcceptedVersions(HexereiConstants.PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(HexereiConstants.PROTOCOL_VERSION::equals)
            .networkProtocolVersion(HexereiConstants.PROTOCOL_VERSION::toString)
            .simpleChannel();

    public static StructureProcessorType<WitchHutLegProcessor> WITCH_HUT_LEG_PROCESSOR = () -> WitchHutLegProcessor.CODEC;
    public static StructureProcessorType<DarkCovenLegProcessor> DARK_COVEN_LEG_PROCESSOR = () -> DarkCovenLegProcessor.CODEC;
    public static StructureProcessorType<MangroveTreeLegProcessor> MANGROVE_TREE_LEG_PROCESSOR = () -> MangroveTreeLegProcessor.CODEC;

    public Hexerei() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

//        eventBus.addListener(this::gatherData);

        eventBus.addListener(HexereiDataGenerator::gatherData);
        eventBus.addGenericListener(RecipeSerializer.class, ModItems::registerRecipeSerializers);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModFluids.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);
        ModRecipeTypes.register(eventBus);
        ModParticleTypes.PARTICLES.register(eventBus);
        ModFeatures.register(eventBus);
        ModStructures.register(eventBus);
        ModBiomes.register(eventBus);


        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        ModEntityTypes.register(eventBus);

        eventBus.addListener(this::loadComplete);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        forgeEventBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        forgeEventBus.addListener(EventPriority.NORMAL, WitchHutStructure::setupStructureSpawns);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, HexConfig.COMMON_CONFIG);

        HexConfig.loadConfig(HexConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("Hexerei-common.toml"));

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

        event.enqueueWork(() -> {
            ModBiomeGeneration.generateBiomes();

            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(ModBlocks.MAHOGANY_LOG.get(), ModBlocks.STRIPPED_MAHOGANY_LOG.get())
                    .put(ModBlocks.MAHOGANY_WOOD.get(), ModBlocks.STRIPPED_MAHOGANY_WOOD.get())
                    .put(ModBlocks.WILLOW_LOG.get(), ModBlocks.STRIPPED_WILLOW_LOG.get())
                    .put(ModBlocks.WILLOW_WOOD.get(), ModBlocks.STRIPPED_WILLOW_WOOD.get()).build();
            ModStructures.setupStructures();
            ModConfiguredStructures.registerConfiguredStructures();

            Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "witch_hut_leg_processor"), WITCH_HUT_LEG_PROCESSOR);
            Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "dark_coven_leg_processor"), DARK_COVEN_LEG_PROCESSOR);
            Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "mangrove_tree_leg_processor"), MANGROVE_TREE_LEG_PROCESSOR);

            HexereiPacketHandler.register();


            ComposterBlock.COMPOSTABLES.put(ModBlocks.WILLOW_VINES.get().asItem(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.WILLOW_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MAHOGANY_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.WILLOW_SAPLING.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MAHOGANY_SAPLING.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MANDRAKE_FLOWER.get().asItem(), 1F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.BELLADONNA_FLOWER.get().asItem(), 1F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MUGWORT_BUSH.get().asItem(), 1F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.YELLOW_DOCK_BUSH.get().asItem(), 1F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.LILY_PAD_BLOCK.get().asItem(), 1F);
            ComposterBlock.COMPOSTABLES.put(ModItems.BELLADONNA_BERRIES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.BELLADONNA_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.MANDRAKE_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.MANDRAKE_ROOT.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.MUGWORT_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.MUGWORT_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_DOCK_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_DOCK_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRIED_BELLADONNA_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRIED_MANDRAKE_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRIED_MUGWORT_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRIED_MUGWORT_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRIED_YELLOW_DOCK_FLOWERS.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRIED_YELLOW_DOCK_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.SAGE.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.SAGE_SEED.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRIED_SAGE.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.TALLOW_IMPURITY.get().asItem(), 0.3F);

        });
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client

        ModKeyBindings.init();
        event.enqueueWork(() -> {

            ItemBlockRenderTypes.setRenderLayer(ModFluids.QUICKSILVER_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.QUICKSILVER_FLOWING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.QUICKSILVER_BLOCK.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModFluids.BLOOD_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.BLOOD_FLOWING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.BLOOD_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.TALLOW_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.TALLOW_FLOWING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.TALLOW_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAHOGANY_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAHOGANY_TRAPDOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAHOGANY_TRAPDOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILLOW_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILLOW_TRAPDOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MIXING_CAULDRON.get(), RenderType.cutoutMipped());

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_BALL.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_BALL_ORB.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_BALL_LARGE_RING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_BALL_SMALL_RING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HERB_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HERB_DRYING_RACK_FULL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HERB_DRYING_RACK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAHOGANY_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILLOW_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MANDRAKE_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BELLADONNA_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MUGWORT_BUSH.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_DOCK_BUSH.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CANDELABRA.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SAGE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LILY_PAD_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILLOW_VINES.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILLOW_VINES_PLANT.get(), RenderType.cutout());

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SELENITE_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SELENITE_CLUSTER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BUDDING_SELENITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_SELENITE_BUD.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_SELENITE_BUD.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_SELENITE_BUD.get(), RenderType.translucent());

            MenuScreens.register(ModContainers.MIXING_CAULDRON_CONTAINER.get(), MixingCauldronScreen::new);
            MenuScreens.register(ModContainers.COFFER_CONTAINER.get(), CofferScreen::new);
            MenuScreens.register(ModContainers.HERB_JAR_CONTAINER.get(), HerbJarScreen::new);
            MenuScreens.register(ModContainers.BROOM_CONTAINER.get(), BroomScreen::new);

        });


    }

//    @SubscribeEvent
//    public static void recipes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
//        register(new Serializer2(), "coffer_dyeing", event.getRegistry());
//    }
//
//    private static <T extends IForgeRegistryEntry<T>> void register(T obj, String name, IForgeRegistry<T> registry) {
//        registry.register(obj.setRegistryName(new ResourceLocation(MOD_ID, name)));
//    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("hexerei", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {

    }


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }

    }


    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerLevel serverLevel){
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
            // Skip superflat to prevent issues with it. Plus, users don't want structures clogging up their superflat worlds.
            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
                return;
            }

            StructureSettings worldStructureConfig = chunkGenerator.getSettings();

            // Create a mutable map we will use for easier adding to biomes
            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap = new HashMap<>();
            HashMap<Feature<?>, HashMultimap<ConfiguredFeature<?, ?>, ResourceKey<Biome>>> featureToMultiMap = new HashMap<>();

            // Add the resourcekey of all biomes that this Configured Structure can spawn in.
            for(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {

                Biome.BiomeCategory biomeCategory = biomeEntry.getValue().getBiomeCategory();

                if(biomeCategory == Biome.BiomeCategory.SWAMP) {
                    associateBiomeToConfiguredStructure(structureToMultiMap, ModConfiguredStructures.CONFIGURED_WITCH_HUT, biomeEntry.getKey());
                    associateBiomeToConfiguredStructure(structureToMultiMap, ModConfiguredStructures.CONFIGURED_DARK_COVEN, biomeEntry.getKey());
                }
            }

            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
            worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !structureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

            // Add our structures to the structure map/multimap and set the world to use this combined map/multimap.
            structureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

            // Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();

            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                Hexerei.LOGGER.error("Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            /*
             * Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also that vanilla superflat is really tricky and buggy to work with in my experience.
             */
            if(chunkGenerator instanceof FlatLevelSource &&
                    serverLevel.dimension().equals(Level.OVERWORLD)){
                return;
            }

            /*
             * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
             * Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
             *
             * NOTE: if you add per-dimension spacing configs, you can't use putIfAbsent as BuiltinRegistries.NOISE_GENERATOR_SETTINGS in FMLCommonSetupEvent
             * already added your default structure spacing to some dimensions. You would need to override the spacing with .put(...)
             * And if you want to do dimension blacklisting, you need to remove the spacing entry entirely from the map below to prevent generation safely.
             */
            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureConfig.structureConfig());
            tempMap.putIfAbsent(ModStructures.WITCH_HUT.get(), StructureSettings.DEFAULTS.get(ModStructures.WITCH_HUT.get()));
            worldStructureConfig.structureConfig = tempMap;
        }
    }

    /**
     * Helper method that handles setting up the map to multimap relationship to help prevent issues.
     */
    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
        structureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = structureToMultiMap.get(configuredStructureFeature.feature);
        if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
            Hexerei.LOGGER.error("""
                    Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
                    This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
                    The two conflicting ConfiguredStructures are: {}, {}
                    The biome that is attempting to be shared: {}
                """,
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
                    biomeRegistryKey
            );
        }
        else{
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        MinecraftForge.EVENT_BUS.register(new SageBurningPlateEvent());
    }

}
