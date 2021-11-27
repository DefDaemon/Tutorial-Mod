package com.defdaemon.tutorialmod.common.block;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes
{
    public static final WoodType REDWOOD = WoodType.create(new ResourceLocation(TutorialMod.MOD_ID, "redwood").toString());
}
