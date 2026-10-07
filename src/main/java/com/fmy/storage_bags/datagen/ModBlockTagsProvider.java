package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.Block.ModBlocks;
import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.tag.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author 宛
 * @version 1.0
 */
//生成方块的标签，例如需要挖掘工具和需要什么挖掘工具
//位于 resources/data/minecraft/tags/blocks
public class ModBlockTagsProvider extends BlockTagsProvider {//方块标签
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StorageBags.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        /*tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_ICE_ETHER_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL) 原版标签
                .add(ModBlocks.RAW_ICE_ETHER_BLOCK.get());
        tag(ModBlockTags.ORE_TAGS) 自定义标签
                .add(ModBlocks.RAW_ICE_ETHER_BLOCK.get())
                .addTag(BlockTags.COAL_ORES);
                原版物品不用get*/

    }
}
