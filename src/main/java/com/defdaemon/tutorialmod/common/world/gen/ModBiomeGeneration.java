package com.defdaemon.tutorialmod.common.world.gen;

import com.defdaemon.tutorialmod.common.world.biome.ModBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ModBiomeGeneration
{

    public static void generateBiomes()
    {
        addBiome(ModBiomes.RIFT_BIOME.get(), BiomeManager.BiomeType.WARM, 20, HOT, DEAD, DRY);
    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types)
    {
        ResourceKey<Biome> key = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));
        BiomeDictionary.addTypes(key, types);
        Boolean result = BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
        System.out.print("DEF DAEMON: result adding biome");
        System.out.println(result);
    }
}
