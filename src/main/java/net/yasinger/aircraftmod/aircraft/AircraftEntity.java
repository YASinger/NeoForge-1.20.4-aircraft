package net.yasinger.aircraftmod.aircraft;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AircraftEntity extends Entity {
    public AircraftEntity(EntityType<? extends AircraftEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        // 定义同步数据（如动画状态、角度等）
    }

    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        // 读取实体数据
    }

    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        // 保存实体数据
    }

    @Override
    public void tick() {
        super.tick();
        // 飞机实体的每帧逻辑，如运动、动画状态更新等
    }

    public void onPlayerControl(Player player, Vec3 input) {
        // 处理玩家输入，控制飞机运动和动画
    }
}

