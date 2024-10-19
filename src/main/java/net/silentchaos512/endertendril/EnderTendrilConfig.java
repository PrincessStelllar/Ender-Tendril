package net.silentchaos512.endertendril;

import net.neoforged.neoforge.common.ModConfigSpec;

public class EnderTendrilConfig {
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    static {
        var commonPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();
    }

    public static final class Common {
        public final ModConfigSpec.DoubleValue tendrilGrowthSpeedMultiplier;

        private Common(ModConfigSpec.Builder builder) {
            tendrilGrowthSpeedMultiplier = builder
                    .comment(
                            "A multiplier for the growth speed of ender tendrils.",
                            "1.0 is the default speed.",
                            "Higher values will increase growth speed."
                    )
                    .defineInRange("tendrilGrowthSpeedMultiplier", 1.0f, 0.01f, 25f);
        }
    }
}
