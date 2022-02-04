package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.config.HexConfig;
import net.joefoxe.hexerei.container.HerbJarContainer;
import net.joefoxe.hexerei.items.JarHandler;
import net.joefoxe.hexerei.util.HexereiPacketHandler;
import net.joefoxe.hexerei.util.HexereiTags;
import net.joefoxe.hexerei.util.message.MessageCountUpdate;
import net.joefoxe.hexerei.util.message.TESyncPacket;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.Clearable;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.players.PlayerList;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.core.NonNullList;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class HerbJarTile extends RandomizableContainerBlockEntity implements Clearable, MenuProvider {

    public JarHandler itemHandler;
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler).cast();

    protected NonNullList<ItemStack> items = NonNullList.withSize(8, ItemStack.EMPTY);

    private final FormattedCharSequence[] renderText = new FormattedCharSequence[1];

    private final Component[] signText = new Component[]{new TextComponent("Text")};

    public int degreesOpened;

    public Component customName;

    private long lastClickTime;
    private UUID lastClickUUID;



    public HerbJarTile(BlockEntityType<?> tileEntityTypeIn, BlockPos blockPos, BlockState blockState) {
        super(tileEntityTypeIn, blockPos, blockState);
        this.itemHandler = createHandler();
    }

    public HerbJarTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.HERB_JAR_TILE.get(),blockPos, blockState);
    }


//    public HerbJarTile(BlockEntityType<?> tileEntityTypeIn) {
//        super(tileEntityTypeIn);
//        this.itemHandler = createHandler();
//    }

    public void readInventory(CompoundTag compound) {
        itemHandler.deserializeNBT(compound);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public ItemStack getItemStackInSlot(int slot) {
        return this.items.get(slot);
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
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + Hexerei.MOD_ID + ".herb_jar");
    }

//    public HerbJarTile() {
//        this(ModTileEntities.HERB_JAR_TILE.get());
//    }
//
//    @Override
//    public void load(CompoundTag nbt) {
//        itemHandler.deserializeNBT(nbt.getCompound("inv"));
//        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
//        super.load(nbt);
//        if (nbt.contains("CustomName", 8))
//            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
//    }



//    @Override
    public CompoundTag save(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inv", itemHandler.serializeNBT());
        return tag;
    }


    @Override
    public void saveAdditional(CompoundTag compound) {
        compound.put("inv", itemHandler.serializeNBT());
        if (this.customName != null)
            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
    }



    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        itemHandler.deserializeNBT(compoundTag.getCompound("inv"));
        if (compoundTag.contains("CustomName", 8))
            this.customName = Component.Serializer.fromJson(compoundTag.getString("CustomName"));
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
            this.level.sendBlockUpdated(this.worldPosition, this.level.getBlockState(this.worldPosition), this.level.getBlockState(this.worldPosition),
                    Block.UPDATE_CLIENTS);
    }


    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new HerbJarContainer(id, this.level, this.worldPosition, player, player.player);
    }


    @Override
    public void clearContent() {
        super.clearContent();
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


    @Nullable
    @OnlyIn(Dist.CLIENT)
    public FormattedCharSequence reorderText(int row, Function<Component, FormattedCharSequence> textProcessorFunction) {
        if (this.renderText[row] == null && this.customName != null) {
            this.renderText[row] = textProcessorFunction.apply(this.customName);
        }

        return this.renderText[row];
    }


    private JarHandler createHandler() {
        return new JarHandler(1,1024) {
            @Override
            protected void onContentsChanged(int slot) {
                sync();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(HexConfig.JARS_ONLY_HOLD_HERBS.get()) {
                    return stack.is(HexereiTags.Items.HERB_ITEM);
                }
                return true;
            }

        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public Item getItemInSlot(int slot) {
        return this.itemHandler.getStackInSlot(slot).getItem();
    }

    public int getNumberOfItems() {

        int num = 0;
        for(int i = 0; i < this.itemHandler.getSlots(); i++)
        {
            if(this.itemHandler.getStackInSlot(i) != ItemStack.EMPTY)
                num++;
        }
        return num;

    }

    public static double getDistanceToEntity(Entity entity, BlockPos pos) {
        double deltaX = entity.getX() - pos.getX();
        double deltaY = entity.getY() - pos.getY();
        double deltaZ = entity.getZ() - pos.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    @Override
    public Component getDisplayName() {
        return customName != null ? customName
                : new TranslatableComponent("");
    }

    @Nonnull
    public ItemStack takeItems (int slot, int count) {

        ItemStack stack = this.itemHandler.getStackInSlot(slot).copy();
        stack.setCount(Math.min(count, this.itemHandler.getStackInSlot(slot).getMaxStackSize()));
        this.itemHandler.getStackInSlot(slot).setCount(this.itemHandler.getStackInSlot(slot).getCount() - stack.getCount());

        return stack;
    }

    public int putItems (int slot, @Nonnull ItemStack stack, int count) {
        if(HexConfig.JARS_ONLY_HOLD_HERBS.get())
            if(!stack.is(HexereiTags.Items.HERB_ITEM))
                return 0;

        if (this.itemHandler.getContents().get(0).isEmpty()) {
            this.itemHandler.insertItem(0, stack.copy(), false);
            sync();
            stack.shrink(count);
            return count;
        }


        if (!this.itemHandler.getContents().get(0).sameItem(stack))
            return 0;
        if(!ItemStack.isSameItemSameTags(stack, this.itemHandler.getContents().get(0)))
            return 0;

        int countAdded = Math.min(count, stack.getCount());
        countAdded = Math.min(countAdded, 1024 - this.itemHandler.getContents().get(0).getCount());

        this.itemHandler.getContents().get(0).setCount(this.itemHandler.getContents().get(0).getCount() + countAdded);
        stack.shrink(countAdded);
        return countAdded;
    }


    @OnlyIn(Dist.CLIENT)
    public void clientUpdateCount (final int slot, final int count) {
        if (!Objects.requireNonNull(getLevel()).isClientSide)
            return;
        Minecraft.getInstance().tell(() -> HerbJarTile.this.clientUpdateCountAsync(slot, count));
    }

    @OnlyIn(Dist.CLIENT)
    private void clientUpdateCountAsync (int slot, int count) {
        if (this.itemHandler.getStackInSlot(0).getCount() != count){
            ItemStack newStack = this.itemHandler.getStackInSlot(0).copy();
            this.itemHandler.setStackInSlot(0, newStack);
        }
    }

    protected void syncClientCount (int slot, int count) {
        if (getLevel() != null && getLevel().isClientSide)
            return;

        PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(
                getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 500, getLevel().dimension());
        HexereiPacketHandler.instance.send(PacketDistributor.NEAR.with(() -> point), new MessageCountUpdate(getBlockPos(), slot, count));
    }



    public int interactPutItems (Player player) {
        int count;
        if (Objects.requireNonNull(getLevel()).getGameTime() - lastClickTime < 10 && player.getUUID().equals(lastClickUUID))
            count = interactPutCurrentInventory(0, player);
        else
            count = interactPutCurrentItem(0, player);

        lastClickTime = getLevel().getGameTime();
        lastClickUUID = player.getUUID();
        if(count > 0)
            sync();

        return count;
    }

    public int interactPutCurrentItem (int slot, Player player) {

        int count = 0;
        ItemStack playerStack = player.inventory.getSelected();
        if (!playerStack.isEmpty())
            count = putItems(slot, playerStack, playerStack.getCount());

        return count;
    }


    public int interactPutCurrentInventory (int slot, Player player) {
        int count = 0;
        if (!this.itemHandler.getContents().get(0).isEmpty()) {
            for (int i = 0, n = player.inventory.getContainerSize(); i < n; i++) {
                ItemStack subStack = player.inventory.getItem(i);
                if (!subStack.isEmpty()) {
                    int subCount = putItems(slot, subStack, subStack.getCount());
                    if (subCount > 0 && subStack.getCount() == 0)
                        player.inventory.setItem(i, ItemStack.EMPTY);

                    count += subCount;
                }
            }
        }

        if (count > 0)
            if (player instanceof ServerPlayer)
                ((ServerPlayer) player).initMenu(player.containerMenu);

        return count;
    }

    @Override
    public Component getCustomName() {
        return this.customName;
    }

    @Override
    public boolean hasCustomName() {
        return customName != null;
    }

    @Override
    public Component getName() {
        return customName;
    }

    public int getDegreesOpened() {
        return this.degreesOpened;
    }
    public void setDegreesOpened(int degrees) {
        this.degreesOpened =  degrees;
    }

    @Override
    public int getContainerSize() {
        return 0;
    }
}
