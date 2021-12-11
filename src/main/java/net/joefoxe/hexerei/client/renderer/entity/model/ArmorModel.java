package net.joefoxe.hexerei.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.client.IItemRenderProperties;

public class ArmorModel <T extends LivingEntity> extends HumanoidModel<T> implements IItemRenderProperties {
//    protected final EquipmentSlot slotType;
//    protected ModelPart Head;
//
//    protected ModelPart Body;
//    protected ModelPart RightArm;
//    protected ModelPart LeftArm;
//
//    protected ModelPart RightLeg;
//    protected ModelPart LeftLeg;
//
//    protected ModelPart Belt;
//    protected ModelPart RightBoot;
//    protected ModelPart LeftBoot;
//
//    public ArmorModel(ModelPart p_170679_, EquipmentSlot slotType) {
//
//        super(p_170679_);
//        this.slotType = slotType;
//    }
//
//    @Override
//    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        matrixStack.pushPose();
//
//        if (this.slotType == EquipmentSlot.HEAD) {
//            Head.copyFrom(this.head);
//            Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//
//        }
//        else if (this.slotType == EquipmentSlot.CHEST) {
//            Body.copyFrom(this.body);
//            Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            RightArm.copyFrom(this.rightArm);
//            RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            LeftArm.copyFrom(this.leftArm);
//            LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//
//        } else if (this.slotType == EquipmentSlot.LEGS) {
//            Belt.copyFrom(this.body);
//            Belt.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            RightLeg.copyFrom(this.rightLeg);
//            RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            LeftLeg.copyFrom(this.leftLeg);
//            LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//
//        } else if (this.slotType == EquipmentSlot.FEET) {
//            RightBoot.copyFrom(this.rightLeg);
//            RightBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            LeftBoot.copyFrom(this.leftLeg);
//            LeftBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        }
//
//        matrixStack.popPose();
//    }



    public EquipmentSlot slot;
    ModelPart root, head, body, leftArm, rightArm, pelvis, leftLegging, rightLegging, leftFoot, rightFoot;

    public ArmorModel(ModelPart root) {
        super(root);
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.pelvis = root.getChild("pelvis");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLegging = root.getChild("left_legging");
        this.rightLegging = root.getChild("right_legging");
        this.leftFoot = root.getChild("left_foot");
        this.rightFoot = root.getChild("right_foot");
    }

    public static PartDefinition createHumanoidAlias(MeshDefinition mesh) {
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("pelvis", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("head", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_legging", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_foot", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_legging", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_foot", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", new CubeListBuilder(), PartPose.ZERO);

        return root;
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return slot == EquipmentSlot.HEAD ? ImmutableList.of(head) : ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        if (slot == EquipmentSlot.CHEST) {
            return ImmutableList.<ModelPart>of(body, leftArm, rightArm);
        }
        else if (slot == EquipmentSlot.LEGS) {
            return ImmutableList.<ModelPart>of(leftLegging, rightLegging, pelvis);
        }
        else if (slot == EquipmentSlot.FEET) {
            return ImmutableList.<ModelPart>of(leftFoot, rightFoot);
        }
        else return ImmutableList.of();
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void copyFromDefault(HumanoidModel model) {
        body.copyFrom(model.body);
        pelvis.copyFrom(model.body);
        head.copyFrom(model.head);
        leftArm.copyFrom(model.leftArm);
        rightArm.copyFrom(model.rightArm);
        leftLegging.copyFrom(leftLeg);
        rightLegging.copyFrom(rightLeg);
        leftFoot.copyFrom(leftLeg);
        rightFoot.copyFrom(rightLeg);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }


}