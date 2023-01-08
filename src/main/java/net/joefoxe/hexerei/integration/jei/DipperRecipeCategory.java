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
import net.joefoxe.hexerei.data.recipes.DipperRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DipperRecipeCategory implements IRecipeCategory<DipperRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Hexerei.MOD_ID, "dipper");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Hexerei.MOD_ID, "textures/gui/dipper_jei.png");
    public final static ResourceLocation TEXTURE_BLANK =
            new ResourceLocation(Hexerei.MOD_ID, "textures/block/blank.png");
    private final IDrawable background;
    private final IDrawable cauldron;
    private final IDrawable icon;

    private final IDrawable liquid;


    public DipperRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 100, 92);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModBlocks.CANDLE_DIPPER.get()));
        this.liquid = helper.createDrawable(TEXTURE_BLANK, 0, 0, 16, 16);
        this.cauldron = helper.createDrawable(TEXTURE, 106, 3, 12, 9);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends DipperRecipe> getRecipeClass() {
        return DipperRecipe.class;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.CANDLE_DIPPER.get().getName();
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
    public void setRecipe(IRecipeLayoutBuilder builder, DipperRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 15, 14)
                .addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 69, 23)
                .addItemStack(recipe.getResultItem());

        FluidStack input = recipe.getLiquid();
        if(recipe.getFluidLevelsConsumed() != 0) {
            if(!input.isEmpty())
                input.setAmount(Mth.clamp(recipe.getFluidLevelsConsumed(), 0, 2000));
        }

        if(!input.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 17, 36)
                    .setFluidRenderer(recipe.getFluidLevelsConsumed(), false, 12, 9)
                    .setOverlay(this.cauldron, 0, 0)
                    .addIngredients(VanillaTypes.FLUID, List.of(input));

        }
    }

    @Override
    public void draw(DipperRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        int numberOfDips = recipe.getNumberOfDips();
        int dippingTime = recipe.getDippingTime();
        int dryingTime = recipe.getDryingTime();
        Minecraft minecraft = Minecraft.getInstance();

        matrixStack.scale(0.6f, 0.6f, 0.6f);
        String numberOfDipsString = numberOfDips < Integer.MAX_VALUE ? Integer.toString(numberOfDips) : "?";
        TranslatableComponent times_dipped_1 = new TranslatableComponent("gui.jei.category.dipper.times_dipped_1");
        TranslatableComponent times_dipped_3 = new TranslatableComponent("gui.jei.category.dipper.result", numberOfDipsString);
        minecraft.font.draw(matrixStack, times_dipped_1, 6*1.666f, 56*1.666f, 0xFF808080);
        minecraft.font.draw(matrixStack, times_dipped_3, 65*1.666f, 56*1.666f, 0xFF808080);
//
        String dippingTimeString = dippingTime < Integer.MAX_VALUE ? dippingTime / 20 + (dippingTime % 20 == 0 ? "" : ("." + Integer.toString(dippingTime % 20))) : "?";
        if(dippingTimeString.charAt(dippingTimeString.length()-1) == '0' && dippingTime != 0 && dippingTime % 20 != 0)
            dippingTimeString = dippingTimeString.substring(0, dippingTimeString.length()-1);
//        TranslatableComponent dip_time = new TranslatableComponent("gui.jei.category.dipper.dip_time_1", dippingTimeString);
//        minecraft.font.draw(matrixStack, dip_time, 42*1.666f, 27*1.666f, 0xFF808080);
        TranslatableComponent dip_time_1 = new TranslatableComponent("gui.jei.category.dipper.dip_time_1");
        TranslatableComponent dip_time_3 = new TranslatableComponent("gui.jei.category.dipper.resultSeconds", dippingTimeString);
        minecraft.font.draw(matrixStack, dip_time_1, 6*1.666f, 68*1.666f, 0xFF808080);
        minecraft.font.draw(matrixStack, dip_time_3, 65*1.666f, 68*1.666f, 0xFF808080);

        String dryingTimeString = dryingTime < Integer.MAX_VALUE ? dryingTime / 20 + (dryingTime % 20 == 0 ? "" : ("." + Integer.toString(dryingTime % 20))) : "?";
        if(dryingTimeString.charAt(dryingTimeString.length()-1) == '0' && dryingTime != 0 && dryingTime % 20 != 0)
            dryingTimeString = dryingTimeString.substring(0, dryingTimeString.length()-1);
//        TranslatableComponent dry_time = new TranslatableComponent("gui.jei.category.dipper.dry_time", dryingTimeString);
//        minecraft.font.draw(matrixStack, dry_time, 42*1.666f, 42*1.666f, 0xFF808080);
        TranslatableComponent dry_time_1 = new TranslatableComponent("gui.jei.category.dipper.dry_time_1");
        TranslatableComponent dry_time_3 = new TranslatableComponent("gui.jei.category.dipper.resultSeconds", dryingTimeString);
        minecraft.font.draw(matrixStack, dry_time_1, 6*1.666f, 80*1.666f, 0xFF808080);
        minecraft.font.draw(matrixStack, dry_time_3, 65*1.666f, 80*1.666f, 0xFF808080);

        String outputName = recipe.getResultItem().getHoverName().getString();
        minecraft.font.draw(matrixStack, outputName, 5*1.666f, 4*1.666f, 0xFF404040);

    }
}