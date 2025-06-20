package net.yasinger.aircraftmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.yasinger.aircraftmod.block.ModBlockEntities;
import net.yasinger.aircraftmod.block.blockentity.PrimogemCounterEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrimogemCounterBlock extends BaseEntityBlock {
    public PrimogemCounterBlock() {
        super(Properties.ofFullCopy(Blocks.STONE));
    }
    @Override
    public  BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PrimogemCounterEntity(pPos, pState);
    }
    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && InteractionHand.MAIN_HAND.equals(hand)) {
            var primogemCounterEntity = (PrimogemCounterEntity) level.getBlockEntity(pos);
            int count = primogemCounterEntity.increase();
            player.sendSystemMessage(Component.literal("counter" + count));
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }
    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }
@Nullable
@Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            @NotNull Level level,
            @NotNull BlockState state,
            @NotNull BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        if (type == ModBlockEntities.PRIMOGEM_COUNTER_ENTITY.get()) {
            return (lvl, pos, st, be) -> {
                if (be instanceof PrimogemCounterEntity primogemCounterEntity) {
                    PrimogemCounterEntity.serverTick(lvl, pos, st, primogemCounterEntity);
                }
            };
        }
        return null;
    }
}
