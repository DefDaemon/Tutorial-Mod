package com.defdaemon.tutorialmod.data.recipes;

import com.defdaemon.tutorialmod.core.init.ModBlocks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Objects;

public class LightningChannelerRecipe implements IlightningChannelerRecipe
{
    public enum Weather {
        CLEAR,
        RAIN,
        THUNDERING;

        public static Weather getWeatherByString(String s) {
            return Objects.equals(s, "thundering") ? THUNDERING : Objects.equals(s, "rain") ? RAIN : CLEAR;
        }
    }

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final Weather weather;

    public LightningChannelerRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, Weather weather) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.weather = weather;
    }

    @Override
    public boolean matches(Inventory inv, Level levelIn)
    {
        if(recipeItems.get(0).test(inv.getItem(0))) {
            return recipeItems.get(1).test(inv.getItem(1));
        }
        return false;
    }

    @Override
    public ItemStack assemble(Inventory inv)
    {
        return output;
    }

    @Override
    public ItemStack getResultItem()
    {
        return output.copy();
    }

    public Weather getWeather()
    {
        return this.weather;
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.LIGHTNING_CHANNELER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.LIGHTNING_SERIALIZER.get();
    }


    @Override
    public int getRecipeWidth() {
        return 1;
    }

    @Override
    public int getRecipeHeight() {
        return 2;
    }

    public static class LightningRecipeType implements RecipeType<LightningChannelerRecipe>
    {
        @Override
        public String toString() {
            return LightningChannelerRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<LightningChannelerRecipe>
    {

        @Override
        public LightningChannelerRecipe fromJson(ResourceLocation recipeId, JsonObject json)
        {
            ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));
            String weather = json.get("weather").toString();

            JsonArray ingredients = json.getAsJsonArray("ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new LightningChannelerRecipe(recipeId, output, inputs, Weather.getWeatherByString(weather));
        }

        @Nullable
        @Override
        public LightningChannelerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }
            ItemStack output = buffer.readItem();
            return new LightningChannelerRecipe(recipeId, output, inputs, null);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, LightningChannelerRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for(Ingredient ing: recipe.getIngredients())
            {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
