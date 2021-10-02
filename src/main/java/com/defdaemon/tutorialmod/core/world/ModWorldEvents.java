package com.defdaemon.tutorialmod.core.world;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.core.world.gen.ModFlowerGeneration;
import com.defdaemon.tutorialmod.core.world.gen.ModOreGeneration;
import com.defdaemon.tutorialmod.core.world.gen.ModStructureGeneration;
import com.defdaemon.tutorialmod.core.world.gen.ModTreeGeneration;
import com.defdaemon.tutorialmod.core.world.structure.ModStructures;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event)
    {
        ModStructureGeneration.generateStructures(event);
        ModOreGeneration.generateOres(event);
        ModFlowerGeneration.generateFlowers(event);
        ModTreeGeneration.generateTrees(event);
    }

    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event)
    {
        if(event.getWorld() instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel) event.getWorld();

            try {
                Method GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>)GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if (cgRL != null && cgRL.getNamespace().equals("terraforged"))
                {
                    return;
                }
            } catch (Exception e)
            {
                TutorialMod.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }
            // Prevent spawning our structure in Vanilla's superflat world
            if (serverWorld.getChunkSource().generator instanceof FlatLevelSource && serverWorld.dimension().equals(Level.OVERWORLD))
            {
                return;
            }

            // Adding our Structure to the Map

            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(ModStructures.TRADING_HALL.get(), StructureSettings.DEFAULTS.get(ModStructures.TRADING_HALL.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
