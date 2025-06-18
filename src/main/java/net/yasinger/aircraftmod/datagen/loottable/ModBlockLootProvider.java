package net.yasinger.aircraftmod.datagen.loottable;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.yasinger.aircraftmod.block.ModBlocks;
import net.yasinger.aircraftmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {
    public ModBlockLootProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }
    public static final Set<Block> BLOCKS = Set.of(
            ModBlocks.PRIMOGEM_BLOCK.get(),
            ModBlocks.PRIMOGEM_LAMP_BLOCK.get()
    );
    @Override
    protected void generate() {
        this.add(ModBlocks.PRIMOGEM_BLOCK.get(), createSingleItemTableWithSilkTouch(
                ModBlocks.PRIMOGEM_BLOCK.get(),
                ModItems.PRIMOGEM.get(),
                UniformGenerator.between(9, 9)
        ));
        this.dropSelf(ModBlocks.PRIMOGEM_LAMP_BLOCK.get());
    }
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BLOCKS;
    }
}
