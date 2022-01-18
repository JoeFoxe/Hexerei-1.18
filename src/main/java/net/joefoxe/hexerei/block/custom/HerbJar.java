package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.container.CofferContainer;
import net.joefoxe.hexerei.container.HerbJarContainer;
import net.joefoxe.hexerei.items.JarHandler;
import net.joefoxe.hexerei.tileentity.CofferTile;
import net.joefoxe.hexerei.tileentity.HerbJarTile;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class HerbJar extends Block implements ITileEntity<HerbJarTile>, EntityBlock, SimpleWaterloggedBlock {

    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
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

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, Boolean.valueOf(direction == Direction.UP));
                if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                    return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection());
                }
            }
        }

        return null;
    }

    // hitbox REMEMBER TO DO THIS
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(5, -0.5, 5, 11, 0, 11),
            Block.box(5.5, 13, 5.5, 10.5, 15, 10.5),
            Block.box(4.5, 12, 10.5, 11.5, 14, 11.5),
            Block.box(4.5, 12, 4.5, 11.5, 14, 5.5),
            Block.box(4.5, 12, 5.5, 5.5, 14, 10.5),
            Block.box(10.5, 12, 5.5, 11.5, 14, 10.5),
            Block.box(4, 0, 4, 12, 11, 12),
            Block.box(5, 11, 5, 11, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);

        if ((itemstack.isEmpty() && player.isShiftKeyDown()) || state.getValue(HorizontalDirectionalBlock.FACING).getOpposite() != hit.getDirection()) {

            BlockEntity tileEntity = worldIn.getBlockEntity(pos);

            if(!worldIn.isClientSide()) {
                if (tileEntity instanceof HerbJarTile) {
                    MenuProvider containerProvider = createContainerProvider(worldIn, pos);
                    NetworkHooks.openGui(((ServerPlayer) player), containerProvider, tileEntity.getBlockPos());
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }

            return InteractionResult.SUCCESS;
        }

        BlockEntity tileEntity = worldIn.getBlockEntity(pos);

        if (tileEntity instanceof HerbJarTile) {
            ((HerbJarTile)tileEntity).interactPutItems(player);
        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {

        ItemStack cloneItemStack = getCloneItemStack(world, pos, state);
        if(!world.isClientSide())
            popResource((ServerLevel)world, pos, cloneItemStack);

        super.playerWillDestroy(world, pos, state, player);
    }
    
    protected BlockHitResult rayTraceEyeLevel(Level world, Player player, double length) {
        Vec3 eyePos = player.getEyePosition(1);
        Vec3 lookPos = player.getViewVector(1);
        Vec3 endPos = eyePos.add(lookPos.x * length, lookPos.y * length, lookPos.z * length);
        ClipContext context = new ClipContext(eyePos, endPos, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
        return world.clip(context);
    }

    @Override
    public void attack(BlockState state, Level worldIn, BlockPos pos, Player playerIn) {
        BlockHitResult rayResult = rayTraceEyeLevel(worldIn, playerIn, playerIn.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue() + 1);
        if (rayResult.getType() == HitResult.Type.MISS)
            return;

        Direction side = rayResult.getDirection();

        BlockEntity tile = worldIn.getBlockEntity(pos);
        HerbJarTile herbJarTile = null;
        //System.out.println(worldIn.isClientSide());
        if(tile instanceof  HerbJarTile)
            herbJarTile = (HerbJarTile) tile;
        if (state.getValue(HorizontalDirectionalBlock.FACING).getOpposite() != rayResult.getDirection())
            return;

        ItemStack item;
        if (playerIn.isShiftKeyDown()) {
            item = herbJarTile.takeItems(0, herbJarTile.itemHandler.getStackInSlot(0).getCount());
        }
        else {
            item = herbJarTile.takeItems(0, 1);
        }

        if (!item.isEmpty()) {
            if (!playerIn.inventory.add(item)) {
                dropItemStack(worldIn, pos.relative(side), playerIn, item);
                worldIn.sendBlockUpdated(pos, state, state, 3);
            }
            else
                worldIn.playSound(null, pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, .2f, ((worldIn.random.nextFloat() - worldIn.random.nextFloat()) * .7f + 1) * 2);
        }

        super.attack(state, worldIn, pos, playerIn);
    }

    private void dropItemStack (Level world, BlockPos pos, Player player, @Nonnull ItemStack stack) {
        ItemEntity entity = new ItemEntity(world, pos.getX() + .5f, pos.getY() + .3f, pos.getZ() + .5f, stack);
        Vec3 motion = entity.getDeltaMovement();
        entity.push(-motion.x, -motion.y, -motion.z);
        world.addFreshEntity(entity);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public HerbJar(Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING, HANGING, WATERLOGGED);
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
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

        CompoundTag inv = stack.getOrCreateTag().getCompound("Inventory");
        ListTag tagList = inv.getList("Items", Tag.TAG_COMPOUND);
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("<%s>", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA6600)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            if(tagList.size() >= 1) {
                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            }
            for (int i = 0; i < tagList.size(); i++)
            {
//                CompoundTag itemTags = tagList.getCompound(i);
//                itemTags.putInt("Count", 1);
//                TranslatableComponent itemText = new TranslatableComponent(ItemStack.of(itemTags).getDescriptionId());
//                int countText = Integer.parseInt(String.valueOf(itemTags.get("ExtendedCount")));
//                itemText.append(" x" + countText);
//
//                tooltip.add(itemText);

                CompoundTag itemTags = tagList.getCompound(i);
                itemTags.putInt("Count", 1);
                TranslatableComponent itemText2 = (TranslatableComponent) new TranslatableComponent(ItemStack.of(itemTags).getDescriptionId()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x998800)));
                TranslatableComponent itemText = (TranslatableComponent) new TranslatableComponent(" - %s", itemText2).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999)));
                int countText = Integer.parseInt(String.valueOf(itemTags.get("ExtendedCount")));
                itemText.append(" x" + countText);

                tooltip.add(itemText);
            }
            if(tagList.size() < 1)
            {
                tooltip.add(new TranslatableComponent("tooltip.hexerei.coffer_shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.coffer_shift_2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.coffer_shift_3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_7").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

            }

        } else {
            tooltip.add(new TranslatableComponent("[%s]", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAA00)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

//            tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
//            tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar_shift_5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

            for (int i = 0; i < Math.min(tagList.size(), 1); i++)
            {
                CompoundTag itemTags = tagList.getCompound(i);
                itemTags.putInt("Count", 1);
                TranslatableComponent itemText2 = (TranslatableComponent) new TranslatableComponent(ItemStack.of(itemTags).getDescriptionId()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x998800)));
                TranslatableComponent itemText = (TranslatableComponent) new TranslatableComponent(" - %s", itemText2).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999)));
                int countText = Integer.parseInt(String.valueOf(itemTags.get("ExtendedCount")));
                itemText.append(" x" + countText);

                tooltip.add(itemText);
            }
//            if(tagList.size() > 3) {
//                tooltip.add(new TranslatableComponent(". . . "));
//                tooltip.add(new TranslatableComponent(""));
//                tooltip.add(new TranslatableComponent("Hold \u00A7eSHIFT\u00A7r to see more"));
//            }
//            else
//            if(tagList.size() < 1)
//            {
//
//                tooltip.add(new TranslatableComponent("tooltip.hexerei.herb_jar"));
//            }
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }
    

    @Override
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        ItemStack item = new ItemStack(this);
        Optional<HerbJarTile> tileEntityOptional = Optional.ofNullable(getBlockEntity(worldIn, pos));
//        System.out.println(worldIn.getBlockEntity(pos));e
        CompoundTag tag = item.getOrCreateTag();
        JarHandler empty = tileEntityOptional.map(herb_jar -> herb_jar.itemHandler)
                .orElse(new JarHandler(1,1024));
        CompoundTag inv = tileEntityOptional.map(herb_jar -> herb_jar.itemHandler.serializeNBT())
                .orElse(new CompoundTag());


        if(!empty.getStackInSlot(0).isEmpty())
            tag.put("Inventory", inv);


        Component customName = tileEntityOptional.map(HerbJarTile::getCustomName)
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
            ((HerbJarTile)tileentity).customName = stack.getHoverName();
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
            if(!worldIn.isClientSide() && worldIn instanceof ServerLevel) {
                ItemStack cloneItemStack = getCloneItemStack(worldIn, currentPos, stateIn);
                worldIn.addFreshEntity(new ItemEntity(((ServerLevel) worldIn).getLevel(), currentPos.getX() + 0.5f, currentPos.getY() - 0.5f, currentPos.getZ() + 0.5f, cloneItemStack));
            }
        }

        return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        Direction direction = getBlockConnected(state).getOpposite();
        return Block.canSupportCenter(worldIn, pos.relative(direction), direction.getOpposite());
    }

    protected static Direction getBlockConnected(BlockState state) {
        return state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
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
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
    }

    private MenuProvider createContainerProvider(Level worldIn, BlockPos pos) {
        return new MenuProvider() {
            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                return new HerbJarContainer(i, worldIn, pos, playerInventory, playerEntity);
            }

            @Override
            public Component getDisplayName() {
                if(((HerbJarTile)worldIn.getBlockEntity(pos)).customName != null)
                    return new TranslatableComponent(((HerbJarTile)worldIn.getBlockEntity(pos)).customName.getString());
                return new TranslatableComponent("screen.hexerei.herb_jar");
            }

        };
    }

    @Override
    public Class<HerbJarTile> getTileEntityClass() {
        return HerbJarTile.class;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new HerbJarTile(ModTileEntities.HERB_JAR_TILE.get(), pos, state);
    }
}
