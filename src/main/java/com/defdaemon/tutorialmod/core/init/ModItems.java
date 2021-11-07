package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.material.ModArmorMaterial;
import com.defdaemon.tutorialmod.common.material.ModToolMaterial;
import com.defdaemon.tutorialmod.common.item.Firestone;
import com.defdaemon.tutorialmod.common.item.SmartBlowtorchItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    // From 1.17.1 tutorial
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())));

    public static final RegistryObject<Item> SMART_BLOW_TORCH = ITEMS.register("smart_blow_torch",
            () -> new SmartBlowtorchItem(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).durability(64)));


    // From 1.16.5 tutorial
    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone", () -> new Firestone(new Item.Properties().durability(8).tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () -> new SwordItem(ModToolMaterial.AMETHYST, 2, 3f, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () -> new PickaxeItem(ModToolMaterial.AMETHYST, 0, -1f, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", () -> new ShovelItem(ModToolMaterial.AMETHYST, 0, -1f, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", () -> new AxeItem(ModToolMaterial.AMETHYST, 4, -6f, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", () -> new HoeItem(ModToolMaterial.AMETHYST, 0, 0f, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> AMETHYST_HORSE_ARMOR = ITEMS.register("amethyst_horse_armor", () -> new HorseArmorItem(9, "amethyst", new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> OATS = ITEMS.register("oats", () -> new ItemNameBlockItem(ModBlocks.OATS.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().build()).tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> HAPPY_SEEDS = ITEMS.register("happy_seeds", () -> new ItemNameBlockItem(ModBlocks.HAPPY_CROP.get(), new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> HAPPY_CROP = ITEMS.register("happy_crop", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> HAPPY_STUFF = ITEMS.register("happy_stuff", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> HAPPY_COOKIE = ITEMS.register("happy_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().fast()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 200), 1.0f).build()).tab(ModCreativeModeTab.TUTORIAL_TAB)));

    //public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () -> new BucketItem(() -> ModFluids.OIL_FLUID.get(), new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<ForgeSpawnEggItem> BUFF_ZOMBIE_SPAWN_EGG = ITEMS.register("buff_zombie_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.BUFF_ZOMBIE, 0x464F56, 0x1D6336, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).stacksTo(16)));

    public static final RegistryObject<ForgeSpawnEggItem> PIGEON_SPAWN_EGG = ITEMS.register("pigeon_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.PIGEON, 0x879995, 0x576ABC, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).stacksTo(16)));


    //Helper function
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}

