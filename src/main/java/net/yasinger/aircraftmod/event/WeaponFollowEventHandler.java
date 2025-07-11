package net.yasinger.aircraftmod.event;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.yasinger.aircraftmod.entity.flyingsword.FlyingSwordEntity;
import net.yasinger.aircraftmod.entity.ModEntityTypes;

@Mod.EventBusSubscriber
public class WeaponFollowEventHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide) return;
        ItemStack mainHand = player.getMainHandItem();
        // 这里只做简单判断：如果主手是剑类物品就生成实体
        if (!mainHand.isEmpty() && mainHand.getItem().getDescriptionId().contains("sword")) {
            // 检查玩家是否已经有飞剑实体跟随（用UUID判断）
            if (!player.getPersistentData().hasUUID("FlyingSwordEntityUUID")) {
                FlyingSwordEntity swordEntity = new FlyingSwordEntity(ModEntityTypes.FLYING_SWORD_ENTITY.get(), player.level(), player);
                swordEntity.moveTo(0, 0, 0); // 初始位置可以根据需要调整
                player.level().addFreshEntity(swordEntity);
                // 记录实体的唯一ID
                player.getPersistentData().putUUID("FlyingSwordEntityUUID", swordEntity.getUUID());
            }
        } else {
            // 主手不是武器时移除实体
            if (player.getPersistentData().hasUUID("FlyingSwordEntityUUID") && player.level() instanceof ServerLevel serverLevel) {
                java.util.UUID uuid = player.getPersistentData().getUUID("FlyingSwordEntityUUID");
                Entity entity = serverLevel.getEntity(uuid);
                if (entity instanceof FlyingSwordEntity) {
                    entity.discard();
                }
                player.getPersistentData().remove("FlyingSwordEntityUUID");
            }
        }
    }
}
