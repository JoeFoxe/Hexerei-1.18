package net.joefoxe.hexerei.item;

import net.joefoxe.hexerei.item.custom.DowsingRodItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ModItemProperties {

    public static double angleDifference( double angle1, double angle2 )
    {
        double diff = ( angle2 - angle1 + 180 ) % 360 - 180;
        return diff < -180 ? diff + 360 : diff;
    }

    public static void makeDowsingRod(Item item) {

        ItemProperties.register(ModItems.DOWSING_ROD.get(), new ResourceLocation("angle"), new ClampedItemPropertyFunction() {
            private double rotation;
            private double rota;
            private long lastUpdateTick;

            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity p_174667_, int p_174668_) {
                Entity entity = (Entity)(p_174667_ != null ? p_174667_ : itemStack.getEntityRepresentation());

                if (entity == null || ((DowsingRodItem)itemStack.getItem()).nearestPos == null) {
                    return 0.3F;
                }
                if (level == null && entity.level instanceof ClientLevel) {
                    level = (ClientLevel)entity.level;
                }

                float viewRot = Mth.wrapDegrees(p_174667_.getViewYRot(1.0f));
                float rotationFromPlayer = (float) (Math.atan2(((DowsingRodItem)itemStack.getItem()).nearestPos.getZ() - p_174667_.getZ() + 0.5f, ((DowsingRodItem)itemStack.getItem()).nearestPos.getX() - p_174667_.getX() + 0.5f) * 180 / Math.PI);
                if (Math.abs(angleDifference(Mth.wrapDegrees(viewRot + 90), rotationFromPlayer)) < 15) {
                    return 0.0f;
                }else if (Math.abs(angleDifference(Mth.wrapDegrees(viewRot + 90), rotationFromPlayer)) < 45) {
                    return 0.1f;
                }else if (Math.abs(angleDifference(Mth.wrapDegrees(viewRot + 90), rotationFromPlayer)) < 75) {
                    return 0.2f;
                }
                return 0.3f;


//                double d0;
//                if (level.dimensionType().natural()) {
//                    d0 = (double)level.getTimeOfDay(1.0F);
//                } else {
//                    d0 = Math.random();
//                }
//
//                d0 = this.wobble(level, d0);
//                return (float)d0;

            }

            private double wobble(Level p_117904_, double p_117905_) {
                if (p_117904_.getGameTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = p_117904_.getGameTime();
                    double d0 = p_117905_ - this.rotation;
                    d0 = Mth.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
                    this.rota += d0 * 0.1D;
                    this.rota *= 0.9D;
                    this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0D);
                }

                return this.rotation;
            }
        });
    }
}