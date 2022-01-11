package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.tileentity.DryingRackTile;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.joefoxe.hexerei.tileentity.PestleAndMortarTile;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class PestleAndMortar extends Block implements ITileEntity<PestleAndMortarTile>, EntityBlock, SimpleWaterloggedBlock {


    public static final IntegerProperty ANGLE = IntegerProperty.create("angle", 0, 180);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

//    @SuppressWarnings("deprecation")
//    @Override
//    public RenderShape getRenderShape(BlockState iBlockState) {
//        return RenderShape.MODEL;
//    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection()).setValue(ANGLE, 0).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }


    // hitbox REMEMBER TO DO THIS
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(6, 0, 10, 10, 1, 11),
            Block.box(6, 0, 5, 10, 1, 6),
            Block.box(5, 0, 6, 6, 1, 10),
            Block.box(10, 0, 6, 11, 1, 10),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(11, 3, 5, 12, 6, 11),
            Block.box(4, 3, 5, 5, 6, 11),
            Block.box(5, 3, 11, 11, 6, 12),
            Block.box(5, 3, 4, 11, 6, 5),
            Block.box(12, 6, 5, 13, 7, 11),
            Block.box(11, 6, 4, 12, 7, 5),
            Block.box(4, 6, 4, 5, 7, 5),
            Block.box(4, 6, 11, 5, 7, 12),
            Block.box(11, 6, 11, 12, 7, 12),
            Block.box(3, 6, 5, 4, 7, 11),
            Block.box(5, 6, 3, 11, 7, 4),
            Block.box(5, 6, 12, 11, 7, 13),
            Block.box(5, 2, 5, 11, 3, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE;
    }


    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);

        if (tileEntity instanceof PestleAndMortarTile) {
            ((PestleAndMortarTile)tileEntity).interactPestleAndMortar(player, hit);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


    public PestleAndMortar(Properties properties) {
        super(properties.noOcclusion());
        this.withPropertiesOf(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING, ANGLE, WATERLOGGED);
    }

    public void setAngle(Level worldIn, BlockPos pos, BlockState state, int angle) {
        worldIn.setBlock(pos, state.setValue(ANGLE, Integer.valueOf(Mth.clamp(angle, 0, 180))), 2);
    }

    public int getAngle(Level worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getValue(ANGLE);
    }

//
//    @SuppressWarnings("deprecation")
//    @Override
//    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
//        return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
//    }
//
//    @SuppressWarnings("deprecation")
//    @Override
//    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
//        return canSupportCenter(worldIn, pos.below(), Direction.UP);
//    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.pestle_and_mortar_shift_1"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.pestle_and_mortar_shift_2"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.pestle_and_mortar_shift_3"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.pestle_and_mortar_shift_4"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.pestle_and_mortar_1"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.pestle_and_mortar_2"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        PestleAndMortarTile te = (PestleAndMortarTile) worldIn.getBlockEntity(pos);

        if(!te.getItems().get(0).isEmpty())
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, te.getItems().get(0)));
        if(!te.getItems().get(1).isEmpty())
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, te.getItems().get(1)));
        if(!te.getItems().get(2).isEmpty())
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, te.getItems().get(2)));
        if(!te.getItems().get(3).isEmpty())
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, te.getItems().get(3)));
        if(!te.getItems().get(4).isEmpty())
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, te.getItems().get(4)));
        if(!te.getItems().get(5).isEmpty())
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, te.getItems().get(5)));

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    public Class<PestleAndMortarTile> getTileEntityClass() {
        return PestleAndMortarTile.class;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PestleAndMortarTile(ModTileEntities.PESTLE_AND_MORTAR_TILE.get(), pos, state);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType){
        return entityType == ModTileEntities.PESTLE_AND_MORTAR_TILE.get() ?
                (world2, pos, state2, entity) -> ((PestleAndMortarTile)entity).tick() : null;
    }
}
