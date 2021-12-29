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

public class BroomBrushBaseModel extends ListModel<BroomEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Hexerei.MOD_ID, "broom_brush_base"), "main");
    private final ModelPart broom_brush;


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Broom = partdefinition.addOrReplaceChild("Broom", CubeListBuilder.create().texOffs(0, 32).addBox(11.2869F, -4.4639F, -1.3505F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
                .texOffs(31, 18).addBox(9.7869F, -4.4639F, -1.3505F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.2131F, 24.9639F, -0.1495F));

        PartDefinition brush_r1 = Broom.addOrReplaceChild("brush_r1", CubeListBuilder.create().texOffs(15, 21).addBox(13.1438F, -3.6703F, -4.8474F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0662F, -0.3505F, 0.0037F));

        PartDefinition brush_r2 = Broom.addOrReplaceChild("brush_r2", CubeListBuilder.create().texOffs(0, 19).addBox(7.373F, -12.4159F, 20.514F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1366F, -11.2743F, -19.5359F, -1.0045F, 0.1655F, -0.298F));

        PartDefinition brush_r3 = Broom.addOrReplaceChild("brush_r3", CubeListBuilder.create().texOffs(17, 18).addBox(19.0002F, -13.234F, 9.3753F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9176F, -9.1008F, -18.2839F, -1.0074F, -0.1748F, 0.3065F));

        PartDefinition brush_r4 = Broom.addOrReplaceChild("brush_r4", CubeListBuilder.create().texOffs(16, 11).addBox(13.2308F, -4.4877F, 6.293F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0364F, -0.0017F, -2.5188F, -0.0662F, 0.3407F, 0.0044F));

        PartDefinition brush_r5 = Broom.addOrReplaceChild("brush_r5", CubeListBuilder.create().texOffs(16, 5).addBox(5.9359F, -0.4819F, -1.0107F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8519F, -2.1549F, 0.1495F, 0.0F, 0.0F, -0.2182F));

        PartDefinition brush_r6 = Broom.addOrReplaceChild("brush_r6", CubeListBuilder.create().texOffs(0, 16).addBox(18.6112F, -11.5946F, 8.8462F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2999F, -2.9994F, -11.3445F, -0.5042F, 0.1461F, 0.2704F));

        PartDefinition brush_r7 = Broom.addOrReplaceChild("brush_r7", CubeListBuilder.create().texOffs(16, 11).addBox(17.6392F, -20.167F, 16.5867F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2199F, -12.2859F, -20.2364F, -1.0714F, 0.2651F, 0.1485F));

        PartDefinition brush_r8 = Broom.addOrReplaceChild("brush_r8", CubeListBuilder.create().texOffs(16, 8).addBox(12.0556F, -8.965F, 16.587F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5594F, -13.2634F, -18.1906F, -1.07F, -0.2748F, -0.1409F));

        PartDefinition brush_r9 = Broom.addOrReplaceChild("brush_r9", CubeListBuilder.create().texOffs(14, 30).addBox(11.017F, -8.2356F, 17.1411F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9576F, -5.0296F, -14.3863F, -0.6605F, 0.2851F, -0.1865F));

        PartDefinition brush_r10 = Broom.addOrReplaceChild("brush_r10", CubeListBuilder.create().texOffs(0, 29).addBox(21.3275F, -23.8649F, 11.6296F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8464F, -21.1744F, -22.7926F, -1.5463F, 0.0102F, 0.3493F));

        PartDefinition brush_r11 = Broom.addOrReplaceChild("brush_r11", CubeListBuilder.create().texOffs(28, 27).addBox(7.8688F, -22.979F, 24.7838F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2016F, -23.6818F, -22.6778F, -1.543F, -0.0194F, -0.3413F));

        PartDefinition brush_r12 = Broom.addOrReplaceChild("brush_r12", CubeListBuilder.create().texOffs(28, 24).addBox(18.1797F, -7.3496F, 3.9877F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0752F, -3.6765F, -12.2593F, -0.6624F, -0.2947F, 0.1951F));

        PartDefinition brush_r13 = Broom.addOrReplaceChild("brush_r13", CubeListBuilder.create().texOffs(14, 27).addBox(13.0639F, -16.106F, 20.1203F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2012F, -16.0775F, -21.4413F, -1.2424F, -0.0249F, -0.1232F));

        PartDefinition brush_r14 = Broom.addOrReplaceChild("brush_r14", CubeListBuilder.create().texOffs(0, 26).addBox(18.4744F, -35.5692F, 11.5897F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7013F, -36.6464F, -17.9619F, -2.2449F, 0.0708F, 0.1557F));

        PartDefinition brush_r15 = Broom.addOrReplaceChild("brush_r15", CubeListBuilder.create().texOffs(14, 24).addBox(12.6711F, -0.8109F, -4.5741F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4841F, -0.6655F, 2.2496F, 0.0674F, -0.1556F, -0.132F));

        PartDefinition brush_r16 = Broom.addOrReplaceChild("brush_r16", CubeListBuilder.create().texOffs(0, 13).addBox(8.223F, -2.2953F, 8.846F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.4854F, -4.877F, -10.1625F, -0.5016F, -0.1555F, -0.2628F));

        PartDefinition brush_r17 = Broom.addOrReplaceChild("brush_r17", CubeListBuilder.create().texOffs(0, 10).addBox(14.5948F, -7.363F, -1.0107F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0835F, 1.1388F, 0.1495F, 0.0F, 0.0F, 0.2182F));

        PartDefinition brush_r18 = Broom.addOrReplaceChild("brush_r18", CubeListBuilder.create().texOffs(0, 7).addBox(10.5398F, -19.9777F, 18.9893F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5774F, -22.9015F, -20.6484F, -1.5708F, -0.2182F, 0.0F));

        PartDefinition brush_r19 = Broom.addOrReplaceChild("brush_r19", CubeListBuilder.create().texOffs(0, 4).addBox(10.5398F, -26.9189F, 18.9893F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3579F, -22.9015F, -23.8821F, -1.5708F, 0.2182F, 0.0F));

        PartDefinition brush_r20 = Broom.addOrReplaceChild("brush_r20", CubeListBuilder.create().texOffs(32, 6).addBox(9.7824F, -6.5931F, -19.9827F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.7536F, -16.4102F, 16.8953F, 1.0284F, 0.3791F, -0.237F));

        PartDefinition brush_r21 = Broom.addOrReplaceChild("brush_r21", CubeListBuilder.create().texOffs(32, 5).addBox(18.5071F, -23.2097F, -15.1342F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.9495F, -9.8195F, 19.9106F, 1.0284F, -0.3791F, 0.237F));

        PartDefinition brush_r22 = Broom.addOrReplaceChild("brush_r22", CubeListBuilder.create().texOffs(30, 8).addBox(18.5071F, -23.2097F, 15.1128F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.9498F, -9.8007F, -19.6227F, -1.0284F, 0.3791F, 0.237F));

        PartDefinition brush_r23 = Broom.addOrReplaceChild("brush_r23", CubeListBuilder.create().texOffs(26, 14).addBox(9.7824F, -6.5931F, 19.9613F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.754F, -16.3914F, -16.6074F, -1.0284F, -0.3791F, -0.237F));

        PartDefinition brush_r24 = Broom.addOrReplaceChild("brush_r24", CubeListBuilder.create().texOffs(26, 4).addBox(23.8256F, -12.431F, -0.174F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.3553F, -0.9075F, 0.7757F, 0.0278F, 0.0039F, 0.444F));

        PartDefinition brush_r25 = Broom.addOrReplaceChild("brush_r25", CubeListBuilder.create().texOffs(16, 14).addBox(6.6435F, 1.6441F, -0.7845F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3473F, -3.4848F, 0.7991F, 0.0278F, -0.0039F, -0.444F));

        PartDefinition brush_r26 = Broom.addOrReplaceChild("brush_r26", CubeListBuilder.create().texOffs(16, 4).addBox(13.5959F, -5.0471F, -14.3838F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4634F, -6.6109F, 15.2698F, 0.752F, 0.081F, -0.152F));

        PartDefinition brush_r27 = Broom.addOrReplaceChild("brush_r27", CubeListBuilder.create().texOffs(13, 33).addBox(0.6098F, -49.2614F, 0.349F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.1996F, -46.7052F, 3.5749F, 3.0439F, -0.2828F, -0.347F));

        PartDefinition brush_r28 = Broom.addOrReplaceChild("brush_r28", CubeListBuilder.create().texOffs(32, 13).addBox(7.0594F, -3.6438F, -8.8922F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.2172F, -7.8829F, 12.0831F, 0.8986F, 0.444F, -0.0043F));

        PartDefinition brush_r29 = Broom.addOrReplaceChild("brush_r29", CubeListBuilder.create().texOffs(33, 16).addBox(13.6726F, -37.7039F, -0.7546F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4089F, -41.0462F, -11.5614F, -2.6721F, 0.2828F, 0.347F));

        PartDefinition brush_r30 = Broom.addOrReplaceChild("brush_r30", CubeListBuilder.create().texOffs(5, 34).addBox(2.7785F, -1.3667F, 14.3589F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7965F, -3.6683F, -10.3982F, -0.3242F, 0.3791F, -0.237F));

        PartDefinition brush_r31 = Broom.addOrReplaceChild("brush_r31", CubeListBuilder.create().texOffs(32, 15).addBox(-1.4501F, -37.4494F, 22.8777F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3866F, -37.4876F, -18.1189F, -2.1539F, -0.0039F, -0.444F));

        PartDefinition brush_r32 = Broom.addOrReplaceChild("brush_r32", CubeListBuilder.create().texOffs(33, 17).addBox(11.5032F, -37.4254F, -11.8363F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.6253F, -30.4728F, 24.3398F, 1.8575F, -0.3791F, 0.237F));

        PartDefinition brush_r33 = Broom.addOrReplaceChild("brush_r33", CubeListBuilder.create().texOffs(5, 32).addBox(7.2136F, -12.1221F, -19.9601F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2301F, -4.4459F, 16.164F, 0.5932F, -0.444F, 0.0043F));

        PartDefinition brush_r34 = Broom.addOrReplaceChild("brush_r34", CubeListBuilder.create().texOffs(5, 33).addBox(15.732F, -32.1159F, -4.2442F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.8284F, -38.2831F, 11.524F, 2.6458F, 0.0039F, 0.444F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public BroomBrushBaseModel(ModelPart root) {
        this.broom_brush = root.getChild("Broom");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        broom_brush.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(broom_brush);
    }

    @Override
    public void setupAnim(BroomEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }
}