package com.defdaemon.tutorialmod.client.renderer.model;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.entity.BuffZombieEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class BuffZombieModel <Type extends BuffZombieEntity> extends EntityModel<Type>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TutorialMod.MOD_ID, "buffzombieentity"), "main");
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;


    public BuffZombieModel(ModelPart root)
    {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("rightArm");
        this.leftArm = root.getChild("leftArm");
        this.rightLeg = root.getChild("rightLeg");
        this.leftLeg = root.getChild("leftLeg");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(55, 0).addBox(-3.5F, -1.5F, -3.5F, 7.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 12.5F, 0.5F));

        PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 54).addBox(-3.5F, -1.5F, -3.5F, 7.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 12.5F, 0.5F, -0.2269F, 0.0F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone = leftArm.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftArm_r1_r1 = bone.addOrReplaceChild("leftArm_r1_r1", CubeListBuilder.create().texOffs(49, 21).addBox(-16.0F, -7.0F, -29.0F, 7.0F, 18.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.3963F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(41, 78).addBox(-11.0F, -27.0F, -19.0F, 1.0F, -1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(44, 82).addBox(-13.0F, -27.0F, -19.0F, 1.0F, -1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(44, 82).addBox(-15.0F, -27.0F, -19.0F, 1.0F, -1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r1_r2 = bone.addOrReplaceChild("cube_r1_r2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r2 = cube_r1_r2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 32).addBox(-11.0F, -31.5F, -14.0F, 1.0F, -1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(1, 28).addBox(-13.0F, -31.5F, -14.0F, 1.0F, -1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(3, 28).addBox(-15.0F, -31.5F, -14.0F, 1.0F, -1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone2 = rightArm.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightArm_r1_r1 = bone2.addOrReplaceChild("rightArm_r1_r1", CubeListBuilder.create().texOffs(42, 47).addBox(9.0F, -7.0F, -29.0F, 7.0F, 18.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.3963F, 0.0F, 0.0F));

        PartDefinition cube_r1_r1 = bone2.addOrReplaceChild("cube_r1_r1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = cube_r1_r1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(35, 28).addBox(14.0F, -31.5F, -14.0F, 1.0F, -1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(35, 28).addBox(10.0F, -31.5F, -14.0F, 1.0F, -1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(35, 28).addBox(12.0F, -31.5F, -14.0F, 1.0F, -1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone3 = rightArm.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r4 = bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(42, 81).addBox(14.0F, -27.0F, -19.0F, 1.0F, -1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(42, 75).addBox(12.0F, -27.0F, -19.0F, 1.0F, -1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(44, 78).addBox(10.0F, -27.0F, -19.0F, 1.0F, -1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -31.0F, -4.0F, 18.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 28).addBox(-5.5F, -6.0F, -6.5F, 11.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -13.0F, 0.2993F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.rightLeg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.leftLeg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
    }

    public ModelPart root()
    {
        return this.root;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
        leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
        leftArm.render(poseStack, buffer, packedLight, packedOverlay);
        rightArm.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
    }
}