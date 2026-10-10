package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.item.ModItem.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * @author 宛
 * @version 1.0
 */
//生成英语翻译文件
//位于 resources/assets/my_first_mod/lang
public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, StorageBags.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        /*add(ModItems.ICE_ETHER.get(),"Ice Ether");
        add("itemGroup.funny_items", "Funny Items");*/
        add(ModItems.CUTTING_TREE_STORAGE_BAG.get(), "Wood Storage Bag");
        add(ModItems.MINING_ORE_STORAGE_BAG.get(), "Ore Storage Bag");
        add(ModItems.PLANT_STORAGE_BAG.get(), "Farming Storage Bag");
        add(ModItems.INDUSTRY_STORAGE_BAG.get(), "Redstone Storage Bag");
        add(ModItems.FISHING_STORAGE_BAG.get(), "Fishing Storage Bag");
        add(ModItems.BATTLE_STORAGE_BAG.get(), "Mob Drop Item Storage Bag");
        add(ModItems.BUILDING_STORAGE_BAG.get(), "Building Storage Bag");
        add(ModItems.HUSBANDRY_STORAGE_BAG.get(), "Husbandry Storage Bag");
        add(ModItems.CUSTOM_STORAGE_BAG.get(), "Custom Storage Bag");

        add(ModItems.IRON_EXPAND_PLUGIN.get(), "Iron Expand Plugin");
        add(ModItems.GORDEN_EXPAND_PLUGIN.get(), "Gorden Expand Plugin");
        add(ModItems.DIAMOND_EXPAND_PLUGIN.get(), "Diamond Expand Plugin");
        add(ModItems.NETHERITE_EXPAND_PLUGIN.get(), "Netherite Expand Plugin");
        add(ModItems.INFINITE_EXPAND_PLUGIN.get(), "Infinite Expand Plugin");
        add(ModItems.IRON_FILL_PLUGIN.get(), "Iron Fill Plugin");
        add(ModItems.GORDEN_FILL_PLUGIN.get(), "Gorden Fill Plugin");
        add(ModItems.DIAMOND_FILL_PLUGIN.get(), "Diamond Fill Plugin");
        add(ModItems.NETHERITE_FILL_PLUGIN.get(), "Netherite Fill Plugin");
        add(ModItems.INFINITE_FILL_PLUGIN.get(), "Infinite Fill Plugin");

        add("storage_bags.plugin.description", "Take storage bags in your offhand and right click for use.");
        add("stat.portable_tools_kit.use_storage_bag", "Used Storage Bag");
        add("itemGroup.storage_bag", "Storage Bag");

        add("container.cutting_tree_storage_bag", "Wood Storage Bag");
        add("container.mining_ore_storage_bag", "Ore Storage Bag");
        add("container.plant_storage_bag", "Farming Storage Bag");
        add("container.industry_storage_bag", "Redstone Storage Bag");
        add("container.fishing_storage_bag", "Fishing Storage Bag");
        add("container.battle_storage_bag", "Mob Drop Item Storage Bag");
        add("container.building_storage_bag", "Building Storage Bag");
        add("container.husbandry_storage_bag", "Husbandry Storage Bag");
        add("container.custom_storage_bag", "Custom Storage Bag");

        add("tooltip.storage_bags.description", "Press shift to get more information");
        add("tooltip.storage_bags.description.custom", "Put custom storage bag in your offhand " +
                "and take your items in your main hand can add kinds you want to storage. " +
                "The types with a quantity of 0 will be deleted. If you are crouching, " +
                "all item in your offhand will be stored directly.");
        add("tooltip.storage_bags.description.cutting_tree", "It can hole some woods.");
        add("tooltip.storage_bags.description.mining_ore", "It can hold some minerals.");
        add("tooltip.storage_bags.description.plant", "It can hold some seeds and crops.");
        add("tooltip.storage_bags.description.industry", "It can hold some redstone items.");
        add("tooltip.storage_bags.description.fishing", "It can hold some fishing spoils.");
        add("tooltip.storage_bags.description.battle", "It can hold some mob drop items.");
        add("tooltip.storage_bags.description.building", "It can hold some building blocks.");
        add("tooltip.storage_bags.description.husbandry", "It can hold some animal drop items.");

        add("storage_bags.screen.button.confirm", "Yes");
        add("storage_bags.screen.button.take_all", "Take");
        add("storage_bags.screen.button.save_all", "Save");
    }
}
