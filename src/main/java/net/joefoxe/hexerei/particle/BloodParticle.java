package net.joefoxe.hexerei.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.particle.*;
import com.mojang.blaze3d.vertex.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureManager;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class BloodParticle extends TextureSheetParticle {

    // thanks to understanding simibubi's code from the Create mod for rendering particles I was able to render my own :D
    public static final Vec3[] CUBE = {
            // bottom render
            //right
            new Vec3(0.5, -0.1, -0.5), // >^
            new Vec3(0.5, -0.1, 0.5),  // >V
            new Vec3(0.25, -0.1, 0.5), // <V
            new Vec3(0.25, -0.1, -0.5),// <^
            //left
            new Vec3(-0.25, -0.1, -0.5), // >^
            new Vec3(-0.25, -0.1, 0.5),  // >V
            new Vec3(-0.5, -0.1, 0.5), // <V
            new Vec3(-0.5, -0.1, -0.5),// <^
            //front
            new Vec3(0.25, -0.1, -0.5), // >^
            new Vec3(0.25, -0.1, -0.25),  // >V
            new Vec3(-0.25, -0.1, -0.25), // <V
            new Vec3(-0.25, -0.1, -0.5),// <^
            //back
            new Vec3(0.25, 0.1, 0.5), // >^
            new Vec3(0.25, 0.1, 0.25),  // >V
            new Vec3(-0.25, 0.1, 0.25), // <V
            new Vec3(-0.25, 0.1, 0.5),// <^


            // top render
            new Vec3(0.25, 0.1, -0.5), // >^
            new Vec3(0.25, 0.1, 0.5),  // >V
            new Vec3(0.5, 0.1, 0.5), // <V
            new Vec3(0.5, 0.1, -0.5),// <^
            //left
            new Vec3(-0.5, 0.1, -0.5), // >^
            new Vec3(-0.5, 0.1, 0.5),  // >V
            new Vec3(-0.25, 0.1, 0.5), // <V
            new Vec3(-0.25, 0.1, -0.5),// <^
            //front
            new Vec3(-0.25, 0.1, -0.5), // >^
            new Vec3(-0.25, 0.1, -0.25),  // >V
            new Vec3(0.25, 0.1, -0.25), // <V
            new Vec3(0.25, 0.1, -0.5),// <^
            //back
            new Vec3(-0.25, -0.1, 0.5), // >^
            new Vec3(-0.25, -0.1, 0.25),  // >V
            new Vec3(0.25, -0.1, 0.25), // <V
            new Vec3(0.25, -0.1, 0.5),// <^


            // front render
            new Vec3(-0.5, -0.1, -0.5),
            new Vec3(-0.5, 0.1, -0.5),
            new Vec3(0.5, 0.1, -0.5),
            new Vec3(0.5, -0.1, -0.5),

            // back render
            new Vec3(0.5, -0.1, 0.5),
            new Vec3(0.5, 0.1, 0.5),
            new Vec3(-0.5, 0.1, 0.5),
            new Vec3(-0.5, -0.1, 0.5),

            // left render
            new Vec3(0.5, -0.1, -0.5),
            new Vec3(0.5, 0.1, -0.5),
            new Vec3(0.5, 0.1, 0.5),
            new Vec3(0.5, -0.1, 0.5),

            // right render
            new Vec3(-0.5, -0.1, 0.5),
            new Vec3(-0.5, 0.1, 0.5),
            new Vec3(-0.5, 0.1, -0.5),
            new Vec3(-0.5, -0.1, -0.5),


            // inside
            // front render
            new Vec3(-0.25, -0.1, 0.25),
            new Vec3(-0.25, 0.1, 0.25),
            new Vec3(0.25, 0.1, 0.25),
            new Vec3(0.25, -0.1, 0.25),

            // back render
            new Vec3(0.25, -0.1, -0.25),
            new Vec3(0.25, 0.1, -0.25),
            new Vec3(-0.25, 0.1, -0.25),
            new Vec3(-0.25, -0.1, -0.25),

            // left render
            new Vec3(-0.25, -0.1, -0.25),
            new Vec3(-0.25, 0.1, -0.25),
            new Vec3(-0.25, 0.1, 0.25),
            new Vec3(-0.25, -0.1, 0.25),

            // right render
            new Vec3(0.25, -0.1, 0.25),
            new Vec3(0.25, 0.1, 0.25),
            new Vec3(0.25, 0.1, -0.25),
            new Vec3(0.25, -0.1, -0.25),

            //middle top inside
            new Vec3(0.25, -0.01, -0.25),
            new Vec3(0.25, -0.01, 0.25),
            new Vec3(-0.25, -0.01, 0.25),
            new Vec3(-0.25, -0.01, -0.25),
            // middle bottom render
            new Vec3(-0.25, 0.01, -0.25),
            new Vec3(-0.25, 0.01, 0.25),
            new Vec3(0.25, 0.01, 0.25),
            new Vec3(0.25, 0.01, -0.25),


    };

    public static final Vec3[] CUBE_NORMALS = {
            // modified normals for the sides
            new Vec3(0, 0.1, 0),
            new Vec3(0, 0.1, 0),
            new Vec3(0, 0.1, 0),
            new Vec3(0, 0.1, 0),
            new Vec3(0, -0.5, 0),
            new Vec3(0, -0.5, 0),
            new Vec3(0, -0.5, 0),
            new Vec3(0, -0.5, 0),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
    };

    private static final ParticleRenderType renderType = new ParticleRenderType() {
        @Override
        public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
            RenderSystem.disableTexture();


            // HELPER FOR RENDERING THE PARTICLE CAN CHANGE FOR RENDERING TYPES

            // transparent, additive blending
//            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
//            RenderSystem.enableLighting();
//            RenderSystem.enableColorMaterial();

            // opaque
			RenderSystem.depthMask(true);
//			RenderSystem.disableBlend();
//			RenderSystem.enableLighting();

            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.BLOCK);
        }

        @Override
        public void end(Tesselator tesselator) {

            tesselator.end();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
                    GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//            RenderSystem.disableLighting();
            RenderSystem.enableTexture();
        }

    };

    protected float scale;
    protected boolean hot;
    protected float rotationDirection;
    protected float rotation;
    protected float rotationOffsetYaw;
    protected float rotationOffsetPitch;
    protected float rotationOffsetRoll;
    protected float colorOffset;


    public BloodParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.rotation = 0;

        averageAge(80);

        Random random = new Random();

        this.colorOffset = (random.nextFloat() * 0.25f);
        this.rotationOffsetYaw = random.nextFloat();
        this.rotationOffsetPitch = random.nextFloat();
        this.rotationOffsetRoll = random.nextFloat();

        setScale(0.2F);
        setRotationDirection(random.nextFloat() - 0.5f);
    }

    public void setScale(float scale) {
        this.scale = scale;
        this.setSize(scale * 0.5f, scale * 0.5f);
    }

    public void averageAge(int age) {
        Random random = new Random();
        this.lifetime = (int) (age + (random.nextDouble() * 2D - 1D) * 8);
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public void setRotationDirection(float rotationDirection) {
        this.rotationDirection = rotationDirection;
    }


    @Override
    public void tick() {

        this.rotation = (this.rotationDirection * 0.1f) + this.rotation;

        super.tick();
    }

    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {


        Vec3 projectedView = renderInfo.getPosition();
        float lerpedX = (float) (Mth.lerp(partialTicks, this.xo, this.x) - projectedView.x());
        float lerpedY = (float) (Mth.lerp(partialTicks, this.yo, this.y) - projectedView.y());
        float lerpedZ = (float) (Mth.lerp(partialTicks, this.zo, this.z) - projectedView.z());

        int light = 15728880;// 15<<20 && 15<<4
        double ageMultiplier = 1 - Math.pow(age, 3) / Math.pow(this.lifetime, 3);

        for (int i = 0; i < CUBE.length / 4; i++) {
            // 10 faces to a blood particle
            for (int j = 0; j < 4; j++) {
                Vec3 vec = CUBE[i * 4 + j];
                vec = vec
                        .yRot(this.rotation + this.rotationOffsetYaw)
                        .xRot(this.rotation + this.rotationOffsetPitch)
                        .zRot(this.rotation + this.rotationOffsetRoll)
                        .scale(scale * ageMultiplier)
                        //.mul(1, 0.25 + 0.55 * (age/4f), 1) //scale non uniform based off age (maybe)
                        .add(lerpedX, lerpedY, lerpedZ);

                Vec3 normal = CUBE_NORMALS[i];
                buffer.vertex((float) vec.x, (float) vec.y, (float) vec.z, this.rCol + this.colorOffset, this.gCol, this.bCol, this.alpha, 0, 0, 0, light,(float) normal.x, (float) normal.y, (float) normal.z);


            }
        }
    }



    @Override
    public ParticleRenderType getRenderType() {
        return renderType;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet sprite) {
            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            BloodParticle cauldronParticle = new BloodParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            Random random = new Random();

            float colorOffset = (random.nextFloat() * 0.20f);
            cauldronParticle.setColor(0.05f + colorOffset, 0.0f, 0.0f);


            cauldronParticle.setAlpha(2.0f);


            cauldronParticle.pickSprite(this.spriteSet);
            return cauldronParticle;
        }
    }


}
