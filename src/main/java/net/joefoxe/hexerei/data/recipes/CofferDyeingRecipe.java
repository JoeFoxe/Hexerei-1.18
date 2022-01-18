package net.joefoxe.hexerei.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.Coffer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class CofferDyeingRecipe extends CustomRecipe {
    public static final SimpleRecipeSerializer<CofferDyeingRecipe> SERIALIZER = new SimpleRecipeSerializer<>(CofferDyeingRecipe::new);

    public CofferDyeingRecipe(ResourceLocation registryName) {
        super(registryName);

    }

    @Override
    public boolean matches(CraftingContainer inventory, Level world) {
        int coffers = 0;
        int dyes = 0;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (Block.byItem(stack.getItem()) instanceof Coffer) {
                    ++coffers;
                } else {
                    if (!stack.is(Tags.Items.DYES))
                        return false;
                    ++dyes;
                }

                if (dyes > 1 || coffers > 1) {
                    return false;
                }
            }
        }

        return coffers == 1 && dyes == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory) {
        ItemStack coffer = ItemStack.EMPTY;
        DyeColor color = DyeColor.BROWN;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (Block.byItem(stack.getItem()) instanceof Coffer) {
                    coffer = stack;
                } else {
                    DyeColor color1 = DyeColor.getColor(stack);
                    if (color1 != null) {
                        color = color1;
                    }
                }
            }
        }

        ItemStack dyedCoffer;
        if(color == DyeColor.BLACK)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_BLACK.get());
        else if(color == DyeColor.BLUE)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_BLUE.get());
        else if(color == DyeColor.CYAN)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_CYAN.get());
        else if(color == DyeColor.GRAY)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_GRAY.get());
        else if(color == DyeColor.GREEN)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_GREEN.get());
        else if(color == DyeColor.LIGHT_BLUE)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_LIGHT_BLUE.get());
        else if(color == DyeColor.LIGHT_GRAY)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_LIGHT_GRAY.get());
        else if(color == DyeColor.LIME)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_LIME.get());
        else if(color == DyeColor.MAGENTA)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_MAGENTA.get());
        else if(color == DyeColor.ORANGE)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_ORANGE.get());
        else if(color == DyeColor.PINK)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_PINK.get());
        else if(color == DyeColor.PURPLE)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_PURPLE.get());
        else if(color == DyeColor.RED)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_RED.get());
        else if(color == DyeColor.WHITE)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_WHITE.get());
        else if(color == DyeColor.YELLOW)
            dyedCoffer = new ItemStack(ModBlocks.COFFER_YELLOW.get());
        else
            dyedCoffer = new ItemStack(ModBlocks.COFFER.get());
        if (coffer.hasTag()) {
            dyedCoffer.setTag(coffer.getTag()
                    .copy());
        }

        return dyedCoffer;
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