package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.tileentity.BookOfShadowsAltarTile;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraftforge.client.model.data.EmptyModelData;

public class BookOfShadowsAltarRenderer implements BlockEntityRenderer<BookOfShadowsAltarTile> {


    public static double getDistanceToEntity(Entity entity, BlockPos pos) {
        double deltaX = entity.getX() - pos.getX();
        double deltaY = entity.getY() - pos.getY();
        double deltaZ = entity.getZ() - pos.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    @Override
    public void render(BookOfShadowsAltarTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(!tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).hasBlockEntity())
            return;

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f / 16f, 18f / 16f, 8f / 16f);
        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun+90f)/57.1f)/32f , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun+90f)/57.1f)/32f);
        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(tileEntityIn.degreesOpened));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesFlopped));
        matrixStackIn.translate(0,0,-(tileEntityIn.degreesFlopped/10f)/32);
        matrixStackIn.translate(0 , (-0.5f * (tileEntityIn.degreesFlopped / 90))/16f, (float)Math.sin((tileEntityIn.degreesFlopped)/57.1f)/32f);
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_COVER.get().defaultBlockState());
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f/16f , 18f/16f, 8f/16f);
        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
        matrixStackIn.translate(-(float)Math.sin((tileEntityIn.degreesSpun+90f)/57.1f)/32f , 0f/16f, -(float)Math.cos((tileEntityIn.degreesSpun+90f)/57.1f)/32f);
        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-tileEntityIn.degreesOpened));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(tileEntityIn.degreesFlopped));
        matrixStackIn.translate(0,0, -(tileEntityIn.degreesFlopped/10f)/32);
        matrixStackIn.translate(0 , (-0.5f * (tileEntityIn.degreesFlopped / 90))/16f, -(float)Math.sin((tileEntityIn.degreesFlopped)/57.1f)/32f);
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_BACK.get().defaultBlockState());
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f/16f , 18f/16f, 8f/16f);
        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesFlopped));
        matrixStackIn.translate(0,0,-(tileEntityIn.degreesFlopped/10f)/32);
        //matrixStackIn.translate(-(float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesFlopped/5f) , 0f/16f, -(float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesFlopped/5f) );
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_BINDING.get().defaultBlockState());
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f / 16f, 18f / 16f, 8f / 16f);
        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesFlopped));
        matrixStackIn.translate(0,0,-(tileEntityIn.degreesFlopped/10f)/32);
        matrixStackIn.translate(0,1f/32f,0);
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees((70f-tileEntityIn.degreesOpened/1.29f)));
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_PAGE.get().defaultBlockState());
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f / 16f, 18f / 16f, 8f / 16f);
        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesFlopped));
        matrixStackIn.translate(0,0,-(tileEntityIn.degreesFlopped/10f)/32);
        matrixStackIn.translate(0,1f/32f,0);
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-(70f-tileEntityIn.degreesOpened/1.29f)));
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_PAGE.get().defaultBlockState());
        matrixStackIn.popPose();




    }

    private void renderItem(ItemStack stack, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn,
                            int combinedLightIn) {
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn,
                OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, 1);
    }


    private void renderBlock(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, BlockState state) {
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, matrixStackIn, bufferIn, combinedLightIn, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);

    }


}
