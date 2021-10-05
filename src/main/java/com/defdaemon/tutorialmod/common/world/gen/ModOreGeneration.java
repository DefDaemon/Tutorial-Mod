package com.defdaemon.tutorialmod.common.world.gen;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModOreGeneration
{
    public static void generateOres(final BiomeLoadingEvent event) {
        for (OreType ore : OreType.values()) {
            OreConfiguration oreFeatureConfig = new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,ore.getBlock().get().defaultBlockState(), ore.getMaxVeinSize());

            ConfiguredFeature<?, ?> oreFeature = registerOreFeature(ore, oreFeatureConfig);
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> { return oreFeature; });
        }
    }

    private static ConfiguredFeature<?, ?> registerOreFeature(OreType ore, OreConfiguration oreFeatureConfig) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ore.getBlock().get().getRegistryName(),
                Feature.ORE.configured(oreFeatureConfig).rangeUniform(VerticalAnchor.absolute(ore.getMinHeight()), VerticalAnchor.absolute(ore.getMaxHeight())).squared().count(ore.getMaxVeinSize()));
    }
}
