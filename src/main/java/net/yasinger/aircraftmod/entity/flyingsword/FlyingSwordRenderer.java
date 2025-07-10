package net.yasinger.aircraftmod.entity.flyingsword;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.yasinger.aircraftmod.AircraftMod;
import org.joml.Vector3f;

import java.util.ArrayDeque;

public class FlyingSwordRenderer extends EntityRenderer {
    private EntityModel<FlyingSwordEntity> flyingSwordModel;
    public FlyingSwordRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        flyingSwordModel = new FlyingSwordModel(pContext.bakeLayer(FlyingSwordModel.LAYER_LOCATION));
    }
    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return new ResourceLocation(AircraftMod.MODID, "textures/entity/flying_sword_entity.png");
    }
    @Override
    public void render(Entity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBufferSource, pPackedLight);
        pPoseStack.pushPose();
        if (pEntity instanceof FlyingSwordEntity sword) {
            // 使用wrapDegrees处理角度差，保证插值平滑
            float yRot = (float) (sword.getLastlerpYRot() + net.minecraft.util.Mth.wrapDegrees(pEntity.getYRot() - sword.getLastlerpYRot()) * pPartialTicks);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(yRot));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getXRot()));
        }
        VertexConsumer buffer = pBufferSource.getBuffer(this.flyingSwordModel.renderType(this.getTextureLocation(pEntity)));
        this.flyingSwordModel.renderToBuffer(pPoseStack, buffer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
    }
}
