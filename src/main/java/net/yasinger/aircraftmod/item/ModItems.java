package net.yasinger.aircraftmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.item.custom.Primogem;
import net.yasinger.aircraftmod.item.custom.PrimogemApple;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, AircraftMod.MODID);
    public static final Supplier<Item> PRIMOGEM = ITEMS.register("primogem", () -> new Primogem(new Item.Properties()));
    public static final Supplier<Item> MAGIC_INGOT = ITEMS.register("magic_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> PRIMOGEM_APPLE = ITEMS.register("primogem_apple", () -> new PrimogemApple());
    public static void registerBlockItem(String name, Supplier<Block> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
