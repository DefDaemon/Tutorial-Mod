package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.BuffZombieEntity;
import com.defdaemon.tutorialmod.common.entity.PigeonEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<BuffZombieEntity>> BUFF_ZOMBIE = ENTITY_TYPES.register("buff_zombie",
            () -> EntityType.Builder.of(BuffZombieEntity::new, MobCategory.MONSTER).sized(1f, 3f)
            .build(new ResourceLocation(TutorialMod.MOD_ID, "buff_zombie").toString()));

    public static final RegistryObject<EntityType<PigeonEntity>> PIGEON = ENTITY_TYPES.register("pigeon",
            () -> EntityType.Builder.of(PigeonEntity::new, MobCategory.CREATURE).sized(0.4f, 0.3f)
           .build(new ResourceLocation(TutorialMod.MOD_ID, "pigeon").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}