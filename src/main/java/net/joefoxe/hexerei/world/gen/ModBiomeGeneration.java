package net.joefoxe.hexerei.world.gen;

import net.joefoxe.hexerei.world.biome.ModBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModBiomeGeneration {

    public static void generateBiomes() {
        addBiome(ModBiomes.WILLOW_SWAMP.get(), BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.LUSH);
    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
        ResourceKey<Biome> key = ResourceKey.create(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

        BiomeDictionary.addTypes(key, types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
    }
}