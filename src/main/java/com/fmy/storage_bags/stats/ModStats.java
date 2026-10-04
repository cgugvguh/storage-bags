package com.fmy.storage_bags.stats;

import com.fmy.storage_bags.StorageBags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author 宛
 * @version 1.0
 */
public class ModStats {
    public static final DeferredRegister<ResourceLocation> STATS =
            DeferredRegister.create(Registries.CUSTOM_STAT, StorageBags.MOD_ID);
    public static final RegistryObject<ResourceLocation> USE_STORAGE_BAG =
            STATS.register("use_storage_bag",
                    () -> new ResourceLocation(StorageBags.MOD_ID, "use_storage_bag"));

    // 3. 在模组构造时把注册器挂到事件总线
    public static void register(IEventBus eventBus) {
        STATS.register(eventBus);
    }
}
