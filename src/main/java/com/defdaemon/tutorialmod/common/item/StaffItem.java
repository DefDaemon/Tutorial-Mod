package com.defdaemon.tutorialmod.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class StaffItem extends Item
{
    public StaffItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60));
        target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 2));
        return super.hurtEnemy(stack, target, attacker);
    }
}
