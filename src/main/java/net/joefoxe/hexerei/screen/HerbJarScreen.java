package net.joefoxe.hexerei.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.container.HerbJarContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class HerbJarScreen extends AbstractContainerScreen<HerbJarContainer> {
    private final ResourceLocation GUI = new ResourceLocation(Hexerei.MOD_ID,
            "textures/gui/herb_jar_gui.png");
    private final ResourceLocation INVENTORY = new ResourceLocation(Hexerei.MOD_ID,
            "textures/gui/inventory.png");

    public HerbJarScreen(HerbJarContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        inventoryLabelY = 135;
        inventoryLabelX = 8;
        titleLabelY = 1;
        titleLabelX = 52;
    }

    @Override
    protected void init() {
        super.init();

//        this.addButton(new ImageButton(this.leftPos + 188, this.topPos , 18, 18, 230, 26, 18, GUI, (button) -> {
////            this.recipeBookGui.initSearchBar(this.widthTooNarrow);
////            this.recipeBookGui.toggleVisibility();
////            this.leftPos = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
//            ((ImageButton)button).setPosition(this.leftPos + 188, this.topPos );
//        }));
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {


        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    public Component getTitle() {
        return super.getTitle();
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);

        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j - 3, 0, 0, 214, 157);
        this.blit(matrixStack, i + 78, j - 30, 230, 0, 26, 26);

        RenderSystem.setShaderTexture(0, INVENTORY);
        this.blit(matrixStack, i + 3, j + 129, 0, 0, 176, 100);

        Minecraft minecraft = Minecraft.getInstance();

        RenderSystem.setShaderTexture(0, GUI);

        ItemRenderer itemRenderer = minecraft.getItemRenderer();
        RenderSystem.disableDepthTest();
        itemRenderer.renderGuiItem(new ItemStack(ModBlocks.HERB_JAR.get().asItem()),
                this.leftPos + 83,
                this.topPos - 25);

//        InventoryScreen.drawEntityOnScreen(this.leftPos + 107, this.topPos + 88, 20, (float)(this.leftPos + 107 - mouseX) , (float)(this.topPos + 88 - 30 - mouseY), this.minecraft.player);

        RenderSystem.enableDepthTest();


    }

    @Override
    public boolean mouseClicked(double x, double y, int button) {
        boolean mouseClicked = super.mouseClicked(x, y, button);


//        if(x > this.leftPos + 188 && x < this.leftPos + 188 + 18 &&  y > this.topPos + 129 && y < this.topPos + 129 + 18){
//            Minecraft.getInstance().getSoundHandler().play(SimpleSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
//        }
//        this.container.playSound();

        return mouseClicked;
    }
}