package com.defdaemon.tutorialmod.common.entity;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.core.init.ModEntityTypes;
import com.defdaemon.tutorialmod.core.init.ModItems;
import com.defdaemon.tutorialmod.core.init.ModSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PigeonEntity extends Parrot
{
    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(TutorialMod.MOD_ID, "entities/pigeon");

    public PigeonEntity(EntityType<? extends Parrot> type, Level levelIn)
    {
        super(type, levelIn);
        this.moveControl = new PigeonFlyingMovementController(this, 10, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D).add(Attributes.FLYING_SPEED, (double)1.3F).add(Attributes.MOVEMENT_SPEED, (double)1.6D);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(1, new PanicGoal(this,1.25D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(ModItems.HAPPY_COOKIE.get()), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this,1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    protected int getExperienceReward(Player player)
    {
        return 1 + this.level.random.nextInt(4);
    }

    @Override
    public SoundEvent getAmbientSound()
    {
        return ModSounds.PIGEON_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.CHICKEN_HURT;
    }
    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return LOOT_TABLE;
    }

    @Override
    public Vec3 getLeashOffset()
    {
        return new Vec3(5.0D, (double)(5.5F * this.getEyeHeight()), (double)(this.getBbWidth() * 5.4F));
    }
}