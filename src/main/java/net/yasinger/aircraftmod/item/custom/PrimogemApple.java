package net.yasinger.aircraftmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class PrimogemApple extends Item {
    private static final FoodProperties FOOD_PROPERTIES = new FoodProperties.Builder()
            .nutrition(20)
            .saturationMod(10)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 1), 1)
            .build();
    public PrimogemApple() {
        super(new Properties()
                .food(FOOD_PROPERTIES));
    }
}
