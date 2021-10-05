package com.defdaemon.tutorialmod.common.events;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.command.ReturnHomeCommand;
import com.defdaemon.tutorialmod.common.command.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModEvents
{
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event)
    {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event)
    {
        if(!event.getOriginal().level.isClientSide())
        {
            event.getPlayer().getPersistentData().putIntArray(TutorialMod.MOD_ID + "homepos", event.getOriginal().getPersistentData().getIntArray(TutorialMod.MOD_ID + "homepos"));
        }
    }
}
