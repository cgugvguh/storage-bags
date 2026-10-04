package com.fmy.storage_bags.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

/**
 * @author 宛
 * @version 1.0
 */
public class ModLootTableProvider {
    public static LootTableProvider creat(PackOutput packOutput) {
        return new LootTableProvider(packOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry
                (ModBlockLootTablesProvider::new, LootContextParamSets.BLOCK)));
    }
}
