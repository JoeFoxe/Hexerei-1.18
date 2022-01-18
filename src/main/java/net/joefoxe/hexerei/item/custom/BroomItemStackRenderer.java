package net.joefoxe.hexerei.item.custom;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.model.BroomModel;
import net.joefoxe.hexerei.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

import javax.annotation.Nonnull;

//public class BroomItemStackRenderer extends BlockEntityWithoutLevelRenderer {
//    private final Minecraft minecraft = Minecraft.getInstance();
//
//    private final EntityModelSet modelSet;
//    private BroomModel broomModel;
//
//    public static final BroomItemStackRenderer RENDERER = new BroomItemStackRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
//
//    public BroomItemStackRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
//        super(blockEntityRenderDispatcher, entityModelSet);
//        this.modelSet = entityModelSet;
//    }
//
//    @Override
//    public void onResourceManagerReload(@Nonnull ResourceManager resourceManager) {
//        this.broomModel = new BroomModel(modelSet.bakeLayer(BroomModel.LAYER_LOCATION));
//    }
//
//    @Override
//    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
//        matrixStack.popPose();
//        matrixStack.pushPose();
//        ItemRenderer itemRenderer = minecraft.getItemRenderer();
//        BakedModel model = itemRenderer.getModel(stack, null, minecraft.player, 0);
//
//        boolean leftHand = minecraft.player != null && minecraft.player.getOffhandItem() == stack;
//        model = ForgeHooksClient.handleCameraTransforms(matrixStack, model, transformType, leftHand);
//        matrixStack.translate(-0.5D, -0.5D, -0.5D);
//        RenderType rendertype = ItemBlockRenderTypes.getRenderType(stack, true);
//        VertexConsumer ivertexbuilder = ItemRenderer.getFoilBufferDirect(buffer, rendertype, true, stack.hasFoil());
//        itemRenderer.renderModelLists(model, stack, combinedLight, combinedOverlay, matrixStack, ivertexbuilder);
//        matrixStack.translate(0.5, 0.6, 0.25);
//        matrixStack.scale(0.5f, 0.5f, 0.5f);
//        itemRenderer.renderStatic(new ItemStack(ModItems.LARGE_SATCHEL.get()), ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrixStack, buffer, 0);
////        stack.getCapability(CapabilityBackpackWrapper.getCapabilityInstance()).ifPresent(backpackWrapper -> {
////            BackpackRenderInfo.ItemDisplayRenderInfo itemDisplayRenderInfo = backpackWrapper.getRenderInfo().getItemDisplayRenderInfo();
////            ItemStack displayItem = itemDisplayRenderInfo.getItem();
////            if (!displayItem.isEmpty()) {
////                matrixStack.translate(0.5, 0.6, 0.25);
////                matrixStack.scale(0.5f, 0.5f, 0.5f);
////                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(itemDisplayRenderInfo.getRotation()));
////                itemRenderer.renderStatic(displayItem, ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrixStack, buffer, 0);
////            }
////        });
//    }
//}

public class BroomItemStackRenderer extends BlockEntityWithoutLevelRenderer {
    private final BroomModel normalModel;

    public BroomItemStackRenderer(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
        super(p_172550_, p_172551_);
        normalModel = new BroomModel(p_172551_.bakeLayer(BroomModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack p_239207_1_, ItemTransforms.TransformType p_239207_2_, PoseStack p_239207_3_, MultiBufferSource p_239207_4_, int p_239207_5_, int p_239207_6_) {
        {
            p_239207_3_.pushPose();
            p_239207_3_.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer ivertexbuilder = ItemRenderer.getFoilBufferDirect(p_239207_4_, this.normalModel.renderType(this.getTexture(p_239207_1_)), true, p_239207_1_.hasFoil());
            this.normalModel.renderToBuffer(p_239207_3_, ivertexbuilder, p_239207_5_, p_239207_6_, 1.0F, 1.0F, 1.0F, 1.0F);
            p_239207_3_.popPose();
        }
    }

//    public static void renderItemModelIntoGUI(ItemStack stack, int combinedLight, BakedModel model) {
//        RenderSystem.getModelViewStack().pushPose();
//        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
//        Minecraft.getInstance().textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
//
//        RenderSystem.enableBlend();
//        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        PoseStack matrixstack = new PoseStack();
//        MultiBufferSource.BufferSource irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
//
//        Lighting.setupForFlatItems();
//
//        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.GUI, false, matrixstack, irendertypebuffer$impl, combinedLight, OverlayTexture.NO_OVERLAY, model);
//        irendertypebuffer$impl.endBatch();
//        RenderSystem.enableDepthTest();
//        Lighting.setupFor3DItems();
//
//
//        RenderSystem.getModelViewStack().popPose();
//    }

    private ResourceLocation getTexture(ItemStack stack) {
            return new ResourceLocation(Hexerei.MOD_ID,"textures/entity/broom_large_satchel.png");
    }
}