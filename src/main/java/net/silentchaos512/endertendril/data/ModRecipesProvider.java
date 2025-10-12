package net.silentchaos512.endertendril.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;
import net.silentchaos512.lib.data.recipe.LibRecipeProvider;

public class ModRecipesProvider extends LibRecipeProvider {
    public ModRecipesProvider(HolderLookup.Provider registries, RecipeOutput recipeOutput) {
        super(registries, recipeOutput, EnderTendrilMod.MOD_ID);
    }

    @Override
    protected void buildRecipes() {
        shapeless(RecipeCategory.MISC, Items.ENDER_EYE)
                .requires(ModItems.TENDRIL_PEARL.get())
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_item", has(ModItems.TENDRIL_PEARL.get()))
                .save(this.output, modId("ender_eye"));
        shapeless(RecipeCategory.MISC, Items.ENDER_PEARL, 2)
                .requires(Items.ENDER_PEARL)
                .requires(ModItems.TENDRIL_PEARL)
                .unlockedBy("has_item", has(ModItems.TENDRIL_PEARL.get()))
                .save(this.output, modId("ender_pearl"));
    }
}
