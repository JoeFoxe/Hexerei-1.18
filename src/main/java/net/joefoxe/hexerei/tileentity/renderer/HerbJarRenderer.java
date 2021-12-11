package net.joefoxe.hexerei.tileentity.renderer;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.HerbJar;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.HerbJarTile;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.util.FormattedCharSequence;
import com.mojang.math.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.List;

public class HerbJarRenderer implements BlockEntityRenderer<HerbJarTile> {

    private final Font font;

    public HerbJarRenderer() {
        super();
        this.font = Minecraft.getInstance().font;
    }

    @Override
    public void render(HerbJarTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).hasBlockEntity()){
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH)
                renderItemsNorth(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST)
                renderItemsWest(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH)
                renderItemsSouth(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST)
                renderItemsEast(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
        }
        else
            return;

        if(!tileEntityIn.itemHandler.isEmpty())
        {
            BlockState state = ModBlocks.HERB_JAR_GENERIC.get().defaultBlockState();
            if(tileEntityIn.itemHandler.getContents().get(0).getItem() == ModBlocks.BELLADONNA_FLOWER.get().asItem())
                state = ModBlocks.HERB_JAR_BELLADONNA.get().defaultBlockState();
            if(tileEntityIn.itemHandler.getContents().get(0).getItem() == ModBlocks.MUGWORT_BUSH.get().asItem())
                state = ModBlocks.HERB_JAR_MUGWORT.get().defaultBlockState();
            if(tileEntityIn.itemHandler.getContents().get(0).getItem() == ModBlocks.MANDRAKE_FLOWER.get().asItem())
                state = ModBlocks.HERB_JAR_MANDRAKE_FLOWER.get().defaultBlockState();
            if(tileEntityIn.itemHandler.getContents().get(0).getItem() == ModItems.MANDRAKE_ROOT.get())
                state = ModBlocks.HERB_JAR_MANDRAKE_ROOT.get().defaultBlockState();
            if(tileEntityIn.itemHandler.getContents().get(0).getItem() == ModBlocks.YELLOW_DOCK_BUSH.get().asItem())
                state = ModBlocks.HERB_JAR_YELLOW_DOCK.get().defaultBlockState();

            for(int a = 0; a < ((float)tileEntityIn.itemHandler.getContents().get(0).getCount() / 1024f) * 10f; a++){
                matrixStackIn.pushPose();

                if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH) {
                matrixStackIn.translate(8D / 16D, 1D / 16D * a, 8D / 16D);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90 * a));
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180 ));
                } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
                    matrixStackIn.translate(8D / 16D, 1D / 16D * a, 8D / 16D);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90 * a));
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(0));
                } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
                    matrixStackIn.translate(8D / 16D, 1D / 16D * a, 8D / 16D);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90 * a));
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90));
                } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                    matrixStackIn.translate(8D / 16D, 1D / 16D * a, 8D / 16D);
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90 * a));
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270));
                }
                renderBlock(matrixStackIn, bufferIn, combinedLightIn, state);
                matrixStackIn.popPose();
            }
        }

        float f2 = 0.010416667F;
        int i = 0x464F56;
        double d0 = 0.4D;
        int j = (int)((double) NativeImage.getR(i) * 0.4D);
        int k = (int)((double)NativeImage.getG(i) * 0.4D);
        int l = (int)((double)NativeImage.getB(i) * 0.4D);
        int i1 = NativeImage.combine(   0, l, k, j);
        int j1 = 20;

        if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH) {
            matrixStackIn.translate(8D / 16D, 8D / 16D, 12.05D / 16D);
        } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH) {
            matrixStackIn.translate(8D / 16D, 8D / 16D, 1 - 12.05D / 16D);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180));
        } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST) {
            matrixStackIn.translate(1 - 12.05D / 16D, 8D / 16D, 8D / 16);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270));
        } else if (tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
            matrixStackIn.translate(12.05D / 16D, 8D / 16D, 8D / 16);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90));
        }

        matrixStackIn.scale(0.010416667F / 1.5f, -0.010416667F / 1.5f, 0.010416667F / 1.5f);
        FormattedCharSequence ireorderingprocessor = tileEntityIn.reorderText(0, (p_243502_1_) -> {
            List<FormattedCharSequence> list = font.split(p_243502_1_, 90);
            return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
        });
        if (ireorderingprocessor != null) {
            float f3 = (float)(-font.width(ireorderingprocessor) / 2);
            font.drawInBatch(ireorderingprocessor, f3, 0, i1, false, matrixStackIn.last().pose(), bufferIn, false, 0, combinedLightIn);
        }



//
//        }


    }

    private void renderItemsNorth(HerbJarTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                                  MultiBufferSource bufferIn, int combinedLightIn)
    {
        matrixStackIn.pushPose();
        matrixStackIn.translate(8D/16D, 5D/16D, 12D/16D);
        matrixStackIn.scale(0.30f, 0.30f, 0.30f);

        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180));
        renderItem(new ItemStack(tileEntityIn.getItemInSlot(0)), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
        matrixStackIn.popPose();

    }

    private void renderItemsSouth(HerbJarTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                                 MultiBufferSource bufferIn, int combinedLightIn)
    {

        matrixStackIn.pushPose();
        matrixStackIn.translate(8D/16D, 5D/16D, 4D/16D);
        matrixStackIn.scale(0.30f, 0.30f, 0.30f);

        renderItem(new ItemStack(tileEntityIn.getItemInSlot(0)), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
        matrixStackIn.popPose();


    }

    private void renderItemsWest(HerbJarTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                                 MultiBufferSource bufferIn, int combinedLightIn)
    {



        matrixStackIn.pushPose();
        matrixStackIn.translate(1, 0, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270));
        matrixStackIn.translate(8D/16D, 5D/16D, 4D/16D);
        matrixStackIn.scale(0.30f, 0.30f, 0.30f);

//        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180));
            renderItem(new ItemStack(tileEntityIn.getItemInSlot(0)), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
        matrixStackIn.popPose();


    }

    private void renderItemsEast(HerbJarTile tileEntityIn, float partialTicks, PoseStack matrixStackIn,
                                 MultiBufferSource bufferIn, int combinedLightIn)
    {

        matrixStackIn.pushPose();
        matrixStackIn.translate(1, 0, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270));
        matrixStackIn.translate(8D/16D, 5D/16D, 12D/16D);
        matrixStackIn.scale(0.30f, 0.30f, 0.30f);

        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180));
        renderItem(new ItemStack(tileEntityIn.getItemInSlot(0)), partialTicks, matrixStackIn, bufferIn, combinedLightIn);
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
