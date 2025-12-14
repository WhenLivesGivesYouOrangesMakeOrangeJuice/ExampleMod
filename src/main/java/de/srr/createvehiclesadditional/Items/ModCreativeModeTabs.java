package de.srr.createvehiclesadditional.Items;

import de.srr.createvehiclesadditional.Blocks.ModBlocks;
import de.srr.createvehiclesadditional.CreateVehiclesAdditional;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateVehiclesAdditional.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("mod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CARBON.get()))
                    .title(Component.translatable("creativetab.createvehiclesadditional.modItems"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(ModBlocks.GAS_PIPE);
                        output.accept(ModBlocks.BLOCK_OF_CARBON);
                        output.accept(ModBlocks.TEMPERATURE_OVEN);
                        output.accept(ModBlocks.CARBON_FIBER_BLOCK);
                        output.accept(ModBlocks.ELEMENT_SEPARATOR);


                        output.accept(ModItems.BUCKET_OF_ACRYLONITRILE);
                        output.accept(ModItems.POLYACRYLONITRILE_POWDER);
                        output.accept(ModItems.CARBON);
                        output.accept(ModItems.CARBON_ALLOY);
                        output.accept(ModItems.CARBON_SHEET);
                        output.accept(ModItems.CARBON_FIBER_MASS);
                        output.accept(ModItems.GAS_TANK);
                        output.accept(ModItems.HYDROGEN_TANK);
                        output.accept(ModItems.NITROGEN_TANK);
                        output.accept(ModItems.OXYGEN_TANK);
                        output.accept(ModItems.PROPYLENE_TANK);
                        output.accept(ModItems.SULFUR_TANK);



                    }).build());




    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}