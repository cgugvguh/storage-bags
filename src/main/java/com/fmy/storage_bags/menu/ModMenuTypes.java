package com.fmy.storage_bags.menu;

import com.fmy.storage_bags.StorageBags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author 宛
 * @version 1.0
 */
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, StorageBags.MOD_ID);
    public static final RegistryObject<MenuType<StorageBagMenu>> STORAGE_BAG_MENU =
            MENU_TYPES.register("storage_bag",
                    () -> IForgeMenuType.create(StorageBagMenu::new));


    // 3. 提供注册到事件总线的方法
    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
