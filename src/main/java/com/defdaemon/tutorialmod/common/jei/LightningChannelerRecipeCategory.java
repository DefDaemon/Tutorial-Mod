package com.defdaemon.tutorialmod.common.jei;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.recipe.LightningChannelerRecipe;
import com.defdaemon.tutorialmod.core.init.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class LightningChannelerRecipeCategory implements IRecipeCategory<LightningChannelerRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(TutorialMod.MOD_ID, "lightning");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/lightning_channeler_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic lightningBolt;

    public LightningChannelerRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.LIGHTNING_CHANNELER.get()));
        this.lightningBolt = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
    }

    @Override
    public ResourceLocation getUid()
    {
        return UID;
    }

    @Override
    public Class<? extends LightningChannelerRecipe> getRecipeClass() {
        return LightningChannelerRecipe.class;
    }

    @Override
    public Component getTitle()
    {
        return ModBlocks.LIGHTNING_CHANNELER.get().getName();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(LightningChannelerRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void draw(LightningChannelerRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
        if(recipe.getWeather() == LightningChannelerRecipe.Weather.THUNDERING) {
            this.lightningBolt.draw(stack, 82, 9);
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, LightningChannelerRecipe recipe, IIngredients ingredients)
    {
        recipeLayout.getItemStacks().init(0, true, 79, 30);
        recipeLayout.getItemStacks().init(1, true, 79, 52);

        recipeLayout.getItemStacks().init(2, false, 102, 42);
        recipeLayout.getItemStacks().set(ingredients);
    }
}
