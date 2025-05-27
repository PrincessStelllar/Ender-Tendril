package net.silentchaos512.endertendril.setup;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.block.EnderTendrilBlock;
import net.silentchaos512.endertendril.block.EnderTendrilTopBlock;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EnderTendrilMod.MOD_ID);

    public static final DeferredBlock<EnderTendrilTopBlock> ENDER_TENDRIL = BLOCKS.registerBlock(
            "ender_tendril",
            EnderTendrilTopBlock::new,
            BlockBehaviour.Properties.of()
                    .randomTicks()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
    );
    public static final DeferredBlock<EnderTendrilBlock> ENDER_TENDRIL_PLANT = BLOCKS.registerBlock(
            "ender_tendril_plant",
            EnderTendrilBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
    );
    public static final DeferredBlock<FloweringEnderTendrilBlock> FLOWERING_ENDER_TENDRIL = BLOCKS.registerBlock(
            "flowering_ender_tendril",
            FloweringEnderTendrilBlock::new,
            BlockBehaviour.Properties.of()
                    .randomTicks()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
    );

    private ModBlocks() {
    }
}
