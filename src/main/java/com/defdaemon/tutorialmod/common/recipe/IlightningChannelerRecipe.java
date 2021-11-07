package com.defdaemon.tutorialmod.common.recipe;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.IShapedRecipe;

public interface IlightningChannelerRecipe extends IShapedRecipe<Inventory>
{
    ResourceLocation TYPE_ID = new ResourceLocation(TutorialMod.MOD_ID, "lightning");

    @Override
    default RecipeType<?> getType()
    {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }


    @Override
    default boolean isSpecial()
    {
        return true;
    }
}
