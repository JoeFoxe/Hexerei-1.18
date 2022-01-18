package net.joefoxe.hexerei.data.recipes;

import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.Coffer;
import net.joefoxe.hexerei.item.custom.KeychainItem;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;


public class KeychainRecipe extends CustomRecipe {
    public static final SimpleRecipeSerializer<KeychainRecipe> SERIALIZER = new SimpleRecipeSerializer<>(KeychainRecipe::new);

    public KeychainRecipe(ResourceLocation registryName) {
        super(registryName);

    }

    @Override
    public boolean matches(CraftingContainer inventory, Level world) {
        int keychain = 0;
        int other = 0;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof KeychainItem) {
                    ++keychain;
                } else {
                    ++other;
                }

                if (other > 1 || keychain > 1) {
                    return false;
                }
            }
        }

        return keychain == 1 && other == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory) {
        ItemStack keychain = ItemStack.EMPTY;
        ItemStack other = ItemStack.EMPTY;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof KeychainItem) {
                    keychain = stack.copy();
                    keychain.setCount(1);
                } else {
                    other = stack.copy();
                    other.setCount(1);
                }
            }
        }
        if (keychain.getItem() instanceof KeychainItem && !other.isEmpty()) {
            CompoundTag tag = new CompoundTag();
            if(keychain.hasTag())
                tag = keychain.getTag();

            ListTag listtag = new ListTag();

            if (!other.isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.putByte("Slot", (byte)0);
                other.save(compoundtag);
                listtag.add(compoundtag);

            }

            tag.put("Items", listtag);

            keychain.setTag(tag);
        }
        System.out.println(other);

        return keychain;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 2 && height >= 1;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}