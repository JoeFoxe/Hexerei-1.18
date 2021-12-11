package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.Candle;
import net.joefoxe.hexerei.tileentity.CandleTile;
import net.joefoxe.hexerei.tileentity.CrystalBallTile;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import com.mojang.math.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;

public class CandleRenderer implements BlockEntityRenderer<CandleTile> {


    @Override
    public void render(CandleTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(!tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).hasBlockEntity())
            return;

//        matrixStackIn.pushPose();
//        matrixStackIn.translate(8f/16f , 4.5f/16f, 8f/16f);
//        matrixStackIn.translate(0f/16f , tileEntityIn.smallRingOffset/16f, 0f/16f);
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesSpun * 6));
//        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CRYSTAL_BALL_SMALL_RING.get().defaultBlockState());
//        matrixStackIn.popPose();
        if(tileEntityIn.candleType1 != 0) {

            matrixStackIn.pushPose();
            matrixStackIn.translate(8f/16f , 0f/16f, 8f/16f);
            matrixStackIn.translate(tileEntityIn.candlePosX1 , tileEntityIn.candlePosY1, tileEntityIn.candlePosZ1);
            if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270f));
//                matrixStackIn.translate(0 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180f));
//                matrixStackIn.translate(-1 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90f));
//                matrixStackIn.translate(-1 , 0, 0);
            }

            if(tileEntityIn.candlePosX1 == 0 && tileEntityIn.candlePosY1 == 0 && tileEntityIn.candlePosZ1 == 0) {
                if (tileEntityIn.numberOfCandles == 4)
                    matrixStackIn.translate(3f / 16f, 0f / 16f, 2f / 16f);
                else if (tileEntityIn.numberOfCandles == 3)
                    matrixStackIn.translate(-1f / 16f, 0f / 16f, 3f / 16f);
                else if (tileEntityIn.numberOfCandles == 2)
                    matrixStackIn.translate(3f / 16f, 0f / 16f, -2f / 16f);
            }

            if(tileEntityIn.candleHeight1 == 0)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_0_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            if(tileEntityIn.candleHeight1 == 1)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_1_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            if(tileEntityIn.candleHeight1 == 2)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_2_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            if(tileEntityIn.candleHeight1 == 3)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_3_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            if(tileEntityIn.candleHeight1 == 4)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_4_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            if(tileEntityIn.candleHeight1 == 5)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_5_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            if(tileEntityIn.candleHeight1 == 6)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_6_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            if(tileEntityIn.candleHeight1 == 7)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_7_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType1));
            matrixStackIn.popPose();
        }
        if(tileEntityIn.candleType2 != 0) {

            matrixStackIn.pushPose();
            matrixStackIn.translate(tileEntityIn.candlePosX2 , tileEntityIn.candlePosY2, tileEntityIn.candlePosZ2);
            matrixStackIn.translate(8f/16f , 0f/16f, 8f/16f);
            if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270f));
//                matrixStackIn.translate(0 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180f));
//                matrixStackIn.translate(-1 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90f));
//                matrixStackIn.translate(-1 , 0, 0);
            }

            if(tileEntityIn.candlePosX2 == 0 && tileEntityIn.candlePosY2 == 0 && tileEntityIn.candlePosZ2 == 0) {
                if(tileEntityIn.numberOfCandles == 4)
                    matrixStackIn.translate(-2f/16f , 0f/16f, -3f/16f);
                else if(tileEntityIn.numberOfCandles == 3)
                    matrixStackIn.translate(3f/16f , 0f/16f, 1f/16f);
                else if(tileEntityIn.numberOfCandles == 2)
                    matrixStackIn.translate(-3f/16f , 0f/16f, 3f/16f);
            }

            if(tileEntityIn.candleHeight2 == 0)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_0_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            if(tileEntityIn.candleHeight2 == 1)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_1_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            if(tileEntityIn.candleHeight2 == 2)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_2_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            if(tileEntityIn.candleHeight2 == 3)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_3_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            if(tileEntityIn.candleHeight2 == 4)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_4_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            if(tileEntityIn.candleHeight2 == 5)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_5_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            if(tileEntityIn.candleHeight2 == 6)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_6_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            if(tileEntityIn.candleHeight2 == 7)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_7_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType2));
            matrixStackIn.popPose();
        }
        if(tileEntityIn.candleType3 != 0) {

            matrixStackIn.pushPose();
            matrixStackIn.translate(8f/16f , 0f/16f, 8f/16f);
            matrixStackIn.translate(tileEntityIn.candlePosX3 , tileEntityIn.candlePosY3, tileEntityIn.candlePosZ3);
            if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270f));
//                matrixStackIn.translate(0 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180f));
//                matrixStackIn.translate(-1 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90f));
//                matrixStackIn.translate(-1 , 0, 0);
            }

            if(tileEntityIn.candlePosX3 == 0 && tileEntityIn.candlePosY3 == 0 && tileEntityIn.candlePosZ3 == 0) {
                if (tileEntityIn.numberOfCandles == 4)
                    matrixStackIn.translate(-2f / 16f, 0f / 16f, 2f / 16f);
                else if (tileEntityIn.numberOfCandles == 3)
                    matrixStackIn.translate(-2f / 16f, 0f / 16f, -3f / 16f);
            }

            if(tileEntityIn.candleHeight3 == 0)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_0_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            if(tileEntityIn.candleHeight3 == 1)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_1_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            if(tileEntityIn.candleHeight3 == 2)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_2_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            if(tileEntityIn.candleHeight3 == 3)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_3_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            if(tileEntityIn.candleHeight3 == 4)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_4_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            if(tileEntityIn.candleHeight3 == 5)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_5_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            if(tileEntityIn.candleHeight3 == 6)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_6_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            if(tileEntityIn.candleHeight3 == 7)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_7_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType3));
            matrixStackIn.popPose();
        }
        if(tileEntityIn.candleType4 != 0) {

            matrixStackIn.pushPose();
            matrixStackIn.translate(8f/16f , 0f/16f, 8f/16f);;
            matrixStackIn.translate(tileEntityIn.candlePosX4 , tileEntityIn.candlePosY4, tileEntityIn.candlePosZ4);
            if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270f));
//                matrixStackIn.translate(0 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180f));
//                matrixStackIn.translate(-1 , 0, -1);
            } else if(tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90f));
//                matrixStackIn.translate(-1 , 0, 0);
            }

            if(tileEntityIn.candlePosX4 == 0 && tileEntityIn.candlePosY4 == 0 && tileEntityIn.candlePosZ4 == 0) {
                matrixStackIn.translate(3f / 16f, 0f / 16f, -2f / 16f);
            }
            if(tileEntityIn.candleHeight4 == 0)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_0_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            if(tileEntityIn.candleHeight4 == 1)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_1_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            if(tileEntityIn.candleHeight4 == 2)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_2_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            if(tileEntityIn.candleHeight4 == 3)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_3_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            if(tileEntityIn.candleHeight4 == 4)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_4_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            if(tileEntityIn.candleHeight4 == 5)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_5_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            if(tileEntityIn.candleHeight4 == 6)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_6_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            if(tileEntityIn.candleHeight4 == 7)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_7_OF_7.get().defaultBlockState().setValue(Candle.SLOT_ONE_TYPE, tileEntityIn.candleType4));
            matrixStackIn.popPose();
        }

    }

    private void renderItem(ItemStack stack, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn,
                            int combinedLightIn) {
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn,
                OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn,1);
    }


    private void renderBlock(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, BlockState state) {
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, matrixStackIn, bufferIn, combinedLightIn, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);

    }


}
