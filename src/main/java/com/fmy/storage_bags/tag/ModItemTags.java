package com.fmy.storage_bags.tag;

import com.fmy.storage_bags.StorageBags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author 宛
 * @version 1.0
 */
public class ModItemTags {
    public static final TagKey<Item> CARPENTER_TABLE_MATERIAL = bind("carpenter_table_material");
    public static final TagKey<Item> STORAGE_BAG = bind("storage_bag");
    public static final TagKey<Item> PLUGIN = bind("plugin");
    public static final TagKey<Item> FILL_BLACKLIST = bind("fill_blacklist");

    //public static final TagKey<Item> POWDERED_ROSE = bind("powdered_rose");
    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(StorageBags.MOD_ID, pName));
    }

}
