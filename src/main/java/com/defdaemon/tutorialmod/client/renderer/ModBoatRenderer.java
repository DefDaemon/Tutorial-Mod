package com.defdaemon.tutorialmod.client.renderer;

import com.defdaemon.tutorialmod.TutorialMod;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer extends BoatRenderer
{
    private final Map<Boat.Type, Pair<ResourceLocation, BoatModel>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(Boat.Type.values()).collect(ImmutableMap.toImmutableMap((p_173938_) -> {
            return p_173938_;
        }, (p_173941_) -> {
            return Pair.of(new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/boat/redwood.png"), new BoatModel(context.bakeLayer(ModelLayers.createBoatModelName(Boat.Type.byName("redwood")))));
        }));
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat)
    {
        return this.boatResources.get(boat.getBoatType());
    }

}
