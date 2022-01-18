package net.joefoxe.hexerei.particle;

import net.joefoxe.hexerei.Hexerei;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {
    public static ParticleType<CauldronParticleData> cauldronParticleType;

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Hexerei.MOD_ID);

    public static final RegistryObject<SimpleParticleType> CAULDRON = PARTICLES.register("cauldron_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLOOD = PARTICLES.register("blood_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLOOD_BIT = PARTICLES.register("blood_bit_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BROOM = PARTICLES.register("broom_particle_1", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BROOM_2 = PARTICLES.register("broom_particle_2", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BROOM_3 = PARTICLES.register("broom_particle_3", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BROOM_4 = PARTICLES.register("broom_particle_4", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BROOM_5 = PARTICLES.register("broom_particle_5", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BROOM_6 = PARTICLES.register("broom_particle_6", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FOG = PARTICLES.register("fog_particle", () -> new SimpleParticleType(true));

}