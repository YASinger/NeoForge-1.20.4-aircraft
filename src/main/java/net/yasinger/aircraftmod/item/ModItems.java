package net.yasinger.aircraftmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.item.custom.FlyingSwordItem;
import net.yasinger.aircraftmod.item.custom.MagicIngot;
import net.yasinger.aircraftmod.item.custom.Primogem;
import net.yasinger.aircraftmod.item.custom.PrimogemApple;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, AircraftMod.MODID);
    public static final Supplier<Item> PRIMOGEM = ITEMS.register("primogem", Primogem::new);
    public static final Supplier<Item> MAGIC_INGOT = ITEMS.register("magic_ingot", MagicIngot::new);
    public static final Supplier<Item> PRIMOGEM_APPLE = ITEMS.register("primogem_apple", PrimogemApple::new);
    public static final Supplier<Item> FLYING_SWORD_ITEM = ITEMS.register("flying_sword", FlyingSwordItem::new);
    public static void registerBlockItem(String name, Supplier<Block> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
