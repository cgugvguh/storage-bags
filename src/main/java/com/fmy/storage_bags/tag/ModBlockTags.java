package com.fmy.storage_bags.tag;

import com.fmy.storage_bags.StorageBags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * @author 宛
 * @version 1.0
 */
public class ModBlockTags {
    //public static final TagKey<Block> ORE_TAGS = create("ore_tags");
    private static TagKey<Block> create(String pName) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(StorageBags.MOD_ID, pName));
    }
    private static TagKey<Block> createForgeTag(String pName) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation("forge", pName));
    }
}

