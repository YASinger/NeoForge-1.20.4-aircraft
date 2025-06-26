package net.yasinger.aircraftmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yasinger.aircraftmod.entity.ModEntityTypes;
import net.yasinger.aircraftmod.entity.flyingsword.FlyingSwordEntity;

public class FlyingSwordItem extends Item {
    public FlyingSwordItem() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            Vec3 look = player.getLookAngle();
            Vec3 spawnPos = player.position().add(look.scale(2.0));
            FlyingSwordEntity sword = new FlyingSwordEntity(ModEntityTypes.FLYING_SWORD_ENTITY.get(), level, player);
            sword.setPos(spawnPos.x, spawnPos.y + 1.0, spawnPos.z);
            level.addFreshEntity(sword);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}
