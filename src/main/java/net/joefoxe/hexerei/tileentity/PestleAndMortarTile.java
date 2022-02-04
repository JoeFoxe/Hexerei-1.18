package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.data.recipes.ModRecipeTypes;
import net.joefoxe.hexerei.data.recipes.PestleAndMortarRecipe;
import net.joefoxe.hexerei.util.HexereiPacketHandler;
import net.joefoxe.hexerei.util.HexereiTags;
import net.joefoxe.hexerei.util.message.TESyncPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class PestleAndMortarTile extends RandomizableContainerBlockEntity implements WorldlyContainer, Clearable, MenuProvider {

//    public final ItemStackHandler itemHandler = createHandler();
//    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    protected NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    public int craftDelay;
    public static final int craftDelayMax = 100;
    public boolean crafted = false;
    public boolean crafting = false;
    public boolean grindSoundPlayed = false;
    public int grindingTimeMax = 200;
    public int grindingTime = 200;
    public ItemStack output = ItemStack.EMPTY;

    public PestleAndMortarTile(BlockEntityType<?> tileEntityTypeIn, BlockPos blockPos, BlockState blockState) {
        super(tileEntityTypeIn, blockPos, blockState);
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


    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
//        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//            return handler.cast();
//        }

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

    public ItemStack getItemStackInSlot(int slot) {
        return this.items.get(slot);
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


    public PestleAndMortarTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.PESTLE_AND_MORTAR_TILE.get(),blockPos, blockState);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < this.items.size()) {
            ItemStack itemStack = stack.copy();
            this.items.set(index, itemStack);
            if(index != 5)
                this.grindingTime = this.grindingTimeMax;
        }

        sync();
    }

    @Override
    public ItemStack removeItem(int index, int p_59614_) {
        this.unpackLootTable((Player)null);
        ItemStack itemstack = ContainerHelper.removeItem(this.getItems(), index, p_59614_);
        if (!itemstack.isEmpty()) {
            this.setChanged();
        }

        sync();

        return itemstack;
    }

    public void craft(){
        SimpleContainer inv = new SimpleContainer(5);
        for (int i = 0; i < 5; i++) {
            inv.setItem(i, this.items.get(i));
        }

        Optional<PestleAndMortarRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.PESTLE_AND_MORTAR_RECIPE, inv, level);

        BlockEntity blockEntity = level.getBlockEntity(this.worldPosition);
        AtomicBoolean matches = new AtomicBoolean(false);
        if(blockEntity instanceof PestleAndMortarTile) {
            recipe.ifPresent(iRecipe -> {
                ItemStack recipeOutput = iRecipe.getResultItem();
                this.output = recipeOutput;

                matches.set(true);
                if(((PestleAndMortarTile) blockEntity).getItemInSlot(5) == Items.AIR && !this.crafting) {
                    this.crafting = true;
                    this.grindingTimeMax = iRecipe.getGrindingTime();
                    this.grindingTime = this.grindingTimeMax;
                    sync();


                }

            });
        }
        if(!matches.get())
        {
            if(this.crafting){
                this.crafting = false;
                sync();
            }
        }
//

    }


    private void craftTheItem(ItemStack output) {
        this.items.set(0, ItemStack.EMPTY);
        this.items.set(1, ItemStack.EMPTY);
        this.items.set(2, ItemStack.EMPTY);
        this.items.set(3, ItemStack.EMPTY);
        this.items.set(4, ItemStack.EMPTY);
        this.items.set(5, output);
    }


    @Override
    public void load(CompoundTag nbt) {
//        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ContainerHelper.loadAllItems(nbt, this.items);
        }


//        if (nbt.contains("CustomName", 8))
//            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));

        if (nbt.contains("grindingTime",  Tag.TAG_INT))
            grindingTime = nbt.getInt("grindingTime");
        if (nbt.contains("grindingTimeMax",  Tag.TAG_INT))
            grindingTimeMax = nbt.getInt("grindingTimeMax");
        if (nbt.contains("crafting",  Tag.TAG_INT))
            crafting = nbt.getInt("crafting") == 1;
        if (nbt.contains("crafted",  Tag.TAG_INT))
            crafted = nbt.getInt("crafted") == 1;
        super.load(nbt);

    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + Hexerei.MOD_ID + ".pestle_and_mortar");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return null;
    }

    public void saveAdditional(CompoundTag compound) {
        ContainerHelper.saveAllItems(compound, this.items);
//        compound.put("inv", itemHandler.serializeNBT());

        compound.putInt("grindingTime", grindingTime);

        compound.putInt("grindingTimeMax", grindingTimeMax);

        compound.putInt("crafted", crafted ? 1 : 0);

        compound.putInt("crafting", crafting ? 1 : 0);
    }


//    @Override
    public CompoundTag save(CompoundTag compound) {
        super.saveAdditional(compound);
//        compound.put("inv", itemHandler.serializeNBT());
//        if (this.customName != null)
//            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
        ContainerHelper.saveAllItems(compound, this.items);

        compound.putInt("grindingTime", grindingTime);

        compound.putInt("grindingTimeMax", grindingTimeMax);

        compound.putInt("crafted", crafted ? 1 : 0);

        compound.putInt("crafting", crafting ? 1 : 0);

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
        ItemStack stack1 = stack.copy();
        Random rand = new Random();

        if (this.items.get(slot).isEmpty()) {
            stack1.setCount(1);
            this.items.set(slot, stack1);
            this.grindingTime = this.grindingTimeMax;
            sync();
            stack.shrink(1);
            level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
            return 1;
        }


        return 0;
    }

    public int interactPestleAndMortar (Player player, BlockHitResult hit) {
        if(!player.isShiftKeyDown()) {

            if(!this.items.get(5).isEmpty()){
                player.inventory.placeItemBackInInventory(this.items.get(5).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(5, ItemStack.EMPTY);
            }
            else
            if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                Random rand = new Random();
                if (this.items.get(0).isEmpty()) {
                    putItems(0, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return 1;
                } else if (this.items.get(1).isEmpty()) {
                    putItems(1, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return 1;
                } else if (this.items.get(2).isEmpty()) {
                    putItems(2, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return 1;
                } else if (this.items.get(3).isEmpty()) {
                    putItems(3, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return 1;
                } else if (this.items.get(4).isEmpty()) {
                    putItems(4, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return 1;
                }
            }


        }
        else
        {
            if(!this.items.get(5).isEmpty()){
                player.inventory.placeItemBackInInventory(this.items.get(5).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                this.items.set(5, ItemStack.EMPTY);
            }

            if(!crafting){
                if (!this.items.get(0).isEmpty()) {
                    player.inventory.placeItemBackInInventory(this.items.get(0).copy());
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                    this.items.set(0, ItemStack.EMPTY);
                    output = ItemStack.EMPTY;
                }
                if (!this.items.get(1).isEmpty()) {
                    player.inventory.placeItemBackInInventory(this.items.get(1).copy());
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                    this.items.set(1, ItemStack.EMPTY);
                    output = ItemStack.EMPTY;
                }
                if (!this.items.get(2).isEmpty()) {
                    player.inventory.placeItemBackInInventory(this.items.get(2).copy());
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                    this.items.set(2, ItemStack.EMPTY);
                    output = ItemStack.EMPTY;
                }
                if (!this.items.get(3).isEmpty()) {
                    player.inventory.placeItemBackInInventory(this.items.get(3).copy());
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                    this.items.set(3, ItemStack.EMPTY);
                    output = ItemStack.EMPTY;
                }
                if (!this.items.get(4).isEmpty()) {
                    player.inventory.placeItemBackInInventory(this.items.get(4).copy());
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                    this.items.set(4, ItemStack.EMPTY);
                    output = ItemStack.EMPTY;
                }
            }
        }

        return 0;
    }

//    @Override
    public void tick() {

        if(level instanceof ServerLevel) {
            craft();
        }

        if(crafting){
            if (this.grindingTime <= 0) {
                Random rand = new Random();
                if(level instanceof ServerLevel)
                    craftTheItem(output);
                //for setting a cooldown on crafting so the animations can take place
                this.crafted = true;
                this.crafting = false;
                sync();


            } else {
                this.grindingTime--;
                Random rand = new Random();
                float craftPercent2 = (this.grindingTimeMax - this.grindingTime) / 100f;
                double pestleYOffset = (Math.pow(Mth.sin( craftPercent2 * 3.14f * 5 - 1.2f), 4))/4f;
                if(pestleYOffset < 0.1){
                    if(!this.grindSoundPlayed){
                        level.playSound((Player) null, worldPosition, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.1F, level.random.nextFloat() * 0.4F + 2.1F);
                        this.grindSoundPlayed = true;
                    }
                    if (!this.items.get(0).isEmpty() && rand.nextInt(4) == 0)
                        level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.items.get(0)), worldPosition.getX() + 0.45f + rand.nextFloat() * 0.1f, worldPosition.getY() + 0.2d, worldPosition.getZ() + 0.45f + rand.nextFloat() * 0.1f, (rand.nextDouble() - 0.5d) / 15d, (rand.nextDouble() + 0.5d) * 0.15d, (rand.nextDouble() - 0.5d) / 15d);
                    if (!this.items.get(1).isEmpty() && rand.nextInt(4) == 0)
                        level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.items.get(1)), worldPosition.getX() + 0.45f + rand.nextFloat() * 0.1f, worldPosition.getY() + 0.2d, worldPosition.getZ() + 0.45f + rand.nextFloat() * 0.1f, (rand.nextDouble() - 0.5d) / 15d, (rand.nextDouble() + 0.5d) * 0.15d, (rand.nextDouble() - 0.5d) / 15d);
                    if (!this.items.get(2).isEmpty() && rand.nextInt(4) == 0)
                        level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.items.get(2)), worldPosition.getX() + 0.45f + rand.nextFloat() * 0.1f, worldPosition.getY() + 0.2d, worldPosition.getZ() + 0.45f + rand.nextFloat() * 0.1f, (rand.nextDouble() - 0.5d) / 15d, (rand.nextDouble() + 0.5d) * 0.15d, (rand.nextDouble() - 0.5d) / 15d);
                    if (!this.items.get(3).isEmpty() && rand.nextInt(4) == 0)
                        level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.items.get(3)), worldPosition.getX() + 0.45f + rand.nextFloat() * 0.1f, worldPosition.getY() + 0.2d, worldPosition.getZ() + 0.45f + rand.nextFloat() * 0.1f, (rand.nextDouble() - 0.5d) / 15d, (rand.nextDouble() + 0.5d) * 0.15d, (rand.nextDouble() - 0.5d) / 15d);
                    if (!this.items.get(4).isEmpty() && rand.nextInt(4) == 0)
                        level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.items.get(4)), worldPosition.getX() + 0.45f + rand.nextFloat() * 0.1f, worldPosition.getY() + 0.2d, worldPosition.getZ() + 0.45f + rand.nextFloat() * 0.1f, (rand.nextDouble() - 0.5d) / 15d, (rand.nextDouble() + 0.5d) * 0.15d, (rand.nextDouble() - 0.5d) / 15d);
                }
                else
                {
                    this.grindSoundPlayed = false;
                }
            }
        }

    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if(direction == Direction.DOWN)
            return new int[]{5};
        return new int[]{0, 1, 2, 3, 4};
    }

    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return index != 5 && this.canPlaceItem(index, itemStackIn);
    }

    public boolean canPlaceItem(int index, ItemStack stack) {
        return this.items.get(index).isEmpty();
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack p_19240_, Direction p_19241_) {

        return (index == 5);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

}
