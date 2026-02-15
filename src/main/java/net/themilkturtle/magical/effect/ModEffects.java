package net.themilkturtle.magical.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.themilkturtle.magical.Magical;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Magical.MOD_ID);

public static final RegistryObject<MobEffect> BLACK_DEATH = MOB_EFFECTS.register("black_death",
        () -> new black_death(MobEffectCategory.HARMFUL, 000000));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

