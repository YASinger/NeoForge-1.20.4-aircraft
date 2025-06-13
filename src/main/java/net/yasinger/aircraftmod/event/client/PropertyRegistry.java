package net.yasinger.aircraftmod.event.client;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.item.ModItems;

@Mod.EventBusSubscriber(modid = AircraftMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PropertyRegistry {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.MAGIC_INGOT.get(), new ResourceLocation(AircraftMod.MODID, "size"),
                    (itemStack, level, livingEntity, int_num) -> itemStack.getCount());
        });
    }
}
