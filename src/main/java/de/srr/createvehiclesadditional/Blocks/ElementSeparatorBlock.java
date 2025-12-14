package de.srr.createvehiclesadditional.Blocks;

import com.simibubi.create.AllShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;


public class ElementSeparatorBlock extends Block {

    public ElementSeparatorBlock(Properties properties) {
        super(properties.noOcclusion());

    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level,
                                        @NotNull BlockPos pos, @NotNull CollisionContext context) {

        return ELEMENT_SEPARATOR_SHAPE;
    }

    @Override
    public @NotNull VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {

        return Block.box(0, 0, 0, 16, 16, 16);
    }

    public static final VoxelShape ELEMENT_SEPARATOR_SHAPE;

    static {
        // Oberer voller Block (voller 16x14x16 Block)
        VoxelShape top = Block.box(0, 2, 0, 16, 16, 16);

        // Unterer voller Block (15x2x15)
        VoxelShape bottom = Block.box(1, 0, 1, 15, 2, 15);

        // Innenraum (8x12x14)
        VoxelShape inner = Block.box(
                2, 2, 4,    // minX, minY, minZ
                14, 16, 12  // maxX, maxY, maxZ
        );


        //Solid Kombinieren
        VoxelShape combinedSolid = Shapes.or(top, bottom);

        // Hohlraum erzeugen
        ELEMENT_SEPARATOR_SHAPE = Shapes.join(combinedSolid, inner, BooleanOp.ONLY_FIRST);
    }
}


