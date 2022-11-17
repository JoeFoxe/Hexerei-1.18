package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.tileentity.*;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.*;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class Candle extends BaseEntityBlock implements ITileEntity<CandleTile>, EntityBlock, SimpleWaterloggedBlock {

    public static final IntegerProperty CANDLES = IntegerProperty.create("candles", 1, 4);
    public static final IntegerProperty CANDLES_LIT = IntegerProperty.create("candles_lit", 0, 4);
    public static final IntegerProperty SLOT_ONE_TYPE = IntegerProperty.create("slot_one_type", 0, 10);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty PLAYER_NEAR = BooleanProperty.create("player_near");

    protected static final VoxelShape ONE_SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 9.0D, 10.0D);
    protected static final VoxelShape TWO_SHAPE = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 9.0D, 12.5D);
    protected static final VoxelShape THREE_SHAPE = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 9.0D, 12.5D);
    protected static final VoxelShape FOUR_SHAPE = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 9.0D, 12.5D);
    @SuppressWarnings("deprecation")
    @Override
    public RenderShape getRenderShape(BlockState iBlockState) {
        return RenderShape.MODEL;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        int candleType = 1;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_BLUE.get().asItem())
            candleType = 2;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_BLACK.get().asItem())
            candleType = 3;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_LIME.get().asItem())
            candleType = 4;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_ORANGE.get().asItem())
            candleType = 5;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_PINK.get().asItem())
            candleType = 6;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_PURPLE.get().asItem())
            candleType = 7;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_RED.get().asItem())
            candleType = 8;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_CYAN.get().asItem())
            candleType = 9;
        if(context.getItemInHand().getItem() == ModBlocks.CANDLE_YELLOW.get().asItem())
            candleType = 10;
        if (blockstate.is(ModBlocks.CANDLE.get())
                || blockstate.is(ModBlocks.CANDLE_BLUE.get())
                || blockstate.is(ModBlocks.CANDLE_BLACK.get())
                || blockstate.is(ModBlocks.CANDLE_LIME.get())
                || blockstate.is(ModBlocks.CANDLE_ORANGE.get())
                || blockstate.is(ModBlocks.CANDLE_PINK.get())
                || blockstate.is(ModBlocks.CANDLE_PURPLE.get())
                || blockstate.is(ModBlocks.CANDLE_RED.get())
                || blockstate.is(ModBlocks.CANDLE_CYAN.get())
                || blockstate.is(ModBlocks.CANDLE_YELLOW.get())) {


            if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof CandleTile)
            {
                CandleTile tile = (CandleTile) context.getLevel().getBlockEntity(context.getClickedPos());
                if(tile.candleType2 == 0) {
                    tile.candleType2 = candleType;
                    tile.candleHeight2 = 7;
                    tile.candleMeltTimer2 = tile.candleMeltTimerMAX;
                }
                else if(tile.candleType3 == 0) {
                    tile.candleType3 = candleType;
                    tile.candleHeight3 = 7;
                    tile.candleMeltTimer3 = tile.candleMeltTimerMAX;
                }
                else if(tile.candleType4 == 0) {
                    tile.candleType4 = candleType;
                    tile.candleHeight4 = 7;
                    tile.candleMeltTimer4 = tile.candleMeltTimerMAX;
                }
            }
            return blockstate.setValue(CANDLES, Integer.valueOf(Math.min(4, blockstate.getValue(CANDLES) + 1)));

        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;

            return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag)).setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection()).setValue(SLOT_ONE_TYPE, candleType).setValue(CANDLES_LIT, 0);
        }
    }


    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return (useContext.getItemInHand().getItem() == ModBlocks.CANDLE.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_BLUE.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_BLACK.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_LIME.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_ORANGE.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_PINK.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_PURPLE.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_RED.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_CYAN.get().asItem()
                || useContext.getItemInHand().getItem() == ModBlocks.CANDLE_YELLOW.get().asItem())
                && state.getValue(CANDLES) < 4 ? true : super.canBeReplaced(state, useContext);
    }

    public void dropCandles(Level world, BlockPos pos) {

        BlockEntity entity = world.getBlockEntity(pos);
        if(entity instanceof CandleTile && !world.isClientSide()) {
            CandleTile candleTile = (CandleTile) entity;
            ItemStack itemStack = new ItemStack(ModBlocks.CANDLE.get());
            if(7 - candleTile.candleHeight1 < 1) {
                if (candleTile.candleType1 == 1) {
                    itemStack = new ItemStack(ModBlocks.CANDLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 2) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLUE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 3) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLACK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 4) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_LIME.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 5) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_ORANGE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 6) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PINK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 7) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PURPLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 8) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_RED.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 9) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_CYAN.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType1 == 10) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_YELLOW.get());
                    popResource((ServerLevel) world, pos, itemStack);
                }
            }


            if(7 - candleTile.candleHeight2 < 1) {
                if (candleTile.candleType2 == 1) {
                    itemStack = new ItemStack(ModBlocks.CANDLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 2) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLUE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 3) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLACK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 4) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_LIME.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 5) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_ORANGE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 6) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PINK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 7) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PURPLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 8) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_RED.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 9) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_CYAN.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType2 == 10) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_YELLOW.get());
                    popResource((ServerLevel) world, pos, itemStack);
                }
            }


            if(7 - candleTile.candleHeight3 < 1) {
                if (candleTile.candleType3 == 1) {
                    itemStack = new ItemStack(ModBlocks.CANDLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 2) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLUE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 3) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLACK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 4) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_LIME.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 5) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_ORANGE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 6) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PINK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 7) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PURPLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 8) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_RED.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 9) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_CYAN.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType3 == 10) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_YELLOW.get());
                    popResource((ServerLevel) world, pos, itemStack);
                }
            }


            if(7 - candleTile.candleHeight4 < 1) {
                if (candleTile.candleType4 == 1) {
                    itemStack = new ItemStack(ModBlocks.CANDLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 2) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLUE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 3) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_BLACK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 4) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_LIME.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 5) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_ORANGE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 6) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PINK.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 7) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_PURPLE.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 8) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_RED.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 9) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_CYAN.get());
                    popResource((ServerLevel) world, pos, itemStack);
                } else if (candleTile.candleType4 == 10) {
                    itemStack = new ItemStack(ModBlocks.CANDLE_YELLOW.get());
                    popResource((ServerLevel) world, pos, itemStack);
                }
            }
        }
    }


    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {

        dropCandles(world, pos);

        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch(state.getValue(CANDLES)) {
            case 1:
            default:
                return ONE_SHAPE;
            case 2:
                return TWO_SHAPE;
            case 3:
                return THREE_SHAPE;
            case 4:
                return FOUR_SHAPE;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);
        Random random = new Random();
            if(itemstack.getItem() == Items.FLINT_AND_STEEL)
            {


                if (canBeLit(state, pos, worldIn)) {

                    if (((CandleTile) worldIn.getBlockEntity(pos)).candleLit1 == 0)
                        ((CandleTile) worldIn.getBlockEntity(pos)).candleLit1 = 1;
                    else if (((CandleTile) worldIn.getBlockEntity(pos)).candleLit2 == 0)
                        ((CandleTile) worldIn.getBlockEntity(pos)).candleLit2 = 1;
                    else if (((CandleTile) worldIn.getBlockEntity(pos)).candleLit3 == 0)
                        ((CandleTile) worldIn.getBlockEntity(pos)).candleLit3 = 1;
                    else if (((CandleTile) worldIn.getBlockEntity(pos)).candleLit4 == 0)
                        ((CandleTile) worldIn.getBlockEntity(pos)).candleLit4 = 1;
                    else
                        return InteractionResult.FAIL;

                    worldIn.playSound((Player) null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 1.0F);
                    itemstack.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(handIn));

                    return InteractionResult.sidedSuccess(worldIn.isClientSide());
                }

            }
        return InteractionResult.PASS;
    }

    public Candle(Properties properties) {
        super(properties.noCollission());
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(PLAYER_NEAR, Boolean.valueOf(false)).setValue(CANDLES_LIT, 0));
    }

    public static void spawnSmokeParticles(Level worldIn, BlockPos pos, boolean spawnExtraSmoke) {
        Random random = worldIn.getRandom();
        SimpleParticleType basicparticletype = ParticleTypes.SMOKE;
        worldIn.addParticle(basicparticletype, true, (double)pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
        if (spawnExtraSmoke) {
            worldIn.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4D, (double)pos.getZ() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
        }

    }


    public static void extinguish(LevelAccessor world, BlockPos pos, BlockState state) {
        if (world.isClientSide()) {
            for(int i = 0; i < 20; ++i) {
                spawnSmokeParticles((Level)world, pos, true);
            }
        }

    }

    public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluidStateIn.getType() == Fluids.WATER) {
            CandleTile tile = ((CandleTile)worldIn.getBlockEntity(pos));
            boolean flag = (tile.candleLit1 == 1 || tile.candleLit2 == 1 || tile.candleLit3 == 1 || tile.candleLit4 == 1);
            if (flag) {
                if (!worldIn.isClientSide()) {
                    worldIn.playSound((Player)null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }

                extinguish(worldIn, pos, state);
                tile.candleLit1 = 0;
                tile.candleLit2 = 0;
                tile.candleLit3 = 0;
                tile.candleLit4 = 0;

            }

            worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)), 3);
            worldIn.scheduleTick(pos, fluidStateIn.getType(), fluidStateIn.getType().getTickDelay(worldIn));
            return true;
        } else {
            return false;
        }
    }

    public void onProjectileCollision(Level worldIn, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (projectile.isOnFire()) {
            CandleTile tile = ((CandleTile)worldIn.getBlockEntity(hit.getBlockPos()));
            boolean flagLit = (tile.candleLit1 == 1 && tile.candleLit2 == 1 && tile.candleLit3 == 1 && tile.candleLit4 == 1);
            Entity entity = projectile.getOwner();
            boolean flag = entity == null || entity instanceof Player || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entity);
            if (flag && !flagLit && !state.getValue(WATERLOGGED)) {
                if(tile.candleType1 != 0)
                    tile.candleLit1 = 1;
                if(tile.candleType2 != 0)
                    tile.candleLit2 = 1;
                if(tile.candleType3 != 0)
                    tile.candleLit3 = 1;
                if(tile.candleType4 != 0)
                    tile.candleLit4 = 1;
            }

        }

    }


    @SuppressWarnings("deprecation")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING, CANDLES, WATERLOGGED, PLAYER_NEAR, SLOT_ONE_TYPE, CANDLES_LIT);
    }

    public static boolean canBeLit(BlockState state, BlockPos pos, Level world) {
        if !(world.getBlockEntity(pos) instanceOf(CandleTile)) return false;
        return !state.getValue(BlockStateProperties.WATERLOGGED) && (((CandleTile)world.getBlockEntity(pos)).candleLit1 == 0 || (((CandleTile)world.getBlockEntity(pos)).candleLit2 == 0 && ((CandleTile)world.getBlockEntity(pos)).candleType2 != 0) || (((CandleTile)world.getBlockEntity(pos)).candleLit3 == 0 && ((CandleTile)world.getBlockEntity(pos)).candleType3 != 0) || (((CandleTile)world.getBlockEntity(pos)).candleLit4 == 0 && ((CandleTile)world.getBlockEntity(pos)).candleType4 != 0));
    }


    public void setPlayerNear(Level worldIn, BlockPos pos, BlockState state, boolean near) {
        worldIn.setBlock(pos, state.setValue(PLAYER_NEAR, near), 1);
    }

    public boolean getPlayerNear(Level worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getValue(PLAYER_NEAR);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {

        if(!state.canSurvive(world, pos))
        {
            if(!world.isClientSide() && world instanceof ServerLevel) {
                dropCandles(((ServerLevel) world).getLevel(), pos);
            }
        }

        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return canSupportCenter(worldIn, pos.below(), Direction.UP);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    @Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
        super.onBlockExploded(state, world, pos, explosion);

        if (world instanceof ServerLevel) {
            if (world.getBlockState(pos) != state && !world.isClientSide()) {
                dropCandles(world, pos);
            }

        }
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("<%s>", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA6600)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.candle_shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.candle_shift_2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.candle_shift_3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

        } else {
            tooltip.add(new TranslatableComponent("[%s]", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAA00)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }


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
//                if(((CofferTile)worldIn.getBlockEntity(pos)).customName != null)
//                    return new TranslatableComponent(((CofferTile)worldIn.getBlockEntity(pos)).customName.getString());
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
//    @Nullable
//    @Override
//    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
//        return ModTileEntities.CANDLE_TILE.get().create();
//    }
//
//    @Override
//    public boolean hasBlockEntity(BlockState state) {
//        return true;
//    }
//
//    @Override
//    public Class<CandleTile> getTileEntityClass() {
//        return CandleTile.class;
//    }
//





//    @Override
//    public Class<CandleTile> getTileEntityClass() {
//        return CandleTile.class;
//    }
//
//    @Nullable
//    @Override
//    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//        return new CandleTile(ModTileEntities.CRYSTAL_BALL_TILE.get(), pos, state);
//    }


    @Override
    public Class<CandleTile> getTileEntityClass() {
        return CandleTile.class;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CandleTile(ModTileEntities.CANDLE_TILE.get(), pos, state);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType){
        return entityType == ModTileEntities.CANDLE_TILE.get() ?
                (world2, pos, state2, entity) -> ((CandleTile)entity).tick() : null;
    }

}
