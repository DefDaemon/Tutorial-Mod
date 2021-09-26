package com.defdaemon.tutorialmod.client.screen;

import com.defdaemon.tutorialmod.TutorialMod;
import com.defdaemon.tutorialmod.common.container.LightningChannelerContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LightningChannelerScreen extends AbstractContainerScreen<LightningChannelerContainer>
{
    private final ResourceLocation GUI = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/lightning_channeler_gui.png");
    private final LightningChannelerContainer container;

    public LightningChannelerScreen(LightningChannelerContainer screenContainer, Inventory inv, Component titleIn)
    {
        super(screenContainer, inv, titleIn);
        container = screenContainer;
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.setShaderTexture(0, GUI);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if(container.isLightningStorm())
        {
            this.blit(matrixStack, i+82, j + 9, 176, 0, 13, 17);
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }
}
