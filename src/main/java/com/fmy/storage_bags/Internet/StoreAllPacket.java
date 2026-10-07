package com.fmy.storage_bags.Internet;

import com.fmy.storage_bags.item.StorageBag.StorageUtil;
import com.fmy.storage_bags.menu.StorageBagMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author 宛
 * @version 1.0
 */
public class StoreAllPacket {
    public StoreAllPacket() {}

    public static void encode(StoreAllPacket msg, FriendlyByteBuf buf) {
        // 无参数
    }

    public static StoreAllPacket decode(FriendlyByteBuf buf) {
        return new StoreAllPacket();
    }

    public static void handle(StoreAllPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            if (player.containerMenu instanceof StorageBagMenu menu) {
                menu.storeAllFromInventory(player);
            }
        });
        context.setPacketHandled(true);
    }
}
