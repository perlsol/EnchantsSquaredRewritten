package me.athlaeos.enchantssquared.listeners;

import me.athlaeos.enchantssquared.EnchantsSquared;
import me.athlaeos.enchantssquared.domain.EntityEquipment;
import me.athlaeos.enchantssquared.enchantments.CustomEnchant;
import me.athlaeos.enchantssquared.enchantments.on_item_damage.TriggerOnItemDamageEnchantment;
import me.athlaeos.enchantssquared.managers.CustomEnchantManager;
import me.athlaeos.enchantssquared.managers.EntityEquipmentCacheManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class ItemDamageListener implements Listener {

    @EventHandler
    public void onEntityTakeDamage(PlayerItemDamageEvent event){
        if (!event.isCancelled()){
            Player player = event.getPlayer();

            if (EnchantsSquared.isWorldGuardAllowed(player, player.getLocation(), "es-deny-all")){
                EntityEquipment equipment = EntityEquipmentCacheManager.getInstance().getAndCacheEquipment(player);
                for (CustomEnchant enchantment : CustomEnchantManager.getInstance().getEnchantmentsMatchingFilter(c -> c instanceof TriggerOnItemDamageEnchantment)){
                    TriggerOnItemDamageEnchantment damageEnchantment = (TriggerOnItemDamageEnchantment) enchantment;
                    damageEnchantment.onItemDamage(event, enchantment.getLevelService(false, player).getLevel(equipment));
                }
            }
        }
    }
}
