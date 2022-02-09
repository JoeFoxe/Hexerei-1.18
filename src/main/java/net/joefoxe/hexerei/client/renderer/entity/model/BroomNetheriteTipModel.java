package net.joefoxe.hexerei.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)

public class BroomNetheriteTipModel extends ListModel<BroomEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Hexerei.MOD_ID, "broom_netherite_tip"), "main");
    private final ModelPart NetheriteTip;

    public BroomNetheriteTipModel(ModelPart root) {
        this.NetheriteTip = root.getChild("NetheriteTip");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition NetheriteTip = partdefinition.addOrReplaceChild("NetheriteTip", CubeListBuilder.create().texOffs(8, 0).addBox(0.1869F, -1.0239F, 0.2495F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(0, 0).addBox(0.75F, -1.5F, -0.25F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-21.6369F, 22.0139F, -1.6995F));

        PartDefinition ring_r1 = NetheriteTip.addOrReplaceChild("ring_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.125F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 1.25F, 0.0F, 0.4363F, 0.0F));

        PartDefinition cube_r1 = NetheriteTip.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 6).addBox(-16.323F, -4.0F, -2.4456F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(20.1197F, 2.9861F, -4.6358F, 0.0F, 0.4363F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        NetheriteTip.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(NetheriteTip);
    }

    @Override
    public void setupAnim(BroomEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }
}