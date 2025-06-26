package net.yasinger.aircraftmod;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.yasinger.aircraftmod.block.ModBlocks;
import net.yasinger.aircraftmod.block.ModBlockEntities;
import net.yasinger.aircraftmod.entity.ModEntityTypes;
import net.yasinger.aircraftmod.item.ModCreativeTab;
import net.yasinger.aircraftmod.item.ModItems;
import org.slf4j.Logger;

@Mod(AircraftMod.MODID)
public class AircraftMod
{
    public static final String MODID = "aircraftmod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public AircraftMod(IEventBus modEventBus)
    {
        modEventBus.addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTab.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }
}
