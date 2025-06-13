package net.yasinger.aircraftmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.block.ModBlocks;
import net.yasinger.aircraftmod.item.ModItems;

public class ModZhLangGen extends LanguageProvider {
    public ModZhLangGen(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        //  物品
        add(ModItems.PRIMOGEM.get(), "原石");
        add(ModItems.MAGIC_INGOT.get(), "魔法锭");
        //  方块
        add(ModBlocks.PRIMOGEM_BLOCK.get(), "原石块");
        add(ModBlocks.PRIMOGEM_LAMP_BLOCK.get(), "原石灯");
        //  物品栏
        add("creativetab.aircraft_tab", "航空器模组");
    }
}
