package de.srr.createvehiclesadditional;

import de.srr.createvehiclesadditional.Blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = CreateVehiclesAdditional.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = CreateVehiclesAdditional.MOD_ID, value = Dist.CLIENT)
public class CreateVehiclesAdditionalClient {
    public CreateVehiclesAdditionalClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    //setRenderLayer on line 37 is flagged as deprecated (needs to be changed in future)
    @SuppressWarnings("deprecation")
    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        CreateVehiclesAdditional.LOGGER.info("HELLO FROM CLIENT SETUP");
        CreateVehiclesAdditional.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

        // to load transparent Textures correctly
        event.enqueueWork(() -> {
           //add new Block with transparent Texture here
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ELEMENT_SEPARATOR.get(), RenderType.cutout() // oder translucent()

            );
        });
    }
}
