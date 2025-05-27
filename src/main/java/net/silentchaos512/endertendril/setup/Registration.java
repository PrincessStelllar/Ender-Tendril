package net.silentchaos512.endertendril.setup;

import net.neoforged.bus.api.IEventBus;

public final class Registration {
    private Registration() {throw new IllegalAccessError("Utility class");}

    public static void register(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModLoot.LOOT_MODIFIERS.register(modEventBus);
    }
}
