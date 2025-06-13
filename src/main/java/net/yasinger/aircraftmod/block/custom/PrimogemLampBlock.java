package net.yasinger.aircraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class PrimogemLampBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public PrimogemLampBlock() {
        super(Block.Properties.ofFullCopy(Blocks.REDSTONE_LAMP)
                .strength(1f)
                .lightLevel(state -> state.getValue(PrimogemLampBlock.LIT) ? 15 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            level.setBlock(pos,state.cycle(LIT),3);
        }
        return super.use(state, level, pos, player, hand, hit);
    }
}
