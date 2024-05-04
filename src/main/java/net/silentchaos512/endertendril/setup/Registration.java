package net.silentchaos512.endertendril.setup;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silentchaos512.endertendril.EnderTendrilMod;

public final class Registration {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EnderTendrilMod.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EnderTendrilMod.MOD_ID);

    private Registration() {throw new IllegalAccessError("Utility class");}

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ModLoot.LOOT_MODIFIERS.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
    }
}
