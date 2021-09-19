package com.defdaemon.tutorialmod.common.blocks;

import com.defdaemon.tutorialmod.common.items.Firestone;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

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
            } else
            {
                System.out.println("I right clicked a Firestone Block. Called from the Off Hand");
            }
        }

        return super.use(state, level, pos, player, hand, result);
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
