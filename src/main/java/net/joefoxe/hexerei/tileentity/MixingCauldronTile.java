package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.MixingCauldron;
import net.joefoxe.hexerei.container.MixingCauldronContainer;
import net.joefoxe.hexerei.data.recipes.MixingCauldronRecipe;
import net.joefoxe.hexerei.data.recipes.ModRecipeTypes;
import net.joefoxe.hexerei.fluid.ModFluids;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.particle.ModParticleTypes;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.joefoxe.hexerei.tileentity.renderer.MixingCauldronRenderer;
import net.joefoxe.hexerei.util.ClientProxy;
import net.joefoxe.hexerei.util.HexereiPacketHandler;
import net.joefoxe.hexerei.util.HexereiTags;
import net.joefoxe.hexerei.util.message.EmitParticlesPacket;
import net.joefoxe.hexerei.util.message.MessageCountUpdate;
import net.joefoxe.hexerei.util.message.TESyncPacket;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.util.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.joefoxe.hexerei.block.custom.MixingCauldron.LEVEL;

public class MixingCauldronTile extends RandomizableContainerBlockEntity implements WorldlyContainer, Clearable, MenuProvider, IFluidHandler {

//    private final ItemStackHandler itemHandler = createHandler();
//    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public boolean crafting;
    public int craftDelay;
    public int emitParticles = 0;
    public boolean emitParticleSpout = false;
    public float degrees;
    private boolean crafted;
    private boolean extracted = false;
    private int isColliding = 0;  // 15 is colliding, 0 is no longer colliding
    public static final int craftDelayMax = 100;
    private long tickedGameTime;
    private NonNullList<ItemStack> items = NonNullList.withSize(10, ItemStack.EMPTY);
    private FluidStack fluidStack = FluidStack.EMPTY;

    private static final int[] SLOTS_INPUT = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    private static final int[] SLOTS_OUTPUT = new int[]{8};

    VoxelShape BLOOD_SIGIL_SHAPE = Block.box(2.0D, 3.0D, 2.0D, 14.0D, 4.0D, 14.0D);
    VoxelShape HOPPER_SHAPE = Block.box(2.0D, 3.0D, 2.0D, 14.0D, 4.0D, 14.0D);



    public MixingCauldronTile(BlockEntityType<?> tileEntityTypeIn, BlockPos blockPos, BlockState blockState) {
        super(tileEntityTypeIn, blockPos, blockState);
    }

    public MixingCauldronTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.MIXING_CAULDRON_TILE.get(),blockPos, blockState);
    }

    public FluidStack getFluidStack(){
        return this.fluidStack;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + Hexerei.MOD_ID + ".mixing_cauldron");
    }

    
    /**
     * Returns the stack in the given slot.
     */
    @Override
    public ItemStack getItem(int index) {
        return index >= 0 && index < this.items.size() ? this.items.get(index) : ItemStack.EMPTY;
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
    public ItemStack removeItem(int index, int count) {
//        setChanged();
        ItemStack itemStack = ContainerHelper.removeItem(this.items, index, count);
        if(itemStack.getCount() < 1)
            itemStack.setCount(1);
        return itemStack;
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
    public ItemStack removeItemNoUpdate(int index) {

        return ContainerHelper.takeItem(this.items, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < this.items.size()) {
            ItemStack itemStack = stack.copy();
            itemStack.setCount(1);
            this.items.set(index, itemStack);
        }

        sync();
    }

    public boolean canPlaceItem(int index, ItemStack stack) {

        if (index == 8)
            return false;
        if (index == 9 && !stack.is(HexereiTags.Items.SIGILS))
            return false;
        return this.items.get(index).isEmpty();

    }

    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_OUTPUT;
        } else {
            return SLOTS_INPUT;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
//        if (index == 3) {
//            return stack.getItem() == Items.GLASS_BOTTLE;
//        } else {
//            return true;
//        }
        return true;
    }

    public int getCraftDelay() {
        return this.craftDelay;
    }
    public void setCraftDelay(int delay) {
        this.craftDelay =  delay;
    }



    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new MixingCauldronContainer(id, this.level, this.getPos(), player, player.player);
    }

    @Override
    public void clearContent() {
        super.clearContent();
        this.items.clear();
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

    @Override
    public void requestModelDataUpdate() {
        super.requestModelDataUpdate();
    }

    @NotNull
    @Override
    public IModelData getModelData() {
        return super.getModelData();
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        this.fluidStack = FluidStack.loadFluidStackFromNBT(compoundTag.getCompound("fluid"));

        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
    }

    public void saveAdditional(CompoundTag compound) {
        ContainerHelper.saveAllItems(compound, this.items);
        compound.put("fluid", this.fluidStack.writeToNBT(new CompoundTag()));
    }

//    @Override
    public CompoundTag save(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
        tag.put("fluid", this.fluidStack.writeToNBT(new CompoundTag()));
        return tag;
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

    public void sync() {
        setChanged();
        if (!level.isClientSide)
            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new TESyncPacket(worldPosition, save(new CompoundTag())));

        if(this.level != null)
            this.level.sendBlockUpdated(this.getPos(), this.level.getBlockState(this.getPos()), this.level.getBlockState(this.getPos()),
                    Block.UPDATE_CLIENTS);
    }


    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        return super.serializeNBT();
    }

    /*
     * This method gets called on the client when it receives the packet that was
     * sent in getUpdatePacket(). And here we just read the data from the packet
     * that was recieved.
     */
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//        this.load(pkt.getTag());
//    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }


    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.extracted && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(capability, LazyOptional.of(() -> this));
        return super.getCapability(capability, facing);
    }


    public Item getItemInSlot(int slot) {
        return this.items.get(slot).getItem();
    }

    public ItemStack getItemStackInSlot(int slot) {
        return this.items.get(slot);
    }

    public int getCraftMaxDelay() {
        return this.craftDelayMax;
    }

    public boolean getCrafted() {
        return this.crafted;
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

    private void strikeLightning() {
        if(!this.level.isClientSide()) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel)level, null, null,
                    worldPosition, MobSpawnType.TRIGGERED, true, true);
        }
    }

    public void entityInside(Entity entity) {
        BlockPos blockpos = this.getPos();
        if (entity instanceof ItemEntity) {
            if (Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move((double)(-blockpos.getX()), (double)(-blockpos.getY()), (double)(-blockpos.getZ()))), HOPPER_SHAPE, BooleanOp.AND)) {
                if(captureItem((ItemEntity)entity) && !level.isClientSide) {
                    HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new EmitParticlesPacket(worldPosition, 2, true));
                }
            }
        }else
        {
            if (Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move((double)(-blockpos.getX()), (double)(-blockpos.getY()), (double)(-blockpos.getZ()))), BLOOD_SIGIL_SHAPE, BooleanOp.AND)) {
                if(this.isColliding <= 1 && this.getItemInSlot(9).asItem() == ModItems.BLOOD_SIGIL.get()) {
                    Random random = new Random();
                    entity.hurt(DamageSource.MAGIC, 3.0f);

                    if(fluidStack.isEmpty() || (fluidStack.containsFluid(new FluidStack(ModFluids.BLOOD_FLUID.get(), 1)) && this.getFluidStack().getAmount() < this.getTankCapacity(0))) {

                        if(fluidStack.isEmpty())
                            this.fill(new FluidStack(ModFluids.BLOOD_FLUID.get(),111), IFluidHandler.FluidAction.EXECUTE);
                        else {
                            this.getFluidStack().grow(111);
                            if (this.getFluidStack().getAmount() % 1000 == 1)
                                this.getFluidStack().shrink(1);
                            if (this.getFluidStack().getAmount() % 1000 == 999)
                                this.getFluidStack().grow(1);
                            sync();
                        }
                        entity.getLevel().playSound((Player)null, entity.blockPosition(), SoundEvents.HONEY_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);
                        if(!level.isClientSide)
                            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new EmitParticlesPacket(worldPosition, 2, true));

                    }
                }

                this.isColliding = 6; // little cooldown so you dont constantly take damage, you must jump on the nails to take damage

            }

        }

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {

        return super.getCapability(cap);
    }

    public boolean captureItem(ItemEntity itemEntity) {
        boolean flag = false;
        ItemStack itemstack = itemEntity.getItem().copy();

        //check if there is a slot open  getFirstOpenSlot
        if (getFirstOpenSlot() >= 0)
        {
            this.setItem(getFirstOpenSlot(), itemstack);
//            this.itemHandler.insertItem(getFirstOpenSlot(), itemstack, false);
            itemEntity.getItem().shrink(1);
            //((MixingCauldron)this.getBlockState().getBlock()).emitCraftCompletedParticles();
            return true;
        }
        return false;
    }



    public int getFirstOpenSlot(){
        for(int i = 0; i < 8; i++) {
            if(this.items.get(i).isEmpty())
                return i;
        }
        return -1;
    }


    public void craft(){
        SimpleContainer inv = new SimpleContainer(10);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, this.items.get(i));
        }


        Optional<MixingCauldronRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.MIXING_CAULDRON_RECIPE, inv, level);

        recipe.ifPresent(iRecipe -> {
            ItemStack output = iRecipe.getResultItem();
            //ask for delay

            if(iRecipe.getLiquid().isFluidEqual(this.getFluidStack()) && (inv.getItem(8) == ItemStack.EMPTY || inv.getItem(8).getCount() == 0) && !this.crafted && iRecipe.getFluidLevelsConsumed() <= this.getFluidStack().getAmount()) {
                this.crafting = true;
                if(this.craftDelay >= this.craftDelayMax) {
                    Random rand = new Random();
                    craftTheItem(output);
                    sync();
                    int temp = this.getFluidStack().getAmount();
                    this.getFluidStack().shrink(this.getTankCapacity(0));
                    this.fill(new FluidStack(iRecipe.getLiquidOutput(), temp), FluidAction.EXECUTE);

                    //for setting a cooldown on crafting so the animations can take place
                    this.crafted = true;
                    HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new EmitParticlesPacket(worldPosition, 10, true));
                    this.getFluidStack().shrink(iRecipe.getFluidLevelsConsumed());
                    if (this.getFluidStack().getAmount() % 10 == 1)
                        this.getFluidStack().shrink(1);
                    if (this.getFluidStack().getAmount() % 10 == 9)
                        this.getFluidStack().grow(1);

               }
            }


        });
        if(this.crafting)
            this.craftDelay += 2;
        if(this.craftDelay >= 1)
            this.craftDelay--;
        this.crafting = false;
        if(this.craftDelay > 0 && !level.isClientSide)
            this.level.setBlock(worldPosition, this.level.getBlockState(this.worldPosition).setValue(MixingCauldron.CRAFT_DELAY, Integer.valueOf(Mth.clamp(this.craftDelay - 1, 0, MixingCauldronTile.craftDelayMax))), 2);
        if(this.craftDelay < 10)
            this.crafted = false;

    }

    private void craftTheItem(ItemStack output) {
//        itemHandler.extractItem(0, 1, false);
//        itemHandler.extractItem(1, 1, false);
//        itemHandler.extractItem(2, 1, false);
//        itemHandler.extractItem(3, 1, false);
//        itemHandler.extractItem(4, 1, false);
//        itemHandler.extractItem(5, 1, false);
//        itemHandler.extractItem(6, 1, false);
//        itemHandler.extractItem(7, 1, false);

        if(output.getItem() == ModItems.TALLOW_IMPURITY.get())
        {
            Random random = new Random();
            if(random.nextInt(5) != 0)
                output = ItemStack.EMPTY;
        }
        this.setItem(0, ItemStack.EMPTY);
        this.setItem(1, ItemStack.EMPTY);
        this.setItem(2, ItemStack.EMPTY);
        this.setItem(3, ItemStack.EMPTY);
        this.setItem(4, ItemStack.EMPTY);
        this.setItem(5, ItemStack.EMPTY);
        this.setItem(6, ItemStack.EMPTY);
        this.setItem(7, ItemStack.EMPTY);
        this.setItem(8, output);
//        itemHandler.setStackInSlot(0, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(1, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(2, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(3, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(4, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(5, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(6, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(7, ItemStack.EMPTY);
//        itemHandler.setStackInSlot(8, output);


    }



//    @Override
    public void tick() {
        if(level.isClientSide) {
            renderParticles();
            return;
        }

        this.tickedGameTime = this.level.getGameTime();
        craft();
        if(extracted)
        {
            extracted = false;
            sync();
        }
        this.isColliding--;
    }

    private void renderParticles() {
        float fillPercentage = 0;
        FluidStack fluidStack = getFluidInTank(0);
        if(!fluidStack.isEmpty())
            fillPercentage = Math.min(1, (float) fluidStack.getAmount() / getTankCapacity(0));
        float height = MixingCauldronRenderer.MIN_Y + (MixingCauldronRenderer.MAX_Y - MixingCauldronRenderer.MIN_Y) * fillPercentage;
        Random rand = new Random();

        if(this.emitParticles > 0)
        {
            for (int i = 0; i < 3; i++)
            {
                if(this.emitParticleSpout){
                    if (rand.nextInt(3) == 0)
                        level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(worldPosition)), worldPosition.getX() + 0.5f, worldPosition.getY() + height, worldPosition.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                    if (rand.nextInt(3) == 0)
                        level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(worldPosition)), worldPosition.getX() + 0.5f, worldPosition.getY() + height, worldPosition.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                    if (rand.nextInt(3) == 0)
                        level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(worldPosition)), worldPosition.getX() + 0.5f, worldPosition.getY() + height, worldPosition.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 20d, (rand.nextDouble() + 0.5d) * 2d, (rand.nextDouble() - 0.5d) / 20d);
                    if (rand.nextInt(3) == 0)
                        level.addParticle(ParticleTypes.SMOKE, worldPosition.getX() + 0.5f, worldPosition.getY() + height, worldPosition.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
                    if (rand.nextInt(3) == 0)
                        level.addParticle(ParticleTypes.SMOKE, worldPosition.getX() + 0.5f, worldPosition.getY() + height, worldPosition.getZ() + 0.5f, (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.045d, (rand.nextDouble() - 0.5d) / 50d);
                }
                if(rand.nextInt(3) == 0)
                    level.addParticle(ModParticleTypes.CAULDRON.get(), worldPosition.getX() + 0.2d + (0.6d * rand.nextDouble()), worldPosition.getY() + height, worldPosition.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                if(rand.nextInt(3) == 0)
                    level.addParticle(ModParticleTypes.CAULDRON.get(), worldPosition.getX() + 0.2d + (0.6d * rand.nextDouble()), worldPosition.getY() + height, worldPosition.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                if(rand.nextInt(3) == 0)
                    level.addParticle(ModParticleTypes.CAULDRON.get(), worldPosition.getX() + 0.2d + (0.6d * rand.nextDouble()), worldPosition.getY() + height, worldPosition.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                if(rand.nextInt(3) == 0)
                    level.addParticle(ModParticleTypes.CAULDRON.get(), worldPosition.getX() + 0.2d + (0.6d * rand.nextDouble()), worldPosition.getY() + height, worldPosition.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
                if(rand.nextInt(3) == 0)
                    level.addParticle(ModParticleTypes.CAULDRON.get(), worldPosition.getX() + 0.2d + (0.6d * rand.nextDouble()), worldPosition.getY() + height, worldPosition.getZ() + 0.2d + (0.6d * rand.nextDouble()), (rand.nextDouble() - 0.5d) / 50d, (rand.nextDouble() + 0.5d) * 0.024d, (rand.nextDouble() - 0.5d) / 50d);
            }
            this.emitParticles--;
        }


    }

    //    @Override
    public BlockPos getPos() {
        return worldPosition;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean hasCustomName() {
        return super.hasCustomName();
    }


    @Override
    public int getTanks() {
        return 1;
    }

    @NotNull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return this.fluidStack.copy();
    }

    @Override
    public int getTankCapacity(int tank) {
        return 2000;
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return true;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || !(this.fluidStack.isEmpty() || this.fluidStack.isFluidEqual(resource)))
            return 0;
        int amount = Math.min(resource.getAmount(), this.getTankCapacity(0) - this.fluidStack.getAmount());
        if (action.execute()) {
            FluidStack newStack = resource.copy();
            newStack.setAmount(this.fluidStack.getAmount() + amount);
            this.fluidStack = newStack;
            this.sync();
        }
        return amount;
    }


    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || this.fluidStack.isEmpty() || !this.fluidStack.getFluid().isSame(resource.getFluid()))
            return FluidStack.EMPTY;
        int amount = Math.min(resource.getAmount(), this.fluidStack.getAmount());
        FluidStack returnStack = this.fluidStack.copy();
        returnStack.setAmount(amount);
        if (action.execute()) {
            this.fluidStack.shrink(amount);
            this.sync();
        }
        return returnStack;
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (maxDrain <= 0 || this.fluidStack.isEmpty())
            return FluidStack.EMPTY;
        int amount = Math.min(maxDrain, this.fluidStack.getAmount());
        FluidStack returnStack = this.fluidStack.copy();
        returnStack.setAmount(amount);
        if (action.execute()) {
            this.fluidStack.shrink(amount);
            this.sync();
        }
        return returnStack;
    }

    public boolean interactWithFluid(IFluidHandlerItem fluidHandler) {
        if (fluidHandler.getTanks() == 0)
            return false;
        FluidStack cauldronFluid = fluidHandler.getFluidInTank(0);
        Random random = new Random();
        if (cauldronFluid.isEmpty()) {
            if (!this.fluidStack.isEmpty() && fluidHandler.isFluidValid(0, this.fluidStack)) {
                int amount = fluidHandler.fill(this.fluidStack.copy(), FluidAction.EXECUTE);
                if (amount > 0) {
                    if(getLevel() != null)
                        getLevel().playSound((Player) null, getPos().getX() + 0.5f, getPos().getY() + 0.5f, getPos().getZ() + 0.5f, fluidHandler.getFluidInTank(1).getFluid().getPickupSound().isPresent() ? fluidHandler.getFluidInTank(1).getFluid().getPickupSound().get() : SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                    this.fluidStack.shrink(amount);
                    this.sync();
                    return true;
                }

            }
        } else if (this.fluidStack.isEmpty() || this.fluidStack.isFluidEqual(cauldronFluid)) {
            cauldronFluid = cauldronFluid.copy();
            cauldronFluid.setAmount(this.getTankCapacity(0) - this.fluidStack.getAmount());
            FluidStack amount = fluidHandler.drain(cauldronFluid, FluidAction.SIMULATE);
            if (!amount.isEmpty() && (this.fluidStack.isEmpty() || this.fluidStack.isFluidEqual(amount))) {
                amount = fluidHandler.drain(cauldronFluid, FluidAction.EXECUTE);
                amount.grow(this.fluidStack.getAmount());
                this.fluidStack = amount;
                if(getLevel() != null)
                    getLevel().playSound((Player) null, getPos().getX() + 0.5f, getPos().getY() + 0.5f, getPos().getZ() + 0.5f, amount.getFluid().getPickupSound().isPresent() ? amount.getFluid().getPickupSound().get() : SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 0.8F + 0.4F * random.nextFloat());
                this.sync();
                return true;
            }
        }
        return false;
    }
}
