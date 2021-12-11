package net.joefoxe.hexerei.world.structure.structures;

import com.mojang.serialization.Codec;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.*;

public class HexereiAbstractTreeFeature extends Feature<TreeConfiguration> {

    private static final ResourceLocation WILLOW_TREE1 = new ResourceLocation("hexerei:willow_tree1");
    private static final ResourceLocation WILLOW_TREE2 = new ResourceLocation("hexerei:willow_tree2");
    private static final ResourceLocation WILLOW_TREE3 = new ResourceLocation("hexerei:willow_tree3");
    private static final ResourceLocation[] WILLOW_TREE = new ResourceLocation[]{WILLOW_TREE1, WILLOW_TREE2, WILLOW_TREE3};

    public HexereiAbstractTreeFeature(Codec codec) {
        super(codec);
    }

    public static boolean isAirOrLeavesAt(LevelSimulatedReader reader, BlockPos pos) {
        return reader.isStateAtPosition(pos, (state) -> {
            return state.isAir() || state.is(BlockTags.LEAVES);
        });
    }

    public static boolean isAirOrLeavesOrLogsAt(LevelSimulatedReader reader, BlockPos pos) {
        return reader.isStateAtPosition(pos, (state) -> {
            return state.isAir() || state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS);
        });
    }

    private static boolean isDirtOrFarmlandAt(LevelSimulatedReader reader, BlockPos pos) {
        return reader.isStateAtPosition(pos, (state) -> {
            Block block = state.getBlock();
            return isDirt(state) || block == Blocks.FARMLAND;
        });
    }
    //    (WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, TreeConfiguration config)
    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel reader = context.level();
        TreeConfiguration config = context.config();
        BlockPos pos = context.origin();
        Random rand = context.random();


        int i = rand.nextInt(WILLOW_TREE.length);

        if (!isDirtOrFarmlandAt(reader, pos.below()))
            return false;

        for(int j = 0; j < 8; j++) {

            BlockPos upPos = new BlockPos(pos).above();
            for(int k = 0; k < j; k++)
            {
                upPos = upPos.above();
            }

            if (!isAirOrLeavesOrLogsAt(reader, upPos)) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.north())) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.south())) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.east())) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.east().north())) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.east().south())) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.west())) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.west().north())) {
                return false;
            }
            if (!isAirOrLeavesOrLogsAt(reader, upPos.west().south())) {
                return false;
            }
        }


        if (isAirOrLeavesOrLogsAt(reader, pos.below().north()))
            return false;
        if (isAirOrLeavesOrLogsAt(reader, pos.below().south()))
            return false;
        if (isAirOrLeavesOrLogsAt(reader, pos.below().east()))
            return false;
        if (isAirOrLeavesOrLogsAt(reader, pos.below().west()))
            return false;

        BlockRotProcessor BlockRotProcessor = new BlockRotProcessor(0.9F);

        StructureManager templatemanager = reader.getLevel().getServer().getStructureManager();
        StructureTemplate template = templatemanager.getOrCreate(WILLOW_TREE[i]);
        System.out.println(template);

        if (template == null) {
            Hexerei.LOGGER.error("Identifier to the specified nbt file was not found! : {}", WILLOW_TREE[i]);
            return false;
        }
        Rotation rotation = Rotation.getRandom(rand);

        // For proper offsetting the feature so it rotate properly around position parameter.
        BlockPos halfLengths = new BlockPos(
                template.getSize().getX() / 2,
                template.getSize().getY() / 2,
                template.getSize().getZ() / 2);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);

        StructurePlaceSettings placementsettings = (new StructurePlaceSettings()).setRotation(rotation).setRotationPivot(halfLengths).setIgnoreEntities(false);
        Optional<StructureProcessorList> processor = reader.getLevel().getServer().registryAccess().registry(Registry.PROCESSOR_LIST_REGISTRY).get().getOptional(
                new ResourceLocation(Hexerei.MOD_ID, "mangrove_tree/mangrove_tree_legs"));
        processor.orElse(ProcessorLists.EMPTY).list().forEach(placementsettings::addProcessor); // add all processors

        BlockPos pos1 = mutable.set(pos).move(-halfLengths.getX(), 0, -halfLengths.getZ());
        template.placeInWorld(reader, pos1, pos1, placementsettings, rand, 2);

        return true;
    }

}