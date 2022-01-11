package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.SageBurningPlate;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.SageBurningPlateTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;

public class SageBurningPlateRenderer implements BlockEntityRenderer<SageBurningPlateTile> {

    @Override
    public void render(SageBurningPlateTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(!tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).hasBlockEntity()){
            return;
        }

        if(tileEntityIn.getItems().get(0).is(ModItems.DRIED_SAGE_BUNDLE.get())){
            matrixStackIn.pushPose();
            int rotationOffset = 0;
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH)
                rotationOffset = 0;
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                rotationOffset = 90;
                matrixStackIn.translate(0D, 0D, 1D);
            }
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
                rotationOffset = 180;
                matrixStackIn.translate(1D, 0D, 1D);
            }
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
                rotationOffset = 270;
                matrixStackIn.translate(1D, 0D, 0D);
            }

            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(rotationOffset));
            float damageOutOf5 = (tileEntityIn.getItems().get(0).getMaxDamage()-tileEntityIn.getItems().get(0).getDamageValue())/(float)tileEntityIn.getItems().get(0).getMaxDamage()*5f;
            if(damageOutOf5 <= 5f && damageOutOf5 > 4f)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(SageBurningPlate.LIT)) ? ModBlocks.DRIED_SAGE_BUNDLE_PLATE_5_LIT.get().defaultBlockState() : ModBlocks.DRIED_SAGE_BUNDLE_PLATE_5.get().defaultBlockState());
            if(damageOutOf5 <= 4f && damageOutOf5 > 3f)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(SageBurningPlate.LIT)) ? ModBlocks.DRIED_SAGE_BUNDLE_PLATE_4_LIT.get().defaultBlockState() : ModBlocks.DRIED_SAGE_BUNDLE_PLATE_4.get().defaultBlockState());
            if(damageOutOf5 <= 3f && damageOutOf5 > 2f)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(SageBurningPlate.LIT)) ? ModBlocks.DRIED_SAGE_BUNDLE_PLATE_3_LIT.get().defaultBlockState() : ModBlocks.DRIED_SAGE_BUNDLE_PLATE_3.get().defaultBlockState());
            if(damageOutOf5 <= 2f && damageOutOf5 > 1f)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(SageBurningPlate.LIT)) ? ModBlocks.DRIED_SAGE_BUNDLE_PLATE_2_LIT.get().defaultBlockState() : ModBlocks.DRIED_SAGE_BUNDLE_PLATE_2.get().defaultBlockState());
            if(damageOutOf5 <= 1f && damageOutOf5 > 0f)
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(SageBurningPlate.LIT)) ? ModBlocks.DRIED_SAGE_BUNDLE_PLATE_1_LIT.get().defaultBlockState() : ModBlocks.DRIED_SAGE_BUNDLE_PLATE_1.get().defaultBlockState());
            matrixStackIn.popPose();
        }
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
