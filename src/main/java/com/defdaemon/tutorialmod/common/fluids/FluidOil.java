package com.defdaemon.tutorialmod.common.fluids;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.core.init.ModFluids;
import com.defdaemon.tutorialmod.core.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.fluids.FluidAttributes;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public abstract class FluidOil extends FlowingFluid
{
    @Override
    public Fluid getFlowing()
    {
        return ModFluids.OIL_FLOWING.get();
    }

    @Override
    public Fluid getSource()
    {
        return ModFluids.OIL_FLUID.get();
    }

    @Override
    protected boolean canConvertToSource()
    {
        return false;
    }

    @Override
    protected int getSlopeFindDistance(LevelReader levelReader)
    {
        return 4;
    }

    public int getDropOff(LevelReader levelReader)
    {
        return 1;
    }

    @Override
    public Item getBucket()
    {
        return ModItems.OIL_BUCKET.get();
    }

    @Nullable
    public ParticleOptions getDripParticle()
    {
        return ParticleTypes.DRIPPING_WATER;
    }

    public void animateTick(Level level, BlockPos p_76446_, FluidState fluidState, Random random)
    {
        if (!fluidState.isSource() && !fluidState.getValue(FALLING)) {
            if (random.nextInt(64) == 0) {
                level.playLocalSound((double)p_76446_.getX() + 0.5D, (double)p_76446_.getY() + 0.5D, (double)p_76446_.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
            }
        } else if (random.nextInt(10) == 0) {
            level.addParticle(ParticleTypes.UNDERWATER, (double)p_76446_.getX() + random.nextDouble(), (double)p_76446_.getY() + random.nextDouble(), (double)p_76446_.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidState, BlockGetter blockGetter, BlockPos blockPos, Fluid fluid, Direction direction)
    {
        return direction == Direction.DOWN && !fluid.is(FluidTags.WATER);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState)
    {
        BlockEntity blockentity = blockState.hasBlockEntity() ? levelAccessor.getBlockEntity(blockPos) : null;
        Block.dropResources(blockState, levelAccessor, blockPos, blockentity);
    }

    public BlockState createLegacyBlock(FluidState fluidState)
    {
        return ModFluids.OIL_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(fluidState)));
    }

    public boolean isSame(Fluid fluid)
    {
        return fluid == ModFluids.OIL_FLUID.get() || fluid == ModFluids.OIL_FLOWING.get();
    }

    @Override
    protected FluidAttributes createAttributes()
    {
        return FluidAttributes.builder(new ResourceLocation(TutorialMod.MOD_ID,"block/oil_still"), new ResourceLocation(TutorialMod.MOD_ID,"block/oil_flow")).overlay(new ResourceLocation(TutorialMod.MOD_ID, "block/oil_overlay")).translationKey("fluid.tutorialmod.oil").build(this);
    }

    @Override
    public int getTickDelay(LevelReader levelReader)
    {
        return 5;
    }

    @Override
    protected float getExplosionResistance()
    {
        return 100.0f;
    }

    public Optional<SoundEvent> getPickupSound()
    {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    public static class Flowing extends FluidOil
    {
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> stateBuilder)
        {
            super.createFluidStateDefinition(stateBuilder);
            stateBuilder.add(LEVEL);
        }

        public int getAmount(FluidState fluidState)
        {
            return fluidState.getValue(LEVEL);
        }

        public boolean isSource(FluidState fluidState)
        {
            return false;
        }
    }

    public static class Source extends FluidOil
    {
        public int getAmount(FluidState fluidState)
        {
            return 8;
        }

        public boolean isSource(FluidState fluidState)
        {
            return true;
        }
    }
}
