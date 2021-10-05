package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.fluids.FluidOil;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids
{
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TutorialMod.MOD_ID);
    public static final RegistryObject<FlowingFluid> OIL_FLUID = FLUIDS.register("oil_fluid", () -> new FluidOil.Source());
    public static final RegistryObject<FlowingFluid> OIL_FLOWING = FLUIDS.register("oil_flowing", () -> new FluidOil.Flowing());

    public static final RegistryObject<LiquidBlock> OIL_BLOCK = ModBlocks.BLOCKS.register("oil", () -> new LiquidBlock(() -> ModFluids.OIL_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0f).noDrops()));


    //Helper class
    public static void register(IEventBus eventBus)
    {
        FLUIDS.register(eventBus);
    }
}
