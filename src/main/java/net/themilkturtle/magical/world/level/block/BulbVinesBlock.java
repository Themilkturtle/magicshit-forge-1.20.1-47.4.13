package net.themilkturtle.magical.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.themilkturtle.magical.block.ModBlocks;
import net.themilkturtle.magical.item.ModItems;

public class BulbVinesBlock extends GrowingPlantHeadBlock {
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);
    public static final BooleanProperty BERRIES = BooleanProperty.create("berries");

    public BulbVinesBlock(BlockBehaviour.Properties p_154864_) {
        super(p_154864_, Direction.UP, SHAPE, false, 0.1D);
        this.registerDefaultState(this.defaultBlockState().setValue(BERRIES, Boolean.valueOf(false)));
    }

    public int getBlocksToGrowWhenBonemealed(RandomSource p_222649_) {
        // Limit growth so total stack height never exceeds MAX_HEIGHT
        final int MAX_HEIGHT =4 ;
        // Default to vanilla value; precise limiting is handled in performBonemeal/randomTick
        return NetherVines.getBlocksToGrowWhenBonemealed(p_222649_);
    }

    public Block getBodyBlock() {
        return ModBlocks.BULB_VINE_PLANT.get();
    }

    public boolean canGrowInto(BlockState p_154869_) {
        return NetherVines.isValidGrowthState(p_154869_);
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
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!state.getValue(BERRIES)) return InteractionResult.PASS;
        if (!world.isClientSide) {
            ItemStack stack = new ItemStack(ModItems.BULB_BERRIES.get());
            if (!player.addItem(stack)) {
                player.drop(stack, false);
            }
            // only clear berries on this block
            world.setBlock(pos, state.setValue(BERRIES, false), 3);
            world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!state.getValue(BERRIES) && random.nextInt(6) == 0) {
            world.setBlock(pos, state.setValue(BERRIES, true), 3);
            final int MAX_HEIGHT = 4;
            for (int i = 1; i < MAX_HEIGHT; i++) {
                BlockPos target = pos.below(i);
                if (!world.getBlockState(target).is(ModBlocks.BULB_VINE_PLANT.get())) break;
                world.setBlock(target, world.getBlockState(target).setValue(BulbVinesPlantBlock.BERRIES, true), 3);
            }
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        if (world.isClientSide) return;
        final int MAX_HEIGHT = 4;
        // compute current height (head + consecutive plant blocks below)
        int currentHeight = 1;
        for (int i = 1; i < MAX_HEIGHT; i++) {
            BlockPos target = pos.below(i);
            if (!world.getBlockState(target).is(ModBlocks.BULB_VINE_PLANT.get())) break;
            currentHeight++;
        }

        if (currentHeight >= MAX_HEIGHT) return; // already at max height

        int desired = NetherVines.getBlocksToGrowWhenBonemealed(random);
        int allowed = Math.min(desired, MAX_HEIGHT - currentHeight);

        for (int i = 0; i < allowed; i++) {
            super.performBonemeal(world, random, pos, state);
        }

        // find new head (scan up from original pos)
        BlockPos.MutableBlockPos mutable = pos.mutable();
        BlockPos headPos = pos;
        for (int i = 0; i < MAX_HEIGHT; i++) {
            BlockPos check = mutable.move(Direction.UP);
            if (!world.getBlockState(check).is(ModBlocks.BULB_VINE.get())) {
                // previous position is head
                headPos = check.below();
                break;
            }
        }

        // set berries on head and propagate down up to MAX_HEIGHT
        BlockState headState = world.getBlockState(headPos);
        world.setBlock(headPos, headState.setValue(BERRIES, true), 3);
        for (int i = 1; i < MAX_HEIGHT; i++) {
            BlockPos target = headPos.below(i);
            if (!world.getBlockState(target).is(ModBlocks.BULB_VINE_PLANT.get())) break;
            world.setBlock(target, world.getBlockState(target).setValue(BulbVinesPlantBlock.BERRIES, true), 3);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState bs = world.getBlockState(below);
        if (bs.is(ModBlocks.BULB_VINE_PLANT.get())) return true;
        return bs.is(Blocks.SCULK) || bs.is(Blocks.STONE) || bs.is(ModBlocks.BULB_VINE.get()) || bs.is(ModBlocks.BULB_VINE_PLANT.get()) || bs.is(Blocks.DEEPSLATE) || bs.is(Blocks.DIRT) || bs.is(Blocks.GRASS_BLOCK);
    }
}