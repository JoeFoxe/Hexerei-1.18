package net.joefoxe.hexerei.world.processor;


import com.mojang.serialization.Codec;
import net.joefoxe.hexerei.Hexerei;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

/**
 * Dynamically generates support legs below small dungeons.
 * Yellow stained glass is used to mark the corner positions where the legs will spawn for simplicity.
 */
@MethodsReturnNonnullByDefault
public class DarkCovenLegProcessor extends StructureProcessor {
    public static final DarkCovenLegProcessor INSTANCE = new DarkCovenLegProcessor();
    public static final Codec<DarkCovenLegProcessor> CODEC = Codec.unit(() -> INSTANCE);

    @ParametersAreNonnullByDefault
    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader worldReader, BlockPos jigsawPiecePos, BlockPos jigsawPieceBottomCenterPos, StructureTemplate.StructureBlockInfo blockInfoLocal, StructureTemplate.StructureBlockInfo blockInfoGlobal, StructurePlaceSettings structurePlacementData, @Nullable StructureTemplate template) {
        if (blockInfoGlobal.state.getBlock() == Blocks.YELLOW_STAINED_GLASS_PANE) {
            ChunkPos currentChunkPos = new ChunkPos(blockInfoGlobal.pos);
            ChunkAccess currentChunk = worldReader.getChunk(currentChunkPos.x, currentChunkPos.z);
            Random random = structurePlacementData.getRandom(blockInfoGlobal.pos);

            // Always replace the glass itself with mossy cobble
            currentChunk.setBlockState(blockInfoGlobal.pos, Blocks.DARK_OAK_LOG.defaultBlockState(), false);
            blockInfoGlobal = new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos, Blocks.DARK_OAK_LOG.defaultBlockState(), blockInfoGlobal.nbt);

            // Generate vertical pillar down
            BlockPos.MutableBlockPos mutable = blockInfoGlobal.pos.below().mutable();
            BlockState currBlock = worldReader.getBlockState(mutable);
            while (mutable.getY() > 0 && (currBlock.getMaterial() == Material.AIR || currBlock.getMaterial() == Material.WATER || currBlock.getMaterial() == Material.LAVA)) {
                currentChunk.setBlockState(mutable, Blocks.DARK_OAK_LOG.defaultBlockState(), false);
                mutable.move(Direction.DOWN);
                currBlock = worldReader.getBlockState(mutable);
            }
        }

        return blockInfoGlobal;
    }

    protected StructureProcessorType<?> getType() {
        return Hexerei.DARK_COVEN_LEG_PROCESSOR;
    }
}

