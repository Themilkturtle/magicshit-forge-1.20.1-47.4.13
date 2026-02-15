package net.themilkturtle.magical.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.configurations.TwistingVinesConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.themilkturtle.magical.Magical;

import net.themilkturtle.magical.item.ModItems;
import net.themilkturtle.magical.world.level.block.BulbVinesBlock;
import net.themilkturtle.magical.world.level.block.BulbVinesPlantBlock;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Magical.MOD_ID);




    public static final RegistryObject<Block> BULB_VINE_PLANT = registerBlock("bulb_vine_plant",
            () -> new BulbVinesPlantBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES_PLANT).noOcclusion()));

    public static final RegistryObject<Block> BULB_VINE = registerBlock("bulb_vine",
            () -> new BulbVinesBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES).noOcclusion()));



    public static final RegistryObject<Block> SICLE = registerBlock("sicle",
            () -> new FlowerBlock(() -> MobEffects.CONFUSION, 5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));


    public static final RegistryObject<Block> POTTED_SICLE = BLOCKS.register("potted_sicle",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.SICLE,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}




