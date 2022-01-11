package net.joefoxe.hexerei.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.CandleDipper;
import net.joefoxe.hexerei.data.recipes.*;
import net.joefoxe.hexerei.screen.MixingCauldronScreen;
import net.joefoxe.hexerei.tileentity.CandleDipperTile;
import net.joefoxe.hexerei.tileentity.DryingRackTile;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class HexereiJei implements IModPlugin {


    public static final ResourceLocation MIXING_CAULDRON_UID = new ResourceLocation(Hexerei.MOD_ID, "mixing_cauldron");
    public static final ResourceLocation DIPPER_UID = new ResourceLocation(Hexerei.MOD_ID, "dipper");
    public static final ResourceLocation DRYING_RACK_UID = new ResourceLocation(Hexerei.MOD_ID, "drying_rack");
    public static final ResourceLocation PESTLE_AND_MORTAR_UID = new ResourceLocation(Hexerei.MOD_ID, "pestle_and_mortar");

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Hexerei.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new MixingCauldronRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new DipperRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new PestleAndMortarRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new DryingRackRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MIXING_CAULDRON.get()), MIXING_CAULDRON_UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CANDLE_DIPPER.get()), DIPPER_UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.HERB_DRYING_RACK.get()), DRYING_RACK_UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PESTLE_AND_MORTAR.get()), PESTLE_AND_MORTAR_UID);
    }
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MixingCauldronScreen.class, 66, 30, 28, 26, MIXING_CAULDRON_UID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.MIXING_CAULDRON_RECIPE).stream()
                        .filter(r -> r instanceof MixingCauldronRecipe).collect(Collectors.toList()),
                MixingCauldronRecipeCategory.UID);
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.DIPPER_RECIPE).stream()
                        .filter(r -> r instanceof DipperRecipe).collect(Collectors.toList()),
                DipperRecipeCategory.UID);
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.DRYING_RACK_RECIPE).stream()
                        .filter(r -> r instanceof DryingRackRecipe).collect(Collectors.toList()),
                DryingRackRecipeCategory.UID);
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.PESTLE_AND_MORTAR_RECIPE).stream()
                        .filter(r -> r instanceof PestleAndMortarRecipe).collect(Collectors.toList()),
                PestleAndMortarRecipeCategory.UID);
    }
}