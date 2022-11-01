package net.fabricmc.shropshire.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class FurnitureBlock extends Block {
    public FurnitureBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);//应该是碰撞箱的喵

    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.BlockView view, BlockPos pos, ShapeContext context){
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context){
        return SHAPE;
    }

}
