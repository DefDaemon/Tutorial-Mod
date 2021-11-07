package com.defdaemon.tutorialmod.client.renderer.model;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.PigeonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PigeonModel<Type extends PigeonEntity> extends EntityModel<Type>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TutorialMod.MOD_ID, "pigeonentity"), "main");
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart legs;
    private final ModelPart head;
    private final ModelPart body;

    public PigeonModel(ModelPart root)
    {
        this.leftWing = root.getChild("leftWing");
        this.rightWing = root.getChild("rightWing");
        this.legs = root.getChild("legs");
        this.head = root.getChild("head");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 18.9509F, -4.0F, -0.0358F, -0.4807F, -0.0151F));

        PartDefinition cube_r2 = leftWing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 13).addBox(-0.8491F, -2.0873F, -1.8691F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(23, 11).addBox(-0.8491F, -0.0873F, 3.1309F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 4).addBox(-0.8491F, -1.0873F, 1.1309F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5076F, 0.0065F, 0.9662F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 19.0F, -2.0F, 0.0515F, -0.3498F, -0.0151F));

        PartDefinition cube_r1 = rightWing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(23, 16).addBox(2.6509F, 3.0873F, 1.8691F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 21).addBox(2.6509F, 2.0873F, -0.1309F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(2.6509F, 1.0873F, -3.1309F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0231F, -3.025F, 2.58F));

        PartDefinition legs = partdefinition.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(0, 8).addBox(1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, -2.0F, 0.0F, -0.4189F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(6, 16).addBox(-0.5F, -0.375F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(9, 21).addBox(-1.0F, -1.125F, -0.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6704F, 16.3052F, -2.4015F, 0.0087F, -0.3185F, -0.0131F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-4.0F, 5.0F, -11.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(15, 0).addBox(-4.0F, 4.0F, -7.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, 3.0F, -12.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(12, 8).addBox(-4.0F, 2.0F, -11.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 15.0F, 8.0F, 0.0F, -0.4189F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightWing.yRot = Mth.cos(limbSwing * 2.6662F + (float)Math.PI) * 2.4F * limbSwingAmount;
        this.leftWing.yRot = Mth.cos(limbSwing * 2.6662F) * 2.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        leftWing.render(poseStack, buffer, packedLight, packedOverlay);
        rightWing.render(poseStack, buffer, packedLight, packedOverlay);
        legs.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
    }
}