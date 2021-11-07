package com.defdaemon.tutorialmod.common.blockentity;

import com.defdaemon.tutorialmod.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LightningChannelerBE extends BlockEntity
{
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public LightningChannelerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.LIGHTNING_CHANNELER_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return super.getCapability(cap);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(nbt);
    }

    @Override
    public CompoundTag save(CompoundTag compound)
    {
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    private ItemStackHandler createHandler()
    {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private void strikeLightning()
    {
        if(!this.level.isClientSide) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, null, null, this.getBlockPos(), MobSpawnType.TRIGGERED, true, true);
        }
        level.getMoonPhase();
    }


    public void craft()
    {
        //Inventory inv = new Inventory(this.itemHandler.getSlots());
        //for (int i = 0; i < itemHandler.getSlots(); i++) {
        //    inv.setItem(i, itemHandler.getStackInSlot(i));
        //}

        //Container container = new SimpleContainer(itemHandler.getStackInSlot(0));

        //Optional<LightningChannelerRecipe> recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.LIGHTNING_RECIPE, inv, level);
        //recipe.ifPresent(iRecipe -> {
        //    ItemStack output = iRecipe.getResultItem();

        //    if(iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.CLEAR) && !level.isRaining()) {
        //        craftTheItem(output);
        //    }

        //    if(iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.RAIN) && level.isRaining()) {
        //        craftTheItem(output);
        //    }

        //    if(iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.THUNDERING) && level.isThundering()) {
        //        strikeLightning();
        //        craftTheItem(output);
        //    }
        //    setChanged();
        //});
    }

    private void craftTheItem(ItemStack output)
    {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.insertItem(1, output, false);
    }

    public void tick()
    {
        if(level.isClientSide)
            return;
        craft();
    }
}
