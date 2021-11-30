package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.block.entity.ToiletBlockEntity;
import com.defdaemon.tutorialmod.common.blockentity.LightningChannelerBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModBlockEntities {

    private ModBlockEntities() { }

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TutorialMod.MOD_ID);

    public static RegistryObject<BlockEntityType<LightningChannelerBE>> LIGHTNING_CHANNELER_BE = BLOCK_ENTITIES.register("lightning_channeler", () -> BlockEntityType.Builder.of(LightningChannelerBE::new, ModBlocks.LIGHTNING_CHANNELER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ToiletBlockEntity>> TOILET = BLOCK_ENTITIES.register("toilet", () -> BlockEntityType.Builder.of(ToiletBlockEntity::new, ModBlocks.TOILET.get()).build(null));

    //Helper functions
     public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
