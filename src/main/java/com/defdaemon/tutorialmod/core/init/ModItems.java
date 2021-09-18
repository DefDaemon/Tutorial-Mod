package com.defdaemon.tutorialmod.core.init;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.items.Firestone;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone", () -> new Firestone(new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
