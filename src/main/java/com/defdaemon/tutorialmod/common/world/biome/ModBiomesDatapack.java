package com.defdaemon.tutorialmod.common.world.biome;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.Biomes;
import net.minecraft.data.worldgen.biome.VanillaBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomesDatapack
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, TutorialMod.MOD_ID);

    public static ResourceKey<Biome> AMETHYST_BIOME = registerBiome("amethyst_biome");

    public static ResourceKey<Biome> registerBiome(String biomeName)
    {
        BIOMES.register(biomeName, VanillaBiomes::theVoidBiome);
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TutorialMod.MOD_ID, biomeName));
    }

    public static void register(IEventBus eventBus)
    {
        BIOMES.register(eventBus);
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(AMETHYST_BIOME, 10));
    }
}
