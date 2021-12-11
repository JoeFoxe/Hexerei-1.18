//package net.joefoxe.hexerei.world.structure.structures;
//
//import java.util.Random;
//
//import net.minecraft.world.level.*;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.levelgen.structure.BoundingBox;
//import net.minecraft.world.level.WorldGenLevel;
//import net.minecraft.world.level.chunk.ChunkGenerator;
//import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
//
//public class WitchHutPiece extends ScatteredFeaturePiece {
//
//    public WitchHutPiece(Random random, int x, int z) {
//        super(IModStructurePieceType.WITCH_HUT_PIECE, x, 64, z, 7, 7, 9, getRandomHorizontalDirection(random));
//    }
//
//    public WitchHutPiece(StructureManager p_i51340_1_, CompoundTag p_i51340_2_) {
//        super(IModStructurePieceType.WITCH_HUT_PIECE, p_i51340_2_);
//    }
//
//    public boolean postProcess(WorldGenLevel p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
//        return this.updateAverageGroundHeight(p_230383_1_, p_230383_5_, 0);
//    }
//
//    @Override
//    public void postProcess(WorldGenLevel p_192637_, StructureFeatureManager p_192638_, ChunkGenerator p_192639_, Random p_192640_, BoundingBox p_192641_, ChunkPos p_192642_, BlockPos p_192643_) {
//
//    }
//}