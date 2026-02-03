package net.themilkturtle.magical.world.level.block;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.themilkturtle.magical.block.ModBlocks;
import net.themilkturtle.magical.item.ModItems;

public class BulbVinesPlantBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public static final BooleanProperty BERRIES = BooleanProperty.create("berries");

    public BulbVinesPlantBlock(BlockBehaviour.Properties p_154873_) {
        super(p_154873_, Direction.UP, SHAPE, false);
        this.registerDefaultState(this.defaultBlockState().setValue(BERRIES, Boolean.valueOf(false)));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!state.getValue(BERRIES)) return InteractionResult.PASS;
        if (!world.isClientSide) {
            ItemStack stack = new ItemStack(ModItems.BULB_BERRIES.get());
            if (!player.addItem(stack)) {
                player.drop(stack, false);
            }
            world.setBlock(pos, state.setValue(BERRIES, false), 3);
            world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    public GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.BULB_VINE.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BERRIES);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(BERRIES) ? 7 : 0;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.randomTick(state, world, pos, random);
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        if (!world.isClientSide) {
            final int MAX_HEIGHT = 4;
            BlockPos.MutableBlockPos mutable = pos.mutable();
            int distance = 0;
            while (distance < MAX_HEIGHT) {
                mutable.move(Direction.UP);
                BlockState bs = world.getBlockState(mutable);
                if (bs.is(ModBlocks.BULB_VINE.get())) {
                    world.setBlock(mutable, bs.setValue(BulbVinesBlock.BERRIES, true), 3);
                    for (int i = 0; i < MAX_HEIGHT; i++) {
                        BlockPos target = mutable.below(i+1);
                        BlockState tbs = world.getBlockState(target);
                        if (!tbs.is(ModBlocks.BULB_VINE_PLANT.get())) break;
                        world.setBlock(target, tbs.setValue(BERRIES, true), 3);
                    }
                    break;
                }
                if (!bs.is(ModBlocks.BULB_VINE_PLANT.get())) break;
                distance++;
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, world, pos, newState, isMoving);
    }

}
