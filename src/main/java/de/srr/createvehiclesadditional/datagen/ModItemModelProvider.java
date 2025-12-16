package de.srr.createvehiclesadditional.datagen;

import de.srr.createvehiclesadditional.CreateVehiclesAdditional;
import de.srr.createvehiclesadditional.Items.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateVehiclesAdditional.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CARBON.get());
        basicItem(ModItems.BUCKET_OF_ACRYLONITRILE.get());
        basicItem(ModItems.POLYACRYLONITRILE_POWDER.get());
        basicItem(ModItems.CARBON_ALLOY.get());
        basicItem(ModItems.CARBON_SHEET.get());
        basicItem(ModItems.CARBON_FIBER_MASS.get());
    }
}
