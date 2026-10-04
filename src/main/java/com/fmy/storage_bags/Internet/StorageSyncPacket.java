package com.fmy.storage_bags.Internet;

import com.fmy.storage_bags.menu.StorageBagMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author 宛
 * @version 1.0
 */
public class StorageSyncPacket {
    private final String kindName;
    private final Map<Item, Integer> data;

    public StorageSyncPacket(String kindName, Map<Item, Integer> data) {
        this.kindName = kindName;
        this.data = data;
    }

    public static void encode(StorageSyncPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.kindName);
        buf.writeVarInt(msg.data.size());
        for (Map.Entry<Item, Integer> e : msg.data.entrySet()) {
            buf.writeUtf(BuiltInRegistries.ITEM.getKey(e.getKey()).toString());
            buf.writeVarInt(e.getValue());
        }
    }

    public static StorageSyncPacket decode(FriendlyByteBuf buf) {
        String kindName = buf.readUtf();
        int size = buf.readVarInt();
        Map<Item, Integer> data = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            String key = buf.readUtf();
            int count = buf.readVarInt();
            Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(key));
            data.put(item, count);
        }
        return new StorageSyncPacket(kindName, data);
    }

    public static void handle(StorageSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            // 客户端收到，更新当前打开的菜单
            if (Minecraft.getInstance().player != null
                    && Minecraft.getInstance().player.containerMenu instanceof StorageBagMenu menu) {
                menu.updateStorageFromServer(msg.kindName, msg.data);
            }
        });
        context.setPacketHandled(true);
    }
}
