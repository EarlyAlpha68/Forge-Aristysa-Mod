package net.earlyalpha.aristysa.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LabotaryTrayScreen extends AbstractContainerScreen<LabotaryTrayMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Aristysa.MOD_ID,"textures/gui/labotary_tray_gui.png");
    public LabotaryTrayScreen(LabotaryTrayMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelX = 10000;
        this.inventoryLabelY = 10000;
        this.titleLabelX = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics context, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        context.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);

        renderProgressArrow(context,x,y);
    }
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if (menu.isCrafting()) {
            context.blit(TEXTURE, x + 83, y + 38, 176, 0,  menu.getScaledProgress(), 8);
        }
    }


}
