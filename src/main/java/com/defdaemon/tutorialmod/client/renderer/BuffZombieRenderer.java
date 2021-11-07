package com.defdaemon.tutorialmod.client.renderer;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.BuffZombieEntity;
import com.defdaemon.tutorialmod.client.renderer.model.BuffZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BuffZombieRenderer<Type extends BuffZombieEntity> extends MobRenderer<Type, BuffZombieModel<Type>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/buff_zombie.png");

    public BuffZombieRenderer(Context context) {
        super(context, new BuffZombieModel<>(context.bakeLayer(BuffZombieModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(BuffZombieEntity entity)
    {
        return TEXTURE;
    }
}
