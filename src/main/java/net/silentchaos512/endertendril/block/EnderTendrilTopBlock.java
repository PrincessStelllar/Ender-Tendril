package net.silentchaos512.endertendril.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.silentchaos512.endertendril.EnderTendrilConfig;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

public class EnderTendrilTopBlock extends GrowingPlantHeadBlock {
    public static final MapCodec<EnderTendrilTopBlock> CODEC = simpleCodec(EnderTendrilTopBlock::new);
    private static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    private static final double GROWTH_CHANCE = 0.05;

    public EnderTendrilTopBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, GROWTH_CHANCE);
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource rand) {
        return 0;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }

    @Override
    protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
        return CODEC;
    }

    private double getGrowthChance() {
        return GROWTH_CHANCE * EnderTendrilConfig.COMMON.tendrilGrowthSpeedMultiplier.get();
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        // Never stop growing as long as space is available
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        // Removes the age check from super
        var targetPos = pos.relative(this.growthDirection);
        var targetBlockState = worldIn.getBlockState(targetPos);
        if (CommonHooks.canCropGrow(worldIn, targetPos, targetBlockState,random.nextDouble() < getGrowthChance())) {
            if (this.canGrowInto(targetBlockState)) {
                worldIn.setBlockAndUpdate(targetPos, state.cycle(AGE));
                CommonHooks.fireCropGrowPost(worldIn, targetPos, targetBlockState);
            }
        }
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.ENDER_TENDRIL_PLANT.get();
    }

    private Block getFloweringPlant() {
        return ModBlocks.FLOWERING_ENDER_TENDRIL.get();
    }

    private BlockState getGrownBlock(BlockGetter worldIn, BlockPos currentPos) {
        for (int i = 1; i < 4; ++i) {
            BlockState state = worldIn.getBlockState(currentPos.above(i));
            if (state.getBlock() != ModBlocks.ENDER_TENDRIL_PLANT.get()) {
                return getBodyBlock().defaultBlockState();
            }
        }
        return getFloweringPlant().defaultBlockState();
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos p_53956_, BlockState facingState, RandomSource p_374560_) {
        if (facing == this.growthDirection.getOpposite() && !state.canSurvive(level, currentPos)) {
            scheduledTickAccess.scheduleTick(currentPos, this, 1);
        }

        if (facing == this.growthDirection && facingState.is(this)) {
            return this.getGrownBlock(level, currentPos);
        } else {
            if (this.scheduleFluidTicks) {
                scheduledTickAccess.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }

            return state;
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);

        return blockstate.is(ModTags.Blocks.ENDER_TENDRILS) || blockstate.isFaceSturdy(worldIn, blockpos, this.growthDirection);
    }

    @Override
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        BlockPos pos1 = pos.above();
        BlockState state1 = worldIn.getBlockState(pos1);

        while (state1.is(ModTags.Blocks.ENDER_TENDRILS)) {
            pos1 = pos1.above();
            state1 = worldIn.getBlockState(pos1);
        }

        // Destroy the topmost block to take down the whole tendril
        worldIn.destroyBlock(pos1.below(), false);
    }
}
