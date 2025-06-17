package net.yasinger.aircraftmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yasinger.aircraftmod.AircraftMod;
import net.yasinger.aircraftmod.block.ModBlocks;
import net.yasinger.aircraftmod.block.custom.PrimogemLampBlock;

public class ModBlockModelGen extends BlockStateProvider {
    public ModBlockModelGen(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(ModBlocks.PRIMOGEM_BLOCK.get(), cubeAll(ModBlocks.PRIMOGEM_BLOCK.get()));
        this.lampBlock(ModBlocks.PRIMOGEM_LAMP_BLOCK.get());
        this.addWithHaveModel(ModBlocks.PRIMOGEM_FRAME.get(), "primogem_frame");
        this.addWithHaveModel(ModBlocks.GLASS_JAR.get(), "glass_jar");
    }
    public void lampBlock(Block block) {
        var blockOff = models().cubeAll("primogem_lamp_off", new ResourceLocation(AircraftMod.MODID, "block/primogem_lamp_off"));
        var blockOn = models().cubeAll("primogem_lamp_on", new ResourceLocation(AircraftMod.MODID, "block/primogem_lamp_on"));
        getVariantBuilder(block).partialState().with(PrimogemLampBlock.LIT, true)
                .modelForState().modelFile(blockOn).addModel()
                .partialState().with(PrimogemLampBlock.LIT, false)
                .modelForState().modelFile(blockOff).addModel();
        simpleBlockItem(block, blockOff);
    }
    public void addWithHaveModel(Block block, String modelName) {
        var model_path = models().getExistingFile(new ResourceLocation(AircraftMod.MODID, modelName));
        var model = new ConfiguredModel(model_path);
        getVariantBuilder(block).partialState().setModels(model);
        simpleBlockItem(block, model_path);
    }
}
