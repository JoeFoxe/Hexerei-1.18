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

public class BroomStickBaseModel extends ListModel<BroomEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Hexerei.MOD_ID, "broom_stick_base"), "main");
    private final ModelPart broom_stick;


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Broom = partdefinition.addOrReplaceChild("Broom", CubeListBuilder.create().texOffs(0, 0).addBox(-11.2131F, -3.9639F, -0.8505F, 25.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-21.6631F, -3.9739F, -1.2505F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.2131F, 24.9639F, -0.1495F));

        PartDefinition stick_r1 = Broom.addOrReplaceChild("stick_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-15.548F, -4.01F, -1.6294F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5056F, 0.0361F, 5.2711F, 0.0F, -0.4363F, 0.0F));

        PartDefinition stick_r2 = Broom.addOrReplaceChild("stick_r2", CubeListBuilder.create().texOffs(30, 9).addBox(-17.398F, -4.0F, -2.4706F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7303F, 0.0361F, -6.1858F, 0.0F, 0.4363F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public BroomStickBaseModel(ModelPart root) {
        this.broom_stick = root.getChild("Broom");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        broom_stick.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(broom_stick);
    }

    @Override
    public void setupAnim(BroomEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }
}