package com.defdaemon.tutorialmod.common.entity;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.core.init.ModEntityTypes;
import com.defdaemon.tutorialmod.core.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBoatEntity extends Boat
{
    private static final EntityDataAccessor<String> WOOD_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.STRING);

    public ModBoatEntity(EntityType<ModBoatEntity> modBoatEntityEntityType, Level levelIn) {
        super(modBoatEntityEntityType, levelIn);
        this.blocksBuilding = true;
    }

    public ModBoatEntity(Level levelIn, double x, double y, double z) {
        this(ModEntityTypes.REDWOOD_BOAT.get(), levelIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xOld = x;
        this.yOld = y;
        this.zOld = z;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WOOD_TYPE, "redwood");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag)
    {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putString("Type", this.getWoodType());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag)
    {
        super.readAdditionalSaveData(compoundTag);
        compoundTag.putString("Type", this.getWoodType());
    }

    public String getWoodType()
    {
        return this.entityData.get(WOOD_TYPE);
    }

    public void setWoodType(String wood)
    {
        this.entityData.set(WOOD_TYPE, wood);
    }

    @Override
    public Item getDropItem()
    {
        switch(this.getWoodType())
        {
            case "redwood":
                return ModItems.REDWOOD_BOAT.get();
            default:
                return ModItems.REDWOOD_BOAT.get();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ItemStack getPickedResult(HitResult target)
    {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TutorialMod.MOD_ID, this.getWoodType() + "_boat")));
    }
}
