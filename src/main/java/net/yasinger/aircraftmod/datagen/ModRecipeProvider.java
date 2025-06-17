package net.yasinger.aircraftmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.yasinger.aircraftmod.block.ModBlocks;
import net.yasinger.aircraftmod.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        // 有序合成表
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GLASS_JAR.get())
                .pattern(" a ")
                .pattern("a a")
                .pattern("aaa")
                .define('a', ModItems.MAGIC_INGOT.get())
                .unlockedBy("has_magic_ingot", has(ModItems.MAGIC_INGOT.get()))
                .save(pRecipeOutput, "glass_jar_from_shaped");
        // 无序合成表
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PRIMOGEM_BLOCK.get())
                .requires(ModItems.PRIMOGEM.get(), 9)
                .unlockedBy("has_primogem", has(ModItems.PRIMOGEM.get()))
                .save(pRecipeOutput);
        // 烧炼配方
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.MAGIC_INGOT.get()), RecipeCategory.MISC, ModBlocks.GLASS_JAR.get(), 0.3f, 100)
                .unlockedBy("has_magic_ingot", has(ModItems.MAGIC_INGOT.get()))
                .save(pRecipeOutput, "glass_jar_from_smelting");
        // 切石机配方
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.PRIMOGEM_BLOCK.get()), RecipeCategory.MISC, ModBlocks.PRIMOGEM_FRAME.get())
                .unlockedBy("has_primogem_block", has(ModBlocks.PRIMOGEM_BLOCK.get()))
                .save(pRecipeOutput, "primogem_frame_from_stonecutting");
    }
}
