package net.joefoxe.hexerei.world.gen;

import com.google.common.collect.ImmutableList;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<TreeConfiguration, ?> MAHOGANY =
            register("mahogany", Feature.TREE.configured((
                    new TreeConfiguration.TreeConfigurationBuilder(

                            BlockStateProvider.simple(ModBlocks.MAHOGANY_LOG.get().defaultBlockState()),
                            new FancyTrunkPlacer(6, 8, 1),
                            BlockStateProvider.simple(ModBlocks.MAHOGANY_LEAVES.get().defaultBlockState()),
                            new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2)),
                            new ThreeLayersFeatureSize(2,2, 1, 1,1, OptionalInt.of(2)))).build()));



    public static final ConfiguredFeature<RandomPatchConfiguration, ?> LILY_PAD_CONFIG = FeatureUtils.register("patch_flower_waterlily", Feature.RANDOM_PATCH.configured(
                    new RandomPatchConfiguration(10, 7, 3, () -> Feature.SIMPLE_BLOCK.configured(
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LILY_PAD_BLOCK.get().defaultBlockState()))).onlyWhenEmpty())));

    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> SWAMP_FLOWERS = FeatureUtils.register("swamp_flowers", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(() -> {
//        return Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.YELLOW_DOCK_BUSH.get().defaultBlockState()))))).placed();
        return Feature.RANDOM_PATCH.configured(
                new RandomPatchConfiguration(10, 7, 3, () -> Feature.SIMPLE_BLOCK.configured(
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.YELLOW_DOCK_BUSH.get().defaultBlockState()))).onlyWhenEmpty())).placed();

    }, () -> {
//        return Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MUGWORT_BUSH.get().defaultBlockState()))))).placed();

        return Feature.RANDOM_PATCH.configured(
                new RandomPatchConfiguration(10, 7, 3, () -> Feature.SIMPLE_BLOCK.configured(
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MUGWORT_BUSH.get().defaultBlockState()))).onlyWhenEmpty())).placed();
    }, () -> {
//        return Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BELLADONNA_FLOWER.get().defaultBlockState()))))).placed();

        return Feature.RANDOM_PATCH.configured(
                new RandomPatchConfiguration(10, 7, 3, () -> Feature.SIMPLE_BLOCK.configured(
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BELLADONNA_FLOWER.get().defaultBlockState()))).onlyWhenEmpty())).placed();
    }, () -> {
//        return Feature.NO_BONEMEAL_FLOWER.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MANDRAKE_FLOWER.get().defaultBlockState()))))).placed();

        return Feature.RANDOM_PATCH.configured(
                new RandomPatchConfiguration(10, 7, 3, () -> Feature.SIMPLE_BLOCK.configured(
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MANDRAKE_FLOWER.get().defaultBlockState()))).onlyWhenEmpty())).placed();
    }))));

//    public static ConfiguredFeature<TreeConfiguration, ?> WILLOW;

    public static final ConfiguredFeature<TreeConfiguration, ?> WILLOW =
            register("willow_tree", ModFeatures.WILLOW_TREE.get().configured((
                new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.WILLOW_LOG.get().defaultBlockState()),
                    new StraightTrunkPlacer(6, 4, 0),
                    BlockStateProvider.simple(ModBlocks.WILLOW_LEAVES.get().defaultBlockState()),
                    new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)),
                    new TwoLayersFeatureSize(1, 0, 1)



            )).decorators(ImmutableList.of(new CocoaDecorator(0.2F), TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).ignoreVines().build()));


//TwoLayerFeature(int limit, int lowerSize, int upperSize)
//ThreeLayersFeatureSize(int limit, int upperLimit, int lowerSize, int middleSize, int upperSize, OptionalInt size)

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key,
                                                                                       ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
//    public static void registerConfiguredFeatures() {
//        WILLOW = ModFeatures.WILLOW_TREE.get().configured((
//                new TreeConfiguration.TreeConfigurationBuilder(
//                        BlockStateProvider.simple(ModBlocks.WILLOW_LOG.get().defaultBlockState()),
//                        new StraightTrunkPlacer(4, 2, 0),
//                        BlockStateProvider.simple(ModBlocks.WILLOW_LEAVES.get().defaultBlockState()),
//                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
//                        new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
//        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Hexerei.MOD_ID,"willow_tree"), WILLOW);
//
//    }


}