package com.defdaemon.tutorialmod.common.item;

import com.defdaemon.tutorialmod.core.init.ModItems;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers
{

    public static final ForgeTier TITANIUM = new ForgeTier(1, 1500, 1f, 4f, 10, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));
    public static final ForgeTier AMETHYST = new ForgeTier(3, 150, 4f, 12f, 10, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(ModItems.AMETHYST.get()));
}
