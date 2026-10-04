package com.fmy.storage_bags;

import com.fmy.storage_bags.item.ModItem.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author 宛
 * @version 1.0
 */
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StorageBags.MOD_ID);
    //注册一个CREATIVE_MODE_TABS原版注册表,标注所属模组
    /*public static final RegistryObject<CreativeModeTab>  MY_FIRST_MOD_TAB =
            //调用注册表的方法注册一个物品栏
            CREATIVE_MODE_TABS.register("my_first_mod_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.ICE_ETHER.get()))
                            //物品栏图标文件,物品数据存在了ItemStack里面,包括材质,这里获取了Ice ether的数据作为图标
                            .title(Component.translatable("itemGroup.my_first_mod_tab"))
                            //用来翻译,系统在lang寻找对应语言的翻译
                            .displayItems((pParameters, pOutput) -> {
                                //物品栏展示的物品
                                pOutput.accept(ModItems.ICE_ETHER.get());
                                pOutput.accept(ModItems.RAW_ICE_ETHER.get());
                                pOutput.accept(ModItems.DIAMOND_FINDER.get());

                            }).build());*/
    public static final RegistryObject<CreativeModeTab> STORAGE_BAGS =
            //调用注册表的方法注册一个物品栏
            CREATIVE_MODE_TABS.register("storage_bags",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.CUTTING_TREE_STORAGE_BAG.get()))
                            //物品栏图标文件,物品数据存在了ItemStack里面,包括材质,这里获取了Ice ether的数据作为图标
                            .title(Component.translatable("itemGroup.storage_bags"))
                            //用来翻译,系统在lang寻找对应语言的翻译
                            .displayItems((pParameters, pOutput) -> {
                                //物品栏展示的物品
                                pOutput.accept(ModItems.CUTTING_TREE_STORAGE_BAG.get());
                                pOutput.accept(ModItems.MINING_ORE_STORAGE_BAG.get());
                                pOutput.accept(ModItems.PLANT_STORAGE_BAG.get());
                                pOutput.accept(ModItems.INDUSTRY_STORAGE_BAG.get());
                                pOutput.accept(ModItems.FISHING_STORAGE_BAG.get());
                                pOutput.accept(ModItems.BUTTLE_STORAGE_BAG.get());
                                pOutput.accept(ModItems.BUILDING_STORAGE_BAG.get());
                                pOutput.accept(ModItems.HUSBANDRY_STORAGE_BAG.get());
                                pOutput.accept(ModItems.CUSTOM_STORAGE_BAG.get());

                                pOutput.accept(ModItems.IRON_EXPAND_PLUGIN.get());
                                pOutput.accept(ModItems.GORDEN_EXPAND_PLUGIN.get());
                                pOutput.accept(ModItems.DIAMOND_EXPAND_PLUGIN.get());
                                pOutput.accept(ModItems.NETHERITE_EXPAND_PLUGIN.get());
                                pOutput.accept(ModItems.INFINITE_EXPAND_PLUGIN.get());
                                pOutput.accept(ModItems.IRON_FILL_PLUGIN.get());
                                pOutput.accept(ModItems.GORDEN_FILL_PLUGIN.get());
                                pOutput.accept(ModItems.DIAMOND_FILL_PLUGIN.get());
                                pOutput.accept(ModItems.NETHERITE_FILL_PLUGIN.get());
                                pOutput.accept(ModItems.INFINITE_FILL_PLUGIN.get());

                            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}
