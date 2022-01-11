package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.config.HexConfig;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.joefoxe.hexerei.tileentity.SageBurningPlateTile;
import net.joefoxe.hexerei.tileentity.SageBurningPlateTile;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import java.util.Random;
import java.util.stream.Stream;

public class SageBurningPlate extends Block implements ITileEntity<SageBurningPlateTile>, EntityBlock, SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final IntegerProperty MODE = IntegerProperty.create("mode", 0, 3);

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(LIT, false).setValue(MODE, 0);
    }

    // hitbox REMEMBER TO DO THIS
    public static final VoxelShape SHAPE = Block.box(2, 0, 5, 14, 1, 11);

    public static final VoxelShape SHAPE_TURNED = Block.box(5, 0, 2, 11, 1, 14);

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        if (p_220053_1_.getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST || p_220053_1_.getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST)
            return SHAPE_TURNED;
        return SHAPE;
    }


    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);

        ItemStack itemstack = player.getItemInHand(handIn);
        Random random = new Random();
        if (tileEntity instanceof SageBurningPlateTile) {
            if(itemstack.getItem() == Items.FLINT_AND_STEEL)
            {
                if (((SageBurningPlateTile) tileEntity).getItems().get(0).is(ModItems.DRIED_SAGE_BUNDLE.get()) && !state.getValue(LIT)) {

                    worldIn.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
                    worldIn.playSound((Player) null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 1.0F);
                    itemstack.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(handIn));

                    return InteractionResult.sidedSuccess(worldIn.isClientSide());
                }
                else
                    return InteractionResult.PASS;

            } else if(itemstack.isEmpty() && !player.isShiftKeyDown())
            {
                worldIn.setBlock(pos, state.setValue(MODE, state.getValue(MODE) + 1 > 3 ? 0 : state.getValue(MODE) + 1), 11);
                state = worldIn.getBlockState(pos);
                String s = "display.hexerei.sage_plate_toggle_" + String.valueOf(state.getValue(MODE));
                player.displayClientMessage(new TranslatableComponent(s), true);

            }
            else
                ((SageBurningPlateTile)tileEntity).interactSageBurningPlate(player, hit);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


    public SageBurningPlate(Properties properties) {
        super(properties.noOcclusion());
        this.withPropertiesOf(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING, WATERLOGGED, LIT, MODE);
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
            String string = "\u00A7e" + HexConfig.SAGE_BURNING_PLATE_RANGE.get() + "\u00A7r";
            tooltip.add(new TranslatableComponent("tooltip.hexerei.sage_burning_plate_shift_1"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.sage_burning_plate_shift_2"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.sage_burning_plate_shift_3", string));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.sage_burning_plate_shift_4"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.sage_burning_plate_shift_5"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.sage_burning_plate_shift_6"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.sage_burning_plate"));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.shift_for_info"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        SageBurningPlateTile te = (SageBurningPlateTile) worldIn.getBlockEntity(pos);

        if(!te.getItems().get(0).isEmpty())
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, te.getItems().get(0)));

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    public Class<SageBurningPlateTile> getTileEntityClass() {
        return SageBurningPlateTile.class;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SageBurningPlateTile(ModTileEntities.SAGE_BURNING_PLATE_TILE.get(), pos, state);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType){
        return entityType == ModTileEntities.SAGE_BURNING_PLATE_TILE.get() ?
                (world2, pos, state2, entity) -> ((SageBurningPlateTile)entity).tick() : null;
    }
}
