
package net.joefoxe.hexerei.client.renderer.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.joefoxe.hexerei.Hexerei;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.HashMap;
import java.util.Map;

public class WitchArmorModel<T extends Entity> extends ArmorModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Hexerei.MOD_ID, "witch_armor"), "main");
    public static WitchArmorModel WITCH_ARMOR_MODEL = null;
//    private static final Map<Integer, WitchArmorModel<? extends LivingEntity>> CACHE = new HashMap<>();
//    private final ModelPart Head;
//    private final ModelPart Body;
//    private final ModelPart RightArm;
//    private final ModelPart LeftArm;
//    private final ModelPart Belt;
//    private final ModelPart RightLeg;
//    private final ModelPart LeftLeg;
//    private final ModelPart RightBoot;
//    private final ModelPart LeftBoot;
//    private final EquipmentSlot slot;
//    private final byte entityFlag;
//
//
//    public WitchArmorModel(ModelPart root, EquipmentSlot slotType) {
//        super(root, slotType);
//        this.slot = slotType;
//		this.entityFlag = (byte) (0 >> 4);
////		textureWidth = 128;
////		textureHeight = 128;
//        this.Head = root.getChild("Head");
//        this.Body = root.getChild("Body");
//        this.RightArm = root.getChild("RightArm");
//        this.LeftArm = root.getChild("LeftArm");
//        this.Belt = root.getChild("Belt");
//        this.RightLeg = root.getChild("RightLeg");
//        this.LeftLeg = root.getChild("LeftLeg");
//        this.RightBoot = root.getChild("RightBoot");
//        this.LeftBoot = root.getChild("LeftBoot");
//    }


    public WitchArmorModel(ModelPart part) {
        super(part);
    }

//    public static LayerDefinition createBodyLayer() {
//        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0), 0);
//        PartDefinition root = createHumanoidAlias(mesh);
//
//        PartDefinition body = root.getChild("body");
//        PartDefinition pelvis = root.getChild("pelvis");
//        PartDefinition right_foot = root.getChild("right_foot");
//        PartDefinition left_foot = root.getChild("left_foot");
//        PartDefinition right_legging = root.getChild("right_legging");
//        PartDefinition left_legging = root.getChild("left_legging");
//        PartDefinition left_arm = root.getChild("left_arm");
//        PartDefinition right_arm = root.getChild("right_arm");
//        PartDefinition head = root.getChild("head");
//
//        PartDefinition chest = body.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
//        PartDefinition right_shoulder = right_arm.addOrReplaceChild("right_shoulder", new CubeListBuilder().mirror().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(1.0f)), PartPose.ZERO);
//        PartDefinition left_shoulder = left_arm.addOrReplaceChild("left_shoulder", new CubeListBuilder().texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(1.0f)), PartPose.ZERO);
//        PartDefinition helm = head.addOrReplaceChild("helm", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.ZERO);
//        PartDefinition guard = helm.addOrReplaceChild("guard", CubeListBuilder.create().texOffs(6, 41).addBox(-5.0F, -6.0F, -5.5F, 10.0F, 10.0F, 10.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
//        PartDefinition left_boot = left_foot.addOrReplaceChild("left_boot", new CubeListBuilder().texOffs(0, 22).mirror().addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(1.0f)), PartPose.ZERO);
//        PartDefinition right_boot = right_foot.addOrReplaceChild("right_boot", new CubeListBuilder().texOffs(0, 22).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(1.0f)), PartPose.ZERO);
//        PartDefinition left_leg = left_legging.addOrReplaceChild("left_leg", new CubeListBuilder().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.5f)), PartPose.ZERO);
//        PartDefinition right_leg = right_legging.addOrReplaceChild("right_leg", new CubeListBuilder().texOffs(0, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.5f)), PartPose.ZERO);
//        PartDefinition codpiece = pelvis.addOrReplaceChild("codpiece", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 7.0F, 0.0F));
//
//        return LayerDefinition.create(mesh, 64, 64);
//    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0), 0);
        PartDefinition root = createHumanoidAlias(mesh);

        PartDefinition head = root.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(-2.0F, -7.0F, 0.0F));

        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.75F, -1.95F, -5.25F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3048F, -0.0186F, -0.1276F));

        PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(24, 31).addBox(1.35F, 0.05F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.704F, -9.2025F, 5.7117F, -0.5968F, 0.6696F, 1.2934F));

        PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(24, 31).addBox(1.75F, 0.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.4009F, -7.7447F, 6.4586F, -0.6173F, 0.8249F, 1.5822F));

        PartDefinition head_r4 = head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -31.75F, -11.25F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6618F, 30.413F, -5.3821F, -0.3048F, -0.0186F, -0.1276F));

        PartDefinition head_r5 = head.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(0, 16).addBox(-2.5F, 0.0F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0099F, -3.6229F, 0.9681F, -0.3481F, 0.0316F, 0.0844F));

        PartDefinition head_r6 = head.addOrReplaceChild("head_r6", CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, -1.5F, -3.75F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0132F, -4.5805F, 1.2908F, -0.4582F, 0.1451F, 0.2752F));

        PartDefinition head_r7 = head.addOrReplaceChild("head_r7", CubeListBuilder.create().texOffs(24, 31).addBox(3.0F, -2.55F, -2.25F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4879F, -8.9451F, 5.148F, -0.4867F, 0.5183F, 0.7968F));

        PartDefinition head_r8 = head.addOrReplaceChild("head_r8", CubeListBuilder.create().texOffs(0, 31).addBox(0.0F, -1.5F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6455F, -6.8213F, 2.9487F, -0.3675F, 0.2629F, 0.4639F));

        PartDefinition body = root.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cloak = body.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(0, 41).addBox(-6.5F, -12.499F, -2.501F, 9.0F, 15.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(28, 56).addBox(-7.0F, -12.5F, -3.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition left_side = cloak.addOrReplaceChild("left_side", CubeListBuilder.create().texOffs(0, 64).addBox(-2.0F, 0.0F, 0.01F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.5F, -3.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition right_side = cloak.addOrReplaceChild("right_side", CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.01F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.5F, -3.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition back_side = cloak.addOrReplaceChild("back_side", CubeListBuilder.create().texOffs(17, 70).addBox(-5.01F, 0.0F, 0.0F, 10.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.5F, 1.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition rightArm = root.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-5.0F, 0.0F, 0.0F));

        PartDefinition rightarm_r1 = rightArm.addOrReplaceChild("rightarm_r1", CubeListBuilder.create().texOffs(28, 38).addBox(-2.469F, -9.2381F, -3.0253F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(28, 38).addBox(-1.969F, -8.4881F, -2.5253F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1232F, 6.015F, 0.0218F, -0.0135F, -0.0064F, 0.0445F));

        PartDefinition rightarm_r2 = rightArm.addOrReplaceChild("rightarm_r2", CubeListBuilder.create().texOffs(28, 38).addBox(-4.25F, -1.5F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 38).addBox(-4.75F, 0.5F, 0.25F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0725F, 3.1407F, -0.9944F, -0.0041F, 0.7798F, 0.0409F));

        PartDefinition rightarm_r3 = rightArm.addOrReplaceChild("rightarm_r3", CubeListBuilder.create().texOffs(28, 48).addBox(-1.75F, -0.5F, -2.25F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3544F, 8.6881F, 3.0088F, -0.1954F, 0.8662F, -0.0082F));

        PartDefinition rightarm_r4 = rightArm.addOrReplaceChild("rightarm_r4", CubeListBuilder.create().texOffs(28, 48).addBox(-2.2814F, 1.8917F, -2.9187F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1232F, 6.015F, 0.0218F, -0.014F, 0.0821F, 0.1306F));

        PartDefinition leftArm = root.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition leftarm_r1 = leftArm.addOrReplaceChild("leftarm_r1", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(2.25F, -1.5F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(28, 38).mirror().addBox(1.75F, 0.5F, 0.25F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0725F, 3.1407F, -0.9944F, -0.0041F, -0.7798F, -0.0409F));

        PartDefinition leftarm_r2 = leftArm.addOrReplaceChild("leftarm_r2", CubeListBuilder.create().texOffs(28, 48).mirror().addBox(-2.25F, -0.5F, -2.25F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.3544F, 8.6881F, 3.0088F, -0.1954F, -0.8662F, 0.0082F));

        PartDefinition leftarm_r3 = leftArm.addOrReplaceChild("leftarm_r3", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-3.531F, -9.2381F, -3.0253F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(28, 38).mirror().addBox(-3.031F, -8.4881F, -2.5253F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1232F, 6.015F, 0.0218F, -0.0135F, 0.0064F, -0.0445F));

        PartDefinition leftarm_r4 = leftArm.addOrReplaceChild("leftarm_r4", CubeListBuilder.create().texOffs(28, 48).mirror().addBox(-3.7186F, 1.8917F, -2.9187F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1232F, 6.015F, 0.0218F, -0.014F, -0.0821F, -0.1306F));

        PartDefinition feathers = leftArm.addOrReplaceChild("feathers", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition feather_r1 = feathers.addOrReplaceChild("feather_r1", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.0F, 0.25F, 0.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.3075F, -3.5968F, 2.3999F, -0.5107F, -0.5313F, -0.6482F));

        PartDefinition feather_r2 = feathers.addOrReplaceChild("feather_r2", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.4492F, -1.1555F, 3.3527F, -1.7342F, -0.5874F, 0.389F));

        PartDefinition feather_r3 = feathers.addOrReplaceChild("feather_r3", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.5F, 1.25F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.2999F, 0.6819F, 3.9032F, -2.0813F, -0.3443F, 1.1283F));

        PartDefinition feather_r4 = feathers.addOrReplaceChild("feather_r4", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.0F, 0.25F, -1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.3075F, -3.5968F, -2.3999F, 0.5107F, 0.5313F, -0.6482F));

        PartDefinition feather_r5 = feathers.addOrReplaceChild("feather_r5", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.5F, 1.25F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.2999F, 0.6819F, -3.9032F, 2.0813F, 0.3443F, 1.1283F));

        PartDefinition feather_r6 = feathers.addOrReplaceChild("feather_r6", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.5F, 0.1F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1114F, -1.5281F, 3.6198F, -2.0357F, -0.4057F, 1.0041F));

        PartDefinition feather_r7 = feathers.addOrReplaceChild("feather_r7", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.5F, 0.1F, -2.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1114F, -1.5281F, -3.6198F, 2.0357F, 0.4057F, 1.0041F));

        PartDefinition feather_r8 = feathers.addOrReplaceChild("feather_r8", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.4492F, -1.1555F, -3.3527F, 1.7342F, 0.5874F, 0.389F));

        PartDefinition feather_r9 = feathers.addOrReplaceChild("feather_r9", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.25F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.7935F, -3.8956F, -1.885F, 0.2882F, 0.5236F, -0.4894F));

        PartDefinition feather_r10 = feathers.addOrReplaceChild("feather_r10", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-3.75F, -0.5F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.1351F, -4.3222F, 0.1185F, -0.0149F, 0.0001F, -0.4808F));

        PartDefinition feather_r11 = feathers.addOrReplaceChild("feather_r11", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.25F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.8214F, -3.843F, 2.1146F, -0.4088F, -0.3926F, -0.4746F));

        PartDefinition feather_r12 = feathers.addOrReplaceChild("feather_r12", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.0927F, -0.8327F, -2.0443F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.9017F, 0.3284F, 0.0985F, 0.6259F, 0.2169F, 1.1857F));

        PartDefinition feather_r13 = feathers.addOrReplaceChild("feather_r13", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.3885F, 0.5048F, -0.522F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.9017F, 0.3284F, 0.0985F, -0.0079F, 0.0126F, 1.2209F));

        PartDefinition feather_r14 = feathers.addOrReplaceChild("feather_r14", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.8894F, -0.6703F, 1.0842F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.9017F, 0.3284F, 0.0985F, -0.5627F, -0.3357F, 1.3216F));

        PartDefinition feather_r15 = feathers.addOrReplaceChild("feather_r15", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.75F, -0.25F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.401F, -2.0952F, 2.0806F, -0.5627F, -0.3357F, 0.6235F));

        PartDefinition feather_r16 = feathers.addOrReplaceChild("feather_r16", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.75F, -0.75F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.3731F, -2.1478F, -1.919F, 0.6259F, 0.2169F, 0.4876F));

        PartDefinition feather_r17 = feathers.addOrReplaceChild("feather_r17", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.5F, 1.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.679F, -3.3455F, 0.1018F, -0.0109F, 0.0101F, 0.2609F));

        PartDefinition feather_r18 = feathers.addOrReplaceChild("feather_r18", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-0.5F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.679F, -3.3455F, 0.1018F, -0.0145F, 0.0033F, -0.2626F));

        PartDefinition feather_r19 = feathers.addOrReplaceChild("feather_r19", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.0F, 0.25F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0712F, -2.7099F, 2.5799F, -0.9739F, -0.6044F, 0.6491F));

        PartDefinition feather_r20 = feathers.addOrReplaceChild("feather_r20", CubeListBuilder.create().texOffs(28, 38).mirror().addBox(-1.5F, 0.0F, -1.25F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0712F, -2.7099F, -2.5799F, 0.9739F, 0.6044F, 0.6491F));

        PartDefinition belt = root.addOrReplaceChild("Belt", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightLeg = root.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftLeg = root.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightBoot = root.addOrReplaceChild("RightBoot", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition right_boot = rightBoot.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(0, 84).addBox(-2.5F, 5.5F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 80).addBox(-2.5F, 9.5F, -3.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition right_boot_cuff = right_boot.addOrReplaceChild("right_boot_cuff", CubeListBuilder.create().texOffs(20, 84).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition leftBoot = root.addOrReplaceChild("LeftBoot", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition left_boot = leftBoot.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(0, 84).addBox(-2.5F, 5.5F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 80).addBox(-2.5F, 9.5F, -3.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition left_boot_cuff = left_boot.addOrReplaceChild("left_boot_cuff", CubeListBuilder.create().texOffs(20, 84).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        return LayerDefinition.create(mesh, 64, 64);
    }


    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}





//    @Override
//    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        Head.render(poseStack, buffer, packedLight, packedOverlay);
//        Body.render(poseStack, buffer, packedLight, packedOverlay);
//        rightArm.render(poseStack, buffer, packedLight, packedOverlay);
//        LeftArm.render(poseStack, buffer, packedLight, packedOverlay);
//        Belt.render(poseStack, buffer, packedLight, packedOverlay);
//        RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
//        LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
//        RightBoot.render(poseStack, buffer, packedLight, packedOverlay);
//        LeftBoot.render(poseStack, buffer, packedLight, packedOverlay);
//    }

    	// THIS IS THE RENDERING OF THE ARMOR FOR SLOTS THIS IS NEEDED IN EACH JAVA FILE
//	@Override
//	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//
//		matrixStack.pushPose();
//		if (this.slot == EquipmentSlot.HEAD) {
//			this.Head.copyFrom(this.head);
//			this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//		}
//		else if (this.slot == EquipmentSlot.CHEST) {
//			this.Body.copyFrom(this.body);
//			this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//			this.RightArm.copyFrom(this.rightArm);
//			this.RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//			this.LeftArm.copyFrom(this.leftArm);
//			this.LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//
//		} else if (this.slot == EquipmentSlot.LEGS) {
//			this.Belt.copyFrom(this.body);
//			this.Belt.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//			this.RightLeg.copyFrom(this.rightLeg);
//			this.RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//			this.LeftLeg.copyFrom(this.leftLeg);
//			this.LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//
//		} else if (this.slot == EquipmentSlot.FEET) {
//			this.RightBoot.copyFrom(this.rightLeg);
//			this.RightBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//			this.LeftBoot.copyFrom(this.leftLeg);
//			this.LeftBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//		}
//		matrixStack.popPose();
//	}

//    	// This is also needed
//	@SuppressWarnings("unchecked")
//	public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
//		boolean illager = entityLiving instanceof AbstractIllager ||
//                entityLiving instanceof ZombieVillager ||
//                entityLiving instanceof AbstractVillager;
//		int entityFlag = (slot.ordinal() & 15) | (illager ? 1 : 0) << 4 | (entityLiving.isBaby() ? 1 : 0) << 6;
//		return (A) CACHE.computeIfAbsent(entityFlag, k -> new WitchArmorModel(1, slot));
//	}
    //// TODO figure out what do to in place of the 1.


//    default <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default)

//}