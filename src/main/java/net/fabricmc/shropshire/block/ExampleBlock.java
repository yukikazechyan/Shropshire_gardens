package net.fabricmc.shropshire.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ExampleBlock extends Block{
    public ExampleBlock(Settings settings) {
        super(settings);
    }

//    onuse方法请在此处编写
@Override
public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {//这部分是解决透视的喵
    return VoxelShapes.cuboid(1f, 1f, 1f, 1f, 1f, 1f);
}

    /*public class PolishedAndesiteSideBlock extends HorizontalFacingBlock {//关于朝向的喵

        public PolishedAndesiteSideBlock(Settings settings) {
            super(settings);
            setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        }

        @Override
        protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
            stateManager.add(Properties.HORIZONTAL_FACING);
        }


        public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ctx) {
            Direction dir = state.get(FACING);
            switch(dir) {
                case NORTH:
                    return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
                case SOUTH:
                    return VoxelShapes.cuboid(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
                case EAST:
                    return VoxelShapes.cuboid(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                case WEST:
                    return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
                default:
                    return VoxelShapes.fullCube();
            }
        }

        public BlockState getPlacementState(ItemPlacementContext ctx) {
            return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
        }

    }*/

    /*@Override//源小姐测试用的，此处大意为右击方块时反馈Hello, world!文本
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.sendMessage(Text.of("Hello, world!"), false);
        }

        return ActionResult.SUCCESS;

    }*/
}
