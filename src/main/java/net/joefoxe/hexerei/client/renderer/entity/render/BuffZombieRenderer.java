//package net.joefoxe.hexerei.client.renderer.entity.render;
//
//import net.joefoxe.hexerei.Hexerei;
//import net.joefoxe.hexerei.client.renderer.entity.custom.BuffZombieEntity;
//import net.joefoxe.hexerei.client.renderer.entity.model.CauldronMimicModel;
//import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.resources.ResourceLocation;
//
//public class BuffZombieRenderer extends MobRenderer<BuffZombieEntity, CauldronMimicModel<BuffZombieEntity>>
//{
//    protected static final ResourceLocation TEXTURE =
//            new ResourceLocation(Hexerei.MOD_ID, "textures/entity/mixing_cauldron.png");
//
//    public BuffZombieRenderer(EntityRendererProvider.Context p_174304_, CauldronMimicModel<BuffZombieEntity> p_174305_, float p_174306_) {
//        super(p_174304_, new CauldronMimicModel<>(), 0.7F);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(BuffZombieEntity p_114482_) {
//        return TEXTURE;
//    }
//}