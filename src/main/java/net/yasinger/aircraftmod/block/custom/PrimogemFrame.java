package net.yasinger.aircraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.level.block.WeepingVinesPlantBlock.SHAPE;

public class PrimogemFrame extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGRD = BlockStateProperties.WATERLOGGED;
    public PrimogemFrame() {
        super(Properties.ofFullCopy(Blocks.STONE).strength(0.5f).noOcclusion());

    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        //return SHAPE;
        return Block.box(0,0,0,16,16,16);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        //super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGRD);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = context.getLevel().getBlockState(blockPos);
        if (blockState.is(this)) {
            return blockState.setValue(WATERLOGGRD, Boolean.FALSE);
        } else {
            FluidState fluidState = context.getLevel().getFluidState(blockPos);
            return this.defaultBlockState()
                    .setValue(WATERLOGGRD, fluidState.getType() == Fluids.WATER);
        }
    }
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos currentPos, BlockPos newPos) {
        if (state.getValue(WATERLOGGRD)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, newState, level, currentPos, newPos);
    }
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGRD) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        switch (type) {
            case LAND:
                return false; // 不允许路径寻找
            case WATER:
                //return state.getValue(WATERLOGGRD); // 如果是水中，则允许路径寻找
                return level.getFluidState(pos).is(FluidTags.WATER);
            case AIR:
                return false;
            default:
                return false; // 默认不允许
        }
    }
}
