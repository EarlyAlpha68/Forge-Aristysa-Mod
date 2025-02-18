package net.earlyalpha.aristysa.screen;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public class CyberwareGuiScreen extends AbstractContainerScreen<CyberwareGuiMenuType> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Aristysa.MOD_ID, "textures/gui/cyberware_gui.png");

    public CyberwareGuiScreen(CyberwareGuiMenuType pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }


    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.inventoryLabelX = 10000;

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        drawEntity(guiGraphics, this.leftPos + 88 , this.topPos +108 , 30, (float) this.leftPos + 88 - mouseX, (float) this.topPos + 62 - mouseY, Minecraft.getInstance().player);
    }
    public static void drawEntity(GuiGraphics context, int x, int y, int size, float mouseX, float mouseY, LivingEntity entity) {
        float f = (float) Math.atan(mouseX / 40.0f);
        float g = (float) Math.atan(mouseY / 40.0f);

        Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternionf2 = new Quaternionf().rotateX(g * 20.0f * ((float) Math.PI / 180));
        quaternionf.mul(quaternionf2);

        float h = entity.yBodyRot;
        float i = entity.getYRot();
        float j = entity.getXRot();
        float k = entity.yHeadRotO;
        float l = entity.yHeadRot;

        entity.yBodyRot = 180.0f + f * 20.0f;
        entity.setYRot(180.0f + f * 40.0f);
        entity.setXRot(-g * 20.0f);
        entity.yBodyRot = entity.getYRot();
        entity.yHeadRotO = entity.getYRot();
        entity.yHeadRot = entity.getYRot();

        // Update head rotation to follow the mouse
        float headYaw = 180.0f + f * 40.0f;
        float headPitch = -g * 20.0f;
        entity.yHeadRotO = headYaw;
        entity.yHeadRot = headYaw;
        entity.setXRot(headPitch);

        CyberwareGuiScreen.drawEntityHelp(context, x, y, size, quaternionf, quaternionf2, entity);

        entity.yBodyRot = h;
        entity.setYRot(i);
        entity.setXRot(j);
        entity.yHeadRotO = k;
        entity.yHeadRot = l;
    }

    public static void drawEntityHelp(GuiGraphics context, int x, int y, int size, Quaternionf quaternionf, @Nullable Quaternionf quaternionf2, LivingEntity entity) {
        PoseStack poseStack = context.pose();
        poseStack.pushPose();
        poseStack.translate(x, y, 50.0);
        poseStack.scale(size, size, -size);
        poseStack.mulPose(quaternionf);
        // Set light source from the bottom
        int light = 0xF000F0; // Maximum brightness

        RenderSystem.applyModelViewMatrix();
        EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();

        if (quaternionf2 != null) {
            quaternionf2.conjugate();
            entityRenderDispatcher.overrideCameraOrientation(quaternionf2);
        }

        entityRenderDispatcher.setRenderShadow(false);

        // Render the entity with constant light level and adjust for bottom light source
        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0f, 1.0f, poseStack, context.bufferSource(), light);
        });

        context.bufferSource().endBatch();

        entityRenderDispatcher.setRenderShadow(true);
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
    }




}
