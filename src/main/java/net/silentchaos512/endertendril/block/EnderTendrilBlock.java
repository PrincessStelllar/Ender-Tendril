package net.silentchaos512.endertendril.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

public class EnderTendrilBlock extends GrowingPlantBodyBlock {
    public static final MapCodec<EnderTendrilBlock> CODEC = simpleCodec(EnderTendrilBlock::new);
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public EnderTendrilBlock(Properties builder) {
        super(builder, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return ModBlocks.ENDER_TENDRIL.get();
    }

    private Block getBodyPlant() {
        return ModBlocks.ENDER_TENDRIL_PLANT.get();
    }

    private Block getFloweringPlant() {
        return ModBlocks.FLOWERING_ENDER_TENDRIL.get();
    }

    @Override
    protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return CODEC;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos p_53918_, BlockState p_53915_, RandomSource p_374442_) {
        if (facing == this.growthDirection.getOpposite() && !state.canSurvive(level, currentPos)) {
            scheduledTickAccess.scheduleTick(currentPos, this, 1);
        }
        return state;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos posUp = pos.above();
        BlockState stateUp = worldIn.getBlockState(posUp);
        BlockPos posDown = pos.below();
        BlockState stateDown = worldIn.getBlockState(posDown);

        return (stateUp.is(ModTags.Blocks.ENDER_TENDRILS) || stateUp.isFaceSturdy(worldIn, posUp, this.growthDirection))
                && stateDown.is(ModTags.Blocks.ENDER_TENDRILS);
    }

    @Override
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        BlockPos pos1 = pos.above();
        BlockState state1 = worldIn.getBlockState(pos1);

        while (state1.is(ModTags.Blocks.ENDER_TENDRILS)) {
            pos1 = pos1.above();
            state1 = worldIn.getBlockState(pos1);
        }

        worldIn.destroyBlock(pos1.below(), false);
    }
}
