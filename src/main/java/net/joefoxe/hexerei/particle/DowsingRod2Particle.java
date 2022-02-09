package net.joefoxe.hexerei.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.joefoxe.hexerei.Hexerei;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class DowsingRod2Particle extends TextureSheetParticle {

    private final ResourceLocation TEXTURE = new ResourceLocation(Hexerei.MOD_ID,
            "textures/particle/cauldron_boil_particle.png");

    // thanks to understanding simibubi's code from the Create mod for rendering particles I was able to render my own :D
    public static final Vec3[] CUBE = {
            // top render
            new Vec3(0.5, 0.5, -0.5),
            new Vec3(0.5, 0.5, 0.5),
            new Vec3(-0.5, 0.5, 0.5),
            new Vec3(-0.5, 0.5, -0.5),

            // bottom render
            new Vec3(-0.5, -0.5, -0.5),
            new Vec3(-0.5, -0.5, 0.5),
            new Vec3(0.5, -0.5, 0.5),
            new Vec3(0.5, -0.5, -0.5),

            // front render
            new Vec3(-0.5, -0.5, 0.5),
            new Vec3(-0.5, 0.5, 0.5),
            new Vec3(0.5, 0.5, 0.5),
            new Vec3(0.5, -0.5, 0.5),

            // back render
            new Vec3(0.5, -0.5, -0.5),
            new Vec3(0.5, 0.5, -0.5),
            new Vec3(-0.5, 0.5, -0.5),
            new Vec3(-0.5, -0.5, -0.5),

            // left render
            new Vec3(-0.5, -0.5, -0.5),
            new Vec3(-0.5, 0.5, -0.5),
            new Vec3(-0.5, 0.5, 0.5),
            new Vec3(-0.5, -0.5, 0.5),

            // right render
            new Vec3(0.5, -0.5, 0.5),
            new Vec3(0.5, 0.5, 0.5),
            new Vec3(0.5, 0.5, -0.5),
            new Vec3(0.5, -0.5, -0.5)
    };

    public static final Vec3[] CUBE_NORMALS = {
            // modified normals for the sides
            new Vec3(0, 0.5, 0),
            new Vec3(0, -0.5, 0),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
            new Vec3(0, 0, 0.5),
    };

    private static final ParticleRenderType renderType = new ParticleRenderType() {
        @Override
        public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
            RenderSystem.disableTexture();

            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }

        @Override
        public void end(Tesselator tesselator) {
            tesselator.end();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
                    GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        }
    };

    protected float scale;
    protected float rotationDirection;
    protected float rotation;

    public DowsingRod2Particle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.rotation = 0;
        Random random = new Random();
        setScale(0.2F);
        setRotationDirection(random.nextFloat() - 0.5f);
    }

    public void setScale(float scale) {
        this.scale = scale;
        this.setSize(scale * 0.5f, scale * 0.5f);
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
    public void render(VertexConsumer builder, Camera renderInfo, float p_225606_3_) {
        Vec3 projectedView = renderInfo.getPosition();
        float lerpX = (float) (Mth.lerp(p_225606_3_, this.xo, this.x) - projectedView.x());
        float lerpY = (float) (Mth.lerp(p_225606_3_, this.yo, this.y) - projectedView.y());
        float lerpZ = (float) (Mth.lerp(p_225606_3_, this.zo, this.z) - projectedView.z());

        int light = 15728880;
        double ageMultiplier = 1 - (Math.pow(Mth.clamp(age + p_225606_3_, 0, lifetime), 3) / Math.pow(lifetime, 3))/2f;

        RenderSystem._setShaderTexture(0, TEXTURE);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                Vec3 vec = CUBE[i * 4 + j];
                vec = vec
                        .yRot(this.rotation)
                        .scale(scale * ageMultiplier)
                        .add(lerpX, lerpY, lerpZ);

                Vec3 normal = CUBE_NORMALS[i];

                if(i == 0) {
                    builder.vertex(vec.x, vec.y, vec.z)
                            .uv(0, 0)
                            .color(Mth.clamp(rCol * 1.25f, 0, 1.0f), Mth.clamp(gCol * 1.25f, 0, 1.0f), Mth.clamp(bCol * 1.25f, 0, 1.0f), alpha)
                            .normal((float) normal.x, (float) normal.y, (float) normal.z)
                            .uv2(light)
                            .endVertex();
                }else if(i == 1) {
                    builder.vertex(vec.x, vec.y, vec.z)
                            .uv(0, 0)
                            .color(rCol * 0.55f, gCol * 0.55f, bCol * 0.55f, alpha)
                            .normal((float) normal.x, (float) normal.y, (float) normal.z)
                            .uv2(light)
                            .endVertex();
                }else if(i == 2) {
                    builder.vertex(vec.x, vec.y, vec.z)
                            .uv(0, 0)
                            .color(rCol * 0.95f, gCol * 0.95f, bCol * 0.95f, alpha)
                            .normal((float) normal.x, (float) normal.y, (float) normal.z)
                            .uv2(light)
                            .endVertex();
                }else if(i == 3) {
                    builder.vertex(vec.x, vec.y, vec.z)
                            .uv(0, 0)
                            .color(rCol * 0.75f, gCol * 0.75f, bCol * 0.75f, alpha)
                            .normal((float) normal.x, (float) normal.y, (float) normal.z)
                            .uv2(light)
                            .endVertex();
                }else if(i == 4) {
                    builder.vertex(vec.x, vec.y, vec.z)
                            .uv(0, 0)
                            .color(rCol * 0.9f, gCol * 0.9f, bCol * 0.9f, alpha)
                            .normal((float) normal.x, (float) normal.y, (float) normal.z)
                            .uv2(light)
                            .endVertex();
                }else {
                    builder.vertex(vec.x, vec.y, vec.z)
                            .uv(0, 0)
                            .color(rCol * 0.85f, gCol * 0.85f, bCol * 0.85f, alpha)
                            .normal((float) normal.x, (float) normal.y, (float) normal.z)
                            .uv2(light)
                            .endVertex();
                }
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
            DowsingRod2Particle cauldronParticle = new DowsingRod2Particle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            Random random = new Random();

            float colorOffset = (random.nextFloat() * 0.25f);

            cauldronParticle.setColor(colorOffset + 0.55f,colorOffset + 0.35f,colorOffset + 0.30f);

            cauldronParticle.setAlpha(1.0f);
            cauldronParticle.setScale(0.25f);
            cauldronParticle.setLifetime(1);


            cauldronParticle.pickSprite(this.spriteSet);
            return cauldronParticle;
        }
    }


}
