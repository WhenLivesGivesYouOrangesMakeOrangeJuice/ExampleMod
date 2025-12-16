package de.srr.createvehiclesadditional.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class GasPipeBlock extends Block {

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST  = BooleanProperty.create("east");
    public static final BooleanProperty WEST  = BooleanProperty.create("west");
    public static final BooleanProperty UP    = BooleanProperty.create("up");
    public static final BooleanProperty DOWN  = BooleanProperty.create("down");


    private static final VoxelShape CORE  = Block.box(4, 4, 4, 12, 12, 12);
    private static final VoxelShape NORTH_SHAPE = Block.box(4, 4, 0, 12, 12, 4);
    private static final VoxelShape SOUTH_SHAPE = Block.box(4, 4, 12, 12, 12, 16);
    private static final VoxelShape EAST_SHAPE  = Block.box(12, 4, 4, 16, 12, 12);
    private static final VoxelShape WEST_SHAPE  = Block.box(0, 4, 4, 4, 12, 12);
    private static final VoxelShape UP_SHAPE    = Block.box(4, 12, 4, 12, 16, 12);
    private static final VoxelShape DOWN_SHAPE  = Block.box(4, 0, 4, 12, 4, 12);


    public static final DirectionProperty FACING = BlockStateProperties.FACING;



    public GasPipeBlock(Properties properties) {
        super(properties.noOcclusion());

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(NORTH, false)
                        .setValue(SOUTH, false)
                        .setValue(EAST,  false)
                        .setValue(WEST,  false)
                        .setValue(UP,    false)
                        .setValue(DOWN,  false)
                        .setValue(FACING,  Direction.NORTH)
        );


    }

    private boolean canConnectTo(BlockGetter level, BlockPos pos, Direction dir) {
        BlockPos neighborPos = pos.relative(dir);
        BlockState neighborState = level.getBlockState(neighborPos);
        return neighborState.getBlock() instanceof GasPipeBlock;
    }

    private static int countConnections(BlockState state) {
        int count = 0;
        if (state.getValue(NORTH)) count++;
        if (state.getValue(SOUTH)) count++;
        if (state.getValue(EAST))  count++;
        if (state.getValue(WEST))  count++;
        if (state.getValue(UP))    count++;
        if (state.getValue(DOWN))  count++;
        return count;
    }

    private static Direction getSingleConnection(BlockState state) {
        if (state.getValue(NORTH)) return Direction.NORTH;
        if (state.getValue(SOUTH)) return Direction.SOUTH;
        if (state.getValue(EAST))  return Direction.EAST;
        if (state.getValue(WEST))  return Direction.WEST;
        if (state.getValue(UP))    return Direction.UP;
        if (state.getValue(DOWN))  return Direction.DOWN;
        return null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockGetter level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();

        return this.defaultBlockState()
                .setValue(FACING, ctx.getNearestLookingDirection())
                .setValue(NORTH, canConnectTo(level, pos, Direction.NORTH))
                .setValue(SOUTH, canConnectTo(level, pos, Direction.SOUTH))
                .setValue(EAST,  canConnectTo(level, pos, Direction.EAST))
                .setValue(WEST,  canConnectTo(level, pos, Direction.WEST))
                .setValue(UP,    canConnectTo(level, pos, Direction.UP))
                .setValue(DOWN,  canConnectTo(level, pos, Direction.DOWN));
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, Direction direction,
                                           BlockState neighbourState, @NotNull LevelAccessor level,
                                           @NotNull BlockPos pos, @NotNull BlockPos neighbourPos) {

        boolean connected = neighbourState.getBlock() instanceof GasPipeBlock;


        return switch (direction) {
            case NORTH -> state.setValue(NORTH, connected);
            case SOUTH -> state.setValue(SOUTH, connected);
            case EAST  -> state.setValue(EAST,  connected);
            case WEST  -> state.setValue(WEST,  connected);
            case UP    -> state.setValue(UP,    connected);
            case DOWN  -> state.setValue(DOWN,  connected);
        };

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, FACING);
    }
    private static VoxelShape shapeFor(Direction dir) {
        return switch (dir) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST  -> EAST_SHAPE;
            case WEST  -> WEST_SHAPE;
            case UP    -> UP_SHAPE;
            case DOWN  -> DOWN_SHAPE;
        };
    }


    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level,
                                        @NotNull BlockPos pos, @NotNull CollisionContext context) {

        VoxelShape shape = CORE;
        int connections = countConnections(state);

        if (connections == 0) {
            Direction facing = state.getValue(FACING);

            shape = Shapes.or(shape, shapeFor(facing));
            shape = Shapes.or(shape, shapeFor(facing.getOpposite()));

            return shape;
        }
        if (connections == 1) {
            Direction dir = getSingleConnection(state);

            assert dir != null;
            shape = Shapes.or(shape, shapeFor(dir));
            shape = Shapes.or(shape, shapeFor(dir.getOpposite()));

            return shape;
        }
        if (state.getValue(NORTH)) shape = Shapes.or(shape, NORTH_SHAPE);
        if (state.getValue(SOUTH)) shape = Shapes.or(shape, SOUTH_SHAPE);
        if (state.getValue(EAST))  shape = Shapes.or(shape, EAST_SHAPE);
        if (state.getValue(WEST))  shape = Shapes.or(shape, WEST_SHAPE);
        if (state.getValue(UP))    shape = Shapes.or(shape, UP_SHAPE);
        if (state.getValue(DOWN))  shape = Shapes.or(shape, DOWN_SHAPE);

        return shape;
    }

    @Override
    public boolean propagatesSkylightDown(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return true;
    }
}