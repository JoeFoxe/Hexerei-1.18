package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.MixingCauldron;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
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
import java.util.Random;

public class CandleDipperTile extends BlockEntity {

//    public final ItemStackHandler itemHandler = createHandler();
//    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
//    protected NonNullList<ItemStack> items = NonNullList.withSize(8, ItemStack.EMPTY);

//    public int degreesSpun;
//    public float degreesOpened;
//    public float degreesFlopped;
//    public float pageOneRotation;
//    public float pageTwoRotation;
    public float numberOfCandles;
    public Vec3 candlePos1;
    public Vec3 candlePos2;
    public Vec3 candlePos3;
    public int candlePos1Slot;
    public int candlePos2Slot;
    public int candlePos3Slot;
    public boolean candle1Dunking;
    public boolean candle2Dunking;
    public boolean candle3Dunking;
    public int candle1DippedTimes = 0;
    public int candle2DippedTimes = 0;
    public int candle3DippedTimes = 0;
    public int candle1DunkingCooldown = 60;
    public int candle2DunkingCooldown = 60;
    public int candle3DunkingCooldown = 60;
    public int candleDunkingCooldownMax = 200;
    public int candleDunkingCooldownStart = 60;
    public int candleDunkingTimeMax = 200;
    public int candle1DunkingTime = candleDunkingTimeMax;
    public int candle2DunkingTime = candleDunkingTimeMax;
    public int candle3DunkingTime = candleDunkingTimeMax;

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
    }

    public CandleDipperTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.CANDLE_DIPPER_TILE.get(),blockPos, blockState);
    }

//    public void readInventory(CompoundTag compound) {
//        itemHandler.deserializeNBT(compound);
//    }

//    @Override
//    protected NonNullList<ItemStack> getItems() {
//        return this.items;
//    }

//    @Override
//    protected void setItems(NonNullList<ItemStack> itemsIn) {
//        this.items = itemsIn;
//    }

//    @Override
//    public void setChanged() {
//        super.setChanged();
//        this.level.sendBlockUpdated(this.pos, this.getBlockState(), this.getBlockState(),
//                Block.UPDATE_CLIENTS);
//    }

//    @Override
//    protected Component getDefaultName() {
//        return new TranslatableComponent("container." + Hexerei.MOD_ID + ".coffer");
//    }

//    public CandleDipperTile() {
//        this(ModTileEntities.CANDLE_DIPPER_TILE.get());
//    }

    @Override
    public void load(CompoundTag nbt) {
//        itemHandler.deserializeNBT(nbt.getCompound("inv"));
//        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
//        super.read(state, nbt);
//        if (nbt.contains("CustomName", 8))
//            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
            if (nbt.contains("candlePos1Slot",  Tag.TAG_INT))
                candlePos1Slot = nbt.getInt("candlePos1Slot");
            if (nbt.contains("candlePos2Slot",  Tag.TAG_INT))
                candlePos2Slot = nbt.getInt("candlePos2Slot");
            if (nbt.contains("candlePos3Slot",  Tag.TAG_INT))
                candlePos3Slot = nbt.getInt("candlePos3Slot");
            if (nbt.contains("candle1DippedTimes",  Tag.TAG_INT))
                candle1DippedTimes = nbt.getInt("candle1DippedTimes");
            if (nbt.contains("candle2DippedTimes",  Tag.TAG_INT))
                candle2DippedTimes = nbt.getInt("candle2DippedTimes");
            if (nbt.contains("candle3DippedTimes",  Tag.TAG_INT))
                candle3DippedTimes = nbt.getInt("candle3DippedTimes");
            if (nbt.contains("candle1DunkingCooldown",  Tag.TAG_INT))
                candle1DunkingCooldown = nbt.getInt("candle1DunkingCooldown");
            if (nbt.contains("candle2DunkingCooldown",  Tag.TAG_INT))
                candle2DunkingCooldown = nbt.getInt("candle2DunkingCooldown");
            if (nbt.contains("candle3DunkingCooldown",  Tag.TAG_INT))
                candle3DunkingCooldown = nbt.getInt("candle3DunkingCooldown");
            if (nbt.contains("candle1DunkingTime",  Tag.TAG_INT))
                candle1DunkingTime = nbt.getInt("candle1DunkingTime");
            if (nbt.contains("candle2DunkingTime",  Tag.TAG_INT))
                candle2DunkingTime = nbt.getInt("candle2DunkingTime");
            if (nbt.contains("candle3DunkingTime",  Tag.TAG_INT))
                candle3DunkingTime = nbt.getInt("candle3DunkingTime");
            if (nbt.contains("candle1Dunking",  Tag.TAG_INT))
                candle1Dunking = nbt.getInt("candle1Dunking") == 1;
            if (nbt.contains("candle2Dunking",  Tag.TAG_INT))
                candle2Dunking = nbt.getInt("candle2Dunking") == 1;
            if (nbt.contains("candle3Dunking",  Tag.TAG_INT))
                candle3Dunking = nbt.getInt("candle3Dunking") == 1;
            super.load(nbt);

        }


    @Override
    public CompoundTag save(CompoundTag compound) {
//        compound.put("inv", itemHandler.serializeNBT());
//        if (this.customName != null)
//            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
//        ContainerHelper.saveAllItems(compound, this.items);

        compound.putInt("candlePos1Slot", candlePos1Slot);
        compound.putInt("candlePos2Slot", candlePos2Slot);
        compound.putInt("candlePos3Slot", candlePos3Slot);

        compound.putInt("candle1DippedTimes", candle1DippedTimes);
        compound.putInt("candle2DippedTimes", candle2DippedTimes);
        compound.putInt("candle3DippedTimes", candle3DippedTimes);

        compound.putInt("candle1DunkingCooldown", candle1DunkingCooldown);
        compound.putInt("candle2DunkingCooldown", candle2DunkingCooldown);
        compound.putInt("candle3DunkingCooldown", candle3DunkingCooldown);

        compound.putInt("candle1DunkingTime", candle1DunkingTime);
        compound.putInt("candle2DunkingTime", candle2DunkingTime);
        compound.putInt("candle3DunkingTime", candle3DunkingTime);

        compound.putInt("candle1Dunking", candle1Dunking ? 1 : 0);
        compound.putInt("candle2Dunking", candle2Dunking ? 1 : 0);
        compound.putInt("candle3Dunking", candle3Dunking ? 1 : 0);

        return super.save(compound);
    }

//    @Override
//    protected AbstractContainerMenu createMenu(int id, Inventory player) {
//        return new CofferContainer(id, this.level, this.pos, player, player.player);
//    }
//
//
//    @Override
//    public void clear() {
//        super.clear();
//        this.items.clear();
//    }
//
//    private ItemStackHandler createHandler() {
//        return new ItemStackHandler(36) {
//            @Override
//            protected void onContentsChanged(int slot) {
//                setChanged();
//            }
//
//            @Override
//            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//                return true;
//            }
//
//            @Override
//            public int getSlotLimit(int slot) {
//                return 64;
//            }
//
//            @Nonnull
//            @Override
//            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
//                if(!isItemValid(slot, stack)) {
//                    return stack;
//                }
//
//                return super.insertItem(slot, stack, simulate);
//            }
//        };
//    }
//
//    @Nonnull
//    @Override
//    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//            return handler.cast();
//        }
//        return super.getCapability(cap, side);
//    }
//
//    public Item getItemInSlot(int slot) {
//        return this.itemHandler.getItem(slot).getItem();
//    }
//
//    public int getNumberOfItems() {
//
//        int num = 0;
//        for(int i = 0; i < this.itemHandler.getSlots(); i++)
//        {
//            if(this.itemHandler.getItem(i) != ItemStack.EMPTY)
//                num++;
//        }
//        return num;
//
//    }

    //TODO find how to accesstransform this
//        @Override
//    public ClientboundBlockEntityDataPacket getUpdatePacket() {
//        CompoundTag nbt = new CompoundTag();
//        this.save(nbt);
//
//        return new ClientboundBlockEntityDataPacket(this.getBlockPos(), 1, nbt);
//    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.save(new CompoundTag());
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

    //    @Override
//    public Component getDisplayName() {
//        return customName != null ? customName
//                : new TranslatableComponent("");
//    }
//
//    @Override
//    public Component getCustomName() {
//        return this.customName;
//    }
//
//    @Override
//    public boolean hasCustomName() {
//        return customName != null;
//    }
//
//    @Override
//    public Component getName() {
//        return customName;
//    }
//
//    public int getdegreesSpun() {
//        return this.degreesSpun;
//    }
//    public void setdegreesSpun(int degrees) {
//        this.degreesSpun =  degrees;
//    }
//
//    public int getButtonToggled() {
//        return this.buttonToggled;
//    }
//    public void setButtonToggled(int degrees) {
//        this.buttonToggled =  degrees;
//    }
//
//    public CompoundTag saveToNbt(CompoundTag compound) {
//        if (!this.trySaveLootTable(compound)) {
//            ContainerHelper.saveAllItems(compound, this.items, false);
//        }
//
//        return compound;
//    }
//

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

    public int interactDipper (Player player, BlockHitResult hit) {
        if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.STRING)
        {

            Random rand = new Random();
            if(candlePos1Slot == 0) {
                candlePos1Slot = 1;
                candle1DunkingCooldown = candleDunkingCooldownStart;
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                if (!player.getAbilities().instabuild)
                    player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                return 1;
            }
            else if(candlePos2Slot == 0) {
                candlePos2Slot = 1;
                candle2DunkingCooldown = candleDunkingCooldownStart;
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                if (!player.getAbilities().instabuild)
                    player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                return 1;
            }
            else if(candlePos3Slot == 0) {
                candlePos3Slot = 1;
                candle3DunkingCooldown = candleDunkingCooldownStart;
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                if (!player.getAbilities().instabuild)
                    player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                return 1;
            }
        } else if(player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())
        {

            Random rand = new Random();
            if(candlePos1Slot != 0 && candle1DippedTimes >= 3) {
                candle1DippedTimes = 0;
                candlePos1Slot = 0;
                candle1DunkingTime = candleDunkingTimeMax;
                candle1Dunking = false;
                candle1DunkingCooldown = candleDunkingCooldownMax;
                player.inventory.placeItemBackInInventory(new ItemStack(ModBlocks.CANDLE.get()), level.isClientSide);

                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                return 1;

            }
            else if(candlePos2Slot != 0 && candle2DippedTimes >= 3) {
                candle2DippedTimes = 0;
                candlePos2Slot = 0;
                candle2DunkingTime = candleDunkingTimeMax;
                candle2Dunking = false;
                candle2DunkingCooldown = candleDunkingCooldownMax;
                player.inventory.placeItemBackInInventory(new ItemStack(ModBlocks.CANDLE.get()), level.isClientSide);

                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                return 1;

            }
            else if(candlePos3Slot != 0 && candle3DippedTimes >= 3) {
                candle3DippedTimes = 0;
                candlePos3Slot = 0;
                candle3DunkingTime = candleDunkingTimeMax;
                candle3Dunking = false;
                candle3DunkingCooldown = candleDunkingCooldownMax;
                player.inventory.placeItemBackInInventory(new ItemStack(ModBlocks.CANDLE.get()), level.isClientSide);

                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
                return 1;

            }


        }

        return 0;
    }

//    @Override
    public void tick() {

            closestPlayerPos = null;
            closestDist = maxDist;
            numberOfCandles = 0;

            Vec3 targetPos1 = new Vec3(4f / 16f, 0f / 16f, 1f / 16f);
            Vec3 targetPos2 = new Vec3(8f / 16f, 0f / 16f, 1f / 16f);
            Vec3 targetPos3 = new Vec3(12f / 16f, 0f / 16f, 1f / 16f);


            BlockState blockState = level.getBlockState(this.worldPosition.below());
            if(blockState.getBlock() instanceof MixingCauldron) {
                if (candlePos1Slot > 0 && candle1DippedTimes < 3) {
                    if (blockState.getValue(MixingCauldron.LEVEL) > 0 && blockState.getValue(MixingCauldron.FLUID) == LiquidType.TALLOW)
                        candle1DunkingCooldown--;
                    if (candle1DunkingCooldown <= 0) {
                        candle1DunkingCooldown = candleDunkingCooldownMax;
                        candle1Dunking = true;
                    }
                    targetPos1 = new Vec3(targetPos1.x(), 5f / 16f + Math.sin((this.level.getGameTime()) / 16f) / 32f, 8f / 16f);
                }

                if (candlePos2Slot > 0 && candle2DippedTimes < 3) {
                    if (blockState.getValue(MixingCauldron.LEVEL) > 0 && blockState.getValue(MixingCauldron.FLUID) == LiquidType.TALLOW)
                        candle2DunkingCooldown--;
                    if (candle2DunkingCooldown <= 0) {
                        candle2DunkingCooldown = candleDunkingCooldownMax;
                        candle2Dunking = true;
                    }
                    targetPos2 = new Vec3(targetPos2.x(), 5f / 16f + Math.sin((this.level.getGameTime() + 20f) / 14f) / 32f, 8f / 16f);
                }
                if (candlePos3Slot > 0 && candle3DippedTimes < 3) {
                    if (blockState.getValue(MixingCauldron.LEVEL) > 0 && blockState.getValue(MixingCauldron.FLUID) == LiquidType.TALLOW)
                        candle3DunkingCooldown--;
                    if (candle3DunkingCooldown <= 0) {
                        candle3DunkingCooldown = candleDunkingCooldownMax;
                        candle3Dunking = true;
                    }
                    targetPos3 = new Vec3(targetPos3.x(), 5f / 16f + Math.sin((this.level.getGameTime() + 40f) / 15f) / 32f, 8f / 16f);
                }

                float dist;
                if (candle1Dunking) {
                    if (blockState.getValue(MixingCauldron.LEVEL) > 0 && blockState.getValue(MixingCauldron.FLUID) == LiquidType.TALLOW)
                        candle1DunkingTime--;
                    candle1DunkingCooldown = candleDunkingCooldownMax;
                    if (candle1DunkingTime <= 0) {
                        candle1DunkingTime = candleDunkingTimeMax;
                        candle1Dunking = false;
                        candle1DunkingCooldown = candleDunkingCooldownStart;
                        candle1DippedTimes++;
                        chanceDecreaseLevel(0.16f);
                    }
                    dist = (3 - blockState.getValue(MixingCauldron.LEVEL));
                    if (blockState.getValue(MixingCauldron.LEVEL) == 0)
                        dist = -2;
                    targetPos1 = new Vec3(targetPos1.x(), -(dist * 3f) / 16f + Math.sin((this.level.getGameTime()) / 16f) / 32f, 8f / 16f);
                }
                if (candle2Dunking) {
                    if (blockState.getValue(MixingCauldron.LEVEL) > 0 && blockState.getValue(MixingCauldron.FLUID) == LiquidType.TALLOW)
                        candle2DunkingTime--;
                    candle2DunkingCooldown = candleDunkingCooldownMax;
                    if (candle2DunkingTime <= 0) {
                        candle2DunkingTime = candleDunkingTimeMax;
                        candle2Dunking = false;
                        candle2DunkingCooldown = candleDunkingCooldownStart;
                        candle2DippedTimes++;
                        chanceDecreaseLevel(0.16f);
                    }
                    dist = (3 - blockState.getValue(MixingCauldron.LEVEL));
                    if (blockState.getValue(MixingCauldron.LEVEL) == 0)
                        dist = -2;
                    targetPos2 = new Vec3(targetPos2.x(), -(dist * 3f) / 16f + Math.sin((this.level.getGameTime() + 20f) / 14f) / 32f, 8f / 16f);
                }
                if (candle3Dunking) {
                    if (blockState.getValue(MixingCauldron.LEVEL) > 0 && blockState.getValue(MixingCauldron.FLUID) == LiquidType.TALLOW)
                        candle3DunkingTime--;
                    candle3DunkingCooldown = candleDunkingCooldownMax;
                    if (candle3DunkingTime <= 0) {
                        candle3DunkingTime = candleDunkingTimeMax;
                        candle3Dunking = false;
                        candle3DunkingCooldown = candleDunkingCooldownStart;
                        candle3DippedTimes++;
                        chanceDecreaseLevel(0.16f);
                    }
                    dist = (3 - blockState.getValue(MixingCauldron.LEVEL));
                    if (blockState.getValue(MixingCauldron.LEVEL) == 0)
                        dist = -2;
                    targetPos3 = new Vec3(targetPos3.x(), -(dist * 3f) / 16f + Math.sin((this.level.getGameTime() + 40f) / 15f) / 32f, 8f / 16f);
                }

                if (candle1DippedTimes >= 3)
                    targetPos1 = new Vec3(targetPos1.x(), 10f / 16f + Math.sin((this.level.getGameTime()) / 16f) / 32f, 8f / 16f);
                if (candle2DippedTimes >= 3)
                    targetPos2 = new Vec3(targetPos2.x(), 10f / 16f + Math.sin((this.level.getGameTime() + 20f) / 14f) / 32f, 8f / 16f);
                if (candle3DippedTimes >= 3)
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

    private void chanceDecreaseLevel(float percent) {
        BlockState blockState = level.getBlockState(this.worldPosition.below());
        Random random = new Random();

        if(blockState.getBlock() instanceof MixingCauldron && !level.isClientSide())
        {
            if(random.nextFloat() <= percent)
                ((MixingCauldron)blockState.getBlock()).subtractLevel(level, this.worldPosition.below());
        }


    }

//    @Override
//    public int getContainerSize() {
//        return 0;
//    }

}
