package de.srr.createvehiclesadditional.Items;

import com.simibubi.create.AllCreativeModeTabs;
import de.srr.createvehiclesadditional.CreateVehiclesAdditional;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems{

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateVehiclesAdditional.MOD_ID);

    public static final DeferredItem<Item> CARBON = ITEMS.registerItem(
            "carbon",
            Item::new,
            new Item.Properties()
    );

    public static final DeferredItem<Item> BUCKET_OF_ACRYLONITRILE = ITEMS.registerItem(
            "bucket_of_acrylonitrile",
            Item::new,
            new Item.Properties()
    );

    public static final DeferredItem<Item> POLYACRYLONITRILE_POWDER = ITEMS.registerItem(
            "polyacrylonitrile_powder",
            Item::new,
            new Item.Properties()
    );

    public static final DeferredItem<Item> CARBON_ALLOY = ITEMS.registerItem(
            "carbon_alloy",
            Item::new,
            new Item.Properties()
    );

    public static final DeferredItem<Item> CARBON_SHEET = ITEMS.registerItem(
            "carbon_sheet",
            Item::new,
            new Item.Properties()
    );

    public static final DeferredItem<Item> CARBON_FIBER_MASS = ITEMS.registerItem(
            "carbon_fiber_mass",
            Item::new,
            new Item.Properties()
    );

    public static final DeferredItem<Item> INCA_KOLA_BOTTLE = ITEMS.registerItem(
            "inca_kola_bottle",
            Item::new,
            new Item.Properties().food(ModFoodProperties.INCA_KOLA_BOTTLE)
    );

    public static final DeferredItem<Item> GAS_TANK = ITEMS.registerItem(
            "gas_tank",
            Item::new,
            new Item.Properties()

    );

    public static final DeferredItem<Item> HYDROGEN_TANK = ITEMS.registerItem(
            "hydrogen_tank",
            Item::new,
            new Item.Properties()

    );

    public static final DeferredItem<Item> NITROGEN_TANK = ITEMS.registerItem(
            "nitrogen_tank",
            Item::new,
            new Item.Properties()

    );

    public static final DeferredItem<Item> OXYGEN_TANK = ITEMS.registerItem(
            "oxygen_tank",
            Item::new,
            new Item.Properties()

    );

    public static final DeferredItem<Item> PROPYLENE_TANK = ITEMS.registerItem(
            "propylene_tank",
            Item::new,
            new Item.Properties()

    );

    public static final DeferredItem<Item> SULFUR_TANK = ITEMS.registerItem(
            "sulfur_tank",
            Item::new,
            new Item.Properties()

    );



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}