package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.BuffZombieEntity;
import com.defdaemon.tutorialmod.common.entity.ModBoatEntity;
import com.defdaemon.tutorialmod.common.entity.PigeonEntity;
import com.defdaemon.tutorialmod.common.entity.SittableEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModEntityTypes
{
    private ModEntityTypes() { }

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<BuffZombieEntity>> BUFF_ZOMBIE = ENTITY_TYPES.register("buff_zombie", () -> EntityType.Builder.of(BuffZombieEntity::new, MobCategory.MONSTER).sized(1f, 3f).build(new ResourceLocation(TutorialMod.MOD_ID, "buff_zombie").toString()));

    public static final RegistryObject<EntityType<PigeonEntity>> PIGEON = ENTITY_TYPES.register("pigeon", () -> EntityType.Builder.of(PigeonEntity::new, MobCategory.CREATURE).sized(0.4f, 0.3f).build(new ResourceLocation(TutorialMod.MOD_ID, "pigeon").toString()));

    public static final RegistryObject<EntityType<ModBoatEntity>> REDWOOD_BOAT = ENTITY_TYPES.register("redwood_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(TutorialMod.MOD_ID, "redwood_boat").toString()));

    public static final RegistryObject<EntityType<SittableEntity>> SEAT = ENTITY_TYPES.register("seat", () -> EntityType.Builder.<SittableEntity>of(SittableEntity::new, MobCategory.MISC).sized(1f, 1f).build(new ResourceLocation(TutorialMod.MOD_ID, "seat").toString()));

    // Helper functions
    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}