//package net.joefoxe.hexerei.client.renderer.entity.model;
//
//// Made with Blockbench 4.0.1
//// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
//// Paste this class into your mod and generate all required imports
//
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.joefoxe.hexerei.client.renderer.entity.custom.BuffZombieEntity;
//import net.minecraft.client.renderer.entity.model.EntityModel;
//import net.minecraft.client.renderer.model.ModelPart;
//import net.minecraft.util.Mth;
//
//public class CauldronMimicModel <T extends BuffZombieEntity> extends EntityModel<T> {
//	private final ModelPart body;
//	private final ModelPart body_r1;
//	private final ModelPart body_r2;
//	private final ModelPart body_r3;
//	private final ModelPart body_r4;
//	private final ModelPart body_r5;
//	private final ModelPart body_r6;
//	private final ModelPart body_r7;
//	private final ModelPart cube_r1;
//	private final ModelPart cube_r2;
//	private final ModelPart cube_r3;
//	private final ModelPart teeth;
//	private final ModelPart cube_r24;
//	private final ModelPart cube_r25;
//	private final ModelPart cube_r26;
//	private final ModelPart cube_r27;
//	private final ModelPart cube_r28;
//	private final ModelPart cube_r29;
//	private final ModelPart cube_r30;
//	private final ModelPart cube_r31;
//	private final ModelPart cube_r32;
//	private final ModelPart cube_r33;
//	private final ModelPart cube_r34;
//	private final ModelPart cube_r35;
//	private final ModelPart cube_r36;
//	private final ModelPart cube_r37;
//	private final ModelPart cube_r38;
//	private final ModelPart cube_r39;
//	private final ModelPart cube_r40;
//	private final ModelPart cube_r41;
//	private final ModelPart cube_r42;
//	private final ModelPart cube_r43;
//	private final ModelPart cube_r44;
//	private final ModelPart cube_r45;
//	private final ModelPart cube_r46;
//	private final ModelPart cube_r47;
//	private final ModelPart cube_r48;
//	private final ModelPart cube_r49;
//	private final ModelPart cube_r50;
//	private final ModelPart cube_r51;
//	private final ModelPart cube_r52;
//	private final ModelPart cube_r53;
//	private final ModelPart cube_r54;
//	private final ModelPart cube_r55;
//	private final ModelPart cube_r56;
//	private final ModelPart cube_r57;
//	private final ModelPart cube_r58;
//	private final ModelPart cube_r59;
//	private final ModelPart cube_r60;
//	private final ModelPart cube_r61;
//	private final ModelPart tongue;
//	private final ModelPart cube_r62;
//	private final ModelPart cube_r63;
//	private final ModelPart cube_r64;
//	private final ModelPart cube_r65;
//	private final ModelPart cube_r66;
//	private final ModelPart tongue_1;
//	private final ModelPart cube_r67;
//	private final ModelPart cube_r68;
//	private final ModelPart cube_r69;
//	private final ModelPart tongue_2;
//	private final ModelPart cube_r70;
//	private final ModelPart cube_r71;
//	private final ModelPart eyes;
//	private final ModelPart left_eye;
//	private final ModelPart left_eye_yaw;
//	private final ModelPart left_eye_pitch;
//	private final ModelPart eye_r1;
//	private final ModelPart eye_r2;
//	private final ModelPart eye_r3;
//	private final ModelPart eye_r4;
//	private final ModelPart eye_r5;
//	private final ModelPart eye_r6;
//	private final ModelPart eye_r7;
//	private final ModelPart eye_r8;
//	private final ModelPart right_eye;
//	private final ModelPart right_eye_yaw;
//	private final ModelPart right_eye_pitch;
//	private final ModelPart eye_r9;
//	private final ModelPart eye_r10;
//	private final ModelPart eye_r11;
//	private final ModelPart eye_r12;
//	private final ModelPart eye_r13;
//	private final ModelPart eye_r14;
//	private final ModelPart eye_r15;
//	private final ModelPart eye_r16;
//	private final ModelPart LeftLegFront;
//	private final ModelPart left_leg_front_1;
//	private final ModelPart left_leg_front_2;
//	private final ModelPart left_leg_front_3;
//	private final ModelPart left_leg_front_tip;
//	private final ModelPart cube_r16;
//	private final ModelPart cube_r17;
//	private final ModelPart cube_r18;
//	private final ModelPart cube_r15;
//	private final ModelPart cube_r14;
//	private final ModelPart LeftLegBack;
//	private final ModelPart left_leg_back_1;
//	private final ModelPart left_leg_back_2;
//	private final ModelPart left_leg_back_3;
//	private final ModelPart left_leg_back_tip;
//	private final ModelPart cube_r4;
//	private final ModelPart cube_r5;
//	private final ModelPart cube_r6;
//	private final ModelPart cube_r7;
//	private final ModelPart cube_r8;
//	private final ModelPart RightLegBack;
//	private final ModelPart right_leg_back_1;
//	private final ModelPart right_leg_back_2;
//	private final ModelPart right_leg_back_3;
//	private final ModelPart right_leg_back_tip;
//	private final ModelPart cube_r9;
//	private final ModelPart cube_r10;
//	private final ModelPart cube_r11;
//	private final ModelPart cube_r12;
//	private final ModelPart cube_r13;
//	private final ModelPart RightLegFront;
//	private final ModelPart right_leg_front_1;
//	private final ModelPart right_leg_front_2;
//	private final ModelPart right_leg_front_3;
//	private final ModelPart right_leg_front_tip;
//	private final ModelPart cube_r19;
//	private final ModelPart cube_r20;
//	private final ModelPart cube_r21;
//	private final ModelPart cube_r22;
//	private final ModelPart cube_r23;
//
//	public CauldronMimicModel() {
//		textureWidth = 128;
//		textureHeight = 128;
//
//		body = new ModelPart(this);
//		body.setRotation(0.1707F, 10.9756F, -2.0317F);
//		setRotationAngle(body, 0.3054F, 0.0F, 0.0F);
//		body.setTextureOffset(10, 50).addBox(-7.1707F, -2.4756F, -8.2683F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//		body.setTextureOffset(0, 50).addBox(-7.1707F, -2.4756F, 8.2317F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//		body.setTextureOffset(36, 50).addBox(-7.1707F, -0.4756F, 8.2317F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//		body.setTextureOffset(14, 50).addBox(-7.1707F, -0.4756F, -8.2683F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//		body.setTextureOffset(5, 20).addBox(6.0793F, -4.9756F, -9.0183F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(6.5793F, -5.9756F, -6.5183F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(8.5793F, -5.9756F, -9.0183F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(5.5793F, -5.9756F, -9.0183F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(6.5793F, -5.9756F, -9.5183F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//		body.setTextureOffset(0, 48).addBox(-8.1707F, -4.4756F, -7.7683F, 16.0F, 13.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(-14, 20).addBox(-7.1707F, 8.0244F, -6.7683F, 14.0F, 1.0F, 14.0F, 0.0F, false);
//		body.setTextureOffset(2, 17).addBox(-7.6707F, 9.0244F, -7.2683F, 15.0F, 1.0F, 15.0F, 0.0F, false);
//		body.setTextureOffset(2, 0).addBox(-7.6707F, 7.5244F, -7.2683F, 15.0F, 1.0F, 15.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(6.0793F, 8.0244F, -8.0183F, 2.0F, 0.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(20, 18).addBox(6.5793F, -3.9756F, -8.5183F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(-7.9207F, 8.7744F, 4.9817F, 3.0F, 2.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(4.5793F, 8.7744F, 4.9817F, 3.0F, 2.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(-8.4207F, 8.0244F, -8.0183F, 2.0F, 0.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(20, 18).addBox(-8.9207F, -3.9756F, -8.5183F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(5, 20).addBox(-9.4207F, -4.9756F, -9.0183F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(-6.9207F, -5.9756F, -9.0183F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(-8.9207F, -5.9756F, -9.5183F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(-9.9207F, -5.9756F, -9.0183F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(-8.9207F, -5.9756F, -6.5183F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(-8.4207F, 8.0244F, 6.4817F, 2.0F, 0.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(19, 18).addBox(-8.9207F, -3.9756F, 6.9817F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(23, 21).addBox(-9.4207F, -4.9756F, 6.4817F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(0, 0).addBox(6.0793F, 8.0244F, 6.4817F, 2.0F, 0.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(22, 18).addBox(6.5793F, -3.9756F, 6.9817F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//		body.setTextureOffset(18, 19).addBox(6.0793F, -4.9756F, 6.4817F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//		body.setTextureOffset(36, 38).addBox(5.8293F, -4.4756F, -5.7683F, 2.0F, 13.0F, 12.0F, 0.0F, false);
//
//		body_r1 = new ModelPart(this);
//		body_r1.setRotation(-7.8207F, -5.4756F, 9.2317F);
//		body.addChild(body_r1);
//		setRotationAngle(body_r1, 0.3433F, 0.2143F, -0.0123F);
//		body_r1.setTextureOffset(36, 61).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);
//
//		body_r2 = new ModelPart(this);
//		body_r2.setRotation(-9.4207F, -5.4756F, 7.9817F);
//		body.addChild(body_r2);
//		setRotationAngle(body_r2, 0.1105F, 0.2698F, 0.5205F);
//		body_r2.setTextureOffset(0, 0).addBox(-0.75F, -0.25F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		body_r3 = new ModelPart(this);
//		body_r3.setRotation(-7.6707F, -5.4756F, 5.9317F);
//		body.addChild(body_r3);
//		setRotationAngle(body_r3, -0.3491F, 0.0F, 0.0F);
//		body_r3.setTextureOffset(0, 0).addBox(-2.0F, -0.5F, -0.25F, 4.0F, 1.0F, 1.0F, 0.0F, true);
//		body_r3.setTextureOffset(0, 0).addBox(13.0F, -0.5F, -0.25F, 4.0F, 1.0F, 1.0F, 0.0F, false);
//
//		body_r4 = new ModelPart(this);
//		body_r4.setRotation(-6.1823F, -5.453F, 7.5534F);
//		body.addChild(body_r4);
//		setRotationAngle(body_r4, 0.0891F, -0.2776F, -0.269F);
//		body_r4.setTextureOffset(9, 13).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, true);
//
//		body_r5 = new ModelPart(this);
//		body_r5.setRotation(5.8408F, -5.453F, 7.5534F);
//		body.addChild(body_r5);
//		setRotationAngle(body_r5, 0.0891F, 0.2776F, 0.269F);
//		body_r5.setTextureOffset(9, 13).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		body_r6 = new ModelPart(this);
//		body_r6.setRotation(9.0793F, -5.4756F, 7.9817F);
//		body.addChild(body_r6);
//		setRotationAngle(body_r6, 0.1105F, -0.2698F, -0.5205F);
//		body_r6.setTextureOffset(0, 0).addBox(-0.25F, -0.25F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//
//		body_r7 = new ModelPart(this);
//		body_r7.setRotation(7.4793F, -5.4756F, 9.2317F);
//		body.addChild(body_r7);
//		setRotationAngle(body_r7, 0.3433F, -0.2143F, 0.0123F);
//		body_r7.setTextureOffset(36, 61).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r1 = new ModelPart(this);
//		cube_r1.setRotation(-0.1707F, -18.9756F, 0.2317F);
//		body.addChild(cube_r1);
//		setRotationAngle(cube_r1, -3.1416F, 0.0F, 3.1416F);
//		cube_r1.setTextureOffset(0, 48).addBox(-8.0F, 14.5F, -8.0F, 16.0F, 13.0F, 2.0F, 0.0F, false);
//
//		cube_r2 = new ModelPart(this);
//		cube_r2.setRotation(-0.1707F, -4.9756F, 0.2317F);
//		body.addChild(cube_r2);
//		setRotationAngle(cube_r2, 0.0F, 1.5708F, 0.0F);
//		cube_r2.setTextureOffset(24, 50).addBox(-7.0F, 4.5F, -8.5F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//		cube_r2.setTextureOffset(4, 50).addBox(-7.0F, 2.5F, -8.5F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//		cube_r2.setTextureOffset(10, 50).addBox(-7.0F, 4.5F, 8.0F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//		cube_r2.setTextureOffset(0, 50).addBox(-7.0F, 2.5F, 8.0F, 14.0F, 1.0F, 0.0F, 0.0F, false);
//
//		cube_r3 = new ModelPart(this);
//		cube_r3.setRotation(-0.1707F, -17.9756F, 0.2317F);
//		body.addChild(cube_r3);
//		setRotationAngle(cube_r3, -3.1416F, 0.0F, 3.1416F);
//		cube_r3.setTextureOffset(36, 38).addBox(6.0F, 13.5F, -6.0F, 2.0F, 13.0F, 12.0F, 0.0F, false);
//
//		teeth = new ModelPart(this);
//		teeth.setRotation(-0.1707F, 15.5244F, 0.2317F);
//		body.addChild(teeth);
//		teeth.setTextureOffset(0, 46).addBox(-6.0F, -19.9139F, -6.5032F, 12.0F, 1.0F, 1.0F, 0.0F, false);
//		teeth.setTextureOffset(0, 46).addBox(-6.0F, -19.9139F, 5.5032F, 12.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r24 = new ModelPart(this);
//		cube_r24.setRotation(-7.3911F, -23.0749F, -7.3936F);
//		teeth.addChild(cube_r24);
//		setRotationAngle(cube_r24, -0.7457F, 0.3137F, -0.0799F);
//		cube_r24.setTextureOffset(0, 41).addBox(-0.4F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		cube_r25 = new ModelPart(this);
//		cube_r25.setRotation(-7.9089F, -23.0749F, -7.1936F);
//		teeth.addChild(cube_r25);
//		setRotationAngle(cube_r25, -0.6897F, 0.604F, -0.1209F);
//		cube_r25.setTextureOffset(0, 41).addBox(-0.5F, -1.0F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		cube_r26 = new ModelPart(this);
//		cube_r26.setRotation(-8.0089F, -23.2749F, -7.4936F);
//		teeth.addChild(cube_r26);
//		setRotationAngle(cube_r26, -0.8329F, 0.6643F, -0.3487F);
//		cube_r26.setTextureOffset(0, 41).addBox(-0.5F, -1.0F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		cube_r27 = new ModelPart(this);
//		cube_r27.setRotation(-6.9172F, -24.1086F, -6.01F);
//		teeth.addChild(cube_r27);
//		setRotationAngle(cube_r27, -0.5668F, 0.2061F, -1.0586F);
//		cube_r27.setTextureOffset(0, 41).addBox(-0.7F, -0.3F, -0.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r28 = new ModelPart(this);
//		cube_r28.setRotation(-7.3186F, -24.0183F, -6.7292F);
//		teeth.addChild(cube_r28);
//		setRotationAngle(cube_r28, -0.7994F, 0.0523F, 0.5427F);
//		cube_r28.setTextureOffset(0, 41).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r29 = new ModelPart(this);
//		cube_r29.setRotation(-7.4911F, -23.2749F, -7.6936F);
//		teeth.addChild(cube_r29);
//		setRotationAngle(cube_r29, -0.6617F, 0.1004F, 0.0772F);
//		cube_r29.setTextureOffset(0, 41).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		cube_r30 = new ModelPart(this);
//		cube_r30.setRotation(-7.75F, -23.25F, -7.45F);
//		teeth.addChild(cube_r30);
//		setRotationAngle(cube_r30, -0.2313F, 0.3422F, -0.0672F);
//		cube_r30.setTextureOffset(0, 41).addBox(-1.0F, 0.5F, -1.1F, 2.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r31 = new ModelPart(this);
//		cube_r31.setRotation(7.9089F, -23.0749F, -7.1936F);
//		teeth.addChild(cube_r31);
//		setRotationAngle(cube_r31, -0.6897F, -0.604F, 0.1209F);
//		cube_r31.setTextureOffset(0, 41).addBox(-0.5F, -1.0F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		cube_r32 = new ModelPart(this);
//		cube_r32.setRotation(7.3911F, -23.0749F, -7.3936F);
//		teeth.addChild(cube_r32);
//		setRotationAngle(cube_r32, -0.7457F, -0.3137F, 0.0799F);
//		cube_r32.setTextureOffset(0, 41).addBox(-0.6F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		cube_r33 = new ModelPart(this);
//		cube_r33.setRotation(7.3186F, -24.0183F, -6.7292F);
//		teeth.addChild(cube_r33);
//		setRotationAngle(cube_r33, -0.7994F, -0.0523F, -0.5427F);
//		cube_r33.setTextureOffset(0, 41).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		cube_r34 = new ModelPart(this);
//		cube_r34.setRotation(6.9172F, -24.1086F, -6.01F);
//		teeth.addChild(cube_r34);
//		setRotationAngle(cube_r34, -0.5668F, -0.2061F, 1.0586F);
//		cube_r34.setTextureOffset(0, 41).addBox(-0.3F, -0.3F, -0.7F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//		cube_r35 = new ModelPart(this);
//		cube_r35.setRotation(7.4911F, -23.2749F, -7.6936F);
//		teeth.addChild(cube_r35);
//		setRotationAngle(cube_r35, -0.6617F, -0.1004F, -0.0772F);
//		cube_r35.setTextureOffset(0, 41).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//
//		cube_r36 = new ModelPart(this);
//		cube_r36.setRotation(8.0089F, -23.2749F, -7.4936F);
//		teeth.addChild(cube_r36);
//		setRotationAngle(cube_r36, -0.8329F, -0.6643F, 0.3487F);
//		cube_r36.setTextureOffset(0, 41).addBox(-0.5F, -1.0F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, true);
//
//		cube_r37 = new ModelPart(this);
//		cube_r37.setRotation(7.75F, -23.25F, -7.45F);
//		teeth.addChild(cube_r37);
//		setRotationAngle(cube_r37, -0.2313F, -0.3422F, 0.0672F);
//		cube_r37.setTextureOffset(0, 41).addBox(-1.0F, 0.5F, -1.1F, 2.0F, 1.0F, 2.0F, 0.0F, true);
//
//		cube_r38 = new ModelPart(this);
//		cube_r38.setRotation(7.75F, -22.5F, -7.75F);
//		teeth.addChild(cube_r38);
//		setRotationAngle(cube_r38, 0.0F, -0.1745F, 0.0F);
//		cube_r38.setTextureOffset(4, 45).addBox(-1.0F, 0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
//
//		cube_r39 = new ModelPart(this);
//		cube_r39.setRotation(-7.75F, -22.5F, -7.75F);
//		teeth.addChild(cube_r39);
//		setRotationAngle(cube_r39, 0.0F, 0.1745F, 0.0F);
//		cube_r39.setTextureOffset(4, 45).addBox(-1.0F, 0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r40 = new ModelPart(this);
//		cube_r40.setRotation(-5.0F, -19.4139F, 4.8532F);
//		teeth.addChild(cube_r40);
//		setRotationAngle(cube_r40, -0.2605F, -0.2891F, -0.2397F);
//		cube_r40.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r41 = new ModelPart(this);
//		cube_r41.setRotation(-4.0F, -19.2989F, 4.9604F);
//		teeth.addChild(cube_r41);
//		setRotationAngle(cube_r41, -0.1719F, 0.0302F, 0.1719F);
//		cube_r41.setTextureOffset(6, 64).addBox(-0.4787F, -0.3383F, -1.0477F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r42 = new ModelPart(this);
//		cube_r42.setRotation(-2.9863F, -19.2649F, 5.1239F);
//		teeth.addChild(cube_r42);
//		setRotationAngle(cube_r42, -0.2586F, 0.0816F, -0.2021F);
//		cube_r42.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r43 = new ModelPart(this);
//		cube_r43.setRotation(-2.0F, -19.1465F, 4.8851F);
//		teeth.addChild(cube_r43);
//		setRotationAngle(cube_r43, -0.0879F, 0.1266F, -0.0549F);
//		cube_r43.setTextureOffset(12, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r44 = new ModelPart(this);
//		cube_r44.setRotation(-0.5F, -19.0F, 3.5F);
//		teeth.addChild(cube_r44);
//		setRotationAngle(cube_r44, -0.2618F, 0.0F, 0.0F);
//		cube_r44.setTextureOffset(6, 64).addBox(-1.0F, -1.25F, 0.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r45 = new ModelPart(this);
//		cube_r45.setRotation(0.0F, -19.1077F, 5.03F);
//		teeth.addChild(cube_r45);
//		setRotationAngle(cube_r45, -0.4834F, -0.1499F, -0.0657F);
//		cube_r45.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r46 = new ModelPart(this);
//		cube_r46.setRotation(1.0F, -19.388F, 4.9498F);
//		teeth.addChild(cube_r46);
//		setRotationAngle(cube_r46, -0.264F, 0.1264F, -0.0341F);
//		cube_r46.setTextureOffset(12, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r47 = new ModelPart(this);
//		cube_r47.setRotation(2.0F, -19.1724F, 4.7885F);
//		teeth.addChild(cube_r47);
//		setRotationAngle(cube_r47, -0.1724F, 0.0338F, 0.1265F);
//		cube_r47.setTextureOffset(12, 64).addBox(-0.5F, -0.5F, -0.55F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r48 = new ModelPart(this);
//		cube_r48.setRotation(3.0F, -19.4139F, 4.8532F);
//		teeth.addChild(cube_r48);
//		setRotationAngle(cube_r48, -0.2679F, -0.2106F, 0.0573F);
//		cube_r48.setTextureOffset(6, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r49 = new ModelPart(this);
//		cube_r49.setRotation(4.0F, -19.1724F, 4.7885F);
//		teeth.addChild(cube_r49);
//		setRotationAngle(cube_r49, -0.3478F, -0.0298F, -0.082F);
//		cube_r49.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -0.8F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r50 = new ModelPart(this);
//		cube_r50.setRotation(5.0F, -19.4139F, 4.8532F);
//		teeth.addChild(cube_r50);
//		setRotationAngle(cube_r50, -0.2739F, 0.2947F, -0.0814F);
//		cube_r50.setTextureOffset(6, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r51 = new ModelPart(this);
//		cube_r51.setRotation(4.0F, -19.1724F, -4.7885F);
//		teeth.addChild(cube_r51);
//		setRotationAngle(cube_r51, 0.3478F, 0.0298F, -0.082F);
//		cube_r51.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -1.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r52 = new ModelPart(this);
//		cube_r52.setRotation(2.0F, -19.1724F, -4.7885F);
//		teeth.addChild(cube_r52);
//		setRotationAngle(cube_r52, 0.1724F, -0.0338F, 0.1265F);
//		cube_r52.setTextureOffset(12, 64).addBox(-0.5F, -0.5F, -1.45F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r53 = new ModelPart(this);
//		cube_r53.setRotation(0.0F, -19.1077F, -5.03F);
//		teeth.addChild(cube_r53);
//		setRotationAngle(cube_r53, 0.4834F, 0.1499F, -0.0657F);
//		cube_r53.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r54 = new ModelPart(this);
//		cube_r54.setRotation(-2.0F, -19.1465F, -4.8851F);
//		teeth.addChild(cube_r54);
//		setRotationAngle(cube_r54, 0.0879F, -0.1266F, -0.0549F);
//		cube_r54.setTextureOffset(12, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r55 = new ModelPart(this);
//		cube_r55.setRotation(-4.0F, -19.2989F, -4.9604F);
//		teeth.addChild(cube_r55);
//		setRotationAngle(cube_r55, 0.1719F, -0.0302F, 0.1719F);
//		cube_r55.setTextureOffset(6, 64).addBox(-0.4787F, -0.3383F, -0.9523F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r56 = new ModelPart(this);
//		cube_r56.setRotation(5.0F, -19.4139F, -4.8532F);
//		teeth.addChild(cube_r56);
//		setRotationAngle(cube_r56, 0.2739F, -0.2947F, -0.0814F);
//		cube_r56.setTextureOffset(6, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r57 = new ModelPart(this);
//		cube_r57.setRotation(3.0F, -19.4139F, -4.8532F);
//		teeth.addChild(cube_r57);
//		setRotationAngle(cube_r57, 0.2679F, 0.2106F, 0.0573F);
//		cube_r57.setTextureOffset(6, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r58 = new ModelPart(this);
//		cube_r58.setRotation(1.0F, -19.388F, -4.9498F);
//		teeth.addChild(cube_r58);
//		setRotationAngle(cube_r58, 0.264F, -0.1264F, -0.0341F);
//		cube_r58.setTextureOffset(12, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r59 = new ModelPart(this);
//		cube_r59.setRotation(-0.5F, -19.0F, -3.5F);
//		teeth.addChild(cube_r59);
//		setRotationAngle(cube_r59, 0.2618F, 0.0F, 0.0F);
//		cube_r59.setTextureOffset(6, 64).addBox(-1.0F, -1.25F, -2.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r60 = new ModelPart(this);
//		cube_r60.setRotation(-2.9863F, -19.2649F, -5.1239F);
//		teeth.addChild(cube_r60);
//		setRotationAngle(cube_r60, 0.2586F, -0.0816F, -0.2021F);
//		cube_r60.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		cube_r61 = new ModelPart(this);
//		cube_r61.setRotation(-5.0F, -19.4139F, -4.8532F);
//		teeth.addChild(cube_r61);
//		setRotationAngle(cube_r61, 0.2605F, 0.2891F, -0.2397F);
//		cube_r61.setTextureOffset(0, 64).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//		tongue = new ModelPart(this);
//		tongue.setRotation(-0.1707F, 15.5244F, 0.2317F);
//		body.addChild(tongue);
//		setRotationAngle(tongue, 0.0F, 0.5672F, 0.0F);
//
//
//		cube_r62 = new ModelPart(this);
//		cube_r62.setRotation(7.0676F, -15.177F, 1.7311F);
//		tongue.addChild(cube_r62);
//		setRotationAngle(cube_r62, 0.0F, -0.5672F, 0.0F);
//		cube_r62.setTextureOffset(0, 114).addBox(-12.2655F, 6.727F, -4.6558F, 11.0F, 2.0F, 12.0F, 0.0F, false);
//
//		cube_r63 = new ModelPart(this);
//		cube_r63.setRotation(2.0575F, -10.9499F, 0.726F);
//		tongue.addChild(cube_r63);
//		setRotationAngle(cube_r63, -0.0411F, -0.3027F, 0.3553F);
//		cube_r63.setTextureOffset(0, 117).addBox(-2.25F, 2.25F, -3.5F, 4.0F, 2.0F, 7.0F, 0.0F, false);
//		cube_r63.setTextureOffset(0, 117).addBox(-1.75F, -2.5F, -3.0F, 3.0F, 5.0F, 6.0F, 0.0F, false);
//
//		cube_r64 = new ModelPart(this);
//		cube_r64.setRotation(7.034F, -21.0937F, 1.3564F);
//		tongue.addChild(cube_r64);
//		setRotationAngle(cube_r64, -0.1458F, 0.0529F, 1.1587F);
//		cube_r64.setTextureOffset(0, 115).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
//
//		cube_r65 = new ModelPart(this);
//		cube_r65.setRotation(4.4383F, -21.6892F, 0.726F);
//		tongue.addChild(cube_r65);
//		setRotationAngle(cube_r65, -0.1037F, -0.1129F, 0.564F);
//		cube_r65.setTextureOffset(0, 115).addBox(0.75F, -0.5F, -2.0F, 2.0F, 3.0F, 4.0F, 0.0F, false);
//
//		cube_r66 = new ModelPart(this);
//		cube_r66.setRotation(1.0F, -24.5F, 0.0F);
//		tongue.addChild(cube_r66);
//		setRotationAngle(cube_r66, -0.0097F, -0.218F, 0.2629F);
//		cube_r66.setTextureOffset(0, 115).addBox(3.6F, 4.5F, -2.75F, 2.0F, 8.0F, 5.0F, 0.0F, false);
//
//		tongue_1 = new ModelPart(this);
//		tongue_1.setRotation(0.0F, 0.0F, 0.0F);
//		tongue.addChild(tongue_1);
//
//
//		cube_r67 = new ModelPart(this);
//		cube_r67.setRotation(8.6747F, -17.1087F, 2.3565F);
//		tongue_1.addChild(cube_r67);
//		setRotationAngle(cube_r67, 0.0853F, 0.0592F, 2.8274F);
//		cube_r67.setTextureOffset(0, 115).addBox(-1.8003F, -0.7251F, -2.98F, 2.0F, 3.0F, 4.0F, 0.0F, false);
//
//		cube_r68 = new ModelPart(this);
//		cube_r68.setRotation(7.034F, -21.0937F, 1.3564F);
//		tongue_1.addChild(cube_r68);
//		setRotationAngle(cube_r68, -0.0779F, 0.1342F, 1.8554F);
//		cube_r68.setTextureOffset(0, 115).addBox(-1.0F, -1.75F, -1.75F, 2.0F, 2.0F, 4.0F, 0.0F, false);
//
//		cube_r69 = new ModelPart(this);
//		cube_r69.setRotation(8.6747F, -17.1087F, 2.3565F);
//		tongue_1.addChild(cube_r69);
//		setRotationAngle(cube_r69, 0.1154F, 0.1745F, 2.4119F);
//		cube_r69.setTextureOffset(0, 115).addBox(-2.6793F, 1.0465F, -3.2315F, 2.0F, 2.0F, 4.0F, 0.0F, false);
//
//		tongue_2 = new ModelPart(this);
//		tongue_2.setRotation(0.0F, 0.0F, 0.0F);
//		tongue_1.addChild(tongue_2);
//
//
//		cube_r70 = new ModelPart(this);
//		cube_r70.setRotation(8.6747F, -17.1087F, 2.3565F);
//		tongue_2.addChild(cube_r70);
//		setRotationAngle(cube_r70, 0.0976F, 0.0351F, 3.0884F);
//		cube_r70.setTextureOffset(0, 115).addBox(-1.5639F, -2.9796F, -2.48F, 2.0F, 3.0F, 3.0F, 0.0F, false);
//
//		cube_r71 = new ModelPart(this);
//		cube_r71.setRotation(8.6747F, -17.1087F, 2.3565F);
//		tongue_2.addChild(cube_r71);
//		setRotationAngle(cube_r71, 0.7085F, 0.0351F, 3.0884F);
//		cube_r71.setTextureOffset(0, 115).addBox(-1.1639F, -3.7241F, -0.204F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//		eyes = new ModelPart(this);
//		eyes.setRotation(-0.1707F, 12.8042F, 0.2506F);
//		body.addChild(eyes);
//
//
//		left_eye = new ModelPart(this);
//		left_eye.setRotation(0.0F, 0.2202F, 1.7811F);
//		eyes.addChild(left_eye);
//		setRotationAngle(left_eye, 1.0664F, 0.3046F, -0.0004F);
//
//
//		left_eye_yaw = new ModelPart(this);
//		left_eye_yaw.setRotation(5.7235F, -3.0705F, 20.5489F);
//		left_eye.addChild(left_eye_yaw);
//
//
//		left_eye_pitch = new ModelPart(this);
//		left_eye_pitch.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_yaw.addChild(left_eye_pitch);
//		left_eye_pitch.setTextureOffset(0, 44).addBox(-0.5235F, -1.1609F, -0.7279F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//		left_eye_pitch.setTextureOffset(27, 35).addBox(-1.0235F, -0.9109F, -1.2279F, 2.0F, 3.0F, 2.0F, 0.0F, false);
//
//		eye_r1 = new ModelPart(this);
//		eye_r1.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r1);
//		setRotationAngle(eye_r1, -1.8414F, 1.2587F, -0.2908F);
//		eye_r1.setTextureOffset(38, 51).addBox(-0.9044F, -1.8866F, 0.0109F, 2.0F, 1.0F, 2.0F, 0.0F, false);
//
//		eye_r2 = new ModelPart(this);
//		eye_r2.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r2);
//		setRotationAngle(eye_r2, -0.0436F, 0.0F, 0.0F);
//		eye_r2.setTextureOffset(38, 51).addBox(-1.4235F, 0.1005F, 0.6063F, 3.0F, 2.0F, 1.0F, 0.0F, false);
//
//		eye_r3 = new ModelPart(this);
//		eye_r3.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r3);
//		setRotationAngle(eye_r3, 0.0F, 0.0F, 0.0F);
//		eye_r3.setTextureOffset(35, 35).addBox(-1.5235F, -0.4109F, -1.7279F, 3.0F, 2.0F, 3.0F, 0.0F, false);
//
//		eye_r4 = new ModelPart(this);
//		eye_r4.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r4);
//		setRotationAngle(eye_r4, 0.219F, 0.3286F, 0.6037F);
//		eye_r4.setTextureOffset(1, 45).addBox(-0.5047F, -1.1181F, -0.9387F, 1.0F, 0.0F, 1.0F, 0.0F, true);
//
//		eye_r5 = new ModelPart(this);
//		eye_r5.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r5);
//		setRotationAngle(eye_r5, 0.219F, -0.3286F, -0.6037F);
//		eye_r5.setTextureOffset(2, 44).addBox(-0.5319F, -1.1414F, -0.9207F, 1.0F, 0.0F, 1.0F, 0.0F, false);
//
//		eye_r6 = new ModelPart(this);
//		eye_r6.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r6);
//		setRotationAngle(eye_r6, -0.219F, -0.3286F, 0.6037F);
//		eye_r6.setTextureOffset(-1, 44).addBox(-0.6518F, -1.0243F, -0.4824F, 1.0F, 0.0F, 1.0F, 0.0F, false);
//
//		eye_r7 = new ModelPart(this);
//		eye_r7.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r7);
//		setRotationAngle(eye_r7, -0.219F, 0.3286F, -0.6037F);
//		eye_r7.setTextureOffset(2, 44).addBox(-0.3848F, -1.0477F, -0.5004F, 1.0F, 0.0F, 1.0F, 0.0F, false);
//
//		eye_r8 = new ModelPart(this);
//		eye_r8.setRotation(0.0F, 0.0F, 0.0F);
//		left_eye_pitch.addChild(eye_r8);
//		setRotationAngle(eye_r8, -1.8414F, -1.2587F, 0.2908F);
//		eye_r8.setTextureOffset(38, 51).addBox(-0.9954F, -1.821F, 0.141F, 2.0F, 1.0F, 2.0F, 0.0F, true);
//
//		right_eye = new ModelPart(this);
//		right_eye.setRotation(0.0F, 0.2202F, 1.7811F);
//		eyes.addChild(right_eye);
//		setRotationAngle(right_eye, 1.0664F, -0.3046F, 0.0004F);
//
//
//		right_eye_yaw = new ModelPart(this);
//		right_eye_yaw.setRotation(-5.7235F, -3.0705F, 20.5489F);
//		right_eye.addChild(right_eye_yaw);
//
//
//		right_eye_pitch = new ModelPart(this);
//		right_eye_pitch.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_yaw.addChild(right_eye_pitch);
//		right_eye_pitch.setTextureOffset(0, 44).addBox(-0.4765F, -1.1609F, -0.7279F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//		right_eye_pitch.setTextureOffset(27, 35).addBox(-0.9765F, -0.9109F, -1.2279F, 2.0F, 3.0F, 2.0F, 0.0F, true);
//
//		eye_r9 = new ModelPart(this);
//		eye_r9.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r9);
//		setRotationAngle(eye_r9, -1.8414F, -1.2587F, 0.2908F);
//		eye_r9.setTextureOffset(38, 51).addBox(-1.0956F, -1.8866F, 0.0109F, 2.0F, 1.0F, 2.0F, 0.0F, true);
//
//		eye_r10 = new ModelPart(this);
//		eye_r10.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r10);
//		setRotationAngle(eye_r10, -0.0436F, 0.0F, 0.0F);
//		eye_r10.setTextureOffset(38, 51).addBox(-1.5765F, 0.1005F, 0.6063F, 3.0F, 2.0F, 1.0F, 0.0F, true);
//
//		eye_r11 = new ModelPart(this);
//		eye_r11.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r11);
//		setRotationAngle(eye_r11, 0.0F, 0.0F, 0.0F);
//		eye_r11.setTextureOffset(35, 35).addBox(-1.4765F, -0.4109F, -1.7279F, 3.0F, 2.0F, 3.0F, 0.0F, true);
//
//		eye_r12 = new ModelPart(this);
//		eye_r12.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r12);
//		setRotationAngle(eye_r12, 0.219F, -0.3286F, -0.6037F);
//		eye_r12.setTextureOffset(1, 45).addBox(-0.4953F, -1.1181F, -0.9387F, 1.0F, 0.0F, 1.0F, 0.0F, false);
//
//		eye_r13 = new ModelPart(this);
//		eye_r13.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r13);
//		setRotationAngle(eye_r13, 0.219F, 0.3286F, 0.6037F);
//		eye_r13.setTextureOffset(2, 44).addBox(-0.4681F, -1.1414F, -0.9207F, 1.0F, 0.0F, 1.0F, 0.0F, true);
//
//		eye_r14 = new ModelPart(this);
//		eye_r14.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r14);
//		setRotationAngle(eye_r14, -0.219F, 0.3286F, -0.6037F);
//		eye_r14.setTextureOffset(-1, 44).addBox(-0.3482F, -1.0243F, -0.4824F, 1.0F, 0.0F, 1.0F, 0.0F, true);
//
//		eye_r15 = new ModelPart(this);
//		eye_r15.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r15);
//		setRotationAngle(eye_r15, -0.219F, -0.3286F, 0.6037F);
//		eye_r15.setTextureOffset(2, 44).addBox(-0.6152F, -1.0477F, -0.5004F, 1.0F, 0.0F, 1.0F, 0.0F, true);
//
//		eye_r16 = new ModelPart(this);
//		eye_r16.setRotation(0.0F, 0.0F, 0.0F);
//		right_eye_pitch.addChild(eye_r16);
//		setRotationAngle(eye_r16, -1.8414F, 1.2587F, -0.2908F);
//		eye_r16.setTextureOffset(38, 51).addBox(-1.0046F, -1.821F, 0.141F, 2.0F, 1.0F, 2.0F, 0.0F, false);
//
//		LeftLegFront = new ModelPart(this);
//		LeftLegFront.setRotation(7.75F, 21.5F, -6.75F);
//
//
//		left_leg_front_1 = new ModelPart(this);
//		left_leg_front_1.setRotation(0.0F, 0.0F, 0.0F);
//		LeftLegFront.addChild(left_leg_front_1);
//
//
//		left_leg_front_2 = new ModelPart(this);
//		left_leg_front_2.setRotation(0.0F, 0.0F, 0.0F);
//		left_leg_front_1.addChild(left_leg_front_2);
//		left_leg_front_2.setTextureOffset(0, 24).addBox(1.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, false);
//
//		left_leg_front_3 = new ModelPart(this);
//		left_leg_front_3.setRotation(16.25F, 0.0F, 0.0F);
//		left_leg_front_2.addChild(left_leg_front_3);
//		left_leg_front_3.setTextureOffset(0, 24).addBox(0.75F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, false);
//
//		left_leg_front_tip = new ModelPart(this);
//		left_leg_front_tip.setRotation(15.0F, 0.0F, 0.0F);
//		left_leg_front_3.addChild(left_leg_front_tip);
//
//
//		cube_r16 = new ModelPart(this);
//		cube_r16.setRotation(5.412F, -4.3771F, -0.588F);
//		left_leg_front_tip.addChild(cube_r16);
//		setRotationAngle(cube_r16, 0.0F, 0.0F, 0.5236F);
//		cube_r16.setTextureOffset(3, 17).addBox(1.5F, 3.0912F, 0.044F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r17 = new ModelPart(this);
//		cube_r17.setRotation(-10.5F, -3.5F, 0.0F);
//		left_leg_front_tip.addChild(cube_r17);
//		setRotationAngle(cube_r17, 0.0F, 1.5708F, 0.0F);
//		cube_r17.setTextureOffset(0, 19).addBox(-1.0F, 2.5F, 11.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
//
//		cube_r18 = new ModelPart(this);
//		cube_r18.setRotation(-0.0832F, -3.9396F, -0.0332F);
//		left_leg_front_tip.addChild(cube_r18);
//		setRotationAngle(cube_r18, 0.0F, 0.0F, 0.2618F);
//		cube_r18.setTextureOffset(15, 24).addBox(-0.9647F, 2.8637F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//		cube_r18.setTextureOffset(15, 24).addBox(-0.4647F, 1.8637F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
//
//		cube_r15 = new ModelPart(this);
//		cube_r15.setRotation(-0.0831F, -3.9396F, -0.0331F);
//		left_leg_front_3.addChild(cube_r15);
//		setRotationAngle(cube_r15, 0.0F, 0.0F, -0.2618F);
//		cube_r15.setTextureOffset(15, 24).addBox(-2.5353F, 1.8637F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
//		cube_r15.setTextureOffset(15, 24).addBox(-3.0353F, 2.8637F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//
//		cube_r14 = new ModelPart(this);
//		cube_r14.setRotation(-0.5F, -3.5F, 0.0F);
//		left_leg_front_2.addChild(cube_r14);
//		setRotationAngle(cube_r14, 0.0F, 1.5708F, 0.2618F);
//		cube_r14.setTextureOffset(15, 24).addBox(-2.0F, 2.3637F, -0.9647F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//		cube_r14.setTextureOffset(15, 24).addBox(-1.5F, 1.3637F, -0.4647F, 3.0F, 4.0F, 3.0F, 0.0F, false);
//
//		LeftLegBack = new ModelPart(this);
//		LeftLegBack.setRotation(7.0F, 17.5F, 7.75F);
//
//
//		left_leg_back_1 = new ModelPart(this);
//		left_leg_back_1.setRotation(0.0F, 0.0F, 0.0F);
//		LeftLegBack.addChild(left_leg_back_1);
//
//
//		left_leg_back_2 = new ModelPart(this);
//		left_leg_back_2.setRotation(0.0F, 0.0F, 0.0F);
//		left_leg_back_1.addChild(left_leg_back_2);
//		left_leg_back_2.setTextureOffset(0, 24).addBox(1.75F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, false);
//
//		left_leg_back_3 = new ModelPart(this);
//		left_leg_back_3.setRotation(17.0F, 0.0F, 0.0F);
//		left_leg_back_2.addChild(left_leg_back_3);
//		left_leg_back_3.setTextureOffset(0, 24).addBox(0.75F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, false);
//
//		left_leg_back_tip = new ModelPart(this);
//		left_leg_back_tip.setRotation(15.0F, 0.0F, 0.0F);
//		left_leg_back_3.addChild(left_leg_back_tip);
//
//
//		cube_r4 = new ModelPart(this);
//		cube_r4.setRotation(5.412F, -4.3771F, -16.088F);
//		left_leg_back_tip.addChild(cube_r4);
//		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.5236F);
//		cube_r4.setTextureOffset(3, 17).addBox(1.5F, 3.0912F, 15.544F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r5 = new ModelPart(this);
//		cube_r5.setRotation(-10.5F, -3.5F, -15.5F);
//		left_leg_back_tip.addChild(cube_r5);
//		setRotationAngle(cube_r5, 0.0F, 1.5708F, 0.0F);
//		cube_r5.setTextureOffset(0, 19).addBox(-16.5F, 2.5F, 10.9999F, 2.0F, 2.0F, 5.0F, 0.0F, false);
//
//		cube_r6 = new ModelPart(this);
//		cube_r6.setRotation(-0.0832F, -3.9396F, -15.5332F);
//		left_leg_back_tip.addChild(cube_r6);
//		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.2618F);
//		cube_r6.setTextureOffset(15, 24).addBox(-0.9647F, 2.8637F, 13.5F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//		cube_r6.setTextureOffset(15, 24).addBox(-0.4647F, 1.8637F, 14.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);
//
//		cube_r7 = new ModelPart(this);
//		cube_r7.setRotation(-0.0831F, -3.9396F, -15.5331F);
//		left_leg_back_3.addChild(cube_r7);
//		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.2618F);
//		cube_r7.setTextureOffset(15, 24).addBox(-2.5353F, 1.8637F, 14.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);
//		cube_r7.setTextureOffset(15, 24).addBox(-3.0353F, 2.8637F, 13.5F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//
//		cube_r8 = new ModelPart(this);
//		cube_r8.setRotation(0.25F, -3.5F, -15.5F);
//		left_leg_back_2.addChild(cube_r8);
//		setRotationAngle(cube_r8, 0.0F, 1.5708F, 0.2618F);
//		cube_r8.setTextureOffset(15, 24).addBox(-17.5F, 2.3637F, -0.9648F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//		cube_r8.setTextureOffset(15, 24).addBox(-17.0F, 1.3637F, -0.4648F, 3.0F, 4.0F, 3.0F, 0.0F, false);
//
//		RightLegBack = new ModelPart(this);
//		RightLegBack.setRotation(-7.0F, 17.5F, 7.75F);
//
//
//		right_leg_back_1 = new ModelPart(this);
//		right_leg_back_1.setRotation(0.0F, 0.0F, 0.0F);
//		RightLegBack.addChild(right_leg_back_1);
//
//
//		right_leg_back_2 = new ModelPart(this);
//		right_leg_back_2.setRotation(0.0F, 0.0F, 0.0F);
//		right_leg_back_1.addChild(right_leg_back_2);
//		right_leg_back_2.setTextureOffset(0, 24).addBox(-15.75F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, true);
//
//		right_leg_back_3 = new ModelPart(this);
//		right_leg_back_3.setRotation(-17.0F, 0.0F, 0.0F);
//		right_leg_back_2.addChild(right_leg_back_3);
//		right_leg_back_3.setTextureOffset(0, 24).addBox(-14.75F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, true);
//
//		right_leg_back_tip = new ModelPart(this);
//		right_leg_back_tip.setRotation(-15.0F, 0.0F, 0.0F);
//		right_leg_back_3.addChild(right_leg_back_tip);
//
//
//		cube_r9 = new ModelPart(this);
//		cube_r9.setRotation(-5.412F, -4.3771F, -16.088F);
//		right_leg_back_tip.addChild(cube_r9);
//		setRotationAngle(cube_r9, 0.0F, 0.0F, -0.5236F);
//		cube_r9.setTextureOffset(3, 17).addBox(-4.5F, 3.0912F, 15.544F, 3.0F, 1.0F, 1.0F, 0.0F, true);
//
//		cube_r10 = new ModelPart(this);
//		cube_r10.setRotation(10.5F, -3.5F, -15.5F);
//		right_leg_back_tip.addChild(cube_r10);
//		setRotationAngle(cube_r10, 0.0F, -1.5708F, 0.0F);
//		cube_r10.setTextureOffset(0, 19).addBox(14.5F, 2.5F, 10.9999F, 2.0F, 2.0F, 5.0F, 0.0F, true);
//
//		cube_r11 = new ModelPart(this);
//		cube_r11.setRotation(0.0832F, -3.9396F, -15.5332F);
//		right_leg_back_tip.addChild(cube_r11);
//		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.2618F);
//		cube_r11.setTextureOffset(15, 24).addBox(-3.0353F, 2.8637F, 13.5F, 4.0F, 2.0F, 4.0F, 0.0F, true);
//		cube_r11.setTextureOffset(15, 24).addBox(-2.5353F, 1.8637F, 14.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);
//
//		cube_r12 = new ModelPart(this);
//		cube_r12.setRotation(0.0831F, -3.9396F, -15.5331F);
//		right_leg_back_3.addChild(cube_r12);
//		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.2618F);
//		cube_r12.setTextureOffset(15, 24).addBox(-0.4647F, 1.8637F, 14.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);
//		cube_r12.setTextureOffset(15, 24).addBox(-0.9647F, 2.8637F, 13.5F, 4.0F, 2.0F, 4.0F, 0.0F, true);
//
//		cube_r13 = new ModelPart(this);
//		cube_r13.setRotation(-0.25F, -3.5F, -15.5F);
//		right_leg_back_2.addChild(cube_r13);
//		setRotationAngle(cube_r13, 0.0F, -1.5708F, -0.2618F);
//		cube_r13.setTextureOffset(15, 24).addBox(13.5F, 2.3637F, -0.9648F, 4.0F, 2.0F, 4.0F, 0.0F, true);
//		cube_r13.setTextureOffset(15, 24).addBox(14.0F, 1.3637F, -0.4648F, 3.0F, 4.0F, 3.0F, 0.0F, true);
//
//		RightLegFront = new ModelPart(this);
//		RightLegFront.setRotation(-7.0F, 21.5F, -6.75F);
//
//
//		right_leg_front_1 = new ModelPart(this);
//		right_leg_front_1.setRotation(0.0F, 0.0F, 0.0F);
//		RightLegFront.addChild(right_leg_front_1);
//
//
//		right_leg_front_2 = new ModelPart(this);
//		right_leg_front_2.setRotation(0.0F, 0.0F, 0.0F);
//		right_leg_front_1.addChild(right_leg_front_2);
//		right_leg_front_2.setTextureOffset(0, 24).addBox(-15.75F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, true);
//
//		right_leg_front_3 = new ModelPart(this);
//		right_leg_front_3.setRotation(-17.0F, 0.0F, 0.0F);
//		right_leg_front_2.addChild(right_leg_front_3);
//		right_leg_front_3.setTextureOffset(0, 24).addBox(-14.75F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, 0.0F, true);
//
//		right_leg_front_tip = new ModelPart(this);
//		right_leg_front_tip.setRotation(-15.0F, 0.0F, 0.0F);
//		right_leg_front_3.addChild(right_leg_front_tip);
//
//
//		cube_r19 = new ModelPart(this);
//		cube_r19.setRotation(-5.412F, -4.3771F, 16.088F);
//		right_leg_front_tip.addChild(cube_r19);
//		setRotationAngle(cube_r19, 0.0F, 0.0F, -0.5236F);
//		cube_r19.setTextureOffset(3, 17).addBox(-4.5F, 3.0912F, -16.544F, 3.0F, 1.0F, 1.0F, 0.0F, true);
//
//		cube_r20 = new ModelPart(this);
//		cube_r20.setRotation(10.5F, -3.5F, 15.5F);
//		right_leg_front_tip.addChild(cube_r20);
//		setRotationAngle(cube_r20, 0.0F, 1.5708F, 0.0F);
//		cube_r20.setTextureOffset(0, 19).addBox(14.5F, 2.5F, -15.9999F, 2.0F, 2.0F, 5.0F, 0.0F, true);
//
//		cube_r21 = new ModelPart(this);
//		cube_r21.setRotation(0.0832F, -3.9396F, 15.5332F);
//		right_leg_front_tip.addChild(cube_r21);
//		setRotationAngle(cube_r21, 0.0F, 0.0F, -0.2618F);
//		cube_r21.setTextureOffset(15, 24).addBox(-3.0353F, 2.8637F, -17.5F, 4.0F, 2.0F, 4.0F, 0.0F, true);
//		cube_r21.setTextureOffset(15, 24).addBox(-2.5353F, 1.8637F, -17.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);
//
//		cube_r22 = new ModelPart(this);
//		cube_r22.setRotation(0.0831F, -3.9396F, 15.5331F);
//		right_leg_front_3.addChild(cube_r22);
//		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.2618F);
//		cube_r22.setTextureOffset(15, 24).addBox(-0.4647F, 1.8637F, -17.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);
//		cube_r22.setTextureOffset(15, 24).addBox(-0.9647F, 2.8637F, -17.5F, 4.0F, 2.0F, 4.0F, 0.0F, true);
//
//		cube_r23 = new ModelPart(this);
//		cube_r23.setRotation(-0.25F, -3.5F, 15.5F);
//		right_leg_front_2.addChild(cube_r23);
//		setRotationAngle(cube_r23, 0.0F, 1.5708F, -0.2618F);
//		cube_r23.setTextureOffset(15, 24).addBox(13.5F, 2.3637F, -3.0352F, 4.0F, 2.0F, 4.0F, 0.0F, true);
//		cube_r23.setTextureOffset(15, 24).addBox(14.0F, 1.3637F, -2.5352F, 3.0F, 4.0F, 3.0F, 0.0F, true);
//	}
//
//	@Override
//	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount,
//								  float ageInTicks, float netHeadYaw, float headPitch) {
//		this.left_eye_pitch.rotateAngleX = headPitch * ((float)Math.PI / 180F);
//		this.left_eye_yaw.rotateAngleZ = netHeadYaw * (-(float)Math.PI / 180F);
//		this.right_eye_pitch.rotateAngleX = headPitch * ((float)Math.PI / 180F);
//		this.right_eye_yaw.rotateAngleZ = netHeadYaw * (-(float)Math.PI / 180F);
//
//
//		this.RightLegFront.rotateAngleY = (-(float)Math.PI / 2.5F);
//		this.LeftLegFront.rotateAngleY = ((float)Math.PI / 2.5F);
//		this.RightLegBack.rotateAngleY = ((float)Math.PI / 8F);
//		this.LeftLegBack.rotateAngleY = (-(float)Math.PI / 8F);
//
//		this.right_leg_front_2.rotateAngleZ = ((float)Math.PI / 4.25F);
//		this.left_leg_front_2.rotateAngleZ = (-(float)Math.PI / 4.25F);
//		this.right_leg_back_2.rotateAngleZ = ((float)Math.PI / 3.25F);
//		this.left_leg_back_2.rotateAngleZ = (-(float)Math.PI / 3.25F);
//
//		this.right_leg_front_3.rotateAngleZ = (-(float)Math.PI / 2.5F);
//		this.left_leg_front_3.rotateAngleZ = ((float)Math.PI / 2.5F);
//		this.right_leg_back_3.rotateAngleZ = (-(float)Math.PI / 1.5F);
//		this.left_leg_back_3.rotateAngleZ = ((float)Math.PI / 1.5F);
//
//		this.right_leg_front_tip.rotateAngleZ = (-(float)Math.PI / 5F);
//		this.left_leg_front_tip.rotateAngleZ = ((float)Math.PI / 5F);
//		this.right_leg_back_tip.rotateAngleZ = (-(float)Math.PI / 5F);
//		this.left_leg_back_tip.rotateAngleZ = ((float)Math.PI / 5F);
//
////		float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
////		float f6 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
////		float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
////		float f10 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
////		this.RightLegFront.rotateAngleY += -f3;
////		this.LeftLegFront.rotateAngleY += f3;
////		this.RightLegBack.rotateAngleY += -f6;
////		this.LeftLegBack.rotateAngleY += f6;
////		this.RightLegFront.rotateAngleZ += -f7;
////		this.LeftLegFront.rotateAngleZ += f7;
////		this.RightLegBack.rotateAngleZ += -f10;
////		this.LeftLegBack.rotateAngleZ += f10;
//	}
//
//	@Override
//	public void render(PoseStack matrixStack, VertexConsumer buffer, int packedLight,
//					   int packedOverlay, float red, float green, float blue, float alpha) {
//		//head.render(matrixStack, buffer, packedLight, packedOverlay);
//		body.render(matrixStack, buffer, packedLight, packedOverlay);
//		RightLegBack.render(matrixStack, buffer, packedLight, packedOverlay);
//		LeftLegBack.render(matrixStack, buffer, packedLight, packedOverlay);
//		RightLegFront.render(matrixStack, buffer, packedLight, packedOverlay);
//		LeftLegFront.render(matrixStack, buffer, packedLight, packedOverlay);
//	}
//
//	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
//}