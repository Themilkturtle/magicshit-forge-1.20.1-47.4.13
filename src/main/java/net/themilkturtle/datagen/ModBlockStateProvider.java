package net.themilkturtle.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.themilkturtle.magical.Magical;
import net.themilkturtle.magical.block.ModBlocks;
import net.themilkturtle.magical.world.level.block.BulbVinesBlock;
import net.themilkturtle.magical.world.level.block.BulbVinesPlantBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Magical.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlockWithItem(ModBlocks.SICLE.get(), models().cross(blockTexture(ModBlocks.SICLE.get()).getPath(),
                blockTexture(ModBlocks.SICLE.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.POTTED_SICLE.get(),
                models().singleTexture("potted_catmint",
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/flower_pot_cross"),
                        "plant",
                        blockTexture(ModBlocks.SICLE.get())
                ).renderType("cutout"));

        // Bulb vines: head and plant with berry variants
        var bulbModel = models().cross("bulb_vine", blockTexture(ModBlocks.BULB_VINE.get())).renderType("cutout");
        var bulbBerryless = models().cross("bulb_vine_berryless", ResourceLocation.fromNamespaceAndPath(Magical.MOD_ID, "block/bulb_vine_berryless")).renderType("cutout");
        var bulbPlant = models().cross("bulb_vine_plant", ResourceLocation.fromNamespaceAndPath(Magical.MOD_ID, "block/bulb_vine_plant")).renderType("cutout");
        var bulbPlantBerryless = models().cross("bulb_vine_plant_berryless", ResourceLocation.fromNamespaceAndPath(Magical.MOD_ID, "block/bulb_vine_plant_berryless")).renderType("cutout");

        // Blockstates: map BERRIES property to corresponding models
        getVariantBuilder(ModBlocks.BULB_VINE.get()).forAllStates(state -> {
            boolean berries = state.getValue(BulbVinesBlock.BERRIES);
            return ConfiguredModel.builder().modelFile(berries ? bulbModel : bulbBerryless).build();
        });

        getVariantBuilder(ModBlocks.BULB_VINE_PLANT.get()).forAllStates(state -> {
            boolean berries = state.getValue(BulbVinesPlantBlock.BERRIES);
            return ConfiguredModel.builder().modelFile(berries ? bulbPlant : bulbPlantBerryless).build();
        });


    }


}