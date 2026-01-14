package net.themilkturtle.magical.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.themilkturtle.magical.Magical;

public class ModCreativeModeTabs {


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Magical.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAGICAL_TAB = CREATIVE_MOD_TABS.register("magical_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ROSEQUARTZ.get()))

                    .title(Component.translatable("creativetab.magical_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ROSEQUARTZ.get());
                        pOutput.accept(ModItems.SCRYINGLENS.get());
                    })

                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
