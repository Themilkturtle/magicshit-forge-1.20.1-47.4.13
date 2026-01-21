package net.themilkturtle.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.themilkturtle.magical.Magical;
import net.themilkturtle.magical.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Magical.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlockWithItem(ModBlocks.SICLE.get(), models().cross(blockTexture(ModBlocks.SICLE.get()).getPath(),
                blockTexture(ModBlocks.SICLE.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.POTTED_SICLE.get(), models().singleTexture("potted_catmint", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.SICLE.get())).renderType("cutout"));

    }


}