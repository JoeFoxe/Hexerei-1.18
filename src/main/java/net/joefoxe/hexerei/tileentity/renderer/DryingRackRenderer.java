package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.Candle;
import net.joefoxe.hexerei.tileentity.DryingRackTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;

public class DryingRackRenderer implements BlockEntityRenderer<DryingRackTile> {


    public static double getDistanceToEntity(Entity entity, BlockPos pos) {
        double deltaX = entity.getX() - pos.getX();
        double deltaY = entity.getY() - pos.getY();
        double deltaZ = entity.getZ() - pos.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    @Override
    public void render(DryingRackTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
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


        if(!tileEntityIn.getItems().get(0).isEmpty()) {
            if(tileEntityIn.getItems().get(0).getItem() == Items.BROWN_MUSHROOM || tileEntityIn.getItems().get(0).getItem() == Items.RED_MUSHROOM) {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                matrixStackIn.translate(0.25f, 0.22f, 0.525f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                matrixStackIn.translate(0f, 0.09f, 0f);
                if(tileEntityIn.getItems().get(0).getItem() == Items.BROWN_MUSHROOM)
                    renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_1.get().defaultBlockState());
                if(tileEntityIn.getItems().get(0).getItem() == Items.RED_MUSHROOM)
                    renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_1.get().defaultBlockState());
                matrixStackIn.popPose();
                if(tileEntityIn.getItems().get(0).getCount() >= 2)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.25f, 0.22f, 0.525f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.translate(0f, -0.03f, 0f);
                    if(tileEntityIn.getItems().get(0).getItem() == Items.BROWN_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_2.get().defaultBlockState());
                    if(tileEntityIn.getItems().get(0).getItem() == Items.RED_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_2.get().defaultBlockState());
                    matrixStackIn.popPose();
                }
                if(tileEntityIn.getItems().get(0).getCount() >= 3)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.25f, 0.22f, 0.525f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.translate(0f, -0.15f, 0f);
                    if(tileEntityIn.getItems().get(0).getItem() == Items.BROWN_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_1.get().defaultBlockState());
                    if(tileEntityIn.getItems().get(0).getItem() == Items.RED_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_1.get().defaultBlockState());
                    matrixStackIn.popPose();
                }
            }
            else
            {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                matrixStackIn.translate(0.25f, 0.22f, 0.525f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                renderItem(tileEntityIn.getItems().get(0), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.popPose();
                if(tileEntityIn.getItems().get(0).getCount() >= 2)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.25f, 0.22f, 0.525f);
                    matrixStackIn.translate(0.075f, 0.05f, -0.025f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                    matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                    renderItem(tileEntityIn.getItems().get(0), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                    matrixStackIn.popPose();
                }
                if(tileEntityIn.getItems().get(0).getCount() >= 3)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.25f, 0.22f, 0.525f);
                    matrixStackIn.translate(-0.075f, 0.025f, -0.025f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                    matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                    renderItem(tileEntityIn.getItems().get(0), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                    matrixStackIn.popPose();
                }
            }

        }

        if(!tileEntityIn.getItems().get(1).isEmpty()) {
            if(tileEntityIn.getItems().get(1).getItem() == Items.BROWN_MUSHROOM || tileEntityIn.getItems().get(1).getItem() == Items.RED_MUSHROOM) {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                matrixStackIn.translate(0.5f, 0.22f, 0.525f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                matrixStackIn.translate(0f, 0.09f, 0f);
                if(tileEntityIn.getItems().get(1).getItem() == Items.BROWN_MUSHROOM)
                    renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_1.get().defaultBlockState());
                if(tileEntityIn.getItems().get(1).getItem() == Items.RED_MUSHROOM)
                    renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_1.get().defaultBlockState());
                matrixStackIn.popPose();
                if(tileEntityIn.getItems().get(1).getCount() >= 2)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.5f, 0.22f, 0.525f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.translate(0f, -0.03f, 0f);
                    if(tileEntityIn.getItems().get(1).getItem() == Items.BROWN_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_2.get().defaultBlockState());
                    if(tileEntityIn.getItems().get(1).getItem() == Items.RED_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_2.get().defaultBlockState());
                    matrixStackIn.popPose();
                }
                if(tileEntityIn.getItems().get(1).getCount() >= 3)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.5f, 0.22f, 0.525f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.translate(0f, -0.15f, 0f);
                    if(tileEntityIn.getItems().get(1).getItem() == Items.BROWN_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_1.get().defaultBlockState());
                    if(tileEntityIn.getItems().get(1).getItem() == Items.RED_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_1.get().defaultBlockState());
                    matrixStackIn.popPose();
                }
            }
            else
            {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                matrixStackIn.translate(0.5f, 0.22f, 0.525f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                renderItem(tileEntityIn.getItems().get(1), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.popPose();
                if(tileEntityIn.getItems().get(1).getCount() >= 2)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.5f, 0.22f, 0.525f);
                    matrixStackIn.translate(0.075f, 0.05f, -0.025f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                    matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                    renderItem(tileEntityIn.getItems().get(1), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                    matrixStackIn.popPose();
                }
                if(tileEntityIn.getItems().get(1).getCount() >= 3)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.5f, 0.22f, 0.525f);
                    matrixStackIn.translate(-0.075f, 0.025f, -0.025f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                    matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                    renderItem(tileEntityIn.getItems().get(1), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                    matrixStackIn.popPose();
                }
            }

        }

        if(!tileEntityIn.getItems().get(2).isEmpty()) {
            if(tileEntityIn.getItems().get(2).getItem() == Items.BROWN_MUSHROOM || tileEntityIn.getItems().get(2).getItem() == Items.RED_MUSHROOM) {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                matrixStackIn.translate(0.75f, 0.22f, 0.525f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                matrixStackIn.translate(0f, 0.09f, 0f);
                if(tileEntityIn.getItems().get(2).getItem() == Items.BROWN_MUSHROOM)
                    renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_1.get().defaultBlockState());
                if(tileEntityIn.getItems().get(2).getItem() == Items.RED_MUSHROOM)
                    renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_1.get().defaultBlockState());
                matrixStackIn.popPose();
                if(tileEntityIn.getItems().get(2).getCount() >= 2)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.75f, 0.22f, 0.525f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.translate(0f, -0.03f, 0f);
                    if(tileEntityIn.getItems().get(2).getItem() == Items.BROWN_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_2.get().defaultBlockState());
                    if(tileEntityIn.getItems().get(2).getItem() == Items.RED_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_2.get().defaultBlockState());
                    matrixStackIn.popPose();
                }
                if(tileEntityIn.getItems().get(2).getCount() >= 3)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.75f, 0.22f, 0.525f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.translate(0f, -0.15f, 0f);
                    if(tileEntityIn.getItems().get(2).getItem() == Items.BROWN_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_BROWN_MUSHROOM_1.get().defaultBlockState());
                    if(tileEntityIn.getItems().get(2).getItem() == Items.RED_MUSHROOM)
                        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.HERB_DRYING_RACK_RED_MUSHROOM_1.get().defaultBlockState());
                    matrixStackIn.popPose();
                }
            }
            else
            {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                matrixStackIn.translate(0.75f, 0.22f, 0.525f);
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                renderItem(tileEntityIn.getItems().get(2), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.popPose();
                if(tileEntityIn.getItems().get(2).getCount() >= 2)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.75f, 0.22f, 0.525f);
                    matrixStackIn.translate(0.075f, 0.05f, -0.025f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                    matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                    renderItem(tileEntityIn.getItems().get(2), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                    matrixStackIn.popPose();
                }
                if(tileEntityIn.getItems().get(2).getCount() >= 3)
                {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5f, 0.0f, 0.5f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotation));
                    matrixStackIn.translate(-0.5f, 0.0f, -0.5f);
                    matrixStackIn.translate(0.75f, 0.22f, 0.525f);
                    matrixStackIn.translate(-0.075f, 0.025f, -0.025f);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(15));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180));
                    matrixStackIn.scale(0.45f, 0.45f, 0.45f);
                    renderItem(tileEntityIn.getItems().get(2), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                    matrixStackIn.popPose();
                }
            }

        }

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
