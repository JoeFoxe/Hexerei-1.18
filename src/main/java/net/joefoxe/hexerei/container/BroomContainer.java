package net.joefoxe.hexerei.container;

import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.CofferTile;
import net.joefoxe.hexerei.util.HexereiTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;


public class BroomContainer extends AbstractContainerMenu {
    private final Player playerEntity;
    public final BroomEntity broomEntity;
    private final IItemHandler playerInventory;



//    public BroomContainer(int windowId, BroomEntity broom, Inventory inv) {
//        this(windowId, inv, new SimpleContainer(5));
//    }

    public BroomContainer(int windowId, BroomEntity broomEntity, Inventory playerInventory, Player player) {
        super(ModContainers.BROOM_CONTAINER.get(), windowId);
        this.broomEntity = broomEntity;
        playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        int offset = 0;
        broomEntity.sync();
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.SMALL_SATCHELS))
            offset = 21;
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.MEDIUM_SATCHELS))
            offset = 42;
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.LARGE_SATCHELS))
            offset = 63;
        layoutPlayerInventorySlots(11, 106 + offset);

        //add slots for mixing cauldron
        if(broomEntity != null) {
            broomEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {

                addSlot(new SlotItemHandler(h, 0, 37, 47){

                    @Override
                    public int getMaxStackSize() {
                        return 1;
                    }

                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return stack.is(HexereiTags.Items.BROOM_MISC);
                    }
                });

                //satchel slot
                addSlot(new SlotItemHandler(h, 1, 99, 47) {

                    @Override
                    public int getMaxStackSize() {
                        return 1;
                    }

                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return stack.is(HexereiTags.Items.SMALL_SATCHELS) || stack.is(HexereiTags.Items.MEDIUM_SATCHELS) || stack.is(HexereiTags.Items.LARGE_SATCHELS);
                    }

                    @Override
                    public boolean mayPickup(Player playerIn) {

                        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.SMALL_SATCHELS)) {
                            if (broomEntity.itemHandler.getStackInSlot(3).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(4).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(5).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(6).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(7).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(8).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(9).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(10).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(11).getItem() == (ItemStack.EMPTY.getItem())

                            )
                                return true;
                            else
                                return false;
                        }
                        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.MEDIUM_SATCHELS)) {
                            if (broomEntity.itemHandler.getStackInSlot(3).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(4).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(5).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(6).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(7).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(8).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(9).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(10).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(11).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(12).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(13).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(14).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(15).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(16).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(17).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(18).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(19).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(20).getItem() == (ItemStack.EMPTY.getItem())

                            )
                                return true;
                            else
                                return false;
                        }
                        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.LARGE_SATCHELS)) {
                            if (broomEntity.itemHandler.getStackInSlot(3).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(4).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(5).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(6).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(7).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(8).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(9).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(10).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(11).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(12).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(13).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(14).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(15).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(16).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(17).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(18).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(19).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(20).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(21).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(22).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(23).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(24).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(25).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(26).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(27).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(28).getItem() == (ItemStack.EMPTY.getItem()) &&
                                    broomEntity.itemHandler.getStackInSlot(29).getItem() == (ItemStack.EMPTY.getItem())

                            )
                                return true;
                            else
                                return false;
                        }

                        return true;
                    }
                });
                addSlot(new SlotItemHandler(h, 2, 160, 47){

                    @Override
                    public int getMaxStackSize() {
                        return 1;
                    }

                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return stack.is(HexereiTags.Items.BROOM_BRUSH);
                    }
                });


                for(int i = 0; i < 3; i++)
                    for(int j = 0; j < 9; j++)
                        addSlot(new SlotItemHandler(h, 3 + (i * 9) + j , 15 + 21 * j, 21 * i + 82) {
                            @Override
                            public boolean mayPlace(@NotNull ItemStack stack) {
                                return !(stack.is(ModItems.WILLOW_BROOM.get()) || stack.is(ModItems.MAHOGANY_BROOM.get()));
                            }
                        });

                int offset2 = 0;
                if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.SMALL_SATCHELS)) {
                    offset2 = 21;

                    for(int i = 0; i < 1; i++)
                        for(int j = 0; j < 9; j++)
                            this.slots.get(39 + (i * 9) + j).y = 21 * i + 82;


                    for(int i = 1; i < 3; i++)
                        for(int j = 0; j < 9; j++)
                            this.slots.get(39 + (i * 9) + j).y = -999;
                }
                if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.MEDIUM_SATCHELS)) {
                    offset2 = 42;

                    for(int i = 0; i < 2; i++)
                        for(int j = 0; j < 9; j++)
                            this.slots.get(39 + (i * 9) + j).y = 21 * i + 82;


                    for(int i = 2; i < 3; i++)
                        for(int j = 0; j < 9; j++)
                            this.slots.get(39 + (i * 9) + j).y = -999;
                }
                if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.LARGE_SATCHELS)) {
                    offset2 = 63;

                    for(int i = 0; i < 3; i++)
                        for(int j = 0; j < 9; j++)
                            this.slots.get(39 + (i * 9) + j).y = 21 * i + 82;

                }

                for(int i = 0; i < 3; i++)
                    for(int j = 0; j < 9; j++)
                        this.slots.get((i * 9) + j).y = 106 + (i * 18) + offset2;

                for(int k = 0; k < 9; k++)
                    this.slots.get(27 + k).y = 106 + 58 + offset2;


                if(offset2 == 0)
                {
                    for(int i = 0; i < 3; i++)
                        for(int j = 0; j < 9; j++)
                            this.slots.get(39 + (i * 9) + j).y = -999;
                }
            });
        }

        addDataSlot(new DataSlot() {
            @Override
            public void set(int value) {
                broomEntity.setFloatMode(value != 0);
            }
            @Override
            public int get() {
                return broomEntity.getFloatMode() ? 1 : 0;
            }
        });



    }

    @Override
    public void clicked(int p_150400_, int p_150401_, ClickType p_150402_, Player p_150403_) {
        super.clicked(p_150400_, p_150401_, p_150402_, p_150403_);
        int offset = 0;
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.SMALL_SATCHELS)) {
            offset = 21;

            for(int i = 0; i < 1; i++)
                for(int j = 0; j < 9; j++)
                    this.slots.get(39 + (i * 9) + j).y = 21 * i + 82;


            for(int i = 1; i < 3; i++)
                for(int j = 0; j < 9; j++)
                    this.slots.get(39 + (i * 9) + j).y = -999;
        }
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.MEDIUM_SATCHELS)) {
            offset = 42;

            for(int i = 0; i < 2; i++)
                for(int j = 0; j < 9; j++)
                    this.slots.get(39 + (i * 9) + j).y = 21 * i + 82;


            for(int i = 2; i < 3; i++)
                for(int j = 0; j < 9; j++)
                    this.slots.get(39 + (i * 9) + j).y = -999;
        }
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.LARGE_SATCHELS)) {
            offset = 63;

            for(int i = 0; i < 3; i++)
                for(int j = 0; j < 9; j++)
                    this.slots.get(39 + (i * 9) + j).y = 21 * i + 82;

        }

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                this.slots.get((i * 9) + j).y = 106 + (i * 18) + offset;

        for(int k = 0; k < 9; k++)
            this.slots.get(27 + k).y = 106 + 58 + offset;


        if(offset == 0)
        {
            for(int i = 0; i < 3; i++)
                for(int j = 0; j < 9; j++)
                    this.slots.get(39 + (i * 9) + j).y = -999;
        }


    }

    public void playSound() {
        this.broomEntity.getLevel().playSound((Player)null, this.broomEntity.blockPosition(), SoundEvents.UI_BUTTON_CLICK, SoundSource.BLOCKS, 1.0F, 1.0F);;
    }

    public boolean getFloatMode() {
        return broomEntity.getFloatMode();
    }

    public void setFloatMode(boolean value) {
        broomEntity.setFloatMode(value);
    }

    @Override
    public boolean stillValid(Player playerIn) {
//        return stillValid(ContainerLevelAccess.create(broomEntity.getLevel(), broomEntity.blockPosition()),
//                playerIn, ModBlocks.COFFER.get());


        if (broomEntity.isRemoved()) {
            return false;
        } else {
            return !(playerIn.distanceToSqr(broomEntity) > 64.0D);
        }
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our BlockEntity slot numbers 0 - 8)

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        int count = 0;

        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.SMALL_SATCHELS))
            count = 9;
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.MEDIUM_SATCHELS))
            count = 18;
        if(broomEntity.itemHandler.getStackInSlot(1).is(HexereiTags.Items.LARGE_SATCHELS))
            count = 27;


        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT + count, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT + count) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerEntity, sourceStack);
        return copyOfSourceStack;
    }
}
