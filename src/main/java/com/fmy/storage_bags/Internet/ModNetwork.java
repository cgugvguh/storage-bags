package com.fmy.storage_bags.Internet;

import com.fmy.storage_bags.StorageBags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * @author 宛
 * @version 1.0
 */
public class ModNetwork {
    private static final String VERSION = "1";
    public static SimpleChannel CHANNEL;   // ← 不要 static final，不要当场赋值

    public static void register() {
        CHANNEL = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(StorageBags.MOD_ID, "main"),
                () -> VERSION,
                VERSION::equals,
                VERSION::equals
        );

        int id = 0;
        CHANNEL.registerMessage(id++,
                StorageActionPacket.class,
                StorageActionPacket::encode,
                StorageActionPacket::decode,
                StorageActionPacket::handle);
        CHANNEL.registerMessage(id++,
                StorageSyncPacket.class,
                StorageSyncPacket::encode,
                StorageSyncPacket::decode,
                StorageSyncPacket::handle);
        CHANNEL.registerMessage(id++,
                StoreAllPacket.class,
                StoreAllPacket::encode,
                StoreAllPacket::decode,
                StoreAllPacket::handle);
        CHANNEL.registerMessage(id++,
                ModeChangePacket.class,
                ModeChangePacket::encode,
                ModeChangePacket::decode,
                ModeChangePacket::handle);
        CHANNEL.registerMessage(id++,
                StoreOnePacket.class,
                StoreOnePacket::encode,
                StoreOnePacket::decode,
                StoreOnePacket::handle);
    }
}
