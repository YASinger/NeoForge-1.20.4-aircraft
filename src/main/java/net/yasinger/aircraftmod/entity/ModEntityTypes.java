package net.yasinger.aircraftmod.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.entity.flyingsword.FlyingSwordEntity;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, AircraftMod.MODID);
    public static final Supplier<EntityType<Entity>> FLYING_SWORD_ENTITY = ENTITY_TYPES.register("flying_sword_entity",
            () -> EntityType.Builder.of(FlyingSwordEntity::new, MobCategory.MISC)
                    .sized(2, 0.5F)
//                    .clientTrackingRange(8)
//                    .updateInterval(10)
                    .build(AircraftMod.MODID + ":flying_sword_entity"));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
