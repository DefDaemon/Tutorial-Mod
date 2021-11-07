package com.defdaemon.tutorialmod.common.jei;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.recipe.LightningChannelerRecipe;
import com.defdaemon.tutorialmod.common.recipe.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.Objects;
import java.util.stream.Collectors;

public class TutorialModJei implements IModPlugin
{

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TutorialMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new LightningChannelerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.LIGHTNING_RECIPE).stream().filter(r -> r instanceof LightningChannelerRecipe).collect(Collectors.toList()), LightningChannelerRecipeCategory.UID);
    }
}
