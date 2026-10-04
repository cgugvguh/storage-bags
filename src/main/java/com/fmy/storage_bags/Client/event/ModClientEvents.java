package com.fmy.storage_bags.Client.event;

import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.menu.ModMenuTypes;
import com.fmy.storage_bags.menu.StorageBagScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author 宛
 * @version 1.0
 */
@Mod.EventBusSubscriber(modid = StorageBags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.STORAGE_BAG_MENU.get(), StorageBagScreen::new);
        });
    }
}