//package net.joefoxe.hexerei.world.structure.structures;
//
//import net.joefoxe.hexerei.Hexerei;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.resources.ResourceLocation;
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
//import net.minecraft.world.level.levelgen.Heightmap;
//import net.minecraft.world.level.levelgen.feature.StructureFeature;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
//public class MangroveTreeStructure extends StructureFeature<NoneFeatureConfiguration> {
//    public MangroveTreeStructure() {
//        super(NoneFeatureConfiguration.CODEC);
//    }
//
//    @Override
//    public GenerationStep.Decoration step() {
//        return GenerationStep.Decoration.SURFACE_STRUCTURES;
//    }
//
//    @Override
//    protected boolean generate(ChunkGenerator chunkGenerator, BiomeSource biomeSource,
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
//        return MangroveTreeStructure.Start::new;
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
//                            .getOrDefault(new ResourceLocation(Hexerei.MOD_ID, "mangrove_tree/start_pool")),
//                            10), PoolElementStructurePiece::new, chunkGenerator, templateManagerIn,
//                    blockpos, this.components, this.rand,false,true);
//
//            this.components.forEach(piece -> piece.offset(0, 2, 0));
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