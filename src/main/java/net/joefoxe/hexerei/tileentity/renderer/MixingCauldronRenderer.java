package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.MixingCauldron;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.MixingCauldronTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Objects;

public class MixingCauldronRenderer implements BlockEntityRenderer<MixingCauldronTile> {

    @Override
    public void render(MixingCauldronTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {


            // Rendering for the items inside the cauldron
            float craftPercent = 0;
            //Mixing the items
            if(Objects.requireNonNull(tileEntityIn.getLevel()).getBlockState(tileEntityIn.getPos()).hasBlockEntity()) {
                craftPercent = tileEntityIn.getLevel().getBlockState(tileEntityIn.getPos()).getValue(MixingCauldron.CRAFT_DELAY) / (float) MixingCauldronTile.craftDelayMax;
            }
            else return;

            for(int i = 0; i < 8; i++)
            {
                ItemStack item = new ItemStack(tileEntityIn.getItemInSlot(i));
                if (!item.isEmpty()) {
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(0.5D, 1.5D, 0.5D);
                    float currentTime = tileEntityIn.getLevel().getGameTime() + partialTicks;

                    //rotation offset when crafting
                    double itemRotationOffset = 0.8 * i + (craftPercent * (20f * craftPercent));
                    if(tileEntityIn.getLevel().getBlockState(tileEntityIn.getPos()).getValue(MixingCauldron.LEVEL) > 0) {
                        matrixStackIn.translate(
                                0D + Math.sin(itemRotationOffset) / (3.5f + ((craftPercent * craftPercent) * 10.0f)),
                                -1.15D + (0.20 * (tileEntityIn.getLevel().getBlockState(tileEntityIn.getPos()).getValue(MixingCauldron.LEVEL) + (Math.sin(Math.PI * (currentTime) / 30 + (i * 20)) / 10))),
                                0D + Math.cos(itemRotationOffset)  / (3.5f + ((craftPercent * craftPercent) * 10.0f)));
                        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees((float)((45 * i) -1f + (2 * Math.sin((tileEntityIn.degrees + i * 20) / 40)))));
                        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees((float)(82.5f + (5 * Math.cos((tileEntityIn.degrees + i * 22) / 40)))));
                        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees((float)(-2.5f + (5 * Math.cos((tileEntityIn.degrees + i * 24) / 40))) ));
                        matrixStackIn.scale(1 - (craftPercent * 0.5f), 1 - (craftPercent * 0.5f), 1 - (craftPercent * 0.5f));
                    } else {
                        matrixStackIn.translate(0D + Math.sin(itemRotationOffset) / 3.5,-1.15D,0D + Math.cos(itemRotationOffset) / 3.5);
                        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(45 * i));
                        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(85f ) );
                        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-2.5f ));
                    }

                    matrixStackIn.scale(0.4f, 0.4f, 0.4f);
                    renderItem(item, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                    matrixStackIn.popPose();
                }


            }
            // output item
            ItemStack item2 = new ItemStack(tileEntityIn.getItemInSlot(8));
            if (!item2.isEmpty()) {

                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5D, 1.5D, 0.5D);
                float currentTime = tileEntityIn.getLevel().getGameTime() + partialTicks;

                if(tileEntityIn.getLevel().getBlockState(tileEntityIn.getPos()).getValue(MixingCauldron.LEVEL) > 0) {
                    matrixStackIn.translate(
                            0D ,
                            -1.15D + (0.20 * (tileEntityIn.getLevel().getBlockState(tileEntityIn.getPos()).getValue(MixingCauldron.LEVEL) + (Math.sin(Math.PI * (currentTime) / 30 + 20) / 10))),
                            0D );
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees((float)((45) -1f + (2 * Math.sin((tileEntityIn.degrees + 20) / 40))) - ((craftPercent * craftPercent) * 720f)));
                    matrixStackIn.mulPose(Vector3f.XP.rotationDegrees((float)(82.5f + (5 * Math.cos((tileEntityIn.degrees + 22) / 40)))));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees((float)(-2.5f + (5 * Math.cos((tileEntityIn.degrees + 24) / 40)))));
                } else {
                    matrixStackIn.translate(0D,-1.15D,0D);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(45 - ((craftPercent * craftPercent) * 720f)));
                    matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(85f));
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-2.5f));
                }

                matrixStackIn.scale(0.4f, 0.4f, 0.4f);
                renderItem(item2, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.popPose();


            }

            //gives the wobble
            ((MixingCauldronTile)tileEntityIn).degrees++;
            if (tileEntityIn.getItemInSlot(9) == ModItems.BLOOD_SIGIL.get()) {
            if(tileEntityIn.getItemInSlot(9).asItem() == ModItems.BLOOD_SIGIL.get())
                {
                    matrixStackIn.pushPose();
                    renderBlock(matrixStackIn, bufferIn, combinedLightIn, ModBlocks.BLOOD_SIGIL.get().defaultBlockState());
                    matrixStackIn.popPose();
                }
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
