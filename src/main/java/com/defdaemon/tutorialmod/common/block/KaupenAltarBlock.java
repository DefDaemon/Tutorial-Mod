package com.defdaemon.tutorialmod.common.block;


import com.defdaemon.tutorialmod.common.world.dimension.KJTeleporter;
import com.defdaemon.tutorialmod.common.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class KaupenAltarBlock extends HorizontalDirectionalBlock
{
    public KaupenAltarBlock(Properties builder) {
        super(builder);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(5, 11, 5, 6, 13, 11),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 10, 10),
            Block.box(5, 10, 4, 11, 11, 12),
            Block.box(5, 11, 4, 11, 12, 5),
            Block.box(5, 11, 11, 11, 14, 12),
            Block.box(10, 11, 5, 11, 13, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(5, 11, 5, 11, 13, 6),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 10, 10),
            Block.box(4, 10, 5, 12, 11, 11),
            Block.box(11, 11, 5, 12, 12, 11),
            Block.box(4, 11, 5, 5, 14, 11),
            Block.box(5, 11, 10, 11, 13, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(10, 11, 5, 11, 13, 11),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 10, 10),
            Block.box(5, 10, 4, 11, 11, 12),
            Block.box(5, 11, 11, 11, 12, 12),
            Block.box(5, 11, 4, 11, 14, 5),
            Block.box(5, 11, 5, 6, 13, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(5, 11, 10, 11, 13, 11),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 10, 10),
            Block.box(4, 10, 5, 12, 11, 11),
            Block.box(4, 11, 5, 5, 12, 11),
            Block.box(11, 11, 5, 12, 14, 11),
            Block.box(5, 11, 5, 11, 13, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        Direction direction = state.getValue(FACING);
        switch((Direction)state.getValue(FACING))
        {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState stateIn, Level levelIn, BlockPos blockPos, Player playerIn, InteractionHand handIn, BlockHitResult hit)
    {
        if (!levelIn.isClientSide) {
            if (!playerIn.isCrouching()) {
                MinecraftServer server = levelIn.getServer();

                if (server != null) {
                    if (levelIn.dimension() == ModDimensions.KJDim) {
                        ServerLevel overWorld = server.getLevel(Level.OVERWORLD);
                        if (overWorld != null) {
                            playerIn.changeDimension(overWorld, new KJTeleporter(blockPos, false));
                        }
                    } else {
                        ServerLevel kjDim = server.getLevel(ModDimensions.KJDim);
                        if (kjDim != null) {
                            playerIn.changeDimension(kjDim, new KJTeleporter(blockPos, true));
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.use(stateIn, levelIn, blockPos, playerIn, handIn, hit);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING);
    }
}
