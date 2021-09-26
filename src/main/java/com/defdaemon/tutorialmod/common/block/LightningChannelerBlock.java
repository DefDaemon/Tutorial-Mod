package com.defdaemon.tutorialmod.common.block;

import com.defdaemon.tutorialmod.common.blockentity.LightningChannelerBE;
import com.defdaemon.tutorialmod.common.container.LightningChannelerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;

public class LightningChannelerBlock extends Block implements EntityBlock
{

    public LightningChannelerBlock(Properties properties)
    {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LightningChannelerBE(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!level.isClientSide())
        {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if(!player.isCrouching())
            {
                if(blockentity instanceof LightningChannelerBE)
                {
                    MenuProvider containerProvider = createContainerProvider(level, pos);
                    NetworkHooks.openGui((ServerPlayer) player, containerProvider, blockentity.getBlockPos());
                } else {
                    throw new IllegalStateException("Container provider is missing");
                }
            } else
            {
                if(blockentity instanceof LightningChannelerBE)
                {
                    if(level.isThundering())
                    {
                        EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, null, null, pos, MobSpawnType.TRIGGERED, true, true);
                        ((LightningChannelerBE)blockentity).lightningHasStruck();
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    private MenuProvider createContainerProvider(Level level, BlockPos pos) {
        return new MenuProvider() {
            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
                return new LightningChannelerContainer(i, level, pos, inv, player);
            }

            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("screen.tutorialmod.lightning_channeler");
            }
        };
    }
}
