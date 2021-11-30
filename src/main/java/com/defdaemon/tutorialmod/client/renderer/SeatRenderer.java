package com.defdaemon.tutorialmod.client.renderer;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.SittableEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class SeatRenderer extends EntityRenderer<SittableEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "");

    public SeatRenderer(Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(SittableEntity entity) {
        return TEXTURE;
    }
}
