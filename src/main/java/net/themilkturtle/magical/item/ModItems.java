package net.themilkturtle.magical.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.themilkturtle.magical.Magical;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Magical.MOD_ID);


    public static final RegistryObject<Item> ROSEQUARTZ = ITEMS.register("rose_quartz",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRYINGLENS = ITEMS.register("scrying_lens",
            () -> new Item(new Item.Properties().stacksTo(1)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
