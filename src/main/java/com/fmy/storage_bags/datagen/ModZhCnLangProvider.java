package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.Block.ModBlocks;
import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.item.ModItem.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * @author 宛
 * @version 1.0
 */
//生成中文翻译文件
//位于 resources/assets/my_first_mod/lang
public class ModZhCnLangProvider extends LanguageProvider {
    public ModZhCnLangProvider(PackOutput output) {
        super(output, StorageBags.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {

        add(ModItems.CUTTING_TREE_STORAGE_BAG.get(),"伐木储物袋");
        add(ModItems.MINING_ORE_STORAGE_BAG.get(),"挖矿储物袋");
        add(ModItems.PLANT_STORAGE_BAG.get(),"种田储物袋");
        add(ModItems.INDUSTRY_STORAGE_BAG.get(),"红石物品储物袋");
        add(ModItems.FISHING_STORAGE_BAG.get(),"鱼获储物袋");
        add(ModItems.BUTTLE_STORAGE_BAG.get(),"怪物掉落物储物袋");
        add(ModItems.BUILDING_STORAGE_BAG.get(),"建筑储物袋");
        add(ModItems.HUSBANDRY_STORAGE_BAG.get(),"养殖储物袋");
        add(ModItems.CUSTOM_STORAGE_BAG.get(),"自定义储物袋");

        add(ModItems.IRON_EXPAND_PLUGIN.get(),"铁扩容插件");
        add(ModItems.GORDEN_EXPAND_PLUGIN.get(),"金扩容插件");
        add(ModItems.DIAMOND_EXPAND_PLUGIN.get(),"钻石扩容插件");
        add(ModItems.NETHERITE_EXPAND_PLUGIN.get(),"下界合金扩容插件");
        add(ModItems.INFINITE_EXPAND_PLUGIN.get(),"无限扩容插件");
        add(ModItems.IRON_FILL_PLUGIN.get(),"铁填充插件");
        add(ModItems.GORDEN_FILL_PLUGIN.get(),"金填充插件");
        add(ModItems.DIAMOND_FILL_PLUGIN.get(),"钻石填充插件");
        add(ModItems.NETHERITE_FILL_PLUGIN.get(),"下界合金填充插件");
        add(ModItems.INFINITE_FILL_PLUGIN.get(),"无限填充插件");

        add("stat.portable_tools_kit.use_storage_bag","使用储物袋");

        add("container.cutting_tree_storage_bag","伐木储物袋");
        add("container.mining_ore_storage_bag","挖矿储物袋");
        add("container.plant_storage_bag","种田储物袋");
        add("container.industry_storage_bag","红石物品储物袋");
        add("container.fishing_storage_bag","鱼获储物袋");
        add("container.buttle_storage_bag","怪物掉落物储物袋");
        add("container.building_storage_bag","建筑储物袋");
        add("container.husbandry_storage_bag","养殖储物袋");
        add("container.custom_storage_bag","自定义储物袋");

        /*add(ModItems.POWDERED_ROSE_INGOT.get(),"粉霞锭");
        add("itemGroup.funny_items", "有趣小玩意");*/

    }
}
