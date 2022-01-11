package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.SageBurningPlate;
import net.joefoxe.hexerei.config.HexConfig;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.particle.ModParticleTypes;
import net.joefoxe.hexerei.util.HexereiPacketHandler;
import net.joefoxe.hexerei.util.message.EmitExtinguishParticlesPacket;
import net.joefoxe.hexerei.util.message.EmitParticlesPacket;
import net.joefoxe.hexerei.util.message.TESyncPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public class SageBurningPlateTile extends RandomizableContainerBlockEntity implements WorldlyContainer, Clearable, MenuProvider {

//    public final ItemStackHandler itemHandler = createHandler();
//    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public int burnTimeMax = 20;
    public int burnTime = 20;

    public SageBurningPlateTile(BlockEntityType<?> tileEntityTypeIn, BlockPos blockPos, BlockState blockState) {
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


    public SageBurningPlateTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.SAGE_BURNING_PLATE_TILE.get(),blockPos, blockState);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < this.items.size()) {
            ItemStack itemStack = stack.copy();
            this.items.set(index, itemStack);
            this.burnTime = this.burnTimeMax;
        }

        sync();
    }

    @Override
    public ItemStack removeItem(int index, int p_59614_) {
        this.unpackLootTable((Player)null);
        if(level.getBlockState(worldPosition).getValue(BlockStateProperties.LIT)) {
            Random random = new Random();
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(BlockStateProperties.LIT, false), 11);
            this.level.playSound((Player) null, worldPosition, SoundEvents.CANDLE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, random.nextFloat() * 0.4F + 1.0F);

            sync();
            if(!level.isClientSide)
                HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new EmitExtinguishParticlesPacket(worldPosition));
        }

        ItemStack itemstack = ContainerHelper.removeItem(this.getItems(), index, p_59614_);
        sync();


        return itemstack;
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

        if (nbt.contains("burnTime",  Tag.TAG_INT))
            burnTime = nbt.getInt("burnTime");
        if (nbt.contains("burnTimeMax",  Tag.TAG_INT))
            burnTimeMax = nbt.getInt("burnTimeMax");
        super.load(nbt);

    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + Hexerei.MOD_ID + ".sage_burning_plate");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return null;
    }

    public void saveAdditional(CompoundTag compound) {
        ContainerHelper.saveAllItems(compound, this.items);

        compound.putInt("burnTime", burnTime);

        compound.putInt("burnTimeMax", burnTimeMax);
    }


    @Override
    public CompoundTag save(CompoundTag compound) {
        super.saveAdditional(compound);
//        compound.put("inv", itemHandler.serializeNBT());
//        if (this.customName != null)
//            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
        ContainerHelper.saveAllItems(compound, this.items);

        compound.putInt("burnTime", burnTime);

        compound.putInt("burnTimeMax", burnTimeMax);

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

        if (this.items.get(slot).isEmpty() && canPlaceItem(slot, stack)) {
            stack1.setCount(1);
            this.items.set(slot, stack1);
            this.burnTime = this.burnTimeMax;
            sync();
            stack.shrink(1);
            level.playSound((Player) null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, rand.nextFloat() * 0.4F + 1.0F);
            return 1;
        }

        return 0;
    }

    public int interactSageBurningPlate (Player player, BlockHitResult hit) {
        if(!player.isShiftKeyDown()) {

            if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                Random rand = new Random();
                if (this.items.get(0).isEmpty()) {
                    putItems(0, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return 1;
                }
            }

        }
        else
        {

            if (!this.items.get(0).isEmpty()) {
                player.inventory.placeItemBackInInventory(this.items.get(0).copy());
                level.playSound((Player) null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 1.0F);
                removeItem(0, 1);


            }

        }

        return 0;
    }

    public void extinguishParticles()
    {
        Random rand = new Random();
        float offsetX = 0;
        float offsetZ = 0;
        float damageOutOf5 = (getItems().get(0).getMaxDamage()-getItems().get(0).getDamageValue())/(float)getItems().get(0).getMaxDamage()*5f;
        if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH)
        {
            offsetX = -0.25f;
            if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                offsetX += 0.09;
            if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                offsetX += 0.18;
            if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                offsetX += 0.25;
            if(damageOutOf5 <= 1f && damageOutOf5 >= 0f)
                offsetX += 0.33;
        }
        if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH)
        {
            offsetX = 0.25f;
            if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                offsetX -= 0.09;
            if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                offsetX -= 0.18;
            if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                offsetX -= 0.25;
            if(damageOutOf5 <= 1f && damageOutOf5 >= 0f)
                offsetX -= 0.33;
        }
        if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST)
        {
            offsetZ = 0.25f;
            if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                offsetZ -= 0.09;
            if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                offsetZ -= 0.18;
            if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                offsetZ -= 0.25;
            if(damageOutOf5 <= 1f && damageOutOf5 >= 0f)
                offsetZ -= 0.33;
        }
        if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST)
        {
            offsetZ = -0.25f;
            if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                offsetZ += 0.09;
            if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                offsetZ += 0.18;
            if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                offsetZ += 0.25;
            if(damageOutOf5 <= 1f && damageOutOf5 >= 0f)
                offsetZ += 0.33;
        }

        level.addParticle(ParticleTypes.LARGE_SMOKE, worldPosition.getX() + 0.5f + offsetX, worldPosition.getY()+0.25f, worldPosition.getZ() + 0.5f + offsetZ, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
        level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, worldPosition.getX() + 0.5f + offsetX, worldPosition.getY()+0.25f, worldPosition.getZ() + 0.5f + offsetZ, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
        level.addParticle(ParticleTypes.SMOKE, worldPosition.getX() + 0.5f + offsetX, worldPosition.getY()+0.25f, worldPosition.getZ() + 0.5f + offsetZ, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
        level.addParticle(ParticleTypes.SMOKE, worldPosition.getX() + 0.5f + offsetX, worldPosition.getY()+0.25f, worldPosition.getZ() + 0.5f + offsetZ, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
        level.addParticle(ParticleTypes.SMOKE, worldPosition.getX() + 0.5f + offsetX, worldPosition.getY()+0.25f, worldPosition.getZ() + 0.5f + offsetZ, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);

    }

    public void emitParticles(){
        Random rand = new Random();
        if(rand.nextInt(4) == 0 && level.isClientSide) {
            float offsetX = 0;
            float offsetZ = 0;
            float damageOutOf5 = (getItems().get(0).getMaxDamage()-getItems().get(0).getDamageValue())/(float)getItems().get(0).getMaxDamage()*5f;
            if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH)
            {
                offsetX = -0.25f;
                if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                    offsetX += 0.09;
                if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                    offsetX += 0.18;
                if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                    offsetX += 0.25;
                if(damageOutOf5 <= 1f && damageOutOf5 > 0f)
                    offsetX += 0.33;
            }
            if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH)
            {
                offsetX = 0.25f;
                if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                    offsetX -= 0.09;
                if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                    offsetX -= 0.18;
                if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                    offsetX -= 0.25;
                if(damageOutOf5 <= 1f && damageOutOf5 > 0f)
                    offsetX -= 0.33;
            }
            if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST)
            {
                offsetZ = 0.25f;
                if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                    offsetZ -= 0.09;
                if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                    offsetZ -= 0.18;
                if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                    offsetZ -= 0.25;
                if(damageOutOf5 <= 1f && damageOutOf5 > 0f)
                    offsetZ -= 0.33;
            }
            if(this.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST)
            {
                offsetZ = -0.25f;
                if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                    offsetZ += 0.09;
                if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                    offsetZ += 0.18;
                if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                    offsetZ += 0.25;
                if(damageOutOf5 <= 1f && damageOutOf5 > 0f)
                    offsetZ += 0.33;
            }

                level.addParticle(ParticleTypes.SMOKE, worldPosition.getX() + 0.5f + offsetX, worldPosition.getY()+0.25f, worldPosition.getZ() + 0.5f + offsetZ, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
            if(rand.nextInt(10) == 0)
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, worldPosition.getX() + 0.5f + offsetX, worldPosition.getY()+0.25f, worldPosition.getZ() + 0.5f + offsetZ, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
        }

        if(this.getBlockState().getValue(SageBurningPlate.MODE) != 3){
            for (int i = 0; i < 360; i++) {
                Vec3 vec = new Vec3(Mth.sin((i / 360f) * (2 * Mth.PI)) * (rand.nextFloat() * HexConfig.SAGE_BURNING_PLATE_RANGE.get()), Mth.sin((rand.nextInt(360) / 360f) * (2 * Mth.PI)) * (rand.nextFloat() * HexConfig.SAGE_BURNING_PLATE_RANGE.get()), Mth.cos((i / 360f) * (2 * Mth.PI)) * (rand.nextFloat() * HexConfig.SAGE_BURNING_PLATE_RANGE.get()));

                Vec3 vec2 = new Vec3(Mth.sin((i / 360f) * (2 * Mth.PI)) * (HexConfig.SAGE_BURNING_PLATE_RANGE.get()), 0, Mth.cos((i / 360f) * (2 * Mth.PI)) * (HexConfig.SAGE_BURNING_PLATE_RANGE.get()));
                BlockPos pos2 = new BlockPos(worldPosition.getX() + 0.5f + vec2.x(), worldPosition.getY() + 0.25f + vec2.y(), worldPosition.getZ() + 0.5f + vec2.z());

                if (rand.nextInt(40) == 0 && (this.getBlockState().getValue(SageBurningPlate.MODE) == 0 || this.getBlockState().getValue(SageBurningPlate.MODE) == 1)) {
                    BlockPos pos = new BlockPos(worldPosition.getX() + 0.5f + vec.x(), worldPosition.getY() + 0.25f + vec.y(), worldPosition.getZ() + 0.5f + vec.z());

                    if ((!level.getBlockState(pos.below()).isAir() || !level.getBlockState(pos.below().below()).isAir()) && level.getBlockState(pos).isAir())
                        level.addParticle(ModParticleTypes.FOG.get(), pos.getX(), pos.getY(), pos.getZ(), (rand.nextDouble() - 0.5d) / 15d, (rand.nextDouble() + 0.5d) * 0.015d, (rand.nextDouble() - 0.5d) / 15d);

                }
                if (rand.nextInt(160) == 0 && (this.getBlockState().getValue(SageBurningPlate.MODE) == 1 || this.getBlockState().getValue(SageBurningPlate.MODE) == 2))
                    level.addParticle(ModParticleTypes.FOG.get(), pos2.getX(), pos2.getY(), pos2.getZ(), (rand.nextDouble() - 0.5d) / 15d, (rand.nextDouble() + 0.5d) * 0.015d, (rand.nextDouble() - 0.5d) / 15d);

            }
        }
    }

//    @Override
    public void tick() {

        if(this.getBlockState().getValue(SageBurningPlate.LIT)){
            if (this.burnTime <= 0) {
                if(!level.isClientSide){
                    this.items.get(0).hurt(1, new Random(), null);

                    if (this.items.get(0).getDamageValue() >= this.items.get(0).getMaxDamage()) {
                        removeItem(0, 1);
                    } else
                        sync();
                    this.burnTime = this.burnTimeMax;
                }
                else {


                }

            } else {
                this.burnTime--;
                emitParticles();
            }
        }

    }

    @Override
    public int[] getSlotsForFace(Direction p_19238_) {
        return new int[]{0};
    }

    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStackIn);
    }

    public boolean canPlaceItem(int index, ItemStack stack) {
        return this.items.get(index).isEmpty() && stack.is(ModItems.DRIED_SAGE_BUNDLE.get());
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack p_19240_, Direction p_19241_) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

}
