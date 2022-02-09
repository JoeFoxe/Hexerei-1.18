package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.MixingCauldron;
import net.joefoxe.hexerei.config.HexConfig;
import net.joefoxe.hexerei.data.recipes.DipperRecipe;
import net.joefoxe.hexerei.data.recipes.MixingCauldronRecipe;
import net.joefoxe.hexerei.data.recipes.ModRecipeTypes;
import net.joefoxe.hexerei.fluid.ModFluids;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.joefoxe.hexerei.tileentity.renderer.MixingCauldronRenderer;
import net.joefoxe.hexerei.util.HexereiPacketHandler;
import net.joefoxe.hexerei.util.HexereiTags;
import net.joefoxe.hexerei.util.message.EmitParticlesPacket;
import net.joefoxe.hexerei.util.message.TESyncPacket;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public class CandleDipperTile extends RandomizableContainerBlockEntity implements WorldlyContainer, Clearable, MenuProvider {

//    public final ItemStackHandler itemHandler = createHandler();
//    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    public float numberOfCandles;
    public Vec3 candlePos1;
    public Vec3 candlePos2;
    public Vec3 candlePos3;
    public boolean candle1Crafted = false;
    public boolean candle2Crafted = false;
    public boolean candle3Crafted = false;
    public boolean candle1Crafting;
    public boolean candle2Crafting;
    public boolean candle3Crafting;
    public boolean candle1Dunking;
    public boolean candle2Dunking;
    public boolean candle3Dunking;
    public int candle1DippedTimes;
    public int candle2DippedTimes;
    public int candle3DippedTimes;
    public int candle1DippedTimesMax;
    public int candle2DippedTimesMax;
    public int candle3DippedTimesMax;
    public int candleDryingTimeStart = 60;
    public int candle1DryingTime = candleDryingTimeStart;
    public int candle2DryingTime = candleDryingTimeStart;
    public int candle3DryingTime = candleDryingTimeStart;
    public int candle1DryingTimeMax = 200;
    public int candle2DryingTimeMax = 200;
    public int candle3DryingTimeMax = 200;
    public int candle1DippingTimeMax = 200;
    public int candle2DippingTimeMax = 200;
    public int candle3DippingTimeMax = 200;
    public int candle1DippingTime = candle1DippingTimeMax;
    public int candle2DippingTime = candle2DippingTimeMax;
    public int candle3DippingTime = candle3DippingTimeMax;
    public ItemStack candle1Output = ItemStack.EMPTY;
    public ItemStack candle2Output = ItemStack.EMPTY;
    public ItemStack candle3Output = ItemStack.EMPTY;
    public int candle1DecreaseAmount = 100;
    public int candle2DecreaseAmount = 100;
    public int candle3DecreaseAmount = 100;

    public Vec3 closestPlayerPos;
    public double closestDist;

    public final double maxDist = 8;


    public CandleDipperTile(BlockEntityType<?> tileEntityTypeIn, BlockPos blockPos, BlockState blockState) {
        super(tileEntityTypeIn, blockPos, blockState);

        candlePos1 = new Vec3(0.5f,0.5f,0.5f);
        candlePos2 = new Vec3(0.5f,0.5f,0.5f);
        candlePos3 = new Vec3(0.5f,0.5f,0.5f);
        candle1Dunking = false;
        candle2Dunking = false;
        candle3Dunking = false;
//        candle1DippedTimes = 0;
//        candle2DippedTimes = 0;
//        candle3DippedTimes = 0;
        candle1DippedTimesMax = 3;
        candle2DippedTimesMax = 3;
        candle3DippedTimesMax = 3;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    public void sync() {
        setChanged();
        if (!level.isClientSide)
            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new TESyncPacket(worldPosition, save(new CompoundTag())));

        if(this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, this.level.getBlockState(this.worldPosition), this.level.getBlockState(this.worldPosition),
                    Block.UPDATE_CLIENTS);
    }

    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }

        return super.getCapability(capability, facing);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {

        return super.getCapability(cap);
    }

    public Item getItemInSlot(int slot) {
        return this.items.get(slot).getItem();
    }

    public int getNumberOfItems() {

        int num = 0;
        for(int i = 0; i < 8; i++)
        {
            if(this.items.get(i) != ItemStack.EMPTY)
                num++;
        }
        return num;

    }


    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        return super.serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }


    public CandleDipperTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.CANDLE_DIPPER_TILE.get(),blockPos, blockState);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < this.items.size()) {
            ItemStack itemStack = stack.copy();
            itemStack.setCount(1);
            this.items.set(index, itemStack);
            if(index == 0)
                candle1DryingTime = candleDryingTimeStart;
            if(index == 1)
                candle2DryingTime = candleDryingTimeStart;
            if(index == 2)
                candle3DryingTime = candleDryingTimeStart;
            level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, this.level.random.nextFloat() * 0.4F + 1.0F);
        }

        sync();
    }

    @Override
    public ItemStack removeItem(int index, int p_59614_) {
        this.unpackLootTable((Player)null);
        ItemStack itemstack = ContainerHelper.removeItem(this.getItems(), index, p_59614_);
        if (!itemstack.isEmpty()) {
            this.setChanged();
            if(index == 0)
            {
                candle1Crafted = false;
                sync();
            }if(index == 0)
            {
                candle2Crafted = false;
                sync();
            }if(index == 0)
            {
                candle3Crafted = false;
                sync();
            }
        }


        return itemstack;
    }

    public void craft(){
        SimpleContainer inv = new SimpleContainer(3);
        for (int i = 0; i < 3; i++) {
            inv.setItem(i, this.items.get(i));
        }


        Optional<DipperRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.DIPPER_RECIPE, inv, level);

        BlockEntity blockEntity = level.getBlockEntity(this.worldPosition.below());
        if(blockEntity instanceof MixingCauldronTile) {
            recipe.ifPresent(iRecipe -> {
                ItemStack output = iRecipe.getResultItem();
                ItemStack input = iRecipe.getIngredients().get(0).getItems()[0];
                boolean matchesFluid = iRecipe.getLiquid().getFluid().isSame(((MixingCauldronTile) blockEntity).getFluidStack().getFluid()) && iRecipe.getFluidLevelsConsumed() <= ((MixingCauldronTile) blockEntity).getFluidStack().getAmount();



                if (input.getItem() == this.items.get(0).getItem()) {
                    // FIRST SLOT MATCHES

                    if(matchesFluid){


                        if (!candle1Crafting) {
                            candle1Crafting = true;
                            candle1Output = output.copy();
                            candle1DecreaseAmount = iRecipe.getFluidLevelsConsumed();
                            candle1DippedTimesMax = iRecipe.getNumberOfDips();
                            candle1DryingTimeMax = iRecipe.getDryingTime();
                            candle1DryingTime = candleDryingTimeStart;
                            candle1DippingTimeMax = iRecipe.getDippingTime();
                            candle1DippingTime = candle1DippingTimeMax;
                            sync();
                        }

                    } else
                    {
                        if(candle1Dunking) {
                            candle1Crafting = false;
                            candle1Dunking = false;
                            candle1DryingTime = candleDryingTimeStart;
                            sync();
                        }

                    }


                } else {
                    if(matchesFluid){
                        if (candle1Crafting) {
                            candle1Crafting = false;
                            sync();
                        }
                    }
                }

                if (input.getItem() == this.items.get(1).getItem()) {
                    // SECOND SLOT MATCHES

                    if(matchesFluid){



                        if (!candle2Crafting) {
                            candle2Crafting = true;
                            candle2Output = output.copy();
                            candle2DecreaseAmount = iRecipe.getFluidLevelsConsumed();
                            candle2DippedTimesMax = iRecipe.getNumberOfDips();
                            candle2DryingTimeMax = iRecipe.getDryingTime();
                            candle2DryingTime = candleDryingTimeStart;
                            candle2DippingTimeMax = iRecipe.getDippingTime();
                            candle2DippingTime = candle2DippingTimeMax;
                            sync();
                        }

                    } else
                    {
                        if(candle2Dunking) {
                            candle2Crafting = false;
                            candle2Dunking = false;
                            candle2DryingTime = candleDryingTimeStart;
                            sync();
                        }

                    }

                } else {
                    if(matchesFluid){
                        if (candle2Crafting) {
                            candle2Crafting = false;
                            sync();
                        }
                    }
                }

                if (input.getItem() == this.items.get(2).getItem()) {
                    // THIRD SLOT MATCHES

                    if(matchesFluid){


                        if (!candle3Crafting) {
                            candle3Crafting = true;
                            candle3Output = output.copy();
                            candle3DecreaseAmount = iRecipe.getFluidLevelsConsumed();
                            candle3DippedTimesMax = iRecipe.getNumberOfDips();
                            candle3DryingTimeMax = iRecipe.getDryingTime();
                            candle3DryingTime = candleDryingTimeStart;
                            candle3DippingTimeMax = iRecipe.getDippingTime();
                            candle3DippingTime = candle3DippingTimeMax;
                            sync();
                        }


                    } else
                    {
                        if(candle3Dunking) {
                            candle3Crafting = false;
                            candle3Dunking = false;
                            candle3DryingTime = candleDryingTimeStart;
                            sync();
                        }

                    }


                } else {
                    if(matchesFluid){
                        if (candle3Crafting) {
                            candle3Crafting = false;
                            sync();
                        }
                    }
                }

//                if(this.craftDelay >= this.craftDelayMax) {
//                    Random rand = new Random();
//                    craftTheItem(output);
//                    sync();
//                    int temp = this.getFluidStack().getAmount();
//                    this.getFluidStack().shrink(this.getTankCapacity(0));
//                    this.fill(new FluidStack(iRecipe.getLiquidOutput(), temp), IFluidHandler.FluidAction.EXECUTE);
//
//                    //for setting a cooldown on crafting so the animations can take place
//                    this.crafted = true;
//
//                    this.getFluidStack().shrink(iRecipe.getFluidLevelsConsumed());
//                    if (this.getFluidStack().getAmount() % 10 == 1)
//                        this.getFluidStack().shrink(1);
//                    if (this.getFluidStack().getAmount() % 10 == 9)
//                        this.getFluidStack().grow(1);
//
//                }
//
//                else
//                {
//                    if(candle3Dunking || candle2Dunking || candle3Dunking) {
//                        System.out.println("SET FALSE");
//                        candle1Crafting = false;
//                        candle2Crafting = false;
//                        candle3Crafting = false;
//                        candle1Dunking = false;
//                        candle2Dunking = false;
//                        candle3Dunking = false;
//                        candle3DryingTime = candleDryingTimeStart;
//                        candle2DryingTime = candleDryingTimeStart;
//                        candle1DryingTime = candleDryingTimeStart;
//                        sync();
//                    }
//
//                }


            });
        }

        if (candle1DippedTimes >= candle1DippedTimesMax) {
            candle1DippedTimes = 0;
            candle1DippingTime = candle1DippingTimeMax;
            candle1Dunking = false;
            candle1DryingTime = candle1DryingTimeMax;
            level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
            candle1Crafted = true;
            candle1Crafting = false;
            this.items.set(0, candle1Output);
            candle1Output = ItemStack.EMPTY;
            sync();

        }
        if (candle2DippedTimes >= candle2DippedTimesMax) {

            candle2DippedTimes = 0;
            candle2DippingTime = candle2DippingTimeMax;
            candle2Dunking = false;
            candle2DryingTime = candle2DryingTimeMax;
            level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
            candle2Crafted = true;
            candle2Crafting = false;
            this.items.set(1, candle2Output);
            candle2Output = ItemStack.EMPTY;
            sync();

        }
        if (candle3DippedTimes >= candle3DippedTimesMax) {
            candle3DippedTimes = 0;
            candle3DippingTime = candle3DippingTimeMax;
            candle3Dunking = false;
            candle3DryingTime = candle3DryingTimeMax;
            level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
            candle3Crafted = true;
            candle3Crafting = false;
            this.items.set(2, candle3Output);
            candle3Output = ItemStack.EMPTY;
            sync();

        }
//

    }


    private void craftTheItem(ItemStack output, int slot) {

        this.setItem(slot, output);
    }


    @Override
    public void load(CompoundTag nbt) {
//        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ContainerHelper.loadAllItems(nbt, this.items);
        }
//        super.read(state, nbt);
//        if (nbt.contains("CustomName", 8))
//            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));

        if (nbt.contains("candle1DippedTimes",  Tag.TAG_INT))
            candle1DippedTimes = nbt.getInt("candle1DippedTimes");
        if (nbt.contains("candle2DippedTimes",  Tag.TAG_INT))
            candle2DippedTimes = nbt.getInt("candle2DippedTimes");
        if (nbt.contains("candle3DippedTimes",  Tag.TAG_INT))
            candle3DippedTimes = nbt.getInt("candle3DippedTimes");
        if (nbt.contains("candle1DippedTimesMax",  Tag.TAG_INT))
            candle1DippedTimesMax = nbt.getInt("candle1DippedTimesMax");
        if (nbt.contains("candle2DippedTimesMax",  Tag.TAG_INT))
            candle2DippedTimesMax = nbt.getInt("candle2DippedTimesMax");
        if (nbt.contains("candle3DippedTimesMax",  Tag.TAG_INT))
            candle3DippedTimesMax = nbt.getInt("candle3DippedTimesMax");
        if (nbt.contains("candle1DryingTime",  Tag.TAG_INT))
            candle1DryingTime = nbt.getInt("candle1DryingTime");
        if (nbt.contains("candle2DryingTime",  Tag.TAG_INT))
            candle2DryingTime = nbt.getInt("candle2DryingTime");
        if (nbt.contains("candle3DryingTime",  Tag.TAG_INT))
            candle3DryingTime = nbt.getInt("candle3DryingTime");
        if (nbt.contains("candle1DippingTime",  Tag.TAG_INT))
            candle1DippingTime = nbt.getInt("candle1DippingTime");
        if (nbt.contains("candle2DippingTime",  Tag.TAG_INT))
            candle2DippingTime = nbt.getInt("candle2DippingTime");
        if (nbt.contains("candle3DippingTime",  Tag.TAG_INT))
            candle3DippingTime = nbt.getInt("candle3DippingTime");
        if (nbt.contains("candle1Dunking",  Tag.TAG_INT))
            candle1Dunking = nbt.getInt("candle1Dunking") == 1;
        if (nbt.contains("candle2Dunking",  Tag.TAG_INT))
            candle2Dunking = nbt.getInt("candle2Dunking") == 1;
        if (nbt.contains("candle3Dunking",  Tag.TAG_INT))
            candle3Dunking = nbt.getInt("candle3Dunking") == 1;
        if (nbt.contains("candle1Crafting",  Tag.TAG_INT))
            candle1Crafting = nbt.getInt("candle1Crafting") == 1;
        if (nbt.contains("candle2Crafting",  Tag.TAG_INT))
            candle2Crafting = nbt.getInt("candle2Crafting") == 1;
        if (nbt.contains("candle3Crafting",  Tag.TAG_INT))
            candle3Crafting = nbt.getInt("candle3Crafting") == 1;
        if (nbt.contains("candle1Crafted",  Tag.TAG_INT))
            candle1Crafted = nbt.getInt("candle1Crafted") == 1;
        if (nbt.contains("candle2Crafted",  Tag.TAG_INT))
            candle2Crafted = nbt.getInt("candle2Crafted") == 1;
        if (nbt.contains("candle3Crafted",  Tag.TAG_INT))
            candle3Crafted = nbt.getInt("candle3Crafted") == 1;
        super.load(nbt);

    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + Hexerei.MOD_ID + ".dipper");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return null;
    }

    public void saveAdditional(CompoundTag compound) {
        ContainerHelper.saveAllItems(compound, this.items);

        compound.putInt("candle1DippedTimes", candle1DippedTimes);
        compound.putInt("candle2DippedTimes", candle2DippedTimes);
        compound.putInt("candle3DippedTimes", candle3DippedTimes);

        compound.putInt("candle1DippedTimesMax", candle1DippedTimesMax);
        compound.putInt("candle2DippedTimesMax", candle2DippedTimesMax);
        compound.putInt("candle3DippedTimesMax", candle3DippedTimesMax);

        compound.putInt("candle1DryingTime", candle1DryingTime);
        compound.putInt("candle2DryingTime", candle2DryingTime);
        compound.putInt("candle3DryingTime", candle3DryingTime);

        compound.putInt("candle1DippingTime", candle1DippingTime);
        compound.putInt("candle2DippingTime", candle2DippingTime);
        compound.putInt("candle3DippingTime", candle3DippingTime);

        compound.putInt("candle1Dunking", candle1Dunking ? 1 : 0);
        compound.putInt("candle2Dunking", candle2Dunking ? 1 : 0);
        compound.putInt("candle3Dunking", candle3Dunking ? 1 : 0);

        compound.putInt("candle1Crafted", candle1Crafted ? 1 : 0);
        compound.putInt("candle2Crafted", candle2Crafted ? 1 : 0);
        compound.putInt("candle3Crafted", candle3Crafted ? 1 : 0);

        compound.putInt("candle1Crafting", candle1Crafting ? 1 : 0);
        compound.putInt("candle2Crafting", candle2Crafting ? 1 : 0);
        compound.putInt("candle3Crafting", candle3Crafting ? 1 : 0);
    }


//    @Override
    public CompoundTag save(CompoundTag compound) {
        super.saveAdditional(compound);
//        compound.put("inv", itemHandler.serializeNBT());
//        if (this.customName != null)
//            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
        ContainerHelper.saveAllItems(compound, this.items);

        compound.putInt("candle1DippedTimes", candle1DippedTimes);
        compound.putInt("candle2DippedTimes", candle2DippedTimes);
        compound.putInt("candle3DippedTimes", candle3DippedTimes);

        compound.putInt("candle1DippedTimesMax", candle1DippedTimesMax);
        compound.putInt("candle2DippedTimesMax", candle2DippedTimesMax);
        compound.putInt("candle3DippedTimesMax", candle3DippedTimesMax);

        compound.putInt("candle1DryingTime", candle1DryingTime);
        compound.putInt("candle2DryingTime", candle2DryingTime);
        compound.putInt("candle3DryingTime", candle3DryingTime);

        compound.putInt("candle1DippingTime", candle1DippingTime);
        compound.putInt("candle2DippingTime", candle2DippingTime);
        compound.putInt("candle3DippingTime", candle3DippingTime);

        compound.putInt("candle1Dunking", candle1Dunking ? 1 : 0);
        compound.putInt("candle2Dunking", candle2Dunking ? 1 : 0);
        compound.putInt("candle3Dunking", candle3Dunking ? 1 : 0);

        compound.putInt("candle1Crafted", candle1Crafted ? 1 : 0);
        compound.putInt("candle2Crafted", candle2Crafted ? 1 : 0);
        compound.putInt("candle3Crafted", candle3Crafted ? 1 : 0);

        compound.putInt("candle1Crafting", candle1Crafting ? 1 : 0);
        compound.putInt("candle2Crafting", candle2Crafting ? 1 : 0);
        compound.putInt("candle3Crafting", candle3Crafting ? 1 : 0);

        return compound;
    }

    @Override
    public CompoundTag getUpdateTag()
    {
        return this.save(new CompoundTag());
    }

    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {

        return ClientboundBlockEntityDataPacket.create(this, (tag) -> this.getUpdateTag());
    }

    @Override
    public void onDataPacket(final Connection net, final ClientboundBlockEntityDataPacket pkt)
    {
        this.deserializeNBT(pkt.getTag());
    }

    public static double getDistanceToEntity(Entity entity, BlockPos pos) {
        double deltaX = entity.position().x() - pos.getX() - 0.5f;
        double deltaY = entity.position().y() - pos.getY() - 0.5f;
        double deltaZ = entity.position().z() - pos.getZ() - 0.5f;

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    public static double getDistance(float x1, float y1, float x2, float y2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }


//    @Override
//    public double getMaxRenderDistanceSquared() {
//        return 4096D;
//    }

    @Override
    public AABB getRenderBoundingBox() {
        AABB aabb = super.getRenderBoundingBox().inflate(5, 5, 5);
        return aabb;
    }

    private float moveTo(float input, float moveTo, float speed)
    {
        float distance = moveTo - input;

        if(Math.abs(distance) <= speed)
        {
            return moveTo;
        }

        if(distance > 0)
        {
            input += speed;
        } else {
            input -= speed;
        }

        return input;
    }

    private float moveToAngle(float input, float movedTo, float speed)
    {
        float distance = movedTo - input;

        if(Math.abs(distance) <= speed)
        {
            return movedTo;
        }

        if(distance > 0)
        {
            if(Math.abs(distance) < 180)
                input += speed;
            else
                input -= speed;
        } else {
            if(Math.abs(distance) < 180)
                input -= speed;
            else
                input += speed;
        }

        if(input < -90){
            input += 360;
        }
        if(input > 270)
            input -= 360;

        return input;
    }

    public float getAngle(Vec3 pos) {
        float angle = (float) Math.toDegrees(Math.atan2(pos.z() - this.getBlockPos().getZ() - 0.5f, pos.x() - this.getBlockPos().getX() - 0.5f));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public float getSpeed(double pos, double posTo) {
        return (float)(0.01f + 0.10f * (Math.abs(pos - posTo) / 3f));
    }

    public Vec3 rotateAroundVec(Vec3 vector3dCenter,float rotation,Vec3 vector3d)
    {
        Vec3 newVec = vector3d.subtract(vector3dCenter);
        newVec = newVec.yRot(rotation/180f*(float)Math.PI);
        newVec = newVec.add(vector3dCenter);

        return newVec;
    }

    public int putItems (int slot, @Nonnull ItemStack stack) {
        if (this.items.get(slot).isEmpty()) {
            ItemStack stack1 = stack.copy();
            stack1.setCount(1);
            this.items.set(slot, stack1);
            sync();
            stack.shrink(1);
            return 1;
        }

        if (!this.items.get(slot).sameItem(stack))
            return 0;
        if(!ItemStack.isSameItemSameTags(stack, this.items.get(slot)))
            return 0;

        return 1;
    }

    public int interactDipper (Player player, BlockHitResult hit) {
        if(!player.isShiftKeyDown()) {
            if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                Random rand = new Random();
                if (this.items.get(0).isEmpty()) {
                    putItems(0, player.getItemInHand(InteractionHand.MAIN_HAND));
                    candle1DryingTime = candleDryingTimeStart;
                    candle1Crafted = false;
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                    return 1;
                } else if (this.items.get(1).isEmpty()) {
                    putItems(1, player.getItemInHand(InteractionHand.MAIN_HAND));
                    candle2DryingTime = candleDryingTimeStart;
                    candle2Crafted = false;
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                    return 1;
                } else if (this.items.get(2).isEmpty()) {
                    putItems(2, player.getItemInHand(InteractionHand.MAIN_HAND));
                    candle3DryingTime = candleDryingTimeStart;
                    candle3Crafted = false;
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                    return 1;
                }
            } else {
//                Random rand = new Random();
//                if (!this.items.get(0).isEmpty() && candle1Crafted) {
//                    this.items.set(0, ItemStack.EMPTY);
//                    candle1DippedTimes = 0;
//                    this.items.get(0).shrink(1);
//                    candle1DippingTime = candleDippingTimeMax;
//                    candle1Dunking = false;
//                    candle1Crafted = false;
//                    candle1DryingTime = candleDryingTimeMax;
//                    player.inventory.placeItemBackInInventory(candle1Output);
//                    candle1Output = ItemStack.EMPTY;
//
//                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
//                    if (candle1Crafting) {
//                        candle1Crafting = false;
//                        sync();
//                    }
//                    return 1;
//
//                } else if (!this.items.get(1).isEmpty() && candle2Crafted) {
//                    this.items.set(1, ItemStack.EMPTY);
//                    candle2DippedTimes = 0;
//                    candle2DippingTime = candleDippingTimeMax;
//                    candle2Dunking = false;
//                    candle2Crafted = false;
//                    candle2DryingTime = candleDryingTimeMax;
//                    player.inventory.placeItemBackInInventory(candle2Output);
//                    candle2Output = ItemStack.EMPTY;
//
//                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
//                    if (candle2Crafting) {
//                        candle2Crafting = false;
//                        sync();
//                    }
//                    return 1;
//
//                } else if (!this.items.get(2).isEmpty() && candle3Crafted) {
//                    this.items.set(2, ItemStack.EMPTY);
//                    candle3DippedTimes = 0;
//                    candle3DippingTime = candleDippingTimeMax;
//                    candle3Dunking = false;
//                    candle3Crafted = false;
//                    candle3DryingTime = candleDryingTimeMax;
//                    player.inventory.placeItemBackInInventory(candle3Output);
//                    candle3Output = ItemStack.EMPTY;
//
//                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
//                    if (candle3Crafting) {
//                        candle3Crafting = false;
//                        sync();
//                    }
//                    return 1;
//
//                }
            }
            if(candle1Crafted){
                candle1DippedTimes = 0;
                candle1DippingTime = candle1DippingTimeMax;
                candle1Dunking = false;
                candle1Crafted = false;
                candle1DryingTime = candle1DryingTimeMax;
                player.inventory.placeItemBackInInventory(this.items.get(0).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(0, ItemStack.EMPTY);
                candle1Output = ItemStack.EMPTY;
            }
            if(candle2Crafted){
                candle2DippedTimes = 0;
                candle2DippingTime = candle2DippingTimeMax;
                candle2Dunking = false;
                candle2Crafted = false;
                candle2DryingTime = candle2DryingTimeMax;
                player.inventory.placeItemBackInInventory(this.items.get(1).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(1, ItemStack.EMPTY);
                candle2Output = ItemStack.EMPTY;
            }
            if(candle3Crafted){
                candle3DippedTimes = 0;
                candle3DippingTime = candle3DippingTimeMax;
                candle3Dunking = false;
                candle3Crafted = false;
                candle3DryingTime = candle3DryingTimeMax;
                player.inventory.placeItemBackInInventory(this.items.get(2).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(2, ItemStack.EMPTY);
                candle3Output = ItemStack.EMPTY;
            }
        }
        else
        {
            if(!this.items.get(0).isEmpty() && !candle1Crafting) {

                candle1DippedTimes = 0;
                candle1DippingTime = candle1DippingTimeMax;
                candle1Dunking = false;
                candle1Crafted = false;
                candle1DryingTime = candle1DryingTimeMax;
                player.inventory.placeItemBackInInventory(this.items.get(0).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(0, ItemStack.EMPTY);
                candle1Output = ItemStack.EMPTY;
            }
            if(!this.items.get(1).isEmpty() && !candle2Crafting) {

                candle2DippedTimes = 0;
                candle2DippingTime = candle2DippingTimeMax;
                candle2Dunking = false;
                candle2Crafted = false;
                candle2DryingTime = candle2DryingTimeMax;
                player.inventory.placeItemBackInInventory(this.items.get(1).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(1, ItemStack.EMPTY);
                candle2Output = ItemStack.EMPTY;
            }
            if(!this.items.get(2).isEmpty() && !candle3Crafting) {

                candle3DippedTimes = 0;
                candle3DippingTime = candle3DippingTimeMax;
                candle3Dunking = false;
                candle3Crafted = false;
                candle3DryingTime = candle3DryingTimeMax;
                player.inventory.placeItemBackInInventory(this.items.get(2).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(2, ItemStack.EMPTY);
                candle3Output = ItemStack.EMPTY;
            }
        }

        return 0;
    }

//    @Override
    public void tick() {

        if(level instanceof ServerLevel) {
            craft();
        }

        closestPlayerPos = null;
        closestDist = maxDist;
        numberOfCandles = 0;

        Vec3 targetPos1 = new Vec3(4f / 16f, 0f / 16f, 1f / 16f);
        Vec3 targetPos2 = new Vec3(8f / 16f, 0f / 16f, 1f / 16f);
        Vec3 targetPos3 = new Vec3(12f / 16f, 0f / 16f, 1f / 16f);


        BlockEntity blockEntity = level.getBlockEntity(this.worldPosition.below());
        if(blockEntity instanceof MixingCauldronTile) {
            float fillPercentage = 0;
            FluidStack fluidStack = ((MixingCauldronTile) blockEntity).getFluidInTank(0);
            if(!fluidStack.isEmpty())
                fillPercentage = Math.min(1, (float) fluidStack.getAmount() / ((MixingCauldronTile) blockEntity).getTankCapacity(0));
            float height = MixingCauldronRenderer.MIN_Y + (MixingCauldronRenderer.MAX_Y - MixingCauldronRenderer.MIN_Y) * fillPercentage - 1 + 1/16f;
            if (candle1Crafting && candle1DippedTimes < 3) {
                candle1DryingTime--;
                if (candle1DryingTime <= 0) {
                    candle1DryingTime = candle1DryingTimeMax;
                    candle1Dunking = true;
                }
                targetPos1 = new Vec3(targetPos1.x(), 5f / 16f + Math.sin((this.level.getGameTime()) / 16f) / 32f, 8f / 16f);
            }
            else if(!this.items.get(0).isEmpty())
            {
                targetPos1 = new Vec3(targetPos1.x(), 5f / 16f + Math.sin((this.level.getGameTime()) / 16f) / 32f, 8f / 16f);
            }

            if (candle2Crafting && candle2DippedTimes < 3) {
                    candle2DryingTime--;
                if (candle2DryingTime <= 0) {
                    candle2DryingTime = candle2DryingTimeMax;
                    candle2Dunking = true;
                }
                targetPos2 = new Vec3(targetPos2.x(), 5f / 16f + Math.sin((this.level.getGameTime() + 20f) / 14f) / 32f, 8f / 16f);
            }
            else if(!this.items.get(1).isEmpty())
            {
                targetPos2 = new Vec3(targetPos2.x(), 5f / 16f + Math.sin((this.level.getGameTime() + 20f) / 14f) / 32f, 8f / 16f);
            }
            if (candle3Crafting && candle3DippedTimes < 3) {
                candle3DryingTime--;
                if (candle3DryingTime <= 0) {
                    candle3DryingTime = candle3DryingTimeMax;
                    candle3Dunking = true;
                }
                targetPos3 = new Vec3(targetPos3.x(), 5f / 16f + Math.sin((this.level.getGameTime() + 40f) / 15f) / 32f, 8f / 16f);
            }
            else if(!this.items.get(2).isEmpty())
            {
                targetPos3 = new Vec3(targetPos3.x(), 5f / 16f + Math.sin((this.level.getGameTime() + 40f) / 15f) / 32f, 8f / 16f);
            }

            if (candle1Dunking) {
                if (((MixingCauldronTile) blockEntity).getFluidStack().getAmount() > 0 )
                    candle1DippingTime--;
                candle1DryingTime = candle1DryingTimeMax;
                if (candle1DippingTime <= 0) {
                    candle1DippingTime = candle1DippingTimeMax;
                    candle1Dunking = false;
                    candle1DryingTime = candleDryingTimeStart;
                    candle1DippedTimes++;
                    chanceDecreaseLevel(candle1DecreaseAmount);
                }

                targetPos1 = new Vec3(targetPos1.x(), height + Math.sin((this.level.getGameTime()) / 16f) / 32f, 8f / 16f);
            }
            if (candle2Dunking) {
                if (((MixingCauldronTile) blockEntity).getFluidStack().getAmount() > 0)
                    candle2DippingTime--;
                candle2DryingTime = candle2DryingTimeMax;
                if (candle2DippingTime <= 0) {
                    candle2DippingTime = candle2DippingTimeMax;
                    candle2Dunking = false;
                    candle2DryingTime = candleDryingTimeStart;
                    candle2DippedTimes++;
                    chanceDecreaseLevel(candle2DecreaseAmount);
                }

                targetPos2 = new Vec3(targetPos2.x(), height + Math.sin((this.level.getGameTime() + 20f) / 14f) / 32f, 8f / 16f);
            }
            if (candle3Dunking) {
                if (((MixingCauldronTile) blockEntity).getFluidStack().getAmount() > 0)
                    candle3DippingTime--;
                candle3DryingTime = candle3DryingTimeMax;
                if (candle3DippingTime <= 0) {
                    candle3DippingTime = candle3DippingTimeMax;
                    candle3Dunking = false;
                    candle3DryingTime = candleDryingTimeStart;
                    candle3DippedTimes++;
                    chanceDecreaseLevel(candle3DecreaseAmount);
                }

                targetPos3 = new Vec3(targetPos3.x(), height + Math.sin((this.level.getGameTime() + 40f) / 15f) / 32f, 8f / 16f);
            }

            if (candle1DippedTimes >= candle1DippedTimesMax)
                targetPos1 = new Vec3(targetPos1.x(), 10f / 16f + Math.sin((this.level.getGameTime()) / 16f) / 32f, 8f / 16f);
            if (candle2DippedTimes >= candle2DippedTimesMax)
                targetPos2 = new Vec3(targetPos2.x(), 10f / 16f + Math.sin((this.level.getGameTime() + 20f) / 14f) / 32f, 8f / 16f);
            if (candle3DippedTimes >= candle3DippedTimesMax)
                targetPos3 = new Vec3(targetPos3.x(), 10f / 16f + Math.sin((this.level.getGameTime() + 40f) / 15f) / 32f, 8f / 16f);

            if (this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH) {
                targetPos1 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 180, targetPos1);
                targetPos2 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 180, targetPos2);
                targetPos3 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 180, targetPos3);
            } else if (this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
                targetPos1 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 0, targetPos1);
                targetPos2 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 0, targetPos2);
                targetPos3 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 0, targetPos3);
            } else if (this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
                targetPos1 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 90, targetPos1);
                targetPos2 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 90, targetPos2);
                targetPos3 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 90, targetPos3);
            } else if (this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                targetPos1 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 270, targetPos1);
                targetPos2 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 270, targetPos2);
                targetPos3 = rotateAroundVec(new Vec3(0.5f, 0, 0.5f), 270, targetPos3);
            }
            candlePos1 = new Vec3(
                    moveTo((float) candlePos1.x, (float) targetPos1.x(), getSpeed((float) candlePos1.x, targetPos1.x())),
                    moveTo((float) candlePos1.y, (float) targetPos1.y(), 0.75f * getSpeed((float) candlePos1.y, targetPos1.y())),
                    moveTo((float) candlePos1.z, (float) targetPos1.z(), getSpeed((float) candlePos1.z, targetPos1.z())));
            candlePos2 = new Vec3(
                    moveTo((float) candlePos2.x, (float) targetPos2.x(), getSpeed((float) candlePos2.x, targetPos2.x())),
                    moveTo((float) candlePos2.y, (float) targetPos2.y(), 0.75f * getSpeed((float) candlePos2.y, targetPos2.y())),
                    moveTo((float) candlePos2.z, (float) targetPos2.z(), getSpeed((float) candlePos2.z, targetPos2.z())));
            candlePos3 = new Vec3(
                    moveTo((float) candlePos3.x, (float) targetPos3.x(), getSpeed((float) candlePos3.x, targetPos3.x())),
                    moveTo((float) candlePos3.y, (float) targetPos3.y(), 0.75f * getSpeed((float) candlePos3.y, targetPos3.y())),
                    moveTo((float) candlePos3.z, (float) targetPos3.z(), getSpeed((float) candlePos3.z, targetPos3.z())));

        }


//            if(closestPlayerPos != null) {
//                if(degreesFlopped == 0)
//                    degreesSpun = (int)moveToAngle(degreesSpun, 270 - (int)getAngle(closestPlayerPos), 3);
//
//                degreesFlopped = moveTo(degreesFlopped, 0, 5);
//            }
//            else
//            {
//                degreesFlopped = moveTo(degreesFlopped, 90, 3);
//            }
//
//            if(degreesFlopped == 0)
//                degreesOpened = moveTo(degreesOpened, (float)(closestDist * (360 / maxDist))/4, 3);
//            else
//                degreesOpened = moveTo(degreesOpened, 90, 2);



    }

    private void chanceDecreaseLevel(int amount) {
        BlockState blockState = level.getBlockState(this.worldPosition.below());
        Random random = new Random();

        BlockEntity blockEntity = level.getBlockEntity(this.worldPosition.below());
        if(blockEntity instanceof MixingCauldronTile && !level.isClientSide()) {

            ((MixingCauldronTile) blockEntity).getFluidStack().shrink(amount);
            if (((MixingCauldronTile) blockEntity).getFluidStack().getAmount() % 10 == 1)
                ((MixingCauldronTile) blockEntity).getFluidStack().shrink(1);
            if (((MixingCauldronTile) blockEntity).getFluidStack().getAmount() % 10 == 9)
                ((MixingCauldronTile) blockEntity).getFluidStack().grow(1);
            ((MixingCauldronTile) blockEntity).sync();
            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new EmitParticlesPacket(worldPosition.below(), 10, false));

        }


    }

    @Override
    public int[] getSlotsForFace(Direction p_19238_) {
        return new int[]{0, 1, 2};
    }

    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStackIn);
    }

    public boolean canPlaceItem(int index, ItemStack stack) {

        return this.items.get(index).isEmpty();
    }

    @Override
    public boolean canTakeItemThroughFace(int p_19239_, ItemStack p_19240_, Direction p_19241_) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

//    @Override
//    public int getContainerSize() {
//        return 0;
//    }

}
