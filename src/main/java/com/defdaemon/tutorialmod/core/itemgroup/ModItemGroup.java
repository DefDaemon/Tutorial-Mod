package com.defdaemon.tutorialmod.core.itemgroup;

import com.defdaemon.tutorialmod.core.init.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class ModItemGroup {

    public static final CreativeModeTab TUTORIAL_GROUP = new CreativeModeTab("tutorialModTab")
    {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };

}