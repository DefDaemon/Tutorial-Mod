package com.defdaemon.tutorialmod.client.renderer;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.PigeonEntity;
import com.defdaemon.tutorialmod.client.renderer.model.PigeonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class PigeonRenderer<Type extends PigeonEntity> extends MobRenderer<Type, PigeonModel<Type>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/pigeon.png");

    public PigeonRenderer(Context context) {
        super(context, new PigeonModel<>(context.bakeLayer(PigeonModel.LAYER_LOCATION)), 0.2f);
    }
    @Override
    public ResourceLocation getTextureLocation(PigeonEntity entity) {
        return TEXTURE;
    }
}
