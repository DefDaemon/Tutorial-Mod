package com.defdaemon.tutorialmod.client.event;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.client.renderer.BuffZombieRenderer;
import com.defdaemon.tutorialmod.client.renderer.PigeonRenderer;
import com.defdaemon.tutorialmod.client.renderer.model.BuffZombieModel;
import com.defdaemon.tutorialmod.client.renderer.model.ModBoatRenderer;
import com.defdaemon.tutorialmod.client.renderer.model.PigeonModel;
import com.defdaemon.tutorialmod.core.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents
{
    private ClientModEvents() { }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(BuffZombieModel.LAYER_LOCATION, BuffZombieModel::createBodyLayer);
        event.registerLayerDefinition(PigeonModel.LAYER_LOCATION, PigeonModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.PIGEON.get(), PigeonRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.REDWOOD_BOAT.get(), ModBoatRenderer::new);
    }
}
