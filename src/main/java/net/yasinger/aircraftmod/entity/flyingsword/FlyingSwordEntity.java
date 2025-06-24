package net.yasinger.aircraftmod.entity.flyingsword;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import org.slf4j.Logger;


public class FlyingSwordEntity extends Entity {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityDataAccessor<Integer> COUNTER = SynchedEntityData.defineId(FlyingSwordEntity.class, EntityDataSerializers.INT);
    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Override
    public void tick() {
        if(this.level().isClientSide){
            LOGGER.info(this.entityData.get(COUNTER).toString());
        } else {
            this.entityData.set(COUNTER, this.entityData.get(COUNTER));
            LOGGER.info(this.entityData.get(COUNTER).toString());
        }
        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(COUNTER, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.entityData.set(COUNTER, pCompound.getInt("counter"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("counter", this.entityData.get(COUNTER));
    }
}
