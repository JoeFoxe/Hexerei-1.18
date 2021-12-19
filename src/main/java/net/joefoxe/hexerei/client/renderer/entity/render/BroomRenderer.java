package net.joefoxe.hexerei.client.renderer.entity.render;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.joefoxe.hexerei.client.renderer.entity.model.BroomModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)


public class BroomRenderer extends EntityRenderer<BroomEntity>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Hexerei.MOD_ID, "textures/entity/broom.png");
    private final Pair<ResourceLocation, BroomModel> broomResources;

    public BroomRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
        this.broomResources = Pair.of(TEXTURE, new BroomModel(context.bakeLayer(BroomModel.LAYER_LOCATION)));
    }

    @Override
    public ResourceLocation getTextureLocation(BroomEntity p_114482_) {
        return TEXTURE;
    }

    public void render(BroomEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.375D + entityIn.floatingOffset, 0.0D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw - (entityIn.deltaRotation * 2)));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees((float)entityIn.getDeltaMovement().y() * 20f));
        float f = (float)entityIn.getTimeSinceHit() - partialTicks;
        float f1 = entityIn.getDamageTaken() - partialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection()));
        }

        float f2 = entityIn.getRockingAngle(partialTicks);
        if (!Mth.equal(f2, 0.0F)) {
            matrixStackIn.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entityIn.getRockingAngle(partialTicks), true));
        }

        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        matrixStackIn.translate(0, -1.6, 0);
        BroomModel broomModel = broomResources.getSecond();
        broomModel.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(broomModel.renderType(TEXTURE));
        broomModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!entityIn.canSwim()) {
            VertexConsumer ivertexbuilder1 = bufferIn.getBuffer(RenderType.waterMask());
        }

        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


}

//
//public class BroomRenderer extends EntityRenderer<BroomEntity> {
//    private static final ResourceLocation[] BROOM_TEXTURES = new ResourceLocation[]{new ResourceLocation(Hexerei.MOD_ID,"textures/entity/broom.png")};
//    protected final BroomModel modelBroom = new BroomModel();
//
//    public BroomRenderer(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn);
//        this.shadowSize = 0.8F;
//    }
//


//    /**
//     * Returns the location of an entity's texture.
//     */
//    public ResourceLocation getEntityTexture(BroomEntity entity) {
//        return BROOM_TEXTURES[0];
//    }
//}
