package com.defdaemon.tutorialmod.data.recipes;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TutorialMod.MOD_ID);

    public static final RegistryObject<LightningChannelerRecipe.Serializer> LIGHTNING_SERIALIZER = RECIPE_SERIALIZER.register("lightning", LightningChannelerRecipe.Serializer::new);

    public static RecipeType<LightningChannelerRecipe> LIGHTNING_RECIPE = new LightningChannelerRecipe.LightningRecipeType();


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, LightningChannelerRecipe.TYPE_ID, LIGHTNING_RECIPE);
    }
}
