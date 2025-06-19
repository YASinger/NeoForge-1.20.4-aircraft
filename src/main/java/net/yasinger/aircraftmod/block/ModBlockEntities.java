package net.yasinger.aircraftmod.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.block.blockentity.PrimogemCounterEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AircraftMod.MODID);
    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }
    public static final Supplier<BlockEntityType<?>> PRIMOGEM_COUNTER_ENTITY =
            BLOCK_ENTITIES.register("primogem_counter_entity", () ->
            BlockEntityType.Builder.of(PrimogemCounterEntity::new,
                    ModBlocks.PRIMOGEM_COUNTER_BLOCK.get()).build(null));
}
