package com.defdaemon.tutorialmod.core.event;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.client.renderer.BuffZombieRenderer;
import com.defdaemon.tutorialmod.client.renderer.PigeonRenderer;
import com.defdaemon.tutorialmod.client.renderer.model.ModBoatRenderer;
import com.defdaemon.tutorialmod.common.entity.BuffZombieEntity;
import com.defdaemon.tutorialmod.common.entity.ModBoatEntity;
import com.defdaemon.tutorialmod.common.entity.PigeonEntity;
import com.defdaemon.tutorialmod.common.events.loot.FirestoneAdditionModifier;
import com.defdaemon.tutorialmod.common.events.loot.FirestoneStructureAdditionModifier;
import com.defdaemon.tutorialmod.common.item.ModSpawnEggItem;
import com.defdaemon.tutorialmod.core.init.ModEntityTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD)
public class CommonModEvents
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieEntity.createAttributes().build());
        event.put(ModEntityTypes.PIGEON.get(), PigeonEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        ModSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new FirestoneAdditionModifier.Serializer().setRegistryName(new ResourceLocation(TutorialMod.MOD_ID,"firestone_from_magma")),
                new FirestoneStructureAdditionModifier.Serializer().setRegistryName(new ResourceLocation(TutorialMod.MOD_ID,"firestone_in_igloo"))
        );
    }
}
