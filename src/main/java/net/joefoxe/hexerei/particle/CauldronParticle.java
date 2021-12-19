package net.joefoxe.hexerei.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.MixingCauldron;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.joefoxe.hexerei.tileentity.MixingCauldronTile;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class CauldronParticle extends TextureSheetParticle {

    private final ResourceLocation TEXTURE = new ResourceLocation(Hexerei.MOD_ID,
            "textures/particle/cauldron_boil_particle.png");

    // thanks to understanding simibubi's code from the Create mod for rendering particles I was able to render my own :D
    public static final Vec3[] CUBE = {
            // top render
            new Vec3(0.5, 0.1, -0.5),
            new Vec3(0.5, 0.1, 0.5),
            new Vec3(-0.5, 0.1, 0.5),
            new Vec3(-0.5, 0.1, -0.5),

            // bottom render
            new Vec3(-0.5, -0.1, -0.5),
            new Vec3(-0.5, -0.1, 0.5),
            new Vec3(0.5, -0.1, 0.5),
            new Vec3(0.5, -0.1, -0.5),

            // front render
            new Vec3(-0.5, -0.1, 0.5),
            new Vec3(-0.5, 0.1, 0.5),
            new Vec3(0.5, 0.1, 0.5),
            new Vec3(0.5, -0.1, 0.5),

            // back render
            new Vec3(0.5, -0.1, -0.5),
            new Vec3(0.5, 0.1, -0.5),
            new Vec3(-0.5, 0.1, -0.5),
            new Vec3(-0.5, -0.1, -0.5),

            // left render
            new Vec3(-0.5, -0.1, -0.5),
            new Vec3(-0.5, 0.1, -0.5),
            new Vec3(-0.5, 0.1, 0.5),
            new Vec3(-0.5, -0.1, 0.5),

            // right render
            new Vec3(0.5, -0.1, 0.5),
            new Vec3(0.5, 0.1, 0.5),
            new Vec3(0.5, 0.1, -0.5),
            new Vec3(0.5, -0.1, -0.5)
    };

    public static final Vec3[] CUBE_NORMALS = {
            // modified normals for the sides
            new Vec3(0, 0.1, 0),
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

    public CauldronParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
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
        double ageMultiplier = 1 - Math.pow(Mth.clamp(age + p_225606_3_, 0, lifetime), 3) / Math.pow(lifetime, 3);

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
            CauldronParticle cauldronParticle = new CauldronParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            Random random = new Random();

//            this.spriteSet = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidStack.getFluid().getAttributes().getStillTexture(fluidStack));
            MixingCauldronTile mixingCauldronTile = null;
            FluidStack fluidStack = FluidStack.EMPTY;

            if(worldIn.getBlockEntity(new BlockPos(x, y-0.1, z)) instanceof MixingCauldronTile) {
                mixingCauldronTile = (MixingCauldronTile) worldIn.getBlockEntity(new BlockPos(x, y - 0.1, z));
                fluidStack = mixingCauldronTile.getFluidStack();
            }

            Color color = new Color(BiomeColors.getAverageWaterColor(worldIn, new BlockPos(x, y, z)));

            BlockState blockStateAtPos = worldIn.getBlockState(new BlockPos(x, y-0.1, z));

            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidStack.getFluid().getAttributes().getStillTexture(fluidStack));

            int colorInt = fluidStack.getFluid().getAttributes().getColor(fluidStack);
            float alpha = (colorInt >> 24 & 255) / 275f;
            float red = (colorInt >> 16 & 255) / 275f;
            float green = (colorInt >> 8 & 255) / 275f;
            float blue = (colorInt & 255) / 275f;
            colorInt = sprite.getPixelRGBA(0, 8, 8);
            float alpha2 = (colorInt >> 24 & 255) / 275f;
            float blue2 = (colorInt >> 16 & 255) / 275f;
            float green2 = (colorInt >> 8 & 255) / 275f;
            float red2 = (colorInt & 255) / 275f;

            float colorOffset = (random.nextFloat() * 0.15f);
            if(red > 0.75f && blue > 0.75f && green > 0.75f)
                cauldronParticle.setColor(Mth.clamp(red2 + colorOffset,0, 1),Mth.clamp(green2 + colorOffset,0, 1),Mth.clamp(blue2 + colorOffset,0, 1));
            else
                cauldronParticle.setColor(Mth.clamp(red + colorOffset,0, 1),Mth.clamp(green + colorOffset,0, 1),Mth.clamp(blue + colorOffset,0, 1));

            if(fluidStack.isFluidEqual(new FluidStack(Fluids.WATER, 1)))
                cauldronParticle.setColor(color.getRed()/450f + colorOffset,color.getGreen()/450f + colorOffset,color.getBlue()/450f + colorOffset);
//            //set the particle color based off the fluid in the cauldron below
//            if(blockStateAtPos.getBlock() == ModBlocks.MIXING_CAULDRON.get()){
//                if(blockStateAtPos.getValue(MixingCauldron.FLUID) == LiquidType.WATER) {
//                    float colorOffset = (random.nextFloat() * 0.1f);
//                    cauldronParticle.setColor(color.getRed()/450f + colorOffset,color.getGreen()/450f + colorOffset,color.getBlue()/450f + colorOffset);}
//                if(blockStateAtPos.getValue(MixingCauldron.FLUID) == LiquidType.MILK) {
//                    float colorOffset = (random.nextFloat() * 0.05f);
//                    cauldronParticle.setColor(0.85f + colorOffset, 0.85f + colorOffset, 0.85f + colorOffset);}
//                if(blockStateAtPos.getValue(MixingCauldron.FLUID) == LiquidType.TALLOW) {
//                    float colorOffset = (random.nextFloat() * 0.05f);
//                    cauldronParticle.setColor(0.53f + colorOffset, 0.53f + colorOffset, 0.41f + colorOffset);}
//                if(blockStateAtPos.getValue(MixingCauldron.FLUID) == LiquidType.LAVA) {
//                    cauldronParticle.setColor(0.8f + (random.nextFloat() * 0.1f), 0.24f + (random.nextFloat() * 0.5f), (random.nextFloat() * 0.3f));}
//                if(blockStateAtPos.getValue(MixingCauldron.FLUID) == LiquidType.QUICKSILVER) {
//                    float colorOffset = (random.nextFloat() * 0.15f);
//                    cauldronParticle.setColor(0.12f + colorOffset, 0.12f + colorOffset, 0.12f + colorOffset);
//                }
//                if(blockStateAtPos.getValue(MixingCauldron.FLUID) == LiquidType.BLOOD) {
//                    float colorOffset = (random.nextFloat() * 0.25f);
//                    cauldronParticle.setColor(0.12f + colorOffset, 0.0f, 0.0f);
//                }
//            }


            cauldronParticle.setAlpha(1.0f);


            cauldronParticle.pickSprite(this.spriteSet);
            return cauldronParticle;
        }
    }


}
