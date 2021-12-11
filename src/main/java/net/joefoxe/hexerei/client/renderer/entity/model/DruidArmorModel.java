//
//package net.joefoxe.hexerei.client.renderer.entity.model;
//
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.model.HumanoidModel;
//import net.minecraft.client.renderer.model.ModelPart;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
//import net.minecraft.entity.monster.AbstractIllagerEntity;
//import net.minecraft.entity.monster.ZombieVillagerEntity;
//import net.minecraft.world.entity.EquipmentSlot;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DruidArmorModel <T extends LivingEntity> extends ArmorModel<T> {
//
//	private static final Map<Integer, net.joefoxe.hexerei.client.renderer.entity.model.DruidArmorModel<? extends LivingEntity>> CACHE = new HashMap<>();
//
//	private final ModelPart Head;
//	private final ModelPart Head_r1;
//	private final ModelPart Head_r2;
//	private final ModelPart Head_r3;
//	private final ModelPart Head_r4;
//	private final ModelPart Head_r5;
//	private final ModelPart Head_r6;
//	private final ModelPart Head_r7;
//	private final ModelPart Head_r8;
//	private final ModelPart Head_r9;
//	private final ModelPart Head_r10;
//	private final ModelPart Head_r11;
//	private final ModelPart Head_r12;
//	private final ModelPart Head_r13;
//	private final ModelPart Head_r14;
//	private final ModelPart Head_r15;
//	private final ModelPart Head_r16;
//	private final ModelPart Head_r17;
//	private final ModelPart Head_r18;
//	private final ModelPart Head_r19;
//	private final ModelPart Head_r20;
//	private final ModelPart Head_r21;
//	private final ModelPart Head_r22;
//	private final ModelPart Head_r23;
//	private final ModelPart Head_r24;
//	private final ModelPart Head_r25;
//	private final ModelPart Head_r26;
//	private final ModelPart Head_r27;
//	private final ModelPart Head_r28;
//	private final ModelPart Head_r29;
//	private final ModelPart Head_r30;
//	private final ModelPart Head_r31;
//	private final ModelPart Head_r32;
//	private final ModelPart Head_r33;
//	private final ModelPart Head_r34;
//	private final ModelPart Body;
//	private final ModelPart Body_r1;
//	private final ModelPart Body_r2;
//	private final ModelPart Body_r3;
//	private final ModelPart Body_r4;
//	private final ModelPart Body_r5;
//	private final ModelPart Body_r6;
//	private final ModelPart Body_r7;
//	private final ModelPart Body_r8;
//	private final ModelPart Body_r9;
//	private final ModelPart Body_r10;
//	private final ModelPart Body_r11;
//	private final ModelPart Body_r12;
//	private final ModelPart Body_r13;
//	private final ModelPart Body_r14;
//	private final ModelPart Body_r15;
//	private final ModelPart Body_r16;
//	private final ModelPart Body_r17;
//	private final ModelPart Body_r18;
//	private final ModelPart Body_r19;
//	private final ModelPart Body_r20;
//	private final ModelPart Body_r21;
//	private final ModelPart Body_r22;
//	private final ModelPart Body_r23;
//	private final ModelPart Body_r24;
//	private final ModelPart Body_r25;
//	private final ModelPart Body_r26;
//	private final ModelPart Body_r27;
//	private final ModelPart Body_r28;
//	private final ModelPart Body_r29;
//	private final ModelPart Body_r30;
//	private final ModelPart Body_r31;
//	private final ModelPart RightArm;
//	private final ModelPart RightArm_r1;
//	private final ModelPart RightArm_r2;
//	private final ModelPart RightArm_r3;
//	private final ModelPart RightArm_r4;
//	private final ModelPart RightArm_r5;
//	private final ModelPart RightArm_r6;
//	private final ModelPart RightArm_r7;
//	private final ModelPart RightArm_r8;
//	private final ModelPart RightArm_r9;
//	private final ModelPart RightArm_r10;
//	private final ModelPart RightArm_r11;
//	private final ModelPart RightArm_r12;
//	private final ModelPart RightArm_r13;
//	private final ModelPart RightArm_r14;
//	private final ModelPart RightArm_r15;
//	private final ModelPart RightArm_r16;
//	private final ModelPart RightArm_r17;
//	private final ModelPart RightArm_r18;
//	private final ModelPart RightArm_r19;
//	private final ModelPart RightArm_r20;
//	private final ModelPart RightArm_r21;
//	private final ModelPart RightArm_r22;
//	private final ModelPart RightArm_r23;
//	private final ModelPart RightArm_r24;
//	private final ModelPart RightArm_r25;
//	private final ModelPart RightArm_r26;
//	private final ModelPart RightArm_r27;
//	private final ModelPart RightArm_r28;
//	private final ModelPart RightArm_r29;
//	private final ModelPart RightArm_r30;
//	private final ModelPart RightArm_r31;
//	private final ModelPart RightArm_r32;
//	private final ModelPart RightArm_r33;
//	private final ModelPart RightArm_r34;
//	private final ModelPart RightArm_r35;
//	private final ModelPart RightArm_r36;
//	private final ModelPart RightArm_r37;
//	private final ModelPart RightArm_r38;
//	private final ModelPart LeftArm;
//	private final ModelPart LeftArm_r1;
//	private final ModelPart LeftArm_r2;
//	private final ModelPart LeftArm_r3;
//	private final ModelPart LeftArm_r4;
//	private final ModelPart LeftArm_r5;
//	private final ModelPart LeftArm_r6;
//	private final ModelPart LeftArm_r7;
//	private final ModelPart LeftArm_r8;
//	private final ModelPart LeftArm_r9;
//	private final ModelPart LeftArm_r10;
//	private final ModelPart LeftArm_r11;
//	private final ModelPart LeftArm_r12;
//	private final ModelPart LeftArm_r13;
//	private final ModelPart LeftArm_r14;
//	private final ModelPart LeftArm_r15;
//	private final ModelPart LeftArm_r16;
//	private final ModelPart LeftArm_r17;
//	private final ModelPart LeftArm_r18;
//	private final ModelPart LeftArm_r19;
//	private final ModelPart LeftArm_r20;
//	private final ModelPart LeftArm_r21;
//	private final ModelPart LeftArm_r22;
//	private final ModelPart LeftArm_r23;
//	private final ModelPart LeftArm_r24;
//	private final ModelPart LeftArm_r25;
//	private final ModelPart LeftArm_r26;
//	private final ModelPart LeftArm_r27;
//	private final ModelPart LeftArm_r28;
//	private final ModelPart LeftArm_r29;
//	private final ModelPart LeftArm_r30;
//	private final ModelPart LeftArm_r31;
//	private final ModelPart LeftArm_r32;
//	private final ModelPart LeftArm_r33;
//	private final ModelPart LeftArm_r34;
//	private final ModelPart LeftArm_r35;
//	private final ModelPart LeftArm_r36;
//	private final ModelPart LeftArm_r37;
//	private final ModelPart LeftArm_r38;
//	private final ModelPart Belt;
//	private final ModelPart Belt_r1;
//	private final ModelPart Belt_r2;
//	private final ModelPart Belt_r3;
//	private final ModelPart Belt_r4;
//	private final ModelPart Belt_r5;
//	private final ModelPart Belt_r6;
//	private final ModelPart Belt_r7;
//	private final ModelPart Belt_r8;
//	private final ModelPart Belt_r9;
//	private final ModelPart Belt_r10;
//	private final ModelPart Belt_r11;
//	private final ModelPart Belt_r12;
//	private final ModelPart RightLeg;
//	private final ModelPart RightLeg_r1;
//	private final ModelPart RightLeg_r2;
//	private final ModelPart RightLeg_r3;
//	private final ModelPart RightLeg_r4;
//	private final ModelPart RightLeg_r5;
//	private final ModelPart RightLeg_r6;
//	private final ModelPart RightLeg_r7;
//	private final ModelPart RightLeg_r8;
//	private final ModelPart RightLeg_r9;
//	private final ModelPart RightLeg_r10;
//	private final ModelPart RightLeg_r11;
//	private final ModelPart RightLeg_r12;
//	private final ModelPart RightLeg_r13;
//	private final ModelPart RightLeg_r14;
//	private final ModelPart RightLeg_r15;
//	private final ModelPart RightLeg_r16;
//	private final ModelPart LeftLeg;
//	private final ModelPart LeftLeg_r1;
//	private final ModelPart LeftLeg_r2;
//	private final ModelPart LeftLeg_r3;
//	private final ModelPart LeftLeg_r4;
//	private final ModelPart LeftLeg_r5;
//	private final ModelPart LeftLeg_r6;
//	private final ModelPart LeftLeg_r7;
//	private final ModelPart LeftLeg_r8;
//	private final ModelPart LeftLeg_r9;
//	private final ModelPart LeftLeg_r10;
//	private final ModelPart LeftLeg_r11;
//	private final ModelPart LeftLeg_r12;
//	private final ModelPart LeftLeg_r13;
//	private final ModelPart LeftLeg_r14;
//	private final ModelPart LeftLeg_r15;
//	private final ModelPart LeftLeg_r16;
//	private final ModelPart LeftLeg_r17;
//	private final ModelPart LeftLeg_r18;
//	private final ModelPart LeftLeg_r19;
//	private final ModelPart LeftLeg_r20;
//	private final ModelPart LeftLeg_r21;
//	private final ModelPart RightBoot;
//	private final ModelPart RightBoot_r1;
//	private final ModelPart RightBoot_r2;
//	private final ModelPart RightBoot_r3;
//	private final ModelPart RightBoot_r4;
//	private final ModelPart RightBoot_r5;
//	private final ModelPart RightBoot_r6;
//	private final ModelPart RightBoot_r7;
//	private final ModelPart LeftBoot;
//	private final ModelPart LeftBoot_r1;
//	private final ModelPart LeftBoot_r2;
//	private final ModelPart LeftBoot_r3;
//	private final ModelPart LeftBoot_r4;
//	private final ModelPart LeftBoot_r5;
//	private final ModelPart LeftBoot_r6;
//	private final ModelPart LeftBoot_r7;
//	private final EquipmentSlot slot;
//	private final byte entityFlag;
//
//	public DruidArmorModel(float modelSize, EquipmentSlot slotType) {
//		super(modelSize, slotType);
//		this.slot = slotType;
//		this.entityFlag = (byte) (0 >> 4);
//		textureWidth = 128;
//		textureHeight = 128;
//
//		Head = new ModelPart(this);
//		Head.setRotation(0.0F, 0.0F, 0.0F);
//		Head.setTextureOffset(88, 0).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
//		Head.setTextureOffset(71, 9).addBox(-4.0F, -9.5F, -5.25F, 2.0F, 11.0F, 11.0F, 0.0F, false);
//		Head.setTextureOffset(102, 70).addBox(-5.3F, -9.5F, 2.0F, 11.0F, 11.0F, 2.0F, 0.0F, false);
//		Head.setTextureOffset(102, 57).addBox(-5.8F, -9.75F, -3.0F, 11.0F, 11.0F, 2.0F, 0.0F, false);
//		Head.setTextureOffset(69, 33).addBox(1.0F, -9.5F, -5.5F, 2.0F, 11.0F, 11.0F, 0.0F, false);
//		Head.setTextureOffset(84, 31).addBox(-5.5F, -3.5F, -5.55F, 11.0F, 2.0F, 11.0F, 0.0F, false);
//		Head.setTextureOffset(84, 44).addBox(-5.5F, -7.0F, -5.4F, 11.0F, 2.0F, 11.0F, 0.0F, false);
//
//		Head_r1 = new ModelPart(this);
//		Head_r1.setRotation(23.9174F, 13.7529F, -8.3746F);
//		Head.addChild(Head_r1);
//		setRotationAngle(Head_r1, -0.2519F, -0.2443F, -0.7543F);
//		Head_r1.setTextureOffset(118, 0).addBox(-3.0F, -34.8247F, -4.2223F, 4.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Head_r2 = new ModelPart(this);
//		Head_r2.setRotation(23.7406F, 13.9223F, -8.3293F);
//		Head.addChild(Head_r2);
//		setRotationAngle(Head_r2, -0.2519F, -0.2443F, -0.7543F);
//		Head_r2.setTextureOffset(114, 20).addBox(-3.0F, -35.5747F, -3.4723F, 5.0F, 5.0F, 2.0F, 0.0F, false);
//
//		Head_r3 = new ModelPart(this);
//		Head_r3.setRotation(5.0464F, -40.4121F, 17.0143F);
//		Head.addChild(Head_r3);
//		setRotationAngle(Head_r3, 2.5148F, 0.3804F, -0.0759F);
//		Head_r3.setTextureOffset(94, 1).addBox(-7.6194F, -39.0474F, 0.4112F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		Head_r4 = new ModelPart(this);
//		Head_r4.setRotation(27.4391F, -1.0286F, 0.0F);
//		Head.addChild(Head_r4);
//		setRotationAngle(Head_r4, 0.0F, 0.0F, -1.0908F);
//		Head_r4.setTextureOffset(120, 5).addBox(-9.3332F, -33.7448F, -0.5F, 2.0F, 3.0F, 2.0F, 0.0F, true);
//		Head_r4.setTextureOffset(101, 22).addBox(-10.4332F, -32.4948F, -2.0F, 4.0F, 3.0F, 5.0F, 0.0F, true);
//
//		Head_r5 = new ModelPart(this);
//		Head_r5.setRotation(20.9154F, -2.1179F, -20.3936F);
//		Head.addChild(Head_r5);
//		setRotationAngle(Head_r5, -0.5905F, 0.1693F, -1.1386F);
//		Head_r5.setTextureOffset(120, 5).addBox(-9.4623F, -35.5016F, 0.6311F, 2.0F, 2.0F, 2.0F, 0.0F, true);
//
//		Head_r6 = new ModelPart(this);
//		Head_r6.setRotation(20.9802F, 4.3846F, -15.9731F);
//		Head.addChild(Head_r6);
//		setRotationAngle(Head_r6, -0.4375F, 0.6446F, -1.1751F);
//		Head_r6.setTextureOffset(94, 1).addBox(-9.3193F, -36.7539F, 0.751F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		Head_r7 = new ModelPart(this);
//		Head_r7.setRotation(24.0435F, -5.0253F, 17.182F);
//		Head.addChild(Head_r7);
//		setRotationAngle(Head_r7, 0.6237F, 0.3336F, -0.9454F);
//		Head_r7.setTextureOffset(94, 1).addBox(-8.8037F, -36.5606F, -1.1663F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		Head_r8 = new ModelPart(this);
//		Head_r8.setRotation(-17.2726F, -11.4835F, 34.5168F);
//		Head.addChild(Head_r8);
//		setRotationAngle(Head_r8, 1.73F, -0.4507F, -0.5033F);
//		Head_r8.setTextureOffset(94, 4).addBox(-9.3868F, -38.034F, -0.6244F, 1.0F, 3.0F, 1.0F, 0.0F, true);
//
//		Head_r9 = new ModelPart(this);
//		Head_r9.setRotation(-16.7634F, 8.4062F, 28.5896F);
//		Head.addChild(Head_r9);
//		setRotationAngle(Head_r9, 1.3972F, -0.8338F, -0.8103F);
//		Head_r9.setTextureOffset(94, 4).addBox(-10.3247F, -39.0839F, -1.0173F, 1.0F, 3.0F, 1.0F, 0.0F, true);
//
//		Head_r10 = new ModelPart(this);
//		Head_r10.setRotation(-17.9633F, 19.2343F, -15.155F);
//		Head.addChild(Head_r10);
//		setRotationAngle(Head_r10, -0.7248F, -0.6036F, 0.9784F);
//		Head_r10.setTextureOffset(94, 1).addBox(-9.3463F, -35.8338F, -1.0439F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		Head_r11 = new ModelPart(this);
//		Head_r11.setRotation(-27.2873F, 18.5891F, -6.1254F);
//		Head.addChild(Head_r11);
//		setRotationAngle(Head_r11, -0.3758F, -0.6036F, 0.9784F);
//		Head_r11.setTextureOffset(94, 1).addBox(-8.2004F, -35.9889F, -1.0633F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		Head_r12 = new ModelPart(this);
//		Head_r12.setRotation(4.1915F, 22.6667F, -3.8066F);
//		Head.addChild(Head_r12);
//		setRotationAngle(Head_r12, 0.0751F, 0.9219F, -0.1458F);
//		Head_r12.setTextureOffset(90, 2).addBox(-6.0591F, -39.5499F, -2.336F, 1.0F, 5.0F, 1.0F, 0.0F, true);
//
//		Head_r13 = new ModelPart(this);
//		Head_r13.setRotation(-19.5846F, -2.9879F, -32.2164F);
//		Head.addChild(Head_r13);
//		setRotationAngle(Head_r13, -1.5534F, 0.6284F, -0.5469F);
//		Head_r13.setTextureOffset(94, 1).addBox(-9.4547F, -38.7144F, -0.3169F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		Head_r14 = new ModelPart(this);
//		Head_r14.setRotation(-14.6542F, 19.441F, -16.6895F);
//		Head.addChild(Head_r14);
//		setRotationAngle(Head_r14, -0.5079F, 0.8084F, 0.0001F);
//		Head_r14.setTextureOffset(94, 4).addBox(-9.7277F, -37.9719F, 0.7261F, 1.0F, 3.0F, 1.0F, 0.0F, true);
//
//		Head_r15 = new ModelPart(this);
//		Head_r15.setRotation(-29.1114F, -27.9053F, 22.7652F);
//		Head.addChild(Head_r15);
//		setRotationAngle(Head_r15, 1.2092F, -1.0412F, 1.1195F);
//		Head_r15.setTextureOffset(94, 1).addBox(-7.2549F, -35.0303F, 2.0295F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		Head_r16 = new ModelPart(this);
//		Head_r16.setRotation(-13.8446F, -0.5413F, 33.7413F);
//		Head.addChild(Head_r16);
//		setRotationAngle(Head_r16, 1.5973F, -0.555F, -1.0437F);
//		Head_r16.setTextureOffset(90, 2).addBox(-4.5807F, -38.0143F, 0.7931F, 1.0F, 5.0F, 1.0F, 0.0F, true);
//
//		Head_r17 = new ModelPart(this);
//		Head_r17.setRotation(-9.9278F, 24.4381F, 3.017F);
//		Head.addChild(Head_r17);
//		setRotationAngle(Head_r17, 0.5475F, 1.0625F, 0.6509F);
//		Head_r17.setTextureOffset(94, 4).addBox(-9.1623F, -40.2696F, 0.4769F, 1.0F, 3.0F, 1.0F, 0.0F, true);
//
//		Head_r18 = new ModelPart(this);
//		Head_r18.setRotation(27.2274F, -5.6177F, 9.5383F);
//		Head.addChild(Head_r18);
//		setRotationAngle(Head_r18, 0.3235F, 0.2057F, -1.1478F);
//		Head_r18.setTextureOffset(120, 5).addBox(-9.524F, -35.2047F, -1.4286F, 2.0F, 2.0F, 2.0F, 0.0F, true);
//
//		Head_r19 = new ModelPart(this);
//		Head_r19.setRotation(-27.4391F, -1.0286F, 0.0F);
//		Head.addChild(Head_r19);
//		setRotationAngle(Head_r19, 0.0F, 0.0F, 1.0908F);
//		Head_r19.setTextureOffset(120, 5).addBox(7.3332F, -33.7448F, -0.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
//		Head_r19.setTextureOffset(101, 22).addBox(6.4332F, -32.4948F, -2.0F, 4.0F, 3.0F, 5.0F, 0.0F, false);
//
//		Head_r20 = new ModelPart(this);
//		Head_r20.setRotation(-20.9154F, -2.1179F, -20.3936F);
//		Head.addChild(Head_r20);
//		setRotationAngle(Head_r20, -0.5905F, -0.1693F, 1.1386F);
//		Head_r20.setTextureOffset(120, 5).addBox(7.4623F, -35.5016F, 0.6311F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//		Head_r21 = new ModelPart(this);
//		Head_r21.setRotation(-27.2274F, -5.6177F, 9.5383F);
//		Head.addChild(Head_r21);
//		setRotationAngle(Head_r21, 0.3235F, -0.2057F, 1.1478F);
//		Head_r21.setTextureOffset(120, 5).addBox(7.524F, -35.2047F, -1.4286F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//		Head_r22 = new ModelPart(this);
//		Head_r22.setRotation(-24.0435F, -5.0253F, 17.182F);
//		Head.addChild(Head_r22);
//		setRotationAngle(Head_r22, 0.6237F, -0.3336F, 0.9454F);
//		Head_r22.setTextureOffset(94, 1).addBox(7.8037F, -36.5606F, -1.1663F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Head_r23 = new ModelPart(this);
//		Head_r23.setRotation(-20.9802F, 4.3846F, -15.9731F);
//		Head.addChild(Head_r23);
//		setRotationAngle(Head_r23, -0.4375F, -0.6446F, 1.1751F);
//		Head_r23.setTextureOffset(94, 1).addBox(8.3193F, -36.7539F, 0.751F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Head_r24 = new ModelPart(this);
//		Head_r24.setRotation(19.5846F, -2.9879F, -32.2164F);
//		Head.addChild(Head_r24);
//		setRotationAngle(Head_r24, -1.5534F, -0.6284F, 0.5469F);
//		Head_r24.setTextureOffset(94, 1).addBox(8.4547F, -38.7144F, -0.3169F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Head_r25 = new ModelPart(this);
//		Head_r25.setRotation(14.6542F, 19.441F, -16.6895F);
//		Head.addChild(Head_r25);
//		setRotationAngle(Head_r25, -0.5079F, -0.8084F, -0.0001F);
//		Head_r25.setTextureOffset(94, 4).addBox(8.7277F, -37.9719F, 0.7261F, 1.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Head_r26 = new ModelPart(this);
//		Head_r26.setRotation(13.8446F, -0.5413F, 33.7413F);
//		Head.addChild(Head_r26);
//		setRotationAngle(Head_r26, 1.5973F, 0.555F, 1.0437F);
//		Head_r26.setTextureOffset(90, 2).addBox(3.5807F, -38.0143F, 0.7931F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//		Head_r27 = new ModelPart(this);
//		Head_r27.setRotation(9.9278F, 24.4381F, 3.017F);
//		Head.addChild(Head_r27);
//		setRotationAngle(Head_r27, 0.5475F, -1.0625F, -0.6509F);
//		Head_r27.setTextureOffset(94, 4).addBox(8.1623F, -40.2696F, 0.4769F, 1.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Head_r28 = new ModelPart(this);
//		Head_r28.setRotation(29.1114F, -27.9053F, 22.7652F);
//		Head.addChild(Head_r28);
//		setRotationAngle(Head_r28, 1.2092F, 1.0412F, -1.1195F);
//		Head_r28.setTextureOffset(94, 1).addBox(6.2549F, -35.0303F, 2.0295F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Head_r29 = new ModelPart(this);
//		Head_r29.setRotation(17.9633F, 19.2343F, -15.155F);
//		Head.addChild(Head_r29);
//		setRotationAngle(Head_r29, -0.7248F, 0.6036F, -0.9784F);
//		Head_r29.setTextureOffset(94, 1).addBox(8.3463F, -35.8338F, -1.0439F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Head_r30 = new ModelPart(this);
//		Head_r30.setRotation(-4.1915F, 22.6667F, -3.8066F);
//		Head.addChild(Head_r30);
//		setRotationAngle(Head_r30, 0.0751F, -0.9219F, 0.1458F);
//		Head_r30.setTextureOffset(90, 2).addBox(5.0591F, -39.5499F, -2.336F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//		Head_r31 = new ModelPart(this);
//		Head_r31.setRotation(17.2726F, -11.4835F, 34.5168F);
//		Head.addChild(Head_r31);
//		setRotationAngle(Head_r31, 1.73F, 0.4507F, 0.5033F);
//		Head_r31.setTextureOffset(94, 4).addBox(8.3868F, -38.034F, -0.6244F, 1.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Head_r32 = new ModelPart(this);
//		Head_r32.setRotation(-5.0464F, -40.4121F, 17.0143F);
//		Head.addChild(Head_r32);
//		setRotationAngle(Head_r32, 2.5148F, -0.3804F, 0.0759F);
//		Head_r32.setTextureOffset(94, 1).addBox(6.6194F, -39.0474F, 0.4112F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Head_r33 = new ModelPart(this);
//		Head_r33.setRotation(16.7634F, 8.4062F, 28.5896F);
//		Head.addChild(Head_r33);
//		setRotationAngle(Head_r33, 1.3972F, 0.8338F, 0.8103F);
//		Head_r33.setTextureOffset(94, 4).addBox(9.3247F, -39.0839F, -1.0173F, 1.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Head_r34 = new ModelPart(this);
//		Head_r34.setRotation(27.2873F, 18.5891F, -6.1254F);
//		Head.addChild(Head_r34);
//		setRotationAngle(Head_r34, -0.3758F, 0.6036F, -0.9784F);
//		Head_r34.setTextureOffset(94, 1).addBox(7.2004F, -35.9889F, -1.0633F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Body = new ModelPart(this);
//		Body.setRotation(0.0F, 0.0F, 0.0F);
//		Body.setTextureOffset(96, 110).addBox(-5.0F, -1.0F, -3.0F, 10.0F, 12.0F, 6.0F, 0.0F, false);
//		Body.setTextureOffset(96, 110).addBox(-5.0F, -1.3F, -3.0F, 10.0F, 12.0F, 6.0F, -0.5F, false);
//		Body.setTextureOffset(122, 101).addBox(-4.5F, 0.5F, -4.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
//		Body.setTextureOffset(122, 101).addBox(-4.5F, 0.5F, 3.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
//
//		Body_r1 = new ModelPart(this);
//		Body_r1.setRotation(-3.5F, 1.5F, 3.5F);
//		Body.addChild(Body_r1);
//		setRotationAngle(Body_r1, 0.0F, 0.0F, 1.1781F);
//		Body_r1.setTextureOffset(112, 100).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//		Body_r1.setTextureOffset(112, 100).addBox(-1.5F, -1.5F, -7.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Body_r2 = new ModelPart(this);
//		Body_r2.setRotation(-3.5F, 1.5F, 3.5F);
//		Body.addChild(Body_r2);
//		setRotationAngle(Body_r2, 0.0F, 0.0F, 0.3927F);
//		Body_r2.setTextureOffset(112, 100).addBox(-1.5F, -1.5F, -0.75F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//		Body_r2.setTextureOffset(112, 100).addBox(-1.5F, -1.5F, -7.25F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Body_r3 = new ModelPart(this);
//		Body_r3.setRotation(5.0573F, 7.8623F, 2.8318F);
//		Body.addChild(Body_r3);
//		setRotationAngle(Body_r3, -0.6088F, 0.1517F, -1.6538F);
//		Body_r3.setTextureOffset(109, 96).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r4 = new ModelPart(this);
//		Body_r4.setRotation(3.9922F, 10.1929F, 2.85F);
//		Body.addChild(Body_r4);
//		setRotationAngle(Body_r4, -0.4028F, 0.2626F, -1.5957F);
//		Body_r4.setTextureOffset(109, 96).addBox(-1.25F, 0.25F, -0.35F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r5 = new ModelPart(this);
//		Body_r5.setRotation(3.0537F, 9.3264F, 2.8F);
//		Body.addChild(Body_r5);
//		setRotationAngle(Body_r5, 0.0F, 0.2618F, -1.0036F);
//		Body_r5.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r6 = new ModelPart(this);
//		Body_r6.setRotation(3.5824F, 7.2169F, 2.9F);
//		Body.addChild(Body_r6);
//		setRotationAngle(Body_r6, 0.0F, 0.2618F, -1.1781F);
//		Body_r6.setTextureOffset(116, 94).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Body_r7 = new ModelPart(this);
//		Body_r7.setRotation(1.1694F, 5.9657F, 3.0F);
//		Body.addChild(Body_r7);
//		setRotationAngle(Body_r7, 0.0F, 0.2618F, -1.0472F);
//		Body_r7.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r8 = new ModelPart(this);
//		Body_r8.setRotation(0.0239F, 7.0735F, 2.9F);
//		Body.addChild(Body_r8);
//		setRotationAngle(Body_r8, 0.0F, 0.2618F, -0.8727F);
//		Body_r8.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r9 = new ModelPart(this);
//		Body_r9.setRotation(-2.1031F, 4.3527F, 3.0F);
//		Body.addChild(Body_r9);
//		setRotationAngle(Body_r9, 0.0F, 0.2618F, -0.4363F);
//		Body_r9.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r10 = new ModelPart(this);
//		Body_r10.setRotation(-1.3977F, 3.5555F, 3.1F);
//		Body.addChild(Body_r10);
//		setRotationAngle(Body_r10, 0.0F, 0.2618F, -0.6109F);
//		Body_r10.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r11 = new ModelPart(this);
//		Body_r11.setRotation(-1.3403F, 2.6075F, 3.2F);
//		Body.addChild(Body_r11);
//		setRotationAngle(Body_r11, 0.0F, 0.2618F, -0.7854F);
//		Body_r11.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r12 = new ModelPart(this);
//		Body_r12.setRotation(1.689F, 4.5368F, 3.1F);
//		Body.addChild(Body_r12);
//		setRotationAngle(Body_r12, 0.0F, 0.2618F, -1.2217F);
//		Body_r12.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r13 = new ModelPart(this);
//		Body_r13.setRotation(3.7422F, 5.2929F, 3.0F);
//		Body.addChild(Body_r13);
//		setRotationAngle(Body_r13, -0.0901F, 0.2577F, -1.5332F);
//		Body_r13.setTextureOffset(109, 96).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r14 = new ModelPart(this);
//		Body_r14.setRotation(4.4838F, 5.3647F, 2.8393F);
//		Body.addChild(Body_r14);
//		setRotationAngle(Body_r14, -0.475F, 0.1261F, -1.7235F);
//		Body_r14.setTextureOffset(109, 96).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r15 = new ModelPart(this);
//		Body_r15.setRotation(-1.3403F, 2.6075F, -3.2F);
//		Body.addChild(Body_r15);
//		setRotationAngle(Body_r15, 0.0F, -0.2618F, -0.7854F);
//		Body_r15.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r16 = new ModelPart(this);
//		Body_r16.setRotation(1.689F, 4.5368F, -3.1F);
//		Body.addChild(Body_r16);
//		setRotationAngle(Body_r16, 0.0F, -0.2618F, -1.2217F);
//		Body_r16.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r17 = new ModelPart(this);
//		Body_r17.setRotation(5.0573F, 7.8623F, -2.8318F);
//		Body.addChild(Body_r17);
//		setRotationAngle(Body_r17, 0.6088F, -0.1517F, -1.6538F);
//		Body_r17.setTextureOffset(109, 96).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r18 = new ModelPart(this);
//		Body_r18.setRotation(3.9922F, 10.1929F, -2.85F);
//		Body.addChild(Body_r18);
//		setRotationAngle(Body_r18, 0.4028F, -0.2626F, -1.5957F);
//		Body_r18.setTextureOffset(109, 96).addBox(-1.25F, 0.25F, -0.65F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r19 = new ModelPart(this);
//		Body_r19.setRotation(4.4838F, 5.3647F, -2.8393F);
//		Body.addChild(Body_r19);
//		setRotationAngle(Body_r19, 0.475F, -0.1261F, -1.7235F);
//		Body_r19.setTextureOffset(109, 96).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r20 = new ModelPart(this);
//		Body_r20.setRotation(3.7422F, 5.2929F, -3.0F);
//		Body.addChild(Body_r20);
//		setRotationAngle(Body_r20, 0.0901F, -0.2577F, -1.5332F);
//		Body_r20.setTextureOffset(109, 96).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//		Body_r21 = new ModelPart(this);
//		Body_r21.setRotation(9.7795F, 9.0349F, 0.2F);
//		Body.addChild(Body_r21);
//		setRotationAngle(Body_r21, 0.0F, 0.0F, -0.2618F);
//		Body_r21.setTextureOffset(114, 98).addBox(-5.5F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
//
//		Body_r22 = new ModelPart(this);
//		Body_r22.setRotation(10.0295F, 6.6349F, 0.2F);
//		Body.addChild(Body_r22);
//		setRotationAngle(Body_r22, 0.0F, 0.0F, -0.2618F);
//		Body_r22.setTextureOffset(114, 98).addBox(-5.5F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
//
//		Body_r23 = new ModelPart(this);
//		Body_r23.setRotation(4.9999F, 6.779F, 0.0F);
//		Body.addChild(Body_r23);
//		setRotationAngle(Body_r23, 0.0F, 0.0F, -0.48F);
//		Body_r23.setTextureOffset(114, 98).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, true);
//
//		Body_r24 = new ModelPart(this);
//		Body_r24.setRotation(4.9999F, 9.179F, 0.0F);
//		Body.addChild(Body_r24);
//		setRotationAngle(Body_r24, 0.0F, 0.0F, -0.48F);
//		Body_r24.setTextureOffset(114, 98).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, true);
//
//		Body_r25 = new ModelPart(this);
//		Body_r25.setRotation(9.7795F, 4.1849F, 0.2F);
//		Body.addChild(Body_r25);
//		setRotationAngle(Body_r25, 0.0F, 0.0F, -0.2618F);
//		Body_r25.setTextureOffset(114, 98).addBox(-5.5F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
//
//		Body_r26 = new ModelPart(this);
//		Body_r26.setRotation(3.5824F, 7.2169F, -2.9F);
//		Body.addChild(Body_r26);
//		setRotationAngle(Body_r26, 0.0F, -0.2618F, -1.1781F);
//		Body_r26.setTextureOffset(116, 94).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
//
//		Body_r27 = new ModelPart(this);
//		Body_r27.setRotation(1.1694F, 5.9657F, -3.0F);
//		Body.addChild(Body_r27);
//		setRotationAngle(Body_r27, 0.0F, -0.2618F, -1.0472F);
//		Body_r27.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r28 = new ModelPart(this);
//		Body_r28.setRotation(-1.3977F, 3.5555F, -3.1F);
//		Body.addChild(Body_r28);
//		setRotationAngle(Body_r28, 0.0F, -0.2618F, -0.6109F);
//		Body_r28.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r29 = new ModelPart(this);
//		Body_r29.setRotation(3.0537F, 9.3264F, -2.8F);
//		Body.addChild(Body_r29);
//		setRotationAngle(Body_r29, 0.0F, -0.2618F, -1.0036F);
//		Body_r29.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r30 = new ModelPart(this);
//		Body_r30.setRotation(0.0239F, 7.0735F, -2.9F);
//		Body.addChild(Body_r30);
//		setRotationAngle(Body_r30, 0.0F, -0.2618F, -0.8727F);
//		Body_r30.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		Body_r31 = new ModelPart(this);
//		Body_r31.setRotation(-2.1031F, 4.3527F, -3.0F);
//		Body.addChild(Body_r31);
//		setRotationAngle(Body_r31, 0.0F, -0.2618F, -0.4363F);
//		Body_r31.setTextureOffset(122, 83).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//
//		RightArm = new ModelPart(this);
//		RightArm.setRotation(-5.0F, 2.0F, 0.0F);
//		RightArm.setTextureOffset(48, 112).addBox(-3.65F, 6.7F, -2.4F, 5.0F, 1.0F, 5.0F, 0.0F, false);
//		RightArm.setTextureOffset(48, 112).addBox(-3.65F, 7.95F, -2.4F, 5.0F, 1.0F, 5.0F, 0.0F, false);
//		RightArm.setTextureOffset(63, 93).addBox(-3.65F, 0.45F, -2.4F, 5.0F, 6.0F, 5.0F, 0.0F, false);
//
//		RightArm_r1 = new ModelPart(this);
//		RightArm_r1.setRotation(-2.8834F, -5.1284F, -1.1755F);
//		RightArm.addChild(RightArm_r1);
//		setRotationAngle(RightArm_r1, 0.8172F, -0.8349F, 1.3677F);
//		RightArm_r1.setTextureOffset(49, 118).addBox(-0.3356F, -0.5979F, -0.661F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r2 = new ModelPart(this);
//		RightArm_r2.setRotation(-2.2366F, -6.9338F, 0.1F);
//		RightArm.addChild(RightArm_r2);
//		setRotationAngle(RightArm_r2, 0.6001F, 0.083F, 0.7782F);
//		RightArm_r2.setTextureOffset(49, 114).addBox(0.683F, -1.1041F, -0.9313F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r3 = new ModelPart(this);
//		RightArm_r3.setRotation(-5.5764F, -3.6083F, 0.1F);
//		RightArm.addChild(RightArm_r3);
//		setRotationAngle(RightArm_r3, -0.3494F, 0.5119F, -1.294F);
//		RightArm_r3.setTextureOffset(49, 114).addBox(-0.6969F, -2.3208F, -0.3293F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r4 = new ModelPart(this);
//		RightArm_r4.setRotation(-2.2366F, -6.9338F, 0.1F);
//		RightArm.addChild(RightArm_r4);
//		setRotationAngle(RightArm_r4, -0.48F, 0.0F, -0.6108F);
//		RightArm_r4.setTextureOffset(49, 114).addBox(-1.3397F, -0.9403F, 0.0948F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r5 = new ModelPart(this);
//		RightArm_r5.setRotation(-8.2072F, -0.6112F, 0.1F);
//		RightArm.addChild(RightArm_r5);
//		setRotationAngle(RightArm_r5, -0.2613F, -0.3535F, -1.8467F);
//		RightArm_r5.setTextureOffset(49, 114).addBox(-0.5866F, -1.9747F, -0.1759F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r6 = new ModelPart(this);
//		RightArm_r6.setRotation(-5.5764F, -3.6083F, 0.1F);
//		RightArm.addChild(RightArm_r6);
//		setRotationAngle(RightArm_r6, 0.49F, -0.1925F, 0.0826F);
//		RightArm_r6.setTextureOffset(49, 114).addBox(-0.6969F, -2.3208F, -0.3293F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r7 = new ModelPart(this);
//		RightArm_r7.setRotation(-20.6804F, 13.3622F, -14.9881F);
//		RightArm.addChild(RightArm_r7);
//		setRotationAngle(RightArm_r7, -0.4784F, 0.9598F, 0.5456F);
//		RightArm_r7.setTextureOffset(49, 121).addBox(-13.2349F, -25.5859F, 0.0713F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r8 = new ModelPart(this);
//		RightArm_r8.setRotation(-5.5963F, -4.094F, -1.7989F);
//		RightArm.addChild(RightArm_r8);
//		setRotationAngle(RightArm_r8, -0.6543F, -0.0841F, 1.7366F);
//		RightArm_r8.setTextureOffset(49, 121).addBox(-0.2127F, -0.623F, -0.2454F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r9 = new ModelPart(this);
//		RightArm_r9.setRotation(-5.372F, -4.9876F, 0.1F);
//		RightArm.addChild(RightArm_r9);
//		setRotationAngle(RightArm_r9, 2.9358F, 0.284F, 2.4575F);
//		RightArm_r9.setTextureOffset(49, 118).addBox(0.7445F, -2.2562F, -0.0691F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r10 = new ModelPart(this);
//		RightArm_r10.setRotation(-4.8632F, -5.0215F, 0.1F);
//		RightArm.addChild(RightArm_r10);
//		setRotationAngle(RightArm_r10, 0.0F, 0.0F, 0.6109F);
//		RightArm_r10.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r11 = new ModelPart(this);
//		RightArm_r11.setRotation(-4.5807F, -5.9779F, 0.1F);
//		RightArm.addChild(RightArm_r11);
//		setRotationAngle(RightArm_r11, -1.7303F, -1.3635F, 1.8882F);
//		RightArm_r11.setTextureOffset(49, 121).addBox(0.234F, -0.6524F, -0.5245F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r12 = new ModelPart(this);
//		RightArm_r12.setRotation(-4.8759F, -3.4953F, 1.1442F);
//		RightArm.addChild(RightArm_r12);
//		setRotationAngle(RightArm_r12, -1.5319F, -0.2222F, -0.1793F);
//		RightArm_r12.setTextureOffset(49, 118).addBox(-2.3736F, -2.1501F, -1.5921F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r13 = new ModelPart(this);
//		RightArm_r13.setRotation(-1.4726F, 10.3955F, 23.7926F);
//		RightArm.addChild(RightArm_r13);
//		setRotationAngle(RightArm_r13, 0.9675F, -0.3481F, -0.1011F);
//		RightArm_r13.setTextureOffset(49, 118).addBox(-10.6419F, -27.7578F, -1.1089F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r14 = new ModelPart(this);
//		RightArm_r14.setRotation(11.485F, 18.5535F, 0.0F);
//		RightArm.addChild(RightArm_r14);
//		setRotationAngle(RightArm_r14, 0.0F, 0.0F, -0.2618F);
//		RightArm_r14.setTextureOffset(49, 114).addBox(-11.2441F, -26.8225F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r15 = new ModelPart(this);
//		RightArm_r15.setRotation(-10.978F, 20.7615F, -14.6449F);
//		RightArm.addChild(RightArm_r15);
//		setRotationAngle(RightArm_r15, -0.2911F, 1.0075F, 0.168F);
//		RightArm_r15.setTextureOffset(49, 121).addBox(-14.3631F, -24.7777F, 0.7084F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r16 = new ModelPart(this);
//		RightArm_r16.setRotation(-9.5266F, 0.0855F, -0.5017F);
//		RightArm.addChild(RightArm_r16);
//		setRotationAngle(RightArm_r16, -1.129F, -0.9288F, 0.6326F);
//		RightArm_r16.setTextureOffset(49, 121).addBox(-0.2345F, 0.0797F, -0.1051F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r17 = new ModelPart(this);
//		RightArm_r17.setRotation(-10.4201F, -1.8175F, 0.1F);
//		RightArm.addChild(RightArm_r17);
//		setRotationAngle(RightArm_r17, 0.3927F, -0.8727F, -1.6581F);
//		RightArm_r17.setTextureOffset(49, 118).addBox(-0.886F, -0.4982F, 1.053F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r18 = new ModelPart(this);
//		RightArm_r18.setRotation(-8.6049F, -2.0368F, 0.1F);
//		RightArm.addChild(RightArm_r18);
//		setRotationAngle(RightArm_r18, 0.3736F, -0.7347F, -0.0928F);
//		RightArm_r18.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r19 = new ModelPart(this);
//		RightArm_r19.setRotation(-8.8757F, -2.9463F, 0.1F);
//		RightArm.addChild(RightArm_r19);
//		setRotationAngle(RightArm_r19, 0.9911F, -0.6932F, -0.3895F);
//		RightArm_r19.setTextureOffset(49, 121).addBox(-0.354F, -0.8549F, -0.1795F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r20 = new ModelPart(this);
//		RightArm_r20.setRotation(0.6114F, 9.7252F, -22.6236F);
//		RightArm.addChild(RightArm_r20);
//		setRotationAngle(RightArm_r20, -0.9013F, 0.602F, -0.6409F);
//		RightArm_r20.setTextureOffset(49, 118).addBox(-14.0613F, -24.5251F, 0.6084F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r21 = new ModelPart(this);
//		RightArm_r21.setRotation(-7.8977F, -1.0763F, -0.6425F);
//		RightArm.addChild(RightArm_r21);
//		setRotationAngle(RightArm_r21, -0.8326F, -0.4531F, 1.7451F);
//		RightArm_r21.setTextureOffset(49, 118).addBox(1.242F, -0.1306F, 0.0216F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r22 = new ModelPart(this);
//		RightArm_r22.setRotation(18.6707F, 1.7755F, 0.0F);
//		RightArm.addChild(RightArm_r22);
//		setRotationAngle(RightArm_r22, 0.0F, 0.0F, -0.9337F);
//		RightArm_r22.setTextureOffset(49, 114).addBox(-14.5702F, -24.0249F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r23 = new ModelPart(this);
//		RightArm_r23.setRotation(-2.8834F, -5.3784F, 1.6255F);
//		RightArm.addChild(RightArm_r23);
//		setRotationAngle(RightArm_r23, -0.7751F, 0.3681F, 0.309F);
//		RightArm_r23.setTextureOffset(49, 121).addBox(-1.1149F, -1.1652F, -0.7334F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightArm_r24 = new ModelPart(this);
//		RightArm_r24.setRotation(-2.1018F, -6.9094F, 0.1F);
//		RightArm.addChild(RightArm_r24);
//		setRotationAngle(RightArm_r24, -0.263F, 0.5086F, 0.4112F);
//		RightArm_r24.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r25 = new ModelPart(this);
//		RightArm_r25.setRotation(-1.1067F, -6.8069F, -0.15F);
//		RightArm.addChild(RightArm_r25);
//		setRotationAngle(RightArm_r25, 0.7484F, -1.1436F, -1.5956F);
//		RightArm_r25.setTextureOffset(49, 118).addBox(-0.9463F, -1.7479F, -0.4313F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r26 = new ModelPart(this);
//		RightArm_r26.setRotation(-1.612F, -5.0083F, 1.0969F);
//		RightArm.addChild(RightArm_r26);
//		setRotationAngle(RightArm_r26, -1.1108F, -0.4842F, -0.5326F);
//		RightArm_r26.setTextureOffset(49, 118).addBox(-0.7709F, -2.2982F, -1.5266F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r27 = new ModelPart(this);
//		RightArm_r27.setRotation(-1.612F, -5.0083F, -0.8969F);
//		RightArm.addChild(RightArm_r27);
//		setRotationAngle(RightArm_r27, 2.0644F, -0.0468F, 1.3657F);
//		RightArm_r27.setTextureOffset(49, 118).addBox(-0.9245F, -0.9814F, -1.5639F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r28 = new ModelPart(this);
//		RightArm_r28.setRotation(6.1965F, 21.6987F, 0.0F);
//		RightArm.addChild(RightArm_r28);
//		setRotationAngle(RightArm_r28, 0.0F, 0.0F, -0.0436F);
//		RightArm_r28.setTextureOffset(49, 114).addBox(-7.7642F, -27.9748F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightArm_r29 = new ModelPart(this);
//		RightArm_r29.setRotation(18.167F, -14.8747F, 0.0F);
//		RightArm.addChild(RightArm_r29);
//		setRotationAngle(RightArm_r29, 0.0F, 0.0F, -1.6581F);
//		RightArm_r29.setTextureOffset(40, 119).addBox(-10.7106F, -24.9698F, -1.401F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		RightArm_r30 = new ModelPart(this);
//		RightArm_r30.setRotation(12.5234F, -15.6373F, 0.0F);
//		RightArm.addChild(RightArm_r30);
//		setRotationAngle(RightArm_r30, 0.0F, 0.0F, -1.7191F);
//		RightArm_r30.setTextureOffset(40, 119).addBox(-12.9543F, -22.5601F, -1.401F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		RightArm_r31 = new ModelPart(this);
//		RightArm_r31.setRotation(29.455F, 23.3209F, 0.0F);
//		RightArm.addChild(RightArm_r31);
//		setRotationAngle(RightArm_r31, 0.0F, 0.0F, -1.021F);
//		RightArm_r31.setTextureOffset(36, 123).addBox(-0.3471F, -43.7482F, -1.9F, 2.0F, 1.0F, 4.0F, 0.0F, false);
//
//		RightArm_r32 = new ModelPart(this);
//		RightArm_r32.setRotation(19.7199F, 6.8245F, 0.0F);
//		RightArm.addChild(RightArm_r32);
//		setRotationAngle(RightArm_r32, 0.0F, 0.0F, -0.829F);
//		RightArm_r32.setTextureOffset(36, 123).addBox(-10.8853F, -24.8152F, -1.9F, 2.0F, 1.0F, 4.0F, 0.0F, false);
//
//		RightArm_r33 = new ModelPart(this);
//		RightArm_r33.setRotation(13.4237F, 14.2147F, 0.0F);
//		RightArm.addChild(RightArm_r33);
//		setRotationAngle(RightArm_r33, 0.0F, 0.0F, -0.5236F);
//		RightArm_r33.setTextureOffset(41, 108).addBox(-10.5657F, -20.3615F, -1.4F, 1.0F, 4.0F, 3.0F, 0.0F, false);
//
//		RightArm_r34 = new ModelPart(this);
//		RightArm_r34.setRotation(14.4252F, 4.1753F, -8.8474F);
//		RightArm.addChild(RightArm_r34);
//		setRotationAngle(RightArm_r34, -0.0011F, 0.7939F, -1.0584F);
//		RightArm_r34.setTextureOffset(38, 115).addBox(-13.469F, -20.8846F, -0.837F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//
//		RightArm_r35 = new ModelPart(this);
//		RightArm_r35.setRotation(20.0929F, 28.3552F, 0.0F);
//		RightArm.addChild(RightArm_r35);
//		setRotationAngle(RightArm_r35, 0.0F, 0.0F, -0.8727F);
//		RightArm_r35.setTextureOffset(46, 99).addBox(2.8105F, -39.0551F, -2.4F, 6.0F, 1.0F, 5.0F, 0.0F, false);
//		RightArm_r35.setTextureOffset(68, 117).addBox(3.5605F, -38.6551F, -2.9F, 5.0F, 5.0F, 6.0F, 0.0F, false);
//
//		RightArm_r36 = new ModelPart(this);
//		RightArm_r36.setRotation(24.4784F, 1.4498F, 0.0F);
//		RightArm.addChild(RightArm_r36);
//		setRotationAngle(RightArm_r36, 0.0F, 0.0F, -1.0908F);
//		RightArm_r36.setTextureOffset(40, 119).addBox(-7.5942F, -26.9254F, -1.401F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		RightArm_r37 = new ModelPart(this);
//		RightArm_r37.setRotation(13.7239F, 17.5811F, 0.0F);
//		RightArm.addChild(RightArm_r37);
//		setRotationAngle(RightArm_r37, 0.0F, 0.0F, -0.3491F);
//		RightArm_r37.setTextureOffset(36, 123).addBox(-8.5324F, -26.1959F, -1.9F, 2.0F, 1.0F, 4.0F, 0.0F, false);
//
//		RightArm_r38 = new ModelPart(this);
//		RightArm_r38.setRotation(5.5898F, 23.4505F, 0.0F);
//		RightArm.addChild(RightArm_r38);
//		setRotationAngle(RightArm_r38, 0.0F, 0.0F, -0.2618F);
//		RightArm_r38.setTextureOffset(63, 105).addBox(-2.4461F, -28.0648F, -3.4F, 5.0F, 5.0F, 7.0F, 0.0F, false);
//		RightArm_r38.setTextureOffset(49, 105).addBox(-2.4461F, -28.5648F, -2.9F, 4.0F, 1.0F, 6.0F, 0.0F, false);
//
//		LeftArm = new ModelPart(this);
//		LeftArm.setRotation(5.0F, 2.0F, 0.0F);
//		LeftArm.setTextureOffset(63, 93).addBox(-1.1F, 0.45F, -2.4F, 5.0F, 6.0F, 5.0F, 0.0F, true);
//		LeftArm.setTextureOffset(48, 112).addBox(-1.15F, 6.7F, -2.4F, 5.0F, 1.0F, 5.0F, 0.0F, false);
//		LeftArm.setTextureOffset(48, 112).addBox(-1.15F, 7.95F, -2.4F, 5.0F, 1.0F, 5.0F, 0.0F, false);
//
//		LeftArm_r1 = new ModelPart(this);
//		LeftArm_r1.setRotation(20.6804F, 13.3622F, -14.9881F);
//		LeftArm.addChild(LeftArm_r1);
//		setRotationAngle(LeftArm_r1, -0.4784F, -0.9598F, -0.5456F);
//		LeftArm_r1.setTextureOffset(49, 121).addBox(12.2349F, -25.5859F, 0.0713F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r2 = new ModelPart(this);
//		LeftArm_r2.setRotation(5.5963F, -4.094F, -1.7989F);
//		LeftArm.addChild(LeftArm_r2);
//		setRotationAngle(LeftArm_r2, -0.6543F, 0.0841F, -1.7366F);
//		LeftArm_r2.setTextureOffset(49, 121).addBox(-0.7873F, -0.623F, -0.2454F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r3 = new ModelPart(this);
//		LeftArm_r3.setRotation(5.372F, -4.9876F, 0.1F);
//		LeftArm.addChild(LeftArm_r3);
//		setRotationAngle(LeftArm_r3, 2.9358F, -0.284F, -2.4575F);
//		LeftArm_r3.setTextureOffset(49, 118).addBox(-1.7445F, -2.2562F, -0.0691F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r4 = new ModelPart(this);
//		LeftArm_r4.setRotation(4.8632F, -5.0215F, 0.1F);
//		LeftArm.addChild(LeftArm_r4);
//		setRotationAngle(LeftArm_r4, 0.0F, 0.0F, -0.6109F);
//		LeftArm_r4.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r5 = new ModelPart(this);
//		LeftArm_r5.setRotation(4.5807F, -5.9779F, 0.1F);
//		LeftArm.addChild(LeftArm_r5);
//		setRotationAngle(LeftArm_r5, -1.7303F, 1.3635F, -1.8882F);
//		LeftArm_r5.setTextureOffset(49, 121).addBox(-1.234F, -0.6524F, -0.5245F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r6 = new ModelPart(this);
//		LeftArm_r6.setRotation(4.8759F, -3.4953F, 1.1442F);
//		LeftArm.addChild(LeftArm_r6);
//		setRotationAngle(LeftArm_r6, -1.5319F, 0.2222F, 0.1793F);
//		LeftArm_r6.setTextureOffset(49, 118).addBox(1.3736F, -2.1501F, -1.5921F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r7 = new ModelPart(this);
//		LeftArm_r7.setRotation(1.4726F, 10.3955F, 23.7926F);
//		LeftArm.addChild(LeftArm_r7);
//		setRotationAngle(LeftArm_r7, 0.9675F, 0.3481F, 0.1011F);
//		LeftArm_r7.setTextureOffset(49, 118).addBox(9.6419F, -27.7578F, -1.1089F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r8 = new ModelPart(this);
//		LeftArm_r8.setRotation(-11.485F, 18.5535F, 0.0F);
//		LeftArm.addChild(LeftArm_r8);
//		setRotationAngle(LeftArm_r8, 0.0F, 0.0F, 0.2618F);
//		LeftArm_r8.setTextureOffset(49, 114).addBox(10.2441F, -26.8225F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r9 = new ModelPart(this);
//		LeftArm_r9.setRotation(5.5764F, -3.6083F, 0.1F);
//		LeftArm.addChild(LeftArm_r9);
//		setRotationAngle(LeftArm_r9, 0.49F, 0.1925F, -0.0826F);
//		LeftArm_r9.setTextureOffset(49, 114).addBox(-0.3031F, -2.3208F, -0.3293F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r10 = new ModelPart(this);
//		LeftArm_r10.setRotation(5.5764F, -3.6083F, 0.1F);
//		LeftArm.addChild(LeftArm_r10);
//		setRotationAngle(LeftArm_r10, -0.3494F, -0.5119F, 1.294F);
//		LeftArm_r10.setTextureOffset(49, 114).addBox(-0.3031F, -2.3208F, -0.3293F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r11 = new ModelPart(this);
//		LeftArm_r11.setRotation(-18.167F, -14.8747F, 0.0F);
//		LeftArm.addChild(LeftArm_r11);
//		setRotationAngle(LeftArm_r11, 0.0F, 0.0F, 1.6581F);
//		LeftArm_r11.setTextureOffset(40, 119).addBox(9.7106F, -24.9698F, -1.401F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		LeftArm_r12 = new ModelPart(this);
//		LeftArm_r12.setRotation(10.978F, 20.7615F, -14.6449F);
//		LeftArm.addChild(LeftArm_r12);
//		setRotationAngle(LeftArm_r12, -0.2911F, -1.0075F, -0.168F);
//		LeftArm_r12.setTextureOffset(49, 121).addBox(13.3631F, -24.7777F, 0.7084F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r13 = new ModelPart(this);
//		LeftArm_r13.setRotation(9.5266F, 0.0855F, -0.5017F);
//		LeftArm.addChild(LeftArm_r13);
//		setRotationAngle(LeftArm_r13, -1.129F, 0.9288F, -0.6326F);
//		LeftArm_r13.setTextureOffset(49, 121).addBox(-0.7655F, 0.0797F, -0.1051F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r14 = new ModelPart(this);
//		LeftArm_r14.setRotation(10.4201F, -1.8175F, 0.1F);
//		LeftArm.addChild(LeftArm_r14);
//		setRotationAngle(LeftArm_r14, 0.3927F, 0.8727F, 1.6581F);
//		LeftArm_r14.setTextureOffset(49, 118).addBox(-0.114F, -0.4982F, 1.053F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r15 = new ModelPart(this);
//		LeftArm_r15.setRotation(8.6049F, -2.0368F, 0.1F);
//		LeftArm.addChild(LeftArm_r15);
//		setRotationAngle(LeftArm_r15, 0.3736F, 0.7347F, 0.0928F);
//		LeftArm_r15.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r16 = new ModelPart(this);
//		LeftArm_r16.setRotation(8.8757F, -2.9463F, 0.1F);
//		LeftArm.addChild(LeftArm_r16);
//		setRotationAngle(LeftArm_r16, 0.9911F, 0.6932F, 0.3895F);
//		LeftArm_r16.setTextureOffset(49, 121).addBox(-0.646F, -0.8549F, -0.1795F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r17 = new ModelPart(this);
//		LeftArm_r17.setRotation(-0.6114F, 9.7252F, -22.6236F);
//		LeftArm.addChild(LeftArm_r17);
//		setRotationAngle(LeftArm_r17, -0.9013F, -0.602F, 0.6409F);
//		LeftArm_r17.setTextureOffset(49, 118).addBox(13.0613F, -24.5251F, 0.6084F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r18 = new ModelPart(this);
//		LeftArm_r18.setRotation(7.8977F, -1.0763F, -0.6425F);
//		LeftArm.addChild(LeftArm_r18);
//		setRotationAngle(LeftArm_r18, -0.8326F, 0.4531F, -1.7451F);
//		LeftArm_r18.setTextureOffset(49, 118).addBox(-2.242F, -0.1306F, 0.0216F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r19 = new ModelPart(this);
//		LeftArm_r19.setRotation(-18.6707F, 1.7755F, 0.0F);
//		LeftArm.addChild(LeftArm_r19);
//		setRotationAngle(LeftArm_r19, 0.0F, 0.0F, 0.9337F);
//		LeftArm_r19.setTextureOffset(49, 114).addBox(13.5702F, -24.0249F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r20 = new ModelPart(this);
//		LeftArm_r20.setRotation(8.2072F, -0.6112F, 0.1F);
//		LeftArm.addChild(LeftArm_r20);
//		setRotationAngle(LeftArm_r20, -0.2613F, 0.3535F, 1.8467F);
//		LeftArm_r20.setTextureOffset(49, 114).addBox(-0.4134F, -1.9747F, -0.1759F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r21 = new ModelPart(this);
//		LeftArm_r21.setRotation(-12.5234F, -15.6373F, 0.0F);
//		LeftArm.addChild(LeftArm_r21);
//		setRotationAngle(LeftArm_r21, 0.0F, 0.0F, 1.7191F);
//		LeftArm_r21.setTextureOffset(40, 119).addBox(11.9543F, -22.5601F, -1.401F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		LeftArm_r22 = new ModelPart(this);
//		LeftArm_r22.setRotation(-29.455F, 23.3209F, 0.0F);
//		LeftArm.addChild(LeftArm_r22);
//		setRotationAngle(LeftArm_r22, 0.0F, 0.0F, 1.021F);
//		LeftArm_r22.setTextureOffset(36, 123).addBox(-1.6529F, -43.7482F, -1.9F, 2.0F, 1.0F, 4.0F, 0.0F, true);
//
//		LeftArm_r23 = new ModelPart(this);
//		LeftArm_r23.setRotation(-19.7199F, 6.8245F, 0.0F);
//		LeftArm.addChild(LeftArm_r23);
//		setRotationAngle(LeftArm_r23, 0.0F, 0.0F, 0.829F);
//		LeftArm_r23.setTextureOffset(36, 123).addBox(8.8853F, -24.8152F, -1.9F, 2.0F, 1.0F, 4.0F, 0.0F, true);
//
//		LeftArm_r24 = new ModelPart(this);
//		LeftArm_r24.setRotation(-13.4237F, 14.2147F, 0.0F);
//		LeftArm.addChild(LeftArm_r24);
//		setRotationAngle(LeftArm_r24, 0.0F, 0.0F, 0.5236F);
//		LeftArm_r24.setTextureOffset(41, 108).addBox(9.5657F, -20.3615F, -1.4F, 1.0F, 4.0F, 3.0F, 0.0F, true);
//
//		LeftArm_r25 = new ModelPart(this);
//		LeftArm_r25.setRotation(-14.4252F, 4.1753F, -8.8474F);
//		LeftArm.addChild(LeftArm_r25);
//		setRotationAngle(LeftArm_r25, -0.0011F, -0.7939F, 1.0584F);
//		LeftArm_r25.setTextureOffset(38, 115).addBox(10.469F, -20.8846F, -0.837F, 3.0F, 1.0F, 3.0F, 0.0F, true);
//
//		LeftArm_r26 = new ModelPart(this);
//		LeftArm_r26.setRotation(-20.0929F, 28.3552F, 0.0F);
//		LeftArm.addChild(LeftArm_r26);
//		setRotationAngle(LeftArm_r26, 0.0F, 0.0F, 0.8727F);
//		LeftArm_r26.setTextureOffset(46, 99).addBox(-8.8105F, -39.0551F, -2.4F, 6.0F, 1.0F, 5.0F, 0.0F, true);
//		LeftArm_r26.setTextureOffset(68, 117).addBox(-8.5605F, -38.6551F, -2.9F, 5.0F, 5.0F, 6.0F, 0.0F, true);
//
//		LeftArm_r27 = new ModelPart(this);
//		LeftArm_r27.setRotation(2.8834F, -5.3784F, 1.6255F);
//		LeftArm.addChild(LeftArm_r27);
//		setRotationAngle(LeftArm_r27, -0.7751F, -0.3681F, -0.309F);
//		LeftArm_r27.setTextureOffset(49, 121).addBox(0.1149F, -1.1652F, -0.7334F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r28 = new ModelPart(this);
//		LeftArm_r28.setRotation(2.8834F, -5.1284F, -1.1755F);
//		LeftArm.addChild(LeftArm_r28);
//		setRotationAngle(LeftArm_r28, 0.8172F, 0.8349F, -1.3677F);
//		LeftArm_r28.setTextureOffset(49, 118).addBox(-0.6644F, -0.5979F, -0.661F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r29 = new ModelPart(this);
//		LeftArm_r29.setRotation(2.1018F, -6.9094F, 0.1F);
//		LeftArm.addChild(LeftArm_r29);
//		setRotationAngle(LeftArm_r29, -0.263F, -0.5086F, -0.4112F);
//		LeftArm_r29.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r30 = new ModelPart(this);
//		LeftArm_r30.setRotation(1.1067F, -6.8069F, -0.15F);
//		LeftArm.addChild(LeftArm_r30);
//		setRotationAngle(LeftArm_r30, 0.7484F, 1.1436F, 1.5956F);
//		LeftArm_r30.setTextureOffset(49, 118).addBox(-0.0537F, -1.7479F, -0.4313F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r31 = new ModelPart(this);
//		LeftArm_r31.setRotation(1.612F, -5.0083F, 1.0969F);
//		LeftArm.addChild(LeftArm_r31);
//		setRotationAngle(LeftArm_r31, -1.1108F, 0.4842F, 0.5326F);
//		LeftArm_r31.setTextureOffset(49, 118).addBox(-0.2291F, -2.2982F, -1.5266F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r32 = new ModelPart(this);
//		LeftArm_r32.setRotation(1.612F, -5.0083F, -0.8969F);
//		LeftArm.addChild(LeftArm_r32);
//		setRotationAngle(LeftArm_r32, 2.0644F, 0.0468F, -1.3657F);
//		LeftArm_r32.setTextureOffset(49, 118).addBox(-0.0755F, -0.9814F, -1.5639F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r33 = new ModelPart(this);
//		LeftArm_r33.setRotation(-6.1965F, 21.6987F, 0.0F);
//		LeftArm.addChild(LeftArm_r33);
//		setRotationAngle(LeftArm_r33, 0.0F, 0.0F, 0.0436F);
//		LeftArm_r33.setTextureOffset(49, 114).addBox(6.7642F, -27.9748F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r34 = new ModelPart(this);
//		LeftArm_r34.setRotation(2.2366F, -6.9338F, 0.1F);
//		LeftArm.addChild(LeftArm_r34);
//		setRotationAngle(LeftArm_r34, -0.48F, 0.0F, 0.6108F);
//		LeftArm_r34.setTextureOffset(49, 114).addBox(0.3397F, -0.9403F, 0.0948F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r35 = new ModelPart(this);
//		LeftArm_r35.setRotation(2.2366F, -6.9338F, 0.1F);
//		LeftArm.addChild(LeftArm_r35);
//		setRotationAngle(LeftArm_r35, 0.6001F, -0.083F, -0.7782F);
//		LeftArm_r35.setTextureOffset(49, 114).addBox(-1.683F, -1.1041F, -0.9313F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftArm_r36 = new ModelPart(this);
//		LeftArm_r36.setRotation(-24.4784F, 1.4498F, 0.0F);
//		LeftArm.addChild(LeftArm_r36);
//		setRotationAngle(LeftArm_r36, 0.0F, 0.0F, 1.0908F);
//		LeftArm_r36.setTextureOffset(40, 119).addBox(6.5942F, -26.9254F, -1.401F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		LeftArm_r37 = new ModelPart(this);
//		LeftArm_r37.setRotation(-13.7239F, 17.5811F, 0.0F);
//		LeftArm.addChild(LeftArm_r37);
//		setRotationAngle(LeftArm_r37, 0.0F, 0.0F, 0.3491F);
//		LeftArm_r37.setTextureOffset(36, 123).addBox(6.5324F, -26.1959F, -1.9F, 2.0F, 1.0F, 4.0F, 0.0F, true);
//
//		LeftArm_r38 = new ModelPart(this);
//		LeftArm_r38.setRotation(-5.5898F, 23.4505F, 0.0F);
//		LeftArm.addChild(LeftArm_r38);
//		setRotationAngle(LeftArm_r38, 0.0F, 0.0F, 0.2618F);
//		LeftArm_r38.setTextureOffset(49, 105).addBox(-1.5539F, -28.5648F, -2.9F, 4.0F, 1.0F, 6.0F, 0.0F, true);
//		LeftArm_r38.setTextureOffset(63, 105).addBox(-2.5539F, -28.0648F, -3.4F, 5.0F, 5.0F, 7.0F, 0.0F, true);
//
//		Belt = new ModelPart(this);
//		Belt.setRotation(0.0F, 0.0F, 0.0F);
//		Belt.setTextureOffset(0, 86).addBox(-5.5F, 10.0F, -3.5F, 11.0F, 2.0F, 7.0F, 0.0F, false);
//
//		Belt_r1 = new ModelPart(this);
//		Belt_r1.setRotation(5.8833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r1);
//		setRotationAngle(Belt_r1, -1.2654F, 0.0F, -0.3054F);
//		Belt_r1.setTextureOffset(0, 97).addBox(-0.6333F, 1.5247F, -2.933F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		Belt_r2 = new ModelPart(this);
//		Belt_r2.setRotation(5.6833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r2);
//		setRotationAngle(Belt_r2, -0.7854F, 0.0F, -0.3054F);
//		Belt_r2.setTextureOffset(0, 97).addBox(-0.3333F, 1.2947F, -0.8553F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		Belt_r3 = new ModelPart(this);
//		Belt_r3.setRotation(5.5833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r3);
//		setRotationAngle(Belt_r3, -0.7854F, 0.0F, -0.3054F);
//		Belt_r3.setTextureOffset(3, 95).addBox(-0.4323F, -1.1838F, -3.7838F, 1.0F, 1.0F, 6.0F, 0.0F, true);
//
//		Belt_r4 = new ModelPart(this);
//		Belt_r4.setRotation(5.5833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r4);
//		setRotationAngle(Belt_r4, 0.7854F, 0.0F, -0.3054F);
//		Belt_r4.setTextureOffset(3, 95).addBox(-0.4333F, -1.1838F, -2.2162F, 1.0F, 1.0F, 6.0F, 0.0F, true);
//
//		Belt_r5 = new ModelPart(this);
//		Belt_r5.setRotation(5.8833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r5);
//		setRotationAngle(Belt_r5, 1.2654F, 0.0F, -0.3054F);
//		Belt_r5.setTextureOffset(0, 97).addBox(-0.6333F, 1.5247F, -0.067F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		Belt_r6 = new ModelPart(this);
//		Belt_r6.setRotation(5.6833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r6);
//		setRotationAngle(Belt_r6, 0.7854F, 0.0F, -0.3054F);
//		Belt_r6.setTextureOffset(0, 97).addBox(-0.4333F, 1.2947F, -2.1447F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		Belt_r7 = new ModelPart(this);
//		Belt_r7.setRotation(-5.6833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r7);
//		setRotationAngle(Belt_r7, -0.7854F, 0.0F, 0.3054F);
//		Belt_r7.setTextureOffset(0, 97).addBox(-0.6667F, 1.2947F, -0.8553F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		Belt_r8 = new ModelPart(this);
//		Belt_r8.setRotation(-5.8833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r8);
//		setRotationAngle(Belt_r8, -1.2654F, 0.0F, 0.3054F);
//		Belt_r8.setTextureOffset(0, 97).addBox(-0.3667F, 1.5247F, -2.933F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		Belt_r9 = new ModelPart(this);
//		Belt_r9.setRotation(-5.8833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r9);
//		setRotationAngle(Belt_r9, 1.2654F, 0.0F, 0.3054F);
//		Belt_r9.setTextureOffset(0, 97).addBox(-0.3667F, 1.5247F, -0.067F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		Belt_r10 = new ModelPart(this);
//		Belt_r10.setRotation(-5.6833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r10);
//		setRotationAngle(Belt_r10, 0.7854F, 0.0F, 0.3054F);
//		Belt_r10.setTextureOffset(0, 97).addBox(-0.5667F, 1.2947F, -2.1447F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		Belt_r11 = new ModelPart(this);
//		Belt_r11.setRotation(-5.5833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r11);
//		setRotationAngle(Belt_r11, -0.7854F, 0.0F, 0.3054F);
//		Belt_r11.setTextureOffset(3, 95).addBox(-0.5677F, -1.1838F, -3.7838F, 1.0F, 1.0F, 6.0F, 0.0F, false);
//
//		Belt_r12 = new ModelPart(this);
//		Belt_r12.setRotation(-5.5833F, 13.5099F, 0.0F);
//		Belt.addChild(Belt_r12);
//		setRotationAngle(Belt_r12, 0.7854F, 0.0F, 0.3054F);
//		Belt_r12.setTextureOffset(3, 95).addBox(-0.5667F, -1.1838F, -2.2162F, 1.0F, 1.0F, 6.0F, 0.0F, false);
//
//		RightLeg = new ModelPart(this);
//		RightLeg.setRotation(-1.9F, 12.0F, 0.0F);
//		RightLeg.setTextureOffset(0, 110).addBox(-3.0F, -1.0F, -2.99F, 5.0F, 12.0F, 6.0F, 0.0F, false);
//
//		RightLeg_r1 = new ModelPart(this);
//		RightLeg_r1.setRotation(-0.4897F, 3.8373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r1);
//		setRotationAngle(RightLeg_r1, 0.0F, 0.0F, -0.7854F);
//		RightLeg_r1.setTextureOffset(19, 101).addBox(-1.1696F, -2.8122F, -3.3306F, 1.0F, 7.0F, 1.0F, 0.0F, true);
//		RightLeg_r1.setTextureOffset(19, 101).addBox(0.5982F, -4.5799F, -3.3306F, 1.0F, 7.0F, 1.0F, 0.0F, true);
//
//		RightLeg_r2 = new ModelPart(this);
//		RightLeg_r2.setRotation(-0.4897F, 3.8373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r2);
//		setRotationAngle(RightLeg_r2, 0.0F, 0.0F, 0.7854F);
//		RightLeg_r2.setTextureOffset(19, 101).addBox(-1.5774F, -4.6007F, -3.4306F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//		RightLeg_r2.setTextureOffset(19, 101).addBox(0.1903F, -2.833F, -3.4306F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r3 = new ModelPart(this);
//		RightLeg_r3.setRotation(-0.4897F, 3.8373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r3);
//		setRotationAngle(RightLeg_r3, 0.0F, 0.0F, 0.7854F);
//		RightLeg_r3.setTextureOffset(0, 101).addBox(-1.5774F, -4.6007F, 2.4354F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//		RightLeg_r3.setTextureOffset(0, 101).addBox(0.1903F, -2.833F, 2.4354F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r4 = new ModelPart(this);
//		RightLeg_r4.setRotation(-0.4897F, 3.8373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r4);
//		setRotationAngle(RightLeg_r4, 0.0F, 0.0F, -0.7854F);
//		RightLeg_r4.setTextureOffset(0, 101).addBox(0.5982F, -4.5799F, 2.3354F, 1.0F, 7.0F, 1.0F, 0.0F, true);
//		RightLeg_r4.setTextureOffset(0, 101).addBox(-1.1696F, -2.8122F, 2.3354F, 1.0F, 7.0F, 1.0F, 0.0F, true);
//
//		RightLeg_r5 = new ModelPart(this);
//		RightLeg_r5.setRotation(-0.4897F, 4.0373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r5);
//		setRotationAngle(RightLeg_r5, -0.3054F, 0.0F, 0.0F);
//		RightLeg_r5.setTextureOffset(0, 78).addBox(-2.9835F, -0.981F, -3.6937F, 6.0F, 1.0F, 7.0F, 0.0F, false);
//
//		RightLeg_r6 = new ModelPart(this);
//		RightLeg_r6.setRotation(-0.4897F, 4.0373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r6);
//		setRotationAngle(RightLeg_r6, 0.3054F, 0.0F, 0.0F);
//		RightLeg_r6.setTextureOffset(0, 78).addBox(-2.9835F, -0.821F, -3.384F, 6.0F, 1.0F, 7.0F, 0.0F, false);
//
//		RightLeg_r7 = new ModelPart(this);
//		RightLeg_r7.setRotation(-0.4897F, 3.8373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r7);
//		setRotationAngle(RightLeg_r7, 0.0F, 0.0F, 0.0F);
//		RightLeg_r7.setTextureOffset(0, 102).addBox(-2.9835F, -3.8402F, -3.4976F, 6.0F, 1.0F, 7.0F, 0.0F, false);
//		RightLeg_r7.setTextureOffset(0, 102).addBox(-2.9835F, 2.7598F, -3.4976F, 6.0F, 1.0F, 7.0F, 0.0F, false);
//
//		RightLeg_r8 = new ModelPart(this);
//		RightLeg_r8.setRotation(-4.6764F, 6.9405F, 1.1254F);
//		RightLeg.addChild(RightLeg_r8);
//		setRotationAngle(RightLeg_r8, -0.85F, -0.4211F, 1.6939F);
//		RightLeg_r8.setTextureOffset(49, 121).addBox(-0.1699F, -0.7262F, 0.0424F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r9 = new ModelPart(this);
//		RightLeg_r9.setRotation(-4.0764F, 6.7405F, 1.9254F);
//		RightLeg.addChild(RightLeg_r9);
//		setRotationAngle(RightLeg_r9, 2.4003F, 0.0576F, -0.6523F);
//		RightLeg_r9.setTextureOffset(49, 118).addBox(-0.2207F, -1.4946F, -0.1371F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r10 = new ModelPart(this);
//		RightLeg_r10.setRotation(-4.1719F, 5.4737F, 0.227F);
//		RightLeg.addChild(RightLeg_r10);
//		setRotationAngle(RightLeg_r10, 2.8792F, 0.8588F, -0.9424F);
//		RightLeg_r10.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r11 = new ModelPart(this);
//		RightLeg_r11.setRotation(-3.9886F, 6.5494F, -1.8674F);
//		RightLeg.addChild(RightLeg_r11);
//		setRotationAngle(RightLeg_r11, 1.0093F, 0.7614F, 0.2047F);
//		RightLeg_r11.setTextureOffset(49, 118).addBox(-0.5898F, -0.7177F, -0.29F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r12 = new ModelPart(this);
//		RightLeg_r12.setRotation(-5.1886F, 4.8494F, 1.5326F);
//		RightLeg.addChild(RightLeg_r12);
//		setRotationAngle(RightLeg_r12, -0.01F, -1.1486F, 0.519F);
//		RightLeg_r12.setTextureOffset(49, 121).addBox(-0.6538F, -0.3525F, -0.794F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r13 = new ModelPart(this);
//		RightLeg_r13.setRotation(-3.9886F, 6.5494F, -1.8674F);
//		RightLeg.addChild(RightLeg_r13);
//		setRotationAngle(RightLeg_r13, -0.2169F, 0.0193F, 0.5191F);
//		RightLeg_r13.setTextureOffset(49, 121).addBox(-0.6538F, -0.3525F, -0.794F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightLeg_r14 = new ModelPart(this);
//		RightLeg_r14.setRotation(-0.7897F, 3.8373F, 0.7726F);
//		RightLeg.addChild(RightLeg_r14);
//		setRotationAngle(RightLeg_r14, 0.7854F, 0.0F, 0.0F);
//		RightLeg_r14.setTextureOffset(16, 112).addBox(-3.1835F, 0.8067F, -3.8034F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//
//		RightLeg_r15 = new ModelPart(this);
//		RightLeg_r15.setRotation(-0.4897F, 3.8373F, 0.0226F);
//		RightLeg.addChild(RightLeg_r15);
//		setRotationAngle(RightLeg_r15, 0.7854F, 0.0F, 0.0F);
//		RightLeg_r15.setTextureOffset(11, 95).addBox(-3.1835F, 0.8067F, -3.8034F, 6.0F, 3.0F, 3.0F, 0.0F, false);
//
//		RightLeg_r16 = new ModelPart(this);
//		RightLeg_r16.setRotation(-0.4897F, 5.0873F, 0.0226F);
//		RightLeg.addChild(RightLeg_r16);
//		setRotationAngle(RightLeg_r16, 0.0F, 0.0F, 0.0F);
//		RightLeg_r16.setTextureOffset(0, 102).addBox(-2.9835F, 2.7598F, -3.4976F, 6.0F, 1.0F, 7.0F, 0.0F, false);
//
//		LeftLeg = new ModelPart(this);
//		LeftLeg.setRotation(1.9F, 12.0F, 0.0F);
//		LeftLeg.setTextureOffset(0, 110).addBox(-2.0F, -1.0F, -2.99F, 5.0F, 12.0F, 6.0F, 0.0F, true);
//
//		LeftLeg_r1 = new ModelPart(this);
//		LeftLeg_r1.setRotation(0.7897F, 3.8373F, 0.7726F);
//		LeftLeg.addChild(LeftLeg_r1);
//		setRotationAngle(LeftLeg_r1, 0.7854F, 0.0F, 0.0F);
//		LeftLeg_r1.setTextureOffset(16, 112).addBox(2.1835F, 0.8067F, -3.8034F, 1.0F, 2.0F, 2.0F, 0.0F, true);
//
//		LeftLeg_r2 = new ModelPart(this);
//		LeftLeg_r2.setRotation(3.9886F, 6.5494F, -1.8674F);
//		LeftLeg.addChild(LeftLeg_r2);
//		setRotationAngle(LeftLeg_r2, -0.2169F, -0.0193F, -0.5191F);
//		LeftLeg_r2.setTextureOffset(49, 121).addBox(-0.3462F, -0.3525F, -0.794F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r3 = new ModelPart(this);
//		LeftLeg_r3.setRotation(3.9886F, 6.5494F, -1.8674F);
//		LeftLeg.addChild(LeftLeg_r3);
//		setRotationAngle(LeftLeg_r3, 1.0093F, -0.7614F, -0.2047F);
//		LeftLeg_r3.setTextureOffset(49, 118).addBox(-0.4102F, -0.7177F, -0.29F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r4 = new ModelPart(this);
//		LeftLeg_r4.setRotation(5.1886F, 4.8494F, 1.5326F);
//		LeftLeg.addChild(LeftLeg_r4);
//		setRotationAngle(LeftLeg_r4, -0.01F, 1.1486F, -0.519F);
//		LeftLeg_r4.setTextureOffset(49, 121).addBox(-0.3462F, -0.3525F, -0.794F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r5 = new ModelPart(this);
//		LeftLeg_r5.setRotation(4.1719F, 5.4737F, 0.227F);
//		LeftLeg.addChild(LeftLeg_r5);
//		setRotationAngle(LeftLeg_r5, 2.8792F, -0.8588F, 0.9424F);
//		LeftLeg_r5.setTextureOffset(49, 118).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r6 = new ModelPart(this);
//		LeftLeg_r6.setRotation(4.0764F, 6.7405F, 1.9254F);
//		LeftLeg.addChild(LeftLeg_r6);
//		setRotationAngle(LeftLeg_r6, 2.4003F, -0.0576F, 0.6523F);
//		LeftLeg_r6.setTextureOffset(49, 118).addBox(-0.7793F, -1.4946F, -0.1371F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r7 = new ModelPart(this);
//		LeftLeg_r7.setRotation(4.6764F, 6.9405F, 1.1254F);
//		LeftLeg.addChild(LeftLeg_r7);
//		setRotationAngle(LeftLeg_r7, -0.85F, 0.4211F, -1.6939F);
//		LeftLeg_r7.setTextureOffset(49, 121).addBox(-0.8301F, -0.7262F, 0.0424F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r8 = new ModelPart(this);
//		LeftLeg_r8.setRotation(0.3F, 6.0346F, -1.333F);
//		LeftLeg.addChild(LeftLeg_r8);
//		setRotationAngle(LeftLeg_r8, 0.0F, 0.0F, 0.7854F);
//		LeftLeg_r8.setTextureOffset(19, 101).addBox(-1.25F, -4.5F, -2.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		LeftLeg_r9 = new ModelPart(this);
//		LeftLeg_r9.setRotation(0.3F, 3.5346F, -1.333F);
//		LeftLeg.addChild(LeftLeg_r9);
//		setRotationAngle(LeftLeg_r9, 0.0F, 0.0F, 0.7854F);
//		LeftLeg_r9.setTextureOffset(19, 101).addBox(-1.25F, -4.5F, -2.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		LeftLeg_r10 = new ModelPart(this);
//		LeftLeg_r10.setRotation(0.65F, 3.5346F, -1.433F);
//		LeftLeg.addChild(LeftLeg_r10);
//		setRotationAngle(LeftLeg_r10, 0.0F, 0.0F, -0.7854F);
//		LeftLeg_r10.setTextureOffset(19, 101).addBox(0.25F, -4.5F, -2.0F, 1.0F, 7.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r11 = new ModelPart(this);
//		LeftLeg_r11.setRotation(0.65F, 6.0346F, -1.433F);
//		LeftLeg.addChild(LeftLeg_r11);
//		setRotationAngle(LeftLeg_r11, 0.0F, 0.0F, -0.7854F);
//		LeftLeg_r11.setTextureOffset(19, 101).addBox(0.25F, -4.5F, -2.0F, 1.0F, 7.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r12 = new ModelPart(this);
//		LeftLeg_r12.setRotation(0.65F, 3.5346F, 1.433F);
//		LeftLeg.addChild(LeftLeg_r12);
//		setRotationAngle(LeftLeg_r12, 0.0F, 0.0F, -0.7854F);
//		LeftLeg_r12.setTextureOffset(0, 101).addBox(0.25F, -4.5F, 1.0F, 1.0F, 7.0F, 1.0F, 0.0F, true);
//
//		LeftLeg_r13 = new ModelPart(this);
//		LeftLeg_r13.setRotation(0.3F, 3.5346F, 1.333F);
//		LeftLeg.addChild(LeftLeg_r13);
//		setRotationAngle(LeftLeg_r13, 0.0F, 0.0F, 0.7854F);
//		LeftLeg_r13.setTextureOffset(0, 101).addBox(-1.25F, -4.5F, 1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		LeftLeg_r14 = new ModelPart(this);
//		LeftLeg_r14.setRotation(0.65F, 6.0346F, 1.433F);
//		LeftLeg.addChild(LeftLeg_r14);
//		setRotationAngle(LeftLeg_r14, 0.0F, 0.0F, -0.7854F);
//		LeftLeg_r14.setTextureOffset(0, 101).addBox(0.25F, -4.5F, 1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		LeftLeg_r15 = new ModelPart(this);
//		LeftLeg_r15.setRotation(0.3F, 6.0346F, 1.333F);
//		LeftLeg.addChild(LeftLeg_r15);
//		setRotationAngle(LeftLeg_r15, 0.0F, 0.0F, 0.7854F);
//		LeftLeg_r15.setTextureOffset(0, 101).addBox(-1.25F, -4.5F, 1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		LeftLeg_r16 = new ModelPart(this);
//		LeftLeg_r16.setRotation(-0.0268F, -0.2529F, -2.033F);
//		LeftLeg.addChild(LeftLeg_r16);
//		setRotationAngle(LeftLeg_r16, -0.3054F, 0.0F, 0.0F);
//		LeftLeg_r16.setTextureOffset(0, 78).addBox(-2.5F, 2.5F, -0.467F, 6.0F, 1.0F, 7.0F, 0.0F, true);
//
//		LeftLeg_r17 = new ModelPart(this);
//		LeftLeg_r17.setRotation(-0.0268F, 1.7471F, -3.783F);
//		LeftLeg.addChild(LeftLeg_r17);
//		setRotationAngle(LeftLeg_r17, 0.3054F, 0.0F, 0.0F);
//		LeftLeg_r17.setTextureOffset(0, 78).addBox(-2.5F, 2.5F, -0.467F, 6.0F, 1.0F, 7.0F, 0.0F, true);
//
//		LeftLeg_r18 = new ModelPart(this);
//		LeftLeg_r18.setRotation(-0.0268F, -2.5029F, -3.033F);
//		LeftLeg.addChild(LeftLeg_r18);
//		setRotationAngle(LeftLeg_r18, 0.0F, 0.0F, 0.0F);
//		LeftLeg_r18.setTextureOffset(0, 102).addBox(-2.5F, 2.5F, -0.467F, 6.0F, 1.0F, 7.0F, 0.0F, true);
//
//		LeftLeg_r19 = new ModelPart(this);
//		LeftLeg_r19.setRotation(0.6732F, 7.0971F, 0.0F);
//		LeftLeg.addChild(LeftLeg_r19);
//		setRotationAngle(LeftLeg_r19, 0.7854F, 0.0F, 0.0F);
//		LeftLeg_r19.setTextureOffset(11, 95).addBox(-3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, true);
//
//		LeftLeg_r20 = new ModelPart(this);
//		LeftLeg_r20.setRotation(-0.0268F, 5.3471F, -3.033F);
//		LeftLeg.addChild(LeftLeg_r20);
//		setRotationAngle(LeftLeg_r20, 0.0F, 0.0F, 0.0F);
//		LeftLeg_r20.setTextureOffset(0, 102).addBox(-2.5F, 2.5F, -0.467F, 6.0F, 1.0F, 7.0F, 0.0F, true);
//
//		LeftLeg_r21 = new ModelPart(this);
//		LeftLeg_r21.setRotation(-0.0268F, 4.0971F, -3.033F);
//		LeftLeg.addChild(LeftLeg_r21);
//		setRotationAngle(LeftLeg_r21, 0.0F, 0.0F, 0.0F);
//		LeftLeg_r21.setTextureOffset(0, 102).addBox(-2.5F, 2.5F, -0.467F, 6.0F, 1.0F, 7.0F, 0.0F, true);
//
//		RightBoot = new ModelPart(this);
//		RightBoot.setRotation(-1.9F, 11.0F, 0.0F);
//		RightBoot.setTextureOffset(0, 0).addBox(-3.478F, 9.16F, -3.515F, 6.0F, 4.0F, 7.0F, 0.0F, false);
//		RightBoot.setTextureOffset(18, 6).addBox(-3.728F, 8.66F, -3.94F, 1.0F, 2.0F, 8.0F, 0.0F, false);
//		RightBoot.setTextureOffset(19, 3).addBox(-3.978F, 8.66F, -3.69F, 7.0F, 2.0F, 1.0F, 0.0F, false);
//		RightBoot.setTextureOffset(0, 21).addBox(-3.978F, 11.41F, 1.14F, 7.0F, 2.0F, 3.0F, 0.0F, false);
//		RightBoot.setTextureOffset(0, 21).addBox(-3.978F, 11.41F, -3.36F, 7.0F, 2.0F, 3.0F, 0.0F, false);
//		RightBoot.setTextureOffset(19, 0).addBox(-3.978F, 8.66F, 2.81F, 7.0F, 2.0F, 1.0F, 0.0F, false);
//		RightBoot.setTextureOffset(8, 15).addBox(-2.578F, 12.16F, -4.81F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//		RightBoot.setTextureOffset(8, 15).addBox(-0.978F, 12.16F, -4.81F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//		RightBoot.setTextureOffset(8, 15).addBox(0.622F, 12.16F, -4.81F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//		RightBoot.setTextureOffset(8, 11).addBox(1.772F, 8.66F, -3.94F, 1.0F, 2.0F, 8.0F, 0.0F, false);
//
//		RightBoot_r1 = new ModelPart(this);
//		RightBoot_r1.setRotation(-3.728F, 10.16F, 0.56F);
//		RightBoot.addChild(RightBoot_r1);
//		setRotationAngle(RightBoot_r1, 0.0F, 0.0F, 1.5708F);
//		RightBoot_r1.setTextureOffset(19, 7).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
//
//		RightBoot_r2 = new ModelPart(this);
//		RightBoot_r2.setRotation(-0.978F, 10.16F, -3.69F);
//		RightBoot.addChild(RightBoot_r2);
//		setRotationAngle(RightBoot_r2, 1.5708F, 0.0F, 0.0F);
//		RightBoot_r2.setTextureOffset(20, 0).addBox(-2.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightBoot_r3 = new ModelPart(this);
//		RightBoot_r3.setRotation(2.772F, 10.16F, -0.44F);
//		RightBoot.addChild(RightBoot_r3);
//		setRotationAngle(RightBoot_r3, 0.0F, 0.0F, -1.5708F);
//		RightBoot_r3.setTextureOffset(19, 16).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
//
//		RightBoot_r4 = new ModelPart(this);
//		RightBoot_r4.setRotation(0.022F, 10.16F, 3.81F);
//		RightBoot.addChild(RightBoot_r4);
//		setRotationAngle(RightBoot_r4, 1.5708F, 0.0F, 0.0F);
//		RightBoot_r4.setTextureOffset(20, 0).addBox(-3.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightBoot_r5 = new ModelPart(this);
//		RightBoot_r5.setRotation(3.022F, 12.26F, -4.01F);
//		RightBoot.addChild(RightBoot_r5);
//		setRotationAngle(RightBoot_r5, -0.8727F, 0.0F, 0.0F);
//		RightBoot_r5.setTextureOffset(12, 15).addBox(-2.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//		RightBoot_r5.setTextureOffset(12, 15).addBox(-4.0F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//		RightBoot_r5.setTextureOffset(12, 15).addBox(-5.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		RightBoot_r6 = new ModelPart(this);
//		RightBoot_r6.setRotation(-0.478F, 12.477F, -2.9386F);
//		RightBoot.addChild(RightBoot_r6);
//		setRotationAngle(RightBoot_r6, -0.3054F, 0.0F, 0.0F);
//		RightBoot_r6.setTextureOffset(4, 13).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
//
//		RightBoot_r7 = new ModelPart(this);
//		RightBoot_r7.setRotation(0.522F, 11.81F, -3.61F);
//		RightBoot.addChild(RightBoot_r7);
//		setRotationAngle(RightBoot_r7, -0.6981F, 0.0F, 0.0F);
//		RightBoot_r7.setTextureOffset(4, 11).addBox(-3.5F, -1.0F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
//
//		LeftBoot = new ModelPart(this);
//		LeftBoot.setRotation(1.9F, 11.0F, 0.0F);
//		LeftBoot.setTextureOffset(0, 0).addBox(-2.522F, 9.16F, -3.54F, 6.0F, 4.0F, 7.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(18, 6).addBox(2.728F, 8.66F, -4.04F, 1.0F, 2.0F, 8.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(19, 3).addBox(-3.022F, 8.66F, -3.79F, 7.0F, 2.0F, 1.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(0, 21).addBox(-3.022F, 11.41F, 1.04F, 7.0F, 2.0F, 3.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(0, 21).addBox(-3.022F, 11.41F, -3.46F, 7.0F, 2.0F, 3.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(19, 0).addBox(-3.022F, 8.66F, 2.71F, 7.0F, 2.0F, 1.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(8, 15).addBox(1.578F, 12.16F, -4.91F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(8, 15).addBox(-0.022F, 12.16F, -4.91F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(8, 15).addBox(-1.622F, 12.16F, -4.91F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//		LeftBoot.setTextureOffset(8, 11).addBox(-2.772F, 8.66F, -4.04F, 1.0F, 2.0F, 8.0F, 0.0F, true);
//
//		LeftBoot_r1 = new ModelPart(this);
//		LeftBoot_r1.setRotation(3.728F, 10.16F, 0.46F);
//		LeftBoot.addChild(LeftBoot_r1);
//		setRotationAngle(LeftBoot_r1, 0.0F, 0.0F, -1.5708F);
//		LeftBoot_r1.setTextureOffset(19, 7).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 7.0F, 0.0F, true);
//
//		LeftBoot_r2 = new ModelPart(this);
//		LeftBoot_r2.setRotation(0.978F, 10.16F, -3.79F);
//		LeftBoot.addChild(LeftBoot_r2);
//		setRotationAngle(LeftBoot_r2, 1.5708F, 0.0F, 0.0F);
//		LeftBoot_r2.setTextureOffset(20, 0).addBox(-3.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftBoot_r3 = new ModelPart(this);
//		LeftBoot_r3.setRotation(-2.772F, 10.16F, -0.54F);
//		LeftBoot.addChild(LeftBoot_r3);
//		setRotationAngle(LeftBoot_r3, 0.0F, 0.0F, 1.5708F);
//		LeftBoot_r3.setTextureOffset(19, 16).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, true);
//
//		LeftBoot_r4 = new ModelPart(this);
//		LeftBoot_r4.setRotation(-0.022F, 10.16F, 3.71F);
//		LeftBoot.addChild(LeftBoot_r4);
//		setRotationAngle(LeftBoot_r4, 1.5708F, 0.0F, 0.0F);
//		LeftBoot_r4.setTextureOffset(20, 0).addBox(-2.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftBoot_r5 = new ModelPart(this);
//		LeftBoot_r5.setRotation(-3.022F, 12.26F, -4.11F);
//		LeftBoot.addChild(LeftBoot_r5);
//		setRotationAngle(LeftBoot_r5, -0.8727F, 0.0F, 0.0F);
//		LeftBoot_r5.setTextureOffset(12, 15).addBox(1.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//		LeftBoot_r5.setTextureOffset(12, 15).addBox(3.0F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//		LeftBoot_r5.setTextureOffset(12, 15).addBox(4.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		LeftBoot_r6 = new ModelPart(this);
//		LeftBoot_r6.setRotation(0.478F, 12.477F, -3.0386F);
//		LeftBoot.addChild(LeftBoot_r6);
//		setRotationAngle(LeftBoot_r6, -0.3054F, 0.0F, 0.0F);
//		LeftBoot_r6.setTextureOffset(4, 13).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, true);
//
//		LeftBoot_r7 = new ModelPart(this);
//		LeftBoot_r7.setRotation(-0.522F, 11.81F, -3.71F);
//		LeftBoot.addChild(LeftBoot_r7);
//		setRotationAngle(LeftBoot_r7, -0.6981F, 0.0F, 0.0F);
//		LeftBoot_r7.setTextureOffset(4, 11).addBox(-1.5F, -1.0F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, true);
//	}
//
//	// THIS IS THE RENDERING OF THE ARMOR FOR SLOTS THIS IS NEEDED IN EACH JAVA FILE
//	@Override
//	public void render(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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
//
//	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
//
//
//	// This is also needed
//	@SuppressWarnings("unchecked")
//	public static <A extends HumanoidModel<?>> A getModel(EquipmentSlot slot, LivingEntity entity) {
//		boolean illager = entity instanceof AbstractIllagerEntity ||
//				entity instanceof ZombieVillagerEntity ||
//				entity instanceof AbstractVillagerEntity;
//		int entityFlag = (slot.ordinal() & 15) | (illager ? 1 : 0) << 4 | (entity.isChild() ? 1 : 0) << 6;
//		return (A) CACHE.computeIfAbsent(entityFlag, k -> new DruidArmorModel(1, slot));
//	}
//
//}
