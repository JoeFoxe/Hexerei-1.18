package net.joefoxe.hexerei.world.gen;

import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModTreeGeneration {

    public static void generateTrees(final BiomeLoadingEvent event) {
//        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
//        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
//        if(event.getName().toString().matches("hexerei:willow_swamp") ) {
//            List<Supplier<ConfiguredFeature<?, ?>>> base =
//                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);


        if (event.getCategory() == Biome.BiomeCategory.SWAMP || event.getCategory() == Biome.BiomeCategory.PLAINS) {
//            event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> ModConfiguredFeatures.WILLOW.placed());

            event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> ModConfiguredFeatures.MAHOGANY.placed(
                    PlacementUtils.countExtra(0, 0.05F * 10, 1),
                    InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.MAHOGANY_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
                    BiomeFilter.biome()));
        }

//                    PlacementUtils.countExtra(0, 0.05F, 1),
//                    InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
//                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.WILLOW_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
//                    BiomeFilter.biome()));
//            System.out.println("MAKE TREE");
//        }



//        List<Supplier<PlacedFeature>> base =
//                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
////
//            base.add(() -> ModPlacements.WILLOW_TREE);
//                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
//                    .withPlacement(Placement.COUNT_EXTRA.configure(
//                            new AtSurfaceWithExtraConfig(1, 0.5f, 2))));
//
//        }
//        if(types.contains(BiomeDictionary.Type.JUNGLE)) {
//            List<Supplier<ConfiguredFeature<?, ?>>> base =
//                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
//
//            base.add(() -> ModConfiguredFeatures.MAHOGANY
//                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
//                    .withPlacement(Placement.COUNT_EXTRA.configure(
//                            new AtSurfaceWithExtraConfig(0, 0.25f, 2))));
//
//        }

    }
}