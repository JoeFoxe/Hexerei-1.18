//package net.joefoxe.hexerei.client.renderer.entity.model;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.joefoxe.hexerei.client.renderer.entity.custom.PigeonEntity;
//import net.minecraft.client.renderer.entity.model.EntityModel;
//import net.minecraft.client.renderer.model.ModelPart;
//import net.minecraft.util.Mth;
//
//public class PigeonModel<T extends PigeonEntity> extends EntityModel<T> {
//    private final ModelPart legs;
//    private final ModelPart body;
//    private final ModelPart head;
//    private final ModelPart rightWing;
//    private final ModelPart cube_r1;
//    private final ModelPart leftWing;
//    private final ModelPart cube_r2;
//
//    public PigeonModel() {
//        textureWidth = 32;
//        textureHeight = 32;
//
//        legs = new ModelPart(this);
//        legs.setRotation(0.0F, 24.0F, 0.0F);
//        legs.setTextureOffset(0, 8).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        legs.setTextureOffset(0, 0).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//        body = new ModelPart(this);
//        body.setRotation(0.0F, 24.0F, 0.0F);
//        body.setTextureOffset(0, 8).addBox(-2.0F, -4.0F, -1.0F, 3.0F, 2.0F, 5.0F, 0.0F, false);
//        body.setTextureOffset(16, 0).addBox(-2.0F, -3.0F, 4.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
//        body.setTextureOffset(0, 0).addBox(-3.0F, -5.0F, -2.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);
//        body.setTextureOffset(12, 8).addBox(-2.0F, -6.0F, -2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//
//        head = new ModelPart(this);
//        head.setRotation(-0.5F, 18.25F, -0.75F);
//        head.setTextureOffset(6, 16).addBox(-0.5F, -1.25F, -2.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        head.setTextureOffset(9, 21).addBox(-1.0F, -2.25F, -1.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//        rightWing = new ModelPart(this);
//        rightWing.setRotation(-2.9509F, 20.0419F, -0.8627F);
//
//
//        cube_r1 = new ModelPart(this);
//        cube_r1.setRotation(2.9509F, 3.9581F, 0.8627F);
//        rightWing.addChild(cube_r1);
//        setRotationAngle(cube_r1, -0.3491F, 0.0873F, 0.1309F);
//        cube_r1.setTextureOffset(23, 16).addBox(-4.0F, -3.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//        cube_r1.setTextureOffset(18, 21).addBox(-4.0F, -4.0F, 0.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
//        cube_r1.setTextureOffset(0, 16).addBox(-4.0F, -5.0F, -3.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
//
//        leftWing = new ModelPart(this);
//        leftWing.setRotation(2.0491F, 20.0419F, -0.8627F);
//
//
//        cube_r2 = new ModelPart(this);
//        cube_r2.setRotation(0.0F, 0.5F, 1.1667F);
//        leftWing.addChild(cube_r2);
//        setRotationAngle(cube_r2, -0.3491F, -0.0873F, -0.1309F);
//        cube_r2.setTextureOffset(14, 13).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
//        cube_r2.setTextureOffset(23, 11).addBox(-0.5F, 0.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//        cube_r2.setTextureOffset(22, 4).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
//    }
//
//    @Override
//    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
//                                  float netHeadYaw, float headPitch) {
//        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
//        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
//        if(entityIn.getDeltaMovement().getY() > 0) {
//            this.rightWing.rotateAngleY = Mth.cos(ageInTicks * 2.6662F + (float) Math.PI) * 2.4F * ageInTicks;
//            this.leftWing.rotateAngleY = Mth.cos(ageInTicks * 2.6662F) * 2.4F * ageInTicks;
//        } else {
//            this.rightWing.rotateAngleY = 0;
//            this.leftWing.rotateAngleY = 0;
//        }
//    }
//
//    @Override
//    public void render(PoseStack matrixStack, VertexConsumer buffer, int packedLight,
//                       int packedOverlay, float red, float green, float blue, float alpha) {
//        legs.render(matrixStack, buffer, packedLight, packedOverlay);
//        body.render(matrixStack, buffer, packedLight, packedOverlay);
//        head.render(matrixStack, buffer, packedLight, packedOverlay);
//        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
//        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
//
//    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//}
