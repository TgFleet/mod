package com.anubhav.steamcraft.client.render;

import com.anubhav.steamcraft.entity.SteamTrainEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SteamTrainRenderer extends EntityRenderer<SteamTrainEntity> {

    public SteamTrainRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(SteamTrainEntity entity, float yaw, float partialTicks, MatrixStack matrixStack,
                       IRenderTypeBuffer buffer, int light) {
        matrixStack.push();
        matrixStack.scale(2.0F, 1.0F, 1.0F);
        matrixStack.translate(0.0D, 1.0D, 0.0D);
        matrixStack.pop();
        super.render(entity, yaw, partialTicks, matrixStack, buffer, light);
    }

    @Override
    public ResourceLocation getEntityTexture(SteamTrainEntity entity) {
        return new ResourceLocation("steamcraft", "textures/entity/steam_train.png");
    }
}