package net.silentchaos512.endertendril.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;
import net.silentchaos512.lib.data.recipe.LibRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends LibRecipeProvider {
    public ModRecipesProvider(HolderLookup.Provider registries, RecipeOutput recipeOutput) {
        super(registries, recipeOutput, EnderTendrilMod.MOD_ID);
    }

    @Override
    protected void buildRecipes() {
        shapeless(RecipeCategory.MISC, Items.ENDER_EYE)
                .requires(ModItems.TENDRIL_PEARL.get(), 2)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_item", has(ModItems.TENDRIL_PEARL.get()))
                .save(this.output, modId("ender_eye"));
    }
}
