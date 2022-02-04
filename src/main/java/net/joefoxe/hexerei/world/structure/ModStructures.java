package net.joefoxe.hexerei.world.structure;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.joefoxe.hexerei.Hexerei;
//import net.joefoxe.hexerei.world.structure.structures.DarkCovenStructure;
//import net.joefoxe.hexerei.world.structure.structures.GenericJigsawStructure;
//import net.joefoxe.hexerei.world.structure.structures.MangroveTreeStructure;
//import net.joefoxe.hexerei.world.structure.structures.WitchHutStructure;
import net.joefoxe.hexerei.config.HexConfig;
import net.joefoxe.hexerei.world.structure.structures.DarkCovenStructure;
import net.joefoxe.hexerei.world.structure.structures.WitchHutStructure;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Hexerei.MOD_ID);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> WITCH_HUT = STRUCTURES.register("witch_hut", () -> (new WitchHutStructure(JigsawConfiguration.CODEC)));
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> DARK_COVEN = STRUCTURES.register("dark_coven", () -> (new DarkCovenStructure(JigsawConfiguration.CODEC)));


//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> WITCH_HUT =
//            STRUCTURES.register("witch_hut", WitchHutStructure::new);
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANGROVE_TREE =
//            STRUCTURES.register("mangrove_tree", MangroveTreeStructure::new);
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> DARK_COVEN =
//            STRUCTURES.register("dark_coven", DarkCovenStructure::new);

//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> DARK_COVEN = addToStructureMaps("dark_coven", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(Hexerei.MOD_ID, "coven/dark_coven/town_centers")).setStructureSize(6).setBiomeRange(1).setStructureBlacklistRange(6).build()));

    private static <T extends StructureFeature<?>> RegistryObject<T> addToStructureMaps(String name, Supplier<T> structure) {
        return STRUCTURES.register(name, structure);
    }

    /* average distance apart in chunks between spawn attempts */
    /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/
    /* this modifies the seed of the structure so no two structures always spawn over each-other.
    Make this large and unique. */
    public static void setupStructures() {
        setupMapSpacingAndLand(WITCH_HUT.get(),
                new StructureFeatureConfiguration(HexConfig.WITCH_HUT_SPACING.get(),HexConfig.WITCH_HUT_SEPARATION.get(), 1234567890), // Spacing and Separation config
                false);
//        setupMapSpacingAndLand(MANGROVE_TREE.get(),
//                new StructureFeatureConfiguration(3,1, 1234567890),
//                false);
        setupMapSpacingAndLand(DARK_COVEN.get(),
                new StructureFeatureConfiguration(HexConfig.DARK_COVEN_SPACING.get(),HexConfig.DARK_COVEN_SEPARATION.get(), 1418987890),
                false);
    }

    /**
     * Adds the provided structure to the registry, and adds the separation settings.
     * The rarity of the structure is determined based on the values passed into
     * this method in the structureSeparationSettings argument.
     * This method is called by setupStructures above.
     **/
    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(F structure, StructureFeatureConfiguration structureSeparationSettings,
                                                                       boolean transformSurroundingLand) {
        //add our structures into the map in StructureFeature class
        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        /*
         * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
         * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         *
         */
        if(transformSurroundingLand){
            StructureFeature.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        /*
         * This is the map that holds the default spacing of all structures.
         * Always add your structure to here so that other mods can utilize it if needed.
         *
         * However, while it does propagate the spacing to some correct dimensions from this map,
         * it seems it doesn't always work for code made dimensions as they read from this list beforehand.
         *
         * Instead, we will use the WorldEvent.Load event in ModWorldEvents to add the structure
         * spacing from this list into that dimension or to do dimension blacklisting properly.
         * We also use our entry in StructureSettings.DEFAULTS in WorldEvent.Load as well.
         *
         * DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        StructureSettings.DEFAULTS =
                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                        .putAll(StructureSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();

        /*
         * There are very few mods that relies on seeing your structure in the
         * noise settings registry before the world is made.
         *
         * You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead of the
         * NOISE_GENERATOR_SETTINGS loop below but that field only applies for the default overworld and
         * won't add to other worldtypes or dimensions (like amplified or Nether).
         * So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop
         * below instead if you must.
         */
        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap =
                    settings.getValue().structureSettings().structureConfig;
            /*
             * Pre-caution in case a mod makes the structure map immutable like datapacks do.
             * I take no chances myself. You never know what another mods does...
             *
             * structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
             */
            if (structureMap instanceof ImmutableMap) {
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig();

            } else {
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }

    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }
}