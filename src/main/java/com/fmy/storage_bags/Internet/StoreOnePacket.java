package com.fmy.storage_bags.Internet;

import com.fmy.storage_bags.menu.StorageBagMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author 宛
 * @version 1.0
 */
public class StoreOnePacket {

    public static void encode(StoreOnePacket msg, FriendlyByteBuf buf) {

    }

    public static StoreOnePacket decode(FriendlyByteBuf buf) {
        return new StoreOnePacket();
    }

    public static void handle(StoreOnePacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            if (player.containerMenu instanceof StorageBagMenu menu) {
                menu.storeOneFromInventory(player);
            }
        });
        context.setPacketHandled(true);
    }
}
