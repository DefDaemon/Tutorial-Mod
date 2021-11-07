package com.defdaemon.tutorialmod.common.block;

import com.defdaemon.tutorialmod.common.world.gen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodTreeGrower extends AbstractTreeGrower
{

  @Nullable
  @Override
  protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean bool) {
    return ModConfiguredFeatures.REDWOOD;
  }
}
