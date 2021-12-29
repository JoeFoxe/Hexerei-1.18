package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.tileentity.*;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CandleDipper extends BaseEntityBlock implements ITileEntity<CandleDipperTile>, EntityBlock, SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    @SuppressWarnings("deprecation")
    @Override
    public RenderShape getRenderShape(BlockState iBlockState) {
        return RenderShape.MODEL;
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

            if (this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) && context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() instanceof MixingCauldron) {
                return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection());
        }
        return null;
    }

    @Override
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState p_49862_) {


        super.destroy(worldIn, pos, p_49862_);
    }

    // hitbox REMEMBER TO DO THIS
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(14, 1, 4, 16, 6, 12),
            Block.box(13, -1, 3.5, 17, 1, 6.5),
            Block.box(13, -1, 9.5, 17, 1, 12.5),
            Block.box(-1, -1, 9.5, 3, 1, 12.5),
            Block.box(0, 1, 4, 2, 6, 12),
            Block.box(-1, -1, 3.5, 3, 1, 6.5),
            Block.box(2, -1, 2, 14, 0, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SHAPE_TURNED = Stream.of(
            Block.box(4, 1, 0, 12, 6, 2),
            Block.box(3.5, -1, -1, 6.5, 1, 3),
            Block.box(9.5, -1, -1, 12.5, 1, 3),
            Block.box(9.5, -1, 13, 12.5, 1, 17),
            Block.box(4, 1, 14, 12, 6, 16),
            Block.box(3.5, -1, 13, 6.5, 1, 17),
            Block.box(2, -1, 2, 14, 0, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return state.getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH || state.getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH ? (SHAPE) : (SHAPE_TURNED);
    }


    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);
        Random random = new Random();
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);

        if (tileEntity instanceof CandleDipperTile) {
            int check = ((CandleDipperTile)tileEntity).interactDipper(player, hit);
//            System.out.println(check);
            return InteractionResult.SUCCESS;
        }


        return InteractionResult.PASS;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public CandleDipper(Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING, WATERLOGGED);
    }

    public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluidStateIn.getType() == Fluids.WATER) {

            worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)), 3);
            worldIn.scheduleTick(pos, fluidStateIn.getType(), fluidStateIn.getType().getTickDelay(worldIn));
            return true;
        } else {
            return false;
        }
    }


    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {


        if(!stateIn.canSurvive(worldIn, currentPos))
        {
            CandleDipperTile te = (CandleDipperTile) worldIn.getBlockEntity(currentPos);

            if(!worldIn.isClientSide()) {
                worldIn.addFreshEntity(new ItemEntity((Level) worldIn, currentPos.getX() + 0.5f, currentPos.getY() - 0.5f, currentPos.getZ() + 0.5f, te.getItem(0)));
                worldIn.addFreshEntity(new ItemEntity((Level) worldIn, currentPos.getX() + 0.5f, currentPos.getY() - 0.5f, currentPos.getZ() + 0.5f, te.getItem(1)));
                worldIn.addFreshEntity(new ItemEntity((Level) worldIn, currentPos.getX() + 0.5f, currentPos.getY() - 0.5f, currentPos.getZ() + 0.5f, te.getItem(2)));
            }
        }

        return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return (worldIn.getBlockState(pos.below()).getBlock() instanceof MixingCauldron);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return !state.getValue(WATERLOGGED);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.candle_dipper_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.candle_dipper"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

//    @Override
//    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
//        super.onBlockExploded(state, world, pos, explosion);
//
//        if (world instanceof ServerLevel) {
//            ItemStack cloneItemStack = getItem(world, pos, state);
//            if (world.getBlockState(pos) != state && !level.isClientSide()) {
//                world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, cloneItemStack));
//            }
//
//        }
//    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
    }

    @Override
    public Class<CandleDipperTile> getTileEntityClass() {
        return CandleDipperTile.class;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CandleDipperTile(ModTileEntities.CANDLE_DIPPER_TILE.get(), pos, state);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType){
        return entityType == ModTileEntities.CANDLE_DIPPER_TILE.get() ?
                (world2, pos, state2, entity) -> ((CandleDipperTile)entity).tick() : null;
    }
}
