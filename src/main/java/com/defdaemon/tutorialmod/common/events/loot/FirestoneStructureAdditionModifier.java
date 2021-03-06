package com.defdaemon.tutorialmod.common.events.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class FirestoneStructureAdditionModifier extends LootModifier
{
    private final Item addition;

    protected FirestoneStructureAdditionModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace
        // anything!
        if(context.getRandom().nextFloat() > 0.15) {
            generatedLoot.add(new ItemStack(addition, 1));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<FirestoneStructureAdditionModifier>
    {

        @Override
        public FirestoneStructureAdditionModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn)
        {
            Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JsonUtils.readNBT(object, "addition").toString()));
            return new FirestoneStructureAdditionModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(FirestoneStructureAdditionModifier instance)
        {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}
