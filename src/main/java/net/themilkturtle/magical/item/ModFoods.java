package net.themilkturtle.magical.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoods {



    public static final FoodProperties PITCHER_OIL = new FoodProperties.Builder().nutrition(-1).fast()
            .saturationMod(-0.2f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1f).alwaysEat().build();

    public static final FoodProperties BULB_BERRIES = new FoodProperties.Builder().nutrition(-1).fast()
            .saturationMod(-0.2f).effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 200), 1f).build();

    public static final FoodProperties TORCHFLOWER_OIL = new FoodProperties.Builder().nutrition(-1).fast()
            .saturationMod(-0.2f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1f).alwaysEat().build();

    public static final FoodProperties BULB_VINE_OIL = new FoodProperties.Builder().nutrition(-1).fast()
            .saturationMod(-0.2f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1f).alwaysEat().build();


    public static final FoodProperties SICLE_OIL = new FoodProperties.Builder().nutrition(-1).fast()
            .saturationMod(-0.2f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1f).alwaysEat().build();





}