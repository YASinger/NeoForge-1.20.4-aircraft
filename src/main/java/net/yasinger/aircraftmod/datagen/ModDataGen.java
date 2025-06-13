package net.yasinger.aircraftmod.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.yasinger.aircraftmod.AircraftMod;

@Mod.EventBusSubscriber(modid = AircraftMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        //  语言文件
        event.getGenerator().addProvider(
                event.includeClient(),
                new ModZhLangGen(event.getGenerator().getPackOutput(), AircraftMod.MODID, "zh_cn")
        );
        //  物品模型
        event.getGenerator().addProvider(
                event.includeClient(),
                new ModItemModelGen(event.getGenerator().getPackOutput(), AircraftMod.MODID, existingFileHelper)
        );
        //  方块模型
        event.getGenerator().addProvider(
                event.includeClient(),
                new ModBlockModelGen(event.getGenerator().getPackOutput(), AircraftMod.MODID, existingFileHelper)
        );
        //  方块标签
        event.getGenerator().addProvider(
                event.includeServer(),
                new ModBlockTagProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), AircraftMod.MODID, existingFileHelper)
        );
    }
}
