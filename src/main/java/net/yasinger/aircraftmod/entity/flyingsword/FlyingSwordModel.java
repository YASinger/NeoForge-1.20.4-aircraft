package net.yasinger.aircraftmod.entity.flyingsword;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.yasinger.aircraftmod.AircraftMod;

public class FlyingSwordModel extends EntityModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AircraftMod.MODID, "fly_sword_entity"), "main");
    private final ModelPart body;
    public FlyingSwordModel(ModelPart pRoot) {
        this.body = pRoot.getChild("body");
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-1.0F, -1.0F, -18.0F, 3.0F, 1.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-3.0F, -2.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -1.0F, 1.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                , PartPose.offset(0.0F, 23.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        body.xRot = 0;
        body.yRot = 0;
        body.zRot = 0;
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
