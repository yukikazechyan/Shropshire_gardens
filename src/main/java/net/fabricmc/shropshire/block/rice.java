package net.fabricmc.shropshire.block;

import net.fabricmc.shropshire.shropshire;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;


    public class rice extends CropBlock{
    public static  final IntProperty AGE = IntProperty.of("age", 0, 7);
    public  rice(Settings settings){
        super(settings);
    }
    @Override
    protected ItemConvertible getSeedsItem(){
        return shropshire.RICE_SEEDS;
}

    @Override
    public IntProperty getAgeProperty(){
        return AGE;
}
    @Override
    public int getMaxAge(){
        return 7;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(AGE);
    }

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)
        };


        public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return AGE_TO_SHAPE[(Integer)state.get(this.getAgeProperty())];
        }


    }

    /*
    public class TutorialMod implements ModInitializer {

        public static final Block CUSTOM_CROP_BLOCK = new CustomCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT));

        public static final Item CUSTOM_SEEDS = new AliasedBlockItem(TutorialMod.CUSTOM_CROP_BLOCK, new Item.Settings().group(ItemGroup.MISC));

        @Override
        @Environment(EnvType.CLIENT)

        public void onInitialize() {
            Registry.register(Registry.BLOCK, new Identifier("shropshire","custom_crop_block"), CUSTOM_CROP_BLOCK);
            Registry.register(Registry.ITEM, new Identifier("shropshire","custom_seeds"), CUSTOM_SEEDS);


        }
          @Environment(EnvType.CLIENT)
          public class TutorialModClient implements ClientModInitializer {
              @Override
              public void onInitializeClient() {
                  BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), CUSTOM_CROP_BLOCK);
              }
          }
    }

     */





