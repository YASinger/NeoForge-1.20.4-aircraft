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
    private static final EntityDataAccessor<Vector3f> POSITION = SynchedEntityData.defineId(FlyingSwordEntity.class, EntityDataSerializers.VECTOR3);
    private UUID ownerUUID;
    private static final double FOLLOW_DISTANCE = 1.0;
    private Vector3f lastTargetPos = new Vector3f(0, 0, 0);
    private Vector3f currentTargetPos = new Vector3f(0, 0, 0);
    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel, Player player) {
        super(pEntityType, pLevel);
        if (player != null) {
            this.ownerUUID = player.getUUID();
            currentTargetPos = new Vector3f(
                    (float) (player.getX() - player.getLookAngle().x * FOLLOW_DISTANCE),
                    (float) (player.getY() + 1.0f), // 让剑悬浮在玩家头顶
                    (float) (player.getZ() - player.getLookAngle().z * FOLLOW_DISTANCE)
            );
            lastTargetPos = currentTargetPos;
            this.entityData.set(POSITION, currentTargetPos);
        }
    }
    @Override
    public void tick() {
        if (!this.level().isClientSide && ownerUUID != null) {
            Player owner = this.level().getPlayerByUUID(ownerUUID);
            if (owner != null) {
                Vector3f target = new Vector3f(
                        (float) (owner.getX() - owner.getLookAngle().x * FOLLOW_DISTANCE),
                        (float) (owner.getY() + 1.0f),
                        (float) (owner.getZ() - owner.getLookAngle().z * FOLLOW_DISTANCE)
                );
                if (!target.equals(this.entityData.get(POSITION))) {
                    this.entityData.set(POSITION, target);
                }
                this.setPos(target.x(), target.y(), target.z());
            }
        }
        if (this.level().isClientSide && !currentTargetPos.equals(this.entityData.get(POSITION))) {
            lastTargetPos.set(currentTargetPos);
            currentTargetPos.set(this.entityData.get(POSITION));
        }
        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(POSITION, new Vector3f(0, 0, 0));
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

    public Vector3f getLastTargetPos() {
        return lastTargetPos;
    }
    public Vector3f getCurrentTargetPos() {
        return currentTargetPos;
    }
}
