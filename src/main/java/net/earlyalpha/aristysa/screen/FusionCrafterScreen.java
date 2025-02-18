package net.earlyalpha.aristysa.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FusionCrafterScreen extends AbstractContainerScreen<FusionCrafterMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Aristysa.MOD_ID,"textures/gui/fusion_crafter_gui.png");
    public FusionCrafterScreen(FusionCrafterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics context, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        context.blit(TEXTURE,x,y,32,0,imageWidth,imageHeight);

        renderProgressArrow(context,x,y);
    }
    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if (menu.isCrafting()) {
            context.blit(TEXTURE, x + 45, y + 42, 208, 0, menu.getScaledProgress(), 8);
            context.blit(TEXTURE, x + 99 + (32-menu.getScaledProgress()) , y + 42, 32-menu.getScaledProgress(), 0,  menu.getScaledProgress(), 8);
        }
    }
}
