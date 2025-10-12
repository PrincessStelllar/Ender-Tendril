package net.silentchaos512.endertendril.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), EnderTendrilMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Blocks.ENDER_TENDRILS)
                .add(ModBlocks.ENDER_TENDRIL.get())
                .add(ModBlocks.ENDER_TENDRIL_PLANT.get())
                .add(ModBlocks.FLOWERING_ENDER_TENDRIL.get());

        tag(BlockTags.CLIMBABLE)
            .addTag(ModTags.Blocks.ENDER_TENDRILS);
    }
}
