package net.silentchaos512.endertendril.setup;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.loot.ChestInjectorLootModifier;

public final class ModLoot {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EnderTendrilMod.MOD_ID);

    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ChestInjectorLootModifier>> CHEST_LOOT_INJECTOR =
            LOOT_MODIFIERS.register("chest_loot_injector", ChestInjectorLootModifier.CODEC);

    private ModLoot() {}
}
