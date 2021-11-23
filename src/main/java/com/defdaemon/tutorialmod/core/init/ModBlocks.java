package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.block.RedwoodTreeGrower;
import com.defdaemon.tutorialmod.common.block.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    // From 1.17.1 tutorial
    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(12f).requiresCorrectToolForDrops()), "tooltip.block.tutorialmod.block");

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> SPEEDY_BLOCK = registerBlock("speedy_block", () -> new SpeedyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> TITANIUM_STAIRS = registerBlock("titanium_stairs", () -> new StairBlock(() -> TITANIUM_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_FENCE = registerBlock("titanium_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_WALL = registerBlock("titanium_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_FENCE_GATE = registerBlock("titanium_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_SLAB = registerBlock("titanium_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_BUTTON = registerBlock("titanium_button", () -> new StoneButtonBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_PRESSURE_PLATE = registerBlock("titanium_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_DOOR = registerBlock("titanium_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> TITANIUM_TRAPDOOR = registerBlock("titanium_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> TOMATO_PLANT = BLOCKS.register("tomato_plant", () -> new TomatoPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));


    // From 1.16.5 tutorial
    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).strength(3.0f, 6.0f)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(8f).sound(SoundType.STONE)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_STAIRS = registerBlock("amethyst_stairs", () -> new StairBlock(() -> AMETHYST_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get())), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_FENCE = registerBlock("amethyst_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get())), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_FENCE_GATE = registerBlock("amethyst_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get())), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_SLAB = registerBlock("amethyst_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get())), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_BUTTON = registerBlock("amethyst_button", () -> new StoneButtonBlock(BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get())), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_WALL = registerBlock("amethyst_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get())), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_PRESSURE_PLATE = registerBlock("amethyst_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get())), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_DOOR = registerBlock("amethyst_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get()).noOcclusion()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> AMETHYST_TRAPDOOR = registerBlock("amethyst_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(AMETHYST_BLOCK.get()).noCollission()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> FIRESTONE_BLOCK = registerBlock("firestone_block", () -> new FirestoneBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(6f).sound(SoundType.METAL)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> REDWOOD_LOG = registerBlock("redwood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> REDWOOD_WOOD = registerBlock("redwood_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> STRIPPED_REDWOOD_LOG = registerBlock("stripped_redwood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> STRIPPED_REDWOOD_WOOD = registerBlock("stripped_redwood_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> REDWOOD_PLANKS = registerBlock("redwood_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> REDWOOD_LEAVES = registerBlock("redwood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2f).randomTicks().sound(SoundType.WOOD).noOcclusion()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> REDWOOD_SAPLING = registerBlock("redwood_sapling", () -> new SaplingBlock(new RedwoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> OATS = BLOCKS.register("oats_crop", () -> new OatsBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().instabreak().sound(SoundType.CROP)));

    public static final RegistryObject<Block> HAPPY_CROP = BLOCKS.register("happy_crop", () -> new HappyCropBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().instabreak().sound(SoundType.CROP)));

    public static final RegistryObject<Block> HYACINTH = registerBlock("hyacinth", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 2, BlockBehaviour.Properties.copy(Blocks.DANDELION)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlock("lightning_channeler", () -> new LightningChannelerBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(2.0f)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> KAUPEN_ALTAR = registerBlock("kaupen_altar", () -> new KaupenAltarBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion()), ModCreativeModeTab.TUTORIAL_TAB);

    // Register helper functions
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, String tooltipKey)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)) {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
                tooltip.add(new TranslatableComponent(tooltipKey));
                super.appendHoverText(stack, level, tooltip, flagIn);
            }
        });
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
