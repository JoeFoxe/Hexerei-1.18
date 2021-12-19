package net.joefoxe.hexerei.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.container.MixingCauldronContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class MixingCauldronScreen extends AbstractContainerScreen<MixingCauldronContainer> {
    private final ResourceLocation GUI = new ResourceLocation(Hexerei.MOD_ID,
            "textures/gui/mixing_cauldron_gui.png");

    public MixingCauldronScreen(MixingCauldronContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);

        inventoryLabelY = 76;
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);

        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize() + 1);
        this.blit(matrixStack, i + 75, j - 30, 189, 0, 26, 26);
        this.blit(matrixStack, i + 150, j  + 22, 176, 26, 44, 44);
        this.blit(matrixStack, i - 18, j  + 22, 176, 26, 44, 44);
        this.blit(matrixStack, i - 4, j  + 36, 176, 70, 16, 16);

        if(this.menu.getCraftPercent() > 0.1f) {
            this.blit(matrixStack, i + 83, j + 31, 215 + ((Math.max(Math.round(this.menu.getCraftPercent() * 5 ) - 1, 0)) * 8), 0, 8, 25);
        }

        Minecraft minecraft = Minecraft.getInstance();

        RenderSystem.setShaderTexture(0, GUI);

        ItemRenderer itemRenderer = minecraft.getItemRenderer();

        RenderSystem.disableDepthTest();
        itemRenderer.renderGuiItem(new ItemStack(ModBlocks.MIXING_CAULDRON.get().asItem()),
                this.leftPos + 80,
                this.topPos - 25);
        RenderSystem.enableDepthTest();
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        //Rendering the cauldron item on the top of the screen

        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }


}