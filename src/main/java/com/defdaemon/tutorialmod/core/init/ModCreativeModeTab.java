package com.defdaemon.tutorialmod.core.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public final class ModCreativeModeTab
{
    private ModCreativeModeTab() { }

    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab("tutorialModTab")
    {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TITANIUM_INGOT.get());
        }
    };
}
