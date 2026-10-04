package com.fmy.storage_bags.Client.event;

import com.fmy.storage_bags.Internet.ModNetwork;
import com.fmy.storage_bags.StorageBags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @author 宛
 * @version 1.0
 */
@Mod.EventBusSubscriber(modid = StorageBags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModNetwork::register);
    }
}
