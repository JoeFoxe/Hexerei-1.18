package net.joefoxe.hexerei.tileentity;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class BookOfShadowsAltarTile extends BlockEntity {

//    public final ItemStackHandler itemHandler = createHandler();
//    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
//    protected NonNullList<ItemStack> items = NonNullList.withSize(8, ItemStack.EMPTY);

    public int degreesSpun;
    public float degreesOpened;
    public float degreesFlopped;
    public float pageOneRotation;
    public float pageTwoRotation;
    public float numberOfCandles;
    public float maxCandles = 3;
    public BlockPos candlePos1;
    public BlockPos candlePos2;
    public BlockPos candlePos3;
    public int candlePos1Slot;
    public int candlePos2Slot;
    public int candlePos3Slot;
    public float degreesSpunCandles;

    public Vec3 closestPlayerPos;
    public double closestDist;

    public final double maxDist = 8;


    public BookOfShadowsAltarTile(BlockEntityType<?> tileEntityTypeIn, BlockPos blockPos, BlockState blockState) {
        super(tileEntityTypeIn, blockPos, blockState);

        pageOneRotation = 0;
        pageTwoRotation = 0;
        degreesFlopped = 90;
        degreesOpened = 90; // reversed because the model is made so the book is opened from the start so offseting 90 degrees from the start will close the book
        degreesSpun = 0;
        candlePos1Slot = 0;
        candlePos2Slot = 0;
        candlePos3Slot = 0;
    }

    public BookOfShadowsAltarTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.BOOK_OF_SHADOWS_ALTAR_TILE.get(),blockPos, blockState);
    }

    //    public BookOfShadowsAltarTile() {
//        this(ModTileEntities.BOOK_OF_SHADOWS_ALTAR_TILE.get());
//    }

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



//    @Override
//    public void read(BlockState state, CompoundTag nbt) {
//        itemHandler.deserializeNBT(nbt.getCompound("inv"));
//        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
//        super.read(state, nbt);
//        if (nbt.contains("CustomName", 8))
//            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
//    }


//    @Override
//    public CompoundTag write(CompoundTag compound) {
//        compound.put("inv", itemHandler.serializeNBT());
//        if (this.customName != null)
//            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
//        ContainerHelper.saveAllItems(compound, this.items);
//        return super.write(compound);
//    }
//
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
//    @Override
//    public ClientboundBlockEntityDataPacket getUpdatePacket() {
//        CompoundTag nbt = new CompoundTag();
//        this.write(nbt);
//
//        return new ClientboundBlockEntityDataPacket(this.getPos(), 1, nbt);
//    }
//
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//        this.read(this.level.getBlockState(this.pos) ,pkt.getNbtCompound());
//    }
//
//    @Override
//    public CompoundTag getUpdateTag() {
//        return this.write(new CompoundTag());
//    }
//
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
        float angle = (float) Math.toDegrees(Math.atan2(pos.z() - this.worldPosition.getZ() - 0.5f, pos.x() - this.worldPosition.getX() - 0.5f));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    private boolean getCandle(Level world, BlockPos pos) {
        return world.getBlockEntity(pos) instanceof CandleTile;
    }

//    @Override
    public void tick() {
        if(level.isClientSide) {

//            PlayerList list = Objects.requireNonNull(ServerLifecycleHooks.getCurrentServer().getPlayerList());

            closestPlayerPos = null;
            closestDist = maxDist;
            numberOfCandles = 0;

            candlePos1 = new BlockPos(0, 0, 0);
            candlePos2 = new BlockPos(0, 0, 0);
            candlePos3 = new BlockPos(0, 0, 0);

            for(int k = -1; k <= 1; ++k) {
                for(int l = -1; l <= 1; ++l) {
                    if ((k != 0 || l != 0) && level.isEmptyBlock(worldPosition.offset(l, 0, k)) && level.isEmptyBlock(worldPosition.offset(l, 1, k))) {
                        if(getCandle(level, worldPosition.offset(l * 2, 0, k * 2)) && numberOfCandles < maxCandles)
                        {
                            for(int i = 0; i < ((CandleTile)level.getBlockEntity(worldPosition.offset(l * 2, 0, k * 2))).numberOfCandles; i++) {

                                if ((i == 0 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k * 2))).candleLit1 == 1)
                                        || (i == 1 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k * 2))).candleLit2 == 1)
                                        || (i == 2 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k * 2))).candleLit3 == 1)
                                        || (i == 3 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k * 2))).candleLit4 == 1)) {
                                    if (numberOfCandles == 0) {
                                        candlePos1 = worldPosition.offset(l * 2, 0, k * 2);
                                        candlePos1Slot = i;
                                    }
                                    if (numberOfCandles == 1) {
                                        candlePos2 = worldPosition.offset(l * 2, 0, k * 2);
                                        candlePos2Slot = i;
                                    }
                                    if (numberOfCandles == 2) {
                                        candlePos3 = worldPosition.offset(l * 2, 0, k * 2);
                                        candlePos3Slot = i;
                                    }
                                    numberOfCandles++;
                                }
                            }

                        }
                        if(getCandle(level, worldPosition.offset(l * 2, 1, k * 2)) && numberOfCandles < maxCandles)
                        {

                            for(int i = 0; i < ((CandleTile)level.getBlockEntity(worldPosition.offset(l * 2, 1, k * 2))).numberOfCandles; i++) {

                                if ((i == 0 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k * 2))).candleLit1 == 1)
                                        || (i == 1 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k * 2))).candleLit2 == 1)
                                        || (i == 2 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k * 2))).candleLit3 == 1)
                                        || (i == 3 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k * 2))).candleLit4 == 1)) {
                                    if (numberOfCandles == 0) {
                                        candlePos1 = worldPosition.offset(l * 2, 1, k * 2);
                                        candlePos1Slot = i;
                                    }
                                    if (numberOfCandles == 1) {
                                        candlePos2 = worldPosition.offset(l * 2, 1, k * 2);
                                        candlePos2Slot = i;
                                    }
                                    if (numberOfCandles == 2) {
                                        candlePos3 = worldPosition.offset(l * 2, 1, k * 2);
                                        candlePos3Slot = i;
                                    }
                                    numberOfCandles++;
                                }
                            }
                        }

                        if (l != 0 && k != 0) {

                            if(getCandle(level, worldPosition.offset(l * 2, 0, k)) && numberOfCandles < maxCandles)
                            {

                                for(int i = 0; i < ((CandleTile)level.getBlockEntity(worldPosition.offset(l * 2, 0, k))).numberOfCandles; i++) {

                                    if ((i == 0 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k))).candleLit1 == 1)
                                            || (i == 1 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k))).candleLit2 == 1)
                                            || (i == 2 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k))).candleLit3 == 1)
                                            || (i == 3 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 0, k))).candleLit4 == 1)) {
                                        if (numberOfCandles == 0) {
                                            candlePos1 = worldPosition.offset(l * 2, 0, k);
                                            candlePos1Slot = i;
                                        }
                                        if (numberOfCandles == 1) {
                                            candlePos2 = worldPosition.offset(l * 2, 0, k);
                                            candlePos2Slot = i;
                                        }
                                        if (numberOfCandles == 2) {
                                            candlePos3 = worldPosition.offset(l * 2, 0, k);
                                            candlePos3Slot = i;
                                        }
                                        numberOfCandles++;
                                    }
                                }
                            }
                            if(getCandle(level, worldPosition.offset(l * 2, 1, k)) && numberOfCandles < maxCandles)
                            {

                                for(int i = 0; i < ((CandleTile)level.getBlockEntity(worldPosition.offset(l * 2, 1, k))).numberOfCandles; i++) {

                                    if ((i == 0 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k))).candleLit1 == 1)
                                            || (i == 1 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k))).candleLit2 == 1)
                                            || (i == 2 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k))).candleLit3 == 1)
                                            || (i == 3 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l * 2, 1, k))).candleLit4 == 1)) {
                                        if (numberOfCandles == 0) {
                                            candlePos1 = worldPosition.offset(l * 2, 1, k);
                                            candlePos1Slot = i;
                                        }
                                        if (numberOfCandles == 1) {
                                            candlePos2 = worldPosition.offset(l * 2, 1, k);
                                            candlePos2Slot = i;
                                        }
                                        if (numberOfCandles == 2) {
                                            candlePos3 = worldPosition.offset(l * 2, 1, k);
                                            candlePos3Slot = i;
                                        }
                                        numberOfCandles++;
                                    }
                                }

                            }
                            if(getCandle(level, worldPosition.offset(l, 0, k * 2)) && numberOfCandles < maxCandles)
                            {

                                for(int i = 0; i < ((CandleTile)level.getBlockEntity(worldPosition.offset(l, 0, k * 2))).numberOfCandles; i++) {

                                    if ((i == 0 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 0, k * 2))).candleLit1 == 1)
                                            || (i == 1 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 0, k * 2))).candleLit2 == 1)
                                            || (i == 2 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 0, k * 2))).candleLit3 == 1)
                                            || (i == 3 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 0, k * 2))).candleLit4 == 1)) {
                                        if (numberOfCandles == 0) {
                                            candlePos1 = worldPosition.offset(l, 0, k * 2);
                                            candlePos1Slot = i;
                                        }
                                        if (numberOfCandles == 1) {
                                            candlePos2 = worldPosition.offset(l, 0, k * 2);
                                            candlePos2Slot = i;
                                        }
                                        if (numberOfCandles == 2) {
                                            candlePos3 = worldPosition.offset(l, 0, k * 2);
                                            candlePos3Slot = i;
                                        }
                                        numberOfCandles++;
                                    }
                                }
                            }
                            if(getCandle(level, worldPosition.offset(l, 1, k * 2)) && numberOfCandles < maxCandles)
                            {

                                for(int i = 0; i < ((CandleTile)level.getBlockEntity(worldPosition.offset(l, 1, k * 2))).numberOfCandles; i++) {

                                    if ((i == 0 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 1, k * 2))).candleLit1 == 1)
                                            || (i == 1 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 1, k * 2))).candleLit2 == 1)
                                            || (i == 2 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 1, k * 2))).candleLit3 == 1)
                                            || (i == 3 && ((CandleTile) level.getBlockEntity(worldPosition.offset(l, 1, k * 2))).candleLit4 == 1)) {
                                        if (numberOfCandles == 0) {
                                            candlePos1 = worldPosition.offset(l, 1, k * 2);
                                            candlePos1Slot = i;
                                        }
                                        if (numberOfCandles == 1) {
                                            candlePos2 = worldPosition.offset(l, 1, k * 2);
                                            candlePos2Slot = i;
                                        }
                                        if (numberOfCandles == 2) {
                                            candlePos3 = worldPosition.offset(l, 1, k * 2);
                                            candlePos3Slot = i;
                                        }
                                        numberOfCandles++;
                                    }
                                }
                            }

                        }
                    }
                }
            }

//            System.out.println(candlePos1);
//            System.out.println(candlePos2);
//            System.out.println(candlePos3);
//            System.out.println(numberOfCandles);
//            System.out.println("-------");

            if(numberOfCandles>=0)

            if(numberOfCandles >= 1)
            {
                degreesSpunCandles = moveToAngle(degreesSpunCandles, degreesSpunCandles + 1, 0.025f);

                if(candlePos1Slot == 0) {

                    ((CandleTile) level.getBlockEntity(candlePos1)).candleReturn1 = false;
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX1 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosX1, (worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX1) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY1 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosY1, (worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY1) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ1 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ1, (worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ1) / 3f));
                }
                if(candlePos1Slot == 1) {
                    ((CandleTile) level.getBlockEntity(candlePos1)).candleReturn2 = false;
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX2 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosX2, (worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX2) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY2 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosY2, (worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY2) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ2 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ2, (worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ2) / 3f));
                }
                if(candlePos1Slot == 2) {
                    ((CandleTile) level.getBlockEntity(candlePos1)).candleReturn3 = false;
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX3 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosX3, (worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX3) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY3 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosY3, (worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY3) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ3 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ3, (worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ3) / 3f));
                }
                if(candlePos1Slot == 3) {
                    ((CandleTile) level.getBlockEntity(candlePos1)).candleReturn4 = false;
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX4 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosX4, (worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos1.getX() + (float)Math.sin(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosX4) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY4 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosY4, (worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos1.getY() + 1f + (float)Math.sin(level.getGameTime()/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosY4) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ4 = moveTo(((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ4, (worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos1.getZ() + (float)Math.cos(degreesSpunCandles) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos1)).candlePosZ4) / 3f));
                }
            }
            if(numberOfCandles >= 2)
            {
                if(candlePos2Slot == 0) {
                    ((CandleTile) level.getBlockEntity(candlePos2)).candleReturn1 = false;
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX1 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosX1, (worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX1) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY1 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosY1, (worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY1) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ1 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ1, (worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ1) / 3f));
                }
                if(candlePos2Slot == 1) {
                    ((CandleTile) level.getBlockEntity(candlePos2)).candleReturn2 = false;
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX2 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosX2, (worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX2) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY2 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosY2, (worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY2) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ2 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ2, (worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ2) / 3f));
                }
                if(candlePos2Slot == 2) {
                    ((CandleTile) level.getBlockEntity(candlePos2)).candleReturn3 = false;
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX3 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosX3, (worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX3) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY3 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosY3, (worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY3) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ3 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ3, (worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ3) / 3f));
                }
                if(candlePos2Slot == 3) {
                    ((CandleTile) level.getBlockEntity(candlePos2)).candleReturn4 = false;
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX4 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosX4, (worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos2.getX() + (float)Math.sin(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosX4) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY4 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosY4, (worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos2.getY() + 1f + (float)Math.sin((level.getGameTime() + 10)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosY4) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ4 = moveTo(((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ4, (worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos2.getZ() + (float)Math.cos(degreesSpunCandles + (numberOfCandles == 2 ? Math.PI : Math.PI*2f/3f)) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos2)).candlePosZ4) / 3f));
                }
            }
            if(numberOfCandles >= 3)
            {
                if(candlePos3Slot == 0) {
                    ((CandleTile) level.getBlockEntity(candlePos3)).candleReturn1 = false;
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX1 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosX1, (worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX1) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY1 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosY1, (worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY1) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ1 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ1, (worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ1) / 3f));
                }
                if(candlePos3Slot == 1) {
                    ((CandleTile) level.getBlockEntity(candlePos3)).candleReturn2 = false;
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX2 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosX2, (worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX2) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY2 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosY2, (worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY2) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ2 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ2, (worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ2) / 3f));
                }
                if(candlePos3Slot == 2) {
                    ((CandleTile) level.getBlockEntity(candlePos3)).candleReturn3 = false;
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX3 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosX3, (worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX3) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY3 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosY3, (worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY3) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ3 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ3, (worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ3) / 3f));
                }
                if(candlePos3Slot == 3) {
                    ((CandleTile) level.getBlockEntity(candlePos3)).candleReturn4 = false;
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX4 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosX4, (worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getX() - candlePos3.getX() + (float)Math.sin(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosX4) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY4 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosY4, (worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10), 0.02f + 0.08f * (Math.abs((worldPosition.getY() - candlePos3.getY() + 1f + (float)Math.sin((level.getGameTime() + 20)/10f)/10) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosY4) / 3f));
                    ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ4 = moveTo(((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ4, (worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + Math.PI*2f/3f*2f) * 1.25f), 0.02f + 0.20f * (Math.abs((worldPosition.getZ() - candlePos3.getZ() + (float)Math.cos(degreesSpunCandles + (Math.PI*2f/3f*2f) * 1.25f) * 1.25f) - ((CandleTile) level.getBlockEntity(candlePos3)).candlePosZ4) / 3f));
                }
            }


            Player playerEntity = this.level.getNearestPlayer(this.worldPosition.getX(),this.worldPosition.getY(),this.worldPosition.getZ(), maxDist, false);
            if(this.level.isClientSide() && playerEntity != null) {
                if (Math.floor(getDistanceToEntity(playerEntity, this.worldPosition)) < maxDist) {
                    if (Math.floor(getDistanceToEntity(playerEntity, this.worldPosition)) < closestDist) {
                        closestDist = (getDistanceToEntity(playerEntity, this.worldPosition));
                        closestPlayerPos = playerEntity.position();
                    }
                }
            }


            if(closestPlayerPos != null) {
                if(degreesFlopped == 0)
                    degreesSpun = (int)moveToAngle(degreesSpun, 270 - (int)getAngle(closestPlayerPos), 3);

                degreesFlopped = moveTo(degreesFlopped, 0, 5);
            }
            else
            {
                degreesFlopped = moveTo(degreesFlopped, 90, 3);
            }

            if(degreesFlopped == 0)
                degreesOpened = moveTo(degreesOpened, (float)(closestDist * (360 / maxDist))/4, 3);
            else
                degreesOpened = moveTo(degreesOpened, 90, 2);


        }

    }

//    @Override
//    public int getContainerSize() {
//        return 0;
//    }

}
