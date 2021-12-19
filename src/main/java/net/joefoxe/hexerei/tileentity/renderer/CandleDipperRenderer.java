package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.Candle;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.CandleDipperTile;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import com.mojang.math.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;

public class CandleDipperRenderer implements BlockEntityRenderer<CandleDipperTile> {


    public static double getDistanceToEntity(Entity entity, BlockPos pos) {
        double deltaX = entity.getX() - pos.getX();
        double deltaY = entity.getY() - pos.getY();
        double deltaZ = entity.getZ() - pos.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    @Override
    public void render(CandleDipperTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(!tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).hasBlockEntity())
            return;

        float rotation = 0;

        if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH) {
            rotation = 180;
        } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
            rotation = 0;
        } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
            rotation = 90;
        } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
            rotation = 270;
        }

        matrixStackIn.pushPose();
        matrixStackIn.translate(tileEntityIn.candlePos1.x(), tileEntityIn.candlePos1.y(), tileEntityIn.candlePos1.z());
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
        if(!(Block.byItem(tileEntityIn.getItems().get(0).getItem()) instanceof Candle)) {
            renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_WICK_BASE.get().defaultBlockState());
        }
        matrixStackIn.popPose();

        if(!tileEntityIn.getItems().get(0).isEmpty()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(tileEntityIn.candlePos1.x(), tileEntityIn.candlePos1.y(), tileEntityIn.candlePos1.z());
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
            if(tileEntityIn.getItems().get(0).getItem() == Items.STRING) {
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_WICK.get().defaultBlockState());
            }
            else {
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                matrixStackIn.translate(0, 3.25f/16f + 0.0001f, 0);

                if(!(Block.byItem(tileEntityIn.getItems().get(0).getItem()) instanceof Candle)) {
                    matrixStackIn.scale(0.4f, 0.4f, 0.4f);
                    matrixStackIn.translate(0, -1.5f/16f, 0);
                }
                renderItem(new ItemStack(tileEntityIn.getItems().get(0).getItem()), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            }
            matrixStackIn.popPose();
        }
        if(tileEntityIn.candle1DippedTimes > 0 && tileEntityIn.getItems().get(0).getItem() == ModBlocks.CANDLE.get().asItem() || tileEntityIn.getItems().get(0).getItem() == Items.STRING) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(tileEntityIn.candlePos1.x(), tileEntityIn.candlePos1.y(), tileEntityIn.candlePos1.z());
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
            if(tileEntityIn.candle1DippedTimes == 1)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_1.get().defaultBlockState());
            if(tileEntityIn.candle1DippedTimes == 2)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_2.get().defaultBlockState());
            if(tileEntityIn.candle1DippedTimes == 3)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_3.get().defaultBlockState());
            matrixStackIn.popPose();
        }




        matrixStackIn.pushPose();
        matrixStackIn.translate(tileEntityIn.candlePos2.x(), tileEntityIn.candlePos2.y(), tileEntityIn.candlePos2.z());
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
        if(!(Block.byItem(tileEntityIn.getItems().get(1).getItem()) instanceof Candle)) {
            renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_WICK_BASE.get().defaultBlockState());
        }
        matrixStackIn.popPose();

        if(!tileEntityIn.getItems().get(1).isEmpty()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(tileEntityIn.candlePos2.x(), tileEntityIn.candlePos2.y(), tileEntityIn.candlePos2.z());
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
            if(tileEntityIn.getItems().get(1).getItem() == Items.STRING) {
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_WICK.get().defaultBlockState());
            }
            else {
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                matrixStackIn.translate(0, 3.25f/16f + 0.0001f, 0);

                if(!(Block.byItem(tileEntityIn.getItems().get(1).getItem()) instanceof Candle)) {
                    matrixStackIn.scale(0.4f, 0.4f, 0.4f);
                    matrixStackIn.translate(0, -1.5f/16f, 0);
                }
                renderItem(new ItemStack(tileEntityIn.getItems().get(1).getItem()), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            }
            matrixStackIn.popPose();
        }


        if(tileEntityIn.candle2DippedTimes > 0 && tileEntityIn.getItems().get(1).getItem() == ModBlocks.CANDLE.get().asItem() || tileEntityIn.getItems().get(1).getItem() == Items.STRING) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(tileEntityIn.candlePos2.x(), tileEntityIn.candlePos2.y(), tileEntityIn.candlePos2.z());
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
            if(tileEntityIn.candle2DippedTimes == 1)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_1.get().defaultBlockState());
            if(tileEntityIn.candle2DippedTimes == 2)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_2.get().defaultBlockState());
            if(tileEntityIn.candle2DippedTimes == 3)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_3.get().defaultBlockState());
            matrixStackIn.popPose();
        }



        matrixStackIn.pushPose();
        matrixStackIn.translate(tileEntityIn.candlePos3.x(), tileEntityIn.candlePos3.y(), tileEntityIn.candlePos3.z());
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
        if(!(Block.byItem(tileEntityIn.getItems().get(2).getItem()) instanceof Candle)) {
            renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_WICK_BASE.get().defaultBlockState());
        }
        matrixStackIn.popPose();

        if(!tileEntityIn.getItems().get(2).isEmpty()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(tileEntityIn.candlePos3.x(), tileEntityIn.candlePos3.y(), tileEntityIn.candlePos3.z());
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
            if(tileEntityIn.getItems().get(2).getItem() == Items.STRING) {
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_WICK.get().defaultBlockState());
            }
            else {
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                matrixStackIn.translate(0, 3.25f/16f + 0.0001f, 0);

                if(!(Block.byItem(tileEntityIn.getItems().get(2).getItem()) instanceof Candle)) {
                    matrixStackIn.scale(0.4f, 0.4f, 0.4f);
                    matrixStackIn.translate(0, -1.5f/16f, 0);
                }
                renderItem(new ItemStack(tileEntityIn.getItems().get(2).getItem()), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            }
            matrixStackIn.popPose();
        }

        if(tileEntityIn.candle3DippedTimes > 0 && tileEntityIn.getItems().get(2).getItem() == ModBlocks.CANDLE.get().asItem() || tileEntityIn.getItems().get(2).getItem() == Items.STRING) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(tileEntityIn.candlePos3.x(), tileEntityIn.candlePos3.y(), tileEntityIn.candlePos3.z());
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
            if(tileEntityIn.candle3DippedTimes == 1)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_1.get().defaultBlockState());
            if(tileEntityIn.candle3DippedTimes == 2)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_2.get().defaultBlockState());
            if(tileEntityIn.candle3DippedTimes == 3)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.CANDLE_DIPPER_CANDLE_3.get().defaultBlockState());
            matrixStackIn.popPose();
        }

//
//        matrixStackIn.pushPose();
//        matrixStackIn.translate(8f/16f , 18f/16f, 8f/16f);
//        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
//        matrixStackIn.translate(-(float)Math.sin((tileEntityIn.degreesSpun+90f)/57.1f)/32f , 0f/16f, -(float)Math.cos((tileEntityIn.degreesSpun+90f)/57.1f)/32f);
//        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
//        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
//        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-tileEntityIn.degreesOpened));
//        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(tileEntityIn.degreesFlopped));
//        matrixStackIn.translate(0,0, -(tileEntityIn.degreesFlopped/10f)/32);
//        matrixStackIn.translate(0 , (-0.5f * (tileEntityIn.degreesFlopped / 90))/16f, -(float)Math.sin((tileEntityIn.degreesFlopped)/57.1f)/32f);
//        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_BACK.get().defaultBlockState());
//        matrixStackIn.popPose();
//
//        matrixStackIn.pushPose();
//        matrixStackIn.translate(8f/16f , 18f/16f, 8f/16f);
//        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
//        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
//        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesFlopped));
//        matrixStackIn.translate(0,0,-(tileEntityIn.degreesFlopped/10f)/32);
//        //matrixStackIn.translate(-(float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesFlopped/5f) , 0f/16f, -(float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesFlopped/5f) );
//        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_BINDING.get().defaultBlockState());
//        matrixStackIn.popPose();
//
//        matrixStackIn.pushPose();
//        matrixStackIn.translate(8f / 16f, 18f / 16f, 8f / 16f);
//        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
//        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
//        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesFlopped));
//        matrixStackIn.translate(0,0,-(tileEntityIn.degreesFlopped/10f)/32);
//        matrixStackIn.translate(0,1f/32f,0);
//        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees((70f-tileEntityIn.degreesOpened/1.29f)));
//        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_PAGE.get().defaultBlockState());
//        matrixStackIn.popPose();
//
//        matrixStackIn.pushPose();
//        matrixStackIn.translate(8f / 16f, 18f / 16f, 8f / 16f);
//        matrixStackIn.translate((float)Math.sin((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f) , 0f/16f, (float)Math.cos((tileEntityIn.degreesSpun)/57.1f)/32f * (tileEntityIn.degreesOpened/5f - 12f));
//        matrixStackIn.translate(0 , -((tileEntityIn.degreesFlopped / 90))/16f, 0);
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tileEntityIn.degreesSpun));
//        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-tileEntityIn.degreesOpened));
//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-tileEntityIn.degreesFlopped));
//        matrixStackIn.translate(0,0,-(tileEntityIn.degreesFlopped/10f)/32);
//        matrixStackIn.translate(0,1f/32f,0);
//        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-(70f-tileEntityIn.degreesOpened/1.29f)));
//        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BOOK_OF_SHADOWS_PAGE.get().defaultBlockState());
//        matrixStackIn.popPose();




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
