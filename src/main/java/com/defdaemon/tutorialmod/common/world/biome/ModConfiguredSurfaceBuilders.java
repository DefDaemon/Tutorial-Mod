package com.defdaemon.tutorialmod.common.world.biome;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;

public class ModConfiguredSurfaceBuilders
{
    public static ConfiguredSurfaceBuilder<?> RIFT_SURFACE = register("rift_surface",
            SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderBaseConfiguration(
                    Blocks.REDSTONE_BLOCK.defaultBlockState(),
                    Blocks.MAGMA_BLOCK.defaultBlockState(),
                    Blocks.RED_CONCRETE.defaultBlockState())));

    private static <SC extends SurfaceBuilderConfiguration>ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb)
    {
        return (ConfiguredSurfaceBuilder) Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(TutorialMod.MOD_ID, name), csb);
    }
}
