package net.joefoxe.hexerei.particle;

import net.joefoxe.hexerei.Hexerei;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hexerei.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleUtil {

    /*
     * this is just a like any other RegistryEvent, however, we are binding the particle to the Particle Factory.
     * This also is similar to binding TileEntityRenderers to TileEntites.
     */

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.CAULDRON.get(), CauldronParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BLOOD.get(), BloodParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BLOOD_BIT.get(), BloodBitParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROOM.get(), BroomParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROOM_2.get(), BroomParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROOM_3.get(), BroomParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROOM_4.get(), BroomParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROOM_5.get(), BroomParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROOM_6.get(), BroomParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.FOG.get(), FogParticle.Factory::new);
    }



}