package net.joefoxe.hexerei.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.data.recipes.PestleAndMortarRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class PestleAndMortarRecipeCategory implements IRecipeCategory<PestleAndMortarRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Hexerei.MOD_ID, "pestle_and_mortar");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Hexerei.MOD_ID, "textures/gui/pestle_and_mortar_jei.png");
    private final IDrawable background;
    private final IDrawable icon;


    public PestleAndMortarRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 143, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModBlocks.PESTLE_AND_MORTAR.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends PestleAndMortarRecipe> getRecipeClass() {
        return PestleAndMortarRecipe.class;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.PESTLE_AND_MORTAR.get().getName();
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
    public void setRecipe(IRecipeLayoutBuilder builder, PestleAndMortarRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 11, 14)
                .addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 36)
                .addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 41, 45)
                .addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 36)
                .addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 73, 14)
                .addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 117, 31)
                .addItemStack(recipe.getResultItem());

    }


    @Override
    public void draw(PestleAndMortarRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        int grindingTime = recipe.getGrindingTime();
        Minecraft minecraft = Minecraft.getInstance();

        matrixStack.scale(0.6f, 0.6f, 0.6f);
        String grindingTimeString = grindingTime < Integer.MAX_VALUE ? grindingTime / 20 + (grindingTime % 20 == 0 ? "" : ("." + Integer.toString(grindingTime % 20))) : "?";
        if(grindingTimeString.charAt(grindingTimeString.length()-1) == '0' && grindingTime != 0 && grindingTime % 20 != 0)
            grindingTimeString = grindingTimeString.substring(0, grindingTimeString.length()-1);
        TranslatableComponent dip_time_1 = new TranslatableComponent("gui.jei.category.pestle_and_mortar.grind_time_1");
        TranslatableComponent dip_time_3 = new TranslatableComponent("gui.jei.category.dipper.resultSeconds", grindingTimeString);


        minecraft.font.draw(matrixStack, dip_time_1, 6*1.666f, 68.5f*1.666f, 0xFF808080);
        minecraft.font.draw(matrixStack, dip_time_3, (58*1.666f), 68.5f*1.666f, 0xFF808080);

        String outputName = recipe.getResultItem().getHoverName().getString();
        minecraft.font.draw(matrixStack, outputName, 5*1.666f, 4*1.666f, 0xFF404040);


    }
}