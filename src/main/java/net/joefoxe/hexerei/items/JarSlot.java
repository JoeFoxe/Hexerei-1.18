package net.joefoxe.hexerei.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class JarSlot extends SlotItemHandler {
    private final int index;

    public JarSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.index = index;
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return true;
    }

    @Override
    public int getMaxStackSize(@Nonnull ItemStack stack) {
        return ((JarHandler) this.getItemHandler()).getStackLimit(this.index, stack);
    }

    @Override
    public boolean isSameInventory(Slot other) {
        return other instanceof JarSlot && ((JarSlot) other).getItemHandler() == this.getItemHandler();
    }
}