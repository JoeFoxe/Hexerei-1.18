//package net.joefoxe.hexerei.client.renderer.entity.model;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.joefoxe.hexerei.client.renderer.entity.custom.BuffZombieEntity;
//import net.minecraft.client.renderer.entity.model.EntityModel;
//import net.minecraft.client.renderer.model.ModelPart;
//import net.minecraft.util.Mth;
//
//public class BuffZombieModel <T extends BuffZombieEntity> extends EntityModel<T> {
//    private final ModelPart head;
//    private final ModelPart body;
//    private final ModelPart rightLeg;
//    private final ModelPart rightArm;
//    private final ModelPart bone2;
//    private final ModelPart rightArm_r1;
//    private final ModelPart cube_r1_r1;
//    private final ModelPart bone3;
//    private final ModelPart rightArm_r2;
//    private final ModelPart cube_r1_r3;
//    private final ModelPart leftLeg;
//    private final ModelPart leftArm;
//    private final ModelPart bone;
//    private final ModelPart cube_r1_r2;
//    private final ModelPart leftArm_r1;
//
//    public BuffZombieModel() {
//        textureWidth = 128;
//        textureHeight = 128;
//
//        head = new ModelPart(this);
//        head.setRotation(0.5F, -4.0F, 0.0F);
//        head.setTextureOffset(0, 28).addBox(-6.0F, -12.0F, -6.0F, 11.0F, 12.0F, 13.0F, 0.0F, false);
//
//        body = new ModelPart(this);
//        body.setRotation(0.0F, 26.0F, 0.0F);
//        body.setTextureOffset(0, 0).addBox(-9.0F, -31.0F, -4.0F, 18.0F, 18.0F, 9.0F, 0.0F, false);
//
//        rightLeg = new ModelPart(this);
//        rightLeg.setRotation(-4.0F, 11.0F, 1.0F);
//        rightLeg.setTextureOffset(55, 0).addBox(-4.0F, 0.0F, -4.0F, 7.0F, 13.0F, 7.0F, 0.0F, false);
//
//        rightArm = new ModelPart(this);
//        rightArm.setRotation(-8.0F, -1.0F, -4.0F);
//
//
//        bone2 = new ModelPart(this);
//        bone2.setRotation(-17.0F, 27.0F, 0.0F);
//        rightArm.addChild(bone2);
//
//
//        rightArm_r1 = new ModelPart(this);
//        rightArm_r1.setRotation(25.0F, 0.0F, 0.0F);
//        bone2.addChild(rightArm_r1);
//        setRotationAngle(rightArm_r1, -1.5708F, 0.0F, 0.0F);
//        rightArm_r1.setTextureOffset(42, 47).addBox(-16.0F, -6.0F, -30.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);
//
//        cube_r1_r1 = new ModelPart(this);
//        cube_r1_r1.setRotation(0.0F, 0.0F, -4.0F);
//        bone2.addChild(cube_r1_r1);
//        setRotationAngle(cube_r1_r1, 0.3491F, 0.0F, 0.0F);
//        cube_r1_r1.setTextureOffset(35, 28).addBox(9.3F, -31.2F, -7.9F, 1.0F, -1.0F, 4.0F, 0.0F, false);
//        cube_r1_r1.setTextureOffset(35, 28).addBox(12.1F, -31.2F, -7.9F, 1.0F, -1.0F, 4.0F, 0.0F, false);
//        cube_r1_r1.setTextureOffset(35, 28).addBox(14.8F, -31.2F, -7.9F, 1.0F, -1.0F, 4.0F, 0.0F, false);
//
//        bone3 = new ModelPart(this);
//        bone3.setRotation(-17.0F, 27.0F, 0.0F);
//        rightArm.addChild(bone3);
//        bone3.setTextureOffset(44, 78).addBox(12.1F, -28.0F, -19.0F, 1.0F, -1.0F, 13.0F, 0.0F, false);
//        bone3.setTextureOffset(42, 75).addBox(14.8F, -28.0F, -19.0F, 1.0F, -1.0F, 13.0F, 0.0F, false);
//        bone3.setTextureOffset(42, 81).addBox(9.3F, -28.0F, -19.0F, 1.0F, -1.0F, 13.0F, 0.0F, false);
//
//        rightArm_r2 = new ModelPart(this);
//        rightArm_r2.setRotation(25.0F, 0.0F, 0.0F);
//        bone3.addChild(rightArm_r2);
//        setRotationAngle(rightArm_r2, -1.5708F, 0.0F, 0.0F);
//        rightArm_r2.setTextureOffset(42, 47).addBox(-16.0F, -6.0F, -30.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);
//
//        cube_r1_r3 = new ModelPart(this);
//        cube_r1_r3.setRotation(0.0F, 0.0F, -4.0F);
//        bone3.addChild(cube_r1_r3);
//        setRotationAngle(cube_r1_r3, 0.3491F, 0.0F, 0.0F);
//
//
//        leftLeg = new ModelPart(this);
//        leftLeg.setRotation(4.0F, 11.0F, 0.0F);
//        leftLeg.setTextureOffset(0, 54).addBox(-3.0F, 0.0F, -3.0F, 7.0F, 13.0F, 7.0F, 0.0F, false);
//
//        leftArm = new ModelPart(this);
//        leftArm.setRotation(8.0F, -1.0F, 0.0F);
//
//
//        bone = new ModelPart(this);
//        bone.setRotation(-8.0F, 27.0F, 0.0F);
//        leftArm.addChild(bone);
//        bone.setTextureOffset(44, 82).addBox(14.8F, -28.0F, -23.0F, 1.0F, -1.0F, 13.0F, 0.0F, false);
//        bone.setTextureOffset(44, 82).addBox(12.1F, -28.0F, -23.0F, 1.0F, -1.0F, 13.0F, 0.0F, false);
//        bone.setTextureOffset(41, 78).addBox(9.3F, -28.0F, -23.0F, 1.0F, -1.0F, 13.0F, 0.0F, false);
//
//        cube_r1_r2 = new ModelPart(this);
//        cube_r1_r2.setRotation(0.0F, 0.0F, 0.0F);
//        bone.addChild(cube_r1_r2);
//        setRotationAngle(cube_r1_r2, 0.3491F, 0.0F, 0.0F);
//        cube_r1_r2.setTextureOffset(3, 28).addBox(14.8F, -34.2F, -15.5F, 1.0F, -1.0F, 4.0F, 0.0F, false);
//        cube_r1_r2.setTextureOffset(1, 28).addBox(12.1F, -34.2F, -15.5F, 1.0F, -1.0F, 4.0F, 0.0F, false);
//        cube_r1_r2.setTextureOffset(0, 32).addBox(9.3F, -34.2F, -15.5F, 1.0F, -1.0F, 4.0F, 0.0F, false);
//
//        leftArm_r1 = new ModelPart(this);
//        leftArm_r1.setRotation(0.0F, 0.0F, 0.0F);
//        bone.addChild(leftArm_r1);
//        setRotationAngle(leftArm_r1, -1.5708F, 0.0F, 0.0F);
//        leftArm_r1.setTextureOffset(49, 21).addBox(9.0F, -3.0F, -30.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);
//    }
//
//    @Override
//    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount,
//                                  float ageInTicks, float netHeadYaw, float headPitch) {
//        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
//        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
//        this.rightLeg.rotateAngleX = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        this.leftLeg.rotateAngleX = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//    }
//
//    @Override
//    public void render(PoseStack matrixStack, VertexConsumer buffer, int packedLight,
//                       int packedOverlay, float red, float green, float blue, float alpha) {
//        head.render(matrixStack, buffer, packedLight, packedOverlay);
//        body.render(matrixStack, buffer, packedLight, packedOverlay);
//        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
//        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
//        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
//        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
//
//    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//}