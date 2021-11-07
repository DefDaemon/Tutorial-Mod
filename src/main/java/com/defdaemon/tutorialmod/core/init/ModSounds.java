package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MOD_ID);

    public static final RegistryObject<SoundEvent> SMALL_EXPLOSION = registerSoundEvent("small_explosion");

    public static final RegistryObject<SoundEvent> BAR_BRAWL = registerSoundEvent("bar_brawl");

    public static final RegistryObject<SoundEvent> BUFF_ZOMBIE_AMBIENT = registerSoundEvent("entity.buff_zombie.ambient");

    public static final RegistryObject<SoundEvent> BUFF_ZOMBIE_HURT = registerSoundEvent("entity.buff_zombie.hurt");

    public static final RegistryObject<SoundEvent> BUFF_ZOMBIE_DEATH = registerSoundEvent("entity.buff_zombie.death");

    public static final RegistryObject<SoundEvent> PIGEON_AMBIENT = registerSoundEvent("entity.pigeon.ambient");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(TutorialMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}
