package net.joefoxe.hexerei.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;


public class KeychainItem extends Item {


    public NonNullList<ItemStack> containedItem = NonNullList.withSize(1, ItemStack.EMPTY);

    public KeychainItem(Properties properties) {
        super(properties);

    }
//ContainerHelper.saveAllItems(tag, this.items);




    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        CompoundTag inv = stack.getOrCreateTag();
        ListTag tagList = inv.getList("Items", Tag.TAG_COMPOUND);
        CompoundTag compoundtag = tagList.getCompound(0);
        CompoundTag itemTags = tagList.getCompound(0);

        TranslatableComponent itemText = (TranslatableComponent) new TranslatableComponent(ItemStack.of(compoundtag).getDescriptionId()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x998800)));

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("<%s>", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA6600)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

            if(!ItemStack.of(itemTags).isEmpty())
            tooltip.add(new TranslatableComponent("tooltip.hexerei.keychain_with_item").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            else
            tooltip.add(new TranslatableComponent("tooltip.hexerei.keychain_without_item").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
        } else {
            tooltip.add(new TranslatableComponent("[%s]", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAA00)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_attachments").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
        }


        if(!ItemStack.of(itemTags).isEmpty()) {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.keychain_contains", itemText).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
        }

        super.appendHoverText(stack, world, tooltip, flagIn);
    }

}
