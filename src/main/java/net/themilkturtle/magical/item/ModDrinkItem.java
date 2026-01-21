package net.themilkturtle.magical.item;


import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class ModDrinkItem extends Item {

    public ModDrinkItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);

            if (!player.getInventory().add(bottle)) {
                player.drop(bottle, false);
            }
        }

        return super.finishUsingItem(stack, level, entity);
    }


    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}