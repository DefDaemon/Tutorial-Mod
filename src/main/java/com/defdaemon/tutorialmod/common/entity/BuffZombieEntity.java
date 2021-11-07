package com.defdaemon.tutorialmod.common.entity;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.core.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BuffZombieEntity extends Zombie
{
    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(TutorialMod.MOD_ID, "entities/buff_zombie");

    public BuffZombieEntity(EntityType<? extends Zombie> type, Level levelIn)
    {
        super(type, levelIn);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.33F)
                .add(Attributes.ATTACK_DAMAGE, 13.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, Player.class, true ) );
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Override
    protected int getExperienceReward(Player player)
    {
        return 3 + this.level.random.nextInt(5);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
       return ModSounds.BUFF_ZOMBIE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return ModSounds.BUFF_ZOMBIE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.BUFF_ZOMBIE_DEATH.get();
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.HOGLIN_STEP;
    }

    protected void playStepSound(BlockPos blockPos, BlockState blockState)
    {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn)
    {
        if(!super.doHurtTarget(entityIn))
        {
            return false;
        }
        else
        {
            if(entityIn instanceof LivingEntity)
            {
                ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 3));
                ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200));
                ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
            }
            return true;
        }
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return LOOT_TABLE;
    }
}
