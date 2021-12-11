package net.joefoxe.hexerei.world.gen;

import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModFlowerGeneration {

//    public static final PlacedFeature SWAMP_FLOWERS = PlacementUtils.register("swamp_flowers", ModConfiguredFeatures.SWAMP_FLOWERS.placed(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    public static void generateFlowers(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.SWAMP)) {
            List<Supplier<PlacedFeature>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
//
////            base.add(() -> ModConfiguredFeatures.MANDRAKE_FLOWER_CONFIG);

//            base.add(() -> ModPlacements.WILLOW_TREE);
//            base.add(() -> ModConfiguredFeatures.LILY_PAD_CONFIG.placed());
//
//            if(event.getName().toString().matches("hexerei:willow_swamp") ) {
//
//                base.add(() -> ModConfiguredFeatures.SWAMP_FLOWERS);
//
//            }
        }
    }
}
