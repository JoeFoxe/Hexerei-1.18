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
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class MixingCauldronRecipeCategory implements IRecipeCategory<MixingCauldronRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Hexerei.MOD_ID, "mixing_cauldron");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Hexerei.MOD_ID, "textures/gui/mixing_cauldron_gui.png");
    public final static ResourceLocation TEXTURE_EXRTA =
            new ResourceLocation(Hexerei.MOD_ID, "textures/gui/mixing_cauldron_gui_jei.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic cauldron_liquid_state;
    private final IDrawableStatic liquid_water_3;
    private final IDrawableStatic liquid_water_2;
    private final IDrawableStatic liquid_water_1;
    private final IDrawableStatic liquid_lava_3;
    private final IDrawableStatic liquid_lava_2;
    private final IDrawableStatic liquid_lava_1;
    private final IDrawableStatic liquid_milk_3;
    private final IDrawableStatic liquid_milk_2;
    private final IDrawableStatic liquid_milk_1;
    private final IDrawableStatic liquid_tallow_3;
    private final IDrawableStatic liquid_tallow_2;
    private final IDrawableStatic liquid_tallow_1;
    private final IDrawableStatic liquid_blood_3;
    private final IDrawableStatic liquid_blood_2;
    private final IDrawableStatic liquid_blood_1;
    private final IDrawableStatic liquid_quicksilver_3;
    private final IDrawableStatic liquid_quicksilver_2;
    private final IDrawableStatic liquid_quicksilver_1;

    private final IDrawable liquid;


    public MixingCauldronRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE_EXRTA, 96, 170, 160, 86);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.MIXING_CAULDRON.get()));
        this.liquid = helper.createDrawableIngredient(new FluidStack(ModFluids.BLOOD_FLUID.get(),1000));
        this.cauldron_liquid_state = helper.createDrawable(TEXTURE_EXRTA, 0, 0, 28, 26);
        this.liquid_water_3 = helper.createDrawable(TEXTURE_EXRTA, 28, 0,  28, 26);
        this.liquid_water_2 = helper.createDrawable(TEXTURE_EXRTA, 28, 26, 28, 26);
        this.liquid_water_1 = helper.createDrawable(TEXTURE_EXRTA, 28, 52, 28, 26);
        this.liquid_lava_3 = helper.createDrawable(TEXTURE_EXRTA, 28 * 2, 0,   28, 26);
        this.liquid_lava_2 = helper.createDrawable(TEXTURE_EXRTA, 28 * 2, 26,  28, 26);
        this.liquid_lava_1 = helper.createDrawable(TEXTURE_EXRTA, 28 * 2, 52,  28, 26);
        this.liquid_milk_3 = helper.createDrawable(TEXTURE_EXRTA, 28 * 3, 0,   28, 26);
        this.liquid_milk_2 = helper.createDrawable(TEXTURE_EXRTA, 28 * 3, 26,  28, 26);
        this.liquid_milk_1 = helper.createDrawable(TEXTURE_EXRTA, 28 * 3, 52,  28, 26);
        this.liquid_tallow_3 = helper.createDrawable(TEXTURE_EXRTA, 28 * 4, 0,   28, 26);
        this.liquid_tallow_2 = helper.createDrawable(TEXTURE_EXRTA, 28 * 4, 26,  28, 26);
        this.liquid_tallow_1 = helper.createDrawable(TEXTURE_EXRTA, 28 * 4, 52,  28, 26);
        this.liquid_blood_3 = helper.createDrawable(TEXTURE_EXRTA, 28 * 5, 0,   28, 26);
        this.liquid_blood_2 = helper.createDrawable(TEXTURE_EXRTA, 28 * 5, 26,  28, 26);
        this.liquid_blood_1 = helper.createDrawable(TEXTURE_EXRTA, 28 * 5, 52,  28, 26);
        this.liquid_quicksilver_3 = helper.createDrawable(TEXTURE_EXRTA, 28 * 6, 0,   28, 26);
        this.liquid_quicksilver_2 = helper.createDrawable(TEXTURE_EXRTA, 28 * 6, 26,  28, 26);
        this.liquid_quicksilver_1 = helper.createDrawable(TEXTURE_EXRTA, 28 * 6, 52,  28, 26);
//        this.liquid = helper.createAnimatedDrawable(this.liquid_static, 200, IDrawableAnimated.StartDirection.TOP, false);
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
    }

    @Override
    public void draw(MixingCauldronRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {

//        this.cauldron_liquid_state.draw(matrixStack, 4, 1);
        if(recipe.getLiquid() == LiquidType.WATER) {
            this.liquid_water_3.draw(matrixStack, 4, -1);

            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_water_1.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_water_2.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 3)
                this.liquid_water_3.draw(matrixStack, 66, 30);

        }
        if(recipe.getLiquid() == LiquidType.LAVA) {
            this.liquid_lava_3.draw(matrixStack, 4, -1);

            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_lava_1.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_lava_2.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 3)
                this.liquid_lava_3.draw(matrixStack, 66, 30);

        }
        if(recipe.getLiquid() == LiquidType.MILK) {
            this.liquid_milk_3.draw(matrixStack, 4, -1);

            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_milk_1.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_milk_2.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 3)
                this.liquid_milk_3.draw(matrixStack, 66, 30);

        }
        if(recipe.getLiquid() == LiquidType.TALLOW) {
            this.liquid_tallow_3.draw(matrixStack, 4, -1);

            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_tallow_1.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_tallow_2.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 3)
                this.liquid_tallow_3.draw(matrixStack, 66, 30);

        }
        if(recipe.getLiquid() == LiquidType.BLOOD) {
            this.liquid_blood_3.draw(matrixStack, 4, -1);

            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_blood_1.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_blood_2.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 3)
                this.liquid_blood_3.draw(matrixStack, 66, 30);

        }
        if(recipe.getLiquid() == LiquidType.QUICKSILVER) {
            this.liquid_quicksilver_3.draw(matrixStack, 4, -1);

            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_quicksilver_1.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_quicksilver_2.draw(matrixStack, 66, 30);

            if(recipe.getFluidLevelsConsumed() == 3)
                this.liquid_quicksilver_3.draw(matrixStack, 66, 30);

        }

        // OUTPUT
        if(recipe.getLiquidOutput() == LiquidType.WATER) {
            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_water_1.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_water_2.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 0)
                this.liquid_water_3.draw(matrixStack, 128, 60);
        }
        if(recipe.getLiquidOutput() == LiquidType.LAVA) {
            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_lava_1.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_lava_2.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 0)
                this.liquid_lava_3.draw(matrixStack, 128, 60);
        }
        if(recipe.getLiquidOutput() == LiquidType.MILK) {
            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_milk_1.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_milk_2.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 0)
                this.liquid_milk_3.draw(matrixStack, 128, 60);
        }
        if(recipe.getLiquidOutput() == LiquidType.TALLOW) {
            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_tallow_1.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_tallow_2.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 0)
                this.liquid_tallow_3.draw(matrixStack, 128, 60);
        }
        if(recipe.getLiquidOutput() == LiquidType.BLOOD) {
            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_blood_1.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_blood_2.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 0)
                this.liquid_blood_3.draw(matrixStack, 128, 60);
        }
        if(recipe.getLiquidOutput() == LiquidType.QUICKSILVER) {
            if(recipe.getFluidLevelsConsumed() == 2)
                this.liquid_quicksilver_1.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 1)
                this.liquid_quicksilver_1.draw(matrixStack, 128, 60);
            if(recipe.getFluidLevelsConsumed() == 0)
                this.liquid_quicksilver_1.draw(matrixStack, 128, 60);
        }

//        Minecraft minecraft = Minecraft.getInstance();
//
//        minecraft.getTextureManager().bindForSetup(TEXTURE);
//
//        ItemRenderer itemRenderer = minecraft.getItemRenderer();
//
//        RenderSystem.disableDepthTest();
//        itemRenderer.renderGuiItem(new ItemStack(ModBlocks.MIXING_CAULDRON.get().asItem()),
//                80,
//                -25);
//        RenderSystem.enableDepthTest();



    }
}