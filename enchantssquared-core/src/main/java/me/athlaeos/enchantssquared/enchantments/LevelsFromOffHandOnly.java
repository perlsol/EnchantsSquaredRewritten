package me.athlaeos.enchantssquared.enchantments;

import me.athlaeos.enchantssquared.domain.EntityEquipment;

public class LevelsFromOffHandOnly extends LevelService {
    public LevelsFromOffHandOnly(CustomEnchant customEnchant) {
        super(customEnchant);
    }

    @Override
    public int getLevel(EntityEquipment equipment) {
        if (!compatible(equipment.getOffHand())) return 0;
        return equipment.getOffHandEnchantments().getOrDefault(customEnchant, 0);
    }
}
