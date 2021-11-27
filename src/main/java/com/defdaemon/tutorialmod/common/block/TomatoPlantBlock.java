package com.defdaemon.tutorialmod.common.block;

import com.defdaemon.tutorialmod.core.init.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public class TomatoPlantBlock extends CropBlock
{
    public TomatoPlantBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.TOMATO_SEEDS.get();
    }
}
