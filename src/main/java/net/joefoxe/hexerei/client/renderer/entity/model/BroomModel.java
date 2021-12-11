package net.joefoxe.hexerei.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)

public class BroomModel extends ListModel<BroomEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Hexerei.MOD_ID, "broom"), "main");
    private final ModelPart brush;
    private final ModelPart bb_main;


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition brush = partdefinition.addOrReplaceChild("brush", CubeListBuilder.create(), PartPose.offset(21.5814F, 20.9482F, 0.0107F));

        PartDefinition cube_r1 = brush.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(5, 33).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.0F, 0.0F, 2.6458F, 0.0039F, 0.444F));

        PartDefinition cube_r2 = brush.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(5, 32).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.005F, 1.1159F, -1.0549F, 0.5932F, -0.444F, 0.0043F));

        PartDefinition cube_r3 = brush.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(33, 17).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.3001F, -0.7912F, 1.8575F, -0.3791F, 0.237F));

        PartDefinition cube_r4 = brush.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 15).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 2.1035F, -0.0214F, -2.1539F, -0.0039F, -0.444F));

        PartDefinition cube_r5 = brush.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(5, 34).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 1.8034F, 0.7698F, -0.3242F, 0.3791F, -0.237F));

        PartDefinition cube_r6 = brush.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(33, 16).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.4136F, 0.8651F, -2.6721F, 0.2828F, 0.347F));

        PartDefinition cube_r7 = brush.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 13).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0039F, 1.0626F, 1.0352F, 0.8986F, 0.444F, -0.0043F));

        PartDefinition cube_r8 = brush.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(13, 33).addBox(-1.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 1.6899F, -0.8865F, 3.0439F, -0.2828F, -0.347F));

        PartDefinition cube_r9 = brush.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 4).addBox(-2.5F, -0.5F, 0.1F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3145F, 1.5888F, 0.1789F, 0.752F, 0.081F, -0.152F));

        PartDefinition cube_r10 = brush.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(16, 14).addBox(-1.4064F, -2.7995F, 0.031F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.0518F, -0.0107F, 0.0278F, -0.0039F, -0.444F));

        PartDefinition cube_r11 = brush.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(26, 4).addBox(-1.4064F, 1.7995F, -0.031F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.0518F, -0.0107F, 0.0278F, 0.0039F, 0.444F));

        PartDefinition cube_r12 = brush.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(26, 14).addBox(-2.5F, -0.5F, -0.25F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9961F, 0.0228F, 2.3287F, -1.0284F, -0.3791F, -0.237F));

        PartDefinition cube_r13 = brush.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(30, 8).addBox(-2.5F, -0.5F, 0.25F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9961F, 2.0808F, -2.3501F, -1.0284F, 0.3791F, 0.237F));

        PartDefinition cube_r14 = brush.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(32, 5).addBox(-2.5F, -0.5F, -0.25F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9961F, 2.0808F, 2.3287F, 1.0284F, -0.3791F, 0.237F));

        PartDefinition cube_r15 = brush.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(32, 6).addBox(-2.5F, -0.5F, 0.25F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9961F, 0.0228F, -2.3501F, 1.0284F, 0.3791F, -0.237F));

        PartDefinition cube_r16 = brush.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 4).addBox(-3.2256F, 0.7377F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4689F, 1.0518F, -0.0107F, -1.5708F, 0.2182F, 0.0F));

        PartDefinition cube_r17 = brush.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 7).addBox(-3.2256F, -1.7377F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4689F, 1.0518F, -0.0107F, -1.5708F, -0.2182F, 0.0F));

        PartDefinition cube_r18 = brush.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 10).addBox(-3.5F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4689F, 2.3195F, -0.0107F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r19 = brush.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 13).addBox(-3.6835F, -2.4408F, -1.0187F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -0.5016F, -0.1555F, -0.2628F));

        PartDefinition cube_r20 = brush.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(14, 24).addBox(-2.3184F, -0.3303F, -0.1273F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, 0.0674F, -0.1556F, -0.132F));

        PartDefinition cube_r21 = brush.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 26).addBox(-2.342F, -1.0624F, -0.1338F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -2.2449F, 0.0708F, 0.1557F));

        PartDefinition cube_r22 = brush.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(14, 27).addBox(-2.3472F, -0.9002F, -1.0518F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -1.2424F, -0.0249F, -0.1232F));

        PartDefinition cube_r23 = brush.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(28, 24).addBox(-2.2376F, -1.0799F, 0.2809F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -0.6624F, -0.2947F, 0.1951F));

        PartDefinition cube_r24 = brush.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(28, 27).addBox(-2.2195F, -1.0525F, -1.3331F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -1.543F, -0.0194F, -0.3413F));

        PartDefinition cube_r25 = brush.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 29).addBox(-2.2422F, -0.9445F, 0.2697F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -1.5463F, 0.0102F, 0.3493F));

        PartDefinition cube_r26 = brush.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(14, 30).addBox(-2.2241F, -0.9719F, -1.3219F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -0.6605F, 0.2851F, -0.1865F));

        PartDefinition cube_r27 = brush.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(16, 8).addBox(-2.2573F, -1.476F, -1.0308F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -1.07F, -0.2748F, -0.1409F));

        PartDefinition cube_r28 = brush.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(16, 11).addBox(-2.2654F, 0.45F, -1.0308F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -1.0714F, 0.2651F, 0.1485F));

        PartDefinition cube_r29 = brush.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 16).addBox(-3.7003F, 1.3873F, -1.0187F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -0.5042F, 0.1461F, 0.2704F));

        PartDefinition cube_r30 = brush.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(16, 5).addBox(-3.5F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4689F, -0.216F, -0.0107F, 0.0F, 0.0F, -0.2182F));

        PartDefinition cube_r31 = brush.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(17, 15).addBox(-3.6415F, -0.9453F, -2.3127F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -0.0662F, 0.3407F, 0.0044F));

        PartDefinition cube_r32 = brush.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(17, 18).addBox(-3.6524F, -0.9286F, 1.2811F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -1.0074F, -0.1748F, 0.3065F));

        PartDefinition cube_r33 = brush.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 19).addBox(-3.632F, -1.1049F, -2.3358F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -1.0045F, 0.1655F, -0.298F));

        PartDefinition cube_r34 = brush.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(15, 21).addBox(-3.6429F, -1.1216F, 1.3041F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7103F, 1.0798F, 0.008F, -0.0662F, -0.3505F, 0.0037F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -3.0F, -1.0F, 25.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(11.5F, -3.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(31, 18).addBox(10.0F, -3.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F))
                .texOffs(28, 30).addBox(-21.45F, -3.01F, -1.4F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r35 = bb_main.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(30, 9).addBox(-2.35F, -0.99F, -2.725F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.048F, -2.01F, 0.2544F, 0.0F, 0.4363F, 0.0F));

        PartDefinition cube_r36 = bb_main.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 22).addBox(-3.35F, -1.0F, -1.275F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.198F, -2.01F, -0.3544F, 0.0F, -0.4363F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public BroomModel(ModelPart root) {
        this.brush = root.getChild("brush");
        this.bb_main = root.getChild("bb_main");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        brush.render(poseStack, buffer, packedLight, packedOverlay);
        bb_main.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(brush,bb_main);
    }

    @Override
    public void setupAnim(BroomEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }
}