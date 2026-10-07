package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.StorageBags;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author 宛
 * @version 1.0
 */
//生成方块的状态文件以及model文件
//状态文件位于 resources/assets/my_first_mod/blockstates
//model 文件位于 resources/assets/my_first_mod/models/block
public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, StorageBags.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //生成方块状态文件，模型文件
        //cubeAll(Block block)每一面都用一个贴图
        //simpleBlockWithItem(ModBlocks.ICE_ETHER_BLOCK.get(),cubeAll(ModBlocks.ICE_ETHER_BLOCK.get()));
    }
}
