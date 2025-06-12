package net.yasinger.aircraftmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yasinger.aircraftmod.block.ModBlocks;

public class ModBlockModelGen extends BlockStateProvider {
    public ModBlockModelGen(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(ModBlocks.PRIMOGEM_BLOCK.get(), cubeAll(ModBlocks.PRIMOGEM_BLOCK.get()));
    }
}
