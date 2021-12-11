package net.joefoxe.hexerei.container;

import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.tileentity.CofferTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;


public class CofferContainer extends AbstractContainerMenu {
    private final BlockEntity tileEntity;
    private final Player playerEntity;
    private final IItemHandler playerInventory;


    public CofferContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.COFFER_CONTAINER.get(), windowId);
        this.tileEntity = world.getBlockEntity(pos);
        playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);


        layoutPlayerInventorySlots(11, 147);

        //add slots for mixing cauldron
        if(tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 15 + (21 * 0), 18));
                addSlot(new SlotItemHandler(h, 1, 15 + (21 * 1), 18));
                addSlot(new SlotItemHandler(h, 2, 15 + (21 * 2), 18));
                addSlot(new SlotItemHandler(h, 3, 15 + (21 * 3), 18));
                addSlot(new SlotItemHandler(h, 4, 15 + (21 * 4), 18));
                addSlot(new SlotItemHandler(h, 5, 15 + (21 * 5), 18));
                addSlot(new SlotItemHandler(h, 6, 15 + (21 * 6), 18));
                addSlot(new SlotItemHandler(h, 7, 15 + (21 * 7), 18));
                addSlot(new SlotItemHandler(h, 8, 15 + (21 * 8), 18));
                addSlot(new SlotItemHandler(h, 9, 15 + (21 * 0), 39));
                addSlot(new SlotItemHandler(h, 10, 15 + (21 * 1), 39));
                addSlot(new SlotItemHandler(h, 11, 15 + (21 * 2), 39));
                addSlot(new SlotItemHandler(h, 12, 15 + (21 * 6), 39));
                addSlot(new SlotItemHandler(h, 13, 15 + (21 * 7), 39));
                addSlot(new SlotItemHandler(h, 14, 15 + (21 * 8), 39));
                addSlot(new SlotItemHandler(h, 15, 15 + (21 * 0), 60));
                addSlot(new SlotItemHandler(h, 16, 15 + (21 * 1), 60));
                addSlot(new SlotItemHandler(h, 17, 15 + (21 * 2), 60));
                addSlot(new SlotItemHandler(h, 18, 15 + (21 * 6), 60));
                addSlot(new SlotItemHandler(h, 19, 15 + (21 * 7), 60));
                addSlot(new SlotItemHandler(h, 20, 15 + (21 * 8), 60));
                addSlot(new SlotItemHandler(h, 21, 15 + (21 * 0), 81));
                addSlot(new SlotItemHandler(h, 22, 15 + (21 * 1), 81));
                addSlot(new SlotItemHandler(h, 23, 15 + (21 * 2), 81));
                addSlot(new SlotItemHandler(h, 24, 15 + (21 * 6), 81));
                addSlot(new SlotItemHandler(h, 25, 15 + (21 * 7), 81));
                addSlot(new SlotItemHandler(h, 26, 15 + (21 * 8), 81));
                addSlot(new SlotItemHandler(h, 27, 15 + (21 * 0), 102));
                addSlot(new SlotItemHandler(h, 28, 15 + (21 * 1), 102));
                addSlot(new SlotItemHandler(h, 29, 15 + (21 * 2), 102));
                addSlot(new SlotItemHandler(h, 30, 15 + (21 * 3), 102));
                addSlot(new SlotItemHandler(h, 31, 15 + (21 * 4), 102));
                addSlot(new SlotItemHandler(h, 32, 15 + (21 * 5), 102));
                addSlot(new SlotItemHandler(h, 33, 15 + (21 * 6), 102));
                addSlot(new SlotItemHandler(h, 34, 15 + (21 * 7), 102));
                addSlot(new SlotItemHandler(h, 35, 15 + (21 * 8), 102));
            });
        }

        addDataSlot(new DataSlot() {
            @Override
            public void set(int value) {
                ((CofferTile)tileEntity).setButtonToggled(value);
            }
            @Override
            public int get() {
                return ((CofferTile)tileEntity).getButtonToggled();
            }
        });



    }


    public void playSound() {
        this.tileEntity.getLevel().playSound((Player)null, this.tileEntity.getBlockPos(), SoundEvents.UI_BUTTON_CLICK, SoundSource.BLOCKS, 1.0F, 1.0F);;
    }

    public int getToggled() {
        return ((CofferTile)tileEntity).getButtonToggled();
    }

    public void setToggled(int value) {
        ((CofferTile)tileEntity).setButtonToggled(value);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()),
                playerIn, ModBlocks.COFFER.get());
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
    private static final int TE_INVENTORY_SLOT_COUNT = 36;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();


        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
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
