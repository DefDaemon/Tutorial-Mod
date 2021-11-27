package com.defdaemon.tutorialmod.client.renderer.model;

import com.defdaemon.tutorialmod.TutorialMod;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer extends EntityRenderer<Boat>
{
    private static final ResourceLocation BOAT_TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/boat/redwood.png");

    public ModBoatRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Boat boat)
    {
        return BOAT_TEXTURE;
    }
}
