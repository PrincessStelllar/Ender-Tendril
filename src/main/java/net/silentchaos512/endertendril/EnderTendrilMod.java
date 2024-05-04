package net.silentchaos512.endertendril;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.silentchaos512.endertendril.data.DataGenerators;
import net.silentchaos512.endertendril.setup.Registration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(EnderTendrilMod.MOD_ID)
public final class EnderTendrilMod {
    public static final String MOD_ID = "endertendril";
    public static final String MOD_NAME = "Ender Tendril";

    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static final Random RANDOM = new Random();
    public static final RandomSource RANDOM_SOURCE = RandomSource.create();

    public EnderTendrilMod(IEventBus modEventBus) {
        Registration.register(modEventBus);
        modEventBus.addListener(DataGenerators::gatherData);
    }

    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
