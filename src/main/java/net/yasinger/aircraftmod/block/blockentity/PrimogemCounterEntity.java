package net.yasinger.aircraftmod.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.yasinger.aircraftmod.block.ModBlockEntities;

public class PrimogemCounterEntity extends BlockEntity {
    private int count = 0;
    public PrimogemCounterEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PRIMOGEM_COUNTER_ENTITY.get(), pPos, pBlockState);
    }
    public int increase() {
        count++;
        setChanged();
        return count;
    }
}
