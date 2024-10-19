package net.silentchaos512.endertendril.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;

import java.util.Objects;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), EnderTendrilMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(ResourceLocation.withDefaultNamespace("item/generated"));

        simpleBuilder(ModItems.ENDER_TENDRIL_SEED, itemGenerated);
        simpleBuilder(ModItems.TENDRIL_PEARL, itemGenerated);
    }

    private void simpleBuilder(Supplier<? extends Item> item, ModelFile parent) {
        String path = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get())).getPath();
        simpleBuilder(item, parent, "item/" + path);
    }

    private void simpleBuilder(Supplier<? extends Item> item, ModelFile parent, String texture) {
        String path = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get())).getPath();
        getBuilder(path).parent(parent).texture("layer0", texture);
    }
}
