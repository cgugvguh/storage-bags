package com.fmy.storage_bags.item.ModItem;


import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.item.Plugin.ExpandPlugin;
import com.fmy.storage_bags.item.Plugin.FillPlugin;
import com.fmy.storage_bags.item.StorageBag.StorageBag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author 宛
 * @version 1.0
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StorageBags.MOD_ID);//获取forge注册器
    /*public static final RegistryObject<Item> POWDERED_ROSE_INGOT =
            ITEMS.register("powdered_rose_ingot",() -> new Item(newItem.Properties()));*/
    public static final RegistryObject<Item> CUTTING_TREE_STORAGE_BAG =
            ITEMS.register("storage_bag/cutting_tree_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "cutting_tree"));
    public static final RegistryObject<Item> MINING_ORE_STORAGE_BAG =
            ITEMS.register("storage_bag/mining_ore_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "mining_ore"));
    public static final RegistryObject<Item> PLANT_STORAGE_BAG =
            ITEMS.register("storage_bag/plant_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "plant"));
    public static final RegistryObject<Item> INDUSTRY_STORAGE_BAG =
            ITEMS.register("storage_bag/industry_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "industry"));
    public static final RegistryObject<Item> FISHING_STORAGE_BAG =
            ITEMS.register("storage_bag/fishing_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "fishing"));
    public static final RegistryObject<Item> BATTLE_STORAGE_BAG =
            ITEMS.register("storage_bag/battle_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "battle"));
    public static final RegistryObject<Item> BUILDING_STORAGE_BAG =
            ITEMS.register("storage_bag/building_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "building"));
    public static final RegistryObject<Item> HUSBANDRY_STORAGE_BAG =
            ITEMS.register("storage_bag/husbandry_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "husbandry"));
    public static final RegistryObject<Item> CUSTOM_STORAGE_BAG =
            ITEMS.register("storage_bag/custom_storage_bag", () -> new StorageBag
                    (new Item.Properties().stacksTo(1), "custom"));

    public static final RegistryObject<Item> IRON_EXPAND_PLUGIN =
            ITEMS.register("plugin/iron_expand_plugin", () -> new ExpandPlugin
                    (Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> GORDEN_EXPAND_PLUGIN =
            ITEMS.register("plugin/gorden_expand_plugin", () -> new ExpandPlugin
                    (Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_EXPAND_PLUGIN =
            ITEMS.register("plugin/diamond_expand_plugin", () -> new ExpandPlugin
                    (Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_EXPAND_PLUGIN =
            ITEMS.register("plugin/netherite_expand_plugin", () -> new ExpandPlugin
                    (Tiers.NETHERITE, new Item.Properties()));
    public static final RegistryObject<Item> INFINITE_EXPAND_PLUGIN =
            ITEMS.register("plugin/infinite_expand_plugin", () -> new ExpandPlugin
                    (ModTiers.INFINITE, new Item.Properties()));
    public static final RegistryObject<Item> IRON_FILL_PLUGIN =
            ITEMS.register("plugin/iron_fill_plugin", () -> new FillPlugin
                    (Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> GORDEN_FILL_PLUGIN =
            ITEMS.register("plugin/gorden_fill_plugin", () -> new FillPlugin
                    (Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_FILL_PLUGIN =
            ITEMS.register("plugin/diamond_fill_plugin", () -> new FillPlugin
                    (Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_FILL_PLUGIN =
            ITEMS.register("plugin/netherite_fill_plugin", () -> new FillPlugin
                    (Tiers.NETHERITE, new Item.Properties()));
    public static final RegistryObject<Item> INFINITE_FILL_PLUGIN =
            ITEMS.register("plugin/infinite_fill_plugin", () -> new FillPlugin
                    (ModTiers.INFINITE, new Item.Properties()));

    public static void register(IEventBus eventBus) {//封装注册方法到此类,方便维护
        ITEMS.register(eventBus);//调用注册器的注册方法
    }
}
