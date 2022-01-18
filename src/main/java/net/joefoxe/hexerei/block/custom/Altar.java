package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.tileentity.BookOfShadowsAltarTile;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.*;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class Altar extends Block implements ITileEntity<BookOfShadowsAltarTile>, EntityBlock, SimpleWaterloggedBlock {


    public static final IntegerProperty ANGLE = IntegerProperty.create("angle", 0, 180);
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
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection()).setValue(ANGLE, 0).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    // hitbox REMEMBER TO DO THIS
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(11, 0, 11, 16, 1, 16),
            Block.box(0, 12, 0, 16, 16, 16),
            Block.box(1, 1, 12, 4, 12, 15),
            Block.box(1, 7, 4, 4, 10, 12),
            Block.box(12, 7, 4, 15, 10, 12),
            Block.box(4, 6, 1, 12, 9, 4),
            Block.box(4, 6, 12, 12, 9, 15),
            Block.box(1, 1, 1, 4, 12, 4),
            Block.box(12, 1, 1, 15, 12, 4),
            Block.box(12, 1, 12, 15, 12, 15),
            Block.box(0, 0, 11, 5, 1, 16),
            Block.box(0, 0, 0, 5, 1, 5),
            Block.box(11, 0, 0, 16, 1, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_){
        return SHAPE;
    }

//    @SuppressWarnings("deprecation")
//    @Override
//    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
//        ItemStack itemstack = player.getItemInHand(handIn);
//        if(!worldIn.isClientSide()) {
//
//            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
//
//            if(tileEntity instanceof BookOfShadowsAltarTile) {
//                MenuProvider containerProvider = createContainerProvider(worldIn, pos);
//
//                NetworkHooks.openGui(((ServerPlayer)player), containerProvider, tileEntity.getPos());
//
//            } else {
//                throw new IllegalStateException("Our Container provider is missing!");
//            }
//        }
//        return InteractionResult.SUCCESS;
//    }

    public Altar(Properties properties) {

        super(properties.noOcclusion());
    }

    @SuppressWarnings("deprecation")
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

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, facing, facingState, world, pos, facingPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return !state.getValue(WATERLOGGED);
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

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
//
//        //world.addParticle(ParticleTypes.ENCHANT, pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.035d ,(rand.nextDouble() - 0.5d) / 50d);
//
//        super.animateTick(state, world, pos, rand);
//    }
//
//    private MenuProvider createContainerProvider(Level worldIn, BlockPos pos) {
//        return new MenuProvider() {
//            @Override
//            public Component getDisplayName() {
//                if(((BookOfShadowsAltarTile)worldIn.getBlockEntity(pos)).customName != null)
//                    return new TranslatableComponent(((BookOfShadowsAltarTile)worldIn.getBlockEntity(pos)).customName.getString());
//                return new TranslatableComponent("screen.hexerei.coffer");
//            }
//
//            @Nullable
//            @Override
//            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
//                return new CofferContainer(i, worldIn, pos, playerInventory, playerEntity);
//            }
//        };
//    }
//

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("<%s>", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA6600)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.altar_shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
        } else {
            tooltip.add(new TranslatableComponent("[%s]", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAA00)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @Override
    public Class<BookOfShadowsAltarTile> getTileEntityClass() {
        return BookOfShadowsAltarTile.class;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType){
        return entityType == ModTileEntities.BOOK_OF_SHADOWS_ALTAR_TILE.get() ?
                (world2, pos, state2, entity) -> ((BookOfShadowsAltarTile)entity).tick() : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BookOfShadowsAltarTile(ModTileEntities.BOOK_OF_SHADOWS_ALTAR_TILE.get(), pos, state);
    }
}
