package net.joefoxe.hexerei.particle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class BroomParticle extends TextureSheetParticle {
    protected float scale;
    protected float rotationDir;
    protected float fallingSpeed;

    public BroomParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.roll = new Random().nextFloat() * (float)Math.PI;
        this.oRoll = this.roll;
        this.rotationDir = new Random().nextFloat() - 0.5f;
        this.fallingSpeed = new Random().nextFloat();

        setScale(0.2F);
    }

    public void setScale(float scale) {
        this.scale = scale;
        this.setSize(scale * 0.5f, scale * 0.5f);
    }

    @Override
    public void tick() {

        this.oRoll = this.roll;
        if(Math.abs(this.yd) > 0 && this.y != this.yo)
            this.roll += 0.3f * rotationDir;
        this.yd -= 0.005f * fallingSpeed;

        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
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
            Random rand = new Random();
            float colorOffset = (rand.nextFloat() * 0.4f);
            BroomParticle broomParticle = new BroomParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            broomParticle.pickSprite(this.spriteSet);
            broomParticle.setColor(0.6f + colorOffset,0.6f + colorOffset,0.6f + colorOffset);
            if(this.spriteSet.get(0,1).getName().getPath().toString().matches("particle/broom_particle_4") ||
                  this.spriteSet.get(0,1).getName().getPath().toString().matches("particle/broom_particle_5") ||
                      this.spriteSet.get(0,1).getName().getPath().toString().matches("particle/broom_particle_6")) {
                broomParticle.lifetime += broomParticle.lifetime * 3 + 30;
            }

            return broomParticle;
        }
    }
}
