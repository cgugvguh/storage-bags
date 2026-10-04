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
public class StorageActionPacket {
    public final int amount;

    public StorageActionPacket(int amount) {
        this.amount = amount;
    }

    public static void encode(StorageActionPacket msg, FriendlyByteBuf buf) {
        buf.writeVarInt(msg.amount);
    }

    public static StorageActionPacket decode(FriendlyByteBuf buf) {
        return new StorageActionPacket(buf.readVarInt());
    }

    public static void handle(StorageActionPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            int amount = msg.amount;
            if (amount < 0) amount = 0;
            if (amount > 1_000_000) amount = 1_000_000;

            if (player.containerMenu instanceof StorageBagMenu menu) {
                menu.handleInputAmount(player, amount);
            }
        });
        context.setPacketHandled(true);
    }
}