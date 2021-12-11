package net.joefoxe.hexerei.world.gen;

import net.joefoxe.hexerei.world.structure.ModStructures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.SWAMP)) {
//            List<Supplier<ConfiguredStructureFeature<?, ?>>> structures = event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES);
//
//            structures.add(() -> ModStructures.WITCH_HUT.get().configured(FeatureConfiguration.NONE));
//            structures.add(() -> ModStructures.MANGROVE_TREE.get().configured(FeatureConfiguration.NONE));
//            structures.add(() -> ModStructures.DARK_COVEN.get().configured(FeatureConfiguration.NONE));
        }
    }
}