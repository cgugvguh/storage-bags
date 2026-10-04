package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.item.ModItem.ModItems;
import com.fmy.storage_bags.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author 宛
 * @version 1.0
 */
public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, StorageBags.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModItemTags.CARPENTER_TABLE_MATERIAL)
                .add(Items.STICK);
        tag(ModItemTags.STORAGE_BAG)
                .add(ModItems.CUTTING_TREE_STORAGE_BAG.get())
                .add(ModItems.MINING_ORE_STORAGE_BAG.get())
                .add(ModItems.PLANT_STORAGE_BAG.get())
                .add(ModItems.INDUSTRY_STORAGE_BAG.get())
                .add(ModItems.FISHING_STORAGE_BAG.get())
                .add(ModItems.BUTTLE_STORAGE_BAG.get())
                .add(ModItems.BUILDING_STORAGE_BAG.get())
                .add(ModItems.HUSBANDRY_STORAGE_BAG.get())
                .add(ModItems.CUSTOM_STORAGE_BAG.get());
        tag(ModItemTags.PLUGIN)
                .add(ModItems.IRON_EXPAND_PLUGIN.get())
                .add(ModItems.GORDEN_EXPAND_PLUGIN.get())
                .add(ModItems.DIAMOND_EXPAND_PLUGIN.get())
                .add(ModItems.NETHERITE_EXPAND_PLUGIN.get())
                .add(ModItems.INFINITE_EXPAND_PLUGIN.get())
                .add(ModItems.IRON_FILL_PLUGIN.get())
                .add(ModItems.GORDEN_FILL_PLUGIN.get())
                .add(ModItems.DIAMOND_FILL_PLUGIN.get())
                .add(ModItems.NETHERITE_FILL_PLUGIN.get())
                .add(ModItems.INFINITE_FILL_PLUGIN.get());

       /* tag(ModItemTags.SUGAR_TAG)
                .add(Items.BEETROOT)
                .add(ModItems.CHOCOLATE.get());*/


    }
}
