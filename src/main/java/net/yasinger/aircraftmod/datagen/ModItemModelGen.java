package net.yasinger.aircraftmod.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.item.ModItems;

import java.util.Objects;

public class ModItemModelGen extends ItemModelProvider {
    public ModItemModelGen(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(ModItems.PRIMOGEM.get());
        this.magicIngotModel(getResourceLocation(ModItems.MAGIC_INGOT.get()));
        this.basicItem(ModItems.PRIMOGEM_APPLE.get());
        this.basicItem(ModItems.FLYING_SWORD_ITEM.get());
    }
    public ResourceLocation getResourceLocation(Item item) {
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
    }
    public void magicIngotModel(ResourceLocation item) {
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("item/iron_ingot"))
                .override().predicate(new ResourceLocation(AircraftMod.MODID, "size"), 16)
                .model(new ModelFile.UncheckedModelFile("item/gold_ingot")).end();
    }
}
