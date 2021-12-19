package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.container.CofferContainer;
import net.joefoxe.hexerei.tileentity.CofferTile;
import net.joefoxe.hexerei.tileentity.ModTileEntities;import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.MenuProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.*;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Coffer extends BaseEntityBlock implements ITileEntity<CofferTile>, EntityBlock, SimpleWaterloggedBlock {


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
            Block.box(2, 0, 4, 14, 4, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SHAPE_TURNED = Stream.of(
            Block.box(4, 0, 2, 12, 4, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_){
        if (p_220053_1_.getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST || p_220053_1_.getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST)
            return SHAPE_TURNED;
        return SHAPE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType){
        return entityType == ModTileEntities.COFFER_TILE.get() ?
                (world2, pos, state2, entity) -> ((CofferTile)entity).tick() : null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);
        if(!worldIn.isClientSide()) {

            BlockEntity tileEntity = worldIn.getBlockEntity(pos);

            if(tileEntity instanceof CofferTile) {
                MenuProvider containerProvider = createContainerProvider(worldIn, pos);

                NetworkHooks.openGui(((ServerPlayer)player), containerProvider, tileEntity.getBlockPos());

            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    public Coffer(Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
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


    @Override
    public void attack(BlockState state, Level world, BlockPos pos, Player player) {
        if (player instanceof FakePlayer)
            return;
        if (world instanceof ServerLevel) {
            ItemStack cloneItemStack = getCloneItemStack(world, pos, state);
            world.destroyBlock(pos, false);
            if (world.getBlockState(pos) != state && !world.isClientSide()) {
                if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.AIR)
                    player.setItemInHand(InteractionHand.MAIN_HAND,cloneItemStack);
                else
                    player.getInventory().placeItemBackInInventory(cloneItemStack);
            }

        }
    }

    @Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
        super.onBlockExploded(state, world, pos, explosion);

        if (world instanceof ServerLevel) {
            ItemStack cloneItemStack = getCloneItemStack(world, pos, state);
            if (world.getBlockState(pos) != state && !world.isClientSide()) {
                world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, cloneItemStack));
            }

        }
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
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        ItemStack item = new ItemStack(this);
        Optional<CofferTile> tileEntityOptional = Optional.ofNullable(getBlockEntity(worldIn, pos));

        CompoundTag tag = item.getOrCreateTag();
        CompoundTag inv = tileEntityOptional.map(coffer -> coffer.itemHandler.serializeNBT())
                .orElse(new CompoundTag());

        if(!tileEntityOptional.map(RandomizableContainerBlockEntity::isEmpty).orElse(true))
            tag.put("Inventory", inv);


        Component customName = tileEntityOptional.map(CofferTile::getCustomName)
                .orElse(null);

        if (customName != null)
            if(customName.getString().length() > 0)
                item.setHoverName(customName);
        return item;
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasCustomHoverName()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            ((CofferTile)tileentity).customName = stack.getHoverName();
        }

        if (worldIn.isClientSide())
            return;
        if (stack == null)
            return;
        withTileEntityDo(worldIn, pos, te -> {
            te.readInventory(stack.getOrCreateTag()
                    .getCompound("Inventory"));
        });

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

        CompoundTag inv = stack.getOrCreateTag().getCompound("Inventory");
        ListTag tagList = inv.getList("Items", Tag.TAG_COMPOUND);
        if(Screen.hasShiftDown()) {

            tooltip.add(new TranslatableComponent("\u00A7e--------------\u00A7r"));
            for (int i = 0; i < tagList.size(); i++)
            {
                CompoundTag itemTags = tagList.getCompound(i);

                TranslatableComponent itemText = new TranslatableComponent(ItemStack.of(itemTags).getDescriptionId());
                int countText = ItemStack.of(itemTags).getCount();
                itemText.append(" x" + countText);

                tooltip.add(itemText);
            }
            if(tagList.size() < 1)
            {
                tooltip.add(new TranslatableComponent("Can be placed in the world."));
                tooltip.add(new TranslatableComponent("Can store items and be moved like a shulker box."));
                tooltip.add(new TranslatableComponent("Can be renamed."));
                tooltip.add(new TranslatableComponent("Punch the Coffer to pick up directly to your inventory."));
            }

        } else {
//            tooltip.add(new TranslatableComponent("tooltip.hexerei.coffer"));
            tooltip.add(new TranslatableComponent("\u00A7e--------------\u00A7r"));

            for (int i = 0; i < Math.min(tagList.size(), 3); i++)
            {
                CompoundTag itemTags = tagList.getCompound(i);

                TranslatableComponent itemText = new TranslatableComponent(ItemStack.of(itemTags).getDescriptionId());
                int countText = ItemStack.of(itemTags).getCount();
                itemText.append(" x" + countText);

                tooltip.add(itemText);
            }
            if(tagList.size() > 3) {
                tooltip.add(new TranslatableComponent(". . . "));
                tooltip.add(new TranslatableComponent(""));
                tooltip.add(new TranslatableComponent("Hold \u00A7eSHIFT\u00A7r to see more"));
            }
            else if(tagList.size() < 1)
            {

                tooltip.add(new TranslatableComponent("tooltip.hexerei.coffer"));
            }
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {

        //world.addParticle(ParticleTypes.ENCHANT, pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.035d ,(rand.nextDouble() - 0.5d) / 50d);

        super.animateTick(state, world, pos, rand);
    }

    private MenuProvider createContainerProvider(Level worldIn, BlockPos pos) {
        return new MenuProvider() {
            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                return new CofferContainer(i, worldIn, pos, playerInventory, playerEntity);
            }

            @Override
            public Component getDisplayName() {
                if(((CofferTile)worldIn.getBlockEntity(pos)).customName != null)
                    return new TranslatableComponent(((CofferTile)worldIn.getBlockEntity(pos)).customName.getString());
                return new TranslatableComponent("screen.hexerei.coffer");
            }

        };
    }

//    @Nullable
//    @Override
//    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
//        BlockEntity te = ModTileEntities.COFFER_TILE.get().create();
//        return te;
//    }
//
//    @Override
//    public boolean hasBlockEntity(BlockState state) {
//        return true;
//    }
    @Override
    public Class<net.joefoxe.hexerei.tileentity.CofferTile> getTileEntityClass() {
        return CofferTile.class;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CofferTile(ModTileEntities.COFFER_TILE.get(), pos, state);
    }

}
