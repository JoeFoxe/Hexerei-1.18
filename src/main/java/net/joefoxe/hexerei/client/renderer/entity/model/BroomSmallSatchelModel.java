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

public class BroomSmallSatchelModel extends ListModel<BroomEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Hexerei.MOD_ID, "broom_small_satchel"), "main");
    private final ModelPart satchel;


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Satchel = partdefinition.addOrReplaceChild("Satchel", CubeListBuilder.create().texOffs(0, 14).addBox(4.5F, -3.5F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(1, 2).addBox(3.0F, -2.0F, -3.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 27).addBox(3.0F, -2.25F, -3.25F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 19).addBox(3.0F, -2.25F, 1.25F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(1, 7).addBox(3.0F, -2.0F, 1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public BroomSmallSatchelModel(ModelPart root) {
        this.satchel = root.getChild("Satchel");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        satchel.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(satchel);
    }

    @Override
    public void setupAnim(BroomEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }
}