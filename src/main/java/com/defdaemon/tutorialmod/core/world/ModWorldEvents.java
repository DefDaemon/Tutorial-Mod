package com.defdaemon.tutorialmod.core.world;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.core.world.gen.ModOreGeneration;
import com.defdaemon.tutorialmod.core.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event)
    {
        ModOreGeneration.generateOres(event);
        ModTreeGeneration.generateTrees(event);
    }
}
