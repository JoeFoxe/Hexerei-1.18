package net.joefoxe.hexerei.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.data.recipes.MixingCauldronRecipe;
import net.joefoxe.hexerei.fluid.ModFluids;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

public class MixingCauldronRecipeCategory implements IRecipeCategory<MixingCauldronRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Hexerei.MOD_ID, "mixing_cauldron");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Hexerei.MOD_ID, "textures/gui/mixing_cauldron_gui_jei.png");
    public final static ResourceLocation TEXTURE_BLANK =
            new ResourceLocation(Hexerei.MOD_ID, "textures/block/blank.png");
    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawable liquid;


    public MixingCauldronRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 96, 170, 160, 86);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.MIXING_CAULDRON.get()));
        this.liquid = helper.createDrawable(TEXTURE, 7, 7, 14, 12);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends MixingCauldronRecipe> getRecipeClass() {
        return MixingCauldronRecipe.class;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.MIXING_CAULDRON.get().getName();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(MixingCauldronRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MixingCauldronRecipe recipe, IIngredients ingredients) {


        recipeLayout.getItemStacks().init(0, true, 37 + 34, 3);
        recipeLayout.getItemStacks().init(1, true, 37 + 56, 12);
        recipeLayout.getItemStacks().init(2, true, 37 + 65, 34);
        recipeLayout.getItemStacks().init(3, true, 37 + 56, 56);
        recipeLayout.getItemStacks().init(4, true, 37 + 34, 65);
        recipeLayout.getItemStacks().init(5, true, 37 + 12, 56);
        recipeLayout.getItemStacks().init(6, true, 37 + 3, 34);
        recipeLayout.getItemStacks().init(7, true, 37 + 12, 12);
        recipeLayout.getItemStacks().init(8, false, 37 + 96, 33);

        recipeLayout.getItemStacks().set(ingredients);
        FluidStack input = recipe.getLiquid();
        FluidStack output = recipe.getLiquidOutput();
        if(recipe.getFluidLevelsConsumed() != 0) {
            if(!input.isEmpty())
                input.setAmount(Mth.clamp(recipe.getFluidLevelsConsumed(), 0, 2000));

        }
        else {
            if(!output.isEmpty())
                output.setAmount(2000);

        }

        if(!output.isEmpty())
            output.setAmount(2000-recipe.getFluidLevelsConsumed());

        if(!input.isEmpty()) {
            recipeLayout.getFluidStacks().init(9, true, 11, 7, 14, 12, 2000, false, this.liquid);
            recipeLayout.getFluidStacks().set(9, input);
        }
        if(!output.isEmpty()) {
            recipeLayout.getFluidStacks().init(10, false, 135, 68, 14, 12, 2000, true, this.liquid);
            recipeLayout.getFluidStacks().set(10, output);
        }
    }

    @Override
    public void draw(MixingCauldronRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {



    }
}