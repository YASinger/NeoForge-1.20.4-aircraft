package net.yasinger.aircraftmod.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.block.custom.GlassJar;
import net.yasinger.aircraftmod.block.custom.PrimogemFrame;
import net.yasinger.aircraftmod.block.custom.PrimogemLampBlock;
import net.yasinger.aircraftmod.block.custom.PrimogemBlock;
import net.yasinger.aircraftmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, AircraftMod.MODID);
    public static final Supplier<Block> PRIMOGEM_BLOCK = registerBlock("primogem_block", PrimogemBlock::new);
    public static final Supplier<Block> PRIMOGEM_LAMP_BLOCK = registerBlock("primogem_lamp_block",
            PrimogemLampBlock::new);
    public static final Supplier<Block> PRIMOGEM_FRAME = registerBlock("primogem_frame", PrimogemFrame::new);
    public static final Supplier<Block> GLASS_JAR = registerBlock("glass_jar", GlassJar::new);
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
    private static Supplier<Block> registerBlock(String name, Supplier<Block> block) {
        Supplier<Block> registeredBlock = BLOCKS.register(name, block);
        ModItems.registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }
}
