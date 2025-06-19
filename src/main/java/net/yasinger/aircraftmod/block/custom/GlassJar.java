package net.yasinger.aircraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class GlassJar extends Block {
    public GlassJar() {
        super(Properties.ofFullCopy(Blocks.GLASS).strength(0.3f).noOcclusion());
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    }
    // 这里可以添加更多的自定义逻辑或方法
}
