package net.joefoxe.hexerei.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.data.recipes.DryingRackRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class DryingRackRecipeCategory implements IRecipeCategory<DryingRackRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Hexerei.MOD_ID, "drying_rack");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Hexerei.MOD_ID, "textures/gui/drying_rack_jei.png");
    private final IDrawable background;
    private final IDrawable icon;


    public DryingRackRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 100, 53);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.HERB_DRYING_RACK.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends DryingRackRecipe> getRecipeClass() {
        return DryingRackRecipe.class;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.HERB_DRYING_RACK.get().getName();
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
    public void setIngredients(DryingRackRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DryingRackRecipe recipe, IIngredients ingredients) {

        recipeLayout.getItemStacks().init(0, true, 13, 15);
        recipeLayout.getItemStacks().init(1, false, 69, 15);

        recipeLayout.getItemStacks().set(ingredients);

    }

    @Override
    public void draw(DryingRackRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {

        int dryingTime = recipe.getDryingTime();
        Minecraft minecraft = Minecraft.getInstance();

        matrixStack.scale(0.6f, 0.6f, 0.6f);
        String dryingTimeString = dryingTime < Integer.MAX_VALUE ? dryingTime / 20 + (dryingTime % 20 == 0 ? "" : ("." + Integer.toString(dryingTime % 20))) : "?";
        if(dryingTimeString.charAt(dryingTimeString.length()-1) == '0' && dryingTime != 0 && dryingTime % 20 != 0)
            dryingTimeString = dryingTimeString.substring(0, dryingTimeString.length()-1);
        TranslatableComponent dip_time_1 = new TranslatableComponent("gui.jei.category.dipper.dry_time_1");
        TranslatableComponent dip_time_3 = new TranslatableComponent("gui.jei.category.dipper.resultSeconds", dryingTimeString);
        minecraft.font.draw(matrixStack, dip_time_1, 6*1.666f, 41*1.666f, 0xFF808080);
        minecraft.font.draw(matrixStack, dip_time_3, 55*1.666f, 41*1.666f, 0xFF808080);

        String outputName = recipe.getResultItem().getHoverName().getString();
        minecraft.font.draw(matrixStack, outputName, 5*1.666f, 4*1.666f, 0xFF404040);

    }
}