package net.yasinger.aircraftmod.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.yasinger.aircraftmod.block.ModBlockEntities;

public class PrimogemCounterEntity extends BlockEntity {
    private int count = 0;
    private static final int MAX_COUNT = 20 * 2;
    private int tickCount = 0;
    public PrimogemCounterEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PRIMOGEM_COUNTER_ENTITY.get(), pPos, pBlockState);
    }
    public int increase() {
        count++;
        setChanged();
        return count;
    }
    public static void serverTick(Level level, BlockPos pos, BlockState state, PrimogemCounterEntity entity) {
        if(level != null && !level.isClientSide()) {
            if(entity.tickCount == MAX_COUNT) {
                entity.tickCount = 1;
                Player nearestPlayer = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 3, false);
                if (nearestPlayer != null) {
                    nearestPlayer.sendSystemMessage(Component.literal("Primogem Counter: " + entity.count));
                }
            } else {
                entity.tickCount++;
            }
        }
    }
    @Override
    public void load(CompoundTag tag) {
        count = tag.getInt("count");
        super.load(tag);
    }
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("count", count);
    }
}
