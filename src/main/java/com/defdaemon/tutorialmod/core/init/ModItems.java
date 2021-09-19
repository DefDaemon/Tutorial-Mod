package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.items.Firestone;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone", () -> new Firestone(new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () -> new SwordItem(ModItemTier.AMETHYST, 2, 3f, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () -> new PickaxeItem(ModItemTier.AMETHYST, 0, -1f, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", () -> new ShovelItem(ModItemTier.AMETHYST, 0, -1f, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", () -> new AxeItem(ModItemTier.AMETHYST, 4, -6f, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", () -> new HoeItem(ModItemTier.AMETHYST, 0, 0f, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_HORSE_ARMOR = ITEMS.register("amethyst_horse_armor", () -> new HorseArmorItem(9, "amethyst", new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
