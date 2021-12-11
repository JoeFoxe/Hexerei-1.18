package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.container.MixingCauldronContainer;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.particle.ModParticleTypes;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.joefoxe.hexerei.tileentity.CandleTile;
import net.joefoxe.hexerei.tileentity.CrystalBallTile;
import net.joefoxe.hexerei.tileentity.MixingCauldronTile;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class MixingCauldron extends BaseEntityBlock implements ITileEntity<MixingCauldronTile> {

    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);
    public static final EnumProperty<LiquidType> FLUID = EnumProperty.create("fluid", LiquidType.class);
    public static final IntegerProperty CRAFT_DELAY = IntegerProperty.create("delay", 0, MixingCauldronTile.craftDelayMax);
    public int emitParticles;

    @SuppressWarnings("deprecation")
    @Override
    public RenderShape getRenderShape(BlockState iBlockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LEVEL, 0).setValue(FLUID, LiquidType.EMPTY).setValue(CRAFT_DELAY, 0);
    }

    // hitbox
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(0, 3, 0, 16, 16, 2),
            Block.box(1, 13, -0.5, 15, 14, 0),
            Block.box(1, 13, 16, 15, 14, 16.5),
            Block.box(-0.5, 13, 1, 0, 14, 15),
            Block.box(16, 13, 1, 16.5, 14, 15),
            Block.box(-0.5, 11, 1, 0, 12, 15),
            Block.box(1, 11, 16, 15, 12, 16.5),
            Block.box(16, 11, 1, 16.5, 12, 15),
            Block.box(1, 11, -0.5, 15, 12, 0),
            Block.box(-1.25, 15.5, -1.25, 1.75, 16.5, 1.75),
            Block.box(-0.75, 16.5, 1.25, 1.25, 17.5, 1.75),
            Block.box(-1.25, 16.5, -1.25, -0.75, 17.5, 1.75),
            Block.box(1.25, 16.5, -1.25, 1.75, 17.5, 1.75),
            Block.box(-0.75, 16.5, -1.25, 1.25, 17.5, -0.75),
            Block.box(0, 3, 14, 16, 16, 16),
            Block.box(14, 3, 2, 16, 16, 14),
            Block.box(1, 2, 1, 15, 2.5, 15),
            Block.box(0.5, 1.5, 0.5, 15.5, 2, 15.5),
            Block.box(0.5, 2.5, 0.5, 15.5, 3, 15.5),
            Block.box(-0.25, 2.6, -0.25, 1.75, 3.5, 1.75),
            Block.box(-0.75, 3.5, -0.75, 1.25, 15.5, 1.25),
            Block.box(0.25, 0.5, 0.25, 3.25, 2.75, 3.25),
            Block.box(-0.25, 0, -0.25, 3.75, 0.5, 3.75),
            Block.box(12.75, 0.5, 0.25, 15.75, 2.75, 3.25),
            Block.box(12.25, 0, -0.25, 16.25, 0.5, 3.75),
            Block.box(12.75, 0.5, 12.75, 15.75, 2.75, 15.75),
            Block.box(12.25, 0, 12.25, 16.25, 0.5, 16.25),
            Block.box(0.25, 0.5, 12.75, 3.25, 2.75, 15.75),
            Block.box(-0.25, 0, 12.25, 3.75, 0.5, 16.25),
            Block.box(14.25, 2.6, -0.25, 16.25, 3.5, 1.75),
            Block.box(14.75, 3.5, -0.75, 16.75, 15.5, 1.25),
            Block.box(14.25, 15.5, -1.25, 17.25, 16.5, 1.75),
            Block.box(14.25, 16.5, -1.25, 14.75, 17.5, 1.75),
            Block.box(14.75, 16.5, -1.25, 16.75, 17.5, -0.75),
            Block.box(16.75, 16.5, -1.25, 17.25, 17.5, 1.75),
            Block.box(14.75, 16.5, 1.25, 16.75, 17.5, 1.75),
            Block.box(14.25, 2.6, 14.25, 16.25, 3.5, 16.25),
            Block.box(14.75, 3.5, 14.75, 16.75, 15.5, 16.75),
            Block.box(14.25, 15.5, 14.25, 17.25, 16.5, 17.25),
            Block.box(14.25, 16.5, 14.25, 14.75, 17.5, 17.25),
            Block.box(14.75, 16.5, 16.75, 16.75, 17.5, 17.25),
            Block.box(16.75, 16.5, 14.25, 17.25, 17.5, 17.25),
            Block.box(14.75, 16.5, 14.25, 16.75, 17.5, 14.75),
            Block.box(-0.25, 2.6, 14.25, 1.75, 3.5, 16.25),
            Block.box(-0.75, 3.5, 14.75, 1.25, 15.5, 16.75),
            Block.box(-1.25, 15.5, 14.25, 1.75, 16.5, 17.25),
            Block.box(-0.75, 16.5, 16.75, 1.25, 17.5, 17.25),
            Block.box(-1.25, 16.5, 14.25, -0.75, 17.5, 17.25),
            Block.box(1.25, 16.5, 14.25, 1.75, 17.5, 17.25),
            Block.box(-0.75, 16.5, 14.25, 1.25, 17.5, 14.75),
            Block.box(0, 3, 2, 2, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public boolean useShapeForLightOcclusion(BlockState p_220074_1_) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_){
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);
        if (itemstack.isEmpty()) {
            if(!worldIn.isClientSide()) {
                BlockEntity tileEntity = worldIn.getBlockEntity(pos);

                if(tileEntity instanceof MixingCauldronTile) {
                    MenuProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayer)player), containerProvider, tileEntity.getBlockPos());

                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }
        } else {
            int i = state.getValue(LEVEL);
            Item item = itemstack.getItem();
            if (item == Items.WATER_BUCKET && ((state.getValue(FLUID) == LiquidType.WATER || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3 && !worldIn.isClientSide)) {

                    player.awardStat(Stats.FILL_CAULDRON);
                    this.setFillLevel(worldIn, pos, state, 3, LiquidType.WATER);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                    } else if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                        player.drop(new ItemStack(Items.BUCKET), false);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == Items.LAVA_BUCKET && ((state.getValue(FLUID) == LiquidType.LAVA || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3 && !worldIn.isClientSide)) {

                    player.awardStat(Stats.FILL_CAULDRON);
                    this.setFillLevel(worldIn, pos, state, 3, LiquidType.LAVA);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                    } else if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                        player.drop(new ItemStack(Items.BUCKET), false);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == Items.MILK_BUCKET && ((state.getValue(FLUID) == LiquidType.MILK || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3 && !worldIn.isClientSide)) {

                    player.awardStat(Stats.FILL_CAULDRON);
                    this.setFillLevel(worldIn, pos, state, 3, LiquidType.MILK);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                    } else if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                        player.drop(new ItemStack(Items.BUCKET), false);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_FILL_FISH, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.QUICKSILVER_BUCKET.get() && ((state.getValue(FLUID) == LiquidType.QUICKSILVER || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3 && !worldIn.isClientSide)) {

                player.awardStat(Stats.FILL_CAULDRON);
                this.setFillLevel(worldIn, pos, state, 3, LiquidType.QUICKSILVER);
                itemstack.shrink(1);
                if (itemstack.isEmpty()) {
                    player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                } else if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                    player.drop(new ItemStack(Items.BUCKET), false);
                }
                worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_FILL_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.BLOOD_BUCKET.get() && ((state.getValue(FLUID) == LiquidType.BLOOD || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3 && !worldIn.isClientSide)) {

                player.awardStat(Stats.FILL_CAULDRON);
                this.setFillLevel(worldIn, pos, state, 3, LiquidType.BLOOD);
                itemstack.shrink(1);
                if (itemstack.isEmpty()) {
                    player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                } else if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                    player.drop(new ItemStack(Items.BUCKET), false);
                }
                worldIn.playSound((Player)null, pos, SoundEvents.HONEY_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.TALLOW_BUCKET.get() && ((state.getValue(FLUID) == LiquidType.TALLOW || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3 && !worldIn.isClientSide)) {

                player.awardStat(Stats.FILL_CAULDRON);
                this.setFillLevel(worldIn, pos, state, 3, LiquidType.TALLOW);
                itemstack.shrink(1);
                if (itemstack.isEmpty()) {
                    player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                } else if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                    player.drop(new ItemStack(Items.BUCKET), false);
                }
                worldIn.playSound((Player)null, pos, SoundEvents.HONEY_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == Items.BUCKET && i == 3) {
                if (!worldIn.isClientSide) {
                    if(state.getValue(FLUID) == LiquidType.WATER) {
                        worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        itemstack.shrink(1);
                        this.setFillLevel(worldIn, pos, state, 0, LiquidType.EMPTY);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, new ItemStack(Items.WATER_BUCKET));
                        } else if (!player.getInventory().add(new ItemStack(Items.WATER_BUCKET))) {
                            player.drop(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.LAVA) {
                        worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_FILL_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F);
                        itemstack.shrink(1);
                        this.setFillLevel(worldIn, pos, state, 0, LiquidType.EMPTY);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, new ItemStack(Items.LAVA_BUCKET));
                        } else if (!player.getInventory().add(new ItemStack(Items.LAVA_BUCKET))) {
                            player.drop(new ItemStack(Items.LAVA_BUCKET), false);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.MILK) {
                        worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_FILL_FISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                        itemstack.shrink(1);
                        this.setFillLevel(worldIn, pos, state, 0, LiquidType.EMPTY);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, new ItemStack(Items.MILK_BUCKET));
                        } else if (!player.getInventory().add(new ItemStack(Items.MILK_BUCKET))) {
                            player.drop(new ItemStack(Items.MILK_BUCKET), false);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.QUICKSILVER) {
                        worldIn.playSound((Player)null, pos, SoundEvents.BUCKET_FILL_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F);
                        itemstack.shrink(1);
                        this.setFillLevel(worldIn, pos, state, 0, LiquidType.EMPTY);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, new ItemStack(ModItems.QUICKSILVER_BUCKET.get()));
                        } else if (!player.getInventory().add(new ItemStack(ModItems.QUICKSILVER_BUCKET.get()))) {
                            player.drop(new ItemStack(ModItems.QUICKSILVER_BUCKET.get()), false);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.BLOOD) {
                        worldIn.playSound((Player)null, pos, SoundEvents.HONEY_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);
                        itemstack.shrink(1);
                        this.setFillLevel(worldIn, pos, state, 0, LiquidType.EMPTY);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, new ItemStack(ModItems.BLOOD_BUCKET.get()));
                        } else if (!player.getInventory().add(new ItemStack(ModItems.BLOOD_BUCKET.get()))) {
                            player.drop(new ItemStack(ModItems.BLOOD_BUCKET.get()), false);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.TALLOW) {
                        worldIn.playSound((Player)null, pos, SoundEvents.HONEY_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);
                        itemstack.shrink(1);
                        this.setFillLevel(worldIn, pos, state, 0, LiquidType.EMPTY);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, new ItemStack(ModItems.TALLOW_BUCKET.get()));
                        } else if (!player.getInventory().add(new ItemStack(ModItems.TALLOW_BUCKET.get()))) {
                            player.drop(new ItemStack(ModItems.TALLOW_BUCKET.get()), false);
                        }
                    }


                    player.awardStat(Stats.USE_CAULDRON);
                }

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (i > 0 && item == Items.GLASS_BOTTLE) {
                if (!worldIn.isClientSide) {
                    if(state.getValue(FLUID) == LiquidType.WATER) {
                        ItemStack itemstack4 = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
                        player.awardStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, itemstack4);
                        } else if (!player.getInventory().add(itemstack4)) {
                            player.drop(itemstack4, false);
                        } else if (player instanceof ServerPlayer) {
                            ((ServerPlayer) player).initMenu(player.containerMenu);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.LAVA) {
                        ItemStack itemstack5 = new ItemStack(ModItems.LAVA_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, itemstack5);
                        } else if (!player.getInventory().add(itemstack5)) {
                            player.drop(itemstack5, false);
                        } else if (player instanceof ServerPlayer) {
                            ((ServerPlayer) player).initMenu(player.containerMenu);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.MILK) {
                        ItemStack itemstack6 = new ItemStack(ModItems.MILK_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, itemstack6);
                        } else if (!player.getInventory().add(itemstack6)) {
                            player.drop(itemstack6, false);
                        } else if (player instanceof ServerPlayer) {
                            ((ServerPlayer) player).initMenu(player.containerMenu);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.QUICKSILVER) {
                        ItemStack itemstack7 = new ItemStack(ModItems.QUICKSILVER_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, itemstack7);
                        } else if (!player.getInventory().add(itemstack7)) {
                            player.drop(itemstack7, false);
                        } else if (player instanceof ServerPlayer) {
                            ((ServerPlayer) player).initMenu(player.containerMenu);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.BLOOD) {
                        ItemStack itemstack8 = new ItemStack(ModItems.BLOOD_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, itemstack8);
                        } else if (!player.getInventory().add(itemstack8)) {
                            player.drop(itemstack8, false);
                        } else if (player instanceof ServerPlayer) {
                            ((ServerPlayer) player).initMenu(player.containerMenu);
                        }
                    } else if(state.getValue(FLUID) == LiquidType.TALLOW) {
                        ItemStack itemstack8 = new ItemStack(ModItems.TALLOW_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(handIn, itemstack8);
                        } else if (!player.getInventory().add(itemstack8)) {
                            player.drop(itemstack8, false);
                        } else if (player instanceof ServerPlayer) {
                            ((ServerPlayer) player).initMenu(player.containerMenu);
                        }
                    }

                    worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);

                    this.setFillLevel(worldIn, pos, state, i - 1, i - 1 > 0 ? state.getValue(FLUID) : LiquidType.EMPTY);

                }

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == Items.POTION && PotionUtils.getPotion(itemstack) == Potions.WATER && (state.getValue(FLUID) == LiquidType.WATER || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3) {
                if (!worldIn.isClientSide) {

                    ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, itemstack3);
                    } else if (!player.getInventory().add(itemstack3)) {
                        player.drop(itemstack3, false);
                    } else if (player instanceof ServerPlayer) {
                        ((ServerPlayer) player).initMenu(player.containerMenu);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.setFillLevel(worldIn, pos, state, i + 1, LiquidType.WATER);
                }

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.LAVA_BOTTLE.get() && (state.getValue(FLUID) == LiquidType.LAVA || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3) {
                if (!worldIn.isClientSide) {

                    ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, itemstack3);
                    } else if (!player.getInventory().add(itemstack3)) {
                        player.drop(itemstack3, false);
                    } else if (player instanceof ServerPlayer) {
                        ((ServerPlayer) player).initMenu(player.containerMenu);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.setFillLevel(worldIn, pos, state, i + 1, LiquidType.LAVA);
                }

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.MILK_BOTTLE.get() && (state.getValue(FLUID) == LiquidType.MILK || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3) {
                if (!worldIn.isClientSide) {

                    ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, itemstack3);
                    } else if (!player.getInventory().add(itemstack3)) {
                        player.drop(itemstack3, false);
                    } else if (player instanceof ServerPlayer) {
                        ((ServerPlayer) player).initMenu(player.containerMenu);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.setFillLevel(worldIn, pos, state, i + 1, LiquidType.MILK);
                }

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.QUICKSILVER_BOTTLE.get() && (state.getValue(FLUID) == LiquidType.QUICKSILVER || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3) {
                if (!worldIn.isClientSide) {

                    ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, itemstack3);
                    } else if (!player.getInventory().add(itemstack3)) {
                        player.drop(itemstack3, false);
                    } else if (player instanceof ServerPlayer) {
                        ((ServerPlayer) player).initMenu(player.containerMenu);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.setFillLevel(worldIn, pos, state, i + 1, LiquidType.QUICKSILVER);
                }
                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.BLOOD_BOTTLE.get() && (state.getValue(FLUID) == LiquidType.BLOOD || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3) {
                if (!worldIn.isClientSide) {

                    ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, itemstack3);
                    } else if (!player.getInventory().add(itemstack3)) {
                        player.drop(itemstack3, false);
                    } else if (player instanceof ServerPlayer) {
                        ((ServerPlayer) player).initMenu(player.containerMenu);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.setFillLevel(worldIn, pos, state, i + 1, LiquidType.BLOOD);
                }
                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (item == ModItems.TALLOW_BOTTLE.get() && (state.getValue(FLUID) == LiquidType.TALLOW || state.getValue(FLUID) == LiquidType.EMPTY) && i < 3) {
                if (!worldIn.isClientSide) {

                    ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(handIn, itemstack3);
                    } else if (!player.getInventory().add(itemstack3)) {
                        player.drop(itemstack3, false);
                    } else if (player instanceof ServerPlayer) {
                        ((ServerPlayer) player).initMenu(player.containerMenu);
                    }
                    worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.setFillLevel(worldIn, pos, state, i + 1, LiquidType.TALLOW);
                }
                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if(!worldIn.isClientSide()) { // If
                BlockEntity tileEntity = worldIn.getBlockEntity(pos);

                if(tileEntity instanceof MixingCauldronTile) {
                    MenuProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayer)player), containerProvider, tileEntity.getBlockPos());

                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }


        }

        return InteractionResult.SUCCESS;
    }

    public void setFillLevel(Level worldIn, BlockPos pos, BlockState state, int level, LiquidType type) {
        worldIn.setBlock(pos, state.setValue(LEVEL, Integer.valueOf(Mth.clamp(level, 0, 3))).setValue(FLUID, type), 2);
    }

    public void subtractLevel(Level worldIn, BlockPos pos) {

        worldIn.setBlock(pos, worldIn.getBlockState(pos)
                .setValue(LEVEL, Integer.valueOf(Mth.clamp(worldIn.getBlockState(pos).getValue(LEVEL) - 1, 0, 3)))
                .setValue(FLUID, worldIn.getBlockState(pos).getValue(LEVEL) - 1 > 0 ? worldIn.getBlockState(pos).getValue(FLUID) : LiquidType.EMPTY), 2);
    }

    public static void setCraftDelay(Level worldIn, BlockPos pos, BlockState state, int delay) {
        worldIn.setBlock(pos, state.setValue(CRAFT_DELAY, delay), 2);
    }

    public MixingCauldron(Properties properties) {

        super(properties.noOcclusion());
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL, FLUID, CRAFT_DELAY);
    }

    // drop blocks in getInventory() of the tile entity
    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        MixingCauldronTile te = (MixingCauldronTile) worldIn.getBlockEntity(pos);

        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {

            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(0)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(1)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(2)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(3)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(4)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(5)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(6)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(7)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(8)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, h.getStackInSlot(9)));
            if (!player.getAbilities().instabuild)
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() - 0.5f, pos.getZ() + 0.5f, new ItemStack(ModBlocks.MIXING_CAULDRON.get().asItem())));
        });

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {

        // get slots and animate particles based off number of items in the cauldron and based off the level and fluid type
        int num = ((MixingCauldronTile)world.getBlockEntity(pos)).getNumberOfItems();

        world.addParticle(ParticleTypes.FLAME, pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.035d ,(rand.nextDouble() - 0.5d) / 50d);
        world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 2d ,(rand.nextDouble() - 0.5d) / 50d);
        world.addParticle(ParticleTypes.SMOKE, pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d ,(rand.nextDouble() - 0.5d) / 50d);

        if(state.getValue(LEVEL) > 0) {
            for(int i = 0; i < state.getValue(LEVEL); i++) {
                if(rand.nextDouble() > 0.5f)
                    world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.004d, (rand.nextDouble() - 0.5d) / 50d);
            }
            for(int i = 0; i < num; i++) {
                if(rand.nextDouble() > 0.5f)
                    world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.004d, (rand.nextDouble() - 0.5d) / 50d);
            }
            if(state.getValue(FLUID) == LiquidType.WATER || state.getValue(FLUID) == LiquidType.MILK || state.getValue(FLUID) == LiquidType.TALLOW)
            {
                world.addParticle(ParticleTypes.BUBBLE, pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.005d, (rand.nextDouble() - 0.5d) / 50d);
            }else if(state.getValue(FLUID) == LiquidType.BLOOD)
            {
                if(rand.nextInt(20) == 0)
                    world.addParticle(ModParticleTypes.BLOOD.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 75d, (rand.nextDouble() + 0.5d) * 0.0005d, (rand.nextDouble() - 0.5d) / 75d);
            }
        }
        if(state.getValue(CRAFT_DELAY) >= MixingCauldronTile.craftDelayMax * 0.80)
        {
            //ffs please clean this up
            //maybe
            //also maybe figure out a way to spawn the particles as the craft is completed and not on an animate tick as they are random it appears
            for(int i = 0; i < 3; i++) {
                world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, world.getBlockState(pos)), pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, world.getBlockState(pos)), pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, world.getBlockState(pos)), pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d ,(rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d ,(rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
            }


        }
        if(this.emitParticles > 0)
        {
            for(int i = 0; i < 3; i++) {
                world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, world.getBlockState(pos)), pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, world.getBlockState(pos)), pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, world.getBlockState(pos)), pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d ,(rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.2d, pos.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d ,(rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + 0.95d + (0.05d * rand.nextDouble()) - ((3 - state.getValue(LEVEL)) * 0.15), pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
            }
            this.emitParticles--;
        }

        super.animateTick(state, world, pos, rand);
    }

    private MenuProvider createContainerProvider(Level worldIn, BlockPos pos) {
        return new MenuProvider() {
            @org.jetbrains.annotations.Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                return new MixingCauldronContainer(i, worldIn, pos, playerInventory, playerEntity);
            }

            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("");
            }
        };
    }

    public void setEmitParticles(int emitParticles) {
        this.emitParticles = emitParticles;
    }

//    @Nullable
//    @Override
//    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
//        BlockEntity te = ModTileEntities.MIXING_CAULDRON_TILE.get().create();
//        return te;
//    }

    

    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof MixingCauldronTile) {
            ((MixingCauldronTile)tileentity).entityInside(entityIn);
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.mixing_cauldron_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.mixing_cauldron"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

//    @Override
//    public boolean hasBlockEntity(BlockState state) {
//        return true;
//    }


    @Override
    public Class<MixingCauldronTile> getTileEntityClass() {
        return MixingCauldronTile.class;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MixingCauldronTile(ModTileEntities.MIXING_CAULDRON_TILE.get(), pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType){
        return entityType == ModTileEntities.MIXING_CAULDRON_TILE.get() ?
                (world2, pos, state2, entity) -> ((MixingCauldronTile)entity).tick() : null;
    }
}
