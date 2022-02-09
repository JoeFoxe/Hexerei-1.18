package net.joefoxe.hexerei.world.gen;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacements {

    public static final PlacedFeature WILLOW_TREE = PlacementUtils.register("willow_tree_placement", ModConfiguredFeatures.WILLOW.placed(worldSurfaceSquaredWithCount(2)));
    public static final PlacedFeature MAHOGANY_TREE = PlacementUtils.register("mahogany_tree_placement", ModConfiguredFeatures.MAHOGANY.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final PlacedFeature FLOWERING_LILYPAD = PlacementUtils.register("lily_pad_placement", ModConfiguredFeatures.LILY_PAD_CONFIG.placed(worldSurfaceSquaredWithCount(4)));
    public static final PlacedFeature SWAMP_FLOWERS = PlacementUtils.register("swamp_flowers_placement", ModConfiguredFeatures.SWAMP_FLOWERS.placed(worldSurfaceSquaredWithCount(1)));
    public static final PlacedFeature SELENITE_GEODE = PlacementUtils.register("selenite_geode", ModConfiguredFeatures.SELENITE_GEODE.placed(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)), BiomeFilter.biome()));

    public static List<PlacementModifier> worldSurfaceSquaredWithCount(int p_195475_) {
        return List.of(CountPlacement.of(p_195475_), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

}
