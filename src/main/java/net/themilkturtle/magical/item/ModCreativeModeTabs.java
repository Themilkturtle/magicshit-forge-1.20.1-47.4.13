package net.themilkturtle.magical.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.themilkturtle.magical.Magical;
import net.themilkturtle.magical.block.ModBlocks;

public class ModCreativeModeTabs {


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Magical.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAGICAL_TAB = CREATIVE_MOD_TABS.register("magical_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ROSEQUARTZ.get()))

                    .title(Component.translatable("creativetab.magical_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ROSEQUARTZ.get());
                        pOutput.accept(ModItems.SCRYINGLENS.get());
                        pOutput.accept(ModItems.ROSEDUST.get());
                        pOutput.accept(ModItems.PITCHER_OIL.get());
                        pOutput.accept(ModItems.PAINT_BRUSH.get());
                        pOutput.accept(ModItems.TORCHFLOWER_OIL.get());
                        pOutput.accept(ModItems.SICLE_OIL.get());
                        pOutput.accept(ModBlocks.SICLE.get());
                    })

                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
