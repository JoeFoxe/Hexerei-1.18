//package net.joefoxe.hexerei.client.renderer.entity.render;
//
//import net.joefoxe.hexerei.Hexerei;
//import net.joefoxe.hexerei.client.renderer.entity.custom.PigeonEntity;
//import net.joefoxe.hexerei.client.renderer.entity.model.PigeonModel;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.resources.ResourceLocation;
//
//public class PigeonRenderer extends MobRenderer<PigeonEntity, PigeonModel<PigeonEntity>>
//{
//    protected static final ResourceLocation TEXTURE =
//            new ResourceLocation(Hexerei.MOD_ID, "textures/entity/pigeon.png");
//
//    public PigeonRenderer(EntityRendererProvider.Context p_174304_, PigeonModel<PigeonEntity> p_174305_, float p_174306_) {
//        super(p_174304_, new PigeonModel<>(), 0.2F);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(PigeonEntity p_114482_) {
//        return TEXTURE;
//    }
//}