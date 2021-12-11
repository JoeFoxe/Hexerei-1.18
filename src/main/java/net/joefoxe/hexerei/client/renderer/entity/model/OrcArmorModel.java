//package net.joefoxe.hexerei.client.renderer.entity.model;
//
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.model.HumanoidModel;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.renderer.model.ModelPart;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
//import net.minecraft.entity.monster.AbstractIllagerEntity;
//import net.minecraft.entity.monster.ZombieVillagerEntity;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.monster.AbstractIllager;
//import net.minecraft.world.entity.monster.ZombieVillager;
//import net.minecraft.world.entity.npc.AbstractVillager;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class OrcArmorModel <T extends LivingEntity> extends ArmorModel<T> {
//
//    private static final Map<Integer, OrcArmorModel<? extends LivingEntity>> CACHE = new HashMap<>();
//
//    private final ModelPart Head;
//    private final ModelPart Head_r1;
//    private final ModelPart Head_r2;
//    private final ModelPart Head_r3;
//    private final ModelPart Head_r4;
//    private final ModelPart Head_r5;
//    private final ModelPart Head_r6;
//    private final ModelPart Head_r7;
//    private final ModelPart Head_r8;
//    private final ModelPart Head_r9;
//    private final ModelPart Head_r10;
//    private final ModelPart Head_r11;
//    private final ModelPart Head_r12;
//    private final ModelPart Head_r13;
//    private final ModelPart Head_r14;
//    private final ModelPart Head_r15;
//    private final ModelPart Head_r16;
//    private final ModelPart Head_r17;
//    private final ModelPart Head_r18;
//    private final ModelPart Head_r19;
//    private final ModelPart Head_r20;
//    private final ModelPart Head_r21;
//    private final ModelPart Head_r22;
//    private final ModelPart Head_r23;
//    private final ModelPart Head_r24;
//    private final ModelPart Body;
//    private final ModelPart Body_r1;
//    private final ModelPart Body_r2;
//    private final ModelPart Body_r3;
//    private final ModelPart Body_r4;
//    private final ModelPart RightArm;
//    private final ModelPart RightArm_r1;
//    private final ModelPart RightArm_r2;
//    private final ModelPart RightArm_r3;
//    private final ModelPart RightArm_r4;
//    private final ModelPart RightArm_r5;
//    private final ModelPart RightArm_r6;
//    private final ModelPart RightArm_r7;
//    private final ModelPart RightArm_r8;
//    private final ModelPart RightArm_r9;
//    private final ModelPart RightArm_r10;
//    private final ModelPart RightArm_r11;
//    private final ModelPart RightArm_r12;
//    private final ModelPart RightArm_r13;
//    private final ModelPart RightArm_r14;
//    private final ModelPart RightArm_r15;
//    private final ModelPart RightArm_r16;
//    private final ModelPart RightArm_r17;
//    private final ModelPart RightArm_r18;
//    private final ModelPart RightArm_r19;
//    private final ModelPart RightArm_r20;
//    private final ModelPart Belt;
//    private final ModelPart Belt_r1;
//    private final ModelPart Belt_r2;
//    private final ModelPart Belt_r3;
//    private final ModelPart Belt_r4;
//    private final ModelPart Belt_r5;
//    private final ModelPart Belt_r6;
//    private final ModelPart Belt_r7;
//    private final ModelPart RightLeg;
//    private final ModelPart LeftLeg;
//    private final ModelPart LeftBoot;
//    private final ModelPart RightBoot;
//    private final EquipmentSlot slot;
//    private final byte entityFlag;
//
//    private OrcArmorModel(float modelSize, EquipmentSlot slotType) {
//        super(modelSize, slotType);
//        this.slot = slotType;
//        this.entityFlag = (byte) (0 >> 4);
//
//
//        textureWidth = 128;
//        textureHeight = 128;
//
//
//        Head = new ModelPart(this);
//        Head.setRotation(0.0F, 0.0F, 0.0F);
//        Head.setTextureOffset(0, 0).addBox(-5.0F, -8.5F, -5.0F, 10.0F, 9.0F, 10.0F, 0.0F, false);
//        Head.setTextureOffset(0, 0).addBox(5.0F, -5.5F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//        Head.setTextureOffset(0, 19).addBox(-5.5F, -7.5F, -4.0F, 11.0F, 7.0F, 8.0F, 0.0F, false);
//        Head.setTextureOffset(0, 19).addBox(6.0F, -5.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//        Head.setTextureOffset(0, 19).addBox(-8.0F, -5.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
//        Head.setTextureOffset(0, 0).addBox(-6.0F, -5.5F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
//        Head.setTextureOffset(0, 34).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 9.0F, 9.0F, 0.0F, false);
//
//        Head_r1 = new ModelPart(this);
//        Head_r1.setRotation(0.479F, -10.0239F, 32.3807F);
//        Head.addChild(Head_r1);
//        setRotationAngle(Head_r1, 1.5708F, 0.0F, 0.5672F);
//        Head_r1.setTextureOffset(0, 5).addBox(4.0134F, -32.9954F, -0.9737F, 1.0F, 1.0F, 4.0F, 0.0F, true);
//
//        Head_r2 = new ModelPart(this);
//        Head_r2.setRotation(-0.479F, -10.0239F, 32.3807F);
//        Head.addChild(Head_r2);
//        setRotationAngle(Head_r2, 1.5708F, 0.0F, -0.5672F);
//        Head_r2.setTextureOffset(0, 5).addBox(-5.0134F, -32.9954F, -0.9737F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r3 = new ModelPart(this);
//        Head_r3.setRotation(-0.8478F, -11.3626F, 32.4538F);
//        Head.addChild(Head_r3);
//        setRotationAngle(Head_r3, 1.5708F, 0.0F, -0.5672F);
//        Head_r3.setTextureOffset(0, 52).addBox(-6.0203F, -37.0685F, -1.8647F, 2.0F, 9.0F, 2.0F, 0.0F, true);
//
//        Head_r4 = new ModelPart(this);
//        Head_r4.setRotation(0.8478F, -11.3626F, 32.4538F);
//        Head.addChild(Head_r4);
//        setRotationAngle(Head_r4, 1.5708F, 0.0F, 0.5672F);
//        Head_r4.setTextureOffset(0, 52).addBox(4.0203F, -37.0685F, -1.8647F, 2.0F, 9.0F, 2.0F, 0.0F, false);
//
//        Head_r5 = new ModelPart(this);
//        Head_r5.setRotation(0.0F, 25.7704F, 65.6372F);
//        Head.addChild(Head_r5);
//        setRotationAngle(Head_r5, 1.5708F, 0.0F, 0.0F);
//        Head_r5.setTextureOffset(0, 5).addBox(-0.4797F, -66.2518F, 33.3391F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//        Head_r5.setTextureOffset(0, 52).addBox(-0.9797F, -70.2518F, 33.3391F, 2.0F, 9.0F, 2.0F, 0.0F, false);
//
//        Head_r6 = new ModelPart(this);
//        Head_r6.setRotation(44.5448F, -52.7719F, -34.4268F);
//        Head.addChild(Head_r6);
//        setRotationAngle(Head_r6, -0.7516F, -0.05F, -2.7519F);
//        Head_r6.setTextureOffset(66, 19).addBox(18.5F, -68.1F, -25.5042F, 1.0F, 1.0F, 2.0F, 0.0F, true);
//
//        Head_r7 = new ModelPart(this);
//        Head_r7.setRotation(44.6796F, -53.3485F, -34.6649F);
//        Head.addChild(Head_r7);
//        setRotationAngle(Head_r7, -0.7516F, -0.05F, -2.7519F);
//        Head_r7.setTextureOffset(70, 16).addBox(18.4348F, -68.7203F, -23.7542F, 1.0F, 2.0F, 3.0F, 0.0F, true);
//
//        Head_r8 = new ModelPart(this);
//        Head_r8.setRotation(-44.6796F, -53.3485F, -34.6649F);
//        Head.addChild(Head_r8);
//        setRotationAngle(Head_r8, -0.7516F, 0.05F, 2.7519F);
//        Head_r8.setTextureOffset(70, 16).addBox(-19.4348F, -68.7203F, -23.7542F, 1.0F, 2.0F, 3.0F, 0.0F, false);
//
//        Head_r9 = new ModelPart(this);
//        Head_r9.setRotation(-44.5448F, -52.7719F, -34.4268F);
//        Head.addChild(Head_r9);
//        setRotationAngle(Head_r9, -0.7516F, 0.05F, 2.7519F);
//        Head_r9.setTextureOffset(66, 19).addBox(-19.5F, -68.1F, -25.5042F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        Head_r10 = new ModelPart(this);
//        Head_r10.setRotation(-8.2275F, 3.6434F, 28.0509F);
//        Head.addChild(Head_r10);
//        setRotationAngle(Head_r10, 1.0472F, 0.0F, 0.5672F);
//        Head_r10.setTextureOffset(0, 5).addBox(4.0134F, -29.6506F, -0.3767F, 1.0F, 1.0F, 4.0F, 0.0F, true);
//
//        Head_r11 = new ModelPart(this);
//        Head_r11.setRotation(8.2275F, 3.6434F, 28.0509F);
//        Head.addChild(Head_r11);
//        setRotationAngle(Head_r11, 1.0472F, 0.0F, -0.5672F);
//        Head_r11.setTextureOffset(0, 5).addBox(-5.0134F, -29.6506F, -0.3767F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r12 = new ModelPart(this);
//        Head_r12.setRotation(0.0F, 41.1759F, 27.9791F);
//        Head.addChild(Head_r12);
//        setRotationAngle(Head_r12, 1.0472F, 0.0F, 0.0F);
//        Head_r12.setTextureOffset(0, 5).addBox(-0.4797F, -46.3451F, 28.6826F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r13 = new ModelPart(this);
//        Head_r13.setRotation(-3.29F, -4.1074F, 31.6174F);
//        Head.addChild(Head_r13);
//        setRotationAngle(Head_r13, 1.3526F, 0.0F, 0.5672F);
//        Head_r13.setTextureOffset(0, 5).addBox(4.0134F, -30.9518F, -0.9378F, 1.0F, 1.0F, 4.0F, 0.0F, true);
//
//        Head_r14 = new ModelPart(this);
//        Head_r14.setRotation(3.29F, -4.1074F, 31.6174F);
//        Head.addChild(Head_r14);
//        setRotationAngle(Head_r14, 1.3526F, 0.0F, -0.5672F);
//        Head_r14.setTextureOffset(0, 5).addBox(-5.0134F, -30.9518F, -0.9378F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r15 = new ModelPart(this);
//        Head_r15.setRotation(0.0F, 36.4871F, 49.9089F);
//        Head.addChild(Head_r15);
//        setRotationAngle(Head_r15, 1.3526F, 0.0F, 0.0F);
//        Head_r15.setTextureOffset(0, 5).addBox(-0.4797F, -57.0386F, 32.2157F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r16 = new ModelPart(this);
//        Head_r16.setRotation(-3.314F, -4.0697F, -31.6532F);
//        Head.addChild(Head_r16);
//        setRotationAngle(Head_r16, -1.3526F, 0.0F, 0.5672F);
//        Head_r16.setTextureOffset(0, 5).addBox(4.0134F, -30.9965F, -3.0981F, 1.0F, 1.0F, 4.0F, 0.0F, true);
//
//        Head_r17 = new ModelPart(this);
//        Head_r17.setRotation(3.314F, -4.0697F, -31.6532F);
//        Head.addChild(Head_r17);
//        setRotationAngle(Head_r17, -1.3526F, 0.0F, -0.5672F);
//        Head_r17.setTextureOffset(0, 5).addBox(-5.0134F, -30.9965F, -3.0981F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r18 = new ModelPart(this);
//        Head_r18.setRotation(0.0F, 36.4871F, -49.9089F);
//        Head.addChild(Head_r18);
//        setRotationAngle(Head_r18, -1.3526F, 0.0F, 0.0F);
//        Head_r18.setTextureOffset(0, 5).addBox(-0.4797F, -57.0386F, -36.2157F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r19 = new ModelPart(this);
//        Head_r19.setRotation(-8.2488F, 3.6769F, -28.0738F);
//        Head.addChild(Head_r19);
//        setRotationAngle(Head_r19, -1.0472F, 0.0F, 0.5672F);
//        Head_r19.setTextureOffset(0, 5).addBox(4.0134F, -29.6903F, -3.6462F, 1.0F, 1.0F, 4.0F, 0.0F, true);
//
//        Head_r20 = new ModelPart(this);
//        Head_r20.setRotation(8.2488F, 3.6769F, -28.0738F);
//        Head.addChild(Head_r20);
//        setRotationAngle(Head_r20, -1.0472F, 0.0F, -0.5672F);
//        Head_r20.setTextureOffset(0, 5).addBox(-5.0134F, -29.6903F, -3.6462F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r21 = new ModelPart(this);
//        Head_r21.setRotation(0.0F, 41.1759F, -27.9791F);
//        Head.addChild(Head_r21);
//        setRotationAngle(Head_r21, -1.0472F, 0.0F, 0.0F);
//        Head_r21.setTextureOffset(0, 5).addBox(-0.4797F, -46.3451F, -32.6826F, 1.0F, 1.0F, 4.0F, 0.0F, false);
//
//        Head_r22 = new ModelPart(this);
//        Head_r22.setRotation(24.7329F, 39.4247F, 36.6226F);
//        Head.addChild(Head_r22);
//        setRotationAngle(Head_r22, 1.2859F, -0.274F, -0.7459F);
//        Head_r22.setTextureOffset(30, 6).addBox(7.7707F, -49.2099F, 35.6748F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//
//        Head_r23 = new ModelPart(this);
//        Head_r23.setRotation(25.7884F, 35.2455F, 45.4248F);
//        Head.addChild(Head_r23);
//        setRotationAngle(Head_r23, 1.2859F, -0.274F, -0.7459F);
//        Head_r23.setTextureOffset(30, 6).addBox(-5.1585F, -55.743F, 38.3807F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//
//        Head_r24 = new ModelPart(this);
//        Head_r24.setRotation(0.0F, 44.3905F, 36.3222F);
//        Head.addChild(Head_r24);
//        setRotationAngle(Head_r24, 1.1781F, 0.0F, 0.0F);
//        Head_r24.setTextureOffset(36, 0).addBox(-5.0F, -50.7347F, 37.4193F, 10.0F, 1.0F, 2.0F, 0.0F, false);
//
//        Body = new ModelPart(this);
//        Body.setRotation(0.0F, 0.0F, 0.0F);
//        Body.setTextureOffset(42, 32).addBox(-5.0F, 9.55F, -3.0F, 10.0F, 1.0F, 6.0F, 0.0F, false);
//        Body.setTextureOffset(42, 32).addBox(-5.0F, 10.55F, -3.0F, 10.0F, 1.0F, 6.0F, 0.0F, false);
//
//        Body_r1 = new ModelPart(this);
//        Body_r1.setRotation(6.731F, 30.731F, 0.0F);
//        Body.addChild(Body_r1);
//        setRotationAngle(Body_r1, 0.0F, 0.0F, -0.7854F);
//        Body_r1.setTextureOffset(84, 0).addBox(7.4905F, -22.0095F, 1.95F, 8.0F, 2.0F, 1.0F, 0.0F, false);
//        Body_r1.setTextureOffset(84, 0).addBox(7.4905F, -22.0095F, -2.95F, 8.0F, 2.0F, 1.0F, 0.0F, false);
//
//        Body_r2 = new ModelPart(this);
//        Body_r2.setRotation(-1.005F, 26.4263F, 0.0F);
//        Body.addChild(Body_r2);
//        setRotationAngle(Body_r2, 0.0F, 0.0F, 0.3927F);
//        Body_r2.setTextureOffset(78, 3).addBox(-13.6013F, -22.5631F, 1.96F, 11.0F, 2.0F, 1.0F, 0.0F, false);
//        Body_r2.setTextureOffset(78, 3).addBox(-13.6013F, -22.5631F, -2.96F, 11.0F, 2.0F, 1.0F, 0.0F, false);
//
//        Body_r3 = new ModelPart(this);
//        Body_r3.setRotation(3.0F, 24.0F, -9.7962F);
//        Body.addChild(Body_r3);
//        setRotationAngle(Body_r3, 0.0F, -1.5708F, 0.0F);
//        Body_r3.setTextureOffset(69, 0).addBox(6.7962F, -19.5216F, -2.3981F, 6.0F, 2.0F, 1.0F, 0.0F, false);
//
//        Body_r4 = new ModelPart(this);
//        Body_r4.setRotation(2.5F, 4.6818F, -3.3418F);
//        Body.addChild(Body_r4);
//        setRotationAngle(Body_r4, 0.0F, 0.0F, -1.1781F);
//        Body_r4.setTextureOffset(56, 3).addBox(-1.5F, -1.5F, 0.25F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//
//        RightArm = new ModelPart(this);
//        RightArm.setRotation(-5.0F, 2.0F, 0.0F);
//        RightArm.setTextureOffset(102, 34).addBox(-5.5F, -3.5F, -3.5F, 6.0F, 6.0F, 7.0F, 0.0F, false);
//        RightArm.setTextureOffset(106, 22).addBox(-4.5F, -2.5F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F, false);
//        RightArm.setTextureOffset(88, 41).addBox(-4.0F, 4.75F, -3.0F, 4.0F, 6.0F, 6.0F, 0.0F, false);
//        RightArm.setTextureOffset(100, 47).addBox(-5.94F, -4.14F, -3.99F, 6.0F, 6.0F, 8.0F, 0.0F, true);
//
//        RightArm_r1 = new ModelPart(this);
//        RightArm_r1.setRotation(-4.7954F, -0.2228F, 0.0F);
//        RightArm.addChild(RightArm_r1);
//        setRotationAngle(RightArm_r1, 0.0F, 0.0F, 0.6109F);
//        RightArm_r1.setTextureOffset(86, 14).addBox(-3.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
//
//        RightArm_r2 = new ModelPart(this);
//        RightArm_r2.setRotation(-6.844F, 0.2475F, 0.0F);
//        RightArm.addChild(RightArm_r2);
//        setRotationAngle(RightArm_r2, 3.1416F, 0.0F, -0.2618F);
//        RightArm_r2.setTextureOffset(86, 14).addBox(-0.25F, 0.0F, -1.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
//
//        RightArm_r3 = new ModelPart(this);
//        RightArm_r3.setRotation(-37.3208F, 38.0125F, -33.796F);
//        RightArm.addChild(RightArm_r3);
//        setRotationAngle(RightArm_r3, -1.0768F, 0.1771F, 1.1228F);
//        RightArm_r3.setTextureOffset(83, 14).addBox(-30.9989F, -47.229F, -31.6171F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//        RightArm_r3.setTextureOffset(83, 14).addBox(-30.7489F, -46.979F, -31.8671F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r4 = new ModelPart(this);
//        RightArm_r4.setRotation(-37.3208F, 38.0125F, 33.796F);
//        RightArm.addChild(RightArm_r4);
//        setRotationAngle(RightArm_r4, 1.0768F, -0.1771F, 1.1228F);
//        RightArm_r4.setTextureOffset(83, 14).addBox(-30.9989F, -47.229F, 29.6171F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//        RightArm_r4.setTextureOffset(83, 14).addBox(-30.7489F, -46.979F, 29.8671F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r5 = new ModelPart(this);
//        RightArm_r5.setRotation(-33.7486F, -73.7767F, 24.7004F);
//        RightArm.addChild(RightArm_r5);
//        setRotationAngle(RightArm_r5, 0.6926F, 0.3885F, -3.0124F);
//        RightArm_r5.setTextureOffset(86, 12).addBox(-26.6698F, -75.9607F, 8.2542F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        RightArm_r6 = new ModelPart(this);
//        RightArm_r6.setRotation(-33.7486F, -73.7767F, -24.7004F);
//        RightArm.addChild(RightArm_r6);
//        setRotationAngle(RightArm_r6, -0.6926F, -0.3885F, -3.0124F);
//        RightArm_r6.setTextureOffset(86, 12).addBox(-26.6698F, -75.9607F, -9.2542F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        RightArm_r7 = new ModelPart(this);
//        RightArm_r7.setRotation(-76.1129F, -11.5105F, 9.7021F);
//        RightArm.addChild(RightArm_r7);
//        setRotationAngle(RightArm_r7, -0.8177F, -0.9419F, 2.4841F);
//        RightArm_r7.setTextureOffset(90, 11).addBox(-43.4479F, -61.1187F, -13.1983F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r8 = new ModelPart(this);
//        RightArm_r8.setRotation(-76.1129F, -11.5105F, -9.7021F);
//        RightArm.addChild(RightArm_r8);
//        setRotationAngle(RightArm_r8, 0.8177F, 0.9419F, 2.4841F);
//        RightArm_r8.setTextureOffset(90, 11).addBox(-43.4479F, -61.1187F, 11.1983F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r9 = new ModelPart(this);
//        RightArm_r9.setRotation(-10.9759F, -27.1978F, -5.2811F);
//        RightArm.addChild(RightArm_r9);
//        setRotationAngle(RightArm_r9, -2.7296F, -0.8486F, -0.9174F);
//        RightArm_r9.setTextureOffset(86, 12).addBox(-10.2492F, -24.3911F, -4.6563F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        RightArm_r10 = new ModelPart(this);
//        RightArm_r10.setRotation(-34.115F, -75.5674F, -2.7241F);
//        RightArm.addChild(RightArm_r10);
//        setRotationAngle(RightArm_r10, -2.3707F, -1.1811F, -1.3326F);
//        RightArm_r10.setTextureOffset(90, 11).addBox(-26.1611F, -74.8057F, -10.5888F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r11 = new ModelPart(this);
//        RightArm_r11.setRotation(-10.9759F, -27.1978F, 5.2811F);
//        RightArm.addChild(RightArm_r11);
//        setRotationAngle(RightArm_r11, 2.7296F, 0.8486F, -0.9174F);
//        RightArm_r11.setTextureOffset(86, 12).addBox(-10.2492F, -24.3911F, 3.6563F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        RightArm_r12 = new ModelPart(this);
//        RightArm_r12.setRotation(-34.115F, -75.5674F, 2.7241F);
//        RightArm.addChild(RightArm_r12);
//        setRotationAngle(RightArm_r12, 2.3707F, 1.1811F, -1.3326F);
//        RightArm_r12.setTextureOffset(90, 11).addBox(-26.1611F, -74.8057F, 8.5888F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r13 = new ModelPart(this);
//        RightArm_r13.setRotation(-59.3749F, -54.8325F, 16.362F);
//        RightArm.addChild(RightArm_r13);
//        setRotationAngle(RightArm_r13, -1.3384F, -1.3947F, -2.7044F);
//        RightArm_r13.setTextureOffset(90, 11).addBox(-33.6902F, -72.1786F, -7.1379F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r14 = new ModelPart(this);
//        RightArm_r14.setRotation(-59.3749F, -54.8325F, -16.362F);
//        RightArm.addChild(RightArm_r14);
//        setRotationAngle(RightArm_r14, 1.3384F, 1.3947F, -2.7044F);
//        RightArm_r14.setTextureOffset(90, 11).addBox(-33.6902F, -72.1786F, 5.1379F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r15 = new ModelPart(this);
//        RightArm_r15.setRotation(-64.6298F, 19.6053F, -14.987F);
//        RightArm.addChild(RightArm_r15);
//        setRotationAngle(RightArm_r15, -0.8986F, -0.3292F, 1.8908F);
//        RightArm_r15.setTextureOffset(90, 11).addBox(-38.1292F, -51.7845F, -24.735F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r16 = new ModelPart(this);
//        RightArm_r16.setRotation(-64.6298F, 19.6053F, 14.987F);
//        RightArm.addChild(RightArm_r16);
//        setRotationAngle(RightArm_r16, 0.8986F, 0.3292F, 1.8908F);
//        RightArm_r16.setTextureOffset(90, 11).addBox(-38.1292F, -51.7845F, 22.735F, 1.0F, 1.0F, 2.0F, 0.0F, false);
//
//        RightArm_r17 = new ModelPart(this);
//        RightArm_r17.setRotation(-5.8389F, 41.7815F, -22.9519F);
//        RightArm.addChild(RightArm_r17);
//        setRotationAngle(RightArm_r17, -0.9228F, 0.3331F, 0.4321F);
//        RightArm_r17.setTextureOffset(88, 7).addBox(-25.1525F, -38.3442F, -27.0579F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//        RightArm_r18 = new ModelPart(this);
//        RightArm_r18.setRotation(-5.8389F, 41.7815F, 22.9519F);
//        RightArm.addChild(RightArm_r18);
//        setRotationAngle(RightArm_r18, 0.9228F, -0.3331F, 0.4321F);
//        RightArm_r18.setTextureOffset(88, 7).addBox(-25.1525F, -38.3442F, 25.0579F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//        RightArm_r19 = new ModelPart(this);
//        RightArm_r19.setRotation(-1.6266F, 36.3886F, 0.0F);
//        RightArm.addChild(RightArm_r19);
//        setRotationAngle(RightArm_r19, 0.0F, 0.0F, 0.7854F);
//        RightArm_r19.setTextureOffset(59, 3).addBox(-31.9479F, -27.9652F, -2.5F, 4.0F, 4.0F, 5.0F, 0.0F, false);
//        RightArm_r19.setTextureOffset(77, 6).addBox(-32.4479F, -27.4652F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
//        RightArm_r19.setTextureOffset(45, 3).addBox(-31.5979F, -28.4652F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);
//
//        RightArm_r20 = new ModelPart(this);
//        RightArm_r20.setRotation(-3.25F, 10.75F, -4.0F);
//        RightArm.addChild(RightArm_r20);
//        setRotationAngle(RightArm_r20, 0.0F, 0.0F, 0.7854F);
//        RightArm_r20.setTextureOffset(104, 53).addBox(-0.5F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        RightArm_r20.setTextureOffset(104, 53).addBox(-0.5F, -0.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        RightArm_r20.setTextureOffset(104, 53).addBox(-0.5F, -0.5F, 5.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        Belt = new ModelPart(this);
//        Belt.setRotation(0.0F, 0.0F, 0.0F);
//        Belt.setTextureOffset(82, 22).addBox(-1.0F, 9.4318F, -4.3418F, 2.0F, 2.0F, 1.0F, 0.0F, false);
//        Belt.setTextureOffset(79, 20).addBox(-0.5F, 10.9318F, -4.0918F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        Belt.setTextureOffset(80, 26).addBox(-1.5F, 8.75F, 2.1F, 3.0F, 2.0F, 1.0F, 0.0F, false);
//        Belt.setTextureOffset(80, 26).addBox(-1.5F, 8.75F, -3.1F, 3.0F, 2.0F, 1.0F, 0.0F, false);
//        Belt.setTextureOffset(51, 22).addBox(-5.5F, 10.5F, -3.5F, 11.0F, 2.0F, 7.0F, 0.0F, false);
//        Belt.setTextureOffset(42, 18).addBox(-5.75F, 13.25F, -3.5F, 1.0F, 4.0F, 7.0F, 0.0F, true);
//        Belt.setTextureOffset(42, 18).addBox(4.75F, 13.25F, -3.5F, 1.0F, 4.0F, 7.0F, 0.0F, false);
//        Belt.setTextureOffset(30, 19).addBox(-5.5F, 16.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, true);
//        Belt.setTextureOffset(30, 19).addBox(4.5F, 16.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
//        Belt.setTextureOffset(30, 26).addBox(-6.0F, 11.25F, -4.0F, 1.0F, 4.0F, 8.0F, 0.0F, true);
//        Belt.setTextureOffset(30, 26).addBox(5.0F, 11.25F, -4.0F, 1.0F, 4.0F, 8.0F, 0.0F, false);
//        Belt.setTextureOffset(42, 32).addBox(-5.0F, 10.55F, -3.0F, 10.0F, 1.0F, 6.0F, 0.0F, false);
//
//        Belt_r1 = new ModelPart(this);
//        Belt_r1.setRotation(-1.8827F, 10.6057F, -3.3418F);
//        Belt.addChild(Belt_r1);
//        setRotationAngle(Belt_r1, 0.6545F, 0.0F, -1.1781F);
//        Belt_r1.setTextureOffset(87, 20).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//
//        Belt_r2 = new ModelPart(this);
//        Belt_r2.setRotation(-1.7691F, 11.6253F, -3.1418F);
//        Belt.addChild(Belt_r2);
//        setRotationAngle(Belt_r2, -0.7854F, 0.0F, -2.0508F);
//        Belt_r2.setTextureOffset(83, 20).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        Belt_r3 = new ModelPart(this);
//        Belt_r3.setRotation(1.8827F, 10.6057F, -3.3418F);
//        Belt.addChild(Belt_r3);
//        setRotationAngle(Belt_r3, 1.0472F, 0.0F, 1.1781F);
//        Belt_r3.setTextureOffset(87, 20).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
//
//        Belt_r4 = new ModelPart(this);
//        Belt_r4.setRotation(1.7691F, 11.6253F, -3.1418F);
//        Belt.addChild(Belt_r4);
//        setRotationAngle(Belt_r4, -0.7854F, 0.0F, 2.0508F);
//        Belt_r4.setTextureOffset(83, 20).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
//
//        Belt_r5 = new ModelPart(this);
//        Belt_r5.setRotation(-0.5983F, 25.6438F, 0.0F);
//        Belt.addChild(Belt_r5);
//        setRotationAngle(Belt_r5, 0.0F, 0.0F, 0.3491F);
//        Belt_r5.setTextureOffset(27, 38).addBox(-4.4598F, -16.3746F, 1.91F, 5.0F, 2.0F, 1.0F, 0.0F, true);
//        Belt_r5.setTextureOffset(27, 38).addBox(-4.4598F, -16.3746F, -2.89F, 5.0F, 2.0F, 1.0F, 0.0F, true);
//
//        Belt_r6 = new ModelPart(this);
//        Belt_r6.setRotation(0.5983F, 25.6438F, 0.0F);
//        Belt.addChild(Belt_r6);
//        setRotationAngle(Belt_r6, 0.0F, 0.0F, -0.3491F);
//        Belt_r6.setTextureOffset(27, 38).addBox(-0.5402F, -16.3746F, 1.91F, 5.0F, 2.0F, 1.0F, 0.0F, false);
//        Belt_r6.setTextureOffset(27, 38).addBox(-0.5402F, -16.3746F, -2.89F, 5.0F, 2.0F, 1.0F, 0.0F, false);
//
//        Belt_r7 = new ModelPart(this);
//        Belt_r7.setRotation(0.0F, 10.1818F, -3.5918F);
//        Belt.addChild(Belt_r7);
//        setRotationAngle(Belt_r7, -0.7854F, 0.0F, 0.0F);
//        Belt_r7.setTextureOffset(83, 18).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
//
//        RightLeg = new ModelPart(this);
//        RightLeg.setRotation(-1.9F, 12.0F, 0.0F);
//        RightLeg.setTextureOffset(8, 52).addBox(-3.0F, -1.0F, -2.99F, 5.0F, 6.0F, 6.0F, 0.0F, false);
//
//        LeftLeg = new ModelPart(this);
//        LeftLeg.setRotation(1.9F, 12.0F, 0.0F);
//        LeftLeg.setTextureOffset(8, 64).addBox(-2.0F, -1.0F, -2.99F, 5.0F, 6.0F, 6.0F, 0.0F, true);
//
//
//
//        LeftBoot = new ModelPart(this);
//        LeftBoot.setRotation(0.0F, 24.0F, 0.0F);
//        LeftBoot.setTextureOffset(29, 116).addBox(-3.122F, 8.34F, -3.54F, 6.0F, 4.0F, 7.0F, 0.0F, true);
//        LeftBoot.setTextureOffset(33, 94).addBox(-2.5307F, 9.3F, -4.5718F, 5.0F, 3.0F, 2.0F, 0.0F, true);
//        LeftBoot.setTextureOffset(34, 99).addBox(-2.0307F, 10.425F, -5.0718F, 4.0F, 2.0F, 2.0F, 0.0F, true);
//        LeftBoot.setTextureOffset(29, 103).addBox(-2.4F, 5.5F, -2.99F, 5.0F, 7.0F, 6.0F, 0.0F, true);
//
//        RightBoot = new ModelPart(this);
//        RightBoot.setRotation(0.0F, 24.0F, 0.0F);
//        RightBoot.setTextureOffset(29, 116).addBox(-2.878F, 8.34F, -3.54F, 6.0F, 4.0F, 7.0F, 0.0F, false);
//        RightBoot.setTextureOffset(33, 94).addBox(-2.4693F, 9.3F, -4.5718F, 5.0F, 3.0F, 2.0F, 0.0F, false);
//        RightBoot.setTextureOffset(34, 99).addBox(-1.9693F, 10.425F, -5.0718F, 4.0F, 2.0F, 2.0F, 0.0F, false);
//        RightBoot.setTextureOffset(29, 103).addBox(-2.4F, 5.5F, -2.99F, 5.0F, 7.0F, 6.0F, 0.0F, false);
//    }
//
//    // THIS IS THE RENDERING OF THE ARMOR FOR SLOTS THIS IS NEEDED IN EACH JAVA FILE
//    @Override
//    public void render(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//
//        matrixStack.pushPose();
//        if (this.slot == EquipmentSlot.HEAD) {
//            this.Head.copyFrom(this.head);
//            this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        }
//        else if (this.slot == EquipmentSlot.CHEST) {
//            this.Body.copyFrom(this.body);
//            this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            this.RightArm.copyFrom(this.rightArm);
//            this.RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            //this.LeftArm.copyFrom(this.leftArm);
//            //this.LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//
//        } else if (this.slot == EquipmentSlot.LEGS) {
//            this.Belt.copyFrom(this.body);
//            this.Belt.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            this.RightLeg.copyFrom(this.rightLeg);
//            this.RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            this.LeftLeg.copyFrom(this.leftLeg);
//            this.LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//
//        } else if (this.slot == EquipmentSlot.FEET) {
//            this.RightBoot.copyFrom(this.rightLeg);
//            this.RightBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            this.LeftBoot.copyFrom(this.leftLeg);
//            this.LeftBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        }
//        matrixStack.popPose();
//    }
//
//    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//
//
//    // This is also needed
//    @SuppressWarnings("unchecked")
//    public static <A extends HumanoidModel<?>> A getModel(EquipmentSlot slot, LivingEntity entity) {
//        boolean illager = entity instanceof AbstractIllager ||
//                entity instanceof ZombieVillager ||
//                entity instanceof AbstractVillager;
//        int entityFlag = (slot.ordinal() & 15) | (illager ? 1 : 0) << 4 | (entity.isChild() ? 1 : 0) << 6;
//        return (A) CACHE.computeIfAbsent(entityFlag, k -> new OrcArmorModel(1, slot));
//    }
//
//}
