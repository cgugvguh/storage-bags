package com.fmy.storage_bags.datagen;

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

        add(ModItems.CUTTING_TREE_STORAGE_BAG.get(), "伐木储物袋");
        add(ModItems.MINING_ORE_STORAGE_BAG.get(), "挖矿储物袋");
        add(ModItems.PLANT_STORAGE_BAG.get(), "种田储物袋");
        add(ModItems.INDUSTRY_STORAGE_BAG.get(), "红石物品储物袋");
        add(ModItems.FISHING_STORAGE_BAG.get(), "鱼获储物袋");
        add(ModItems.BATTLE_STORAGE_BAG.get(), "怪物掉落物储物袋");
        add(ModItems.BUILDING_STORAGE_BAG.get(), "建筑储物袋");
        add(ModItems.HUSBANDRY_STORAGE_BAG.get(), "养殖储物袋");
        add(ModItems.CUSTOM_STORAGE_BAG.get(), "自定义储物袋");

        add(ModItems.IRON_EXPAND_PLUGIN.get(), "铁扩容插件");
        add(ModItems.GORDEN_EXPAND_PLUGIN.get(), "金扩容插件");
        add(ModItems.DIAMOND_EXPAND_PLUGIN.get(), "钻石扩容插件");
        add(ModItems.NETHERITE_EXPAND_PLUGIN.get(), "下界合金扩容插件");
        add(ModItems.INFINITE_EXPAND_PLUGIN.get(), "无限扩容插件");
        add(ModItems.IRON_FILL_PLUGIN.get(), "铁填充插件");
        add(ModItems.GORDEN_FILL_PLUGIN.get(), "金填充插件");
        add(ModItems.DIAMOND_FILL_PLUGIN.get(), "钻石填充插件");
        add(ModItems.NETHERITE_FILL_PLUGIN.get(), "下界合金填充插件");
        add(ModItems.INFINITE_FILL_PLUGIN.get(), "无限填充插件");

        add("storage_bags.plugin.description", "将储物袋放在副手右键使用");

        add("stat.portable_tools_kit.use_storage_bag", "使用储物袋");
        add("itemGroup.storage_bag", "便携储物袋");

        add("container.cutting_tree_storage_bag", "伐木储物袋");
        add("container.mining_ore_storage_bag", "挖矿储物袋");
        add("container.plant_storage_bag", "种田储物袋");
        add("container.industry_storage_bag", "红石物品储物袋");
        add("container.fishing_storage_bag", "鱼获储物袋");
        add("container.battle_storage_bag", "怪物掉落物储物袋");
        add("container.building_storage_bag", "建筑储物袋");
        add("container.husbandry_storage_bag", "养殖储物袋");
        add("container.custom_storage_bag", "自定义储物袋");

        add("tooltip.storage_bags.description", "按住 shift 以了解更多");
        add("tooltip.storage_bags.description.custom", "将自定义储物袋放在副手，主手拿要存储的物品右键可以增加种类。" +
                "主手为空会清空所有数量为 0 的类型。蹲下可以直接添加种类并存入。");
        add("tooltip.storage_bags.description.cutting_tree", "此类储物袋可以存一些木头。");
        add("tooltip.storage_bags.description.mining_ore", "此类储物袋可以存一些矿物。");
        add("tooltip.storage_bags.description.plant", "此类储物袋可以存一些农作物和种子。");
        add("tooltip.storage_bags.description.industry", "此类储物袋可以存一些红石物品。");
        add("tooltip.storage_bags.description.fishing", "此类储物袋可以存一些钓鱼战利品。");
        add("tooltip.storage_bags.description.battle", "此类储物袋可以存一些怪物掉落物。");
        add("tooltip.storage_bags.description.building", "此类储物袋可以存一些建筑类方块。");
        add("tooltip.storage_bags.description.husbandry", "此类储物袋可以存一些动物掉落物。");

        add("storage_bags.screen.button.confirm", "确定");
        add("storage_bags.screen.button.take_all", "全取");
        add("storage_bags.screen.button.save_all", "全存");


        /*add(ModItems.POWDERED_ROSE_INGOT.get(),"粉霞锭");
        add("itemGroup.funny_items", "有趣小玩意");*/

    }
}
