package com.defdaemon.tutorialmod;

import com.defdaemon.tutorialmod.common.world.biome.ModBiomes;
import com.defdaemon.tutorialmod.common.world.gen.ModBiomeGeneration;
import com.defdaemon.tutorialmod.core.init.*;
import com.defdaemon.tutorialmod.client.screen.LightningChannelerScreen;
import com.defdaemon.tutorialmod.common.world.structures.ModStructures;
import com.defdaemon.tutorialmod.common.recipe.ModRecipeTypes;
import com.defdaemon.tutorialmod.core.util.ModItemModelProperties;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TutorialMod.MOD_ID)
public class TutorialMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "tutorialmod";


    public TutorialMod()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModContainers.register(eventBus);
        ModStructures.register(eventBus);
        //ModFluids.register(eventBus);
        ModRecipeTypes.register(eventBus);
        ModSounds.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModBiomes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::enqueueIMC);
        eventBus.addListener(this::processIMC);
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                        .put(ModBlocks.REDWOOD_LOG.get(), ModBlocks.STRIPPED_REDWOOD_LOG.get())
                        .put(ModBlocks.REDWOOD_WOOD.get(), ModBlocks.STRIPPED_REDWOOD_WOOD.get()).build();
            MenuScreens.register(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(), LightningChannelerScreen::new);
        });
        ModStructures.setupStructures();
        ModBiomeGeneration.generateBiomes();

        SpawnPlacements.register(ModEntityTypes.BUFF_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.PIGEON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.AMETHYST_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.AMETHYST_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TITANIUM_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TITANIUM_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOMATO_PLANT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.OATS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HAPPY_CROP.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDWOOD_LEAVES.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDWOOD_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYACINTH.get(), RenderType.cutout());
            //ScreenManager.registerFactory(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(), LightningChannelerScreen::new);
//            ItemBlockRenderTypes.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.translucent());
//            ItemBlockRenderTypes.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.translucent());
//            ItemBlockRenderTypes.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.translucent());
            ModItemModelProperties.makeBow(ModItems.KAUPENBOW.get());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.KAUPEN_ALTAR.get(), RenderType.cutout());
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
