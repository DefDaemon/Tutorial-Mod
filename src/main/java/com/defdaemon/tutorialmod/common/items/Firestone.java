package com.defdaemon.tutorialmod.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Random;

public class Firestone extends Item
{

    public static Random random = new Random();

    public Firestone(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        return super.interactLivingEntity(stack, player, entity, hand);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();
        if(!level.isClientSide)
        {
            Player player = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = level.getBlockState(context.getClickedPos());
            rightClockOnCertainBlockState(clickedBlock, context, player);
            //stack.hurtAndBreak(1, player, (player1) -> player.sendBreakAnimation(context.getHand()));
        }
        return super.onItemUseFirst(stack, context);
    }

    private void rightClockOnCertainBlockState(BlockState clickedBlock, UseOnContext context, Player player)
    {
        boolean playerIsNotOnFire = player.isOnFire();

        if(random.nextFloat() > 0.5f)
        {
            lightEntityOnFire(player, 6);
        } else if(!playerIsNotOnFire && BlockIsValidForResistance(clickedBlock))
        {
            gainFireResistanceAndDestroyBlock(player, context.getLevel(), context.getClickedPos());
        } else {
            lightGroundOnFire(context);
        }
    }

    private boolean BlockIsValidForResistance(BlockState clickedBlock)
    {
        return clickedBlock.getBlock() == Blocks.OBSIDIAN;
    }

    public static void lightEntityOnFire(Entity entity, int seconds)
    {
        entity.setSecondsOnFire(seconds);
    }

    private void gainFireResistanceAndDestroyBlock(Player player, Level level, BlockPos pos)
    {
        gainFireResistance(player);
        level.destroyBlock(pos, false);
    }

    public static void gainFireResistance(Player player)
    {
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200));
    }

    public static void lightGroundOnFire(UseOnContext context)
    {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos().relative(context.getClickedFace());
        BlockState blockstate = level.getBlockState(blockpos);
        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
            level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            BlockState blockstate1 = BaseFireBlock.getState(level, blockpos);
            level.setBlock(blockpos, blockstate1, 11);
        }
    }
}