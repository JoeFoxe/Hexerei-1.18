package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.PestleAndMortar;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.PestleAndMortarTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;
import java.util.Objects;

public class PestleAndMortarRenderer implements BlockEntityRenderer<PestleAndMortarTile> {

    @Override
    public void render(PestleAndMortarTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(!tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).hasBlockEntity()){
            return;
        }

        // Rendering for the items inside the cauldron
        float craftPercent = (tileEntityIn.grindingTimeMax - tileEntityIn.grindingTime) / (float) tileEntityIn.grindingTimeMax;
        float craftPercent2 = (tileEntityIn.grindingTimeMax - tileEntityIn.grindingTime) / 100f;

        float height = 6f/16f;

        for(int i = 0; i < 5; i++)
        {
            ItemStack item = tileEntityIn.getItemStackInSlot(i);
            if (!item.isEmpty()) {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5D, height + 1f / 256f, 0.5D);
                float currentTime = tileEntityIn.getLevel().getGameTime() + partialTicks;

                //rotation offset when crafting
                double itemRotationOffset = 2.512 * i + tileEntityIn.grindingTime/6f - (Math.pow(Mth.sin( craftPercent2 * 3.14f * 5 - 3.14f), 2));
                matrixStackIn.translate(
                        0D + Math.sin(itemRotationOffset) / (6.5f + ((craftPercent2 * craftPercent2) * 10.0f)),
                        -2/15f * craftPercent,
                        0D + Math.cos(itemRotationOffset) / (6.5f + ((craftPercent2 * craftPercent2) * 10.0f)));
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees((float)itemRotationOffset * 58f - 8));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(55f - 40f * craftPercent));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-2.5f));

                matrixStackIn.scale(0.4f, 0.4f, 0.4f);
                renderItem(item, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.popPose();
            }


        }
        // output item
        ItemStack item2 = tileEntityIn.getItemStackInSlot(5);
        if (!item2.isEmpty()) {

            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5D, height + 1f / 256f - 2/16f, 0.5D);
            float currentTime = tileEntityIn.getLevel().getGameTime() + partialTicks;

            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(45 - ((craftPercent * craftPercent) * 720f)));
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(75f));
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-2.5f));

            matrixStackIn.scale(0.4f, 0.4f, 0.4f);
            if(item2.getCount() >= 8) {
                matrixStackIn.translate(2.25f/16f, -2/16f, -1.25f/16f);
                renderItem(item2, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.translate(-3.25f/16f, 1/16f, 2.5f/16f);

                matrixStackIn.translate(-2.25f/16f, 0, -1.25f/16f);
                renderItem(item2, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.translate(3.25f/16f, 2/16f, 1.25f/16f);
            }else
            if(item2.getCount() >= 2) {
                matrixStackIn.translate(2.25f/16f, 0, -1.25f/16f);
                renderItem(item2, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.translate(-3.25f/16f, 2/16f, 1.25f/16f);
            }
            renderItem(item2, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            matrixStackIn.popPose();


        }

        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5D, 4.5/16f, 0.5D);
        int rotationOffset = 0;
        if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH)
            rotationOffset = 0;
        if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST)
            rotationOffset = 90;
        if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH)
            rotationOffset = 180;
        if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST)
            rotationOffset = 270;
        double itemRotationOffset = 2.512 + tileEntityIn.grindingTime/6f - (Math.pow(Mth.sin( craftPercent2 * 3.14f * 5 - 3.14f), 2));


//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(45 - ((craftPercent * craftPercent) * 720f)));
//        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(85f));
//        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-2.5f));
        double pestleYOffset = (Math.pow(Mth.sin( craftPercent2 * 3.14f * 5 - 1.2f), 10))/4f;
        double pestleTwistOffset = (Math.pow(Mth.sin( craftPercent2 * 3.14f * 5 - 3.14f), 10))/4f;
        double pestleTwistOffset2 = (Math.pow(Mth.sin( craftPercent2 * 3.14f * 5 - 3.14f), 10))/4f - (Math.pow(Mth.cos( craftPercent2 * 3.14f * 5 - 3.14f), 10))/4f;
        if(!tileEntityIn.crafting) {
            pestleYOffset = 0;
            pestleTwistOffset = 0;
        }
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotationOffset + (float)itemRotationOffset/6.28f*360 + 65));
        matrixStackIn.translate(0.05D, pestleYOffset, 0D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(- (float)itemRotationOffset/6.28f*360 - 90 + ((float)pestleTwistOffset * 150) - 30));
        if(!tileEntityIn.crafting)
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-20));
        else {
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-40 * (float) pestleTwistOffset2));
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(40 * (float) pestleTwistOffset));
        }

//        matrixStackIn.scale(0.4f, 0.4f, 0.4f);
        renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.PESTLE_AND_MORTAR_PESTLE.get().defaultBlockState());
        matrixStackIn.popPose();
    }

    // THIS IS WHAT I WAS LOOKING FOR FOREVER AHHHHH
    private void renderItem(ItemStack stack, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn,
                            int combinedLightIn) {
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn,
                OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, 1);
    }

    private void renderBlock(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, BlockState state) {
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, matrixStackIn, bufferIn, combinedLightIn, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);

    }


}
