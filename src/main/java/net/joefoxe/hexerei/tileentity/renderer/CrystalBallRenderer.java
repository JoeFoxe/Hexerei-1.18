package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.CrystalBall;
import net.joefoxe.hexerei.block.custom.MixingCauldron;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.CrystalBallTile;
import net.joefoxe.hexerei.tileentity.MixingCauldronTile;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import com.mojang.math.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;

public class CrystalBallRenderer implements BlockEntityRenderer<CrystalBallTile> {


    @Override
    public void render(CrystalBallTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(!tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).hasBlockEntity())
            return;

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f / 16f, 9f / 16f, 8f / 16f);
        matrixStackIn.translate(0f/16f , tileEntityIn.orbOffset/16f, 0f/16f);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesSpun * 4));
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CRYSTAL_BALL_ORB.get().defaultBlockState());
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f/16f , 7f/16f, 8f/16f);
        matrixStackIn.translate(0f/16f , tileEntityIn.largeRingOffset/16f, 0f/16f);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun * 6));
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CRYSTAL_BALL_LARGE_RING.get().defaultBlockState());
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(8f/16f , 4.5f/16f, 8f/16f);
        matrixStackIn.translate(0f/16f , tileEntityIn.smallRingOffset/16f, 0f/16f);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesSpun * 6));
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CRYSTAL_BALL_SMALL_RING.get().defaultBlockState());
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CRYSTAL_BALL_STAND.get().defaultBlockState());
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
