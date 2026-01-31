package net.themilkturtle.magical.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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


    public static final RegistryObject<Item> ROSEDUST = ITEMS.register("rose_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PAINT_BRUSH = ITEMS.register("paint_brush",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRYINGLENS = ITEMS.register("scrying_lens",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PITCHER_OIL = ITEMS.register("pitcher_oil",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(4).food(ModFoods.PITCHER_OIL)));

    public static final RegistryObject<Item> TORCHFLOWER_OIL = ITEMS.register("torchflower_oil",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(4).food(ModFoods.TORCHFLOWER_OIL)));

    public static final RegistryObject<Item> SICLE_OIL = ITEMS.register("sicle_oil",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(4).food(ModFoods.SICLE_OIL)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
