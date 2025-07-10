package net.yasinger.aircraftmod.entity.flyingsword;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class FlyingSwordEntity extends Entity {
    private UUID ownerUUID;
    private static final double FOLLOW_DISTANCE = 0.5;

    // 插值相关变量
    private int lerpSteps;
    private double lerpX, lerpY, lerpZ;
    private double lastlerpX, lastlerpY, lastlerpZ;
    private double lerpYRot, lastlerpYRot;

    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel, Player player) {
        super(pEntityType, pLevel);
        if (player != null) {
            this.ownerUUID = player.getUUID();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            // 服务器端：跟随玩家并同步旋转
            if (this.ownerUUID != null) {
                Player owner = this.level().getPlayerByUUID(this.ownerUUID);
                if (owner != null) {
                    float yaw = owner.getYRot();
                    double rad = Math.toRadians(yaw);
                    double x = owner.getX() + Math.sin(rad) * FOLLOW_DISTANCE;
                    double y = owner.getY() + 1.5F; // 可根据需要调整高度
                    double z = owner.getZ() - Math.cos(rad) * FOLLOW_DISTANCE;
                    this.setPos(x, y, z);
                    float lastYRot = this.getYRot();
                    float targetYRot = -yaw;
                    float wrapped = net.minecraft.util.Mth.wrapDegrees(targetYRot - lastYRot) + lastYRot;
                    this.setYRot(wrapped); // 让飞剑YRot与玩家一致且连续
                }
            }
        } else {
            // 客户端：插值平滑移动
            tickLerp();
        }
    }

    @Override
    protected void defineSynchedData() {
        // 无需同步额外数据
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

    public double getLastlerpYRot() {
        return this.lastlerpYRot;
    }

    @Override
    public void lerpTo(double pX, double pY, double pZ, float pYRot, float pXRot, int pLerpSteps) {
        // 位置插值，防止瞬移
        this.lerpX = (pX - this.lastlerpX) > 8 || (pX - this.lastlerpX) < -8 ? pX : (pX * 2 - this.lastlerpX);
        this.lerpY = (pY - this.lastlerpY) > 8 || (pY - this.lastlerpY) < -8 ? pY : (pY * 2 - this.lastlerpY);
        this.lerpZ = (pZ - this.lastlerpZ) > 8 || (pZ - this.lastlerpZ) < -8 ? pZ : (pZ * 2 - this.lastlerpZ);
        this.lastlerpX = pX;
        this.lastlerpY = pY;
        this.lastlerpZ = pZ;
        this.lerpYRot = pYRot;
        this.lerpSteps = 3;
    }

    public void tickLerp() {
        if (this.lerpSteps > 0) {
            double d0 = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
            double d1 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
            double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
            double d3 = net.minecraft.util.Mth.wrapDegrees(this.lerpYRot - (double) this.getYRot());
            this.lastlerpYRot = this.getYRot();
            this.setYRot(this.getYRot() + (float) d3 / (float) this.lerpSteps);
            this.setXRot(270);
            this.setPos(d0, d1, d2);
            this.lerpSteps--;
        }
    }
}
