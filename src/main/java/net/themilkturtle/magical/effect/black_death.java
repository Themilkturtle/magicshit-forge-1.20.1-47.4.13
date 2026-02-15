package net.themilkturtle.magical.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class black_death extends MobEffect {
    public black_death(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.getHealth() > 0.5F) {
            pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 0.1F);
            ((Player)pLivingEntity).causeFoodExhaustion(0.005F * (float)(pAmplifier + 1));

        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }







}
