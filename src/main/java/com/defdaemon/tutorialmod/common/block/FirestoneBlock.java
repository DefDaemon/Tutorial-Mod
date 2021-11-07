package com.defdaemon.tutorialmod.common.block;

import com.defdaemon.tutorialmod.common.item.Firestone;
import com.defdaemon.tutorialmod.core.util.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class FirestoneBlock extends Block
{
    public FirestoneBlock(Properties properties)
    {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        if(!level.isClientSide)
        {
            if(hand == InteractionHand.MAIN_HAND)
            {
                System.out.println("I right clicked a Firestone Block. Called from the Main Hand");
                level.playSound(null, pos, ModSoundEvents.SMALL_EXPLOSION.get(), SoundSource.BLOCKS, 1, 1);
            } else
            {
                System.out.println("I right clicked a Firestone Block. Called from the Off Hand");
            }
        }

        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public void animateTick(BlockState stateIn, Level levelIn, BlockPos pos, Random rand)
    {
        float chance = 0.35f;
        if(chance < rand.nextFloat())
        {
            levelIn.addParticle(ParticleTypes.FLAME, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(), 0d,0.05d,0d);

            levelIn.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, stateIn), pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(), 0.0D, 0.05D, 0.0D);
        }
        super.animateTick(stateIn, levelIn, pos, rand);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player)
    {
        if(!level.isClientSide)
        {
            System.out.println("I left clicked a Firestone Block.");
        }
        super.attack(state, level, pos, player);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        Firestone.lightEntityOnFire(entity, 5);
        super.stepOn(level, pos, state, entity);
    }
}
