package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).strength(3.0f, 6.0f)), ModItemGroup.TUTORIAL_GROUP);

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(8f).sound(SoundType.STONE)), ModItemGroup.TUTORIAL_GROUP);




    // Register helper functions
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab itemGroup)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, itemGroup);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab itemGroup)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(itemGroup)));
    }


    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
