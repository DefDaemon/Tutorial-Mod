package com.defdaemon.tutorialmod.core.event;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.BuffZombieEntity;
import com.defdaemon.tutorialmod.common.entity.PigeonEntity;
import com.defdaemon.tutorialmod.core.init.ModEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD)
public class CommonModEvents
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieEntity.createAttributes().build());
        event.put(ModEntityTypes.PIGEON.get(), PigeonEntity.createAttributes().build());
    }
}
