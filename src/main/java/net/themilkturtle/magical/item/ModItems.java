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


    public static final RegistryObject<Item> BULB_BERRIES = ITEMS.register("bulb_berries",
            () -> new Item(new Item.Properties().food(ModFoods.BULB_BERRIES)));

    public static final RegistryObject<Item> HORSE_RADISH = ITEMS.register("horse_radish",
            () -> new Item(new Item.Properties().food(ModFoods.HORSE_RADISH)));

    public static final RegistryObject<Item> RADISH = ITEMS.register("radish",
            () -> new Item(new Item.Properties().food(ModFoods.RADISH)));



    public static final RegistryObject<Item> MINI_LEMON_TART = ITEMS.register("mini_lemon_tart",
            () -> new Item(new Item.Properties().food(ModFoods.MINI_LEMON_TART)));


    public static final RegistryObject<Item> ROSEDUST = ITEMS.register("rose_dust",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> NET = ITEMS.register("net",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SICLE_SEED = ITEMS.register("sicle_seed",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SLING_SHOT = ITEMS.register("sling_shot",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> COPPER_RING = ITEMS.register("copper_ring",
            () -> new Item(new Item.Properties().stacksTo(1)));




    public static final RegistryObject<Item> BASIC_WOOD_STAFF_CIRCLE = ITEMS.register("basic_wood_staff_circle",
            () -> new StaffTypeSystemItem(new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> BASIC_WOOD_STAFF_BLADE = ITEMS.register("basic_wood_staff_blade",
            () -> new StaffTypeSystemItem(new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> BASIC_WOOD_STAFF_CLUB = ITEMS.register("basic_wood_staff_club",
            () -> new StaffTypeSystemItem(new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> BASIC_WOOD_STAFF_HOOK = ITEMS.register("basic_wood_staff_hook",
            () -> new StaffTypeSystemItem(new Item.Properties().stacksTo(1)));






    public static final RegistryObject<Item> IRON_RING = ITEMS.register("iron_ring",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PAINT_BRUSH = ITEMS.register("paint_brush",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRYINGLENS = ITEMS.register("scrying_lens",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PITCHER_OIL = ITEMS.register("pitcher_oil",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(4).food(ModFoods.PITCHER_OIL)));

    public static final RegistryObject<Item> VERY_OLD_WINE = ITEMS.register("very_old_wine",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.VERY_OLD_WINE)));

    public static final RegistryObject<Item> BEER_MUG = ITEMS.register("beer_mug",
            () -> new ModDrinkItemMug(new Item.Properties().stacksTo(1).food(ModFoods.ALCOHOLS)));

    public static final RegistryObject<Item> WHITE_WINE = ITEMS.register("white_wine",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.ALCOHOLS)));

    public static final RegistryObject<Item> RED_WINE = ITEMS.register("red_wine",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.ALCOHOLS)));

    public static final RegistryObject<Item> VODKA = ITEMS.register("vodka",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.ALCOHOLS)));

    public static final RegistryObject<Item> EMPTY_MUG = ITEMS.register("empty_mug",
            () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> TORCHFLOWER_OIL = ITEMS.register("torchflower_oil",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(4).food(ModFoods.TORCHFLOWER_OIL)));

    public static final RegistryObject<Item> SICLE_OIL = ITEMS.register("sicle_oil",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(4).food(ModFoods.SICLE_OIL)));

    public static final RegistryObject<Item> RADISH_AND_BEEF_STEW = ITEMS.register("radish_and_beef_stew",
            () -> new ModBowltem(new Item.Properties().stacksTo(1).food(ModFoods.RADISH_AND_BEEF_STEW)));

    public static final RegistryObject<Item> BULB_VINE_OIL = ITEMS.register("bulb_vine_oil",
            () -> new ModDrinkItem(new Item.Properties().stacksTo(4).food(ModFoods.BULB_VINE_OIL)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
