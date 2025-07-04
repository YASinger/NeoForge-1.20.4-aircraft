package net.yasinger.aircraftmod.entity.flyingsword;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import java.util.UUID;
import net.minecraft.world.level.Level;

import org.joml.Vector3f;
import org.slf4j.Logger;


public class FlyingSwordEntity extends Entity {
    private UUID ownerUUID;
    private static final double FOLLOW_DISTANCE = 2.0;
    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel, Player player) {
        super(pEntityType, pLevel);
        if (player != null) {
            this.ownerUUID = player.getUUID();
        }
    }
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYRot;
    private double lerpXRot;


    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (this.ownerUUID != null) {
                Player owner = this.level().getPlayerByUUID(this.ownerUUID);
                if (owner != null) {
                    // 设置飞剑悬浮在玩家脚下
                    double x = owner.getX();
                    double y = owner.getY() - 0.2F;
                    double z = owner.getZ();
                    this.setPos(x, y, z);
                }
            }
        } else {
            tickLerp();
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.hasUUID("ownerUUID")) {
            this.ownerUUID = pCompound.getUUID("ownerUUID");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        if (ownerUUID != null) {
            pCompound.putUUID("ownerUUID", ownerUUID);
        }
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public net.minecraft.world.entity.EntityDimensions getDimensions(net.minecraft.world.entity.Pose pose) {
        // 返回极小的尺寸，实现无碰撞体积
        return net.minecraft.world.entity.EntityDimensions.fixed(0.0F, 0.0F);
    }

    @Override
    public void lerpTo(double pX, double pY, double pZ, float pYRot, float pXRot, int pLerpSteps) {
        // 更新当前同步点
        this.lerpX = pX;
        this.lerpY = pY;
        this.lerpZ = pZ;
        this.lerpYRot = pYRot;
        this.lerpXRot = pXRot;
        this.lerpSteps = pLerpSteps;
    }

    public void tickLerp() {
        if (this.lerpSteps > 0) {
            double d0 = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
            double d1 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
            double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
            double d3 = net.minecraft.util.Mth.wrapDegrees(this.lerpYRot - (double) this.getYRot());
            this.setYRot(this.getYRot() + (float) d3 / (float) this.lerpSteps);
            this.setXRot(this.getXRot() + (float) (this.lerpXRot - (double) this.getXRot()) / (float) this.lerpSteps);
            --this.lerpSteps;
            this.setPos(d0, d1, d2);
            this.setRot(this.getYRot(), this.getXRot());
        }
    }
}
