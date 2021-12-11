package net.joefoxe.hexerei.world.structure.structures;
//
//import net.joefoxe.hexerei.Hexerei;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.chunk.ChunkGenerator;
//import net.minecraft.world.level.levelgen.GenerationStep;
//import net.minecraft.world.level.levelgen.WorldgenRandom;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
//import net.minecraft.world.level.levelgen.structure.BoundingBox;
//import net.minecraft.core.RegistryAccess;
//import net.minecraft.core.Registry;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.BiomeSource;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.level.levelgen.Heightmap;
//import net.minecraft.world.gen.feature.NoneFeatureConfiguration;
//import net.minecraft.world.gen.feature.jigsaw.JigsawPlacement;
//import net.minecraft.world.gen.feature.structure.PoolElementStructurePiece;
//import net.minecraft.world.level.levelgen.feature.StructureFeature;
//import net.minecraft.world.gen.feature.structure.StructureStart;
//import net.minecraft.world.gen.feature.structure.JigsawConfiguration;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
//
//import static net.minecraft.world.gen.GenerationStage.Decoration;
//
//public class DarkCovenStructure extends StructureFeature<NoneFeatureConfiguration> {
//    public DarkCovenStructure() {
//        super(NoneFeatureConfiguration.CODEC);
//    }
//
//    @Override
//    public GenerationStep.Decoration getDecorationStage() {
//        return GenerationStep.Decoration.SURFACE_STRUCTURES;
//    }
//
//    @Override
//    protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeSource biomeSource,
//                                     long seed, WorldgenRandom chunkRandom, int chunkX, int chunkZ,
//                                     Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig) {
//        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
//        int landHeight = chunkGenerator.getHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
//                Heightmap.Type.WORLD_SURFACE_WG);
//
//        BlockGetter columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.getX(), centerOfChunk.getZ());
//        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.up(landHeight));
//
//        return topBlock.getFluidState().isEmpty();
//    }
//
//    @Override
//    public IStartFactory<NoneFeatureConfiguration> getStartFactory() {
//        return DarkCovenStructure.Start::new;
//    }
//
//    public static class Start extends StructureStart<NoneFeatureConfiguration> {
//        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ,
//                     BoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
//            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
//        }
//
//        @Override // generatePieces
//        public void func_230364_a_(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator,
//                                   StructureManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn,
//                                   NoneFeatureConfiguration config) {
//            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
//            int x = (chunkX << 4) + 7;
//            int z = (chunkZ << 4) + 7;
//            BlockPos blockpos = new BlockPos(x, 0, z);
//
//            //addpieces()
//            JigsawPlacement.addPieces(dynamicRegistryManager,
//                    new JigsawConfiguration(() -> dynamicRegistryManager.getRegistry(Registry.TEMPLATE_POOL_REGISTRY)
//                            .getOrDefault(new ResourceLocation(Hexerei.MOD_ID, "coven/dark_coven/town_centers")),
//                            10), PoolElementStructurePiece::new, chunkGenerator, templateManagerIn,
//                    blockpos, this.components, this.rand,false,true);
//
//            this.components.forEach(piece -> piece.offset(0, 4, 0));
//            this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);
//
//            this.recalculateStructureSize();
//
////            LogManager.getLogger().log(Level.DEBUG, "MangroveTree at " +
////                    this.components.get(0).getBoundingBox().minX + " " +
////                    this.components.get(0).getBoundingBox().minY + " " +
////                    this.components.get(0).getBoundingBox().minZ);
//        }
//    }
//
//}


import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.world.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;

import java.util.List;
import java.util.Optional;

public class DarkCovenStructure extends StructureFeature<JigsawConfiguration> {


    public DarkCovenStructure(Codec<JigsawConfiguration> codec) {
        super(codec, (context) -> {
                    // Check if the spot is valid for structure gen. If false, return nothing to signal to the game to skip this spawn attempt.
                    if (!net.joefoxe.hexerei.world.structure.structures.DarkCovenStructure.isFeatureChunk(context)) {
                        return Optional.empty();
                    }
                    // Create the pieces layout of the structure and give it to
                    else {
                        return net.joefoxe.hexerei.world.structure.structures.DarkCovenStructure.createPiecesGenerator(context);
                    }
                },
                PostPlacementProcessor.NONE);
    }
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

//    private static final Lazy<List<MobSpawnSettings.SpawnerData>> STRUCTURE_MONSTERS = Lazy.of(() -> ImmutableList.of(
//            new MobSpawnSettings.SpawnerData(EntityType.WITCH, 100, 4, 9)
//    ));
//    private static final Lazy<List<MobSpawnSettings.SpawnerData>> STRUCTURE_CREATURES = Lazy.of(() -> ImmutableList.of(
//            new MobSpawnSettings.SpawnerData(EntityType.CAT, 100, 1, 2)
//    ));
//
//    // Hooked up in StructureTutorialMain. You can move this elsewhere or change it up.
//    public static void setupStructureSpawns(final StructureSpawnListGatherEvent event) {
//        if(event.getStructure() == ModStructures.WITCH_HUT.get()) {
//            event.addEntitySpawns(MobCategory.MONSTER, STRUCTURE_MONSTERS.get());
//            event.addEntitySpawns(MobCategory.CREATURE, STRUCTURE_CREATURES.get());
//        }
//    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos blockPos = context.chunkPos().getWorldPosition();

        // Grab height of land. Will stop at first non-air block.
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());

        // Grabs column of blocks at given position. In overworld, this column will be made of stone, water, and air.
        // In nether, it will be netherrack, lava, and air. End will only be endstone and air. It depends on what block
        // the chunk generator will place for that dimension.
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(blockPos.getX(), blockPos.getZ(), context.heightAccessor());

        // Combine the column of blocks with land height and you get the top block itself which you can test.
        BlockState topBlock = columnOfBlocks.getBlock(landHeight);

        // Now we test to make sure our structure is not spawning on water or other fluids.
        // You can do height check instead too to make it spawn at high elevations.
        return topBlock.getFluidState().isEmpty(); //landHeight > 100;
    }


    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        /*
         * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
         * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
         * Then loop through it and look for land with air above it and set blockpos's Y value to it.
         * Make sure to set the final boolean in JigsawPlacement.addPieces to false so
         * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
         */
        // NoiseColumn blockReader = context.chunkGenerator().getBaseColumn(blockpos.getX(), blockpos.getZ(), context.heightAccessor());


        /*
         * The only reason we are using JigsawConfiguration here is because further down, we are using
         * JigsawPlacement.addPieces which requires JigsawConfiguration. However, if you create your own
         * JigsawPlacement.addPieces, you could reduce the amount of workarounds like above that you need
         * and give yourself more opportunities and control over your structures.
         *
         * An example of a custom JigsawPlacement.addPieces in action can be found here:
         * https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.18/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
         */
        JigsawConfiguration newConfig = new JigsawConfiguration(
                // The path to the starting Template Pool JSON file to read.
                //
                // Note, this is "structure_tutorial:run_down_house/start_pool" which means
                // the game will automatically look into the following path for the template pool:
                // "resources/data/structure_tutorial/worldgen/template_pool/run_down_house/start_pool.json"
                // This is why your pool files must be in "data/<modid>/worldgen/template_pool/<the path to the pool here>"
                // because the game automatically will check in worldgen/template_pool for the pools.
                () -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                        .get(new ResourceLocation(Hexerei.MOD_ID, "coven/dark_coven/town_centers")),

                // How many pieces outward from center can a recursive jigsaw structure spawn.
                // Our structure is only 1 piece outward and isn't recursive so any value of 1 or more doesn't change anything.
                // However, I recommend you keep this a decent value like 7 so people can use datapacks to add additional pieces to your structure easily.
                // But don't make it too large for recursive structures like villages or you'll crash server due to hundreds of pieces attempting to generate!
                // Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
                10
        );

        // Create a new context with the new config that has our json pool. We will pass this into JigsawPlacement.addPieces
        PieceGeneratorSupplier.Context<JigsawConfiguration> newContext = new PieceGeneratorSupplier.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.heightAccessor(),
                context.validBiome(),
                context.structureManager(),
                context.registryAccess()
        );

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        newContext, // Used for JigsawPlacement to get all the proper behaviors done.
                        PoolElementStructurePiece::new, // Needed in order to create a list of jigsaw pieces when making the structure's layout.
                        blockpos.above(context.chunkGenerator().getFirstFreeHeight(blockpos.getX(), blockpos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor()) + 5), // Position of the structure. Y value is ignored if last parameter is set to true.
                        false,  // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                        false // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                );
        /*
         * Note, you are always free to make your own JigsawPlacement class and implementation of how the structure
         * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
         * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
         *
         * An example of a custom JigsawPlacement.addPieces in action can be found here (warning, it is using Mojmap mappings):
         * https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.18/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
         */

        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        return structurePiecesGenerator;
    }


}