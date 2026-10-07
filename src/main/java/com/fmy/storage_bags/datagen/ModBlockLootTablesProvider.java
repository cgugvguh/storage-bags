package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.Block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

/**
 * @author 宛
 * @version 1.0
 */
//生成方块的战利品列表 json 文件
//位于 resources/data/my_first_mod/loot_tables/blocks
public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    public ModBlockLootTablesProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        /*// dropSelf 掉落方块本身
        dropSelf(ModBlocks.ICE_ETHER_BLOCK.get());
        //矿石写法
        add(ModBlocks.RAW_ICE_ETHER_BLOCK.get(), block ->
                createOreLikeDrops(ModBlocks.RAW_ICE_ETHER_BLOCK.get(),
                ModItems.RAW_ICE_ETHER.get(),2.0F,5.0F));*/
    }
    protected LootTable.Builder createOreLikeDrops(Block pBlock,Item pItem,Float pMin,Float pMax) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pItem)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(pMin, pMax)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
