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
import net.minecraft.world.entity.Entity;
import net.yasinger.aircraftmod.AircraftMod;

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
        // 用目标点插值实现逐帧平滑
        if (pEntity instanceof FlyingSwordEntity sword) {
            var last = sword.getLastTargetPos();
            var curr = sword.getCurrentTargetPos();
            double x = net.minecraft.util.Mth.lerp(pPartialTicks, last.x, curr.x);
            double y = net.minecraft.util.Mth.lerp(pPartialTicks, last.y, curr.y);
            double z = net.minecraft.util.Mth.lerp(pPartialTicks, last.z, curr.z);
            pPoseStack.translate(x - sword.getX(), y - sword.getY(), z - sword.getZ());
            // 让剑柄朝右上，剑尖朝左下
            // 先让剑面朝向玩家背后（假设实体yaw为玩家yaw）
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-pEntityYaw));
            // 再绕Z轴旋转45度（右上），绕X轴旋转-30度（左下）
//            pPoseStack.mulPose(Axis.ZP.rotationDegrees(45));
//            pPoseStack.mulPose(Axis.XP.rotationDegrees(-30));
        }
        pPoseStack.mulPose(Axis.YN.rotationDegrees(45));
        pPoseStack.translate(0,-1,0);
        VertexConsumer buffer = pBufferSource.getBuffer(this.flyingSwordModel.renderType(this.getTextureLocation(pEntity)));
        this.flyingSwordModel.renderToBuffer(pPoseStack, buffer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
    }
}
