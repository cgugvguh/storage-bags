package com.fmy.storage_bags.Internet;

import com.fmy.storage_bags.menu.StorageBagMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author 宛
 * @version 1.0
 */
public class ModeChangePacket {
    public ModeChangePacket() {
    }

    public static void encode(ModeChangePacket msg, FriendlyByteBuf buf) {
        // 无参数
    }

    public static ModeChangePacket decode(FriendlyByteBuf buf) {
        return new ModeChangePacket();
    }

    public static void handle(ModeChangePacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            if (player.containerMenu instanceof StorageBagMenu menu) {
                menu.changeBagMode(player);
            }
        });
        context.setPacketHandled(true);
    }
}