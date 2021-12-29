package net.joefoxe.hexerei.block.custom;

import net.joefoxe.hexerei.block.ITileEntity;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.container.MixingCauldronContainer;
import net.joefoxe.hexerei.fluid.ModFluids;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.particle.ModParticleTypes;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.joefoxe.hexerei.tileentity.CandleTile;
import net.joefoxe.hexerei.tileentity.CrystalBallTile;
import net.joefoxe.hexerei.tileentity.MixingCauldronTile;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.joefoxe.hexerei.util.HexereiPacketHandler;
import net.joefoxe.hexerei.util.message.EmitParticlesPacket;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static net.joefoxe.hexerei.tileentity.renderer.MixingCauldronRenderer.MAX_Y;
import static net.joefoxe.hexerei.tileentity.renderer.MixingCauldronRenderer.MIN_Y;

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

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult) {
        ItemStack stack = player.getItemInHand(hand).copy();
        Random random = new Random();
        ItemStack fillStack = stack.copy();
        fillStack.setCount(1);
        LazyOptional<IFluidHandlerItem> fluidHandlerOptional = fillStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
        if (fluidHandlerOptional.isPresent()) {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MixingCauldronTile) {
                IFluidHandlerItem fluidHandler = fluidHandlerOptional.resolve().get();

                if (((MixingCauldronTile) tileEntity).interactWithFluid(fluidHandler)) {
                    stack.shrink(1);
                    if(!tileEntity.getLevel().isClientSide)
                        HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                    if (stack.isEmpty()) {

                        player.setItemInHand(hand, fluidHandler.getContainer());
                    }
                    else {
                        player.setItemInHand(hand, stack);
                        if (!player.getInventory().add(fluidHandler.getContainer()))
                            player.drop(fluidHandler.getContainer(), false);
                    }

                    return InteractionResult.sidedSuccess(world.isClientSide);
                }
            }
            return InteractionResult.CONSUME;
        }
        else if(stack.getItem() == Items.GLASS_BOTTLE)
        {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MixingCauldronTile) {
                if(((MixingCauldronTile) tileEntity).getFluidStack().getAmount() >= 333){
                    if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(Fluids.WATER, 1))) {
                        ItemStack itemstack4 = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
                        player.awardStat(Stats.USE_CAULDRON);
                        player.getItemInHand(hand).shrink(1);
                        if (player.getItemInHand(hand).isEmpty()) {
                            player.setItemInHand(hand, itemstack4);
                        } else if (!player.getInventory().add(itemstack4)) {
                            player.drop(itemstack4, false);
                        }
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(333);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                            ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                            ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                        if(!tileEntity.getLevel().isClientSide)
                            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                        if(tileEntity.getLevel() != null)
                            tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                        return InteractionResult.CONSUME;
                    } else if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(Fluids.LAVA, 1))) {
                        ItemStack itemstack4 = new ItemStack(ModItems.LAVA_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        player.getItemInHand(hand).shrink(1);
                        if (player.getItemInHand(hand).isEmpty()) {
                            player.setItemInHand(hand, itemstack4);
                        } else if (!player.getInventory().add(itemstack4)) {
                            player.drop(itemstack4, false);
                        }
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(333);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                            ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                            ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                        if(!tileEntity.getLevel().isClientSide)
                            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                        if(tileEntity.getLevel() != null)
                            tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                        return InteractionResult.CONSUME;
                    } else if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(ModFluids.QUICKSILVER_FLUID.get(), 1))) {
                        ItemStack itemstack4 = new ItemStack(ModItems.QUICKSILVER_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        player.getItemInHand(hand).shrink(1);
                        if (player.getItemInHand(hand).isEmpty()) {
                            player.setItemInHand(hand, itemstack4);
                        } else if (!player.getInventory().add(itemstack4)) {
                            player.drop(itemstack4, false);
                        }
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(333);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                            ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                            ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                        if(!tileEntity.getLevel().isClientSide)
                            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                        if(tileEntity.getLevel() != null)
                            tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                        return InteractionResult.CONSUME;
                    } else if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(ModFluids.TALLOW_FLUID.get(), 1))) {
                        ItemStack itemstack4 = new ItemStack(ModItems.TALLOW_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        player.getItemInHand(hand).shrink(1);
                        if (player.getItemInHand(hand).isEmpty()) {
                            player.setItemInHand(hand, itemstack4);
                        } else if (!player.getInventory().add(itemstack4)) {
                            player.drop(itemstack4, false);
                        }
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(333);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                            ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                            ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                        if(!tileEntity.getLevel().isClientSide)
                            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                        if(tileEntity.getLevel() != null)
                            tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                        return InteractionResult.CONSUME;
                    } else if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(ModFluids.BLOOD_FLUID.get(), 1))) {
                        ItemStack itemstack4 = new ItemStack(ModItems.BLOOD_BOTTLE.get());
                        player.awardStat(Stats.USE_CAULDRON);
                        player.getItemInHand(hand).shrink(1);
                        if (player.getItemInHand(hand).isEmpty()) {
                            player.setItemInHand(hand, itemstack4);
                        } else if (!player.getInventory().add(itemstack4)) {
                            player.drop(itemstack4, false);
                        }
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(333);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                            ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                        if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                            ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                        if(!tileEntity.getLevel().isClientSide)
                            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                        if(tileEntity.getLevel() != null)
                            tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                        return InteractionResult.CONSUME;
                    }
                }
            }
        }
        else if(stack.getItem() == Items.POTION && PotionUtils.getPotion(stack) == Potions.WATER)
        {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MixingCauldronTile) {
                if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(Fluids.WATER, 1)) || ((MixingCauldronTile) tileEntity).getFluidStack().isEmpty()) {
                    ItemStack itemstack4 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    player.getItemInHand(hand).shrink(1);
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemInHand(hand, itemstack4);
                    } else if (!player.getInventory().add(itemstack4)) {
                        player.drop(itemstack4, false);
                    }
                    if(((MixingCauldronTile) tileEntity).getFluidStack().isEmpty())
                        ((MixingCauldronTile) tileEntity).fill(new FluidStack(Fluids.WATER,333), IFluidHandler.FluidAction.EXECUTE);
                    else
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(333);
                    if(((MixingCauldronTile) tileEntity).getFluidStack().getAmount() > ((MixingCauldronTile) tileEntity).getTankCapacity(1))
                        ((MixingCauldronTile) tileEntity).getFluidStack().setAmount(((MixingCauldronTile) tileEntity).getTankCapacity(1));
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                    if(!tileEntity.getLevel().isClientSide)
                        HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                    if(tileEntity.getLevel() != null)
                        tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                    return InteractionResult.CONSUME;
                }

            }
        }
        else if(stack.getItem() == ModItems.LAVA_BOTTLE.get())
        {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MixingCauldronTile) {
                if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(Fluids.LAVA, 1)) || ((MixingCauldronTile) tileEntity).getFluidStack().isEmpty()) {
                    ItemStack itemstack4 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    player.getItemInHand(hand).shrink(1);
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemInHand(hand, itemstack4);
                    } else if (!player.getInventory().add(itemstack4)) {
                        player.drop(itemstack4, false);
                    }
                    if(((MixingCauldronTile) tileEntity).getFluidStack().isEmpty())
                        ((MixingCauldronTile) tileEntity).fill(new FluidStack(Fluids.LAVA,333), IFluidHandler.FluidAction.EXECUTE);
                    else
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(333);
                    if(((MixingCauldronTile) tileEntity).getFluidStack().getAmount() > ((MixingCauldronTile) tileEntity).getTankCapacity(1))
                        ((MixingCauldronTile) tileEntity).getFluidStack().setAmount(((MixingCauldronTile) tileEntity).getTankCapacity(1));
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                    if(!tileEntity.getLevel().isClientSide)
                        HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                    if(tileEntity.getLevel() != null)
                        tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                    return InteractionResult.CONSUME;
                }
            }
        }
        else if(stack.getItem() == ModItems.QUICKSILVER_BOTTLE.get())
        {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MixingCauldronTile) {
                if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(ModFluids.QUICKSILVER_FLUID.get(), 1)) || ((MixingCauldronTile) tileEntity).getFluidStack().isEmpty()) {
                    ItemStack itemstack4 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    player.getItemInHand(hand).shrink(1);
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemInHand(hand, itemstack4);
                    } else if (!player.getInventory().add(itemstack4)) {
                        player.drop(itemstack4, false);
                    }
                    if(((MixingCauldronTile) tileEntity).getFluidStack().isEmpty())
                        ((MixingCauldronTile) tileEntity).fill(new FluidStack(ModFluids.QUICKSILVER_FLUID.get(),333), IFluidHandler.FluidAction.EXECUTE);
                    else
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(333);
                    if(((MixingCauldronTile) tileEntity).getFluidStack().getAmount() > ((MixingCauldronTile) tileEntity).getTankCapacity(1))
                        ((MixingCauldronTile) tileEntity).getFluidStack().setAmount(((MixingCauldronTile) tileEntity).getTankCapacity(1));
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                    if(!tileEntity.getLevel().isClientSide)
                        HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                    if(tileEntity.getLevel() != null)
                        tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                    return InteractionResult.CONSUME;
                }
            }
        }
        else if(stack.getItem() == ModItems.TALLOW_BOTTLE.get())
        {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MixingCauldronTile) {
                if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(ModFluids.TALLOW_FLUID.get(), 1)) || ((MixingCauldronTile) tileEntity).getFluidStack().isEmpty()) {
                    ItemStack itemstack4 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    player.getItemInHand(hand).shrink(1);
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemInHand(hand, itemstack4);
                    } else if (!player.getInventory().add(itemstack4)) {
                        player.drop(itemstack4, false);
                    }
                    if(((MixingCauldronTile) tileEntity).getFluidStack().isEmpty())
                        ((MixingCauldronTile) tileEntity).fill(new FluidStack(ModFluids.TALLOW_FLUID.get(),333), IFluidHandler.FluidAction.EXECUTE);
                    else
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(333);
                    if(((MixingCauldronTile) tileEntity).getFluidStack().getAmount() > ((MixingCauldronTile) tileEntity).getTankCapacity(1))
                        ((MixingCauldronTile) tileEntity).getFluidStack().setAmount(((MixingCauldronTile) tileEntity).getTankCapacity(1));
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                    if(!tileEntity.getLevel().isClientSide)
                        HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));

                    if(tileEntity.getLevel() != null)
                        tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                    return InteractionResult.CONSUME;
                }
            }
        }
        else if(stack.getItem() == ModItems.BLOOD_BOTTLE.get())
        {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof MixingCauldronTile) {
                if (((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(ModFluids.BLOOD_FLUID.get(), 1)) || ((MixingCauldronTile) tileEntity).getFluidStack().isEmpty()) {
                    ItemStack itemstack4 = new ItemStack(Items.GLASS_BOTTLE);
                    player.awardStat(Stats.USE_CAULDRON);
                    player.getItemInHand(hand).shrink(1);
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemInHand(hand, itemstack4);
                    } else if (!player.getInventory().add(itemstack4)) {
                        player.drop(itemstack4, false);
                    }
                    if(((MixingCauldronTile) tileEntity).getFluidStack().isEmpty())
                        ((MixingCauldronTile) tileEntity).fill(new FluidStack(ModFluids.BLOOD_FLUID.get(),333), IFluidHandler.FluidAction.EXECUTE);
                    else
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(333);
                    if(((MixingCauldronTile) tileEntity).getFluidStack().getAmount() > ((MixingCauldronTile) tileEntity).getTankCapacity(1))
                        ((MixingCauldronTile) tileEntity).getFluidStack().setAmount(((MixingCauldronTile) tileEntity).getTankCapacity(1));
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 1)
                        ((MixingCauldronTile) tileEntity).getFluidStack().shrink(1);
                    if (((MixingCauldronTile) tileEntity).getFluidStack().getAmount() % 10 == 9)
                        ((MixingCauldronTile) tileEntity).getFluidStack().grow(1);
                    if(!tileEntity.getLevel().isClientSide)
                        HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
                    if(tileEntity.getLevel() != null)
                        tileEntity.getLevel().playSound((Player) null, ((MixingCauldronTile) tileEntity).getPos().getX() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getY() + 0.5f, ((MixingCauldronTile) tileEntity).getPos().getZ() + 0.5f, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                    return InteractionResult.CONSUME;
                }
            }
        }
        if (!world.isClientSide()) {
            BlockEntity tileEntity = world.getBlockEntity(pos);

            if (tileEntity instanceof MixingCauldronTile) {
                MenuProvider containerProvider = createContainerProvider(world, pos);

                NetworkHooks.openGui(((ServerPlayer) player), containerProvider, tileEntity.getBlockPos());

            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.CONSUME;
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
        float height = MIN_Y;
        BlockEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof MixingCauldronTile)
            height = MIN_Y + (MAX_Y - MIN_Y) * Math.min(1, (float) ((MixingCauldronTile) tileEntity).getFluidStack().getAmount() / ((MixingCauldronTile) tileEntity).getTankCapacity(0)) + 1/16f;

        int num = ((MixingCauldronTile)world.getBlockEntity(pos)).getNumberOfItems();

        world.addParticle(ParticleTypes.FLAME, pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.035d ,(rand.nextDouble() - 0.5d) / 50d);
        world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 2d ,(rand.nextDouble() - 0.5d) / 50d);
        world.addParticle(ParticleTypes.SMOKE, pos.getX() + Math.round(rand.nextDouble()), pos.getY() + 1.2d, pos.getZ() + Math.round(rand.nextDouble()) , (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d ,(rand.nextDouble() - 0.5d) / 50d);


        if(((MixingCauldronTile) Objects.requireNonNull(world.getBlockEntity(pos))).getFluidStack().getAmount() > 0) {
            for(int i = 0; i < Mth.floor(((MixingCauldronTile)world.getBlockEntity(pos)).getFluidStack().getAmount() / 666f + 0.5f); i++) {
                if(rand.nextDouble() > 0.5f)
                    world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + height, pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.004d, (rand.nextDouble() - 0.5d) / 50d);
            }
            for(int i = 0; i < num; i++) {
                if(rand.nextDouble() > 0.5f)
                    world.addParticle(ModParticleTypes.CAULDRON.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + height, pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.004d, (rand.nextDouble() - 0.5d) / 50d);
            }
            if (tileEntity instanceof MixingCauldronTile) {
                if(((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(Fluids.WATER, 1)) || ((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(ModFluids.TALLOW_FLUID.get(), 1)))
                {
                    world.addParticle(ParticleTypes.BUBBLE, pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + height, pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.005d, (rand.nextDouble() - 0.5d) / 50d);
                }
                else if(((MixingCauldronTile) tileEntity).getFluidStack().isFluidEqual(new FluidStack(Fluids.WATER, 1)))
                {
                    if(rand.nextInt(20) == 0)
                        world.addParticle(ModParticleTypes.BLOOD.get(), pos.getX() + 0.2d + (0.6d * rand.nextDouble()), pos.getY() + height, pos.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 75d, (rand.nextDouble() + 0.5d) * 0.0005d, (rand.nextDouble() - 0.5d) / 75d);
                }
            }
        }
        if(state.getValue(CRAFT_DELAY) >= MixingCauldronTile.craftDelayMax * 0.80)
        {

            if (tileEntity instanceof MixingCauldronTile) {
                if (!tileEntity.getLevel().isClientSide)
                    HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> tileEntity.getLevel().getChunkAt(((MixingCauldronTile) tileEntity).getPos())), new EmitParticlesPacket(((MixingCauldronTile) tileEntity).getPos(), 3, false));
            }

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
