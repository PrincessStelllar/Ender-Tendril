package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.loot.ChestInjectorLootModifier;

public final class DataGenerators {
    private DataGenerators() {}

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        var existingFileHelper = event.getExistingFileHelper();
        var packOutput = gen.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        ModBlockTagsProvider blocks = new ModBlockTagsProvider(event);
        gen.addProvider(true, blocks);
        gen.addProvider(true, new ModItemTagsProvider(event, blocks));

        gen.addProvider(true, new ModLootTables(packOutput, lookupProvider));
        gen.addProvider(true, new ModRecipesProvider(packOutput, lookupProvider));

        gen.addProvider(true, new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(gen, existingFileHelper));

        gen.addProvider(true, new GlobalLootModifierProvider(packOutput, lookupProvider, EnderTendrilMod.MOD_ID) {
            @Override
            protected void start() {
                add("chest_loot_injector", new ChestInjectorLootModifier(new LootItemCondition[]{}));
            }
        });
    }
}
