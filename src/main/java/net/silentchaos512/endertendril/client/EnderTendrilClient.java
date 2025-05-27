package net.silentchaos512.endertendril.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.silentchaos512.endertendril.setup.ModBlocks;

public class EnderTendrilClient {
    public EnderTendrilClient(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(this::onClientSetup);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        setRenderTypes();
    }

    private void setRenderTypes() {
        RenderType cutout = RenderType.CUTOUT;
        setRenderLayer(ModBlocks.ENDER_TENDRIL, cutout);
        setRenderLayer(ModBlocks.ENDER_TENDRIL_PLANT, cutout);
        setRenderLayer(ModBlocks.FLOWERING_ENDER_TENDRIL, cutout);
    }

    @SuppressWarnings("deprecation")
    private void setRenderLayer(DeferredBlock<?> block, RenderType renderType) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), renderType);
    }
}
