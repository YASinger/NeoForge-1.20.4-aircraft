package net.yasinger.aircraftmod.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PrimogemBlock extends Block {
    public PrimogemBlock() {
        super(Properties.ofFullCopy(Blocks.STONE).strength(5f));
    }
}
