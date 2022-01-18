package net.joefoxe.hexerei.data.recipes;

import net.joefoxe.hexerei.Hexerei;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface ICofferDyeingRecipe {
    ResourceLocation TYPE_ID = new ResourceLocation(Hexerei.MOD_ID, "coffer_dyeing");

}
