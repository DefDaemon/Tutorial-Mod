package com.defdaemon.tutorialmod.common.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;

public class PigeonFlyingMovementController extends FlyingMoveControl
{
    private int maxTurn = 0;
    private boolean hoversInPlace = false;

    public PigeonFlyingMovementController(Mob mob, int maxTurn, boolean hoversInPlace)
    {
        super(mob, maxTurn, hoversInPlace);
        this.maxTurn = maxTurn;
        this.hoversInPlace = hoversInPlace;
    }

    public void tick()
    {
        if (this.operation == Operation.MOVE_TO)
        {
            this.operation = Operation.WAIT;
            this.mob.setNoGravity(true);
            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getY();
            double d2 = this.wantedZ - this.mob.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 < (double)2.5000003E-7F)
            {
                this.mob.setYya(2.0F);
                this.mob.setZza(2.0F);
                return;
            }

            float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 90.0F));
            float f1;
            if (this.mob.isOnGround())
            {
                f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            }
            else
            {
                f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
            }

            this.mob.setSpeed(f1);
            double d4 = Math.sqrt(d0 * d0 + d2 * d2);
            float f2 = (float)(-(Mth.atan2(d1, d4) * (double)(180F / (float)Math.PI)));
            this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, (float)this.maxTurn));
            this.mob.setYya(d1 > 0.0D ? f1 : -f1);
        }
        else
        {
            if (!this.hoversInPlace)
            {
                this.mob.setNoGravity(false);
            }

            this.mob.setYya(2.0F);
            this.mob.setZza(2.0F);
        }
    }
}
